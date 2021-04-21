package com.kanjistudy;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.kanjistudy.views.loginProcess.MainActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class RecyclerViewTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testingRecyclerView() {

        onView(withId(R.id.mainActivityId)).check(matches(isDisplayed()));

        onView(withId(R.id.kanjiActivityTextViewMain)).perform(click());

        onView(withId(R.id.kanjiMenuActivityId)).check(matches(isDisplayed()));

        onView(withId(R.id.kanjiLevelOneSelectOptionsTextView)).perform(click());

        onView(withId(R.id.kanjiActivityId)).check(matches(isDisplayed()));
        onView(withId(R.id.levelTitle)).check(matches(withText("Level 1")));

        onView(withId(R.id.recyclerViewKanji)).perform(scrollToPosition(0));

        onView(withId(R.id.kanji_button_sound_id)).check(matches(withId(R.raw.ichi)));

    }

}
