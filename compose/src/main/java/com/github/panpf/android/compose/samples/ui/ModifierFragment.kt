package com.github.panpf.android.compose.samples.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.model.Link
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.github.panpf.android.compose.samples.ui.base.list.LinkList

class ModifierFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Modifier"
    }

    @Composable
    override fun DrawContent() {
        val links = remember {
            listOf(
                Link("size", R.id.action_global_modifierSizeFragment),
                Link("padding", R.id.action_global_modifierPaddingFragment),
                Link("border", R.id.action_global_modifierBorderFragment),
                Link("background", R.id.action_global_modifierBackgroundFragment),
                Link("clip", R.id.action_global_modifierClipFragment),
                Link("clickable", R.id.action_global_modifierClickableFragment),
                Link("shadow（Implementation）"),   // todo Implementation
                Link("alpha（Implementation）"),   // todo Implementation
                Link("blur（Implementation）"),   // todo Implementation
                Link("rotate（Implementation）"),   // todo Implementation
                Link("scale（Implementation）"),   // todo Implementation
                Link("aspectRatio（Implementation）"),   // todo Implementation
                Link("nestedScroll（Implementation）"),   // todo Implementation
                Link("absoluteOffset（Implementation）"),   // todo Implementation
                Link("drawBehind,drawWithCache,drawWithContent,safeDrawingPadding（Implementation）"),   // todo Implementation
                Link("draggable（Implementation）"),   // todo Implementation
                Link("displayCutoutPadding（Implementation）"),   // todo Implementation
                Link("focusable,focusGroup,focusRequester,focusProperties（Implementation）"),   // todo Implementation
                Link("onFocusChanged,onFocusedBoundsChanged,onFocusEvent（Implementation）"),   // todo Implementation
                Link("offset（Implementation）"),   // todo Implementation
                Link("onPlaced（Implementation）"),   // todo Implementation
                Link("onKeyEvent（Implementation）"),   // todo Implementation
                Link("onGloballyPositioned（Implementation）"),   // todo Implementation
                Link("onSizeChanged（Implementation）"),   // todo Implementation
                Link("paint（Implementation）"),   // todo Implementation
                Link("statusBarPadding（Implementation）"),   // todo Implementation
                Link("systemBarPadding（Implementation）"),   // todo Implementation
            )
        }
        LinkList(links) { _, link ->
            val nav = link.nav
            if (nav is Int) {
                findNavController().navigate(nav)
            } else if (nav is NavDirections) {
                findNavController().navigate(nav)
            }
        }
    }
}