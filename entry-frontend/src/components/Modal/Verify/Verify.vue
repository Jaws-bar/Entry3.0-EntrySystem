<template>
  <div class="Verify">
    <div class="Verify__Icon">
      <div class="Verify__Icon__box Verify__Icon__box--info"></div>
      <div class="Verify__Icon__box Verify__Icon__box--classify"></div>
      <div class="Verify__Icon__box Verify__Icon__box--plan"></div>
      <div class="Verify__Icon__box Verify__Icon__box--gradeInput"></div>
    </div>
  </div>
</template>

<script>
export default {

};
</script>

<style lang="scss" scoped>
@import '../../../style/setting';
.Verify{
  @include e('Icon'){
    width: 240px;
    height: 240px;
    transform: rotate(-45deg);
    position: relative;
    @include e('box'){
      display: inline-block;
      width: 100px;
      height: 100px;
      background-color: #ffffff;
      border: solid 1px #96b2b5;
      float: left;
      margin: 9px;
      transition: 0.5s;
      position: absolute;
      display: flex;
      justify-content: center;
      align-items: center;
      &:hover{
        position: absolute;
        width: 220px;
        height: 220px;
        z-index: 10;
        &::before{
          opacity: 1;
          transition: 1s;
        }
      }
      &::before{
        font-family: NanumSquareR;
        font-size: 24px;
        color: #356a71;
        transform: rotate(45deg);
        opacity: 0;
        display: block;
        text-align: center;
        width: 125px;
        padding-bottom: 10px;
        border-bottom: solid 1px #356a71;
        position: relative;
        top: -20px;
        left: 20px;
      }
      @include m('info'){
        background-color: #e8f3f6;
        top: 0;
        left: 0;
        &::before{
          content: '인적사항';
        }
      }
      @include m('classify'){
        background-color: #d3ebf1;
        top:0;
        right: 0;
        &::before{
          content: '전형구분'
        }
      }
      @include m('plan'){
        background-color: #abd7e2;
        bottom:0;
        left: 0;
        &::before{
          content: '자기소개서'
        }
      }
      @include m('gradeInput'){
        background-color: #c0e2eb;
        bottom:0;
        right: 0;
        &::before{
          content: '성적입력'
        }
      }
    }
  }
}
</style>

