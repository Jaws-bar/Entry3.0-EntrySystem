<template>
  <div class="classification form">
    <navigation />
    <headline :title="title" :subText="subText" />
    <div class="form__cover">

      <!-- form -->
      <div class="form__cover__form">
        <div class="form__cover__form__colums">
          <div class="form__cover__form__colums__name">
            검정고시 지원여부
          </div>
          <div class="form__cover__form__colums__input-content">
            <input type="checkbox" class="input-checkbox" id="isGED" v-model="isGED">
            <label class="input-checkbox-label" for="isGED"></label>
            <span class="form__cover__form__colums__input-content__sign">
              * 검정고시를 통하여 지원하실 경우 체크해주세요
            </span>
          </div>
        </div>

        <div class="form__cover__form__colums">
          <div class="form__cover__form__colums__name">
            전형 선택
          </div>
          <div class="form__cover__form__colums__input-content">
            <input type="radio"
              class="input-radio"
              id="general"
              value="NORMAL"
              @click="isOpen = false"
              v-model="admission">
            <label class="input-radio-label" for="general">
              <span class="input-radio-span"></span>
            </label>
            <label class="form__cover__form__colums__input-content__label"
              for="general">
              일반전형
            </label>

            <input type="radio"
              class="input-radio"
              id="meister"
              value="MEISTER"
              @click="isOpen = false"
              v-model="admission">
            <label class="input-radio-label" for="meister">
              <span class="input-radio-span"></span>
            </label>
            <label class="form__cover__form__colums__input-content__label"
              for="meister">
              마이스터 인재전형
            </label>

            <input type="radio"
              class="input-radio"
              id="social"
              value="SOCIAL"
              v-model="admission"
              @click="isOpen = true">
            <label class="input-radio-label" for="social">
              <span class="input-radio-span"></span>
            </label>
            <social-option
              :options="socialOptions"
              v-model="admissionDetail"
              v-show="isOpen"
              @close="isOpen = false"/>
            <label class="form__cover__form__colums__input-content__label"
              for="social">
              사회통합전형
              <span v-show="admissionDetail">/ {{ admissionDetail ? admissionDetail.text : '' }}
                <span class="point-color">▾</span>
              </span>
            </label>
          </div>
        </div>

        <div class="form__cover__form__colums">
          <div class="form__cover__form__colums__name">
            지역 선택
          </div>
          <div class="form__cover__form__colums__input-content">
            <selectbox class="selectbox"
              v-model="region"
              :options="[
                {text: '전국', value:false},
                {text: '대전', value:true}
              ]"/>
          </div>
        </div>

        <div v-show="!isGED">
          <div class="form__cover__form__colums">
            <div class="form__cover__form__colums__name">
              졸업 구분
            </div>
            <div class="form__cover__form__colums__input-content">
              <input type="radio"
                class="input-radio"
                id="notGraduated"
                :value="false"
                v-model="isGraduated">
              <label class="input-radio-label" for="notGraduated">
                <span class="input-radio-span"></span>
              </label>
              <label class="form__cover__form__colums__input-content__label"
                for="notGraduated">
                졸업 예정자
              </label>

              <input type="radio"
                class="input-radio"
                id="graduated"
                :value="true"
                v-model="isGraduated">
              <label class="input-radio-label" for="graduated">
                <span class="input-radio-span"></span>
              </label>
              <label class="form__cover__form__colums__input-content__label"
                for="graduated">
                졸업자
              </label>
            </div>
          </div>

          <div v-show="isGraduated" class="form__cover__form__colums">
            <div class="form__cover__form__colums__name">
              졸업 연도
            </div>
            <div class="form__cover__form__colums__input-content">
              <selectbox class="selectbox"
                v-model="graduateYear"
                :isEnabled="isGraduated"
                :options="[
                  {text: '2018', value: 2018},
                  {text: '2017', value: 2017},
                  {text: '2016', value: 2016},
                  {text: '2015', value: 2015},
                  {text: '2014', value: 2014},
                  {text: '2013', value: 2013},
                  {text: '2012', value: 2012},
                  {text: '2011', value: 2011},
                  {text: '2010', value: 2010},
                ]"/>
                년
              <span class="form__cover__form__colums__input-content__sign">
                * 졸업자의 경우 졸업연도를 선택해주세요
              </span>
            </div>
          </div>
        </div>

        <div class="form__cover__form__colums">
          <div class="form__cover__form__colums__name">
            특기사항
          </div>
          <div class="form__cover__form__colums__input-content">
            <input type="radio"
              class="input-radio"
              id="honor"
              value="NATIONAL_MERIT"
              v-model="additionalType">
            <label class="input-radio-label" for="honor">
              <span class="input-radio-span"></span>
            </label>
            <label class="form__cover__form__colums__input-content__label"
              for="honor">
              국가 유공자
            </label>

            <input type="radio"
              class="input-radio"
              id="exception"
              value="SPECIAL_ADMISSION"
              v-model="additionalType">
            <label class="input-radio-label" for="exception">
              <span class="input-radio-span"></span>
            </label>
            <label class="form__cover__form__colums__input-content__label"
              for="exception">
              특례 입학 대상자
            </label>

            <input type="radio"
              class="input-radio"
              id="NONE"
              value="NONE"
              v-model="additionalType">
            <label class="input-radio-label" for="NONE">
              <span class="input-radio-span"></span>
            </label>
            <label class="form__cover__form__colums__input-content__label"
              for="NONE">
              해당없음
            </label>
          </div>
        </div>
      </div>
      <!-- form end -->

      <prev-next-btn
        :prevShow="false"
        :nextShow="true"
        :nextLink="nextLink"
        :onClick="() => sendServer()"/>
    </div>
    <entry-footer />
  </div>
</template>

<script>
import Navigation from '../common/Navigation';
import Headline from '../common/Headline';
import PrevNextBtn from '../common/PrevNextBtn';
import EntryFooter from '../common/EntryFooter';
import Selectbox from '../common/Selectbox';
import SocialOption from './SocialOption';

export default {
  name: 'classification',
  components: {
    Navigation,
    Headline,
    PrevNextBtn,
    EntryFooter,
    Selectbox,
    SocialOption,
  },
  data() {
    return {
      title: '전형 구분 선택',
      subText: '2019 입학원서 작성',
      socialOptions: [
        { text: '기초생활수급권자', value: 'BENEFICIARY' },
        { text: '한부모가족 보호대상자', value: 'ONE_PARENT' },
        { text: '차상위 계층', value: 'CHA_UPPER' },
        { text: '차차상위 계층', value: 'CHACHA_UPPER' },
        { text: '북한이탈주민', value: 'FROM_NORTH' },
        { text: '다문화 가정', value: 'MULTI_CULTURE' },
        { text: '그 외 대상자', value: 'ETC' },
      ],
      isOpen: false,
      nextLink: '/personal',
    };
  },
  computed: {
    graduateType() {
      if (this.isGED) {
        this.$store.commit('updateGraduateType', {
          data: 'GED',
        });
        return 'GED';
      } else if (!this.isGraduated) {
        this.$store.commit('updategraduateYear', {
          data: 2019,
        });
        this.$store.commit('updateGraduateType', {
          data: 'WILL',
        });
        return 'WILL';
      } else if (this.isGraduated) {
        this.$store.commit('updateGraduateType', {
          data: 'DONE',
        });
        return 'DONE';
      }
      return 'WILL';
    },
    isGED: {
      get() {
        return this.$store.state.classify.isGED;
      },
      set(value) {
        this.$store.commit('updateisGED', {
          data: value,
        });
        this.$store.commit('updateGraduateType', {
          data: this.graduateType,
        });
      },
    },
    admissionDetail: {
      get() {
        return this.$store.state.classify.admissionDetail;
      },
      set(data) {
        if (typeof data === 'object') {
          this.$store.commit('updateadmissionDetail', {
            data,
          });
        }
        return 'NONE';
      },
    },
    admission: {
      get() {
        return this.$store.state.classify.admission;
      },
      set(value) {
        this.$store.commit('updateadmission', {
          data: value,
        });
        if (value !== 'SOCIAL') {
          this.$store.commit('updateadmissionDetail', {
            data: {
              text: '',
              value: 'NONE',
            },
          });
        }
      },
    },
    region: {
      get() {
        return this.$store.state.classify.region;
      },
      set(value) {
        this.$store.commit('updateRegion', {
          data: value,
        });
      },
    },
    isGraduated: {
      get() {
        return this.$store.state.classify.isGraduated;
      },
      set(value) {
        this.$store.commit('updateIsGraduated', {
          data: value,
        });
        this.$store.commit('updateGraduateType', {
          data: this.graduateType,
        });
      },
    },
    additionalType: {
      get() {
        return this.$store.state.classify.additionalType;
      },
      set(value) {
        this.$store.commit('updateadditionalType', {
          data: value,
        });
      },
    },
    graduateYear: {
      get() {
        return this.$store.state.classify.graduateYear;
      },
      set(value) {
        this.$store.commit('updategraduateYear', {
          data: value,
        });
      },
    },
  },
  methods: {
    moveNext() {
      this.$router.push('/');
    },
    sendServer() {
      const token = this.$cookies.get('accessToken');
      const { s, e, w } = this.$toastr;
      const {
        graduateType,
        admission,
        additionalType,
        region,
        graduateYear,
      } = this.$store.state.classify;
      const admissionDetail = this.$store.state.classify.admissionDetail.value;
      let data = {
        graduateType,
        admission,
        admissionDetail,
        additionalType,
        region,
        graduateYear,
      };
      if (graduateType === 'GED' && admission === 'SOCIAL') {
        data = {
          graduateType,
          admission,
          additionalType,
          admissionDetail,
          region,
        };
      } else if (admission === 'SOCIAL') {
        data = {
          graduateType,
          admission,
          additionalType,
          region,
          graduateYear,
          admissionDetail,
        };
      } else if (graduateType === 'GED') {
        data = {
          graduateType,
          admission,
          additionalType,
          admissionDetail,
          region,
        };
      }
      if (graduateType === 'DONE' && graduateYear === 2019) {
        w('졸업자의 졸업년도가 설정되지 않았습니다.');
      }
      this.$axios({
        method: 'put',
        url: 'http://entrydsm.hs.kr/api/me/classification',
        headers: { Authorization: `JWT ${token}` },
        data,
      }).then((res) => {
        if (res.status === 200) {
          s('서버에 임시저장 되었습니다.');
        } else if (res.status === 400) {
          res.data.errors.map((error => e(`${error.field}-${error.message}`)));
        } else {
          e('서버와 통신이 불안정합니다.<br/>다시 시도해주세요.');
        }
      }).catch((error) => {
        if (error.response.status === 400) {
          error.response.data.errors.map((msg => w(`${msg.field}-${msg.message}`)));
        } else if (error.response.status === 401) {
          e('로그인이 반드시 필요합니다.');
          this.$router.push('/');
          this.$store.commit('changeIndex', {
            index: 1,
          });
        } else {
          e('서버와 통신이 불안정합니다.<br/>다시 시도해주세요.');
        }
      });
    },
  },
};
</script>

<style lang="scss" scoped>
@import '../../style/setting';
.point-color {
  color: #799da1;
}

.selectbox {
  width: 150px;
}
</style>
