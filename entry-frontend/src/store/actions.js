export const actions = {
  // auth 관련 action
  updateAccept: context => context.commit('updateAccept'),
  updateEmail: context => context.commit('updateEmail'),
  updatePw: context => context.commit('updateEmail'),
  updatePwCheck: context => context.commit('updateEmail'),
  updateVerifi: context => context.commit('updateVerifi'),
};

export default actions;
