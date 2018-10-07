<template>
  <div id="preview-container">
    <div id="recommendation-letter-header">
      학교장추천서
    </div>
    <div id="recommendation-letter-content">
      <div>
        <p>{{school.name}}</p>
        <p>3학년 <span class="recommendation-blank">{{studentClass}}</span>반</p>
        <p>성 명 : <span class="recommendation-blank">{{personName}}</span></p>
      </div>
      <table>
        <tbody>
          <tr>
            <td colspan="4">특별전형 추천분야(해당란에 O표)</td>
          </tr>
          <tr>
            <td rowspan="2">전형 유형</td>
            <td rowspan="2">마이스터 인재 전형</td>
            <td colspan="2">사회통합전형</td>
          </tr>
          <tr>
            <td>기회 균등</td>
            <td>사회 다양성</td>
          </tr>
          <tr>
            <td>대전시 교육청 관내</td>
            <td><span class="check" v-show="check === 1">O</span></td>
            <td><span class="check" v-show="check === 2">O</span></td>
            <td><span class="check" v-show="check === 3">O</span></td>
          </tr>
          <tr>
            <td>대전시 교육청 관외</td>
            <td><span class="check" v-show="check === 4">O</span></td>
            <td><span class="check" v-show="check === 5">O</span></td>
            <td><span class="check" v-show="check === 6">O</span></td>
          </tr>
        </tbody>
      </table>
      <pre>    위 학생을 2019학년도 대덕소프트웨어마이스터고등학교 특별<br />  전형 대상자로 추천합니다.</pre>
      <div id="recommendation-footer">
        <p>2018 년 <span class="recommendation-blank">{{nowMonth}}</span>월 <span class="recommendation-blank">{{nowDay}}</span>일</p>
        <p>작성자 담임 : <span class="recommendation-blank"></span>(인)</p>
        <p>{{school.name}}장</p>
        <div>출신중학교장<br />직인</div>
        <p id="recommendation-school">대덕소프트웨어마이스터고등학교장 귀하</p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    nowYear: Number,
    nowMonth: Number,
    nowDay: Number,
  },
  name: 'recommendation-letter',
  computed: {
    admission() { return this.$store.state.classify.admission; },
    admissionDetail() { return this.$store.state.classify.admissionDetail; },
    region() { return this.$store.state.classify.region; },
    personName() { return this.$store.state.PersonInfo.personName; },
    studentClass() { return this.$store.state.PersonInfo.studentClass; },
    school() { return this.$store.state.PersonInfo.school; },
    isOpportunity() {
      return (
        this.admissionDetail.value === 'BENEFICIARY' ||
        this.admissionDetail.value === 'ONE_PARENT' ||
        this.admissionDetail.value === 'CHA_UPPER' ||
        this.admissionDetail.value === 'CHACHA_UPPER');
    },
    isDiversity() {
      return (
        this.admissionDetail.value === 'FROM_NORTH' ||
        this.admissionDetail.value === 'MULTI_CULTURE');
    },
    check() {
      let value = 0;
      if (this.region) {
        if (this.admission === 'MEISTER') {
          value = 1;
        } else if (this.isOpportunity) {
          value = 2;
        } else if (this.isDiversity) {
          value = 3;
        }
      } else if (!this.region) {
        if (this.admission === 'MEISTER') {
          value = 4;
        } else if (this.isOpportunity) {
          value = 5;
        } else if (this.isDiversity) {
          value = 6;
        }
      }
      return value;
    },
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
  margin-bottom:20px;
}

p, pre {
  margin: 0;
  padding: 0;
  font-family: inherit;
  font-size: 17px;
}

td {
  height: 20px;
  padding: 4px;
  border: 1px solid #000;
  text-align: center;
}

/* All preview document(pdf) use this container. */
#preview-container {
  width: 595px;
  height: 842px;
  padding: 30px;
  border: 1px solid #000;
}

/* preview -> recommendation-letter(학교장추천서) */
#recommendation-letter-header {
  font-size: 26px;
  font-weight: bold;
  text-align: center;
  margin-bottom: 15px;
}

/* main content */
#recommendation-letter-content {
  width: 100%;
  height: 733px;
  border: black solid 1px;
  padding: 12px;
}

/* p -> span-blank */
.recommendation-blank {
  width: 30px;
}

/* school name */
#recommendation-school {
  font-size: 22px;
  margin-top: 42px;
}

/* row_1, content(특별전형 추천분야~) */
#recommendation-letter-content { position: relative; }
#recommendation-letter-content div:nth-child(1) { padding-left: 45px; line-height: 34px; width: 50%; float: right; margin: 10px; }
#recommendation-letter-content div:nth-child(1) p:nth-child(1) .recommendation-blank:nth-child(1) { width: 80px; display: inline-block; }
#recommendation-letter-content div:nth-child(1) p:nth-child(2) .recommendation-blank:nth-child(1) { width: 16px; display: inline-block; }
#recommendation-letter-content div:nth-child(1) p:nth-child(3) .recommendation-blank:nth-child(1) { width: 90px; display: inline-block; }
#recommendation-letter-content table tr:nth-child(1) td:nth-child(1) { font-weight: bold; font-size: 17px; }
#recommendation-letter-content table tr:nth-child(2) td:nth-child(1) { width: 32%; }
#recommendation-letter-content table tr:nth-child(2) td:nth-child(2) { width: 30%; }
#recommendation-letter-content table tr:nth-child(2) td:nth-child(3) { width: 37%; }
#recommendation-letter-content table tr:nth-child(3) td:nth-child(1) { width: 19%; }
#recommendation-letter-content table tr:nth-child(3) td:nth-child(2) { width: 19%; }
#recommendation-letter-content table tr:nth-child(4) { height: 60px; }
#recommendation-letter-content table tr:nth-child(5) { height: 60px; }

/* footer */
#recommendation-footer { text-align: center; font-weight: bold; line-height: 44px; width: 508px; height: 240px; position: absolute; bottom: 12px; }
#recommendation-footer div { position: absolute; width: 80px; height: 80px; border: 1px dotted black; right: 55px; bottom: 70px; font-size: 11px; padding: 3px; line-height: 40px; }
#recommendation-footer p:nth-child(3) { font-size: 22px; margin: 20px; }
#recommendation-footer p:nth-child(1) .recommendation-blank { width: 25px; display: inline-block; }
#recommendation-footer p:nth-child(2) .recommendation-blank { width: 100px; display: inline-block; }
#recommendation-footer p:nth-child(3) .recommendation-blank { width: 150px; display: inline-block; }

.check {
  font-size: 30px;
}
</style>
