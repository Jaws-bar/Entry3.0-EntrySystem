// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue';
import axios from 'axios';
import toastr from 'vue-toastr';
import vueCookies from 'vue-cookies';
import 'vue-toastr/dist/vue-toastr.min.css';
import store from './store';
import App from './App';
import router from './router';

// import toastr scss file: need webpack sass-loader

Vue.config.productionTip = false;
Vue.prototype.$axios = axios;
Vue.use(vueCookies);
Vue.use(toastr);

/* eslint-disable no-new */
new Vue({
  el: '#app',
  store,
  router,
  components: { App },
  template: '<App/>',
});
