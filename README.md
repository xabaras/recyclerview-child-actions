# RecyclerViewChildActions

This is a simple API to perform actions and checks on child views of a RecycleView's item in Espresso tests.

*RecyclerViewChildActions* has been designed as an extension to RecyclerViewActions from espresso-contrib package and thus from that class it depends.

Get it via Gradle
```groovy
implementation 'it.xabaras.android.espresso:recyclerview-child-actions:1.0'
```
or Maven
```xml
<dependency>
  <groupId>it.xabaras.android.espresso</groupId>
  <artifactId>recyclerview-child-actions</artifactId>
  <version>1.0</version>
  <type>pom</type>
</dependency>
```

Or download the latest AAR and add it to your project's libraries.

[ ![Download](https://api.bintray.com/packages/xabaras/maven/recyclerview-child-actions/images/download.svg?version=1.0) ](https://bintray.com/xabaras/maven/recyclerview-child-actions/1.0/link)

## Usage ##

You can use *RecyclerViewChildActions* to perform [ViewActions](https://developer.android.com/reference/android/support/test/espresso/action/ViewActions) or [ViewAssertions](https://developer.android.com/training/testing/espresso/basics) on Views which are descendants of a RecyclerView's item.

Functionality is exposed via two convenience functions implemented inside the RecyclerViewChildActions class:

```kotlin
fun actionOnChild(action: ViewAction, childId: Int) : ViewAction
```
which performs an action on a view with a given id inside a RecyclerView's item

```kotlin
fun childOfViewAtPositionWithMatcher(childId: Int, position: Int, childMatcher: Matcher<View>) : Matcher<View>
```

which checks that the matcher childMatcher matches a view having a given id inside a RecyclerView's item (given its position).

### actionOnChild
For the sake of simplicity let's suppose we have a RecyclerView whose items are editable notes in a vertical list and we want to change the text contained in one of them, lets say 3rd item.
With reference to the RecyclerViewActions api, you can use RecylerViewChildActions to perform a replaceText action as follows

```kotlin
onView(ViewMatchers.withId(R.id.recyclerView))
    .perform(
        actionOnItemAtPosition<ViewHolder>(
            3,
            actionOnChild(
                replaceText("I changed this text via RecyclerViewChildActions"),
                R.id.txtDescription
            )
        )
    )
```
### childOfViewAtPositionWithMatcher
Again if you want to check that text of a given row (say the 3rd) has actually been changed, you can issue a check given the childId, the position inside the RecyclerView and a matcher, like in the following code snippet

```kotlin
onView(withId(R.id.recyclerView))
    .check(
        matches(
            childOfViewAtPositionWithMatcher(
                R.id.txtDescription,
                3,
                withText("I changed this text via RecyclerViewChildActions")
            )
        )
    )
```

Than you for using *RecylerViewChildActions*.

Any suggestion or contribution to extend/improve this api is warmly welcome.
