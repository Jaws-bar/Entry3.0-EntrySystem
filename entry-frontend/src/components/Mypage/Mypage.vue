<template>
  <div class="mypage">
    <navigation />
    <headline :title="title" :subText="subText" />
    <mypage-form
      :finalSubmit="finalSubmit"
      :payment="payment"
      :receipt="receipt"/>
    <div class="status-section">
      <status
        name="전형구분"
        color="#e8f3f6"
        :isValid="isClassificationValid"/>
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
  computed: mapState({
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

</style>
