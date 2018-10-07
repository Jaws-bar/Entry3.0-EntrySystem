const utilsOfGrade = {
  // 보낼 성적 값 필터링
  sendGrade: (grade, grades, graduateType, volunteerNAttendance) => {
    const gradeArr = [];
    const typeCount = graduateType === 'DONE' ? 6 : 5;
    const semesters = [
      'korean',
      'social',
      'history',
      'math',
      'science',
      'tech',
      'english',
    ];
    let gradeObj = {};

    for (let type = 0; type < typeCount; type += 1) {
      for (let idx = 0; idx < grades.length; idx += 1) {
        const score = grades[idx][type].score;

        Object.assign(gradeObj, {
          [semesters[idx]]: score === '' ? null : score,
        });
      }

      gradeArr.push(gradeObj);
      gradeObj = {};
    }

    const sendForm = Object.assign(volunteerNAttendance, {
      grades: gradeArr,
      grade,
    });

    return sendForm;
  },

  getGrade: (responseGrade) => {
    // 필터링 함수
    const filteredGrade = (grades, subject) => {
      const setSubject = subject;
      const sub = grades.map(grade => Object.assign({}, {
        score: grade[setSubject] === null ? '' : grade[setSubject],
        passed: (grade[setSubject] !== null && grade[setSubject] !== 'X'),
        decided: grade[setSubject] !== null,
      }));

      return sub;
    };

    const {
      grades,
      grade,
      earlyLeave,
      fullCut,
      late,
      periodCut,
      volunteerTime,
    } = responseGrade;
    const totalGrades = [];
    const subjects = Object.keys(grades[0]);

    for (let sub = 1; sub < subjects.length; sub += 1) {
      totalGrades.push(filteredGrade(grades, subjects[sub]));
    }

    const filteredObject = Object.assign({}, {
      grades: totalGrades,
      grade,
      earlyLeave,
      fullCut,
      late,
      periodCut,
      volunteerTime,
    });

    return filteredObject;
  },
};

export default utilsOfGrade;
