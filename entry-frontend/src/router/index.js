import Vue from 'vue';
import Router from 'vue-router';
import Brochure from '../components/Info/Brochure';
import SelectionInfo from '../components/Info/SelectionInfo';
import IntroduceNPlan from '../components/IntroNPlan/IntroduceNPlan';
import Classification from '../components/Classification/Classification';
import PersonalInformation from '../components/PersonalInformation/PersonalInformation';
import Authorization from './../components/Authorization/Authorization';

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: '/',
    },
    {
      path: '/brochure',
      name: 'brochure',
      component: Brochure,
    },
    {
      path: '/selection-info',
      name: 'selection-info',
      component: SelectionInfo,
    },
    {
      path: '/introduce',
      name: 'introduce',
      component: IntroduceNPlan,
    },
    {
      path: '/classify',
      name: 'classification',
      component: Classification,
    },
    {
      path: '/auth',
      name: 'authorization',
      component: Authorization,
    },
    {
      path: '/personal',
      name: 'personal-information',
      component: PersonalInformation,
    },
  ],
});
