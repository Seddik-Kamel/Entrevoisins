package com.openclassrooms.entrevoisins.neighbour_list;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.core.IsNull.notNullValue;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class NeighbourDetailActivityTest {

    private static final int ITEMS_COUNT_FAVORITE = 1;

    private ListNeighbourActivity mActivity;
    private String mTitleFavorite;

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
        mTitleFavorite = mActivity.getApplicationContext().getResources().getString(R.string.title_item_favorite);
    }

    @Test
    public void myNeighboursList_clickItemList_shouldLaunchNeighbourDetailsActivity() {
        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.list_neighbours),
                        withParent(withId(R.id.container))));
        recyclerView.perform(actionOnItemAtPosition(0, click()));

       onView(ViewMatchers.withId(R.id.neighbour_id)).check(matches(isDisplayed()));
    }

    @Test
    public void neighbourDetails_activityNeighbourDetailsLaunched_shouldDisplayNeighbourName() {
        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.list_neighbours),
                        withParent(withId(R.id.container))));
        recyclerView.perform(actionOnItemAtPosition(0, click()));
        onView(ViewMatchers.withId(R.id.last_name)).check(matches(withText("Caroline")));
    }

    @Test
    public void neighbourDetails_activityNeighbourDetailsLaunched_shouldDisplayOnlyListFromFavorite() {

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.list_neighbours),
                        withParent(withId(R.id.container))));
        recyclerView.perform(actionOnItemAtPosition(0, click()));
        onView(ViewMatchers.withId(R.id.add_favorite_button)).perform(click());
        onView(ViewMatchers.withId(R.id.come_back_button)).perform(click());

        ViewInteraction tabView = onView(
                allOf(withContentDescription(mTitleFavorite),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tabs),
                                        0),
                                1),
                        isDisplayed()));
        tabView.perform(click());
        onView(ViewMatchers.withId(R.id.list_neighbours_favorite))
                .check(withItemCount(1))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(ViewMatchers.withId(R.id.last_name)).check(matches(withText("Caroline")));

    }

    @Test
    public void neighbourDetails_deleteActionFavorite_shouldRemoveItem() {

        //add neighbour to favorites
        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.list_neighbours),
                        withParent(withId(R.id.container))));
        recyclerView.perform(actionOnItemAtPosition(0, click()));
        onView(ViewMatchers.withId(R.id.add_favorite_button)).perform(click());
        onView(ViewMatchers.withId(R.id.come_back_button)).perform(click());

        //Go to the favorite tab
        ViewInteraction tabView = onView(
                allOf(withContentDescription(mTitleFavorite),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tabs),
                                        0),
                                1),
                        isDisplayed()));
        tabView.perform(click());

        //Check number of favorite
        onView(ViewMatchers.withId(R.id.list_neighbours_favorite)).check(withItemCount(ITEMS_COUNT_FAVORITE));
        // Delete a neighbour
        onView(ViewMatchers.withId(R.id.list_neighbours_favorite))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, new DeleteViewAction()));
        // Then : the number of element is 0
        onView(ViewMatchers.withId(R.id.list_neighbours_favorite)).check(withItemCount(ITEMS_COUNT_FAVORITE-1));


    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
