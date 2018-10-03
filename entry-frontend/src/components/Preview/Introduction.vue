<template>
  <div id="preview-container">
    <div id="introduction-header">
      자기소개서
    </div>
    <pre id="introduction-title"> 【 지원자 기재사항 】</pre>
    <span id="introduction-info-tables">
      <table>
        <tbody>
          <tr>
            <td><pre>성       명</pre></td>
            <td>{{personName}}</td>
          </tr>
          <tr>
            <td>출신중학교</td>
            <td>{{school.name}}</td>
          </tr>
        </tbody>
      </table>
      <table>
        <tbody>
          <tr>
            <td><div id="tag-icon">◎</div> <span id="introduction-info-main-text">자기소개서</span> 내용은 특별한 형식은 없으며 개인의 특성 및 성장 과정, 취미·특기, 학교생활, 가족 안에서의 역할, 남들보다 뛰어나다고 생각하는 자신의 장점(특성 혹은 능력)과 보완·발전시켜야 할 단점에대하여 기술하십시오.</td>
          </tr>
          <tr>
            <td><pre id="preview-text">{{introduce}}</pre></td>
          </tr>
        </tbody>
      </table>
    </span>
  </div>
</template>

<script>
import { mapState } from 'vuex';

export default {
  name: 'introduction',
  computed: {
    personName() { return this.$store.state.PersonInfo.personName; },
    school() { return this.$store.state.PersonInfo.school; },
    ...mapState({
      introduce: state => state.introNPlan.introduce,
      plan: state => state.introNPlan.plan,
    }),
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
  font-size: 10px;
}

td {
  height: 20px;
  padding: 0;
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

/* preview -> introduction(자기소개서) */
#introduction-header {
  font-size: 21px;
  font-weight: bold;
  text-align: center;
}

#introduction-title {
  font-size: 12px;
  font-weight: bold;
  margin-bottom: 5px;
}

#introduction-info-tables {
  margin-top: 10px;
  border-bottom: 1px solid #000;
  font-size: 10px;
}

#introduction-info-main-text {
  font-size: 14px;
  font-weight: bold;
}

#tag-icon {
  font-size: 12px;
  height: 100%;
  float: left;
  margin-right: 6px;
}

#preview-text {
  width: 100%;
  height: 550px;
  white-space: pre-line;
  line-height: 1.4;
}

/* row_1, 성명~ */
#introduction-info-tables table:nth-child(1) td:nth-child(1) { width: 21%; height: 30px; font-size: 12px;  }
#introduction-info-tables table:nth-child(1) td:nth-child(2) { width: 78%; }

/* row_2, 자기소개서 */
#introduction-info-tables table:nth-child(2) tr:nth-child(1) td:nth-child(1) { text-align: left; padding: 8px; }
#introduction-info-tables table:nth-child(2) tr:nth-child(2) td:nth-child(1) { text-align: left; padding: 8px; height: 570px; border-top: 0; width: 100%; }
</style>
