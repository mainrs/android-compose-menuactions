# Compose Action Menu Items

> Based on https://gist.github.com/MachFour/369ebb56a66e2f583ebfb988dda2decf, originally licensed
> under [CC BY 4.0](https://creativecommons.org/licenses/by/4.0/).
>
> The idea has been extended on and appropriate changes have been made.

# Installation

First, make sure that https://jitpack.io has been declared as a Maven repository. Then just declare
the dependency either in Gradle

```groovy
dependencies {
    implementation 'com.github.mainrs:android-compose-menuactions:1.0.0'
}
```

or the new Kotlin DSL

```kotlin
dependencies {
    implementation("com.github.mainrs:android-compose-menuactions:1.0.0")
}
```

# Usage

```kotlin
@Composable
fun MainContent() {
    SmallTopAppBar(title = { Text(text = "Test App") }, actions = {
        ActionMenu(actions = listOf(
            Action(title = R.string.synchronize,
                icon = Icons.Default.Sync,
                iconContentDescription = R.string.synchronize_cd,
                onClick = {}),

            Action(title = R.string.settings,
                icon = Icons.Default.Settings,
                iconContentDescription = R.string.settings_cd,
                onClick = {}),
        ))
    })
}
```

There is a small example app under [./app] that also showcases how the `maxIcons` property
influences the menu layout.

# License

This project is licensed under the [MIT license](./license).
