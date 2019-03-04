package it.xabaras.android.espresso.recyclerviewchildactions.sample

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.replaceText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import android.support.test.espresso.matcher.RootMatchers.isDialog
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import it.xabaras.android.espresso.recyclerviewchildactions.RecyclerViewChildActions.Companion.actionOnChild
import it.xabaras.android.espresso.recyclerviewchildactions.RecyclerViewChildActions.Companion.childViewAtPositionWithMatcher
import org.hamcrest.CoreMatchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class ChildActionsTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun performChildActions() {

        // Replace text of the EditText contained in the 3rd item of the RecyclerView with the one cointained in REPLACE_TEXT
        onView(ViewMatchers.withId(R.id.recyclerView))
            .perform(
                actionOnItemAtPosition<ViewHolder>(
                    3,
                    actionOnChild(replaceText(REPLACE_TEXT), R.id.txtDescription)
                )
            )

        // Check if text has actually changed
        onView(withId(R.id.recyclerView))
            .check(matches(
                childViewAtPositionWithMatcher(
                    R.id.txtDescription, 3, withText(
            REPLACE_TEXT)
                )
            ))

        // Check if btnAlert in the 3rd item of the RecyclerView is visible and enabled
        onView(withId(R.id.recyclerView))
            .check(matches(
                childViewAtPositionWithMatcher(
                    R.id.btnAlert,
                    3,
                    allOf(isDisplayed(), isEnabled())
                )
            ))

        // Click btnAlert inside 3rd item of the RecyclerView
        onView(withId(R.id.recyclerView))
            .perform(
                actionOnItemAtPosition<ViewHolder>(
                    3,
                   actionOnChild(click(), R.id.btnAlert)
                )
            )

        // check that a dialog is displayed with the text contained in REPLACE_TEXT
        onView(withText(REPLACE_TEXT)).inRoot(isDialog()).check(matches(isDisplayed()))

        // Check that ok button is displayed
        onView(withId(android.R.id.button3)).check(matches(isDisplayed()))

        // Click ok button
        onView(withId(android.R.id.button3)).perform(click())
    }

    companion object {
        const val REPLACE_TEXT = "Changed text with RecyclerViewChildActions"
    }
}
