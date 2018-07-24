<template>
  <div class="brochure">
    <navigation />
    <headline :title="title" :subText="subText" />
    <embed class="brochure__pdf"
      type='application/pdf'
      :src="`${selectionInfoSrc}#page=${pageNumber}&zoom=${zoom}`"
      :height="height">
    <entry-footer />
  </div>
</template>

<script>
import Navigation from '../common/Navigation';
import Headline from '../common/Headline';
import EntryFooter from '../common/EntryFooter';

export default {
  name: 'brochure',
  components: {
    Navigation,
    Headline,
    EntryFooter,
  },
  data() {
    return {
      title: '브로슈어',
      subText: '대덕소프트웨어마이스터고등학교',
      selectionInfoSrc: '',
      pageNumber: 1,
      zoom: 80,
      height: 1055,
    };
  },
};
</script>

<style lang="scss" scoped>
@import '../../style/setting';

.brochure {
  @include e('pdf') {
    width: 1140px;
    margin: {
      left: auto;
      right: auto;
      bottom: 125px;
    };
    display: block;
  }
}
</style>
