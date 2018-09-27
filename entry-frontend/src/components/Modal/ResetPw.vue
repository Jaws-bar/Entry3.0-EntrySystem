<template>
  <div>
    <div class="PwReset" v-if="index === 2">
      <div class="PwReset__title modal-title">
        비밀번호 재설정
      </div>
      <span class="PwReset__text">아래의 이메일로 인증번호를 전송합니다</span>
      <form @submit.prevent="EmailSend()">
        <input type="text" class="modal--input PwReset__input"
        v-if="!notInput" v-model="email" placeholder="이메일"/>
        <input type="text" class="modal--input PwReset__input input-shake"
        v-else v-model="email" placeholder="이메일"/>
        <div class="PwReset__btns">
          <div class="modal--btn PwReset__btns__btn" v-on:click="reset">
            다시 입력
          </div>
          <button type="submit" class="modal--btn PwReset__btns__btn">
            인증번호 전송
          </button>
        </div>
      </form>
    </div>
    <certify-number v-if="index === 3" :email="email"/>
    <change-pw v-if="index === 4" :email="email"/>
    <change-success v-if="index === 5" :email="email"/>
  </div>
</template>

<script>
import CertifyNumber from './ResetPw/CertifyNumber';
import ChangePw from './ResetPw/ChangePw';
import ChangeSuccess from './ResetPw/ChangeSuccess';

const emailReg = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,5}$/i;
export default {
  components: {
    CertifyNumber,
    ChangePw,
    ChangeSuccess,
  },
  data: () => ({
    email: '',
    notInput: false,
  }),
  methods: {
    EmailSend() {
      const email = this.email;
      if (email === '' || !emailReg.test(email)) {
        this.notInput = true;
        setTimeout(() => {
          this.notInput = false;
        }, 2000);
      } else {
        this.$store.commit('changeIndex', {
          index: 3,
        });
        /*
          this.$axios.post('/ResetPW', { email }).then(({ data }) => {
            if (data.type) {
            } else {
              this.notInput = true;
              setTimeout(() => {
                this.notInput = false;
              }, 2000);
            }
          });
        */
      }
    },
    reset() {
      this.email = '';
    },
  },
  computed: {
    index() {
      return this.$store.state.modal.index;
    },
  },
};
</script>

<style lang="scss" scoped>
@import '../../style/setting';

.PwReset{
  @include e('text'){
    display: block;
    margin: 34px 0 14px 0;
    color: #000;
    font-size: 12px;
    text-align: center;
  }
  @include e('input'){
    width: 250px;
    padding: 13px 0 13px 0;
    text-align: center;
    margin-bottom: 10px;
  }
  @include e('btns'){
    @include e('btn'){
      display: inline-block;
      width: 121px;
      margin-right: 4px;
    }
  }
}
</style>
