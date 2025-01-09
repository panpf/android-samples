package com.github.panpf.android.view.samples

import coil3.PlatformContext as CoilSketchPlatformContext
import com.github.panpf.sketch.PlatformContext as SketchPlatformContext
import android.app.ActivityManager
import android.app.Application
import android.content.Context
import coil3.ImageLoader
import coil3.SingletonImageLoader
import coil3.util.DebugLogger
import com.github.panpf.android.samples.utils.CoilAppIconUriFetcher
import com.github.panpf.sketch.SingletonSketch
import com.github.panpf.sketch.Sketch
import com.github.panpf.sketch.cache.internal.LruMemoryCache
import com.github.panpf.sketch.util.Logger

class MyApplication : Application(), SingletonSketch.Factory, SingletonImageLoader.Factory {

    private val memoryCacheMaxSize: Long by lazy {
        getSystemService(Context.ACTIVITY_SERVICE).let { it as ActivityManager }
            .memoryClass.times(1024 * 1024).times(0.4f).toLong()
    }

    override fun createSketch(context: SketchPlatformContext): Sketch {
        return Sketch.Builder(this)
            .memoryCache(LruMemoryCache(memoryCacheMaxSize.div(2)))
            .logger(level = Logger.Level.Debug)
            .build()
    }

    override fun newImageLoader(context: CoilSketchPlatformContext): ImageLoader {
        return ImageLoader.Builder(this)
            .memoryCache(
                coil3.memory.MemoryCache.Builder()
                    .maxSizeBytes(memoryCacheMaxSize.div(2))
                    .build()
            ).components {
                add(CoilAppIconUriFetcher.Factory(this@MyApplication))
            }
            .logger(DebugLogger())
            .build()
    }
}