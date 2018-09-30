export const GradeInput = {
  /* 검정고시 관련 */
  grade: 0,

  /* 졸업 예정, 졸업자 관련 */

  // 봉사 및 출석
  volunteerNAttendance: {
    volunteerTime: 0,
    periodCut: 0,
    fullCut: 0,
    late: 0,
    earlyLeave: 0,
  },

  // 국어
  korean: [
    { score: '', decided: false, passed: false },
    { score: '', decided: false, passed: false },
    { score: '', decided: false, passed: false },
    { score: '', decided: false, passed: false },
    { score: '', decided: false, passed: false },
    { score: '', decided: false, passed: false },
  ],

  // 사회
  social: [
    { score: '', decided: false, passed: false },
    { score: '', decided: false, passed: false },
    { score: '', decided: false, passed: false },
    { score: '', decided: false, passed: false },
    { score: '', decided: false, passed: false },
    { score: '', decided: false, passed: false },
  ],

  // 역사
  history: [
    { score: '', decided: false, passed: false },
    { score: '', decided: false, passed: false },
    { score: '', decided: false, passed: false },
    { score: '', decided: false, passed: false },
    { score: '', decided: false, passed: false },
    { score: '', decided: false, passed: false },
  ],

  // 수학
  math: [
    { score: '', decided: false, passed: false },
    { score: '', decided: false, passed: false },
    { score: '', decided: false, passed: false },
    { score: '', decided: false, passed: false },
    { score: '', decided: false, passed: false },
    { score: '', decided: false, passed: false },
  ],

  // 과학
  science: [
    { score: '', decided: false, passed: false },
    { score: '', decided: false, passed: false },
    { score: '', decided: false, passed: false },
    { score: '', decided: false, passed: false },
    { score: '', decided: false, passed: false },
    { score: '', decided: false, passed: false },
  ],

  // 기술 - 가정
  tech: [
    { score: '', decided: false, passed: false },
    { score: '', decided: false, passed: false },
    { score: '', decided: false, passed: false },
    { score: '', decided: false, passed: false },
    { score: '', decided: false, passed: false },
    { score: '', decided: false, passed: false },
  ],

  // 영어
  english: [
    { score: '', decided: false, passed: false },
    { score: '', decided: false, passed: false },
    { score: '', decided: false, passed: false },
    { score: '', decided: false, passed: false },
    { score: '', decided: false, passed: false },
    { score: '', decided: false, passed: false },
  ],
};

export default GradeInput;
