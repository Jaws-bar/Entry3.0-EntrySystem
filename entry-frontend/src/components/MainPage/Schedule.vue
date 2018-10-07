<template>
  <transition name="open-schedule" @after-enter="isAppear = true">
    <div class="schedule">
      <button class="schedule__close-btn"
        @click="$emit('close')">
        &times;
      </button>
      <transition name="appear-content">
        <div class="schedule__content" v-if="isAppear">
          <p class="schedule__content__text">
            지금은 <span class="text-deco--1">{{ scheduleName }}</span> 기간입니다.
          </p>
          <p class="schedule__content__text">
            오늘은 <span class="text-deco--2">{{ thisDateText }}</span>이며
            {{ word }}까지 <span class="text-deco--2">{{ restOfDateText }}</span> 남았습니다.
          </p>
          <process-bar class="schedule__content__process-bar"
            :captions="captions"
            :ahead="todayIndex"
            @change="changeCurrent"/>
          <p class="schedule__content__text">
            {{ scheduleDateText }}
          </p>
          <router-link to='/classify'  class="schedule__content__link">
            원서작성 하러가기
          </router-link >
        </div>
      </transition>
    </div>
  </transition>
</template>

<script>
import ProcessBar from './ProcessBar';

const week = ['일', '월', '화', '수', '목', '금', '토'];
const scheduleData = [
  {
    scheduleName: '원서작성',
    word: '마감일',
    endDate: [2018, 9, 26, 17, 0],
    startDate: [2018, 9, 23, 9, 0],
  },
  {
    scheduleName: '1차 발표',
    word: '발표일',
    endDate: [2018, 9, 31, 10, 0],
  },
  {
    scheduleName: '면접',
    word: '면접일',
    endDate: [2018, 10, 3, 8, 30],
  },
  {
    scheduleName: '2차 발표',
    word: '발표일',
    endDate: [2018, 10, 9, 10, 0],
  },
  {
    scheduleName: '합격자 등록',
    word: '마감일',
    endDate: [2018, 10, 14, 17, 0],
    startDate: [2018, 10, 9, 10, 0],
  },
];

export default {
  name: 'schedule',
  components: {
    ProcessBar,
  },
  props: {
    rotuerLink: String,
  },
  data() {
    return {
      isAppear: false, // to animate
      date: null, // Date Object
      captions: [],
      startDate: [],
      endDate: [],
      todayIndex: 0.5,
      scheduleName: '',
      word: '',
      scheduleDateText: '',
    };
  },
  created() {
    this.date = new Date();

    // captions 데이터 할당
    // ["원서작성", "1차 발표", "면접", "2차 발표", "합격자 등록"]
    this.captions = Array.from(scheduleData, data => data.scheduleName);

    this.todayIndex = this.checkCurrent();
    this.changeCurrent(this.todayIndex);
  },
  computed: {
    thisDateText() {
      return (
        `${this.date.getFullYear().toString().slice(-2)}년
        ${this.pad(this.date.getMonth() + 1, 2)}월
        ${this.pad(this.date.getDate(), 2)}일`
      );
    },
    restOfDateText() {
      const endDate = new Date(...this.endDate);

      // date constant
      const HOUR = 1000 * 60 * 60;
      const DAY = HOUR * 24;

      // calculate diffrance
      const diff = endDate - this.date;
      let dateDiff = parseInt(diff / DAY, 10);
      let hourDiff = parseInt((diff / HOUR) % 24, 10);

      // pad
      dateDiff = this.pad(dateDiff, 2);
      hourDiff = this.pad(hourDiff, 2);
      return `${dateDiff}일 ${hourDiff}시간`;
    },
  },
  methods: {
    checkCurrent() {
      let current;
      scheduleData.some((data, index) => {
        const endDate = new Date(...data.endDate);
        const diff = endDate - this.date;
        if (diff > 0) {
          current = index;
        } else if (diff === 0) {
          current = index + 0.5;
        }
        return diff >= 0;
      });
      if (current === 0) current = 0.5;
      return current;
    },
    pad(text, n) {
      return '0'.repeat(n - 1).concat(text).slice(-n);
    },
    formatDateText(array) {
      const date = new Date(...array);

      // 'yyyy, mm, dd, (d) hh:mm'
      return `${date.getFullYear()}. ${date.getMonth() + 1}. ${date.getDate()}
        (${week[date.getDay()]}) ${this.pad(date.getHours(), 2)}:${this.pad(date.getMinutes(), 2)}`;
    },
    changeCurrent(current) {
      // current index의 객체 참조
      let contents = scheduleData[parseInt(current, 10)];
      // contents 객체의 배열화
      contents = Object.values(contents);
      // contents 적용
      this.setContent(...contents);
    },
    setContent(scheduleName, word, endDate, startDate) {
      this.scheduleName = scheduleName;
      this.word = word;
      this.endDate = endDate;
      this.startDate = startDate;
      if (startDate) this.scheduleDateText = `${this.formatDateText(startDate)} ~ ${this.formatDateText(endDate)}`;
      else this.scheduleDateText = this.formatDateText(endDate);
    },
  },
};
</script>

<style lang="scss" scoped>
@import '../../style/setting';

.schedule {
  max-height: 580px;
  min-height: 480px;
  height: 60vh;
  width: 100%;
  background-color: #fff;
  display: flex;
  align-items: center;
  justify-content: center;
  position: relative;
  border: {
    top: solid 6px #6ab7b7;
  }
  @include e('close-btn') {
    color: #6ab7b7;
    font-size: 40px;
    height: 20px;
    line-height: 20px;
    position: absolute;
    top: 19px;
    right: 23px;
    border: none;
    background: none;
    font-weight: lighter;
    outline: none;
  }
  @include e('content') {
    @include e('process-bar') {
      margin: {
        top: 100px;
        bottom: 60px;
        left: auto;
        right: auto;
      }
    }
    text-align: center;
    @include e('text') {
      font-size: 22px;
      line-height: 2.05;
      letter-spacing: 0.6px;
      text-align: center;
      color: #000;
      font-weight: bold;
    }
    @include e('link') {
      height: 50px;
      display: inline-block;
      width: 300px;
      border-radius: 30px;
      background: -webkit-linear-gradient(101deg, #82cdca, #5db3b6);
      box-shadow: 1px 25px 20px -15px #9ff0eb;
      font-size: 22px;
      color: #fff;
      border: none;
      cursor: pointer;
      line-height: 50px;
      text-decoration: none;
      margin: {
        top: 40px;
      }
    }
  }
}

.text-deco {
  @include m('1') {
    font-size: 30px;
    line-height: 1.5;
    color: #005c4f;
  }
  @include m('2') {
    font-size: 24px;
    line-height: 1.88;
    color: #41beb8;
  }
}

// animation

.open-schedule {
  &-enter-active {
    transition: all .5s ease-in-out;
  }

  &-enter {
    min-height: 0;
    max-height: 0;
  }

  &-enter-to {
    max-height: 580px;
    min-height: 480px;
  }
}

.appear {
  &-content-enter {
    opacity: 0;
  }

  &-content-enter-to {
    opacity: 1;
  }

  &-content-enter-active {
    transition: opacity .5s;
  }
}
</style>
