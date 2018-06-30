<template>
  <div class="Authorization">
    <headline title="2019 지원자 본인인증"/>
    <accept-terms />
    <info-input/>
  </div>
</template>

<script>
import Headline from '../common/Headline';
import AcceptTerms from './AcceptTerms';
import InfoInput from './InfoInput';

export default {
  name: 'Authorization',
  components: {
    Headline,
    AcceptTerms,
    InfoInput,
  },
};
</script>

<style lang="scss" scoped>
@import '../../style/setting.scss';

.Authorization{
  width: 1140px;
  height: 1065px;
  margin: 0 auto;
}
</style>
