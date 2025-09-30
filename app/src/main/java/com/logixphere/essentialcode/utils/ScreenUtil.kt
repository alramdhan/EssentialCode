package com.logixphere.essentialcode.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

class ScreenUtil {
    companion object {
        @Composable
        fun getScreenHeight(v: Float = 1.0f): Dp {
            val configuration = LocalConfiguration.current

            return (configuration.screenHeightDp * v).dp
        }

        @Composable
        fun getScreenWidth(v: Float = 1.0f): Dp {
            val configuration = LocalConfiguration.current

            return (configuration.screenWidthDp * v).dp
        }
    }
}