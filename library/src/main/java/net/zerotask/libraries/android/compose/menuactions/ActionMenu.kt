package net.zerotask.libraries.android.compose.menuactions

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource

/**
 * The state of an [ActionMenu].
 *
 * @property visible **True** if the menu is visible to the user, **false** otherwise.
 */
data class ActionMenuState(
    var visible: MutableState<Boolean> = mutableStateOf(false),
)

/**
 *
 */
@Composable
fun rememberActionMenuState() = remember { ActionMenuState() }

/**
 * An [ActionMenu] consists of visible action displayed inside an app topbar or hidden inside a
 * [DropdownMenu].
 *
 * @param maxIcons The number of actions that are displayed inside the app topbar before actions
 * are shown inside the overflow menu.
 * @param state The ActionMenu state.
 * @param actions The list of actions.
 */
@Composable
fun RowScope.ActionMenu(
    maxIcons: Int = 2,
    state: ActionMenuState = rememberActionMenuState(),
    actions: List<Action>,
) {
    // Fast-return if the user added zero items.
    if (actions.isEmpty()) return

    // Calculate the lists of visible actions and actions inside the action menu.
    val (topBarActions, menuActions) = remember(maxIcons, actions) {
        calculateIconAndMenuLists(maxIcons, actions)
    }

    topBarActions.forEach { action ->
        key(action.hashCode()) {
            val text = stringResource(action.title)

            if (action.icon != null) {
                IconButton(onClick = action.onClick) {
                    Icon(action.icon,
                        contentDescription = action.iconContentDescription?.let { stringResource(it) })
                }
            } else {
                TextButton(onClick = action.onClick) {
                    Text(text = text)
                }
            }
        }
    }

    var visible by state.visible
    if (menuActions.isNotEmpty()) {
        IconButton(onClick = { visible = true }) {
            Icon(Icons.Default.MoreVert,
                contentDescription = stringResource(id = R.string.menu_more_content_description))
        }

        DropdownMenu(expanded = visible, onDismissRequest = {
            visible =
                false
        }) {
            menuActions.forEach { action ->
                key(action.hashCode()) {
                    DropdownMenuItem(onClick = {
                        visible = false
                        action.onClick()
                    }, text = {
                        Text(text = stringResource(action.title))
                    })
                }
            }
        }
    }
}

private fun calculateIconAndMenuLists(maxIcons: Int, actions: List<Action>): ActionLists {
    var itemsVisibleInTopBar = 0
    var itemsVisibleInMenu = 0
    var itemsToBeDecided = 0

    actions.forEach { action ->
        when (action.visibility) {
            Visibility.ALWAYS_SHOW -> itemsVisibleInTopBar++
            Visibility.HIDE -> {}
            Visibility.IF_POSSIBLE -> itemsToBeDecided++
            Visibility.NEVER -> itemsVisibleInMenu++
        }
    }

    var topBarActionSlotsLeft = maxIcons - itemsVisibleInTopBar
    val topBarActions = mutableListOf<Action>()
    val menuActions = mutableListOf<Action>()

    actions.forEach { action ->
        when (action.visibility) {
            Visibility.ALWAYS_SHOW -> menuActions.add(action)
            Visibility.HIDE -> {}
            Visibility.IF_POSSIBLE -> {
                if (topBarActionSlotsLeft > 0) {
                    topBarActionSlotsLeft--
                    topBarActions.add(action)
                } else {
                    menuActions.add(action)
                }
            }
            Visibility.NEVER -> topBarActions.add(action)
        }
    }

    return ActionLists(topBarActions, menuActions)
}

private data class ActionLists(
    val topBarActions: List<Action>,
    val menuActions: List<Action>,
)
