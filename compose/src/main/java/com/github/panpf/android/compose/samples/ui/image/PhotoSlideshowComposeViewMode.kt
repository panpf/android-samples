package com.github.panpf.android.compose.samples.ui.image

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class PhotoSlideshowComposeViewMode(application: Application) : AndroidViewModel(application) {
    val horizontalLayout = MutableStateFlow(true)
}