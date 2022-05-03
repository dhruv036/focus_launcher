package dev.mslalith.focuslauncher.data.model

import android.graphics.drawable.Drawable

data class AppWithIcon(
    val name: String,
    val packageName: String,
    val icon: Drawable,
    val isSystem: Boolean
) {
    fun toApp() = App(
        name = name,
        packageName = packageName,
        isSystem = isSystem
    )
}
