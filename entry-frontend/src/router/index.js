import Vue from 'vue';
import Router from 'vue-router';
import InfoBrochure from '../components/Info/InfoBrochure';
import InfoSummary from '../components/Info/InfoSummary';
import MainPage from '../components/MainPage/MainPage';
import IntroduceNPlan from '../components/IntroNPlan/IntroduceNPlan';
import Classification from '../components/Classification/Classification';
import GradeInput from '../components/GradeInput/GradeInput';
import PersonalInformation from '../components/PersonalInformation/PersonalInformation';
import Authorization from './../components/Authorization/Authorization';
import Preview from './../components/Preview/Preview';
import SignupConfirm from './../components/Authorization/SignupConfirm';
import Mypage from './../components/Mypage/Mypage';

Vue.use(Router);

export default new Router({
  scrollBehavior(to, from, savedPosition) {
    return savedPosition || { x: 0, y: 0 };
  },
  routes: [{
    path: '/',
    name: 'main-page',
    component: MainPage,
  }, {
    path: '/auth',
    name: 'authorization',
    component: Authorization,
  }, {
    path: '/info-brochure',
    name: 'info-brochure',
    component: InfoBrochure,
  }, {
    path: '/info-summary',
    name: 'info-summary',
    component: InfoSummary,
  }, {
    path: '/classify',
    name: 'classification',
    component: Classification,
  }, {
    path: '/personal',
    name: 'personal-information',
    component: PersonalInformation,
  }, {
    path: '/intro',
    name: 'introduce',
    component: IntroduceNPlan,
  }, {
    path: '/preview',
    name: 'preview',
    component: Preview,
  }, {
    path: '/grade',
    name: 'grade-input',
    component: GradeInput,
  }, {
    path: '/confirm/:code',
    name: 'signup-confirm',
    component: SignupConfirm,
    props: true,
  },
  {
    path: '/mypage',
    name: 'mypage',
    component: Mypage,
  }],
});
