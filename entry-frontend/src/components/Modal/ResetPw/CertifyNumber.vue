<template>
  <div class="PwReset">
    <div class="PwReset__title modal-title">
      비밀번호 재설정
    </div>
    <span class="PwReset__text --title">메일함에 전송된 인증번호를 입력해주세요</span>
    <form @submit.prevent="Verify()">
      <input type="text" class="modal--input PwReset__input"
      v-if="notInput" v-model="certify" placeholder="••••••" maxlength="6"/>
      <input type="text" class="modal--input PwReset__input input-shake"
      v-else v-model="certify" placeholder="••••••" maxlength="6"/>
      <div class="PwReset__btns">
        <div class="modal--btn PwReset__btns__btn" v-on:click="reset">
          다시 입력
        </div>
        <div class="modal--btn PwReset__btns__btn" v-on:click="Resend">
          재전송
        </div>
        <button type="submit" class="modal--btn PwReset__btns__btn --last">
          확인
        </button>
      </div>
    </form>
    <span class="PwReset__text --hint">이메일이 오지 않습니다</span>
    <div class="PwReset__text--hint">
      <div class="PwReset__text--hint__hover">
        <div class="PwReset__text--hint__hover__bubble">
          <span>{{email}}</span>
          <div class="PwReset__text--hint__hover__bubble__message">
            <span>위의 메일 주소가 맞는지 다시 한 번 확인하시고,</span><br/>
            <span>스팸 메일함도 확인해주세요.</span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data: () => ({
    certify: '',
    notInput: false,
  }),
  props: ['email'],
  methods: {
    Verify() {
      // 구현 예정
      /*
        const certify = this.certify;
        if (certify === '') {
          this.notInput = true;
          setTimeout(() => {
            this.notInput = false;
          }, 1500);
        } else {
      */
      this.$store.commit('changeIndex', {
        index: 4,
      });
      // axios 부분
      /*
          this.$axios.post('/Certify', { certify }).then(({ data }) => {
            if (data.type) {
            } else {
              this.notInput = true;
              setTimeout(() => {
                this.notInput = false;
              }, 2000);
            }
          });
      }
      */
    },
    reset() {
      this.certify = '';
    },
    Resend() {
    // axios 부분
    /*
      this.$axios.post('/Resend', { certify }).then(({ data }) => {
          if (data.type) {
          } else {
            this.notInput = true;
          setTimeout(() => {
            this.notInput = false;
          }, 2000);
        }
      });
    */
    },
  },
};
</script>

<style lang="scss" scoped>
@import '../../../style/setting';

.PwReset{
  @include e('text'){
    display: block;
    margin: 34px 0 14px 0;
    font-size: 12px;
    text-align: center;
    &.--title{
      color: #000;
    }
    &.--hint{
      margin-top: 16px;
      color: #5a8f97;
      cursor: pointer;
    }
    @include m('hint'){
      position: absolute;
      top: 290px;
      left: 80px;
      height: 80px;
      opacity: 0;
      padding-top: 25px;
      cursor: pointer;
      @include e('hover'){
        position: relative;
        display: flex;
        justify-content: center;
        align-items: center;
        width: 230px;
        height: 60px;
        border-radius: 9px;
        border: #799da1 solid 1px;
        background: #FFF;
        top: 25px;
        &::after{
          content: '';
          position: absolute;
          border-style: solid;
          border-width: 0 10px 9px;
          border-color: #FFFFFF transparent;
          display: block;
          width: 0;
          z-index: 1;
          top: -8px;
          left: 105px;
        }
        &::before{
          content: '';
          position: absolute;
          border-style: solid;
          border-width: 0 10px 9px;
          border-color: #799da1 transparent;
          display: block;
          width: 0;
          z-index: 0;
          top: -9px;
          left: 105px;
        }
        @include e('bubble'){
          color: #26484c;
          text-align: center;
          & span{
            font-size: 10px;
          }
          @include e('message'){
            & span{
              font-size: 8px;
            }
            &::before{
              content: '';
              display: block;
              width: 100px;
              height: 1px;
              background-color: #70b1ba;
              margin: 5px auto;
            }
          }
        }
      }
      &:hover{
        animation: openmsg 0.5s;
        opacity: 1;
      }
    }
  }
  @include e('input'){
    width: 250px;
    padding: 13px 0 13px 0;
    text-align: center;
    margin-bottom: 10px;
    letter-spacing: 10px;
  }
  @include e('btns'){
    @include e('btn'){
      display: inline-block;
      width: 77px;
      margin-right: 6px;
      &.--last{
        margin-right: 0;
      }
    }
  }
}

@keyframes openmsg {
  0%{
    top: 320px;
    opacity: 0;
  }
  80%{
    top: 280px;
    opacity: 1;
  }
  100%{
    top: 290px;
  }
}
</style>
