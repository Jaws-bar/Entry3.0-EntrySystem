<template>
  <div class="userModal">
    <div class="userModal__status">
      {{finalSubmit}}
    </div>
    <div class="userModal__hr" />
    <div class="userModal__Icon" @click="changeModalIndex">
      <div class="userModal__Icon__box userModal__Icon__box--info" v-if="isInfoValid">
      </div>
      <div class="userModal__Icon__box userModal__Icon__box--info userModal__Icon__box--false" v-else>
      </div>
      <div class="userModal__Icon__box userModal__Icon__box--classify" v-if="isClassificationValid">
      </div>
      <div class="userModal__Icon__box userModal__Icon__box--classify userModal__Icon__box--false" v-else>
      </div>
      <div class="userModal__Icon__box userModal__Icon__box--plan" v-if="isDocumentValid">
      </div>
      <div class="userModal__Icon__box userModal__Icon__box--plan userModal__Icon__box--false" v-else>
      </div>
      <div class="userModal__Icon__box userModal__Icon__box--gradeInput" v-if="isGradeValid">
      </div>
      <div class="userModal__Icon__box userModal__Icon__box--gradeInput userModal__Icon__box--false" v-else>
      </div>
    </div>
    <div class="userModal__hr userModal__hr--long" />
    <router-link class="userModal__menu" to="/mypage">
      마이페이지
    </router-link>
    <div class="userModal__menu" @click="logout">
      로그아웃
    </div>
  </div>
</template>

<script>
export default {
  name: 'userModal',
  props: {
    onUserModal: {
      type: Boolean,
    },
  },
  created() {
    const token = this.$cookies.get('accessToken');
    this.$store.dispatch('getMypage', {
      token,
    });
  },
  computed: {
    finalSubmit: {
      get() {
        if (this.$store.state.mypage.applyStatus.finalSubmit) {
          return '제출완료';
        }
        return '미제출';
      },
    },
    isClassificationValid: {
      get() {
        if (this.$store.state.mypage.validationResult.isClassificationValid) {
          return true;
        }
        return false;
      },
    },
    isDocumentValid: {
      get() {
        if (this.$store.state.mypage.validationResult.isDocumentValid) {
          return true;
        }
        return false;
      },
    },
    isInfoValid: {
      get() {
        if (this.$store.state.mypage.validationResult.isInfoValid) {
          return true;
        }
        return false;
      },
    },
    isGradeValid: {
      get() {
        if (this.$store.state.mypage.validationResult.isGradeValid) {
          return true;
        }
        return false;
      },
    },
  },
  methods: {
    logout() {
      this.$cookies.remove('accessToken');
      localStorage.removeItem('name');
      this.$store.commit('updateaccessToken', {
        accessToken: null,
        email: null,
      });
      this.$router.push('/');
      window.location.reload();
    },
    changeModalIndex() {
      this.$store.commit('changeIndex', {
        index: 8,
      });
    },
    changeRouter(url) {
      this.$router.push(`/${url}`);
      this.$store.commit('changeIndex', {
        index: 0,
      });
    },
  },
};
</script>

<style scoped lang="scss">
@import '../../style/setting';
.userModal{
  position: absolute;
  top: 60px;
  right: 0;
  width: 150px;
  height: 230px;
  border-radius: 5px;
  background-color: #ffffff;
  box-shadow: 0 0 20px -7px rgba(76, 166, 160, 1);
  z-index: 5;
  transition: 0.5s;
  opacity: 1;
  animation: pop 0.5s;
  padding-top: 21px;
  @include e('status'){
    text-align: center;
  }
  @include e('hr'){
    display: block;
    width: 43px;
    height: 1px;
    background: -webkit-linear-gradient(left, transparent 0%, #8aaaad 50%, transparent 100%);
    margin: 10px auto 10px auto;
    @include m('long'){
      width: 100px;
      margin-bottom: 0;
    }
  }
  @include e('Icon'){
    width: 80px;
    height: 80px;
    transform: rotate(-45deg);
    position: relative;
    margin: 25px auto 30px auto;
    cursor: pointer;
    @include e('box'){
      display: inline-block;
      width: 35px;
      height: 35px;
      border: solid 1px #96b2b5;
      float: left;
      transition: 0.5s;
      position: absolute;
      display: flex;
      justify-content: center;
      align-items: center;
      @include m('false') {
        background-color: #FFF !important;
      }
      @include e('text') {
        transform: rotate(45deg);
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
      }
      @include m('classify'){
        background-color: #d3ebf1;
        top:0;
        right: 0;
      }
      @include m('plan'){
        background-color: #abd7e2;
        bottom:0;
        left: 0;
      }
      @include m('gradeInput'){
        background-color: #c0e2eb;
        bottom:0;
        right: 0;
      }
    }
  }
  @include e('menu'){
    display: block;
    text-align: center;
    width: 100%;
    height: 30px;
    line-height: 30px;
    font-size: 12px;
    transition: 0.5s;
    cursor: pointer;
    &:hover{
      background: -webkit-linear-gradient(left, transparent 0%, #f4f9fa 50%, transparent 100%);
    }
  }
}
@keyframes pop{
  0%{
    top: 80px;
    right: 0;
    opacity: 0;
  }
  80%{
    top: 50px;
    right: 0;
    opacity: 1;
  }
  100%{
    top: 60px;
    right: 0;
    opacity: 1;
  }
}
a {
  &:link{
    color: #000;
    text-decoration: none;
  }
  &:visited{
    color: #000;
    text-decoration: none;
  }
}
</style>

