<template>
  <div class="nav">
    <nav class="nav__wrapper">
    <div class="nav__wrapper__contants">
      <div class="nav__wrapper__contants__logo">
      <router-link to="/" class="main-link">
        <img
          @click="$emit('on-main')"
          class="nav__wrapper__contants__logo--img"
          src="./../../assets/entry_logo.png"
          alt="EntryDSM 로고">
      </router-link>
    </div>
    <ul class="nav__wrapper__contants__list">
      <li class="nav__wrapper__contants__list__link">
        <a href="http://dsmhs.djsch.kr/boardCnts/list.do?boardID=54814&m=0602&s=dsmhs" target="_blank">공지사항</a>
      </li>
      <li class="nav__wrapper__contants__list__link">
        <a href="http://dsmhs.djsch.kr/boardCnts/list.do?boardID=76196&m=0605&s=dsmhs" target="_blank">Q&A</a>
      </li>
      <li class="nav__wrapper__contants__list__link">
        <router-link to="/info-summary">전형요강</router-link>
      </li>
      <li class="nav__wrapper__contants__list__link"
        @click="$emit('on-schedule')">
        <router-link to="/">모집일정</router-link>
      </li>
      <li class="nav__wrapper__contants__list__link
        nav__wrapper__contants__list__link--login"
        v-if="isLogin"
        @click="changeUserModal">
          <span class="username-btn">{{userName}}</span>
      </li>
    </ul>
    <user-modal :onUserModal="onUserModal" v-if="onUserModal"/>
    </div>
  </nav>
  <modal/>
  </div>
</template>

<script>
/* eslint-disable */
import Modal from '../Modal';
import userModal from './userModal';

export default {
  name: 'navigation',
  components: {
    Modal,
    userModal,
  },
  data() {
    return {
      onUserModal: false,
    };
  },
  mounted() {
    this.$on('CloseModal', () => {
      this.index = 0;
    });
    const isChrome = !!window.chrome && !!window.chrome.webstore;
    if (!isChrome) {
      alert('EntryDSM은 Window, Mac, Linux환경의 크롬에서만 접속 할 수 있습니다.');
      window.location.href = 'https://www.google.com/intl/ko_ALL/chrome/';
    }
  },
  computed: {
    index() {
      return this.$store.state.modal.index;
    },
    userName() {
      return this.$store.state.email;
    },
    isLogin: {
      get() {
        const token = this.$cookies.get('accessToken');
        const { s, e } = this.$toastr;
        if (this.$store.state.accessToken !== null && typeof token === 'string') {
          return true;
        } else if (this.$store.state.accessToken === null
        && token !== undefined
        && token !== null
        && localStorage.getItem('name') !== undefined) {
          this.$store.commit('updateaccessToken', {
            accessToken: token,
            email: localStorage.getItem('name'),
          });
          this.$store.dispatch('getMypage',
            {
              token,
              s,
              e,
            },
          );
          this.$store.dispatch('getClassify', {
            token,
            s,
            e,
          });
          this.$store.dispatch('getInfo', {
            token,
            s,
            e,
          });
          this.$store.dispatch('getGrades', {
            token,
            s,
            e,
          });
          this.$store.dispatch('getIntro', {
            token,
            s,
            e,
          });
          return true;
        }
        return false;
      },
    },
  },
  methods: {
    changeIndex() {
      this.$store.commit('changeIndex', {
        index: 1,
      });
    },
    changeUserModal() {
      this.onUserModal = !this.onUserModal;
    },
    writeApplication() {
      const token = this.$cookies.get('accessToken');
      const { e } = this.$toastr;
      if (token !== undefined && token !== null && token !== '') {
        this.$router.push('/classify');
      } else {
        e('로그인이 필요한 기능입니다.');
        this.$store.commit('changeIndex', {
          index: 1,
        });
      }
    },
  },
};
</script>


<style lang="scss" scoped>
@import '../../style/setting.scss';
.nav {
  box-shadow: 0 2px 10px 0 rgba(99, 141, 147, 0.05);

  @include e('wrapper'){
    height: 60px;
    padding: 0 auto;
    @include e('contants'){
      position: relative;
      width: 1140px;
      height: 100%;
      margin: 0 auto;
      display: flex;
      flex-direction: row;

      .main-link { height: 30px; }

      @include e('logo'){
        display: flex;
        justify-content: center;
        align-items: center;
        cursor: pointer;
        @include m('img') {
          height: 30px;
        }
      }
      @include e('list') {
        position: absolute;
        display: flex;
        align-items: center;
        height: 100%;
        right: 0;
        @include e('link'){
          line-height: 60px;
          height: 100%;
          padding: 0 15px;
          margin-left: 15px;
          cursor: pointer;
          display: inline-block;
          @include m('login'){
            margin-left: 67px;
            .username-btn {
              background-color: #65bbb7;
              padding: 7px 18px;
              border-radius: 30px;
              color: #fff;
            }
          }
        }
      }
    }
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
.rotate{
  transform: rotate(180deg);
}
.btn{
  display: inline-block;
  transition: 0.5s;
}
</style>
