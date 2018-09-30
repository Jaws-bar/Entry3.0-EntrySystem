<template>
  <div class="grade-input-cover">
    <h3>봉사 & 출석</h3>
    <table class="volunteer-attendance-table">
      <tr class="table-row">
        <td class="table-data-text">봉사시간</td>
        <td class="table-data-text">
          <input type="number"
                  v-model.number="volunteerNAttendance.volunteerTime"
                  @change="updateVolunteerNAttendance('volunteerTime', $event.target.value)"
                  @keydown="onlyNumber"
                  min="0"
                  class="input-text table-data-text__input"
          >
          <span class="table-data-text__time">시간</span>
        </td>
      </tr>
      <tr class="table-row">
        <td class="table-data-text">출석정보</td>
        <td class="table-data-text table-data-text--attendance-info">
          <tr class="table-data-text__row">
            <td class="table-data-text__row__data">전체 무단 결석 일수</td>
            <td class="table-data-text__row__data">
              <input type="number"
                      v-model.number="volunteerNAttendance.fullCut"
                      @change="updateVolunteerNAttendance('fullCut', $event.target.value)"
                      @keydown="onlyNumber"
                      min="0"
                      class="input-text table-data-text__input"
              >
              <span class="table-data-text__time">일</span>
            </td>
            <td class="table-data-text__row__data">전체 무단 조퇴 일수</td>
            <td class="table-data-text__row__data">
              <input type="number"
                      v-model.number="volunteerNAttendance.earlyLeave"
                      @change="updateVolunteerNAttendance('earlyLeave', $event.target.value)"
                      @keydown="onlyNumber"
                      min="0"
                      class="input-text table-data-text__input"
              >
              <span class="table-data-text__time">일</span>
            </td>
          </tr>
          <tr class="table-data-text__row">
            <td class="table-data-text__row__data">전체 무단 지각 일수</td>
            <td class="table-data-text__row__data">
              <input type="number"
                      v-model.number="volunteerNAttendance.late"
                      @change="updateVolunteerNAttendance('late', $event.target.value)"
                      @keydown="onlyNumber"
                      min="0"
                      class="input-text table-data-text__input"
              >
              <span class="table-data-text__time">일</span>
            </td>
            <td class="table-data-text__row__data">전체 무단 결과 일수</td>
            <td class="table-data-text__row__data">
              <input type="number"
                      v-model.number="volunteerNAttendance.periodCut"
                      @change="updateVolunteerNAttendance('periodCut', $event.target.value)"
                      @keydown="onlyNumber"
                      min="0"
                      class="input-text table-data-text__input"
              >
              <span class="table-data-text__time">일</span>
            </td>
          </tr>
        </td>
      </tr>
    </table>

    <h3>미이수 학기(자유학기제) 선택</h3>
    <table class="discomplete-table">
      <tr class="table-row">
        <td class="table-row__first-grade grade-names">1학년</td>
        <td class="table-row__second-grade grade-names">2학년</td>
        <td class="table-row__third-grade grade-names">3학년</td>
      </tr>
      <tr class="table-row">
        <td class="table-row__first-semester semester-checkboxes">
          <input type="checkbox"
                  class="input-checkbox"
                  id="input-first-first"
                  :value="`${semesters.firstFirstSemester}`"
                  v-model="semesters.firstFirstSemester"
                  @change="discompleteSemester"
          >
          <label class="input-checkbox-label" for="input-first-first"></label>
          <span class="table-row__semester-text">1학기</span>
        </td>
        <td class="table-row__first-semester semester-checkboxes">
          <input type="checkbox"
                  class="input-checkbox"
                  id="input-first-second"
                  :value="`${semesters.firstSecondSemester}`"
                  v-model="semesters.firstSecondSemester"
                  @change="discompleteSemester"
          >
          <label class="input-checkbox-label" for="input-first-second"></label>
          <span class="table-row__semester-text">2학기</span>
        </td>
        <td class="table-row__second-semester semester-checkboxes">
          <input type="checkbox"
                  class="input-checkbox"
                  id="input-second-first"
                  :value="`${semesters.secondFirstSemester}`"
                  v-model="semesters.secondFirstSemester"
                  @change="discompleteSemester"
          >
          <label class="input-checkbox-label" for="input-second-first"></label>
          <span class="table-row__semester-text">1학기</span>
        </td>
        <td class="table-row__second-semester semester-checkboxes">
          <input type="checkbox"
                  class="input-checkbox"
                  id="input-second-second"
                  :value="`${semesters.secondSecondSemester}`"
                  v-model="semesters.secondSecondSemester"
                  @change="discompleteSemester"
          >
          <label class="input-checkbox-label" for="input-second-second"></label>
          <span class="table-row__semester-text">2학기</span>
        </td>
        <td class="table-row__third-semester semester-checkboxes">
          <input type="checkbox"
                  class="input-checkbox"
                  id="input-third-first"
                  :value="`${semesters.thirdFirstSemester}`"
                  v-model="semesters.thirdFirstSemester"
                  @change="discompleteSemester"
          >
          <label class="input-checkbox-label" for="input-third-first"></label>
          <span class="table-row__semester-text">1학기</span>
        </td>
        <td class="table-row__third-semester semester-checkboxes">
          <input type="checkbox"
                  class="input-checkbox"
                  id="input-third-second"
                  :value="`${semesters.thirdSecondSemester}`"
                  v-model="semesters.thirdSecondSemester"
                  @change="discompleteSemester"
          >
          <label class="input-checkbox-label" for="input-third-second"></label>
          <span class="table-row__semester-text">2학기</span>
        </td>
      </tr>
    </table>

    <h3>성적입력 (해당 학기 미이수 과목은 X 선택)</h3>
    <table class="grade-input-table" ref="grade-table">
      <div class="all-grade-reset-cover">
        <div class="all-grade-reset-cover__box" :class="allHoverCheck()">
          <span class="all-grade-reset-cover__box__text">
            전체 성적이 {{ resetAllGrade }} 로 초기화 됩니다.
          </span>
        </div>
        <div class="all-grade-reset-cover__reset">
          <span class="all-grade-reset-cover__reset__text">전체 성적 초기화: </span>
          <button type="button"
                  v-for="reset in scores"
                  :key="reset"
                  @click="resetGrade"
                  @mouseover="setHoverGrade(reset)"
                  @mouseout="checkHover = false"
          >
            {{ reset.toUpperCase() }}
          </button>
        </div>
      </div>
      <tr class="grade-input-table__row">
        <td class="empty-space"></td>
        <td class="first-grade row-grades" colspan="2">1학년</td>
        <td class="second-grade row-grades" colspan="2">2학년</td>
        <td class="third-grade row-grades" colspan="2">3학년</td>
      </tr>
      <tr class="grade-input-table__row">
        <td class="empty-space"></td>
        <td>1학기</td>
        <td>2학기</td>
        <td>1학기</td>
        <td>2학기</td>
        <td>1학기</td>
        <td>2학기</td>
      </tr>
      <tr class="grade-input-table__row">
        <td>국어</td>
        <td :class="changeBackground(korean[0].score)">
          <input type="radio"
                  v-for="k in scores"
                  :key="k"
                  :value="k.toUpperCase()"
                  :id="`korean-first-first-${k}`"
                  v-model="korean[0].score"
                  :checked="korean[0].decided"
          >
          <label v-for="(k, index) in scores"
                  :key="index"
                  :for="`korean-first-first-${k}`"
                  class="input-grade-label"
                  @click="changeDecided($event, korean[0])"
                  :class="complete(korean[0].score, k.toUpperCase())"
          >
            {{ k.toUpperCase() }}
          </label>
          <div class="button-side-line" v-show="!korean[0].score"></div>
          <input type="radio"
                  value="X"
                  id="korean-first-first-null"
                  v-model="korean[0].score"
                  :checked="korean[0].decided"
          >
          <label for="korean-first-first-null"
                  class="input-grade-label"
                  @click="changeDecided($event, korean[0])"
                  :class="discomplete(korean[0].score)"
          >X</label>
        </td>
        <td :class="changeBackground(korean[1].score)">
          <input type="radio"
                  v-for="k in scores"
                  :key="k"
                  :value="k.toUpperCase()"
                  :id="`korean-first-second-${k}`"
                  v-model="korean[1].score"
                  :checked="korean[1].decided"
          >
          <label v-for="(k, index) in scores"
                  :key="index"
                  :for="`korean-first-second-${k}`"
                  class="input-grade-label"
                  @click="changeDecided($event, korean[1])"
                  :class="complete(korean[1].score, k.toUpperCase())"
          >
            {{ k.toUpperCase() }}
          </label>
          <div class="button-side-line" v-show="!korean[1].score"></div>
          <input type="radio"
                  value="X"
                  id="korean-first-second-null"
                  v-model="korean[1].score"
                  :checked="korean[1].decided"
          >
          <label for="korean-first-second-null"
                  class="input-grade-label"
                  @click="changeDecided($event, korean[1])"
                  :class="discomplete(korean[1].score)"
          >X</label>
        </td>
        <td :class="changeBackground(korean[2].score)">
          <input type="radio"
                  v-for="k in scores"
                  :key="k"
                  :value="k.toUpperCase()"
                  :id="`korean-second-first-${k}`"
                  v-model="korean[2].score"
                  :checked="korean[2].decided"
          >
          <label v-for="(k, index) in scores"
                  :key="index"
                  :for="`korean-second-first-${k}`"
                  class="input-grade-label"
                  @click="changeDecided($event, korean[2])"
                  :class="complete(korean[2].score, k.toUpperCase())"
          >
            {{ k.toUpperCase() }}
          </label>
          <div class="button-side-line" v-show="!korean[2].score"></div>
          <input type="radio"
                  value="X"
                  id="korean-second-first-null"
                  v-model="korean[2].score"
                  :checked="korean[2].decided"
          >
          <label for="korean-second-first-null"
                  class="input-grade-label"
                  @click="changeDecided($event, korean[2])"
                  :class="discomplete(korean[2].score)"
          >X</label>
        </td>
        <td :class="changeBackground(korean[3].score)">
          <input type="radio"
                  v-for="k in scores"
                  :key="k"
                  :value="k.toUpperCase()"
                  :id="`korean-second-second-${k}`"
                  v-model="korean[3].score"
                  :checked="korean[3].decided"
          >
          <label v-for="(k, index) in scores"
                  :key="index"
                  :for="`korean-second-second-${k}`"
                  class="input-grade-label"
                  @click="changeDecided($event, korean[3])"
                  :class="complete(korean[3].score, k.toUpperCase())"
          >
            {{ k.toUpperCase() }}
          </label>
          <div class="button-side-line" v-show="!korean[3].score"></div>
          <input type="radio"
                  value="X"
                  id="korean-second-second-null"
                  v-model="korean[3].score"
                  :checked="korean[3].decided"
          >
          <label for="korean-second-second-null"
                  class="input-grade-label"
                  @click="changeDecided($event, korean[3])"
                  :class="discomplete(korean[3].score)"
          >X</label>
        </td>
        <td :class="changeBackground(korean[4].score)">
          <input type="radio"
                  v-for="k in scores"
                  :key="k"
                  :value="k.toUpperCase()"
                  :id="`korean-third-first-${k}`"
                  v-model="korean[4].score"
                  :checked="korean[4].decided"
          >
          <label v-for="(k, index) in scores"
                  :key="index"
                  :for="`korean-third-first-${k}`"
                  class="input-grade-label"
                  @click="changeDecided($event, korean[4])"
                  :class="complete(korean[4].score, k.toUpperCase())"
          >
            {{ k.toUpperCase() }}
          </label>
          <div class="button-side-line" v-show="!korean[4].score"></div>
          <input type="radio"
                  value="X"
                  id="korean-third-first-null"
                  v-model="korean[4].score"
                  :checked="korean[4].decided"
          >
          <label for="korean-third-first-null"
                  class="input-grade-label"
                  @click="changeDecided($event, korean[4])"
                  :class="discomplete(korean[4].score)"
          >X</label>
        </td>
        <td :class="changeBackground(korean[5].score)">
          <input type="radio"
                  v-for="k in scores"
                  :key="k"
                  :value="k.toUpperCase()"
                  :id="`korean-third-second-${k}`"
                  v-model="korean[5].score"
                  :checked="korean[5].decided"
          >
          <label v-for="(k, index) in scores"
                  :key="index"
                  :for="`korean-third-second-${k}`"
                  class="input-grade-label"
                  @click="changeDecided($event, korean[5])"
                  :class="complete(korean[5].score, k.toUpperCase())"
          >
            {{ k.toUpperCase() }}
          </label>
          <div class="button-side-line" v-show="!korean[5].score"></div>
          <input type="radio"
                  value="X"
                  id="korean-third-second-null"
                  v-model="korean[5].score"
                  :checked="korean[5].decided"
          >
          <label for="korean-third-second-null"
                  class="input-grade-label"
                  @click="changeDecided($event, korean[5])"
                  :class="discomplete(korean[5].score)"
          >X</label>
        </td>
      </tr>
      <tr class="grade-input-table__row">
        <td>사회</td>
        <td :class="changeBackground(social[0].score)">
          <input type="radio"
                  v-for="k in scores"
                  :key="k"
                  :value="k.toUpperCase()"
                  :id="`society-first-first-${k}`"
                  v-model="social[0].score"
                  :checked="social[0].decided"
          >
          <label v-for="(k, index) in scores"
                  :key="index"
                  :for="`society-first-first-${k}`"
                  class="input-grade-label"
                  @click="changeDecided($event, social[0])"
                  :class="complete(social[0].score, k.toUpperCase())"
          >
            {{ k.toUpperCase() }}
          </label>
          <div class="button-side-line" v-show="!social[0].score"></div>
          <input type="radio"
                  value="X"
                  id="society-first-first-null"
                  v-model="social[0].score"
                  :checked="social[0].decided"
          >
          <label for="society-first-first-null"
                  class="input-grade-label"
                  @click="changeDecided($event, social[0])"
                  :class="discomplete(social[0].score)"
          >X</label>
        </td>
        <td :class="changeBackground(social[1].score)">
          <input type="radio"
                  v-for="k in scores"
                  :key="k"
                  :value="k.toUpperCase()"
                  :id="`society-first-second-${k}`"
                  v-model="social[1].score"
                  :checked="social[1].decided"
          >
          <label v-for="(k, index) in scores"
                  :key="index"
                  :for="`society-first-second-${k}`"
                  class="input-grade-label"
                  @click="changeDecided($event, social[1])"
                  :class="complete(social[1].score, k.toUpperCase())"
          >
            {{ k.toUpperCase() }}
          </label>
          <div class="button-side-line" v-show="!social[1].score"></div>
          <input type="radio"
                  value="X"
                  id="society-first-second-null"
                  v-model="social[1].score"
                  :checked="social[1].decided"
          >
          <label for="society-first-second-null"
                  class="input-grade-label"
                  @click="changeDecided($event, social[1])"
                  :class="discomplete(social[1].score)"
          >X</label>
        </td>
        <td :class="changeBackground(social[2].score)">
          <input type="radio"
                  v-for="k in scores"
                  :key="k"
                  :value="k.toUpperCase()"
                  :id="`society-second-first-${k}`"
                  v-model="social[2].score"
                  :checked="social[2].decided"
          >
          <label v-for="(k, index) in scores"
                  :key="index"
                  :for="`society-second-first-${k}`"
                  class="input-grade-label"
                  @click="changeDecided($event, social[2])"
                  :class="complete(social[2].score, k.toUpperCase())"
          >
            {{ k.toUpperCase() }}
          </label>
          <div class="button-side-line" v-show="!social[2].score"></div>
          <input type="radio"
                  value="X"
                  id="society-second-first-null"
                  v-model="social[2].score"
                  :checked="social[2].decided"
          >
          <label for="society-second-first-null"
                  class="input-grade-label"
                  @click="changeDecided($event, social[2])"
                  :class="discomplete(social[2].score)"
          >X</label>
        </td>
        <td :class="changeBackground(social[3].score)">
          <input type="radio"
                  v-for="k in scores"
                  :key="k"
                  :value="k.toUpperCase()"
                  :id="`society-second-second-${k}`"
                  v-model="social[3].score"
                  :checked="social[3].decided"
          >
          <label v-for="(k, index) in scores"
                  :key="index"
                  :for="`society-second-second-${k}`"
                  class="input-grade-label"
                  @click="changeDecided($event, social[3])"
                  :class="complete(social[3].score, k.toUpperCase())"
          >
            {{ k.toUpperCase() }}
          </label>
          <div class="button-side-line" v-show="!social[3].score"></div>
          <input type="radio"
                  value="X"
                  id="society-second-second-null"
                  v-model="social[3].score"
                  :checked="social[3].decided"
          >
          <label for="society-second-second-null"
                  class="input-grade-label"
                  @click="changeDecided($event, social[3])"
                  :class="discomplete(social[3].score)"
          >X</label>
        </td>
        <td :class="changeBackground(social[4].score)">
          <input type="radio"
                  v-for="k in scores"
                  :key="k"
                  :value="k.toUpperCase()"
                  :id="`society-third-first-${k}`"
                  v-model="social[4].score"
                  :checked="social[4].decided"
          >
          <label v-for="(k, index) in scores"
                  :key="index"
                  :for="`society-third-first-${k}`"
                  class="input-grade-label"
                  @click="changeDecided($event, social[4])"
                  :class="complete(social[4].score, k.toUpperCase())"
          >
            {{ k.toUpperCase() }}
          </label>
          <div class="button-side-line" v-show="!social[4].score"></div>
          <input type="radio"
                  value="X"
                  id="society-third-first-null"
                  v-model="social[4].score"
                  :checked="social[4].decided"
          >
          <label for="society-third-first-null"
                  class="input-grade-label"
                  @click="changeDecided($event, social[4])"
                  :class="discomplete(social[4].score)"
          >X</label>
        </td>
        <td :class="changeBackground(social[5].score)">
          <input type="radio"
                  v-for="k in scores"
                  :key="k"
                  :value="k.toUpperCase()"
                  :id="`society-third-second-${k}`"
                  v-model="social[5].score"
                  :checked="social[5].decided"
          >
          <label v-for="(k, index) in scores"
                  :key="index"
                  :for="`society-third-second-${k}`"
                  class="input-grade-label"
                  @click="changeDecided($event, social[5])"
                  :class="complete(social[5].score, k.toUpperCase())"
          >
            {{ k.toUpperCase() }}
          </label>
          <div class="button-side-line" v-show="!social[5].score"></div>
          <input type="radio"
                  value="X"
                  id="society-third-second-null"
                  v-model="social[5].score"
                  :checked="social[5].decided"
          >
          <label for="society-third-second-null"
                  class="input-grade-label"
                  @click="changeDecided($event, social[5])"
                  :class="discomplete(social[5].score)"
          >X</label>
        </td>
      </tr>
      <tr class="grade-input-table__row">
        <td>역사</td>
        <td :class="changeBackground(history[0].score)">
          <input type="radio"
                  v-for="k in scores"
                  :key="k"
                  :value="k.toUpperCase()"
                  :id="`history-first-first-${k}`"
                  v-model="history[0].score"
                  :checked="history[0].decided"
          >
          <label v-for="(k, index) in scores"
                  :key="index"
                  :for="`history-first-first-${k}`"
                  class="input-grade-label"
                  @click="changeDecided($event, history[0])"
                  :class="complete(history[0].score, k.toUpperCase())"
          >
            {{ k.toUpperCase() }}
          </label>
          <div class="button-side-line" v-show="!history[0].score"></div>
          <input type="radio"
                  value="X"
                  id="history-first-first-null"
                  v-model="history[0].score"
                  :checked="history[0].decided"
          >
          <label for="history-first-first-null"
                  class="input-grade-label"
                  @click="changeDecided($event, history[0])"
                  :class="discomplete(history[0].score)"
          >X</label>
        </td>
        <td :class="changeBackground(history[1].score)">
          <input type="radio"
                  v-for="k in scores"
                  :key="k"
                  :value="k.toUpperCase()"
                  :id="`history-first-second-${k}`"
                  v-model="history[1].score"
                  :checked="history[1].decided"
          >
          <label v-for="(k, index) in scores"
                  :key="index"
                  :for="`history-first-second-${k}`"
                  class="input-grade-label"
                  @click="changeDecided($event, history[1])"
                  :class="complete(history[1].score, k.toUpperCase())"
          >
            {{ k.toUpperCase() }}
          </label>
          <div class="button-side-line" v-show="!history[1].score"></div>
          <input type="radio"
                  value="X"
                  id="history-first-second-null"
                  v-model="history[1].score"
                  :checked="history[1].decided"
          >
          <label for="history-first-second-null"
                  class="input-grade-label"
                  @click="changeDecided($event, history[1])"
                  :class="discomplete(history[1].score)"
          >X</label>
        </td>
        <td :class="changeBackground(history[2].score)">
          <input type="radio"
                  v-for="k in scores"
                  :key="k"
                  :value="k.toUpperCase()"
                  :id="`history-second-first-${k}`"
                  v-model="history[2].score"
                  :checked="history[2].decided"
          >
          <label v-for="(k, index) in scores"
                  :key="index"
                  :for="`history-second-first-${k}`"
                  class="input-grade-label"
                  @click="changeDecided($event, history[2])"
                  :class="complete(history[2].score, k.toUpperCase())"
          >
            {{ k.toUpperCase() }}
          </label>
          <div class="button-side-line" v-show="!history[2].score"></div>
          <input type="radio"
                  value="X"
                  id="history-second-first-null"
                  v-model="history[2].score"
                  :checked="history[2].decided"
          >
          <label for="history-second-first-null"
                  class="input-grade-label"
                  @click="changeDecided($event, history[2])"
                  :class="discomplete(history[2].score)"
          >X</label>
        </td>
        <td :class="changeBackground(history[3].score)">
          <input type="radio"
                  v-for="k in scores"
                  :key="k"
                  :value="k.toUpperCase()"
                  :id="`history-second-second-${k}`"
                  v-model="history[3].score"
                  :checked="history[3].decided"
          >
          <label v-for="(k, index) in scores"
                  :key="index"
                  :for="`history-second-second-${k}`"
                  class="input-grade-label"
                  @click="changeDecided($event, history[3])"
                  :class="complete(history[3].score, k.toUpperCase())"
          >
            {{ k.toUpperCase() }}
          </label>
          <div class="button-side-line" v-show="!history[3].score"></div>
          <input type="radio"
                  value="X"
                  id="history-second-second-null"
                  v-model="history[3].score"
                  :checked="history[3].decided"
          >
          <label for="history-second-second-null"
                  class="input-grade-label"
                  @click="changeDecided($event, history[3])"
                  :class="discomplete(history[3].score)"
          >X</label>
        </td>
        <td :class="changeBackground(history[4].score)">
          <input type="radio"
                  v-for="k in scores"
                  :key="k"
                  :value="k.toUpperCase()"
                  :id="`history-third-first-${k}`"
                  v-model="history[4].score"
                  :checked="history[4].decided"
          >
          <label v-for="(k, index) in scores"
                  :key="index"
                  :for="`history-third-first-${k}`"
                  class="input-grade-label"
                  @click="changeDecided($event, history[4])"
                  :class="complete(history[4].score, k.toUpperCase())"
          >
            {{ k.toUpperCase() }}
          </label>
          <div class="button-side-line" v-show="!history[4].score"></div>
          <input type="radio"
                  value="X"
                  id="history-third-first-null"
                  v-model="history[4].score"
                  :checked="history[4].decided"
          >
          <label for="history-third-first-null"
                  class="input-grade-label"
                  @click="changeDecided($event, history[4])"
                  :class="discomplete(history[4].score)"
          >X</label>
        </td>
        <td :class="changeBackground(history[5].score)">
          <input type="radio"
                  v-for="k in scores"
                  :key="k"
                  :value="k.toUpperCase()"
                  :id="`history-third-second-${k}`"
                  v-model="history[5].score"
                  :checked="history[5].decided"
          >
          <label v-for="(k, index) in scores"
                  :key="index"
                  :for="`history-third-second-${k}`"
                  class="input-grade-label"
                  @click="changeDecided($event, history[5])"
                  :class="complete(history[5].score, k.toUpperCase())"
          >
            {{ k.toUpperCase() }}
          </label>
          <div class="button-side-line" v-show="!history[5].score"></div>
          <input type="radio"
                  value="X"
                  id="history-third-second-null"
                  v-model="history[5].score"
                  :checked="history[5].decided"
          >
          <label for="history-third-second-null"
                  class="input-grade-label"
                  @click="changeDecided($event, history[5])"
                  :class="discomplete(history[5].score)"
          >X</label>
        </td>
      </tr>
      <tr class="grade-input-table__row">
        <td>수학</td>
        <td :class="changeBackground(math[0].score)">
          <input type="radio"
                  v-for="k in scores"
                  :key="k"
                  :value="k.toUpperCase()"
                  :id="`math-first-first-${k}`"
                  v-model="math[0].score"
                  :checked="math[0].decided"
          >
          <label v-for="(k, index) in scores"
                  :key="index"
                  :for="`math-first-first-${k}`"
                  class="input-grade-label"
                  @click="changeDecided($event, math[0])"
                  :class="complete(math[0].score, k.toUpperCase())"
          >
            {{ k.toUpperCase() }}
          </label>
          <div class="button-side-line" v-show="!math[0].score"></div>
          <input type="radio"
                  value="X"
                  id="math-first-first-null"
                  v-model="math[0].score"
                  :checked="math[0].decided"
          >
          <label for="math-first-first-null"
                  class="input-grade-label"
                  @click="changeDecided($event, math[0])"
                  :class="discomplete(math[0].score)"
          >X</label>
        </td>
        <td :class="changeBackground(math[1].score)">
          <input type="radio"
                  v-for="k in scores"
                  :key="k"
                  :value="k.toUpperCase()"
                  :id="`math-first-second-${k}`"
                  v-model="math[1].score"
                  :checked="math[1].decided"
          >
          <label v-for="(k, index) in scores"
                  :key="index"
                  :for="`math-first-second-${k}`"
                  class="input-grade-label"
                  @click="changeDecided($event, math[1])"
                  :class="complete(math[1].score, k.toUpperCase())"
          >
            {{ k.toUpperCase() }}
          </label>
          <div class="button-side-line" v-show="!math[1].score"></div>
          <input type="radio"
                  value="X"
                  id="math-first-second-null"
                  v-model="math[1].score"
                  :checked="math[1].decided"
          >
          <label for="math-first-second-null"
                  class="input-grade-label"
                  @click="changeDecided($event, math[1])"
                  :class="discomplete(math[1].score)"
          >X</label>
        </td>
        <td :class="changeBackground(math[2].score)">
          <input type="radio"
                  v-for="k in scores"
                  :key="k"
                  :value="k.toUpperCase()"
                  :id="`math-second-first-${k}`"
                  v-model="math[2].score"
                  :checked="math[2].decided"
          >
          <label v-for="(k, index) in scores"
                  :key="index"
                  :for="`math-second-first-${k}`"
                  class="input-grade-label"
                  @click="changeDecided($event, math[2])"
                  :class="complete(math[2].score, k.toUpperCase())"
          >
            {{ k.toUpperCase() }}
          </label>
          <div class="button-side-line" v-show="!math[2].score"></div>
          <input type="radio"
                  value="X"
                  id="math-second-first-null"
                  v-model="math[2].score"
                  :checked="math[2].decided"
          >
          <label for="math-second-first-null"
                  class="input-grade-label"
                  @click="changeDecided($event, math[2])"
                  :class="discomplete(math[2].score)"
          >X</label>
        </td>
        <td :class="changeBackground(math[3].score)">
          <input type="radio"
                  v-for="k in scores"
                  :key="k"
                  :value="k.toUpperCase()"
                  :id="`math-second-second-${k}`"
                  v-model="math[3].score"
                  :checked="math[3].decided"
          >
          <label v-for="(k, index) in scores"
                  :key="index"
                  :for="`math-second-second-${k}`"
                  class="input-grade-label"
                  @click="changeDecided($event, math[3])"
                  :class="complete(math[3].score, k.toUpperCase())"
          >
            {{ k.toUpperCase() }}
          </label>
          <div class="button-side-line" v-show="!math[3].score"></div>
          <input type="radio"
                  value="X"
                  id="math-second-second-null"
                  v-model="math[3].score"
                  :checked="math[3].decided"
          >
          <label for="math-second-second-null"
                  class="input-grade-label"
                  @click="changeDecided($event, math[3])"
                  :class="discomplete(math[3].score)"
          >X</label>
        </td>
        <td :class="changeBackground(math[4].score)">
          <input type="radio"
                  v-for="k in scores"
                  :key="k"
                  :value="k.toUpperCase()"
                  :id="`math-third-first-${k}`"
                  v-model="math[4].score"
                  :checked="math[4].decided"
          >
          <label v-for="(k, index) in scores"
                  :key="index"
                  :for="`math-third-first-${k}`"
                  class="input-grade-label"
                  @click="changeDecided($event, math[4])"
                  :class="complete(math[4].score, k.toUpperCase())"
          >
            {{ k.toUpperCase() }}
          </label>
          <div class="button-side-line" v-show="!math[4].score"></div>
          <input type="radio"
                  value="X"
                  id="math-third-first-null"
                  v-model="math[4].score"
                  :checked="math[4].decided"
          >
          <label for="math-third-first-null"
                  class="input-grade-label"
                  @click="changeDecided($event, math[4])"
                  :class="discomplete(math[4].score)"
          >X</label>
        </td>
        <td :class="changeBackground(math[5].score)">
          <input type="radio"
                  v-for="k in scores"
                  :key="k"
                  :value="k.toUpperCase()"
                  :id="`math-third-second-${k}`"
                  v-model="math[5].score"
                  :checked="math[5].decided"
          >
          <label v-for="(k, index) in scores"
                  :key="index"
                  :for="`math-third-second-${k}`"
                  class="input-grade-label"
                  @click="changeDecided($event, math[5])"
                  :class="complete(math[5].score, k.toUpperCase())"
          >
            {{ k.toUpperCase() }}
          </label>
          <div class="button-side-line" v-show="!math[5].score"></div>
          <input type="radio"
                  value="X"
                  id="math-third-second-null"
                  v-model="math[5].score"
                  :checked="math[5].decided"
          >
          <label for="math-third-second-null"
                  class="input-grade-label"
                  @click="changeDecided($event, math[5])"
                  :class="discomplete(math[5].score)"
          >X</label>
        </td>
      </tr>
      <tr class="grade-input-table__row">
        <td>과학</td>
        <td :class="changeBackground(science[0].score)">
          <input type="radio"
                  v-for="k in scores"
                  :key="k"
                  :value="k.toUpperCase()"
                  :id="`science-first-first-${k}`"
                  v-model="science[0].score"
                  :checked="science[0].decided"
          >
          <label v-for="(k, index) in scores"
                  :key="index"
                  :for="`science-first-first-${k}`"
                  class="input-grade-label"
                  @click="changeDecided($event, science[0])"
                  :class="complete(science[0].score, k.toUpperCase())"
          >
            {{ k.toUpperCase() }}
          </label>
          <div class="button-side-line" v-show="!science[0].score"></div>
          <input type="radio"
                  value="X"
                  id="science-first-first-null"
                  v-model="science[0].score"
                  :checked="science[0].decided"
          >
          <label for="science-first-first-null"
                  class="input-grade-label"
                  @click="changeDecided($event, science[0])"
                  :class="discomplete(science[0].score)"
          >X</label>
        </td>
        <td :class="changeBackground(science[1].score)">
          <input type="radio"
                  v-for="k in scores"
                  :key="k"
                  :value="k.toUpperCase()"
                  :id="`science-first-second-${k}`"
                  v-model="science[1].score"
                  :checked="science[1].decided"
          >
          <label v-for="(k, index) in scores"
                  :key="index"
                  :for="`science-first-second-${k}`"
                  class="input-grade-label"
                  @click="changeDecided($event, science[1])"
                  :class="complete(science[1].score, k.toUpperCase())"
          >
            {{ k.toUpperCase() }}
          </label>
          <div class="button-side-line" v-show="!science[1].score"></div>
          <input type="radio"
                  value="X"
                  id="science-first-second-null"
                  v-model="science[1].score"
                  :checked="science[1].decided"
          >
          <label for="science-first-second-null"
                  class="input-grade-label"
                  @click="changeDecided($event, science[1])"
                  :class="discomplete(science[1].score)"
          >X</label>
        </td>
        <td :class="changeBackground(science[2].score)">
          <input type="radio"
                  v-for="k in scores"
                  :key="k"
                  :value="k.toUpperCase()"
                  :id="`science-second-first-${k}`"
                  v-model="science[2].score"
                  :checked="science[2].decided"
          >
          <label v-for="(k, index) in scores"
                  :key="index"
                  :for="`science-second-first-${k}`"
                  class="input-grade-label"
                  @click="changeDecided($event, science[2])"
                  :class="complete(science[2].score, k.toUpperCase())"
          >
            {{ k.toUpperCase() }}
          </label>
          <div class="button-side-line" v-show="!science[2].score"></div>
          <input type="radio"
                  value="X"
                  id="science-second-first-null"
                  v-model="science[2].score"
                  :checked="science[2].decided"
          >
          <label for="science-second-first-null"
                  class="input-grade-label"
                  @click="changeDecided($event, science[2])"
                  :class="discomplete(science[2].score)"
          >X</label>
        </td>
        <td :class="changeBackground(science[3].score)">
          <input type="radio"
                  v-for="k in scores"
                  :key="k"
                  :value="k.toUpperCase()"
                  :id="`science-second-second-${k}`"
                  v-model="science[3].score"
                  :checked="science[3].decided"
          >
          <label v-for="(k, index) in scores"
                  :key="index"
                  :for="`science-second-second-${k}`"
                  class="input-grade-label"
                  @click="changeDecided($event, science[3])"
                  :class="complete(science[3].score, k.toUpperCase())"
          >
            {{ k.toUpperCase() }}
          </label>
          <div class="button-side-line" v-show="!science[3].score"></div>
          <input type="radio"
                  value="X"
                  id="science-second-second-null"
                  v-model="science[3].score"
                  :checked="science[3].decided"
          >
          <label for="science-second-second-null"
                  class="input-grade-label"
                  @click="changeDecided($event, science[3])"
                  :class="discomplete(science[3].score)"
          >X</label>
        </td>
        <td :class="changeBackground(science[4].score)">
          <input type="radio"
                  v-for="k in scores"
                  :key="k"
                  :value="k.toUpperCase()"
                  :id="`science-third-first-${k}`"
                  v-model="science[4].score"
                  :checked="science[4].decided"
          >
          <label v-for="(k, index) in scores"
                  :key="index"
                  :for="`science-third-first-${k}`"
                  class="input-grade-label"
                  @click="changeDecided($event, science[4])"
                  :class="complete(science[4].score, k.toUpperCase())"
          >
            {{ k.toUpperCase() }}
          </label>
          <div class="button-side-line" v-show="!science[4].score"></div>
          <input type="radio"
                  value="X"
                  id="science-third-first-null"
                  v-model="science[4].score"
                  :checked="science[4].decided"
          >
          <label for="science-third-first-null"
                  class="input-grade-label"
                  @click="changeDecided($event, science[4])"
                  :class="discomplete(science[4].score)"
          >X</label>
        </td>
        <td :class="changeBackground(science[5].score)">
          <input type="radio"
                  v-for="k in scores"
                  :key="k"
                  :value="k.toUpperCase()"
                  :id="`science-third-second-${k}`"
                  v-model="science[5].score"
                  :checked="science[5].decided"
          >
          <label v-for="(k, index) in scores"
                  :key="index"
                  :for="`science-third-second-${k}`"
                  class="input-grade-label"
                  @click="changeDecided($event, science[5])"
                  :class="complete(science[5].score, k.toUpperCase())"
          >
            {{ k.toUpperCase() }}
          </label>
          <div class="button-side-line" v-show="!science[5].score"></div>
          <input type="radio"
                  value="X"
                  id="science-third-second-null"
                  v-model="science[5].score"
                  :checked="science[5].decided"
          >
          <label for="science-third-second-null"
                  class="input-grade-label"
                  @click="changeDecided($event, science[5])"
                  :class="discomplete(science[5].score)"
          >X</label>
        </td>
      </tr>
      <tr class="grade-input-table__row">
        <td>기술・가정</td>
        <td :class="changeBackground(tech[0].score)">
          <input type="radio"
                  v-for="k in scores"
                  :key="k"
                  :value="k.toUpperCase()"
                  :id="`tech-home-first-first-${k}`"
                  v-model="tech[0].score"
                  :checked="tech[0].decided"
          >
          <label v-for="(k, index) in scores"
                  :key="index"
                  :for="`tech-home-first-first-${k}`"
                  class="input-grade-label"
                  @click="changeDecided($event, tech[0])"
                  :class="complete(tech[0].score, k.toUpperCase())"
          >
            {{ k.toUpperCase() }}
          </label>
          <div class="button-side-line" v-show="!tech[0].score"></div>
          <input type="radio"
                  value="X"
                  id="tech-home-first-first-null"
                  v-model="tech[0].score"
                  :checked="tech[0].decided"
          >
          <label for="tech-home-first-first-null"
                  class="input-grade-label"
                  @click="changeDecided($event, tech[0])"
                  :class="discomplete(tech[0].score)"
          >X</label>
        </td>
        <td :class="changeBackground(tech[1].score)">
          <input type="radio"
                  v-for="k in scores"
                  :key="k"
                  :value="k.toUpperCase()"
                  :id="`tech-home-first-second-${k}`"
                  v-model="tech[1].score"
                  :checked="tech[1].decided"
          >
          <label v-for="(k, index) in scores"
                  :key="index"
                  :for="`tech-home-first-second-${k}`"
                  class="input-grade-label"
                  @click="changeDecided($event, tech[1])"
                  :class="complete(tech[1].score, k.toUpperCase())"
          >
            {{ k.toUpperCase() }}
          </label>
          <div class="button-side-line" v-show="!tech[1].score"></div>
          <input type="radio"
                  value="X"
                  id="tech-home-first-second-null"
                  v-model="tech[1].score"
                  :checked="tech[1].decided"
          >
          <label for="tech-home-first-second-null"
                  class="input-grade-label"
                  @click="changeDecided($event, tech[1])"
                  :class="discomplete(tech[1].score)"
          >X</label>
        </td>
        <td :class="changeBackground(tech[2].score)">
          <input type="radio"
                  v-for="k in scores"
                  :key="k"
                  :value="k.toUpperCase()"
                  :id="`tech-home-second-first-${k}`"
                  v-model="tech[2].score"
                  :checked="tech[2].decided"
          >
          <label v-for="(k, index) in scores"
                  :key="index"
                  :for="`tech-home-second-first-${k}`"
                  class="input-grade-label"
                  @click="changeDecided($event, tech[2])"
                  :class="complete(tech[2].score, k.toUpperCase())"
          >
            {{ k.toUpperCase() }}
          </label>
          <div class="button-side-line" v-show="!tech[2].score"></div>
          <input type="radio"
                  value="X"
                  id="tech-home-second-first-null"
                  v-model="tech[2].score"
                  :checked="tech[2].decided"
          >
          <label for="tech-home-second-first-null"
                  class="input-grade-label"
                  @click="changeDecided($event, tech[2])"
                  :class="discomplete(tech[2].score)"
          >X</label>
        </td>
        <td :class="changeBackground(tech[3].score)">
          <input type="radio"
                  v-for="k in scores"
                  :key="k"
                  :value="k.toUpperCase()"
                  :id="`tech-home-second-second-${k}`"
                  v-model="tech[3].score"
                  :checked="tech[3].decided"
          >
          <label v-for="(k, index) in scores"
                  :key="index"
                  :for="`tech-home-second-second-${k}`"
                  class="input-grade-label"
                  @click="changeDecided($event, tech[3])"
                  :class="complete(tech[3].score, k.toUpperCase())"
          >
            {{ k.toUpperCase() }}
          </label>
          <div class="button-side-line" v-show="!tech[3].score"></div>
          <input type="radio"
                  value="X"
                  id="tech-home-second-second-null"
                  v-model="tech[3].score"
                  :checked="tech[3].decided"
          >
          <label for="tech-home-second-second-null"
                  class="input-grade-label"
                  @click="changeDecided($event, tech[3])"
                  :class="discomplete(tech[3].score)"
          >X</label>
        </td>
        <td :class="changeBackground(tech[4].score)">
          <input type="radio"
                  v-for="k in scores"
                  :key="k"
                  :value="k.toUpperCase()"
                  :id="`tech-home-third-first-${k}`"
                  v-model="tech[4].score"
                  :checked="tech[4].decided"
          >
          <label v-for="(k, index) in scores"
                  :key="index"
                  :for="`tech-home-third-first-${k}`"
                  class="input-grade-label"
                  @click="changeDecided($event, tech[4])"
                  :class="complete(tech[4].score, k.toUpperCase())"
          >
            {{ k.toUpperCase() }}
          </label>
          <div class="button-side-line" v-show="!tech[4].score"></div>
          <input type="radio"
                  value="X"
                  id="tech-home-third-first-null"
                  v-model="tech[4].score"
                  :checked="tech[4].decided"
          >
          <label for="tech-home-third-first-null"
                  class="input-grade-label"
                  @click="changeDecided($event, tech[4])"
                  :class="discomplete(tech[4].score)"
          >X</label>
        </td>
        <td :class="changeBackground(tech[5].score)">
          <input type="radio"
                  v-for="k in scores"
                  :key="k"
                  :value="k.toUpperCase()"
                  :id="`tech-home-third-second-${k}`"
                  v-model="tech[5].score"
                  :checked="tech[5].decided"
          >
          <label v-for="(k, index) in scores"
                  :key="index"
                  :for="`tech-home-third-second-${k}`"
                  class="input-grade-label"
                  @click="changeDecided($event, tech[5])"
                  :class="complete(tech[5].score, k.toUpperCase())"
          >
            {{ k.toUpperCase() }}
          </label>
          <div class="button-side-line" v-show="!tech[5].score"></div>
          <input type="radio"
                  value="X"
                  id="tech-home-third-second-null"
                  v-model="tech[5].score"
                  :checked="tech[5].decided"
          >
          <label for="tech-home-third-second-null"
                  class="input-grade-label"
                  @click="changeDecided($event, tech[5])"
                  :class="discomplete(tech[5].score)"
          >X</label>
        </td>
      </tr>
      <tr class="grade-input-table__row">
        <td>영어</td>
        <td :class="changeBackground(english[0].score)">
          <input type="radio"
                  v-for="k in scores"
                  :key="k"
                  :value="k.toUpperCase()"
                  :id="`english-first-first-${k}`"
                  v-model="english[0].score"
                  :checked="english[0].decided"
          >
          <label v-for="(k, index) in scores"
                  :key="index"
                  :for="`english-first-first-${k}`"
                  class="input-grade-label"
                  @click="changeDecided($event, english[0])"
                  :class="complete(english[0].score, k.toUpperCase())"
          >
            {{ k.toUpperCase() }}
          </label>
          <div class="button-side-line" v-show="!english[0].score"></div>
          <input type="radio"
                  value="X"
                  id="english-first-first-null"
                  v-model="english[0].score"
                  :checked="english[0].decided"
          >
          <label for="english-first-first-null"
                  class="input-grade-label"
                  @click="changeDecided($event, english[0])"
                  :class="discomplete(english[0].score)"
          >X</label>
        </td>
        <td :class="changeBackground(english[1].score)">
          <input type="radio"
                  v-for="k in scores"
                  :key="k"
                  :value="k.toUpperCase()"
                  :id="`english-first-second-${k}`"
                  v-model="english[1].score"
                  :checked="english[1].decided"
          >
          <label v-for="(k, index) in scores"
                  :key="index"
                  :for="`english-first-second-${k}`"
                  class="input-grade-label"
                  @click="changeDecided($event, english[1])"
                  :class="complete(english[1].score, k.toUpperCase())"
          >
            {{ k.toUpperCase() }}
          </label>
          <div class="button-side-line" v-show="!english[1].score"></div>
          <input type="radio"
                  value="X"
                  id="english-first-second-null"
                  v-model="english[1].score"
                  :checked="english[1].decided"
          >
          <label for="english-first-second-null"
                  class="input-grade-label"
                  @click="changeDecided($event, english[1])"
                  :class="discomplete(english[1].score)"
          >X</label>
        </td>
        <td :class="changeBackground(english[2].score)">
          <input type="radio"
                  v-for="k in scores"
                  :key="k"
                  :value="k.toUpperCase()"
                  :id="`english-second-first-${k}`"
                  v-model="english[2].score"
                  :checked="english[2].decided"
          >
          <label v-for="(k, index) in scores"
                  :key="index"
                  :for="`english-second-first-${k}`"
                  class="input-grade-label"
                  @click="changeDecided($event, english[2])"
                  :class="complete(english[2].score, k.toUpperCase())"
          >
            {{ k.toUpperCase() }}
          </label>
          <div class="button-side-line" v-show="!english[2].score"></div>
          <input type="radio"
                  value="X"
                  id="english-second-first-null"
                  v-model="english[2].score"
                  :checked="english[2].decided"
          >
          <label for="english-second-first-null"
                  class="input-grade-label"
                  @click="changeDecided($event, english[2])"
                  :class="discomplete(english[2].score)"
          >X</label>
        </td>
        <td :class="changeBackground(english[3].score)">
          <input type="radio"
                  v-for="k in scores"
                  :key="k"
                  :value="k.toUpperCase()"
                  :id="`english-second-second-${k}`"
                  v-model="english[3].score"
                  :checked="english[3].decided"
          >
          <label v-for="(k, index) in scores"
                  :key="index"
                  :for="`english-second-second-${k}`"
                  class="input-grade-label"
                  @click="changeDecided($event, english[3])"
                  :class="complete(english[3].score, k.toUpperCase())"
          >
            {{ k.toUpperCase() }}
          </label>
          <div class="button-side-line" v-show="!english[3].score"></div>
          <input type="radio"
                  value="X"
                  id="english-second-second-null"
                  v-model="english[3].score"
                  :checked="english[3].decided"
          >
          <label for="english-second-second-null"
                  class="input-grade-label"
                  @click="changeDecided($event, english[3])"
                  :class="discomplete(english[3].score)"
          >X</label>
        </td>
        <td :class="changeBackground(english[4].score)">
          <input type="radio"
                  v-for="k in scores"
                  :key="k"
                  :value="k.toUpperCase()"
                  :id="`english-third-first-${k}`"
                  v-model="english[4].score"
                  :checked="english[4].decided"
          >
          <label v-for="(k, index) in scores"
                  :key="index"
                  :for="`english-third-first-${k}`"
                  class="input-grade-label"
                  @click="changeDecided($event, english[4])"
                  :class="complete(english[4].score, k.toUpperCase())"
          >
            {{ k.toUpperCase() }}
          </label>
          <div class="button-side-line" v-show="!english[4].score"></div>
          <input type="radio"
                  value="X"
                  id="english-third-first-null"
                  v-model="english[4].score"
                  :checked="english[4].decided"
          >
          <label for="english-third-first-null"
                  class="input-grade-label"
                  @click="changeDecided($event, english[4])"
                  :class="discomplete(english[4].score)"
          >X</label>
        </td>
        <td :class="changeBackground(english[5].score)">
          <input type="radio"
                  v-for="k in scores"
                  :key="k"
                  :value="k.toUpperCase()"
                  :id="`english-third-second-${k}`"
                  v-model="english[5].score"
                  :checked="english[5].decided"
          >
          <label v-for="(k, index) in scores"
                  :key="index"
                  :for="`english-third-second-${k}`"
                  class="input-grade-label"
                  @click="changeDecided($event, english[5])"
                  :class="complete(english[5].score, k.toUpperCase())"
          >
            {{ k.toUpperCase() }}
          </label>
          <div class="button-side-line" v-show="!english[5].score"></div>
          <input type="radio"
                  value="X"
                  id="english-third-second-null"
                  v-model="english[5].score"
                  :checked="english[5].decided"
          >
          <label for="english-third-second-null"
                  class="input-grade-label"
                  @click="changeDecided($event, english[5])"
                  :class="discomplete(english[5].score)"
          >X</label>
        </td>
      </tr>
    </table>
  </div>
</template>

<script>
export default {
  name: 'normal-grade-input-graduated',
  data() {
    return {
      // Hover 체크
      checkHover: false,

      // 미이수 학기 선택
      semesters: {
        firstFirstSemester: false,
        firstSecondSemester: false,
        secondFirstSemester: false,
        secondSecondSemester: false,
        thirdFirstSemester: false,
        thirdSecondSemester: false,
      },

      // 모든 학기 초기화
      resetAllGrade: '',

      // 초기화 점수
      scores: ['a', 'b', 'c', 'd', 'e'],

      prevLink: '/personal',
      nextLink: '/intro',
    };
  },
  computed: {
    volunteerNAttendance: {
      get() {
        return this.$store.getters.selectType.volunteerNAttendance;
      },
    },

    // score : "A", "B", "C", "D", "E", "X"(미이수), null (씨발련이 입력 안함)
    korean: {
      get() {
        return this.$store.getters.selectType.korean;
      },
    },
    social: {
      get() {
        return this.$store.getters.selectType.social;
      },
    },
    history: {
      get() {
        return this.$store.getters.selectType.history;
      },
    },
    math: {
      get() {
        return this.$store.getters.selectType.math;
      },
    },
    science: {
      get() {
        return this.$store.getters.selectType.science;
      },
    },
    tech: {
      get() {
        return this.$store.getters.selectType.tech;
      },
    },
    english: {
      get() {
        return this.$store.getters.selectType.english;
      },
    },
    grades: {
      get() {
        return this.$store.getters.selectType.grades;
      },
    },
  },
  created() {
    const token = this.$cookies.get('accessToken');
    const { e } = this.$toastr;
    if (token === undefined || token === null || token === '') {
      this.$router.push('/');
      e('해당 페이지는 로그인이 필요합니다.');
      this.$store.commit('changeIndex', {
        index: 1,
      });
    }
  },
  methods: {
    // 봉사 및 출석 Commit - 완료
    updateVolunteerNAttendance(field, value) {
      this.$store.commit('updateVolunteerNAttendance', {
        field,
        value,
      });
    },

    // 전체 성적 기능
    setHoverGrade(grade) {
      this.checkHover = true;
      this.resetAllGrade = grade.toUpperCase();
    },

    // 초기화 버튼 설정
    setButton(t) {
      this.resetAllGrade = t.textContent.trim();
    },

    // 실제 점수 초기화
    setGrades() {
      this.$store.commit('updateGrades', {
        grades: this.grades,
        resetAllGrade: this.resetAllGrade,
      });
    },

    // 미이수 체크 해제
    resetDiscomplete() {
      const s = this.semesters;
      s.firstFirstSemester = false;
      s.firstSecondSemester = false;
      s.secondFirstSemester = false;
      s.secondSecondSemester = false;
      s.thirdFirstSemester = false;
    },

    resetGrade({ target }) {
      this.resetDiscomplete();
      this.setButton(target);
      this.setGrades();
    },

    // 미이수 설정 - 완료
    discompleteSemester({ target }) {
      this.$store.commit('updateDiscompleteSemester', {
        grades: this.grades,
        target,
      });
    },

    // 점수를 눌렀을 시의 이벤트
    changeDecided({ target }, val) {
      this.$nextTick(() => {
        this.$store.commit('updateChangeDecided', {
          target,
          val,
        });
      });
    },

    // 애니메이션을 위한 Class Binding
    complete(current, compare) {
      return {
        decide: current !== compare && current !== '',
        underline: current !== '',
      };
    },

    discomplete(current) {
      return {
        decide: current !== 'X' && current !== '',
        underline: current !== '',
      };
    },

    changeBackground(current) {
      return {
        background: current === 'X',
      };
    },

    // 전체 성적 Hover를 위한 Class Binding
    allHoverCheck() {
      return {
        'all-hover': this.checkHover,
      };
    },

    // 봉사 및 출석 Input Value 체크
    onlyNumber(e) {
      if (!(e.keyCode >= 48 && e.keyCode <= 57)) {
        switch (e.key) {
          case 'Backspace':
          case 'ArrowLeft':
          case 'ArrowRight':
          case 'Delete': break;
          default: e.preventDefault();
        }
      }
    },
  },
};
</script>

<style lang="scss" scoped>
@import '../../style/setting';

$table-border: #769b9f;
$table-inner: #f2f2f2;
$input-color: #5f8a90;
$table-background: #f7fbfc;
$button-color: #edf5f6;

.grade-input-cover {
  width: 1140px;
  margin: 0 auto;
  position: relative;

  h3 {
    font-size: 22px;
    font-weight: 400;
    margin-top: 55px;

    &:first-of-type {
      margin-top: 0;
    }
  }

  input[type=radio] {
    display: none;
  }

  button, .input-grade-label {
    border-radius: 50%;
    background-color: transparent;
    border: 0;
    cursor: pointer;
    outline: none;
    transition: background-color 0.3s;

    &:hover {
      background-color: $button-color;
    }
  }

  button {
    width: 20px;
    height: 20px;
    font-size: 16px;
  }

  .input-grade-label {
    width: 21px;
    height: 21px;
    font-size: 18px;
    top: 50%;
    transform: translateY(-50%);
    display: flex;
    align-items: center;
    justify-content: center;
    position: absolute;
    left: 10px;
    transition: 0.35s ease-in-out;
    border-style: solid;
    border-color: #cfdee0;

    @for $i from 1 through 4 {
      &:nth-of-type(#{$i + 1}) {
        left: calc(10px + (22px * #{$i}));
      }
    }
    &:nth-of-type(6) {
      left: 126px;
    }
  }

  table {
    width: 100%;
    position: relative;
    margin-top: 20px;

    &::before, &::after {
      content: '';
      display: block;
      width: 112%;
      height: 1px;
      position: absolute;
      left: 50%;
      transform: translateX(-50%);
      background: linear-gradient(left, transparent 0%,
      $table-border 15%, $table-border 85%, transparent 100%);
      z-index: 99;
    }

    &::before { top: 0; }
    &::after { bottom: 0; }
  }

  .volunteer-attendance-table {
    height: 225px;

    .table-row {
      display: flex;
      align-items: center;
      font-size: 20px;

      &:first-of-type {
        height: 74px;
        border-bottom: 1px solid $table-inner;
      }
      &:nth-of-type(2) {
        height: 149px;
      }

      .table-data-text {
        width: calc(100% - 148px);
        height: 100%;
        display: flex;
        align-items: center;

        &:nth-of-type(2n-1) {
          width: 148px;
          justify-content: center;
          border-right: 1px solid $table-inner;
        }

        @include e('input') {
          width: 75px;
          margin-left: 28px;
        }

        @include e('time') {
          font-size: 18px;
          padding-left: 11px;
        }

        @include e('row') {
          display: block;
          width: 100%;
          height: 50%;
          border-bottom: 1px solid $table-inner;

          @include e('data') {
            display: inline-flex;
            width: 25%;
            height: 100%;
            align-items: center;
            justify-content: center;
            float: left;
            font-size: 20px;
            box-sizing: border-box;
            border-right: 1px solid $table-inner;

            &:nth-of-type(2n) {
              justify-content: flex-start;
            }

            &:nth-of-type(4n) {
              border-right: 0;
            }
          }
        }

        @include m('attendance-info') {
          display: block;
        }
      }
    }
  }

  .discomplete-table {
    width: 100%;
    height: 150px;

    .table-row {
      height: calc(100% / 2);

      &:first-of-type {
        background: linear-gradient(left, transparent 0%,
        $table-background 10%, $table-background 90%, transparent 100%);
      }

      & > td {
        display: inline-flex;
        justify-content: center;
        align-items: center;
        height: 100%;
        font-size: 20px;
        box-sizing: border-box;
      }

      .grade-names {
        width: calc(100% / 3);
      }

      @include e('first-grade') {
        border-right: 1px solid $table-inner;
      }

      @include e('second-grade') {
        border-right: 1px solid $table-inner;
      }

      .semester-checkboxes {
        width: calc(100% / 6);
        height: 100%;

        &:nth-of-type(n) {
          border-right: 1px solid $table-inner;
        }

        &:last-of-type {
          border-right: 0;
        }
      }

      @include e('semester-text') {
        font-size: 18px;
        padding-left: 7px;
      }

      .input-checkbox-label {
        margin-top: -7px;
      }
    }
  }

  .grade-input-table {
    height: 675px;

    .all-grade-reset-cover {
      width: 250px;
      height: 60px;
      box-sizing: border-box;
      position: absolute;
      right: 0;
      margin-top: -76px;

      @include e('box') {
        width: 200px;
        height: 30px;
        border: 1px solid $input-color;
        border-radius: 5px;
        display: flex;
        align-items: center;
        justify-content: center;
        box-sizing: border-box;
        float: right;
        opacity: 0;
        transition: opacity .4s;

        @include e('text') {
          font-size: 12px;
          color: #26484c;
        }
      }

      // 전체 선택 Class Binding
      .all-hover {
        opacity: 1;
      }

      @include e('reset') {
        margin-top: 13px;
        display: flex;
        align-items: center;
        justify-content: flex-end;
        float: right;

        @include e('text') {
          font-size: 16px;
        }

        button {
          margin-left: 1px;

          &:last-of-type {
            margin-right: -5px;
          }
        }
      }
    }

    @include e('row') {
      width: 100%;
      height: 75px;

      &:first-of-type {
        height: 75px;
        font-size: 20px;
        background-color: $table-background;
      }

      &:nth-of-type(2) {
        height: 73px;
        background-color: $table-background;
        border-bottom: 1px solid $table-inner;

        td {
          border-right: 1px solid $table-inner;
          &:last-of-type { border-right: 0; }
          font-size: 20px;
        }
      }

      td {
        width: calc(977px / 6);
        vertical-align: middle;
        text-align: center;
        font-size: 18px;
        transition: 0.4s;
        position: relative;

        border: {
          right: 1px solid $table-inner;
          bottom: 1px solid $table-inner;
        }

        &:nth-of-type(1) {
          width: 163px !important;
          background-color: $table-background;
          font-size: 20px;
          border: {
            top: 1px solid $table-inner;
            right: 1px solid $table-inner;
          }
        }

        &:last-of-type { border-right: 0; }

        &.empty-space {
          border: {
            top: 0;
            bottom: 0;
          }
        }
        &.first-grade,
        &.second-grade { border-right: 1px solid $table-inner; }
        &.third-grade { border-right: 0; }
        &.row-grades {
          border-bottom: 1px solid $table-inner;
          font-size: 20px;
        }
      }

      // 버튼 스타일
      input[value="X"] + label{
        margin-left: 2px;

        &::before {
          content: '';
          position: absolute;
          width: 125px;
          height: 30px;
          background-image: url(../../assets/GradeInput/no-score.png);
          background-size: cover;
          opacity: 0;
          visibility: hidden;
          transition: opacity 0.3s;
          margin: {
            left: -1px;
            top: -30px;
          }
        }

        &:hover::before {
          opacity: 1;
          visibility: visible;
        }
      }

      .no-score-img {
        width: 125px;
        height: 30px;
        object-fit: cover;
        opacity: 1;
        transition: opacity 0.3s;
      }

      .button-side-line {
        width: 2px;
        height: 18px;
        background-color: #cfdee0;
        position: absolute;
        top: 50%;
        margin-top: -1px;
        transform: translateY(-50%);
        right: 35px;
      }

      // 점수 선택되었을 시의 애니메이션
      .decide {
        left: 50%;
        opacity: 0;
        visibility: hidden;
      }

      .underline {
        border-bottom: 1px solid #cfdee0;
        border-radius: 0;
        left: 50%;
        transform: translate(-50%, -50%);

        &:hover {
          border-radius: 50%;
        }
      }

      .background {
        background-color: $table-background;
      }
    }
  }
}
</style>
