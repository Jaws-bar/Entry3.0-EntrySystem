export const mypage = {
  graduateType: '', // WILL, DONE, GED
  validationResult: {
    classificationErrors: [], // 공백란
    infoErrors: [], // 공백란
    gradeErrors: [], // 공백란
    documentErrors: [], // 공백란
    isValid: false, // 전체 검증
    isClassificationValid: false, // 전형구분 검증
    isInfoValid: false, // 인적사항 검증
    isGradeValid: true, // 성적 검증
    isDocumentValid: false, // 자기소개서 검증
  },
  applyStatus: {
    finalSubmit: false, // 최종 제출
    passStatus: false, // 1차 합격여부
    payment: false, // 전형료 납부
    receipt: false, // 우편물 수령
  },
};

export default mypage;
