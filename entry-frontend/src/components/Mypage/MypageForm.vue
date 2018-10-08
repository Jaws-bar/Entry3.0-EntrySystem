<template>
  <div class="form">
    <div class="form__img-cover">
      <div class="form__img" :style="imgStyle"></div>
    </div>
    <div class="form__colum">
      <div class="form__key">
        이름
      </div>
      <div class="form__value">
        {{ personName || '비어있음' }}
      </div>
    </div>
    <div class="form__colum">
      <div class="form__key">
        전형
      </div>
      <div class="form__value">
        {{ admissionString }}
      </div>
    </div>
    <div class="form__colum">
      <div class="form__key">
        최종 제출
      </div>
      <div class="form__value">
        {{ finalSubmitString }}
        <button
          v-if="!finalSubmit"
          @click="gotoPreview"
          class="form__preview-btn">
          원서 미리보기
        </button>
      </div>
    </div>
    <div class="form__colum">
      <div class="form__key">
        전형료 납부
      </div>
      <div class="form__value">
        {{ paymentString }}
      </div>
    </div>
    <div class="form__colum">
      <div class="form__key">
        우편물 수령
      </div>
      <div class="form__value">
        {{ receiptString }}
      </div>
    </div>
  </div>
</template>

<script>

export default {
  name: 'mypage',
  props: {
    admission: String,
    personName: String,
    imgPath: String,
    finalSubmit: Boolean,
    payment: Boolean,
    receipt: Boolean,
  },
  computed: {
    imgStyle() {
      return this.imgPath ? { backgroundImage: `url('${this.imgPath}')` } : '';
    },
    admissionString() {
      let str;
      switch (this.admission) {
        case 'NORMAL': str = '일반전형'; break;
        case 'MEISTER': str = '마이스터 인재전형'; break;
        case 'SOCIAL': str = '사회통합전형'; break;
        default: str = '미 선택';
      }
      return str;
    },
    finalSubmitString() { return this.finalSubmit ? '제출 완료' : '미완료'; },
    paymentString() { return this.payment ? '납부 완료' : '납부 전'; },
    receiptString() { return this.receipt ? '수령 완료' : '수령 전'; },
  },
  methods: {
    gotoPreview() {
      this.$emit('goto-preview');
    },
  },
};
</script>

<style lang="scss" scoped>
@import '../../style/setting';
$border1: solid 1px #eff3f4;

.form {
  width: 1140px;
  background-attachment: #fff;
  font-size: 20px;
  position: relative;
  z-index: 0;
  border: {
    top: solid 1px #769b9f;
    bottom: solid 1px #769b9f;
  };
  @include e('img-cover') {
    position: absolute;
    top: 0;
    right: 0;
    z-index: 3;
    background-color: #fff;
    height: 100%;
    width: 315px;
    box-sizing: border-box;
    padding: {
      top: 17px;
      left: 17px;
    }
    border-left: $border1;
  }
  @include e('img') {
    width: 294px;
    height: 341px;
    background-image: url('../../assets/Mypage/void_img.png');
    background-size: cover;
    background-repeat: no-repeat;
    background-position: center center;
  }
  @include e('colum') {
    border-bottom: $border1;
    display: flex;
    height: 75px;
    line-height: 75px;
    position: relative;
    z-index: 1;
    &:last-child {
      border-bottom: none;
    }
  }
  @include e('key') {
    width: 150px;
    text-align: right;
    padding-right: 35px;
  }
  @include e('value') {
    flex: 1;
    padding-left: 35px;
  }
  @include e('preview-btn') {
    width: 140px;
    height: 40px;
    border-radius: 5px;
    background-color: #f7fbfc;
    border: 1px solid #98c4ca;
    font-size: 18px;
    color: #296169;
    position: absolute;
    z-index: 2;
    top: 17px;
    right: 337px;
    outline: none;
    transition: 0.5s;
    &:hover{
      background-color: #d5edf0;
    }
    &:active {
      background-color: #D8E6E9;
    }
  }
}
</style>
