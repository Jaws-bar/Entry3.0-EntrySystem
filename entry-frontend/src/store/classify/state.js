export const classify = {
  isGED: false, // 검정고시 인지 아닌지
  admission: 'NORMAL', // 입학 분류 | 졸업예정자, 졸업자, 검정고시
  admissionDetail: {
    text: '',
    value: 'NONE',
  }, // 사회통합전형 분류 기준
  region: true, // 대전인지 전국인지
  isGraduated: false, //
  graduateYear: 2019,
  additionalType: 'NONE',
  graduateType: 'WILL',
};

export default classify;
