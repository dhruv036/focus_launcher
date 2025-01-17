package dev.mslalith.focuslauncher.androidtest.shared

import dev.mslalith.focuslauncher.data.model.App

object TestApps {
    val Chrome = App(name = "Chrome", packageName = "com.android.chrome", isSystem = true)
    val Youtube = App(name = "Youtube", packageName = "com.android.youtube", isSystem = false)
    val Phone = App(name = "Phone", packageName = "com.android.phone", isSystem = true)

    val all = listOf(Chrome, Youtube, Phone)
}
