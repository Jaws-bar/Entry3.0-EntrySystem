<template>
  <div class="social-option">
    <ul class="social-option__list">
      <li class="social-option__list__item"
        v-for="(option, index) in options"
        :key="index"
        :class="{'social-option__list__item--focused': selected == option.value}"
        @click="select(option)">
        {{ option.text }}
      </li>
    </ul>
</div>
</template>

<script>
export default {
  name: 'social-option',
  props: {
    options: {
      type: Array,
    },
  },
  data() {
    return {
      selected: 0,
    };
  },
  methods: {
    select(option) {
      this.selected = option.value;
      this.$emit('input', option);
      this.$emit('close');
    },
  },
};
</script>

<style lang="scss" scoped>
@import '../../style/setting';

.social-option {
  position: relative;
  width: 0;
  height: 0;
  display: inline-block;
  @include e('list') {
    position: absolute;
    z-index: 2;
    top: 15px;
    left: 5px;
    width: 250px;
    background-color: #ffffff;
    border: solid 1px #7b9fa2;
    @include e('item') {
      width: 250px;
      height: 40px;
      box-sizing: border-box;
      font-size: 16px;
      line-height: 40px;
      position: relative;
      border: {
        bottom: solid 1px #9bb6b9;
      }
      padding: {
        left: 20px;
      }
      &::after {
        font-size: 18px;
        position: absolute;
        right: 20px;
        color: #709ea2;
      }
      &:hover {
        background-color: #d0e5ea;
        &::after {
          content: '〉';
        }
      }
      &:last-child {
        border: none;
      }
      @include m('focused') {
        background-color: #f7fbfc;
        &::after {
          content: '✓';
        }
        &:hover {
          background-color: #f7fbfc;
          &::after {
            content: '✓';
          }
        }
      }
    }
  }
}
</style>
