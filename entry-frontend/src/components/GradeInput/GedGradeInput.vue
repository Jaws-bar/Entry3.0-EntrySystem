<template>
  <div class="grade-input-cover">
    <h3>검정고시 평균</h3>
    <div class="total-score-cover">
      <div class="total-text-part">
        평균
      </div>
      <div class="total-input-part">
        <input type="number"
                v-model.number="grade"
                @keydown="onlyNumber"
                min="0"
                max="100"
                class="total-input-part--score"
        >
        <span class="total-input-part--text">점</span>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'ged-grade-input',
  methods: {
    onlyNumber(e) {
      if (!(e.keyCode >= 48 && e.keyCode <= 57)) {
        switch (e.key) {
          case 'Backspace':
          case 'ArrowLeft':
          case 'ArrowRight':
          case 'Delete': break;
          default: e.preventDefault();
        }
      }
    },
  },
  computed: {
    grade: {
      get() {
        return this.$store.getters.selectType.grade;
      },
      set(val) {
        this.$store.commit('updateGedScore', val);
      },
    },
  },
  watch: {
    grade(val) {
      if (val > 100) this.grade = 100;
    },
  },
};
</script>

<style lang="scss" scoped>
@import '../../style/setting';

.grade-input-cover {
  width: 1140px;
  margin: 0 auto;
  position: relative;

  h3 {
    font-size: 22px;
    font-weight: 400;
    margin-top: 50px;

    &:first-of-type {
      margin-top: 0;
    }
  }

  .total-score-cover {
    height: 75px;
    margin-top: 20px;
    position: relative;

    &::before, &::after {
      content: '';
      display: block;
      width: 112%;
      height: 1px;
      position: absolute;
      left: 50%;
      transform: translateX(-50%);
      background: linear-gradient(left, transparent 0%,
      #769b9f 15%, #769b9f 85%, transparent 100%);
      z-index: 99;
    }

    &::before { top: 0; }
    &::after { bottom: 0; }
  }

  .total-text-part {
    width: 148px;
    height: 100%;
    float: left;
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: 20px;
    border-right: 1px solid #f2f2f2;
    box-sizing: border-box;
  }

  .total-input-part {
    width: calc(100% - 148px);
    height: 100%;
    float: right;
    display: flex;
    align-items: center;

    @include m('score') {
      margin-left: 28px;
      width: 75px;
      height: 40px;
      border-radius: 5px;
      border: 1px solid #5f8a90;
      font-size: 18px;
      display: flex;
      text-align: center;
      box-sizing: border-box;
      outline: none;
      padding: {
        top: 3px;
        left: 15px;
      }
    }

    @include m('text') {
      font-size: 18px;
      margin-left: 11px;
    }
  }
}
</style>
