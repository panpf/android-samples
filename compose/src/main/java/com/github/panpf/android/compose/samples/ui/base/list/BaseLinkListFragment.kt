package com.github.panpf.android.compose.samples.ui.base.list

import android.os.Build
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.github.panpf.android.samples.model.Link

abstract class BaseLinkListFragment : Material3ComposeAppBarFragment() {

    private var pendingStartLink: Link? = null
    private val permissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { grantedMap ->
            val pendingStartLink = pendingStartLink ?: return@registerForActivityResult
            this@BaseLinkListFragment.pendingStartLink = null
            requestLinkPermissionsResult(grantedMap, pendingStartLink)
        }

    @Composable
    override fun DrawContent() {
        val links = remember { buildLinkList() }
        LinkList(links) { _, link ->
            startLink(link)
        }
    }

    abstract fun buildLinkList(): List<Link>

    private fun startLink(data: Link) {
        val minSdk = data.minSdk
        if (minSdk == null || Build.VERSION.SDK_INT >= minSdk) {
            val permissions = data.permissions
            if (permissions != null) {
                pendingStartLink = data
                permissionLauncher.launch(permissions.toTypedArray())
            } else {
                when (val nav = data.nav) {
                    is Int -> findNavController().navigate(nav)
                    is NavDirections -> findNavController().navigate(nav)
                }
            }
        } else {
            Toast.makeText(
                context,
                "Must be API $minSdk or above",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun requestLinkPermissionsResult(grantedMap: Map<String, Boolean>, data: Link) {
        if (grantedMap.values.all { it }) {
            when (val nav = data.nav) {
                is Int -> findNavController().navigate(nav)
                is NavDirections -> findNavController().navigate(nav)
            }
        } else {
            Toast.makeText(context, "Please grant permission", Toast.LENGTH_LONG).show()
        }
    }
}