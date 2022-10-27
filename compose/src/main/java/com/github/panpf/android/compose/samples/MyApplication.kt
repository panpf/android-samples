package com.github.panpf.android.compose.samples

import coil.memory.MemoryCache as CoilMemoryCache
import com.github.panpf.sketch.cache.MemoryCache as SketchMemoryCache
import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.util.DebugLogger
import com.github.panpf.sketch.Sketch
import com.github.panpf.sketch.SketchFactory
import com.github.panpf.sketch.util.Logger

class MyApplication : Application(), SketchFactory, ImageLoaderFactory {

    override fun createSketch(): Sketch {
        return Sketch.Builder(this)
            // 因为集成了多个图片加载框架，如果都开启内存缓存的话内存占用会很高
            .memoryCache(EmptySketchMemoryCache())
            .logger(Logger(Logger.Level.DEBUG))
            .build()
    }

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            // 因为集成了多个图片加载框架，如果都开启内存缓存的话内存占用会很高
            .memoryCache(EmptyCoilMemoryCache())
            .logger(DebugLogger())
            .build()
    }

    class EmptySketchMemoryCache : SketchMemoryCache {
        @Suppress("UNUSED_PARAMETER")
        override var logger: Logger?
            get() = null
            set(value) {}
        override val maxSize: Long
            get() = 0
        override val size: Long
            get() = 0

        override fun clear() {

        }

        override fun exist(key: String): Boolean {
            return false
        }

        override fun get(key: String): SketchMemoryCache.Value? {
            return null
        }

        override fun keys(): Set<String> {
            return emptySet()
        }

        override fun put(
            key: String,
            value: SketchMemoryCache.Value
        ): Boolean {
            return false
        }

        override fun remove(key: String): SketchMemoryCache.Value? {
            return null
        }

        override fun trim(level: Int) {

        }
    }

    class EmptyCoilMemoryCache : CoilMemoryCache {
        override val keys: Set<CoilMemoryCache.Key>
            get() = emptySet()
        override val maxSize: Int
            get() = 0
        override val size: Int
            get() = 0

        override fun clear() {

        }

        override fun get(key: CoilMemoryCache.Key): CoilMemoryCache.Value? {
            return null
        }

        override fun remove(key: CoilMemoryCache.Key): Boolean {
            return false
        }

        override fun set(key: CoilMemoryCache.Key, value: CoilMemoryCache.Value) {

        }

        override fun trimMemory(level: Int) {

        }
    }
}