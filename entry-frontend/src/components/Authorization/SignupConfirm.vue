<script>
import contact from './../../api/contact';

export default {
  props: [
    'code',
  ],
  created() {
    const { s, e } = this.$toastr;
    contact.confirmSignup(this.code).then(() => {
      s('인증이 완료되었습니다. 로그인을 해주세요.');
      this.$router.push('/');
      this.$store.commit('changeIndex', {
        index: 1,
      });
    }).catch((error) => {
      e(error.response.data.errors[0].message);
      this.$router.push('/');
    });
  },
};
</script>
