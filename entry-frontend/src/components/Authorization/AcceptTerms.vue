<template>
  <div class="AcceptTerms">
    <div class="AcceptTerms__box">
      <div class="AcceptTerms__box__wapper">
        <div class="AcceptTerms__box__wapper__contents">
          본 입학원서에 기재된 지원자의 개인정보는 신입생 입학관리업무의 원활한 수행을 위하여 개인정보의
          수집‧유출‧오용‧남용으로부터 사생활의 비밀
          등을 보호하도록 한 개인정보보호법 규정에 따라 다음과 같이 수집‧이용‧제공됩니다.<br /><br />
          <span class="AcceptTerms__box__wapper__contents--title">1. (개인정보 처리의 법령상 근거)</span><br />
          본 입학원서에 기재된 개인정보의 처리업무는 초‧중등교육법 제47조 및 동법 시행령 제81조,
          제82조, 제84조, 제98조 및 본교의 입학전형 실시계획 등에 근거하고 있습니다.<br /><br />
          <span class="AcceptTerms__box__wapper__contents--title">2. (정보주체의 권리)</span><br />
          지원자는 자신이 제공한 개인정보에 대하여 개인정보 보호법 제4조 및 제35조부터 제38조까지에
          따라 열람‧처리‧정지‧정정‧삭제‧파기 등을 요구할 수 있으며, 개인정보 보호법을
          위반한 행위로 인한 손해 발생시에는 개인정보 보호법 제39조에 따라 손해배상을 청
          구할 수 있습니다.<br /><br />
          <span class="AcceptTerms__box__wapper__contents--title">3. (개인정보 수집항목)</span><br />
          입학관리 업무의 원활한 수행을 위하여 수집하는 개인정보는 성명, 생년월일, 증명사진,
          주소, 전화번호, 학력, 출결사항‧교과성적 등입니다.<br /><br />
          <span class="AcceptTerms__box__wapper__contents--title">4. (개인정보의 수집‧이용 목적)</span><br />
          수집한 지원자의 개인정보는 원서접수, 지원자격‧지원결격 사유 확인, 지원자 본인 확인,
          성적산출, 합격자 명부 관리, 합격증명서 발급, 성적 통지, 통계자료 산출 등 입학관리
          업무를 위한 정보로 이용 됩니다.<br /><br />
          <span class="AcceptTerms__box__wapper__contents--title">5. (개인정보 제공)</span><br />
          수집한 개인정보는 지원자격‧지원결격 사유 조회 및 교과성적 확인 등을 위하여 지원자가
          졸업한 중학교 등 관련된 기관에 제공될 수 있습니다.<br /><br />
          <span class="AcceptTerms__box__wapper__contents--title">
            6. (개인정보의 보유기간 및 이용기간)
            </span><br />
          수집한 개인정보는 입학관리 업무를 계속하는 동안 보유‧이용할 수 있으며, 입학관리 업무
          완료 후 본인의 삭제요청이 있을 경우에는 모두 삭제됩니다.<br /><br />
          <span class="AcceptTerms__box__wapper__contents--title">
            7. (개인정보의 수집‧이용‧제공에 대한 동의 거부)
          </span>
          <br />
          지원자는 개인정보의 수집‧이용‧제공에 대한 동의를 거부할 수 있으며, 동의를 거부할 경우
          지원 결격 사유 조회 등 입학관리 업무를 수행할 수 없으므로 원서를 접수할 수 없습니다.<br />
        </div>
      </div>
    </div>
    <div class="AcceptTerms__checkbox">
      <input type="checkbox" class="AcceptTerms__checkbox--input" id="Accept-checkbox"
      @click="sendAccept"/>
      <label class="AcceptTerms__checkbox--label" for="Accept-checkbox"></label>
      개인정보 이용약관에 동의합니다
    </div>
  </div>
</template>

<script>
export default {
  methods: {
    sendAccept() {
      this.$store.commit('updateAccept', !this.$store.state.auth.isAccept);
    },
  },
};
</script>

<style lang="scss" scoped>
@import '../../style/setting.scss';

  .AcceptTerms{
    height: 369px;
    @include e(box){
      position: relative;
      height: 300px;
      background: #eff7f8;
      background: -webkit-linear-gradient(left, transparent 0%, #eff7f8 50%, transparent 100%);
      background: -moz-linear-gradient(left, transparent 0%, #eff7f8 50%, transparent 100%);
      background: -ms-linear-gradient(left, transparent 0%, #eff7f8 50%, transparent 100%);
      background: -o-linear-gradient(left, transparent 0%, #eff7f8 50%, transparent 100%);
      background: linear-gradient(left, transparent 0%, #eff7f8 50%, transparent 100%);
      &::before, &::after{
        content: '';
        display: block;
        width: 100%;
        height: 1px;
        position: absolute;
        background: -webkit-linear-gradient(left, transparent 0%, #5f8a90 50%, transparent 100%);
        background: -moz-linear-gradient(left, transparent 0%, #5f8a90 50%, transparent 100%);
        background: -ms-linear-gradient(left, transparent 0%, #5f8a90 50%, transparent 100%);
        background: -o-linear-gradient(left, transparent 0%, #5f8a90 50%, transparent 100%);
        background: linear-gradient(left, transparent 0%, #5f8a90 50%, transparent 100%);
      }
      &::after{
        bottom: 0;
      }
      @include e(wapper){
        position: relative;
        top: 20px;
        padding: 21px 18px 19px 22px;
        background-color: #FFF;
        @include e(contents){
          width: 1100px;
          height: 220px;
          font-size: 17px;
          line-height: 21px;
          letter-spacing: 0.1px;
          overflow-y: scroll;
          @include m(title){
            font-size: 19px;
            color: #328086;
          }
        }
      }
    }
    @include e(checkbox){
      height: 69px;
      margin-top: 25px;
      float: right;
      @include m(input){
        display: none;
      }
      @include m(label){
        width: 22px;
        height: 22px;
        background-color:#b9b9b9;
        border-radius: 50px;
        position: relative;
        top: -2px;
        margin-right: 5px;
        display: inline-block;
        box-sizing: border-box;
        &::after{
          content: "✔";
          color: #FFF;
          position: relative;
          left: 4.5px;
          top: 3px;
        }
      }
    }
  }
  .AcceptTerms__checkbox--input:checked + .AcceptTerms__checkbox--label,
  .AcceptTerms__checkbox--label.checked {
    background-color: #799da1;
  }
</style>
