import { modal } from './modal/state';
import { auth } from './auth/state';
import { classify } from './classify/state';
import { PersonInfo } from './PersonInfo/state';
import { GradeInput as gradeInput } from './GradeInput/state';
import { IntroNPlan as introNPlan } from './IntroNPlan/state';
import { mypage } from './mypage/mypage';

export const state = {
  modal,
  auth,
  classify,
  PersonInfo,
  gradeInput,
  introNPlan,
  mypage,
  accessToken: null,
};

export default state;
