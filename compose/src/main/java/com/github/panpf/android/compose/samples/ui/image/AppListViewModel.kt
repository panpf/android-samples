package com.github.panpf.android.compose.samples.ui.image

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AppListViewModel(application: Application) : AndroidViewModel(application) {

    private var _appListFlow: MutableStateFlow<List<App>> = MutableStateFlow(emptyList())
    val appListFlow: StateFlow<List<App>> = _appListFlow

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val packageManager = getApplication<Application>().packageManager
            val appList = packageManager.getInstalledPackages(0).map {
                val appName = it.applicationInfo.loadLabel(packageManager).toString()
                App(
                    appName,
                    it.packageName,
                    it.versionName.orEmpty(),
                    it.versionCode,
                )
            }
            _appListFlow.value = appList
        }
    }
}

data class App(val name: String, val packageName: String, val versionName: String, val versionCode: Int)