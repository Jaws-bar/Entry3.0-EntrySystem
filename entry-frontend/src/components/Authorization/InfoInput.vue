<template>
  <div class="InfoInput">
    <div class="InfoInput__wapper">
      <div class="InfoInput__wapper__title">
        이메일
      </div>
      <div class="InfoInput__wapper__inputBox">
        <input type="text" class="InfoInput__wapper__inputBox__input"
        placeholder="example@dsmhs.kr" v-model="email"/>
        <div class="InfoInput__wapper__inputBox__check" v-if="verifi[0]">
          ✓
        </div>
      </div>
    </div>
    <div class="InfoInput__wapper">
      <div class="InfoInput__wapper__title">
        비밀번호
      </div>
      <div class="InfoInput__wapper__inputBox">
        <input type="password" class="InfoInput__wapper__inputBox__input"
        placeholder="●●●●●●●●●●●●" v-model="pw"/>
        <div class="InfoInput__wapper__inputBox__check" v-if="verifi[1]">
          ✓
        </div>
      </div>
      <span class="InfoInput__wapper__inputBox__warning">
          * 영문(대소문자 구분), 숫자 포함 8자리 이상, 특수기호 가능
      </span>
    </div>
    <div class="InfoInput__wapper pwcheck">
      <div class="InfoInput__wapper__title">
        비밀번호 확인
      </div>
      <div class="InfoInput__wapper__inputBox">
        <input type="password" class="InfoInput__wapper__inputBox__input"
        placeholder="●●●●●●●●●●●●" v-model="pwcheck"/>
        <div class="InfoInput__wapper__inputBox__check" v-if="verifi[2]">
          ✓
        </div>
        <div class="InfoInput__wapper__inputBox--wrong"
        v-if="(pw !== pwcheck)">
          비밀번호를 정확히 입력해주세요
        </div>
      </div>
    </div>
    <div class="btn">
      <button type="button"
            class="input-btn input-btn--next"
            v-if="(isAccept && verifi[0] && verifi[1] && verifi[2])"
            @click="moveToNextPage"
      >
        <span class="input-btn__arrow input-btn__arrow--right">〉</span>
        <span class="input-btn__text input-btn__text--next">인증하기</span>
      </button>
      <button type="button"
            class="input-btn input-btn--next input-btn input-btn--next--false"
            v-else
      >
        <span class="input-btn__arrow input-btn__arrow--right
        input-btn__arrow--right--false">〉</span>
        <span class="input-btn__text input-btn__text--next
        input-btn__text--false">인증하기</span>
      </button>
    </div>
  </div>
</template>

<script>
const emailReg = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,5}$/i;
const pwReg = /^(?=.*?[A-Z])*(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-])*.{8,}$/;
export default {
  computed: {
    email: {
      get() {
        return this.$store.state.auth.email;
      },
      set(value) {
        const RegResult = emailReg.test(value);
        this.$store.commit('updateEmail', value);
        if (this.$store.state.auth.verifi[0] !== RegResult) {
          this.$store.commit('updateVerifi', {
            index: 0,
            data: RegResult,
          });
        }
      },
    },
    pw: {
      get() {
        return this.$store.state.auth.pw;
      },
      set(value) {
        const RegResult = pwReg.test(value);
        this.$store.commit('updatePw', value);
        if (this.$store.state.auth.verifi[1] !== RegResult) {
          this.$store.commit('updateVerifi', {
            index: 1,
            data: RegResult,
          });
        }
      },
    },
    pwcheck: {
      get() {
        return this.$store.state.auth.pwcheck;
      },
      set(value) {
        const RegResult = this.$store.state.auth.pw === value;
        this.$store.commit('updatePwCheck', value);
        if (this.$store.state.auth.verifi[2] !== RegResult &&
        value !== '') {
          this.$store.commit('updateVerifi', {
            index: 2,
            data: RegResult,
          });
        }
      },
    },
    verifi() {
      return this.$store.state.auth.verifi;
    },
    isAccept() {
      return this.$store.state.auth.isAccept;
    },
  },
  methods: {
    moveToNextPage() {
      this.$emit('toNextPage');
    },
  },
};
</script>

<style lang="scss" scoped>
@import '../../style/setting.scss';
@import '../../style/input.scss';

.InfoInput{
  position: relative;
  width: 1140px;
  height: 226px;
  &::before, &::after{
    content: '';
    display: block;
    width: 1140px;
    height: 1px;
    position: absolute;
    background: -webkit-linear-gradient(left, transparent 0%, #a7a7a7 50%, transparent 100%);
    background: -moz-linear-gradient(left, transparent 0%, #a7a7a7 50%, transparent 100%);
    background: -ms-linear-gradient(left, transparent 0%, #a7a7a7 50%, transparent 100%);
    background: -o-linear-gradient(left, transparent 0%, #a7a7a7 50%, transparent 100%);
    background: linear-gradient(left, transparent 0%, #a7a7a7 50%, transparent 100%);
  }
  &::after{
    bottom: 0;
  }
  @include e(wapper){
    position: relative;
    &:not(.pwcheck){
      border-bottom: 1px solid #5f8a90;
    }
      @include e(title){
      position: relative;
      display: inline-block;
      width: 148px;
      height: 100%;
      font-size: 20px;
      color: #000;
      text-align: center;
      line-height: 75px;
      float: left;
    }
    @include e(inputBox){
      position: relative;
      display: block;
      width: 990px;
      height: 75px;
      @include e(input){
        width: 382px;
        height: 40px;
        border-radius: 5px;
        background-color: #fafdfe;
        border: solid 0.5px #5f8a90;
        font-size: 18px;
        padding-left: 16px;
        margin-top: 17px;
        color: #26484c;
        &::placeholder{
          color: #acbec1;
          font-weight: 300;
        }
        &:focus{
          outline: none;
        }
      }
      @include e(check){
        position: absolute;
        display: inline-block;
        font-size: 18px;
        color: #799da1;
        height: 40px;
        line-height: 40px;
        left: 525px;
        top: 20px;
      }
    @include m(wrong){
      display: inline-block;
      position: relative;
      top: -2px;
      left: 10px;
      width: 148px;
      height: 25px;
      padding-left: 12px;
      background: #FFFFFF;
      border-radius: 3px;
      border: #799da1 solid 1px;
      font-size: 10px;
      line-height: 25px;
      color: #26484c;
        &::after{
          content: '';
          position: absolute;
          border-style: solid;
          border-width: 5px 4px 5px 0;
          border-color: transparent #FFFFFF;
          display: block;
          width: 0;
          z-index: 1;
          left: -4px;
          top: 7px;
        }
        &::before{
          content: '';
          position: absolute;
          border-style: solid;
          border-width: 5px 4px 5px 0;
          border-color: transparent #799da1;
          width: 0;
          z-index: 0;
          left: -5px;
          top: 7px;
        }
      }
      @include e(warning){
        position: absolute;
        font-size: 14px;
        top: 30.5px;
        right: 23px;
        color: #939393;
      }
    }
  }
}
.btn{
  width: 100%;
  position: relative;
}
  // 버튼 2개
.input-btn {
  position: absolute;
  top: 25px;
  width: 150px;
  height: 50px;
  border-radius: 5px;
  background-color: #f7fbfc;
  border: 1px solid #5f8a90;
  outline: none;
  cursor: pointer;
  // 오른쪽 버튼
  @include m('next') {
    right: 0;
    @include m('false'){
      background-color: #f7fbfc;
      border-color: #a7a7a7;
    }
  }
  // 버튼 내부 화살표 및 세부 설정
  @include e('arrow') {
    font-size: 19px;
    color: #296169;
    @include m('right') {
      float: right;
      margin-right: 13px;
      @include m('false'){
        color: #979797;
      }
    }
  }
  // 버튼 내부 텍스트 및 세부 설정
  @include e('text') {
    font-size: 18px;
    color: #296169;
    @include m('false'){
      color: #979797;
    }
    @include m('prev') {
      float: right;
      margin: {
        top: 2px;
        right: 14px;
      }
    }
    @include m('next') {
      float: left;
      margin: {
        top: 2px;
        left: 14px;
      };
    }
  }
}
</style>
