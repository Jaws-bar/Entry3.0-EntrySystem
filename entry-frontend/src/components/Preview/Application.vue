<template>
  <div id="preview-container">
    <div id="application-header">
      2019학년도 대덕소프트웨어마이스터고등학교 입학원서
    </div>
    <span id="application-info-tables">
      <table>
        <tbody>
          <tr>
            <td>접수번호</td>
            <td></td>
            <td>중학교 코드</td>
            <td>{{school.code}}</td>
            <td>반</td>
            <td>{{studentClass}}</td>
            <td>수험번호</td>
            <td></td>
          </tr>
        </tbody>
      </table>
      <table>
        <tbody>
          <tr>
            <td rowspan="2">지원자<br />인적사항</td>
            <td>성명</td>
            <td>{{personName}}</td>
            <td>생년월일</td>
            <td>{{year}}.{{month}}.{{day}}.</td>
            <td>성별</td>
            <td class="application-info-textalign-left">
              <input :checked="sex === 'MALE'" type="checkbox" onclick="return false" />남
            </td>
            <td class="application-info-textalign-left">
              <input :checked="sex === 'FEMALE'" type="checkbox" onclick="return false" />여
            </td>
          </tr>
          <tr>
            <td>주소</td>
            <td colspan="6">{{addressBase}}{{addressDetail ? `(${addressDetail})` : ''}}</td>
          </tr>
        </tbody>
      </table>
      <table>
        <tbody>
          <tr>
            <td rowspan="3">전화<br />연락처</td>
            <td>보호자</td>
            <td>{{parentTel}}</td>
            <td rowspan="3">졸업<br />구분</td>
            <td class="application-info-textalign-left">
              <input :checked="graduateType === 'WILL'" type="checkbox" onclick="return false" />2019년 2월 중학교 졸업예정
            </td>
          </tr>
          <tr>
            <td>학교</td>
            <td>{{schoolTel}}</td>
            <td class="application-info-textalign-left">
              <input :checked="graduateType === 'DONE'" type="checkbox" onclick="return false" /><span :style="{paddingRight: graduateType === 'DONE' ? '0' : '7px'}">{{graduateType === 'DONE' ? graduateYear : '201'}}</span>년 2월 중학교 졸업</td>
          </tr>
          <tr>
            <td>학생</td>
            <td>{{myTel}}</td>
            <td class="application-info-textalign-left">
              <input :checked="graduateType === 'GED'" type="checkbox" onclick="return false" />201<span style="padding:0 9px 0 7px">년</span>월 고입 검정고시 합격
            </td>
          </tr>
        </tbody>
      </table>
      <table>
        <tbody>
          <tr>
            <td rowspan="6">전형유형</td>
            <td class="application-info-textalign-left" rowspan="2" colspan="2">
              <input :checked="admission === 'NORMAL'" type="checkbox" onclick="return false" /> 일반전형
            </td>
            <td rowspan="6">지원자<br/> 특기<br />사항</td>
            <td class="application-info-textalign-left" rowspan="3">
              <input :checked="additionalType === 'NATIONAL_MERIT'" type="checkbox" onclick="return false" /> 국가유공자 자녀
            </td>
            <td rowspan="6">지역</td>
            <td class="application-info-textalign-left" rowspan="3">
              <input :checked="region" type="checkbox" onclick="return false" /> 대전
            </td>
          </tr>
          <tr></tr>
          <tr>
            <td class="application-info-textalign-left" rowspan="2">
              <input :checked="admission === 'MEISTER'" type="checkbox" onclick="return false" /> 마이스터인재전형
            </td>
            <td rowspan="4">특별<br /> 전형</td>
          </tr>
          <tr>
            <td class="application-info-textalign-left" rowspan="2">
              <input :checked="additionalType === 'SPECIAL_ADMISSION'" type="checkbox" onclick="return false" /> 특례입학대상자
            </td>
            <td class="application-info-textalign-left" rowspan="3">
              <input :checked="!region" type="checkbox" onclick="return false" /> 전국
            </td>
          </tr>
          <tr>
            <td class="application-info-textalign-left" rowspan="2">
              <input :checked="admission === 'SOCIAL'" type="checkbox" onclick="return false" /> 사회통합전형
            </td>
          </tr>
          <tr></tr>
        </tbody>
      </table>
      <table>
        <tbody>
          <tr>
            <td rowspan="3">내신성적</td>
            <td colspan="4">교과성적</td>
            <td rowspan="2">출석<br /> 점수</td>
            <td rowspan="2">봉사활동<br />점수</td>
            <td rowspan="2">총점</td>
          </tr>
          <tr>
            <td>1학년<br /> 환산점수</td>
            <td>2학년<br /> 환산점수</td>
            <td>3학년<br /> 환산점수</td>
            <td>교과성적<br /> 환산점수</td>
          </tr>
          <tr>
            <td>{{firstGrade}}</td>
            <td>{{secondGrade}}</td>
            <td>{{thirdGrade}}</td>
            <td>{{conversionScore}}</td>
            <td>{{attendanceScore}}</td>
            <td>{{volunteerScore}}</td>
            <td>{{finalScore}}</td>
          </tr>
        </tbody>
      </table>
      <table>
        <tbody>
          <tr>
            <td class="img-cover">
              <img class="img" :src="`https://entry.entrydsm.hs.kr:80/images/${this.imgPath}`">
              <pre>사     진<br /><br />(3cm×4cm)</pre>
            </td>
            <td>
              <pre>본인은 귀 고등학교에 입학하고자 소정의 서류를 갖추어<br />지원합니다.                                              </pre>
              <p>2018년 10 월 <span class="application-info-blank">{{nowDay}}</span>일</p>
              <p>지원자 : <span class="application-info-long-blank">{{ personName }}</span>(인)<span style="padding-right: 20px"></span>보호자 : <span class="application-info-long-blank">{{parentName}}</span>(인)</p>
              <br />
              <p>대덕소프트웨어마이스터고등학교장 귀하</p>
            </td>
            <td>
              <p>보훈번호:(<span class="application-info-middle-blank"></span>)</p>
              <p>위는 국가유공자</p>
              <p>자녀임을 확인함</p>
              <p>2018.10.{{nowDay}}.</p>
              <p>작성자 : <span class="application-info-middle-blank"></span>(인)</p>
            </td>
          </tr>
        </tbody>
      </table>
      <table>
        <tbody>
          <tr>
            <td>
              <pre>추        천        서</pre>
              <pre>본 입학원서의 내용은 사실과 다름이 없으며 상기자는 귀교에 입학 적격자로<br />인정되므로 추천합니다.                                                              </pre>
              <p>2018년 10 월 <span class="application-info-middle-blank">{{nowDay}}</span>일</p>
              <p>작성자 : <span class="application-info-long-blank"></span>(인)<span class="application-info-long-blank"></span><span class="application-info-long-blank"></span>{{school.name}}장<span class="application-info-middle-blank"></span>(직인)</p>
            </td>
          </tr>
        </tbody>
      </table>
    </span>
    <div id="application-terms">
      <div id="application-terms-header">
        개인정보 활용 동의서
      </div>
      <div id="application-terms-info-box">
        <p class="agreement-text">본 입학원서에 기재된 지원자의 개인정보는 신입생 입학관리업무의 원활한 수행을 위하여 개인정보의 수집·유출·오용·남용으로부터 사생활의 비밀 등을 보호하도록 한 개인정보보호법 규정에 따라 다음과 같이 수집·이용.제공됩니다.</p>
        <p class="agreement-text">1. (개인정보 처리의 법령상 근거) 본 입학원서에 기재된 개인정보의 처리업무는 초·중등교육법 제47조 및 동법 시행령 제81조, 제82조, 제84조, 제98조 및 본교의 입학전형 실시계획 등에 근거하고 있습니다.</p>
        <p class="agreement-text">2. (정보주체의 권리) 지원자는 자신이 제공한 개인정보에 대하여 개인정보 보호법 제4조 및 제35조부터 제38조까지에 따라 열람·처리·정지·정정·삭제·파기 등을 요구할 수 있으며,  개인정보 보호법을 위반한 행위로 인한 손해 발생시에는 개인정보 보호법 제39조에 따라 손해배상을 청구할 수 있습니다.</p>
        <p class="agreement-text">3. (개인정보 수집항목) 입학관리 업무의 원활한 수행을 위하여 수집하는 개인정보는 성명, 생년월일, 증명사진, 주소, 전화번호, 학력, 출결사항·교과성적 등입니다.</p>
        <p class="agreement-text">4. (개인정보의 수집·이용 목적) 수집한 지원자의 개인정보는 원서접수, 지원자격·지원결격 사유 확인, 지원자 본인확인, 성적산출, 합격자 명부 관리, 합격증명서 발급, 성적 통지, 통계자료 산출 등 입학관리 업무를 위한 정보로 이용됩니다.</p>
        <p class="agreement-text">5. (개인정보 제공) 수집한 개인정보는 지원자격·지원결격 사유 조회 및 교과성적 확인 등을 위하여 지원자가 졸업한 중학교 등 관련된 기관에 제공될 수 있습니다.</p>
        <p class="agreement-text">6. (개인정보의 보유기간 및 이용기간) 수집한 개인정보는 입학관리 업무를 계속하는 동안 보유·이용할 수 있으며, 입학관리 업무 완료 후 본인의 삭제요청이 있을 경우에는 모두 삭제됩니다.</p>
        <p class="agreement-text">7. (개인정보의 수집·이용·제공에 대한 동의 거부) 지원자는 개인정보의 수집·이용·제공에 대한 동의를 거부할 수 있으며, 동의를 거부할 경우 지원결격 사유 조회 등 입학관리 업무를 수행할 수 없으므로 원서를 접수할 수 없습니다.</p>
      </div>
    </div>
    <div id="foo">
      <p>본 입학원서의 개인정보 수집·이용·제공에 동의합니다.</p>
      <p>지원자 성명 <span class="application-info-long-blank">{{personName}}</span>(인)</p>
      <p>보호자 성명 <span class="application-info-long-blank">{{parentName}}</span>(인)</p>
      <p>대덕소프트웨어마이스터고등학교장 귀하</p>
    </div>
  </div>
</template>

<script>
const phoneReg = /(^02.{0}|^01.{1}|[0-9]{3})([0-9]+)([0-9]{4})/;
export default {
  props: {
    nowYear: Number,
    nowMonth: Number,
    nowDay: Number,
  },
  name: 'application',
  data() {
    return {
      firstGrade: 0,
      secondGrade: 0,
      thirdGrade: 0,
      conversionScore: 0,
      attendanceScore: 0,
      volunteerScore: 0,
      finalScore: 0,
    };
  },
  computed: {
    admission() { return this.$store.state.classify.admission; },
    admissionDetail() { return this.$store.state.classify.admissionDetail; },
    region() { return this.$store.state.classify.region; },
    isGraduated() { return this.$store.state.classify.isGraduated; },
    graduateYear() { return this.$store.state.classify.graduateYear; },
    additionalType() { return this.$store.state.classify.additionalType; },
    graduateType() { return this.$store.state.classify.graduateType; },
    personName() { return this.$store.state.PersonInfo.personName; },
    sex() { return this.$store.state.PersonInfo.sex; },
    year() { return this.$store.state.PersonInfo.year; },
    month() { return this.$store.state.PersonInfo.month; },
    day() { return this.$store.state.PersonInfo.day; },
    studentClass() { return this.$store.state.PersonInfo.studentClass; },
    studentNumber() { return this.$store.state.PersonInfo.studentNumber; },
    school() { return this.$store.state.PersonInfo.school; },
    parentName() { return this.$store.state.PersonInfo.parentName; },
    schoolTel() { return this.$store.state.PersonInfo.schoolTel.replace(phoneReg, '$1-$2-$3'); },
    parentTel() { return this.$store.state.PersonInfo.parentTel.replace(phoneReg, '$1-$2-$3'); },
    myTel() { return this.$store.state.PersonInfo.myTel.replace(phoneReg, '$1-$2-$3'); },
    zipCode() { return this.$store.state.PersonInfo.zipCode; },
    addressBase() { return this.$store.state.PersonInfo.addressBase; },
    addressDetail() { return this.$store.state.PersonInfo.addressDetail; },
    imgPath() { return this.$store.state.PersonInfo.imgPath; },
  },
  created() {
    this.$axios.get('https://entry.entrydsm.hs.kr:80/api/me/score',
      { headers: { Authorization: `JWT ${this.$cookies.get('accessToken')}` },
      }).then((res) => {
      if (res.status === 200) {
        const keyArray = Object.keys(res.data.data);
        const valueArray = Object.values(res.data.data);
        for (let i = 0; i < keyArray.length; i += 1) {
          if (typeof valueArray[i] === 'number') {
            this[keyArray[i]] = valueArray[i] ? valueArray[i].toFixed(3) : 0;
          }
        }
      } else {
        this.$toastr.e('서버와 통신이 불안정합니다.<br/> 재연결이 필요합니다.');
      }
    });
  },
};
</script>

<style scoped>
div {
  box-sizing: border-box;
}

table {
  width: 100%;
  border-spacing: 0;
  border-collapse: collapse;
  table-layout: fixed;
}

p, pre {
  margin: 0;
  padding: 0;
}

td {
  height: 20px;
  padding: 0;
  border: 1px solid #000;
  border-bottom: 0;
  text-align: center;
}

input[type="checkbox"] {
  margin: 0 18px 0 10px;
}

/* All preview document(pdf) use this container. */
#preview-container {
  width: 595px;
  height: 842px;
  padding: 30px;
  border: 1px solid #000;
}

/* preview -> application(입학원서) */
#application-header {
  font-size: 21px;
  font-weight: bold;
  text-align: center;
}

/* application-terms -> agreement(동의서) */
#application-terms-header {
  font-size: 13px;
  text-align: center;
  font-weight: bold;
  margin: 6px;
}

/* table container */
#application-info-tables {
  margin-top: 10px;
  border-bottom: 1px solid #000;
  font-size: 10px;
}

/* checkbox textalign */
.application-info-textalign-left {
  text-align: left;
}

/* number blank */
.application-info-blank {
  display: inline-block;
  min-width: 6px;
}

/* long number blank */
.application-info-long-blank {
  display: inline-block;
  width: 70px;
}

/* middle number blank */
.application-info-middle-blank {
  display: inline-block;
  width: 15px;
}

#application-terms-info-box {
  padding-top: 10px;
  height: 160px;
  /* padding: 2px; */
  border: 1px solid black;
}

/* agreement-text */
.agreement-text {
  font-size: 3px;
  margin: 0 6px;
}

/* The total value of the row percentage should be 99%, beacuse 100% has some issue. */
/* row_1, 접수번호~ */
#application-info-tables table:nth-child(1) td:nth-child(1) { width: 10%; }
#application-info-tables table:nth-child(1) td:nth-child(2) { width: 20%; }
#application-info-tables table:nth-child(1) td:nth-child(3) { width: 14%; }
#application-info-tables table:nth-child(1) td:nth-child(4) { width: 14%; }
#application-info-tables table:nth-child(1) td:nth-child(5) { width: 5%; }
#application-info-tables table:nth-child(1) td:nth-child(6) { width: 6%; }
#application-info-tables table:nth-child(1) td:nth-child(7) { width: 12%; }
#application-info-tables table:nth-child(1) td:nth-child(8) { width: 18%; }

/* row_2, 지원자 인적사항~ */
#application-info-tables table:nth-child(2) tr:nth-child(1) td:nth-child(1) { width: 10%; }
#application-info-tables table:nth-child(2) tr:nth-child(1) td:nth-child(2) { width: 7%; }
#application-info-tables table:nth-child(2) tr:nth-child(1) td:nth-child(3) { width: 13%; }
#application-info-tables table:nth-child(2) tr:nth-child(1) td:nth-child(4) { width: 14%; }
#application-info-tables table:nth-child(2) tr:nth-child(1) td:nth-child(5) { width: 25%; }
#application-info-tables table:nth-child(2) tr:nth-child(1) td:nth-child(6) { width: 8%; }
#application-info-tables table:nth-child(2) tr:nth-child(1) td:nth-child(7) { width: 11%; }
#application-info-tables table:nth-child(2) tr:nth-child(1) td:nth-child(8) { width: 11%; }

/* row_4, 전화 연락처~ */
#application-info-tables table:nth-child(3) tr:nth-child(1) td:nth-child(1) { width: 10%; }
#application-info-tables table:nth-child(3) tr:nth-child(1) td:nth-child(2) { width: 7%; }
#application-info-tables table:nth-child(3) tr:nth-child(1) td:nth-child(3) { width: 31%; }
#application-info-tables table:nth-child(3) tr:nth-child(1) td:nth-child(4) { width: 10%; }
#application-info-tables table:nth-child(3) tr:nth-child(1) td:nth-child(5) { width: 41%; }

/* row_5, 전형유형~ */
#application-info-tables table:nth-child(4) {table-layout: inherit;}

#application-info-tables table:nth-child(4) tr:nth-child(1) td:nth-child(1) { width: 10%; }
#application-info-tables table:nth-child(4) tr:nth-child(1) td:nth-child(2) { width: 10%; }
#application-info-tables table:nth-child(4) tr:nth-child(1) td:nth-child(3) { width: 7.9%; }
#application-info-tables table:nth-child(4) tr:nth-child(1) td:nth-child(4) { width: 24.9%; }
#application-info-tables table:nth-child(4) tr:nth-child(1) td:nth-child(5) { width: 8.1%; }
#application-info-tables table:nth-child(4) tr:nth-child(1) td:nth-child(6) { width: 17%; }
#application-info-tables table:nth-child(4) tr:nth-child(3) td:nth-child(1) { width: 24%; }
#application-info-tables table:nth-child(4) tr:nth-child(3) td:nth-child(2) { width: 6%; }

/* row_6, 내신성적~ */
#application-info-tables table:nth-child(5) tr:nth-child(1) td:nth-child(1) { width: 10%; }
#application-info-tables table:nth-child(5) tr:nth-child(1) td:nth-child(2) { width: 50%; }
#application-info-tables table:nth-child(5) tr:nth-child(1) td:nth-child(3) { width: 14%; }
#application-info-tables table:nth-child(5) tr:nth-child(1) td:nth-child(4) { width: 13%; }
#application-info-tables table:nth-child(5) tr:nth-child(1) td:nth-child(5) { width: 13%; }

/* row_7, 사진~ */
#application-info-tables table:nth-child(6) tr:nth-child(1) td:nth-child(1) { width: 3cm; height: 4cm; }
#application-info-tables table:nth-child(6) tr:nth-child(1) td:nth-child(2) { width: 58%; }
#application-info-tables table:nth-child(6) tr:nth-child(1) td:nth-child(2) p, pre { margin: 10px; }
#application-info-tables table:nth-child(6) tr:nth-child(1) td:nth-child(2) p:nth-child(5) { font-size: 11px; font-weight: bold; text-align: left; margin-left: 10px; }
#application-info-tables table:nth-child(6) tr:nth-child(1) td:nth-child(3) { width: 21%; }
#application-info-tables table:nth-child(6) tr:nth-child(1) td:nth-child(3) p, pre { margin: 10px; }

/* row_8, 추천서~ */
#application-info-tables table:nth-child(7) tr:nth-child(1) td:nth-child(1) { border: 1px solid #000 }
#application-info-tables table:nth-child(7) tr:nth-child(1) td:nth-child(1) p, pre { margin: 12px; }

/* row-9, 개인정보 활용 동의서 */
#application-terms-info-box p:nth-child(1) { font-size: 8px; line-height: 1.2 }
#application-terms-info-box p:nth-child(2) { font-size: 8px; line-height: 1.2 }
#application-terms-info-box p:nth-child(3) { font-size: 8px; line-height: 1.2 }
#application-terms-info-box p:nth-child(4) { font-size: 8px; line-height: 1.2 }
#application-terms-info-box p:nth-child(5) { font-size: 8px; line-height: 1.2 }
#application-terms-info-box p:nth-child(6) { font-size: 8px; line-height: 1.2 }
#application-terms-info-box p:nth-child(7) { font-size: 8px; line-height: 1.2 }
#application-terms-info-box p:nth-child(8) { font-size: 8px; line-height: 1.2 }

#foo p:nth-child(1) { font-size: 10px; font-weight: bold; line-height: 2 }
#foo p:nth-child(2) { font-size: 10px; text-align: right; line-height: 2}
#foo p:nth-child(3) { font-size: 10px; text-align: right; }
#foo p:nth-child(4) { font-size: 14px; font-weight: bold; }
#foo p:nth-child(2) span { text-align: center; }
#foo p:nth-child(3) span { text-align: center; }

.img-cover {
  position: relative;
}
.img {
  position: absolute;
  width: 100%;
  height: 100%;
  left: 0;
  top: 0;
}
</style>
