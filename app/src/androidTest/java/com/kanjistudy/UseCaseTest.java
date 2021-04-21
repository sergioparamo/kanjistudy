package com.kanjistudy;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.kanjistudy.views.loginProcess.LoginActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class UseCaseTest {

    public static String USER_TO_BE_TYPED = "manolo";
    public static String PASS_TO_BE_TYPED = "manolo1234";

    @Rule
    public ActivityScenarioRule<LoginActivity> activityScenarioRule = new ActivityScenarioRule<>(LoginActivity.class);


    @Test
    public void takeQuiz() {

        LoginRegisterTest.login_test();

        onView(withId(R.id.mainActivityId)).check(matches(isDisplayed()));

        onView(withId(R.id.quizActivityTextViewMain)).perform(click());

        onView(withId(R.id.quizActivityId)).check(matches(isDisplayed()));

        for (int i = 0; i < 10; i++) {
            onView(withId(R.id.trueButtonQuiz)).perform(click());
        }

        onView(withId(R.id.leaveQuizButton)).perform(click());

        onView(withId(R.id.mainActivityId)).check(matches(isDisplayed()));


    }

    @Test
    public void register_test() {

        onView(withId(R.id.loginActivityId)).check(matches(isDisplayed()));

        onView(withId(R.id.register_button_login)).perform(click());

        onView(withId(R.id.registerActivityId)).check(matches(isDisplayed()));

        onView(withId(R.id.usernameEditText)).perform(typeText(USER_TO_BE_TYPED), closeSoftKeyboard());

        onView(withId(R.id.passwordEditText)).perform(typeText(PASS_TO_BE_TYPED), closeSoftKeyboard());

        onView(withId(R.id.repeatEditText)).perform(typeText(PASS_TO_BE_TYPED), closeSoftKeyboard());

        onView(withId(R.id.emailEditText)).perform(typeText("manolo@gmail.com"), closeSoftKeyboard());

        onView(withId(R.id.nameEditText)).perform(typeText("Manolo"), closeSoftKeyboard());

        onView(withId(R.id.surnameEditText)).perform(typeText("Garcia"), closeSoftKeyboard());

        onView(withId(R.id.register_button_register)).perform(click());


    }

    @Test
    public void login_test() {

        onView(withId(R.id.loginActivityId)).check(matches(isDisplayed()));

        onView(withId(R.id.username_input_login)).perform(typeText(USER_TO_BE_TYPED), closeSoftKeyboard());
        onView(withId(R.id.username_input_login)).check(matches(withText(USER_TO_BE_TYPED)));

        onView(withId(R.id.password_input_login)).perform(typeText(PASS_TO_BE_TYPED), closeSoftKeyboard());
        onView(withId(R.id.password_input_login)).check(matches(withText(PASS_TO_BE_TYPED)));

        onView(withId(R.id.login_button_login)).perform(click());

        onView(withId(R.id.mainActivityId)).check(matches(isDisplayed()));

    }


    @Test
    public void studyKanas() {

        LoginRegisterTest.login_test();

        onView(withId(R.id.mainActivityId)).check(matches(isDisplayed()));

        onView(withId(R.id.hiraganaActivityTextViewMain)).perform(click());

        onView(withId(R.id.kanaActivityId)).check(matches(isDisplayed()));
        onView(withId(R.id.kanaTitleDescriptionTypeId)).check(matches(withText(R.string.hiragana_dictionary)));

        onView(withId(R.id.recyclerKana)).perform(scrollToPosition(0));

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.recyclerKana)).perform(scrollToPosition(5));

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.recyclerKana)).perform(scrollToPosition(10));

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void studyKanjis() {

        LoginRegisterTest.login_test();

        onView(withId(R.id.mainActivityId)).check(matches(isDisplayed()));

        onView(withId(R.id.kanjiActivityTextViewMain)).perform(click());

        onView(withId(R.id.kanjiLevelOneSelectOptionsTextView)).perform(click());

        onView(withId(R.id.recyclerViewKanji)).perform(scrollToPosition(0));

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.recyclerViewKanji)).perform(scrollToPosition(4));

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}
