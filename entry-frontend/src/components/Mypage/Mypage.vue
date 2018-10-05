<template>
  <div class="mypage">
    <navigation />
    <headline :title="title" :subText="subText" />
    <mypage-form/>
    <entry-footer />
  </div>
</template>

<script>
import { mapState } from 'vuex';
import Navigation from '../common/Navigation';
import Headline from '../common/Headline';
import EntryFooter from '../common/EntryFooter';
import MypageForm from './MypageForm';

export default {
  components: {
    Navigation,
    Headline,
    EntryFooter,
    MypageForm,
  },
  name: 'mypage',
  data() {
    return {
      title: '마이페이지',
      subText: '2019 입학원서 작성',
    };
  },
  computed: mapState({
    graduateType: state => state.mypage.graduateType,
    isValid: state => state.mypage.validationResult.isValid,
    isClassificationValid: state => state.mypage.validationResult.isClassificationValid,
    isInfoValid: state => state.mypage.validationResult.isInfoValid,
    isGradeValid: state => state.mypage.validationResult.isGradeValid,
    isDocumentValid: state => state.mypage.validationResult.isDocumentValid,
    finalSubmit: state => state.mypage.applyStatus.finalSubmit,
    passStatus: state => state.mypage.applyStatus.passStatus,
    payment: state => state.mypage.applyStatus.payment,
    receipt: state => state.mypage.applyStatus.receipt,
  }),
};
</script>

<style lang="scss" scoped>
@import '../../style/setting';

</style>
