<template>
  <div class="info-preview">
    <navigation />
    <headline :title="title" :subText="subText" />
    <div class="pdf-target a4">
      <div class="cover">
        <application></application>
      </div>
      <div class="cover">
        <introduction class="introduction"></introduction>
      </div>
      <div class="cover">
        <academic-plan></academic-plan>
      </div>
      <div class="cover" v-if="admission !== 'NORMAL'">
        <recommendation-letter></recommendation-letter>
      </div>
      <div class="cover">
        <quitting-smoking></quitting-smoking>
      </div>
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
      :nextLink="nextLink"/>
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
      nextLink: '/submit',
    };
  },
  created() {
    const token = this.$cookies.get('accessToken');
    const { e } = this.$toastr;
    if (token !== undefined || token !== null || token !== '') {
      this.$router.push('/');
      e('로그인이 되지 않은 사용자입니다.');
      this.$store.commit('changeIndex', {
        index: 1,
      });
    }
  },
  computed: {
    admission() {
      return this.$store.state.classify.admission;
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
  },
};
</script>

<style lang="scss" scoped>
@import '../../style/setting';

@page a4sheet { size: 21.0cm 29.7cm }
.a4 { page: a4sheet; page-break-after: always }

.pdf-target {
  margin: 0 auto;
  width: 595px;
  display: flex;
  flex-flow: column;
  .cover {
    margin: 20px 0;
  }
  &.on {
    position: absolute;
    top: 10px;
    left: 50%;
    transform: translateX(-50%);
    .cover {
      height: 1565px;
      width: fit-content;
      display: flex;
      align-items: center;
      margin: 0;
    }
    .cover div {
      transform: scale(1.8, 1.8);
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
