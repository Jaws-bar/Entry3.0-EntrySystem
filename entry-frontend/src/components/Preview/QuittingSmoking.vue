<template>
  <div id="preview-container">
    <div id="quitting-header">
      금연서약서
    </div>
    <table>
      <tbody>
        <tr>
          <td rowspan="3">서약서</td>
          <td><pre>성   명</pre></td>
          <td>{{personName}}</td>
          <td>수 험 번 호</td>
          <td></td>
        </tr>
        <tr>
          <td>연락처</td>
          <td>{{myTel}}</td>
          <td>출신 중학교</td>
          <td>{{school.name}}</td>
        </tr>
        <tr>
          <td><pre>주   소</pre></td>
          <td colspan="3">{{addressBase}}{{addressDetail ? `(${addressDetail})` : ''}}</td>
        </tr>
        <tr>
          <td id="quitting-smoking-content" colspan="5">
            <div id="quitting-content-message">
              <p>하나, 나 자신의 건강을 위해서 흡연을 하지 않겠습니다.</p>
              <p>하나, 흡연의 유혹에 절대로 흔들리지 않겠습니다.</p>
              <p>하나, 흡연을 하는 친구가 있으면 충고하여 금연을 할 수 있도록 돕겠습니다.</p>
              <p>하나, 위의 약속을 위반할 경우 어떠한 처벌에도 이의를 제기하지 않겠습니다.</p>
            </div>
            <div id="quitting-content-declaration">
              <pre>   나 (<span class="quitting-blank" style="text-align:center">{{personName}}</span>)은(는) 장차 소프트웨어 분야를 선도할 전문가로 성장<br />하기 위하여 흡연을 하지 않겠습니다.</pre>
              <pre>   보호자는 서약자가 금연을 하는데 용기와 도움을 줄 것을 약속합니다.</pre>
            </div>
            <div id="quitting-content-footer">
              <p>2018년 <span class="quitting-blank"></span>월 <span class="quitting-blank"></span>일</p>
              <p>서 약 자 : <span class="quitting-blank">{{ personName }}</span>( 서 명 )</p>
              <p>보 호 자 : <span class="quitting-blank">{{ parentName }}</span>( 서 명 )</p>
            </div>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
export default {
  name: 'quitting-smoking',
  computed: {
    personName() { return this.$store.state.PersonInfo.personName; },
    parentName() { return this.$store.state.PersonInfo.parentName; },
    school() { return this.$store.state.PersonInfo.school; },
    myTel() { return this.$store.state.PersonInfo.myTel; },
    addressBase() { return this.$store.state.PersonInfo.addressBase; },
    addressDetail() { return this.$store.state.PersonInfo.addressDetail; },
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
  margin-bottom:20px;
}

p, pre {
  margin: 0;
  padding: 0;
  font-family: inherit;
}

td {
  height: 20px;
  padding: 4px;
  border: 1px solid #000;
  text-align: center;
  font-size: 12px;
}

/* All preview document(pdf) use this container. */
#preview-container {
  width: 595px;
  height: 842px;
  padding: 30px;
  border: 1px solid #000;
}

/* preview -> quitting(금지-금연) */
#quitting-header {
  font-size: 21px;
  font-weight: bold;
  text-align: center;
  margin: 12px;
}

/* row_1, pledge(서약서~) */
#preview-container table tr:nth-child(1) td:nth-child(1) { width: 8%; padding: 0 8px; font-weight: bold; font-size: 14px; }
#preview-container table tr:nth-child(1) td:nth-child(2) { width: 18%; }
#preview-container table tr:nth-child(1) td:nth-child(3) { width: 32.5%; }
#preview-container table tr:nth-child(1) td:nth-child(4) { width: 18%; }
#preview-container table tr:nth-child(1) td:nth-child(5) { width: 32.5%; }

/* pledge -> content */
#quitting-smoking-content { width: 100%; height: 617px; padding: 4px; }
#quitting-smoking-content #quitting-content-message { text-align: left; font-size: 15px; line-height: 40px; margin: 50px 0; }

#quitting-smoking-content #quitting-content-declaration { text-align: left; font-size: 15px; line-height: 35px; }
#quitting-smoking-content #quitting-content-declaration .quitting-blank:nth-child(1) { display: inline-block; width: 110px; }

#quitting-smoking-content #quitting-content-footer { font-size: 15px; line-height: 35px; }
#quitting-smoking-content #quitting-content-footer p:nth-child(1) {  margin: 40px 0; }
#quitting-smoking-content #quitting-content-footer p:nth-child(2) {  text-align: right; margin: 0 10px; }
#quitting-smoking-content #quitting-content-footer p:nth-child(3) {  text-align: right; margin: 0 10px; }
#quitting-smoking-content #quitting-content-footer p:nth-child(1) .quitting-blank { width: 40px; display: inline-block; }
#quitting-smoking-content #quitting-content-footer p:nth-child(2) .quitting-blank { width: 120px; display: inline-block; text-align: left; }
#quitting-smoking-content #quitting-content-footer p:nth-child(3) .quitting-blank { width: 120px; display: inline-block; text-align: left; }
</style>
