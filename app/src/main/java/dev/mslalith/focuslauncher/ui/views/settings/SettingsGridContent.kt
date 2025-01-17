package dev.mslalith.focuslauncher.ui.views.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun <T> SettingsGridContent(
    modifier: Modifier = Modifier,
    itemModifier: Modifier = Modifier,
    columnSize: Int = 2,
    items: List<T>,
    content: @Composable (item: T) -> Unit
) {
    val chunkedItems by remember(items) {
        derivedStateOf { items.chunked(columnSize) }
    }

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        chunkedItems.forEach { rowItems ->
            Row {
                rowItems.forEach { item ->
                    Box(
                        modifier = itemModifier.weight(weight = 1f)
                    ) {
                        content(item)
                    }
                }

                if (rowItems.size < columnSize) {
                    val count = columnSize - rowItems.size
                    repeat(count) {
                        Box(modifier = itemModifier.weight(weight = 1f))
                    }
                }
            }
        }
    }
}
