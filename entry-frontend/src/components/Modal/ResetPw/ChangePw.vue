<template>
  <div class="ChangePw">
    <div class="ChangePw__title modal-title" v-if="(Checkpw && isSame)">
      비밀번호 재설정
    </div>
    <div class="ChangePw__title ChangePw__title--false modal-title" v-else>
      비밀번호 재설정
    </div>
    <div class="ChangePw__title--false--alert" v-if="!Checkpw">
      비밀번호 생성규칙에 맞지 않습니다.
    </div>
    <div class="ChangePw__title--false--alert" v-else-if="!isSame">
      비밀번호가 일치하지 않습니다
    </div>
    <div class="ChangePw__Inputbox">
      <input type="password" class="modal--input ChangePw__input"
      v-if="Checkpw" v-model="pw" placeholder="비밀번호"/>
      <input type="password" class="modal--input ChangePw__input input-shake"
      v-else v-model="pw" placeholder="비밀번호"/>
      <input type="password" class="modal--input ChangePw__input"
      v-if="isSame" v-model="pwcheck" placeholder="비밀번호 확인"/>
      <input type="password" class="modal--input ChangePw__input input-shake"
      v-else v-model="pwcheck" placeholder="비밀번호 확인"/>
      <div class="ChangePw__btns">
        <div class="modal--btn ChangePw__btns__btn" @click="submit">
          완료
        </div>
      </div>
      <span class="ChangePw--hint">비밀번호 생성규칙</span>
      <div class="ChangePw__hover">
        <div class="ChangePw__hover__bubble">
          <span class="ChangePw__hover__bubble__contents">
              영문(대소문자 포함), 숫자 포함 8자리 이상 특수기호 가능
          </span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
const pwReg = /^(?=.*?[A-Z])*(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-])*.{8,}$/;
export default {
  data: () => ({
    pw: '',
    pwcheck: '',
    Checkpw: true,
    isSame: true,
  }),
  methods: {
    submit() {
      const pw = this.pw;
      const pwcheck = this.pwcheck;
      this.isSame = (pw === pwcheck);
      this.Checkpw = pwReg.test(pw);
      if (this.isSame && this.Checkpw) {
        /*
          this.$axios.post('/ChangePw', {  }).then(({ data }) => {
            if (data.type) {
            } else {
              this.notInput = true;
              setTimeout(() => {
                this.notInput = false;
              }, 2000);
            }
          });
        */
        this.$store.commit('changeIndex', {
          index: 5,
        });
        return;
      }
      setTimeout(() => {
        this.isSame = true;
        this.Checkpw = true;
      }, 4000);
    },
  },
};
</script>

<style lang="scss" scoped>
@import '../../../style/setting';
.ChangePw {
  @include e('title') {
    margin-bottom: 32px;
    @include m('false') {
      animation: TitleWrong 4s;
      @include m('alert') {
        animation: MsgWrong 4s;
        display: block;
        width: 250px;
        text-align: center;
        font-size: 14px;
        color: #000;
        position: absolute;
        top: 109px;
        opacity: 0;
      }
    }
  }
  @include e('Inputbox') {
    margin: 0 auto;
    width: 250px;
  }
  @include m('hint') {
    margin-top: 16px;
    display: block;
    width: 100%;
    text-align: center;
    font-size: 12px;
    color: #5a8f97;
  }
  @include e('hover') {
    cursor: pointer;
    position: relative;
    display: block;
    top: -20px;
    opacity: 0;
    &:hover{
      animation: bubble 0.5s;
      opacity: 1;
    }
    @include e('bubble') {
      position: relative;
      top: 30px;
      width: 240px;
      height: 25px;
      padding: 0px;
      background: #FFF;
      border-radius: 6px;
      border: #799da1 solid 1px;
      display: flex;
      justify-content: center;
      align-items: center;
      &::after {
        content: '';
        position: absolute;
        border-style: solid;
        border-width: 0 10px 6px;
        border-color: #FFFFFF transparent;
        display: block;
        width: 0;
        z-index: 1;
        top: -6px;
        left: 110px;
      }
      &::before {
        content: '';
        position: absolute;
        border-style: solid;
        border-width: 0 10px 6px;
        border-color: #799da1 transparent;
        display: block;
        width: 0;
        z-index: 0;
        top: -7px;
        left: 110px;
      }
      @include e('contents'){
        font-size: 8px;
      }
    }
  }
}

@keyframes TitleWrong {
  0% {
    opacity: 1;
    top: 0px;
  }
  13% {
    opacity: 0;
    top: -30px;
    display: none;
  }
  87% {
    opacity: 0;
    top: 30px;
    display: block;
  }
  100% {
    top: 0;
    opacity: 1;
  }
}

@keyframes MsgWrong {
  0% {
    opacity: 0;
    top: 139px;
    display: block;
  }
  13% {
    opacity: 1;
    top: 109px;
  }
  87% {
    top: 109px;
    opacity: 1;
  }
  100% {
    top: 79px;
    opacity: 0;
    display: none;
  }
}

@keyframes bubble {
  0%{
    opacity: 0;
    top: 9px;
  }
  100%{
    opacity: 1;
    top: -19px;
  }
}
</style>
