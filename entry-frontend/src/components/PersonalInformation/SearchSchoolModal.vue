<template>
  <div class="school-modal">
    <selectbox
      class="school-modal__select-office"
      v-model="office"
      :options="offices"></selectbox>
    <input
      type="text"
      class="input-text school-modal__input"
      placeholder="중학교 명"
      v-focus
      v-model="keyword"
      @keyup.enter="getSchools"
      @keydown.esc="$emit('close')">
    <ul class="school-modal__list">
      <li
        class="school-modal__school"
        v-for="school in schools"
        :key="school.code"
        @click="selectSchool(school)">
        <div class="cover">
          {{ school.name }}
        </div>
        <div class="select">선택</div>
      </li>
    </ul>
  </div>
</template>

<script>
import CONSTANT from '../../api/constant';
import Selectbox from '../common/Selectbox';

const focus = {
  inserted(el) {
    el.focus();
  },
};

const offices = [
  { text: '충청북도교육청', value: '충청북도교육청' },
  { text: '충청남도교육청', value: '충청남도교육청' },
  { text: '제주특별자치도교육청', value: '제주특별자치도교육청' },
  { text: '제주도교육청', value: '제주도교육청' },
  { text: '전라북도교육청', value: '전라북도교육청' },
  { text: '전라남도교육청', value: '전라남도교육청' },
  { text: '인천광역시교육청', value: '인천광역시교육청' },
  { text: '울산광역시교육청', value: '울산광역시교육청' },
  { text: '세종특별자치시교육청', value: '세종특별자치시교육청' },
  { text: '서울특별시교육청', value: '서울특별시교육청' },
  { text: '부산광역시교육청', value: '부산광역시교육청' },
  { text: '대전광역시교육청', value: '대전광역시교육청' },
  { text: '대구광역시교육청', value: '대구광역시교육청' },
  { text: '광주광역시교육청', value: '광주광역시교육청' },
  { text: '경상북도교육청', value: '경상북도교육청' },
  { text: '경상남도교육청', value: '경상남도교육청' },
  { text: '경기도교육청', value: '경기도교육청' },
  { text: '강원도교육청', value: '강원도교육청' },
];

export default {
  directives: { focus },
  name: 'school-modal',
  components: {
    Selectbox,
  },
  data() {
    return {
      offices,
      office: offices[0].value,
      keyword: '',
      schools: [],
    };
  },
  methods: {
    search() {
      this.current = 1;
      this.getSchools();
    },
    getSchools() {
      const token = this.$cookies.get('accessToken');
      this.$axios.get(`${CONSTANT.SCHOOL_URI}/search?name=${this.keyword}&government=${this.office}`,
        { headers: { Authorization: `JWT ${token}` } })
        .then(({ data }) => {
          this.schools = data.data;
        })
        .catch(err => Promise.reject(err.response));
    },
    selectSchool(school) {
      this.$emit('selectSchool', school);
      this.$emit('close');
    },
  },
};
</script>

<style lang="scss" scoped>
@import '../../style/setting';
@import '../../style/input';
$color-main1: #769b9f;
$color-main2: #e0eef1;
$color-main3: #5f8a90;
$color-main4: #f7fbfc;
$modal-z-index: 5;

.school-modal {
  $modal-width: 1000px;
  $modal-height: 500px;

  position: fixed;
  top: 50%;
  left: 50%;
  width: $modal-width;
  height: $modal-height;
  transform: translate(-$modal-width / 2, -$modal-height / 2);
  border: 1px solid $color-main1;
  z-index: $modal-z-index;
  background-color: #fff;
  box-sizing: border-box;
  padding: 40px 45px;

  @include e('select-office') {
    width: 200px;
  }
  @include e('input') {
    width: 193px;
    margin-left: 10px;
  }
  @include e('list') {
    width: 100%;
    height: 360px;
    overflow-y: scroll;
    margin-top: 20px;
  }
  .select {
    display: none;
  }
  .cover {
    height: 60px;
    line-height: 60px;
    font-size: 18px;
  }
  @include e('school') {
    border-bottom: 1px solid #f1f5f5;
    height: 60px;
    padding-left: 25px;
    position: relative;
    overflow-x: hidden;
    &:hover {
      background-color: #f4f9fa;
      .select {
        width: 95px;
        height: 60px;
        position: absolute;
        top: 0;
        right: 0;
        color: #bbd2d6;
        font-size: 18px;
        line-height: 60px;
        background-color: #f4f9fa;
        display: block;
        text-align: center;
      }
    }
  }
}

</style>
