package com.github.panpf.android.view.samples.ui.link

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.panpf.android.samples.model.Link
import com.github.panpf.android.view.samples.databinding.RecyclerBinding
import com.github.panpf.android.view.samples.ui.base.ToolbarBindingFragment
import com.github.panpf.assemblyadapter.recycler.AssemblyRecyclerAdapter

abstract class BaseLinkListFragment : ToolbarBindingFragment<RecyclerBinding>() {

    private var pendingStartLink: Link? = null
    private val permissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { grantedMap ->
            val pendingStartLink = pendingStartLink ?: return@registerForActivityResult
            this@BaseLinkListFragment.pendingStartLink = null
            requestLinkPermissionsResult(grantedMap, pendingStartLink)
        }

    override fun onViewCreated(
        toolbar: Toolbar,
        binding: RecyclerBinding,
        savedInstanceState: Bundle?
    ) {
        binding.recycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = AssemblyRecyclerAdapter(
                itemFactoryList = listOf(
                    LinkItemFactory().setOnItemClickListener { _, _, _, _, data ->
                        startLink(data)
                    },
                    ListSeparatorItemFactory()
                ),
                initDataList = buildData()
            )
        }
    }

    abstract fun buildData(): List<Any>

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