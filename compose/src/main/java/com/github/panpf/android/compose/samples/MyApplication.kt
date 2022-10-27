package com.github.panpf.android.compose.samples

import android.app.ActivityManager
import coil.memory.MemoryCache as CoilMemoryCache
import com.github.panpf.sketch.cache.MemoryCache as SketchMemoryCache
import android.app.Application
import android.content.Context
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.util.DebugLogger
import com.github.panpf.sketch.Sketch
import com.github.panpf.sketch.SketchFactory
import com.github.panpf.sketch.cache.internal.LruMemoryCache
import com.github.panpf.sketch.util.Logger

class MyApplication : Application(), SketchFactory, ImageLoaderFactory {

    private val memoryCacheMaxSize: Long by lazy {
        getSystemService(Context.ACTIVITY_SERVICE).let { it as ActivityManager }
            .memoryClass.times(1024 * 1024).times(0.4f).toLong()
    }

    override fun createSketch(): Sketch {
        return Sketch.Builder(this)
            .memoryCache(LruMemoryCache(memoryCacheMaxSize.div(2)))
            .logger(Logger(Logger.Level.DEBUG))
            .build()
    }

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .memoryCache(
                coil.memory.MemoryCache.Builder(this)
                    .maxSizeBytes(memoryCacheMaxSize.div(2).toInt())
                    .build()
            )
            .logger(DebugLogger())
            .build()
    }
}