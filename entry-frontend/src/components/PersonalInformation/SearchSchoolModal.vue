<template>
  <div class="search-school-modal">
    <button class="search-school-modal__close-btn"
      @click="$emit('close')">
      &times;
    </button>

    <input type="text"
      class="search-school-modal__input"
      :placeholder="placeholder"
      v-model="keyword"
      @keydown.enter="getSchools">

    <ul class="search-school-modal__list">
      <li class="
        search-school-modal__list__item
        search-school-modal__list__item--head">
        <span class="search-school-modal__list__item__name">
          학교 이름
        </span>
        <span class="search-school-modal__list__item__region">
          지역
        </span>
      </li>

      <li class="
        search-school-modal__list__item
        search-school-modal__list__item--instance"
        v-for="school in schools"
        :key="school.seq">
        <span class="
          search-school-modal__list__item__name
          search-school-modal__list__item--instance__name"
          @click="selectSchool(school.schoolName, school.region)">
          {{ school.schoolName }}
        </span>
        <span class="search-school-modal__list__item__region">
          {{ school.region }}
        </span>
      </li>
    </ul>

    <div class="search-school-modal__pagination">
      <div class="search-school-modal__pagination__number"
        @click="current > 1 ? prevPage() : ''">이전</div>
      <div class="search-school-modal__pagination__number">
        {{ current }}
      </div>
      <div class="search-school-modal__pagination__number"
        @click="nextPage">다음</div>
    </div>
  </div>
</template>

<script>
// import config from '../../config';
// 오류 때문에 임시적으로 적어놓은 것
const config = {
  appKey: 'Test Key',
};

export default {
  name: 'search-school-modal',
  data() {
    return {
      placeholder: '학교명 입력',
      keyword: '',
      current: 1,
      schools: [],
    };
  },
  methods: {
    search() {
      this.current = 1;
      this.getSchools();
    },
    getSchools() {
      const pageCount = 10; // 한 페이지에 보여질 건수
      const keyword = encodeURI(this.keyword);
      const dataURI = `http://www.career.go.kr/cnet/openapi/getOpenApi?apiKey=${config.appKey}&svcType=api&svcCode=SCHOOL&contentType=json&gubun=midd_list&thisPage=${this.current}&perPage=${pageCount}&searchSchulNm=${keyword}`;
      this.$axios.get(dataURI)
        .then((result) => {
          this.schools = result.data.dataSearch.content;
        });
    },
    selectSchool(name, region) {
      this.$emit('selectSchool', `${name}(${region})`);
      this.$emit('close');
    },
    prevPage() {
      this.current -= 1;
      this.getSchools();
    },
    nextPage() {
      this.current += 1;
      this.getSchools();
    },
  },
};
</script>

<style lang="scss" scoped>
@import '../../style/setting';
$color-main1: #769b9f;
$color-main2: #e0eef1;
$color-main3: #5f8a90;
$color-main4: #f7fbfc;
$modal-z-index: 5;

.search-school-modal {
  $modal-width: 600px;
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
  position: fixed;
  box-sizing: border-box;
  padding: {
    top: 40px;
    left: 10px;
    right: 10px;
  }
  @include e('close-btn') {
    box-sizing: border-box;
    $btn-size: 25px;
    font-size: $btn-size;
    line-height: $btn-size;
    width: $btn-size;
    height: $btn-size;
    border: none;
    background-color: #fff;
    position: absolute;
    top: 5px;
    right: 5px;
    color: $color-main3;
    outline: none;
  }
  @include e('input') {
    display: block;
    width: 100%;
    box-sizing: border-box;
    border: 1px solid $color-main1;
    background-color: $color-main4;
    padding: 10px;
    font-size: 18px;
    line-height: 18px;
    outline: none;
  }
  @include e('list') {
    border: 1px solid $color-main2;
    height: $modal-height - 150px;
    margin-top: 10px;
    overflow-y: scroll;
    @include e('item') {
      box-sizing: border-box;
      padding-left: 10px;
      border-bottom: 1px solid $color-main2;
      position: relative;
      @include m('head') {
        background-color: $color-main4;
        color: $color-main3;
        height: 30px;
        line-height: 30px;
      }
      @include m('instance') {
        height: 50px;
        line-height: 50px;
        @include e('name') {
          cursor: pointer;
          &:hover {
            color: $color-main3;
            border-bottom: 1px solid $color-main3;
          }
        }
      }
      @include e('name') {
        font-size: 18px;
      }
      @include e('region') {
        font-size: 15px;
        position: absolute;
        right: 0;
        padding-right: 10px
      }
    }
  }
  @include e('pagination') {
    width: 100%;
    display: flex;
    justify-content: center;
    margin-top: 20px;
    @include e('number') {
      height: 20px;
      font-size: 18px;
      line-height: 20px;
      width: 40px;
      cursor: pointer;
      text-align: center;
    }
  }
}

</style>
