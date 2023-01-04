package com.github.panpf.android.compose.samples.tools

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.net.Uri
import coil.ImageLoader
import coil.decode.DataSource
import coil.fetch.DrawableResult
import coil.fetch.FetchResult
import coil.fetch.Fetcher
import coil.request.Options
import com.github.panpf.sketch.fetch.AppIconUriFetcher
import com.github.panpf.sketch.request.UriInvalidException
import com.github.panpf.sketch.util.ifOrNull

class CoilAppIconUriFetcher(
    val context: Context,
    val packageName: String,
    val versionCode: Int,
) : Fetcher {

    override suspend fun fetch(): FetchResult? {
        val packageManager = context.packageManager
        val packageInfo: PackageInfo = try {
            packageManager.getPackageInfo(packageName, 0)
        } catch (e: PackageManager.NameNotFoundException) {
            throw Exception("Not found PackageInfo by '$packageName'", e)
        }
        @Suppress("DEPRECATION")
        if (packageInfo.versionCode != versionCode) {
            throw Exception("App versionCode mismatch, ${packageInfo.versionCode} != $versionCode")
        }
        val iconDrawable = packageInfo.applicationInfo.loadIcon(packageManager)
            ?: throw Exception("loadIcon return null '$packageName'")
        return DrawableResult(drawable = iconDrawable, isSampled = false, DataSource.DISK)
    }

    class Factory(val context: Context) : Fetcher.Factory<Uri> {
        override fun create(data: Uri, options: Options, imageLoader: ImageLoader): Fetcher? {
            return ifOrNull(AppIconUriFetcher.SCHEME.equals(data.scheme, ignoreCase = true)) {
                val packageName = data.authority
                    ?.takeIf { it.isNotEmpty() && it.isNotBlank() }
                    ?: throw UriInvalidException("App icon uri 'packageName' part invalid: $data")
                val versionCode = data.lastPathSegment
                    ?.takeIf { it.isNotEmpty() && it.isNotBlank() }
                    ?.toIntOrNull()
                    ?: throw UriInvalidException("App icon uri 'versionCode' part invalid: $data")
                CoilAppIconUriFetcher(context, packageName, versionCode)
            }
        }
    }
}