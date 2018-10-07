<template>
  <div class="personal-information form">
    <!-- 학교 검색 모달 -->
    <search-school-modal
      v-if="isOpen"
      @close="isOpen = false"
      @selectSchool="schoolObj => this.school = schoolObj"/>

    <navigation />
    <headline :title="title" :subText="subText" />
    <div class="form__cover">

      <!-- form -->
      <div class="form__cover__form">

        <!-- 이미지 첨부 컴포넌트 -->
        <attach-image class="form__cover__form__attach-image"
          @upload="path => this.imgPath = path"/>
        <div class="form__cover__form__colums">
          <div class="form__cover__form__colums__name">
            이름
          </div>
          <div class="form__cover__form__colums__input-content">
            <input type="text"
              class="input-text input-text-name"
              :value="personName"
              @input="personName = $event.target.value">
          </div>
        </div>
        <div class="form__cover__form__colums">
          <div class="form__cover__form__colums__name">
            성별
          </div>
          <div class="form__cover__form__colums__input-content">
            <input type="radio"
              class="input-radio"
              id="FEMALE"
              value="FEMALE"
              @click="isOpen = false"
              v-model="sex">
            <label class="input-radio-label" for="FEMALE">
              <span class="input-radio-span"></span>
            </label>
            <label class="form__cover__form__colums__input-content__label"
              for="FEMALE">
              여자
            </label>

            <input type="radio"
              class="input-radio"
              id="MALE"
              value="MALE"
              @click="isOpen = false"
              v-model="sex">
            <label class="input-radio-label" for="MALE">
              <span class="input-radio-span"></span>
            </label>
            <label class="form__cover__form__colums__input-content__label"
              for="MALE">
              남자
            </label>
          </div>
        </div>
        <div class="form__cover__form__colums">
          <div class="form__cover__form__colums__name">
            생년월일
          </div>
          <div class="form__cover__form__colums__input-content">
            <selectbox class="selectbox-year"
              v-model="year"
              :options="yearOptions"/>
            <label class="title--input">
              년
            </label>
            <selectbox class="selectbox-month"
              v-model="month"
              :options="monthOptions"/>
            <label class="title--input">
              월
            </label>
            <selectbox class="selectbox-day"
              v-model="day"
              :options="dayOptions"/>
            <label class="title--input">
              일
            </label>
          </div>
        </div>
        <div class="form__cover__form__colums" v-if="!isGED">
          <div class="form__cover__form__colums__name">
            학번
          </div>
          <div class="form__cover__form__colums__input-content">
            <input type="text"
              class="input-text input-text-school-grade"
              value="3"
              disabled>
            <label class="title--input">
              학년
            </label>
            <input type="text"
              class="input-text input-text-school-class"
              v-model="studentClass"
              @keydown="onlyNumber">
            <label class="title--input">
              반
            </label>
            <input type="text"
              class="input-text input-text-school-number"
              v-model="studentNumber"
              @keydown="onlyNumber">
            <label class="title--input">
              번
            </label>
          </div>
        </div>
        <div class="form__cover__form__colums" v-if="!isGED">
          <div class="form__cover__form__colums__name">
            중학교명
          </div>
          <div class="form__cover__form__colums__input-content">
            <input class="input-text input-text-school-name"
              :value="school ? school.name : ''"
              disabled>
            <button class="button button-search" @click="isOpen = true">
              검색
            </button>
          </div>
        </div>
        <div class="form__cover__form__colums">
          <div class="form__cover__form__colums__name">
            보호자명
          </div>
          <div class="form__cover__form__colums__input-content">
            <input type="text"
              class="input-text input-text-guardian-name"
              :value="parentName"
              @input="parentName = $event.target.value">
          </div>
        </div>
        <div class="form__cover__form__colums" v-if="!isGED">
          <div class="form__cover__form__colums__name">
            학교 연락처
          </div>
          <div class="form__cover__form__colums__input-content">
            <input type="text"
              class="input-text input-text-school-contact"
              v-model="schoolTel"
              @keydown="onlyNumber"
              maxLength="11">
            <span class="form__cover__form__colums__input-content__sign">
              * ‘-’ 문자를 제외한 숫자만 입력해주세요.
            </span>
          </div>
        </div>
        <div class="form__cover__form__colums">
          <div class="form__cover__form__colums__name">
            보호자 연락처
          </div>
          <div class="form__cover__form__colums__input-content">
            <input type="text"
              class="input-text input-text-guardian-contact"
              v-model="parentTel"
              @keydown="onlyNumber"
              maxLength="11">
            <span class="form__cover__form__colums__input-content__sign">
              * ‘-’ 문자를 제외한 숫자만 입력해주세요.
            </span>
          </div>
        </div>
        <div class="form__cover__form__colums">
          <div class="form__cover__form__colums__name">
            본인 연락처
          </div>
          <div class="form__cover__form__colums__input-content">
            <input type="text"
              class="input-text input-text-contact"
              v-model="myTel"
              @keydown="onlyNumber"
              maxLength="11">
            <span class="form__cover__form__colums__input-content__sign">
              * ‘-’ 문자를 제외한 숫자만 입력해주세요.
            </span>
          </div>
        </div>
        <div class="form__cover__form__colums">
          <div class="form__cover__form__colums__name">
            주소
          </div>
          <div class="form__cover__form__colums__input-content">
            <input type="text"
              disabled
              class="input-text input-text-zip"
              placeholder="우편번호"
              :value="zipCode">
            <input type="text"
              disabled
              class="input-text input-text-address"
              placeholder="기본주소"
              :value="addressBase">
            <button class="button button-search-address" @click="openSearchAdress">
              주소 검색
            </button>
            <br>
            <input type="text"
              class="input-text input-text-detailed-address"
              placeholder="상세주소"
              :value="addressDetail"
              @input="addressDetail = $event.target.value">
          </div>
        </div>
      </div>
      <!-- form end -->

      <prev-next-btn
        :prevShow="true"
        :nextShow="true"
        :prevLink="prevLink"
        :nextLink="nextLink"
        :onClick="() => sendServer()"/>
    </div>
    <entry-footer />
  </div>
</template>

<script>
import Navigation from '../common/Navigation';
import Headline from '../common/Headline';
import PrevNextBtn from '../common/PrevNextBtn';
import EntryFooter from '../common/EntryFooter';
import Selectbox from '../common/Selectbox';
import AttachImage from './AttachImage';
import SearchSchoolModal from './SearchSchoolModal';

export default {
  name: 'personalInformation',
  components: {
    Navigation,
    Headline,
    PrevNextBtn,
    EntryFooter,
    Selectbox,
    AttachImage,
    SearchSchoolModal,
  },
  data() {
    return {
      isOpen: false, // 학교 검색 모달창 열림 여부
      title: '인적 사항',
      subText: '2019 입학원서 작성',
      yearOptions: [],
      monthOptions: [],
      prevLink: '/classify',
      nextLink: '/grade',
    };
  },
  computed: {
    // 생년월일에서 '월' 선택에 따라 '일' 옵션 변경
    // 30일, 31일인지를 파악하고, 윤년을 계산한다
    dayOptions() {
      let count;
      const year = this.year;
      // 윤년 계산
      const isLeapYear = (year % 4 === 0 && year % 100 !== 0) || year % 400 === 0;

      switch (this.month) {
        case 2: count = isLeapYear ? 29 : 28; break;
        case 4:
        case 6:
        case 9:
        case 11: count = 30; break;
        default: count = 31; break;
      }

      const dayArray = [];
      for (let i = 1; i <= count; i += 1) {
        const pad = i >= 10 ? '' : '0';
        const day = pad + i;

        dayArray[i - 1] = {
          text: day,
          value: day,
        };
      }
      return dayArray;
    },
    isGED: {
      get() {
        return this.$store.state.classify.isGED;
      },
    },
    personName: {
      get() {
        return this.$store.state.PersonInfo.personName;
      },
      set(data) {
        this.$store.commit('updatePersonName', {
          data,
        });
      },
    },
    sex: {
      get() {
        return this.$store.state.PersonInfo.sex;
      },
      set(data) {
        this.$store.commit('updateSex', {
          data,
        });
      },
    },
    year: {
      get() {
        return this.$store.state.PersonInfo.year;
      },
      set(data) {
        this.$store.commit('updateYear', {
          data,
        });
      },
    },
    month: {
      get() {
        return this.$store.state.PersonInfo.month;
      },
      set(data) {
        this.$store.commit('updateMonth', {
          data,
        });
      },
    },
    day: {
      get() {
        return this.$store.state.PersonInfo.day;
      },
      set(data) {
        this.$store.commit('updateDay', {
          data,
        });
      },
    },
    studentClass: {
      get() {
        return this.$store.state.PersonInfo.studentClass;
      },
      set(data) {
        this.$store.commit('updateStudentClass', {
          data,
        });
      },
    },
    studentNumber: {
      get() {
        return this.$store.state.PersonInfo.studentNumber;
      },
      set(data) {
        this.$store.commit('updateStudentNumber', {
          data,
        });
      },
    },
    school: {
      get() {
        return this.$store.state.PersonInfo.school;
      },
      set(data) {
        this.$store.commit('updateSchool', {
          data,
        });
      },
    },
    parentName: {
      get() {
        return this.$store.state.PersonInfo.parentName;
      },
      set(data) {
        this.$store.commit('updateParentName', {
          data,
        });
      },
    },
    schoolTel: {
      get() {
        return this.$store.state.PersonInfo.schoolTel;
      },
      set(data) {
        this.$store.commit('updateSchoolTel', {
          data,
        });
      },
    },
    parentTel: {
      get() {
        return this.$store.state.PersonInfo.parentTel;
      },
      set(data) {
        this.$store.commit('updateParentTel', {
          data,
        });
      },
    },
    myTel: {
      get() {
        return this.$store.state.PersonInfo.myTel;
      },
      set(data) {
        this.$store.commit('updateMyTel', {
          data,
        });
      },
    },
    zipCode: {
      get() {
        return this.$store.state.PersonInfo.zipCode;
      },
      set(data) {
        this.$store.commit('updateZipCode', {
          data,
        });
      },
    },
    addressBase: {
      get() {
        return this.$store.state.PersonInfo.addressBase;
      },
      set(data) {
        this.$store.commit('updateAddressBase', {
          data,
        });
      },
    },
    addressDetail: {
      get() {
        return this.$store.state.PersonInfo.addressDetail;
      },
      set(data) {
        this.$store.commit('updateAddressDetail', {
          data,
        });
      },
    },
    imgPath: {
      get() {
        return this.$store.state.PersonInfo.imgPath;
      },
      set(data) {
        this.$store.commit('updateImgPath', {
          data,
        });
      },
    },
  },
  created() {
    // '년'옵션 생성
    const lastYear = 2005;
    const count = 20; // '년'옵션의 개수
    const yearArray = [];
    for (let i = 0; i < count; i += 1) {
      const year = `${lastYear - i}`;
      yearArray[i] = {
        text: year,
        value: year,
      };
    }
    this.yearOptions = yearArray;

    // '달'옵션 생성
    const monthArray = [];
    for (let i = 1; i <= 12; i += 1) {
      const pad = i >= 10 ? '' : '0';
      monthArray[i - 1] = {
        text: pad + i,
        value: pad + i,
      };
    }
    this.monthOptions = monthArray;
    // 로그인 안하면 메인으로 내쫓기
    const token = this.$cookies.get('accessToken');
    const { e } = this.$toastr;
    if (token === undefined || token === null || token === '') {
      this.$router.push('/');
      e('해당 페이지는 로그인이 필요합니다.');
      this.$store.commit('changeIndex', {
        index: 1,
      });
    }
    if (this.$store.state.mypage.applyStatus.finalSubmit) {
      e('최종 제출 후에는 접근 할 수 없습니다.');
      this.$router.push('/');
    }
  },
  methods: {
    // 숫자, 백스페이스가 아닐 경우 이벤트 막기
    onlyNumber(e) {
      if (!(e.keyCode >= 48 && e.keyCode <= 57) || (e.keyCode >= 96 && e.keyCode <= 105)) {
        switch (e.key) {
          case 'Backspace':
          case 'ArrowLeft':
          case 'ArrowRight':
          case 'Delete': break;
          default: e.preventDefault();
        }
      }
    },
    openSearchAdress() {
      const vueObject = this;
      new window.daum.Postcode({
        oncomplete(data) {
          let fullRoadAddr = data.roadAddress; // 도로명 주소 변수
          let extraRoadAddr = ''; // 도로명 조합형 주소 변수

          // 법정동명이 있을 경우 추가한다. (법정리는 제외)
          // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
          if (data.bname && /[동|로|가]$/g.test(data.bname)) {
            extraRoadAddr += data.bname;
          }
          // 건물명이 있고, 공동주택일 경우 추가한다.
          if (data.buildingName && data.apartment === 'Y') {
            extraRoadAddr += (extraRoadAddr !== '' ? `, ${data.buildingName}` : data.buildingName);
          }
          // 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
          extraRoadAddr = extraRoadAddr ? ` (${extraRoadAddr})` : '';
          // 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
          fullRoadAddr += fullRoadAddr ? extraRoadAddr : '';

          // 우편번호와 주소 정보를 해당 필드에 넣는다.
          vueObject.zipCode = data.zonecode; // 5자리 새 우편번호 사용
          vueObject.addressBase = fullRoadAddr;
        },
      }).open();
    },
    sendServer() {
      const token = this.$cookies.get('accessToken');
      const data = { ...this.$store.state.PersonInfo };
      const { s, w, e } = this.$toastr;
      data.name = data.personName;
      data.birth = `${data.year}-${data.month}-${data.day}`;
      data.schoolCode = this.school.code;
      this.$axios({
        method: 'put',
        url: 'https://entry.entrydsm.hs.kr:80/api/me/info',
        headers: { Authorization: `JWT ${token}` },
        data,
      }).then((res) => {
        if (res.status === 200) {
          s('인적 사항이 임시저장 되었습니다.');
        }
      }).catch((error) => {
        if (error.response.status === 401) {
          e('로그인이 반드시 필요합니다.');
          this.$router.push('/');
          this.$store.commit('changeIndex', {
            index: 1,
          });
        } else {
          e('인적사항 임시저장을 실패하였습니다.');
          error.response.data.errors.map(((msg) => {
            let field;
            let message;
            switch (msg.field) {
              case 'myTel':
                field = '본인 연락처';
                break;
              case 'parentName':
                field = '보호자명';
                break;
              case 'zipCode':
                field = '우편번호';
                break;
              case 'name':
                field = '이름';
                break;
              case 'studentNumber':
                field = '학번';
                break;
              case 'studentClass':
                field = '반';
                break;
              case 'parentTel':
                field = '보호자 연락처';
                break;
              case 'schoolTel':
                field = '학교 연락처';
                break;
              default:
                field = msg.field;
            }
            switch (msg.message) {
              case 'must not be null':
                message = '값을 비워둘 수 없습니다.';
                break;
              default:
                message = '잘못된 값입니다.';
                break;
            }
            return w(`${field}-${message}`);
          }));
        }
      });
    },
  },
};
</script>

<style lang="scss" scoped>
@import '../../style/setting';

.button {
  height: 40px;
  border-radius: 5px;
  background-color: #f7fbfc;
  border: solid 1px #5f8a90;
  font-size: 18px;
  color: #296169;
  margin-left: 15px;
  outline: none;
  transition: 0.5s;
  &:hover{
    background-color: #d5edf0;
  }
  &:active {
    background-color: #D8E6E9;
  }
  &-search {
    width: 75px;
  }
  &-search-address {
    width: 125px;
  }
}

.input-text {
  &-name, &-guardian-name, &-zip {
    width: 150px;
  }
  &-school-name, &-school-contact, &-guardian-contact, &-contact {
    width: 250px;
  }
  &-address {
    width: 275px;
    margin: {
      left: 15px;
    }
  }
  &-detailed-address {
    width: 580px;
    margin-top: 20px;
  }
  &-school {
    &-grade, &-class, &-number {
      width: 50px;
      text-align: center;
      padding: 0;
    }
  }
}

.title {
  @include m('input') {
    font-size: 18px;
    width: fit-content;
    margin: {
      left: 10px;
      right: 35px;
    }
  }
}

.selectbox {
  &-year {
    width: 125px;
  }
  &-month, &-day {
    width: 75px;
  }
}

.form {
  @include e('cover') {
    @include e('form') {
      @include e('attach-image') {
        position: absolute;
        top: 0;
        right: 0;
        z-index: 1;
      }
    }
  }
}
</style>
