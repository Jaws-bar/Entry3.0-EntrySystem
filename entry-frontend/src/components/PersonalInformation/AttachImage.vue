<template>
  <div class="attach-image">
    <input type="file"
      id="image"
      style="display: none"
      @change="onFileChange">
    <label class="attach-image__label"
      for="image">
      <div class="attach-image__label__preview"
        :style="{backgroundImage: `url(${this.imgPath})`}">
      </div>
    </label>
  </div>
</template>

<script>
export default {
  name: 'attach-image',
  computed: {
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
  methods: {
    onFileChange(e) {
      const file = e.target.files[0];
      this.imageURL = URL.createObjectURL(file);
      this.$emit('upload', this.imageURL);
    },
  },
};
</script>

<style lang="scss" scoped>
@import '../../style/setting';

.attach-image {
  width: 315px;
  height: 375px;
  background-color: #fff;
  box-sizing: border-box;
  border: {
    left: 1px solid #eff3f4;
    bottom: 1px solid #eff3f4;
  }
  padding: {
    left: 17px;
    right: 5px;
    top: 20px;
    bottom: 15px;
  }
  @include e('label') {
    cursor: pointer;
    width: 100%;
    height: 100%;
    display: block;
    box-sizing: border-box;
    border: solid 0.5px #8aaaad;
    background-color: #f7fbfc;
    background-image: url('../../assets/PersonalInformation/attach_image.png');
    background-repeat: no-repeat;
    background-position: center center;
    @include e('preview') {
      width: 100%;
      height: 100%;
      background-repeat: no-repeat;
      background-position: center center;
      background-size: cover;
    }
  }
}

</style>
