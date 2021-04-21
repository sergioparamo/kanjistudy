package com.kanjistudy;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.kanjistudy.views.loginProcess.MainActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class NavigationTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void fromMainToSelectKanjisMenu() {

        onView(withId(R.id.mainActivityId)).check(matches(isDisplayed()));

        onView(withId(R.id.kanjiActivityTextViewMain)).perform(click());

        onView(withId(R.id.kanjiMenuActivityId)).check(matches(isDisplayed()));

    }

    @Test
    public void fromMainToKanji1() {

        onView(withId(R.id.mainActivityId)).check(matches(isDisplayed()));

        onView(withId(R.id.kanjiActivityTextViewMain)).perform(click());

        onView(withId(R.id.kanjiMenuActivityId)).check(matches(isDisplayed()));

        onView(withId(R.id.kanjiLevelOneSelectOptionsTextView)).perform(click());

        onView(withId(R.id.kanjiActivityId)).check(matches(isDisplayed()));
        onView(withId(R.id.levelTitle)).check(matches(withText("Level 1")));

    }


    @Test
    public void fromMainToKanji2() {

        onView(withId(R.id.mainActivityId)).check(matches(isDisplayed()));

        onView(withId(R.id.kanjiActivityTextViewMain)).perform(click());

        onView(withId(R.id.kanjiMenuActivityId)).check(matches(isDisplayed()));

        onView(withId(R.id.kanjiLevelTwoSelectOptionsTextView)).perform(click());

        onView(withId(R.id.kanjiActivityId)).check(matches(isDisplayed()));
        onView(withId(R.id.levelTitle)).check(matches(withText("Level 2")));

    }

    @Test
    public void fromMainToHiragana() {

        onView(withId(R.id.mainActivityId)).check(matches(isDisplayed()));

        onView(withId(R.id.hiraganaActivityTextViewMain)).perform(click());

        onView(withId(R.id.kanaActivityId)).check(matches(isDisplayed()));
        onView(withId(R.id.kanaTitleDescriptionTypeId)).check(matches(withText(R.string.hiragana_dictionary)));

    }

    @Test
    public void fromMainToKatakana() {

        onView(withId(R.id.mainActivityId)).check(matches(isDisplayed()));

        onView(withId(R.id.katakanaActivityTextViewMain)).perform(click());

        onView(withId(R.id.kanaActivityId)).check(matches(isDisplayed()));
        onView(withId(R.id.kanaTitleDescriptionTypeId)).check(matches(withText(R.string.katakana_dictionary)));

    }

    @Test
    public void fromMainToQuiz() {

        onView(withId(R.id.mainActivityId)).check(matches(isDisplayed()));

        onView(withId(R.id.quizActivityTextViewMain)).perform(click());

        onView(withId(R.id.quizActivityId)).check(matches(isDisplayed()));

    }


}
