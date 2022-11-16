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
                Link("alpha", R.id.action_global_modifierAlphaFragment),
                Link("aspectRatio", R.id.action_global_modifierAspectRatioFragment),
                Link("background", R.id.action_global_modifierBackgroundFragment),
                Link("blur", R.id.action_global_modifierBlurFragment),
                Link("border", R.id.action_global_modifierBorderFragment),
                Link("clickable", R.id.action_global_modifierClickableFragment),
                Link("clip", R.id.action_global_modifierClipFragment),
                Link("horizontalScroll", R.id.action_global_modifierHorizontalScrollFragment),
                Link("offset", R.id.action_global_modifierOffsetFragment),
                Link("padding*", R.id.action_global_modifierPaddingFragment),
                Link("rotate", R.id.action_global_modifierRotateFragment),
                Link("scale", R.id.action_global_modifierScaleFragment),
                Link("shadow", R.id.action_global_modifierShadowFragment),
                Link("size*", R.id.action_global_modifierSizeFragment),
                Link("verticalScroll", R.id.action_global_modifierVerticalScrollFragment),
//                Link("nestedScroll,toolingGraphicsLayer（Implementation）"),   // todo Implementation
//                Link("graphicsLayer（Implementation）"),   // todo Implementation
//                Link("drawBehind,drawWithCache,drawWithContent"),   // todo Implementation
//                Link("draggable（Implementation）"),   // todo Implementation
//                Link("focusable,focusGroup,focusRequester,focusProperties（Implementation）"),   // todo Implementation
//                Link("onFocusChanged,onFocusedBoundsChanged,onFocusEvent（Implementation）"),   // todo Implementation
//                Link("onPlaced（Implementation）"),   // todo Implementation
//                Link("onKeyEvent（Implementation）"),   // todo Implementation
//                Link("onGloballyPositioned（Implementation）"),   // todo Implementation
//                Link("onSizeChanged（Implementation）"),   // todo Implementation
//                Link("paint（Implementation）"),   // todo Implementation
//                Link("transformable（Implementation）"),   // todo Implementation
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