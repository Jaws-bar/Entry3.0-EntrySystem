export const getters = {
  selectType(state) {
    // const { graduateType } = state.classify;
    const {
      grade,
      volunteerNAttendance,
      korean,
      social,
      history,
      math,
      science,
      tech,
      english,
    } = state.gradeInput;

    // 경우: 검정고시, 졸업, 졸업 예정
    return {
      grade,
      volunteerNAttendance,
      korean,
      social,
      history,
      math,
      science,
      tech,
      english,
      grades: [
        korean,
        social,
        history,
        math,
        science,
        tech,
        english,
      ],
    };
  },
};

export default getters;
