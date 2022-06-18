package net.zerotask.libraries.android.compose.menuactions

/**
 * An enum describing the possible visibility of an [Action].
 */
enum class Visibility {
    /**
     * The action is always visible inside the app's TopBar.
     */
    ALWAYS_SHOW,

    /**
     * The action is hidden and shown inside the overflow menu.
     */
    HIDE,

    /**
     * The action is visible if there are slots left in the TopBar. If not, the action is shown
     * inside the overflow menu.
     */
    IF_POSSIBLE,

    /**
     * The action is never visible and is not shown inside the TopBar nor inside the overflow menu.
     */
    NEVER,
}
