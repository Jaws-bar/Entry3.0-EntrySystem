import axios from 'axios';

export const mutations = {
  updateGedScore: (state, payload) => {
    state.gradeInput.gedScore = payload;
  },
  updateVolunteerNAttendance: (state, { field, value }) => {
    state.gradeInput.volunteerNAttendance[field] = parseInt(value, 10);
  },
  updateGrades: (state, { grades, resetAllGrade }) => {
    const allGrades = grades;

    for (let i = 0; i < allGrades.length; i += 1) {
      for (let j = 0; j < allGrades[i].length; j += 1) {
        const resetAll = allGrades[i][j];
        // 초기화 - 점수 및 클릭 여부
        resetAll.score = resetAllGrade;
        resetAll.decided = true;
        resetAll.passed = true;
      }
    }
  },
  updateDiscompleteSemester: (state, { grades, target }) => {
    // 학기별 null로 초기화
    function reset(all, index) {
      for (let i = 0; i < all.length; i += 1) {
        const allScores = all[i];

        if (target.value === 'true') {
          allScores[index].score = '';
          allScores[index].passed = false;
          allScores[index].decided = false;
        } else {
          allScores[index].score = 'X';
          allScores[index].passed = false;
          allScores[index].decided = true;
        }
      }
    }
    // 학급 불러오기
    const allGrades = grades;

    switch (target.id) {
      case 'input-first-first':
        reset(allGrades, 0);
        break;
      case 'input-first-second':
        reset(allGrades, 1);
        break;
      case 'input-second-first':
        reset(allGrades, 2);
        break;
      case 'input-second-second':
        reset(allGrades, 3);
        break;
      case 'input-third-first':
        reset(allGrades, 4);
        break;
      case 'input-third-second':
        reset(allGrades, 5);
        break;
      default: break;
    }
  },
  updateChangeDecided: (state, { target, val }) => {
    const v = val;

    v.decided = !v.decided;
    v.passed = target.innerText === 'X' ? false : !v.passed;
    v.score = v.decided ? v.score : '';
  },
  updateIntroduce: (state, payload) => {
    state.introNPlan.introduce = payload;
  },
  updatePlan: (state, payload) => {
    state.introNPlan.plan = payload;
  },
  changeIndex: (state, payload) => {
    state.modal.index = payload.index;
  },
  updateAccept: (state, payload) => {
    state.auth.isAccept = payload;
  },
  updateEmail: (state, payload) => {
    state.auth.email = payload;
  },
  updatePw: (state, payload) => {
    state.auth.pw = payload;
  },
  updatePwCheck: (state, payload) => {
    state.auth.pwcheck = payload;
  },
  updateverify: (state, payload) => {
    state.auth.verify[payload.index] = payload.data;
  },
  updateisGED: (state, payload) => {
    state.classify.isGED = payload.data;
  },
  updateadmission: (state, payload) => {
    state.classify.admission = payload.data;
  },
  updateadmissionDetail: (state, payload) => {
    state.classify.admissionDetail = payload.data;
  },
  updateRegion: (state, payload) => {
    state.classify.region = payload.data;
  },
  updateIsGraduated: (state, payload) => {
    state.classify.isGraduated = payload.data;
  },
  updategraduateYear: (state, payload) => {
    state.classify.graduateYear = payload.data;
  },
  updateSpecialPoints: (state, payload) => {
    state.classify.specialPoints = payload.data;
  },
  updateadditionalType: (state, payload) => {
    state.classify.additionalType = payload.data;
  },
  updateGraduateType: (state, payload) => {
    state.classify.graduateType = payload.data;
  },
  // PersonalInformation
  updatePersonName: (state, payload) => {
    state.PersonInfo.personName = payload.data;
  },
  updateSex: (state, payload) => {
    state.PersonInfo.sex = payload.data;
  },
  updateYear: (state, payload) => {
    state.PersonInfo.year = payload.data;
  },
  updateMonth: (state, payload) => {
    state.PersonInfo.month = payload.data;
  },
  updateDay: (state, payload) => {
    state.PersonInfo.day = payload.data;
  },
  updateStudentClass: (state, payload) => {
    state.PersonInfo.studentClass = payload.data;
  },
  updateStudentNumber: (state, payload) => {
    state.PersonInfo.studentNumber = payload.data;
  },
  updateSchoolName: (state, payload) => {
    state.PersonInfo.schoolName = payload.data;
  },
  updateParentName: (state, payload) => {
    state.PersonInfo.parentName = payload.data;
  },
  updateSchoolTel: (state, payload) => {
    state.PersonInfo.schoolTel = payload.data;
  },
  updateParentTel: (state, payload) => {
    state.PersonInfo.parentTel = payload.data;
  },
  updateMyTel: (state, payload) => {
    state.PersonInfo.myTel = payload.data;
  },
  updateZipCode: (state, payload) => {
    state.PersonInfo.zipCode = payload.data;
  },
  updateAddressBase: (state, payload) => {
    state.PersonInfo.addressBase = payload.data;
  },
  updateAddressDetail: (state, payload) => {
    state.PersonInfo.addressDetail = payload.data;
  },
  updateImgPath: (state, payload) => {
    state.PersonInfo.imgPath = payload.data;
  },
  updateClassify: (state, payload) => {
    axios.get('http://entrydsm.hs.kr/api/me/classification',
      { headers: { Authorization: `JWT ${payload.token}` } },
    ).then((res) => {
      if (res.status === 200) {
        const {
          graduateType,
          admission,
          admissionDetail,
          region,
          graduateYear,
          additionalType,
        } = res.data.data;
        if (graduateType === 'GED') {
          state.classify.isGED = true;
        } else if (graduateType === 'DONE') {
          state.classify.isGraduated = true;
        }
        state.classify.graduateType = graduateType;
        state.classify.admission = admission;
        switch (admissionDetail) {
          case 'BENEFICIARY' :
            state.classify.admissionDetail = { text: '기초생활수급권자', value: admissionDetail };
            break;
          case 'ONE_PARENT' :
            state.classify.admissionDetail = { text: '한부모가족 보호대상자', value: admissionDetail };
            break;
          case 'CHA_UPPER':
            state.classify.admissionDetail = { text: '차상위 계층', value: admissionDetail };
            break;
          case 'CHACHA_UPPER' :
            state.classify.admissionDetail = { text: '차차상위 계층', value: admissionDetail };
            break;
          case 'FROM_NORTH' :
            state.classify.admissionDetail = { text: '북한이탈주민', value: admissionDetail };
            break;
          case 'MULTI_CULTURE' :
            state.classify.admissionDetail = { text: '다문화 가정', value: admissionDetail };
            break;
          case 'ETC' :
            state.classify.admissionDetail = { text: '그 외 대상자', value: admissionDetail };
            break;
          case 'NONE':
            state.classify.admissionDetail = { text: '', value: 'NONE' };
            break;
          default:
            state.classify.admissionDetail = { text: '서버에서 오류가 났습니다.', value: 'ERROR' };
        }
        state.classify.admissionDetail.value = admissionDetail;
        state.classify.region = region;
        state.classify.graduateYear = graduateYear * 1;
        state.classify.additionalType = additionalType;
      }
    });
  },
  updateInfo: (state, payload) => {
    axios.get('http://entrydsm.hs.kr/api/me/info',
      { headers: { Authorization: `JWT ${payload.token}` } },
    ).then((res) => {
      const {
        addressBase,
        addressDetail,
        birth,
        graduateYear,
        imgPath,
        myTel,
        name,
        parentName,
        parentTel,
        school,
        schoolTel,
        sex,
        studentClass,
        studentGrade,
        studentNumber,
        zipCode,
      } = res.data.data;

      state.PersonInfo.addressBase = addressBase;
      state.PersonInfo.addressDetail = addressDetail;
      state.PersonInfo.year = birth.split('-')[0];
      state.PersonInfo.month = birth.split('-')[1];
      state.PersonInfo.day = birth.split('-')[2];
      state.classify.graduateYear = graduateYear;
      state.PersonInfo.schoolName = (school != null) ? school.name : '';
      state.PersonInfo.schoolCode = (school != null) ? school.code : '';
      state.PersonInfo.schoolTel = schoolTel;
      state.PersonInfo.sex = sex;
      state.PersonInfo.studentClass = studentClass;
      state.PersonInfo.studentGrade = studentGrade;
      state.PersonInfo.studentNumber = studentNumber;
      state.PersonInfo.zipCode = zipCode;
      state.PersonInfo.personName = name;
      state.PersonInfo.imgPath = imgPath;
      state.PersonInfo.myTel = myTel;
      state.PersonInfo.parentTel = parentTel;
      state.PersonInfo.parentName = parentName;
    });
  },
  updateIntroNPlan: (state, payload) => {
    axios.get('http://entrydsm.hs.kr/api/me/info',
      { headers: { Authorization: `JWT ${payload.token}` } },
    ).then((res) => {
      const {
        introduce,
        studyPlan,
      } = res.data.data;
      state.IntroNPlan.introduce = introduce;
      state.IntroNPlan.plan = studyPlan;
    });
  },
  updateaccessToken: (state, payload) => {
    state.accessToken = payload.data;
  },
};

export default mutations;
