import contact from '../api/contact';
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
    contact.updateDocument('document', payload)
    .catch(err => Promise.reject(err.response));
  },

  getIntro: ({ commit }, payload) => {
    contact.getDocument('document', payload)
    .then((response) => {
      const { data } = response;
      commit('updateIntroAndPlan', data);
    })
    .catch(err => Promise.reject(err.response));
  },

  getGrades: ({ commit }, payload) => {
    contact.getGrades('grade', payload)
    .then((response) => {
      const { data } = response.data;
      commit('getGrades', utils.getGrade(data));
    })
    .catch(err => Promise.reject(err.response));
  },

  updateGrade: (store, payload) => {
    contact.updateGrades('grade', payload)
    .catch(err => Promise.reject(err.response));
  },

  getClassify: ({ commit }, payload) => {
    contact.getClassify('classification', payload)
    .then((response) => {
      const { data } = response;
      commit('updateClassify', data);
    });
  },

  getInfo: ({ commit }, payload) => {
    contact.getInfo('info', payload)
    .then((response) => {
      const { data } = response;
      commit('updateInfo', data);
    });
  },
};

export default actions;
