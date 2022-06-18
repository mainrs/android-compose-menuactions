package net.zerotask.libraries.android.compose.menuactions

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector

data class Action(
    @StringRes val title: Int,
    val icon: ImageVector? = null,
    @StringRes val iconContentDescription: Int? = null,
    val visibility: Visibility = Visibility.IF_POSSIBLE,
    val onClick: () -> Unit,
)
