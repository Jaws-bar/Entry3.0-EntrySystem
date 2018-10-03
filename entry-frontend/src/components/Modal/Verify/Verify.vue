<template>
  <div class="Verify">
    <div class="Verify__Icon">
      <div class="Verify__Icon__box Verify__Icon__box--info" v-if="isInfoValid==='작성완료'">
      </div>
      <div class="Verify__Icon__box Verify__Icon__box--info Verify__Icon__box--false" v-else>
      </div>
      <div class="Verify__Icon__box Verify__Icon__box--classify" v-if="isClassificationValid==='작성완료'">
      </div>
      <div class="Verify__Icon__box Verify__Icon__box--classify Verify__Icon__box--false" v-else>
      </div>
      <div class="Verify__Icon__box Verify__Icon__box--plan" v-if="isDocumentValid==='작성완료'">
      </div>
      <div class="Verify__Icon__box Verify__Icon__box--plan Verify__Icon__box--false" v-else>
      </div>
      <div class="Verify__Icon__box Verify__Icon__box--gradeInput" v-if="isGradeValid==='작성완료'">
      </div>
      <div class="Verify__Icon__box Verify__Icon__box--gradeInput Verify__Icon__box--false" v-else>
      </div>
    </div>
    <div class="Verify__msg" v-if="!isValid">
      <p class="Verify__msg__header">원서를 미리 볼 수 없습니다</p>
      <div class="Verify__msg__hr"></div>
      <p class="Verify__msg__content">
        아직 입력되지 않았거나 잘못 된 정보가 있습니다.<br/>
        위의 카드를 통해 페이지를 확인하시고 <br/>
        해당 항목을 입력하거나 수정해주시길 바랍니다.<br/>
      </p>
    </div>
    <div class="Verify__msg" v-else>
      <p class="Verify__msg__header">최종 제출을 하시겠습니까?</p>
      <div class="Verify__msg__hr"></div>
      <p class="Verify__msg__content">
        최종 제출이 완료 후에는 작성한 입학 원서를 수정할 수 없습니다.
      </p>
      <div class="modal--btn Verify__msg__btn">
        최종 제출
      </div>
    </div>
  </div>
</template>

<script>
export default {
  computed: {
    isClassificationValid: {
      get() {
        if (this.$store.state.mypage.validationResult.isClassificationValid) {
          return '작성완료';
        }
        return '작성 미완료';
      },
    },
    isDocumentValid: {
      get() {
        if (this.$store.state.mypage.validationResult.isDocumentValid) {
          return '작성완료';
        }
        return '작성 미완료';
      },
    },
    isInfoValid: {
      get() {
        if (this.$store.state.mypage.validationResult.isInfoValid) {
          return '작성완료';
        }
        return '작성 미완료';
      },
    },
    isGradeValid: {
      get() {
        if (this.$store.state.mypage.validationResult.isGradeValid) {
          return '작성완료';
        }
        return '작성 미완료';
      },
    },
    isValid: {
      get() {
        return this.$store.state.mypage.validationResult.isValid;
      },
    },
  },
  created() {
    const token = this.$cookies.get('accessToken');
    this.$store.dispatch('getMypage', {
      token,
    });
  },
};
</script>

<style lang="scss" scoped>
@import '../../../style/setting';
.Verify{
  widows: 100%;
  height: 100%;
  position: absolute;
  top: 100px;
  @include e('Icon'){
    width: 240px;
    height: 240px;
    transform: rotate(-45deg);
    position: relative;
    margin: 0 auto;
    @include e('box'){
      display: inline-block;
      width: 100px;
      height: 100px;
      border: solid 1px #96b2b5;
      float: left;
      margin: 9px;
      transition: 0.5s;
      position: absolute;
      display: flex;
      justify-content: center;
      align-items: center;
      &::after{
        content: '작성완료';
        opacity: 0;
        position: absolute;
        transform: rotate(45deg);
      }
      @include m('false') {
        background-color: #FFF !important;
        &::after{
          position: absolute;
          opacity: 0;
          content: '작성 미완료';
          transform: rotate(45deg);
        }
        &:hover{
          &::after{
            opacity: 1;
            transition: 1s;
            top: 120px;
            left: 54.1px !important;
          }
        }
      }
      @include e('text') {
        transform: rotate(45deg);
      }
      &:hover{
        position: absolute;
        width: 220px;
        height: 220px;
        z-index: 10;
        &::before{
          opacity: 1;
          transition: 1s;
        }
        &::after{
          opacity: 1;
          transition: 1s;
          top: 120px;
          left: 64px;
        }
      }
      &::before{
        font-family: NanumSquareR;
        font-size: 24px;
        color: #356a71;
        transform: rotate(45deg);
        opacity: 0;
        display: block;
        text-align: center;
        width: 125px;
        padding-bottom: 10px;
        border-bottom: solid 1px #356a71;
        position: relative;
        top: -20px;
        left: 20px;
      }
      @include m('info'){
        background-color: #e8f3f6;
        top: 0;
        left: 0;
        &::before{
          content: '인적사항';
        }
      }
      @include m('classify'){
        background-color: #d3ebf1;
        top:0;
        right: 0;
        &::before{
          content: '전형구분'
        }
      }
      @include m('plan'){
        background-color: #abd7e2;
        bottom:0;
        left: 0;
        &::before{
          content: '자기소개서'
        }
      }
      @include m('gradeInput'){
        background-color: #c0e2eb;
        bottom:0;
        right: 0;
        &::before{
          content: '성적입력'
        }
      }
    }
  }
  @include e('msg'){
    position: relative;
    top: 75px;
    text-align: center;
    font-size: 18px;
    color: #000000;
    @include e('hr'){
      width: 100px;
      height: 1px;
      background-color: #70b1ba;
      margin: 17px auto 0 auto;
    }
    @include e('content'){
      margin-top: 14px;
      font-size: 12px;
      line-height: 1.58;
    }
    @include e('btn'){
      width: 295px;
      height: 40px;
    }
  }
}
</style>

