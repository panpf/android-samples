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
                Link("AnimatedContent", R.id.action_global_animatedContentFragment),
                Link("animatedContentSize", R.id.action_global_animatedContentSizeFragment),
                Link("animate*AsState", R.id.action_global_animateAsStateFragment),
                Link("updateTransition", R.id.action_global_updateTransitionFragment),
                Link("Crossfade", R.id.action_global_crossfadeFragment),
                Link("rememberInfiniteTransition", R.id.action_global_rememberInfiniteTransitionFragment),
                // todo Animatable
                // todo Animation
                // todo AnimationSpec
                // todo Easing
                // todo AnimationVector
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