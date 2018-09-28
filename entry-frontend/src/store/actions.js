import axios from 'axios';
import utils from '../utils';

export const actions = {
  updateaccessToken: context => context.commit('updateaccessToken'),
  // modal 관련 이벤트
  changeIndex: context => context.commit('changeIndex'),
  // auth 관련 action
  updateAccept: context => context.commit('updateAccept'),
  updateEmail: context => context.commit('updateEmail'),
  updatePw: context => context.commit('updateEmail'),
  updatePwCheck: context => context.commit('updateEmail'),
  updateverify: context => context.commit('updateverify'),
  // classify 관련 action
  updateisGED: context => context.commit('updateisGED'),
  updateadmission: context => context.commit('updateadmission'),
  updateadmissionDetail: context => context.commit('updateadmissionDetail'),
  updateRegion: context => context.commit('updateRegion'),
  updateIsGraduated: context => context.commit('updateIsGraduated'),
  updateGraduationYear: context => context.commit('updateGraduationYear'),
  updateadditionalType: context => context.commit('updateadditionalType'),
  updateGraduateType: context => context.commit('updateGraduateType'),
  // PersonInfo 관련 action
  updatePersonName: context => context.commit('updatePersonName'),
  updateSex: context => context.commit('updateSex'),
  updateYear: context => context.commit('updateYear'),
  updateMonth: context => context.commit('updateMonth'),
  updateDay: context => context.commit('updateDay'),
  updateSchoolClass: context => context.commit('updateSchoolClass'),
  updateSchoolnumber: context => context.commit('updateSchoolnumber'),
  updateSchoolName: context => context.commit('updateSchoolName'),
  updateParentName: context => context.commit('updateParentName'),
  updateSchoolContact: context => context.commit('updateSchoolContact'),
  updateParentContact: context => context.commit('updateParentContact'),
  updateContact: context => context.commit('updateContact'),
  updateZip: context => context.commit('updateZip'),
  updateAddress: context => context.commit('updateAddress'),
  updateDetailedAddress: context => context.commit('updateDetailedAddress'),
  updateImgPath: context => context.commit('updateImgPath'),
  updateIntro: (state, payload) => {
    axios({
      url: 'http://entrydsm.hs.kr/api/me/document',
      method: 'put',
      headers: { Authorization: `JWT ${payload.token}` },
      data: {
        introduce: payload.introduce,
        studyPlan: payload.studyPlan,
      },
    })
    .then((response) => {
      console.log(response);
    })
    .catch(err => Promise.reject(err.response));
  },

  getIntro: ({ commit }, payload) => {
    axios.get('http://entrydsm.hs.kr/api/me/document', {
      headers: { Authorization: `JWT ${payload}` },
    })
    .then((response) => {
      const { data } = response;
      commit('updateIntroAndPlan', data);
    })
    .catch(err => Promise.reject(err.response));
  },

  getGrades: ({ commit }, payload) => {
    axios.get('http://entrydsm.hs.kr/api/me/grade', {
      headers: { Authorization: `JWT ${payload}` },
    })
    .then((response) => {
      const { data } = response.data;
      commit('getGrades', utils.getGrade(data));
    })
    .catch(err => Promise.reject(err.response));
  },

  updateGrade: (store, payload) => {
    axios({
      url: 'http://entrydsm.hs.kr/api/me/grade',
      method: 'put',
      headers: { Authorization: `JWT ${payload.token}` },
      data: payload.sendData,
    })
    .then((response) => {
      console.log(response);
    })
    .catch(err => Promise.reject(err.response));
  },
};

export default actions;
