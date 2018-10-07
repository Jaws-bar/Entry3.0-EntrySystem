<template>
  <div>
    <navigation />
    <headline :subText="'2019 입학원서 작성'" title="성적 입력" />
    <ged-grade-input v-if="graduateType === 'GED'" />
    <normal-grade-input-scheduled v-else-if="graduateType === 'WILL'" />
    <normal-grade-input-graduated v-else-if="graduateType === 'DONE'" />
    <prev-next-btn
      :prevShow="true"
      :nextShow="true"
      :prevLink="prevLink"
      :nextLink="nextLink"
      :onClick="() => updateGrade()"
    />
    <entry-footer />
  </div>
</template>

<script>
import Navigation from '../common/Navigation';
import Headline from '../common/Headline';
import PrevNextBtn from '../common/PrevNextBtn';
import EntryFooter from '../common/EntryFooter';
import GedGradeInput from './GedGradeInput';
import NormalGradeInputScheduled from './NormalGradeInputScheduled';
import NormalGradeInputGraduated from './NormalGradeInputGraduated';
import utils from '../../utils';

export default {
  name: 'grade-input',
  components: {
    Navigation,
    Headline,
    PrevNextBtn,
    EntryFooter,
    GedGradeInput,
    NormalGradeInputScheduled,
    NormalGradeInputGraduated,
  },
  created() {
    const token = this.$cookies.get('accessToken');
    const { e } = this.$toastr;
    if (token === undefined || token === null || token === '') {
      this.$router.push('/');
      e('해당 페이지는 로그인이 필요합니다.');
      this.$store.commit('changeIndex', {
        index: 1,
      });
    }
    if (this.$store.state.mypage.applyStatus.finalSubmit) {
      e('최종 제출 후에는 접근 할 수 없습니다.');
      this.$router.push('/');
    }
  },
  data() {
    return {
      prevLink: '/personal',
      nextLink: '/intro',
    };
  },
  computed: {
    volunteerNAttendance: {
      get() {
        return this.$store.getters.selectType.volunteerNAttendance;
      },
    },
    grade: {
      get() {
        return this.$store.state.gradeInput.grade;
      },
    },
    grades: {
      get() {
        return this.$store.getters.selectType.grades;
      },
    },
    graduateType: {
      get() {
        return this.$store.state.classify.graduateType;
      },
    },
  },
  methods: {
    updateGrade() {
      const {
        volunteerNAttendance,
        grade,
        grades,
        graduateType,
      } = this;
      const { s, e } = this.$toastr;
      const token = this.$cookies.get('accessToken');
      const sendData = utils.sendGrade(grade, grades, graduateType, volunteerNAttendance);
      this.$store.dispatch('updateGrade', {
        token,
        sendData,
        s,
        e,
      });
    },
  },
};
</script>
