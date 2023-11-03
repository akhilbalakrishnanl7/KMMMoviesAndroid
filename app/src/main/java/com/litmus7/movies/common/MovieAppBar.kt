package com.litmus7.movies.common

import android.widget.ImageButton
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MovieAppBar(
    modifier: Modifier = Modifier,
    canNavigateBack: Boolean,
    currentScreen: Destination,
    onNavigateBack: () -> Unit
) {
    Surface(
        modifier = modifier.fillMaxWidth()
            .height(56.dp),
        shadowElevation = 4.dp,
        /**Appbar surface color*/
        color = MaterialTheme.colorScheme.primary
    ) {
        Row(
            modifier = modifier.padding(start = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AnimatedVisibility(visible = canNavigateBack) {
                IconButton(onClick = onNavigateBack) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "back",
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
                Spacer(modifier = modifier.width(24.dp))
            }
            Text(
                text = currentScreen.title,
                /**Appbar title size*/
                style = MaterialTheme.typography.titleMedium,
                modifier = modifier.padding(12.dp),
                /**Color of appbar title*/
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}