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
    .then(() => payload.s('자기소개서 및 학업계획서 정보가<br/>임시저장 되었습니다.'))
    .catch((err) => {
      payload.e('자기소개서와 학업계획서 정보저장을 실패하였습니다..');
      Promise.reject(err.response);
    });
  },
  gotoPreview: ({ commit }, payload) => {
    contact.updateDocument('document', payload)
    .then(() => {
      payload.s('자기소개서 및 학업계획서 정보가<br/>임시저장 되었습니다.');
      contact.getMypage('mypage', payload.token)
      .then((response) => {
      const { data } = response;
      commit('checkValidate', data);
    });
    })
    .catch((err) => {
      payload.e('자기소개서와 학업계획서 정보저장을 실패하였습니다..');
      Promise.reject(err.response);
    });
  },

  getIntro: ({ commit }, payload) => {
    contact.getDocument('document', payload.token)
    .then((response) => {
      const { data } = response;
      commit('updateIntroAndPlan', data);
      payload.s('자기소개서 및 학업계획서 정보를<br/>불러왔습니다');
    })
    .catch((err) => {
      payload.e('자기소개서 및 학업계획서 정보를<br/>불러오는데 실패하였습니다.');
      Promise.reject(err.response);
    });
  },

  getGrades: ({ commit }, payload) => {
    contact.getGrades('grade', payload.token)
    .then((response) => {
      const { data } = response.data;
      commit('getGrades', utils.getGrade(data));
      payload.s('성적 정보를 불러왔습니다');
    })
    .catch((err) => {
      payload.e('성적 정보를 불러오는데<br/>실패하였습니다.');
      Promise.reject(err.response);
    });
  },

  updateGrade: (store, payload) => {
    contact.updateGrades('grade', payload)
    .then(() => payload.s('성적 정보가 임시저장 되었습니다.'))
    .catch((err) => {
      payload.e('성적 정보를 임시저장하지 못하였습니다..');
      Promise.reject(err.response);
    });
  },

  getClassify: ({ commit }, payload) => {
    contact.getClassify('classification', payload.token)
    .then((response) => {
      const { data } = response;
      commit('updateClassify', data);
      payload.s('전형 정보를 불러왔습니다');
    }).catch((err) => {
      payload.e('전형 정보를 불러오는데<br/>실패하였습니다.');
      Promise.reject(err.response);
    });
  },

  getInfo: ({ commit }, payload) => {
    contact.getInfo('info', payload.token)
    .then((response) => {
      const { data } = response;
      commit('updateInfo', data);
      payload.s('인적사항 정보를 불러왔습니다');
    }).catch((err) => {
      payload.e('인적사항 정보를 불러오는데<br/>실패하였습니다.');
      Promise.reject(err.response);
    });
  },

  getMypage: ({ commit }, payload) => {
    contact.getMypage('mypage', payload.token)
    .then((response) => {
      const { data } = response;
      commit('updateMypage', data);
      payload.s('유저 정보를 불러왔습니다');
    }).catch((err) => {
      payload.e('유저 정보를 불러오는데<br/>실패하였습니다.');
      Promise.reject(err.response);
    });
  },
};

export default actions;
