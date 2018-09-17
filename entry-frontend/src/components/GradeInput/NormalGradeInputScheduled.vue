<template>
  <div>
    <navigation />
    <headline :subText="'2019 입학원서 작성'" title="성적 입력" />
    <div class="grade-input-cover">
      <h3>봉사 & 출석</h3>
      <table class="volunteer-attendance-table">
        <tr class="table-row">
          <td class="table-data-text">봉사시간</td>
          <td class="table-data-text">
            <input type="number"
                   v-model.number="volunteerNAttendance.volunteer"
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
                       v-model.number="volunteerNAttendance.absence"
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
                       v-model.number="volunteerNAttendance.lateness"
                       @keydown="onlyNumber"
                       min="0"
                       class="input-text table-data-text__input"
                >
                <span class="table-data-text__time">일</span>
              </td>
              <td class="table-data-text__row__data">전체 무단 결과 일수</td>
              <td class="table-data-text__row__data">
                <input type="number"
                       v-model.number="volunteerNAttendance.missingClass"
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

      <h3>미이수(자유) 학기 선택</h3>
      <table class="discomplete-table">
        <tr class="table-row">
          <td class="table-row__first-grade">1학년</td>
          <td class="table-row__second-grade">2학년</td>
          <td class="table-row__third-grade">3학년</td>
        </tr>
        <tr class="table-row">
          <td class="table-row__first-semester">
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
          <td class="table-row__first-semester">
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
          <td class="table-row__second-semester">
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
          <td class="table-row__second-semester">
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
          <td class="table-row__third-semester">
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
        </tr>
      </table>

      <h3>성적입력</h3>
      <table class="grade-input-table" ref="grade-table">
        <div class="all-grade-reset-cover">
          <div class="all-grade-reset-cover__box">
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
            >
              {{ reset.toUpperCase() }}
            </button>
          </div>
        </div>
        <tr class="grade-input-table__row">
          <td class="empty-space"></td>
          <td class="first-grade row-grades" colspan="2">1학년</td>
          <td class="second-grade row-grades" colspan="2">2학년</td>
          <td class="third-grade row-grades">3학년</td>
        </tr>
        <tr class="grade-input-table__row">
          <td class="empty-space"></td>
          <td>1학기</td>
          <td>2학기</td>
          <td>1학기</td>
          <td>2학기</td>
          <td>1학기</td>
        </tr>
        <tr class="grade-input-table__row">
          <td>국어</td>
          <td :class="changeBackground(koreanScores[0].score)">
            <input type="radio"
                   v-for="k in scores"
                   :key="k"
                   :value="k.toUpperCase()"
                   :id="`korean-first-first-${k}`"
                   v-model="koreanScores[0].score"
                   :checked="koreanScores[0].decided"
            >
            <label v-for="(k, index) in scores"
                   :key="index"
                   :for="`korean-first-first-${k}`"
                   class="input-grade-label"
                   @click="changeDecided($event, koreanScores[0])"
                   :class="complete(koreanScores[0].score, k.toUpperCase())"
            >
              {{ k.toUpperCase() }}
            </label>
            <div class="button-side-line" v-show="!koreanScores[0].score"></div>
            <input type="radio"
                   value="X"
                   id="korean-first-first-null"
                   v-model="koreanScores[0].score"
                   :checked="koreanScores[0].decided"
            >
            <label for="korean-first-first-null"
                   class="input-grade-label"
                   @click="changeDecided($event, koreanScores[0])"
                   :class="discomplete(koreanScores[0].score)"
            >X</label>
          </td>
          <td :class="changeBackground(koreanScores[1].score)">
            <input type="radio"
                   v-for="k in scores"
                   :key="k"
                   :value="k.toUpperCase()"
                   :id="`korean-first-second-${k}`"
                   v-model="koreanScores[1].score"
                   :checked="koreanScores[1].decided"
            >
            <label v-for="(k, index) in scores"
                   :key="index"
                   :for="`korean-first-second-${k}`"
                   class="input-grade-label"
                   @click="changeDecided($event, koreanScores[1])"
                   :class="complete(koreanScores[1].score, k.toUpperCase())"
            >
              {{ k.toUpperCase() }}
            </label>
            <div class="button-side-line" v-show="!koreanScores[1].score"></div>
            <input type="radio"
                   value="X"
                   id="korean-first-second-null"
                   v-model="koreanScores[1].score"
                   :checked="koreanScores[1].decided"
            >
            <label for="korean-first-second-null"
                   class="input-grade-label"
                   @click="changeDecided($event, koreanScores[1])"
                   :class="discomplete(koreanScores[1].score)"
            >X</label>
          </td>
          <td :class="changeBackground(koreanScores[2].score)">
            <input type="radio"
                   v-for="k in scores"
                   :key="k"
                   :value="k.toUpperCase()"
                   :id="`korean-second-first-${k}`"
                   v-model="koreanScores[2].score"
                   :checked="koreanScores[2].decided"
            >
            <label v-for="(k, index) in scores"
                   :key="index"
                   :for="`korean-second-first-${k}`"
                   class="input-grade-label"
                   @click="changeDecided($event, koreanScores[2])"
                   :class="complete(koreanScores[2].score, k.toUpperCase())"
            >
              {{ k.toUpperCase() }}
            </label>
            <div class="button-side-line" v-show="!koreanScores[2].score"></div>
            <input type="radio"
                   value="X"
                   id="korean-second-first-null"
                   v-model="koreanScores[2].score"
                   :checked="koreanScores[2].decided"
            >
            <label for="korean-second-first-null"
                   class="input-grade-label"
                   @click="changeDecided($event, koreanScores[2])"
                   :class="discomplete(koreanScores[2].score)"
            >X</label>
          </td>
          <td :class="changeBackground(koreanScores[3].score)">
            <input type="radio"
                   v-for="k in scores"
                   :key="k"
                   :value="k.toUpperCase()"
                   :id="`korean-second-second-${k}`"
                   v-model="koreanScores[3].score"
                   :checked="koreanScores[3].decided"
            >
            <label v-for="(k, index) in scores"
                   :key="index"
                   :for="`korean-second-second-${k}`"
                   class="input-grade-label"
                   @click="changeDecided($event, koreanScores[3])"
                   :class="complete(koreanScores[3].score, k.toUpperCase())"
            >
              {{ k.toUpperCase() }}
            </label>
            <div class="button-side-line" v-show="!koreanScores[3].score"></div>
            <input type="radio"
                   value="X"
                   id="korean-second-second-null"
                   v-model="koreanScores[3].score"
                   :checked="koreanScores[3].decided"
            >
            <label for="korean-second-second-null"
                   class="input-grade-label"
                   @click="changeDecided($event, koreanScores[3])"
                   :class="discomplete(koreanScores[3].score)"
            >X</label>
          </td>
          <td :class="changeBackground(koreanScores[4].score)">
            <input type="radio"
                   v-for="k in scores"
                   :key="k"
                   :value="k.toUpperCase()"
                   :id="`korean-third-first-${k}`"
                   v-model="koreanScores[4].score"
                   :checked="koreanScores[4].decided"
            >
            <label v-for="(k, index) in scores"
                   :key="index"
                   :for="`korean-third-first-${k}`"
                   class="input-grade-label"
                   @click="changeDecided($event, koreanScores[4])"
                   :class="complete(koreanScores[4].score, k.toUpperCase())"
            >
              {{ k.toUpperCase() }}
            </label>
            <div class="button-side-line" v-show="!koreanScores[4].score"></div>
            <input type="radio"
                   value="X"
                   id="korean-third-first-null"
                   v-model="koreanScores[4].score"
                   :checked="koreanScores[4].decided"
            >
            <label for="korean-third-first-null"
                   class="input-grade-label"
                   @click="changeDecided($event, koreanScores[4])"
                   :class="discomplete(koreanScores[4].score)"
            >X</label>
          </td>
        </tr>
        <tr class="grade-input-table__row">
          <td>사회</td>
          <td :class="changeBackground(societyScores[0].score)">
            <input type="radio"
                   v-for="k in scores"
                   :key="k"
                   :value="k.toUpperCase()"
                   :id="`society-first-first-${k}`"
                   v-model="societyScores[0].score"
                   :checked="societyScores[0].decided"
            >
            <label v-for="(k, index) in scores"
                   :key="index"
                   :for="`society-first-first-${k}`"
                   class="input-grade-label"
                   @click="changeDecided($event, societyScores[0])"
                   :class="complete(societyScores[0].score, k.toUpperCase())"
            >
              {{ k.toUpperCase() }}
            </label>
            <div class="button-side-line" v-show="!societyScores[0].score"></div>
            <input type="radio"
                   value="X"
                   id="society-first-first-null"
                   v-model="societyScores[0].score"
                   :checked="societyScores[0].decided"
            >
            <label for="society-first-first-null"
                   class="input-grade-label"
                   @click="changeDecided($event, societyScores[0])"
                   :class="discomplete(societyScores[0].score)"
            >X</label>
          </td>
          <td :class="changeBackground(societyScores[1].score)">
            <input type="radio"
                   v-for="k in scores"
                   :key="k"
                   :value="k.toUpperCase()"
                   :id="`society-first-second-${k}`"
                   v-model="societyScores[1].score"
                   :checked="societyScores[1].decided"
            >
            <label v-for="(k, index) in scores"
                   :key="index"
                   :for="`society-first-second-${k}`"
                   class="input-grade-label"
                   @click="changeDecided($event, societyScores[1])"
                   :class="complete(societyScores[1].score, k.toUpperCase())"
            >
              {{ k.toUpperCase() }}
            </label>
            <div class="button-side-line" v-show="!societyScores[1].score"></div>
            <input type="radio"
                   value="X"
                   id="society-first-second-null"
                   v-model="societyScores[1].score"
                   :checked="societyScores[1].decided"
            >
            <label for="society-first-second-null"
                   class="input-grade-label"
                   @click="changeDecided($event, societyScores[1])"
                   :class="discomplete(societyScores[1].score)"
            >X</label>
          </td>
          <td :class="changeBackground(societyScores[2].score)">
            <input type="radio"
                   v-for="k in scores"
                   :key="k"
                   :value="k.toUpperCase()"
                   :id="`society-second-first-${k}`"
                   v-model="societyScores[2].score"
                   :checked="societyScores[2].decided"
            >
            <label v-for="(k, index) in scores"
                   :key="index"
                   :for="`society-second-first-${k}`"
                   class="input-grade-label"
                   @click="changeDecided($event, societyScores[2])"
                   :class="complete(societyScores[2].score, k.toUpperCase())"
            >
              {{ k.toUpperCase() }}
            </label>
            <div class="button-side-line" v-show="!societyScores[2].score"></div>
            <input type="radio"
                   value="X"
                   id="society-second-first-null"
                   v-model="societyScores[2].score"
                   :checked="societyScores[2].decided"
            >
            <label for="society-second-first-null"
                   class="input-grade-label"
                   @click="changeDecided($event, societyScores[2])"
                   :class="discomplete(societyScores[2].score)"
            >X</label>
          </td>
          <td :class="changeBackground(societyScores[3].score)">
            <input type="radio"
                   v-for="k in scores"
                   :key="k"
                   :value="k.toUpperCase()"
                   :id="`society-second-second-${k}`"
                   v-model="societyScores[3].score"
                   :checked="societyScores[3].decided"
            >
            <label v-for="(k, index) in scores"
                   :key="index"
                   :for="`society-second-second-${k}`"
                   class="input-grade-label"
                   @click="changeDecided($event, societyScores[3])"
                   :class="complete(societyScores[3].score, k.toUpperCase())"
            >
              {{ k.toUpperCase() }}
            </label>
            <div class="button-side-line" v-show="!societyScores[3].score"></div>
            <input type="radio"
                   value="X"
                   id="society-second-second-null"
                   v-model="societyScores[3].score"
                   :checked="societyScores[3].decided"
            >
            <label for="society-second-second-null"
                   class="input-grade-label"
                   @click="changeDecided($event, societyScores[3])"
                   :class="discomplete(societyScores[3].score)"
            >X</label>
          </td>
          <td :class="changeBackground(societyScores[4].score)">
            <input type="radio"
                   v-for="k in scores"
                   :key="k"
                   :value="k.toUpperCase()"
                   :id="`society-third-first-${k}`"
                   v-model="societyScores[4].score"
                   :checked="societyScores[4].decided"
            >
            <label v-for="(k, index) in scores"
                   :key="index"
                   :for="`society-third-first-${k}`"
                   class="input-grade-label"
                   @click="changeDecided($event, societyScores[4])"
                   :class="complete(societyScores[4].score, k.toUpperCase())"
            >
              {{ k.toUpperCase() }}
            </label>
            <div class="button-side-line" v-show="!societyScores[4].score"></div>
            <input type="radio"
                   value="X"
                   id="society-third-first-null"
                   v-model="societyScores[4].score"
                   :checked="societyScores[4].decided"
            >
            <label for="society-third-first-null"
                   class="input-grade-label"
                   @click="changeDecided($event, societyScores[4])"
                   :class="discomplete(societyScores[4].score)"
            >X</label>
          </td>
        </tr>
        <tr class="grade-input-table__row">
          <td>역사</td>
          <td :class="changeBackground(historyScores[0].score)">
            <input type="radio"
                   v-for="k in scores"
                   :key="k"
                   :value="k.toUpperCase()"
                   :id="`history-first-first-${k}`"
                   v-model="historyScores[0].score"
                   :checked="historyScores[0].decided"
            >
            <label v-for="(k, index) in scores"
                   :key="index"
                   :for="`history-first-first-${k}`"
                   class="input-grade-label"
                   @click="changeDecided($event, historyScores[0])"
                   :class="complete(historyScores[0].score, k.toUpperCase())"
            >
              {{ k.toUpperCase() }}
            </label>
            <div class="button-side-line" v-show="!historyScores[0].score"></div>
            <input type="radio"
                   value="X"
                   id="history-first-first-null"
                   v-model="historyScores[0].score"
                   :checked="historyScores[0].decided"
            >
            <label for="history-first-first-null"
                   class="input-grade-label"
                   @click="changeDecided($event, historyScores[0])"
                   :class="discomplete(historyScores[0].score)"
            >X</label>
          </td>
          <td :class="changeBackground(historyScores[1].score)">
            <input type="radio"
                   v-for="k in scores"
                   :key="k"
                   :value="k.toUpperCase()"
                   :id="`history-first-second-${k}`"
                   v-model="historyScores[1].score"
                   :checked="historyScores[1].decided"
            >
            <label v-for="(k, index) in scores"
                   :key="index"
                   :for="`history-first-second-${k}`"
                   class="input-grade-label"
                   @click="changeDecided($event, historyScores[1])"
                   :class="complete(historyScores[1].score, k.toUpperCase())"
            >
              {{ k.toUpperCase() }}
            </label>
            <div class="button-side-line" v-show="!historyScores[1].score"></div>
            <input type="radio"
                   value="X"
                   id="history-first-second-null"
                   v-model="historyScores[1].score"
                   :checked="historyScores[1].decided"
            >
            <label for="history-first-second-null"
                   class="input-grade-label"
                   @click="changeDecided($event, historyScores[1])"
                   :class="discomplete(historyScores[1].score)"
            >X</label>
          </td>
          <td :class="changeBackground(historyScores[2].score)">
            <input type="radio"
                   v-for="k in scores"
                   :key="k"
                   :value="k.toUpperCase()"
                   :id="`history-second-first-${k}`"
                   v-model="historyScores[2].score"
                   :checked="historyScores[2].decided"
            >
            <label v-for="(k, index) in scores"
                   :key="index"
                   :for="`history-second-first-${k}`"
                   class="input-grade-label"
                   @click="changeDecided($event, historyScores[2])"
                   :class="complete(historyScores[2].score, k.toUpperCase())"
            >
              {{ k.toUpperCase() }}
            </label>
            <div class="button-side-line" v-show="!historyScores[2].score"></div>
            <input type="radio"
                   value="X"
                   id="history-second-first-null"
                   v-model="historyScores[2].score"
                   :checked="historyScores[2].decided"
            >
            <label for="history-second-first-null"
                   class="input-grade-label"
                   @click="changeDecided($event, historyScores[2])"
                   :class="discomplete(historyScores[2].score)"
            >X</label>
          </td>
          <td :class="changeBackground(historyScores[3].score)">
            <input type="radio"
                   v-for="k in scores"
                   :key="k"
                   :value="k.toUpperCase()"
                   :id="`history-second-second-${k}`"
                   v-model="historyScores[3].score"
                   :checked="historyScores[3].decided"
            >
            <label v-for="(k, index) in scores"
                   :key="index"
                   :for="`history-second-second-${k}`"
                   class="input-grade-label"
                   @click="changeDecided($event, historyScores[3])"
                   :class="complete(historyScores[3].score, k.toUpperCase())"
            >
              {{ k.toUpperCase() }}
            </label>
            <div class="button-side-line" v-show="!historyScores[3].score"></div>
            <input type="radio"
                   value="X"
                   id="history-second-second-null"
                   v-model="historyScores[3].score"
                   :checked="historyScores[3].decided"
            >
            <label for="history-second-second-null"
                   class="input-grade-label"
                   @click="changeDecided($event, historyScores[3])"
                   :class="discomplete(historyScores[3].score)"
            >X</label>
          </td>
          <td :class="changeBackground(historyScores[4].score)">
            <input type="radio"
                   v-for="k in scores"
                   :key="k"
                   :value="k.toUpperCase()"
                   :id="`history-third-first-${k}`"
                   v-model="historyScores[4].score"
                   :checked="historyScores[4].decided"
            >
            <label v-for="(k, index) in scores"
                   :key="index"
                   :for="`history-third-first-${k}`"
                   class="input-grade-label"
                   @click="changeDecided($event, historyScores[4])"
                   :class="complete(historyScores[4].score, k.toUpperCase())"
            >
              {{ k.toUpperCase() }}
            </label>
            <div class="button-side-line" v-show="!historyScores[4].score"></div>
            <input type="radio"
                   value="X"
                   id="history-third-first-null"
                   v-model="historyScores[4].score"
                   :checked="historyScores[4].decided"
            >
            <label for="history-third-first-null"
                   class="input-grade-label"
                   @click="changeDecided($event, historyScores[4])"
                   :class="discomplete(historyScores[4].score)"
            >X</label>
          </td>
        </tr>
        <tr class="grade-input-table__row">
          <td>수학</td>
          <td :class="changeBackground(mathScores[0].score)">
            <input type="radio"
                   v-for="k in scores"
                   :key="k"
                   :value="k.toUpperCase()"
                   :id="`math-first-first-${k}`"
                   v-model="mathScores[0].score"
                   :checked="mathScores[0].decided"
            >
            <label v-for="(k, index) in scores"
                   :key="index"
                   :for="`math-first-first-${k}`"
                   class="input-grade-label"
                   @click="changeDecided($event, mathScores[0])"
                   :class="complete(mathScores[0].score, k.toUpperCase())"
            >
              {{ k.toUpperCase() }}
            </label>
            <div class="button-side-line" v-show="!mathScores[0].score"></div>
            <input type="radio"
                   value="X"
                   id="math-first-first-null"
                   v-model="mathScores[0].score"
                   :checked="mathScores[0].decided"
            >
            <label for="math-first-first-null"
                   class="input-grade-label"
                   @click="changeDecided($event, mathScores[0])"
                   :class="discomplete(mathScores[0].score)"
            >X</label>
          </td>
          <td :class="changeBackground(mathScores[1].score)">
            <input type="radio"
                   v-for="k in scores"
                   :key="k"
                   :value="k.toUpperCase()"
                   :id="`math-first-second-${k}`"
                   v-model="mathScores[1].score"
                   :checked="mathScores[1].decided"
            >
            <label v-for="(k, index) in scores"
                   :key="index"
                   :for="`math-first-second-${k}`"
                   class="input-grade-label"
                   @click="changeDecided($event, mathScores[1])"
                   :class="complete(mathScores[1].score, k.toUpperCase())"
            >
              {{ k.toUpperCase() }}
            </label>
            <div class="button-side-line" v-show="!mathScores[1].score"></div>
            <input type="radio"
                   value="X"
                   id="math-first-second-null"
                   v-model="mathScores[1].score"
                   :checked="mathScores[1].decided"
            >
            <label for="math-first-second-null"
                   class="input-grade-label"
                   @click="changeDecided($event, mathScores[1])"
                   :class="discomplete(mathScores[1].score)"
            >X</label>
          </td>
          <td :class="changeBackground(mathScores[2].score)">
            <input type="radio"
                   v-for="k in scores"
                   :key="k"
                   :value="k.toUpperCase()"
                   :id="`math-second-first-${k}`"
                   v-model="mathScores[2].score"
                   :checked="mathScores[2].decided"
            >
            <label v-for="(k, index) in scores"
                   :key="index"
                   :for="`math-second-first-${k}`"
                   class="input-grade-label"
                   @click="changeDecided($event, mathScores[2])"
                   :class="complete(mathScores[2].score, k.toUpperCase())"
            >
              {{ k.toUpperCase() }}
            </label>
            <div class="button-side-line" v-show="!mathScores[2].score"></div>
            <input type="radio"
                   value="X"
                   id="math-second-first-null"
                   v-model="mathScores[2].score"
                   :checked="mathScores[2].decided"
            >
            <label for="math-second-first-null"
                   class="input-grade-label"
                   @click="changeDecided($event, mathScores[2])"
                   :class="discomplete(mathScores[2].score)"
            >X</label>
          </td>
          <td :class="changeBackground(mathScores[3].score)">
            <input type="radio"
                   v-for="k in scores"
                   :key="k"
                   :value="k.toUpperCase()"
                   :id="`math-second-second-${k}`"
                   v-model="mathScores[3].score"
                   :checked="mathScores[3].decided"
            >
            <label v-for="(k, index) in scores"
                   :key="index"
                   :for="`math-second-second-${k}`"
                   class="input-grade-label"
                   @click="changeDecided($event, mathScores[3])"
                   :class="complete(mathScores[3].score, k.toUpperCase())"
            >
              {{ k.toUpperCase() }}
            </label>
            <div class="button-side-line" v-show="!mathScores[3].score"></div>
            <input type="radio"
                   value="X"
                   id="math-second-second-null"
                   v-model="mathScores[3].score"
                   :checked="mathScores[3].decided"
            >
            <label for="math-second-second-null"
                   class="input-grade-label"
                   @click="changeDecided($event, mathScores[3])"
                   :class="discomplete(mathScores[3].score)"
            >X</label>
          </td>
          <td :class="changeBackground(mathScores[4].score)">
            <input type="radio"
                   v-for="k in scores"
                   :key="k"
                   :value="k.toUpperCase()"
                   :id="`math-third-first-${k}`"
                   v-model="mathScores[4].score"
                   :checked="mathScores[4].decided"
            >
            <label v-for="(k, index) in scores"
                   :key="index"
                   :for="`math-third-first-${k}`"
                   class="input-grade-label"
                   @click="changeDecided($event, mathScores[4])"
                   :class="complete(mathScores[4].score, k.toUpperCase())"
            >
              {{ k.toUpperCase() }}
            </label>
            <div class="button-side-line" v-show="!mathScores[4].score"></div>
            <input type="radio"
                   value="X"
                   id="math-third-first-null"
                   v-model="mathScores[4].score"
                   :checked="mathScores[4].decided"
            >
            <label for="math-third-first-null"
                   class="input-grade-label"
                   @click="changeDecided($event, mathScores[4])"
                   :class="discomplete(mathScores[4].score)"
            >X</label>
          </td>
        </tr>
        <tr class="grade-input-table__row">
          <td>과학</td>
          <td :class="changeBackground(scienceScores[0].score)">
            <input type="radio"
                   v-for="k in scores"
                   :key="k"
                   :value="k.toUpperCase()"
                   :id="`science-first-first-${k}`"
                   v-model="scienceScores[0].score"
                   :checked="scienceScores[0].decided"
            >
            <label v-for="(k, index) in scores"
                   :key="index"
                   :for="`science-first-first-${k}`"
                   class="input-grade-label"
                   @click="changeDecided($event, scienceScores[0])"
                   :class="complete(scienceScores[0].score, k.toUpperCase())"
            >
              {{ k.toUpperCase() }}
            </label>
            <div class="button-side-line" v-show="!scienceScores[0].score"></div>
            <input type="radio"
                   value="X"
                   id="science-first-first-null"
                   v-model="scienceScores[0].score"
                   :checked="scienceScores[0].decided"
            >
            <label for="science-first-first-null"
                   class="input-grade-label"
                   @click="changeDecided($event, scienceScores[0])"
                   :class="discomplete(scienceScores[0].score)"
            >X</label>
          </td>
          <td :class="changeBackground(scienceScores[1].score)">
            <input type="radio"
                   v-for="k in scores"
                   :key="k"
                   :value="k.toUpperCase()"
                   :id="`science-first-second-${k}`"
                   v-model="scienceScores[1].score"
                   :checked="scienceScores[1].decided"
            >
            <label v-for="(k, index) in scores"
                   :key="index"
                   :for="`science-first-second-${k}`"
                   class="input-grade-label"
                   @click="changeDecided($event, scienceScores[1])"
                   :class="complete(scienceScores[1].score, k.toUpperCase())"
            >
              {{ k.toUpperCase() }}
            </label>
            <div class="button-side-line" v-show="!scienceScores[1].score"></div>
            <input type="radio"
                   value="X"
                   id="science-first-second-null"
                   v-model="scienceScores[1].score"
                   :checked="scienceScores[1].decided"
            >
            <label for="science-first-second-null"
                   class="input-grade-label"
                   @click="changeDecided($event, scienceScores[1])"
                   :class="discomplete(scienceScores[1].score)"
            >X</label>
          </td>
          <td :class="changeBackground(scienceScores[2].score)">
            <input type="radio"
                   v-for="k in scores"
                   :key="k"
                   :value="k.toUpperCase()"
                   :id="`science-second-first-${k}`"
                   v-model="scienceScores[2].score"
                   :checked="scienceScores[2].decided"
            >
            <label v-for="(k, index) in scores"
                   :key="index"
                   :for="`science-second-first-${k}`"
                   class="input-grade-label"
                   @click="changeDecided($event, scienceScores[2])"
                   :class="complete(scienceScores[2].score, k.toUpperCase())"
            >
              {{ k.toUpperCase() }}
            </label>
            <div class="button-side-line" v-show="!scienceScores[2].score"></div>
            <input type="radio"
                   value="X"
                   id="science-second-first-null"
                   v-model="scienceScores[2].score"
                   :checked="scienceScores[2].decided"
            >
            <label for="science-second-first-null"
                   class="input-grade-label"
                   @click="changeDecided($event, scienceScores[2])"
                   :class="discomplete(scienceScores[2].score)"
            >X</label>
          </td>
          <td :class="changeBackground(scienceScores[3].score)">
            <input type="radio"
                   v-for="k in scores"
                   :key="k"
                   :value="k.toUpperCase()"
                   :id="`science-second-second-${k}`"
                   v-model="scienceScores[3].score"
                   :checked="scienceScores[3].decided"
            >
            <label v-for="(k, index) in scores"
                   :key="index"
                   :for="`science-second-second-${k}`"
                   class="input-grade-label"
                   @click="changeDecided($event, scienceScores[3])"
                   :class="complete(scienceScores[3].score, k.toUpperCase())"
            >
              {{ k.toUpperCase() }}
            </label>
            <div class="button-side-line" v-show="!scienceScores[3].score"></div>
            <input type="radio"
                   value="X"
                   id="science-second-second-null"
                   v-model="scienceScores[3].score"
                   :checked="scienceScores[3].decided"
            >
            <label for="science-second-second-null"
                   class="input-grade-label"
                   @click="changeDecided($event, scienceScores[3])"
                   :class="discomplete(scienceScores[3].score)"
            >X</label>
          </td>
          <td :class="changeBackground(scienceScores[4].score)">
            <input type="radio"
                   v-for="k in scores"
                   :key="k"
                   :value="k.toUpperCase()"
                   :id="`science-third-first-${k}`"
                   v-model="scienceScores[4].score"
                   :checked="scienceScores[4].decided"
            >
            <label v-for="(k, index) in scores"
                   :key="index"
                   :for="`science-third-first-${k}`"
                   class="input-grade-label"
                   @click="changeDecided($event, scienceScores[4])"
                   :class="complete(scienceScores[4].score, k.toUpperCase())"
            >
              {{ k.toUpperCase() }}
            </label>
            <div class="button-side-line" v-show="!scienceScores[4].score"></div>
            <input type="radio"
                   value="X"
                   id="science-third-first-null"
                   v-model="scienceScores[4].score"
                   :checked="scienceScores[4].decided"
            >
            <label for="science-third-first-null"
                   class="input-grade-label"
                   @click="changeDecided($event, scienceScores[4])"
                   :class="discomplete(scienceScores[4].score)"
            >X</label>
          </td>
        </tr>
        <tr class="grade-input-table__row">
          <td>기술・가정</td>
          <td :class="changeBackground(techAndHomeScores[0].score)">
            <input type="radio"
                   v-for="k in scores"
                   :key="k"
                   :value="k.toUpperCase()"
                   :id="`tech-home-first-first-${k}`"
                   v-model="techAndHomeScores[0].score"
                   :checked="techAndHomeScores[0].decided"
            >
            <label v-for="(k, index) in scores"
                   :key="index"
                   :for="`tech-home-first-first-${k}`"
                   class="input-grade-label"
                   @click="changeDecided($event, techAndHomeScores[0])"
                   :class="complete(techAndHomeScores[0].score, k.toUpperCase())"
            >
              {{ k.toUpperCase() }}
            </label>
            <div class="button-side-line" v-show="!techAndHomeScores[0].score"></div>
            <input type="radio"
                   value="X"
                   id="tech-home-first-first-null"
                   v-model="techAndHomeScores[0].score"
                   :checked="techAndHomeScores[0].decided"
            >
            <label for="tech-home-first-first-null"
                   class="input-grade-label"
                   @click="changeDecided($event, techAndHomeScores[0])"
                   :class="discomplete(techAndHomeScores[0].score)"
            >X</label>
          </td>
          <td :class="changeBackground(techAndHomeScores[1].score)">
            <input type="radio"
                   v-for="k in scores"
                   :key="k"
                   :value="k.toUpperCase()"
                   :id="`tech-home-first-second-${k}`"
                   v-model="techAndHomeScores[1].score"
                   :checked="techAndHomeScores[1].decided"
            >
            <label v-for="(k, index) in scores"
                   :key="index"
                   :for="`tech-home-first-second-${k}`"
                   class="input-grade-label"
                   @click="changeDecided($event, techAndHomeScores[1])"
                   :class="complete(techAndHomeScores[1].score, k.toUpperCase())"
            >
              {{ k.toUpperCase() }}
            </label>
            <div class="button-side-line" v-show="!techAndHomeScores[1].score"></div>
            <input type="radio"
                   value="X"
                   id="tech-home-first-second-null"
                   v-model="techAndHomeScores[1].score"
                   :checked="techAndHomeScores[1].decided"
            >
            <label for="tech-home-first-second-null"
                   class="input-grade-label"
                   @click="changeDecided($event, techAndHomeScores[1])"
                   :class="discomplete(techAndHomeScores[1].score)"
            >X</label>
          </td>
          <td :class="changeBackground(techAndHomeScores[2].score)">
            <input type="radio"
                   v-for="k in scores"
                   :key="k"
                   :value="k.toUpperCase()"
                   :id="`tech-home-second-first-${k}`"
                   v-model="techAndHomeScores[2].score"
                   :checked="techAndHomeScores[2].decided"
            >
            <label v-for="(k, index) in scores"
                   :key="index"
                   :for="`tech-home-second-first-${k}`"
                   class="input-grade-label"
                   @click="changeDecided($event, techAndHomeScores[2])"
                   :class="complete(techAndHomeScores[2].score, k.toUpperCase())"
            >
              {{ k.toUpperCase() }}
            </label>
            <div class="button-side-line" v-show="!techAndHomeScores[2].score"></div>
            <input type="radio"
                   value="X"
                   id="tech-home-second-first-null"
                   v-model="techAndHomeScores[2].score"
                   :checked="techAndHomeScores[2].decided"
            >
            <label for="tech-home-second-first-null"
                   class="input-grade-label"
                   @click="changeDecided($event, techAndHomeScores[2])"
                   :class="discomplete(techAndHomeScores[2].score)"
            >X</label>
          </td>
          <td :class="changeBackground(techAndHomeScores[3].score)">
            <input type="radio"
                   v-for="k in scores"
                   :key="k"
                   :value="k.toUpperCase()"
                   :id="`tech-home-second-second-${k}`"
                   v-model="techAndHomeScores[3].score"
                   :checked="techAndHomeScores[3].decided"
            >
            <label v-for="(k, index) in scores"
                   :key="index"
                   :for="`tech-home-second-second-${k}`"
                   class="input-grade-label"
                   @click="changeDecided($event, techAndHomeScores[3])"
                   :class="complete(techAndHomeScores[3].score, k.toUpperCase())"
            >
              {{ k.toUpperCase() }}
            </label>
            <div class="button-side-line" v-show="!techAndHomeScores[3].score"></div>
            <input type="radio"
                   value="X"
                   id="tech-home-second-second-null"
                   v-model="techAndHomeScores[3].score"
                   :checked="techAndHomeScores[3].decided"
            >
            <label for="tech-home-second-second-null"
                   class="input-grade-label"
                   @click="changeDecided($event, techAndHomeScores[3])"
                   :class="discomplete(techAndHomeScores[3].score)"
            >X</label>
          </td>
          <td :class="changeBackground(techAndHomeScores[4].score)">
            <input type="radio"
                   v-for="k in scores"
                   :key="k"
                   :value="k.toUpperCase()"
                   :id="`tech-home-third-first-${k}`"
                   v-model="techAndHomeScores[4].score"
                   :checked="techAndHomeScores[4].decided"
            >
            <label v-for="(k, index) in scores"
                   :key="index"
                   :for="`tech-home-third-first-${k}`"
                   class="input-grade-label"
                   @click="changeDecided($event, techAndHomeScores[4])"
                   :class="complete(techAndHomeScores[4].score, k.toUpperCase())"
            >
              {{ k.toUpperCase() }}
            </label>
            <div class="button-side-line" v-show="!techAndHomeScores[4].score"></div>
            <input type="radio"
                   value="X"
                   id="tech-home-third-first-null"
                   v-model="techAndHomeScores[4].score"
                   :checked="techAndHomeScores[4].decided"
            >
            <label for="tech-home-third-first-null"
                   class="input-grade-label"
                   @click="changeDecided($event, techAndHomeScores[4])"
                   :class="discomplete(techAndHomeScores[4].score)"
            >X</label>
          </td>
        </tr>
        <tr class="grade-input-table__row">
          <td>영어</td>
          <td :class="changeBackground(englishScores[0].score)">
            <input type="radio"
                   v-for="k in scores"
                   :key="k"
                   :value="k.toUpperCase()"
                   :id="`english-first-first-${k}`"
                   v-model="englishScores[0].score"
                   :checked="englishScores[0].decided"
            >
            <label v-for="(k, index) in scores"
                   :key="index"
                   :for="`english-first-first-${k}`"
                   class="input-grade-label"
                   @click="changeDecided($event, englishScores[0])"
                   :class="complete(englishScores[0].score, k.toUpperCase())"
            >
              {{ k.toUpperCase() }}
            </label>
            <div class="button-side-line" v-show="!englishScores[0].score"></div>
            <input type="radio"
                   value="X"
                   id="english-first-first-null"
                   v-model="englishScores[0].score"
                   :checked="englishScores[0].decided"
            >
            <label for="english-first-first-null"
                   class="input-grade-label"
                   @click="changeDecided($event, englishScores[0])"
                   :class="discomplete(englishScores[0].score)"
            >X</label>
          </td>
          <td :class="changeBackground(englishScores[1].score)">
            <input type="radio"
                   v-for="k in scores"
                   :key="k"
                   :value="k.toUpperCase()"
                   :id="`english-first-second-${k}`"
                   v-model="englishScores[1].score"
                   :checked="englishScores[1].decided"
            >
            <label v-for="(k, index) in scores"
                   :key="index"
                   :for="`english-first-second-${k}`"
                   class="input-grade-label"
                   @click="changeDecided($event, englishScores[1])"
                   :class="complete(englishScores[1].score, k.toUpperCase())"
            >
              {{ k.toUpperCase() }}
            </label>
            <div class="button-side-line" v-show="!englishScores[1].score"></div>
            <input type="radio"
                   value="X"
                   id="english-first-second-null"
                   v-model="englishScores[1].score"
                   :checked="englishScores[1].decided"
            >
            <label for="english-first-second-null"
                   class="input-grade-label"
                   @click="changeDecided($event, englishScores[1])"
                   :class="discomplete(englishScores[1].score)"
            >X</label>
          </td>
          <td :class="changeBackground(englishScores[2].score)">
            <input type="radio"
                   v-for="k in scores"
                   :key="k"
                   :value="k.toUpperCase()"
                   :id="`english-second-first-${k}`"
                   v-model="englishScores[2].score"
                   :checked="englishScores[2].decided"
            >
            <label v-for="(k, index) in scores"
                   :key="index"
                   :for="`english-second-first-${k}`"
                   class="input-grade-label"
                   @click="changeDecided($event, englishScores[2])"
                   :class="complete(englishScores[2].score, k.toUpperCase())"
            >
              {{ k.toUpperCase() }}
            </label>
            <div class="button-side-line" v-show="!englishScores[2].score"></div>
            <input type="radio"
                   value="X"
                   id="english-second-first-null"
                   v-model="englishScores[2].score"
                   :checked="englishScores[2].decided"
            >
            <label for="english-second-first-null"
                   class="input-grade-label"
                   @click="changeDecided($event, englishScores[2])"
                   :class="discomplete(englishScores[2].score)"
            >X</label>
          </td>
          <td :class="changeBackground(englishScores[3].score)">
            <input type="radio"
                   v-for="k in scores"
                   :key="k"
                   :value="k.toUpperCase()"
                   :id="`english-second-second-${k}`"
                   v-model="englishScores[3].score"
                   :checked="englishScores[3].decided"
            >
            <label v-for="(k, index) in scores"
                   :key="index"
                   :for="`english-second-second-${k}`"
                   class="input-grade-label"
                   @click="changeDecided($event, englishScores[3])"
                   :class="complete(englishScores[3].score, k.toUpperCase())"
            >
              {{ k.toUpperCase() }}
            </label>
            <div class="button-side-line" v-show="!englishScores[3].score"></div>
            <input type="radio"
                   value="X"
                   id="english-second-second-null"
                   v-model="englishScores[3].score"
                   :checked="englishScores[3].decided"
            >
            <label for="english-second-second-null"
                   class="input-grade-label"
                   @click="changeDecided($event, englishScores[3])"
                   :class="discomplete(englishScores[3].score)"
            >X</label>
          </td>
          <td :class="changeBackground(englishScores[4].score)">
            <input type="radio"
                   v-for="k in scores"
                   :key="k"
                   :value="k.toUpperCase()"
                   :id="`english-third-first-${k}`"
                   v-model="englishScores[4].score"
                   :checked="englishScores[4].decided"
            >
            <label v-for="(k, index) in scores"
                   :key="index"
                   :for="`english-third-first-${k}`"
                   class="input-grade-label"
                   @click="changeDecided($event, englishScores[4])"
                   :class="complete(englishScores[4].score, k.toUpperCase())"
            >
              {{ k.toUpperCase() }}
            </label>
            <div class="button-side-line" v-show="!englishScores[4].score"></div>
            <input type="radio"
                   value="X"
                   id="english-third-first-null"
                   v-model="englishScores[4].score"
                   :checked="englishScores[4].decided"
            >
            <label for="english-third-first-null"
                   class="input-grade-label"
                   @click="changeDecided($event, englishScores[4])"
                   :class="discomplete(englishScores[4].score)"
            >X</label>
          </td>
        </tr>
      </table>
    </div>
    <prev-next-btn :prevShow="1"
                   :nextShow="1"
                   @toPrevPage="movePrev"
                   @toNextPage="moveNext"
    />
    <entry-footer />
  </div>
</template>

<script>
import Navigation from '../common/Navigation';
import Headline from '../common/Headline';
import PrevNextBtn from '../common/PrevNextBtn';
import EntryFooter from '../common/EntryFooter';

export default {
  name: 'normal-grade-input-scheduled',
  components: {
    Navigation,
    Headline,
    PrevNextBtn,
    EntryFooter,
  },
  data() {
    return {
      // 봉사 및 출석
      volunteerNAttendance: {
        volunteer: 0,
        absence: 0,
        earlyLeave: 0,
        lateness: 0,
        missingClass: 0,
      },

      // 미이수 학기 선택
      semesters: {
        firstFirstSemester: false,
        firstSecondSemester: false,
        secondFirstSemester: false,
        secondSecondSemester: false,
        thirdFirstSemester: false,
      },

      // 모든 학기 초기화
      resetAllGrade: '',

      // 초기화 점수
      scores: ['a', 'b', 'c', 'd', 'e'],

      /* 점수 실제 설정 */
      // 국어
      koreanScores: [
        { score: '', decided: false, passed: false },
        { score: '', decided: false, passed: false },
        { score: '', decided: false, passed: false },
        { score: '', decided: false, passed: false },
        { score: '', decided: false, passed: false },
      ],

      // 사회
      societyScores: [
        { score: '', decided: false, passed: false },
        { score: '', decided: false, passed: false },
        { score: '', decided: false, passed: false },
        { score: '', decided: false, passed: false },
        { score: '', decided: false, passed: false },
      ],

      // 역사
      historyScores: [
        { score: '', decided: false, passed: false },
        { score: '', decided: false, passed: false },
        { score: '', decided: false, passed: false },
        { score: '', decided: false, passed: false },
        { score: '', decided: false, passed: false },
      ],

      // 수학
      mathScores: [
        { score: '', decided: false, passed: false },
        { score: '', decided: false, passed: false },
        { score: '', decided: false, passed: false },
        { score: '', decided: false, passed: false },
        { score: '', decided: false, passed: false },
      ],

      // 과학
      scienceScores: [
        { score: '', decided: false, passed: false },
        { score: '', decided: false, passed: false },
        { score: '', decided: false, passed: false },
        { score: '', decided: false, passed: false },
        { score: '', decided: false, passed: false },
      ],

      // 기술 - 가정
      techAndHomeScores: [
        { score: '', decided: false, passed: false },
        { score: '', decided: false, passed: false },
        { score: '', decided: false, passed: false },
        { score: '', decided: false, passed: false },
        { score: '', decided: false, passed: false },
      ],

      // 영어
      englishScores: [
        { score: '', decided: false, passed: false },
        { score: '', decided: false, passed: false },
        { score: '', decided: false, passed: false },
        { score: '', decided: false, passed: false },
        { score: '', decided: false, passed: false },
      ],
    };
  },
  computed: {
    grades() {
      const allGrades = [
        this.koreanScores,
        this.societyScores,
        this.historyScores,
        this.mathScores,
        this.scienceScores,
        this.techAndHomeScores,
        this.englishScores,
      ];

      return allGrades;
    },
  },
  methods: {
    movePrev() {
      this.$router.push('/');
    },

    moveNext() {
      this.$router.push('/');
    },

    // 초기화 버튼 설정
    setButton(t) {
      this.resetAllGrade = t.textContent.trim();
    },

    // 실제 점수 초기화
    setGrades() {
      const allGrades = this.grades;

      for (let i = 0; i < allGrades.length; i += 1) {
        for (let j = 0; j < allGrades[i].length; j += 1) {
          const resetAll = allGrades[i][j];
          // 초기화 - 점수 및 클릭 여부
          resetAll.score = this.resetAllGrade;
          resetAll.decided = true;
          resetAll.passed = true;
        }
      }
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

    // 미이수 설정
    discompleteSemester({ target }) {
      // 학기별 null로 초기화
      function reset(all, index) {
        for (let i = 0; i < all.length; i += 1) {
          const allScores = all[i];

          if (target.value === 'true') {
            allScores[index].score = '';
            allScores[index].passed = false;
            allScores[index].decided = false;
          } else {
            allScores[index].score = 'X';
            allScores[index].passed = false;
            allScores[index].decided = true;
          }
        }
      }
      // 학급 불러오기
      const allGrades = this.grades;

      switch (target.id) {
        case 'input-first-first':
          reset(allGrades, 0);
          break;
        case 'input-first-second':
          reset(allGrades, 1);
          break;
        case 'input-second-first':
          reset(allGrades, 2);
          break;
        case 'input-second-second':
          reset(allGrades, 3);
          break;
        case 'input-third-first':
          reset(allGrades, 4);
          break;
        default: break;
      }
    },

    // 점수를 눌렀을 시의 이벤트
    changeDecided({ target }, val) {
      const v = val;
      v.decided = !v.decided;
      v.passed = target.innerText === 'X' ? false : !v.passed;
      v.score = v.decided ? v.score : '';
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
    font-size: 14px;
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
    left: 22px;
    transition: 0.35s ease-in-out;
    border-style: solid;
    border-color: #cfdee0;

    @for $i from 1 through 4 {
      &:nth-of-type(#{$i + 1}) {
        left: calc(22px + (23px * #{$i}));
      }
    }
    &:nth-of-type(6) {
      left: 145px;
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

      @include e('first-grade') {
        width: 455.6px;
        border-right: 1px solid $table-inner;
      }

      @include e('second-grade') {
        width: 453.6px;
        border-right: 1px solid $table-inner;
      }

      @include e('third-grade') {
        width: calc(100% - 909.2px);
      }

      @include e('first-semester') {
        width: 227.8px;
        height: 100%;
        border-right: 1px solid $table-inner;
      }

      @include e('second-semester') {
        width: 226.8px;
        height: 100%;
        border-right: 1px solid $table-inner;
      }

      @include e('third-semester') {
        width: calc(100% - 909.2px);
        height: 100%;
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
      width: 200px;
      height: 60px;
      box-sizing: border-box;
      position: absolute;
      right: 0;
      margin-top: -64px;

      @include e('box') {
        width: 175px;
        height: 24px;
        border: 1px solid $input-color;
        border-radius: 5px;
        display: flex;
        align-items: center;
        justify-content: center;
        box-sizing: border-box;
        float: right;

        @include e('text') {
          font-size: 10px;
          color: #26484c;
        }
      }

      @include e('reset') {
        margin-top: 13px;
        display: flex;
        align-items: center;
        justify-content: flex-end;
        float: right;

        @include e('text') {
          font-size: 10px;
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
        transform: translateY(-50%);
        right: 47px;
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
