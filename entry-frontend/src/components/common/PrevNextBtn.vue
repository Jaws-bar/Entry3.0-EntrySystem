<template>
  <div class="prev-next-btn-cover">
    <a
      type="button"
      class="input-btn input-btn--prev"
      v-if="prevShow"
      @click="gotoPrev">
      <span class="input-btn__arrow input-btn__arrow--left">〈</span>
      <span class="input-btn__text input-btn__text--prev">이전</span>
    </a>
    <a
      type="button"
      class="input-btn input-btn--next"
      v-if="nextShow"
      @click="gotoNext">
      <span class="input-btn__arrow input-btn__arrow--right">〉</span>
      <span class="input-btn__text input-btn__text--next">{{ text }}</span>
    </a>
  </div>
</template>

<script>
export default {
  name: 'prev-next-btn',
  props: {
    prevShow: {
      type: Boolean,
      required: true,
    },
    nextShow: {
      type: Boolean,
      required: true,
    },
    text: {
      type: String,
      default: '다음',
    },
    prevLink: {
      type: String,
    },
    nextLink: {
      type: String,
      required: false,
    },
    onClick: {
      type: Function,
      required: false,
    },
    SavingFunction: {
      type: Function,
      required: false,
    },
    disablePrevClick: {
      type: Boolean,
      required: false,
    },
  },
  methods: {
    gotoPrev() {
      if (typeof this.onClick === 'function' && this.disablePrevClick !== true && typeof this.SavingFunction !== 'function') {
        this.onClick();
      } else if (typeof this.SavingFunction === 'function') {
        this.SavingFunction();
      }
      this.$router.push(this.prevLink);
    },
    gotoNext() {
      if (typeof this.onClick === 'function') {
        this.onClick();
      }
      this.$router.push(this.nextLink);
    },
  },
};
</script>

<style lang="scss" scoped>
@import '../../style/setting';
$inner-btn: #296169;

// 전체 감싸는 부분
.prev-next-btn-cover {
  width: 1140px;
  height: 25vh;
  margin: 0 auto;
  position: relative;

  // 버튼 2개
  .input-btn {
    position: absolute;
    top: 25px;
    width: 150px;
    height: 50px;
    line-height: 50px;
    border-radius: 5px;
    background-color: #f7fbfc;
    border: 1px solid #5f8a90;
    outline: none;
    cursor: pointer;
    transition: 0.5s;
    &:active {
      background-color: #D8E6E9;
    }
    // 왼쪽 버튼
    @include m('prev') {
      left: 0;
    }

    // 오른쪽 버튼
    @include m('next') {
      right: 0;
    }

    // 버튼 내부 화살표 및 세부 설정
    @include e('arrow') {
      font-size: 19px;
      color: $inner-btn;
      @include m('left') {
        float: left;
        margin-left: 13px;
      }
      @include m('right') {
        float: right;
        margin-right: 13px;
      }
    }

    // 버튼 내부 텍스트 및 세부 설정
    @include e('text') {
      font-size: 18px;
      color: $inner-btn;
      @include m('prev') {
        float: right;
        margin: {
          top: 2px;
          right: 14px;
        }
      }
      @include m('next') {
        float: left;
        margin: {
          top: 2px;
          left: 14px;
        };
      }
    }
  }
}
a[type="button"]{
  &:hover{
    background-color: #d5edf0;
  }
}
</style>
