export const PersonInfo = {
  personName: null, // 이름 name으로 바꿔야함
  sex: null, // 성별
  year: null, // 생년 ->
  month: null, // 생월 ->
  day: null, // 생일 -> YYYY-MM-DD 로 보내야함
  studentClass: null, // 학급 반
  studentNumber: null, //  학급 번호
  school: {
    code: null,
    government: null,
    name: null,
    schoolRegion: null,
  }, // 중학교 code, government, name, schoolRegion
  parentName: null, // 보호자명
  schoolTel: null, // 학교 연락처
  parentTel: null, // 보호자 연락처
  myTel: null, // 본인 연락처
  zipCode: null, // 우편번호
  addressBase: null, // 기본주소
  addressDetail: null, // 상세주소
  imgPath: null, // 증명사진
};

export default PersonInfo;
