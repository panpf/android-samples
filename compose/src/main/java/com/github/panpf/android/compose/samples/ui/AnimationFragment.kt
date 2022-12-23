package com.github.panpf.android.compose.samples.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.model.Link
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.github.panpf.android.compose.samples.ui.base.list.LinkList

class AnimationFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "Animation"
    }

    @Composable
    override fun DrawContent() {
        val links = remember {
            listOf(
                Link("AnimatedVisibility", R.id.action_global_animatedVisibilityFragment),
                // todo animate*AsState
                //  AnimatedContent
                //  animateContentSize
                //  Crossfade
                //  updateTransition
                //  rememberInfiniteTransition
                //  Animatable
                //  Animation
                //  AnimationSpec
                //  Easing
                //  AnimationVector
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