package com.kanjistudy;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.kanjistudy.views.loginProcess.LoginActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class LoginRegisterTest {

    public static String USER_TO_BE_TYPED = "sergioparamo";
    public static String PASS_TO_BE_TYPED = "sergioparamo123";

    @Rule
    public ActivityScenarioRule<LoginActivity> activityScenarioRule = new ActivityScenarioRule<>(LoginActivity.class);

    @Test
    public static void register_test() {

        onView(withId(R.id.loginActivityId)).check(matches(isDisplayed()));

        onView(withId(R.id.register_button_login)).perform(click());

        onView(withId(R.id.registerActivityId)).check(matches(isDisplayed()));

        onView(withId(R.id.usernameEditText)).perform(typeText(USER_TO_BE_TYPED), closeSoftKeyboard());

        onView(withId(R.id.passwordEditText)).perform(typeText(PASS_TO_BE_TYPED), closeSoftKeyboard());

        onView(withId(R.id.repeatEditText)).perform(typeText(PASS_TO_BE_TYPED), closeSoftKeyboard());

        onView(withId(R.id.emailEditText)).perform(typeText("sergio.paramo1997@gmail.com"), closeSoftKeyboard());

        onView(withId(R.id.nameEditText)).perform(typeText("Sergio"), closeSoftKeyboard());

        onView(withId(R.id.surnameEditText)).perform(typeText("Paramo"), closeSoftKeyboard());

        onView(withId(R.id.register_button_register)).perform(click());


    }

    @Test
    public static void login_test() {

        onView(withId(R.id.loginActivityId)).check(matches(isDisplayed()));

        onView(withId(R.id.username_input_login)).perform(typeText(USER_TO_BE_TYPED), closeSoftKeyboard());
        onView(withId(R.id.username_input_login)).check(matches(withText(USER_TO_BE_TYPED)));

        onView(withId(R.id.password_input_login)).perform(typeText(PASS_TO_BE_TYPED), closeSoftKeyboard());
        onView(withId(R.id.password_input_login)).check(matches(withText(PASS_TO_BE_TYPED)));

        onView(withId(R.id.login_button_login)).perform(click());

        onView(withId(R.id.mainActivityId)).check(matches(isDisplayed()));

    }


}
