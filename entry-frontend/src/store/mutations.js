export const mutations = {
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
  updateEntranceModel: (state, payload) => {
    state.classify.entranceModel = payload.data;
  },
  updateSocialOption: (state, payload) => {
    state.classify.socialOption = payload.data;
  },
  updateRegion: (state, payload) => {
    state.classify.region = payload.data;
  },
  updateIsGraduated: (state, payload) => {
    state.classify.isGraduated = payload.data;
  },
  updateGraduationYear: (state, payload) => {
    state.classify.graduationYear = payload.data;
  },
  updateSpecialPoints: (state, payload) => {
    state.classify.specialPoints = payload.data;
  },
  updateAdditionalType: (state, payload) => {
    state.classify.AdditionalType = payload.data;
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
};

export default mutations;
