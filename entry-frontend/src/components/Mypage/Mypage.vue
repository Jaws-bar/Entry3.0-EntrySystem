<template>
  <div class="mypage">
    <navigation />
    <headline :title="title" :subText="subText" />
    <div class="mypage__cover">
      <mypage-form
        :admission="admission"
        :personName="personName"
        :imgPath="imgPath"
        :finalSubmit="finalSubmit"
        :payment="payment"
        :receipt="receipt"
        @goto-preview="gotoPreview"/>
      <div class="mypage__status-section">
        <status
          class="mypage__status"
          name="전형구분"
          color="#e8f3f6"
          :isValid="isClassificationValid"/>
        <div class="mypage__joiner"></div>
        <status
          class="mypage__status"
          name="인적사항"
          color="#d3ebf1"
          :isValid="isInfoValid"/>
        <div class="mypage__joiner"></div>
        <status
          class="mypage__status"
          name="성적입력"
          color="#c0e2eb"
          :isValid="isGradeValid"/>
        <div class="mypage__joiner"></div>
        <status
          class="mypage__status"
          name="자기소개서"
          color="#abd7e2"
          :isValid="isDocumentValid"/>
      </div>
    </div>
    <entry-footer />
  </div>
</template>

<script>
import { mapState } from 'vuex';
import Navigation from '../common/Navigation';
import Headline from '../common/Headline';
import EntryFooter from '../common/EntryFooter';
import MypageForm from './MypageForm';
import Status from './Status';
import CONSTANT from '../../api/constant';

export default {
  components: {
    Navigation,
    Headline,
    EntryFooter,
    MypageForm,
    Status,
  },
  name: 'mypage',
  data() {
    return {
      title: '마이페이지',
      subText: '2019 입학원서 작성',
    };
  },
  methods: {
    gotoPreview() {
      const token = this.$cookies.get('accessToken');
      const { introduce, studyPlan } = this.$store.state.introNPlan;
      this.$store.dispatch('gotoPreview', { token, introduce, studyPlan });
    },
  },
  computed: mapState({
    admission: state => state.classify.admission,
    personName: state => state.PersonInfo.personName,
    imgPath: state => (state.PersonInfo.imgPath ? `${CONSTANT.IMAGE_URI}${state.PersonInfo.imgPath}` : ''),
    isValid: state => state.mypage.validationResult.isValid,
    isClassificationValid: state => state.mypage.validationResult.isClassificationValid,
    isInfoValid: state => state.mypage.validationResult.isInfoValid,
    isGradeValid: state => state.mypage.validationResult.isGradeValid,
    isDocumentValid: state => state.mypage.validationResult.isDocumentValid,
    finalSubmit: state => state.mypage.applyStatus.finalSubmit,
    payment: state => state.mypage.applyStatus.payment,
    receipt: state => state.mypage.applyStatus.receipt,
  }),
};
</script>

<style lang="scss" scoped>
@import '../../style/setting';

.mypage {
  @include e('cover') {
    width: 1140px;
    margin: 0 auto;
  }
  @include e('status-section') {
    display: flex;
    margin: {
      top: 108px;
      bottom: 125px;
    }
  }
  @include e('status') {
    flex: 1;
  }
  @include e('joiner') {
    width: 47px;
    background: url('../../assets/Mypage/joiner.png') no-repeat center 70px;
    background-size: contain;
  }
}
</style>
