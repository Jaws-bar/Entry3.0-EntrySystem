<template>
  <div class="mypage">
    <navigation />
    <headline :title="title" :subText="subText" />
    <mypage-form
      :admission="admission"
      :personName="personName"
      :imgPath="imgPath"
      :finalSubmit="finalSubmit"
      :payment="payment"
      :receipt="receipt"/>
    <div class="status-section">
      <status
        name="전형구분"
        color="#e8f3f6"
        :isValid="isClassificationValid"/>
      <status
        name="인적사항"
        color="#d3ebf1"
        :isValid="isInfoValid"/>
      <status
        name="성적입력"
        color="#c0e2eb"
        :isValid="isGradeValid"/>
      <status
        name="자기소개서"
        color="#abd7e2"
        :isValid="isDocumentValid"/>
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

.status-section {
  display: flex;
}
</style>
