<template>
  <div class="info-preview">
    <navigation />
    <headline :title="title" :subText="subText" />
    <div class="pdf-target a4">
      <application
        :nowYear="nowYear"
        :nowMonth="nowMonth"
        :nowDay="nowDay"/>
      <introduction class="introduction"/>
      <academic-plan/>
      <recommendation-letter
        v-if="admission !== 'NORMAL'"
        :nowYear="nowYear"
        :nowMonth="nowMonth"
        :nowDay="nowDay"/>
      <quitting-smoking
        :nowYear="nowYear"
        :nowMonth="nowMonth"
        :nowDay="nowDay"/>
    </div>
    <div class="btn-cover">
      <button
        class="input-btn"
        @click="pagePrint">
        <span class="input-btn__text">모두 인쇄</span>
      </button>
    </div>
    <prev-next-btn
      :prevShow="true"
      :nextShow="true"
      :prevLink="prevLink"
      :disablePrevClick="true"
      :onClick="confirm.bind(this)"/>
    <entry-footer />
  </div>
</template>

<script>
import Navigation from '../common/Navigation';
import Headline from '../common/Headline';
import PrevNextBtn from '../common/PrevNextBtn';
import EntryFooter from '../common/EntryFooter';
import AcademicPlan from './AcademicPlan';
import Application from './Application';
import Introduction from './Introduction';
import QuittingSmoking from './QuittingSmoking';
import RecommendationLetter from './RecommendationLetter';

export default {
  name: 'preview',
  components: {
    Navigation,
    Headline,
    PrevNextBtn,
    EntryFooter,
    AcademicPlan,
    Application,
    Introduction,
    QuittingSmoking,
    RecommendationLetter,
  },
  data() {
    return {
      title: '원서 미리보기',
      subText: '대덕소프트웨어마이스터고등학교',
      prevLink: '/intro',
      nowYear: 0,
      nowMonth: 0,
      nowDay: 0,
    };
  },
  created() {
    const dateObj = (new Date());
    this.nowYear = dateObj.getFullYear();
    this.nowMonth = dateObj.getMonth() + 1;
    this.nowDay = dateObj.getDate();
    const token = this.$cookies.get('accessToken');
    const { e } = this.$toastr;
    if (token === undefined || token === null || token === '') {
      this.$router.push('/');
      e('해당 페이지는 로그인이 필요합니다.');
      this.$store.commit('changeIndex', {
        index: 1,
      });
    }
    if (!this.isValid) {
      e('원서를 전부 채우지 못하였습니다.<br/>모두 채운 뒤 미리보기를 볼 수 있습니다.');
    }
  },
  computed: {
    admission() {
      return this.$store.state.classify.admission;
    },
    isValid: {
      get() {
        return this.$store.state.mypage.validationResult.isValid;
      },
    },
  },
  methods: {
    pagePrint() {
      const initBody = document.querySelector('.app');
      const pdfTarget = document.querySelector('.pdf-target');
      const pdfForPrint = pdfTarget.cloneNode(true);
      window.onbeforeprint = () => {
        initBody.style.display = 'none';
        pdfForPrint.classList.add('on');
        document.body.appendChild(pdfForPrint);
      };
      window.onafterprint = () => {
        initBody.style.display = 'block';
        document.body.removeChild(pdfForPrint);
      };
      window.print();
    },
    confirm() {
      this.$store.commit('changeIndex', {
        index: 7,
      });
    },
  },
};
</script>

<style lang="scss" scoped>
@import '../../style/setting';

@page a4sheet { size: 21.0cm 29.7cm }

.pdf-target {
  display: flex;
  flex-flow: column;
  width: 100%;
  &>div {
    zoom: 1.8;
    margin: 0 auto 30px;
    &:last-child {
      margin: 0 auto;
    }
  }
  &.on {
    position: absolute;
    top: 10px;
    left: 50%;
    transform: translateX(-50%);
  }
}

.info-preview {
  .pdf-target {
    background-color: #525659;
    width: 1140px;
    height: 1055px;
    overflow-y: scroll;
    margin: 0 auto;
    padding: 50px 0;
    &>div {
      zoom: 1.3;
      background-color: white;
    }
  }
}

$inner-btn: #296169;
.btn-cover {
  width: 1140px;
  height: 100px;
  margin: 0 auto;
  position: relative;

  .input-btn {
    position: absolute;
    top: 25px;
    width: 150px;
    height: 50px;
    line-height: 50px;
    border-radius: 5px;
    background-color: #f7fbfc;
    border: 1px solid #5f8a90;
    outline: none;
    cursor: pointer;
    transition: 0.5s;
    &:active {
      background-color: #D8E6E9;
    }
    right: 0;

    @include e('text') {
      font-size: 18px;
      color: $inner-btn;
      float: left;
      margin: {
        top: 2px;
        left: 14px;
      };
    }
  }
}
</style>
