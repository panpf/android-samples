package com.github.panpf.android.compose.samples.ui.animation

import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.list.BaseLinkListFragment
import com.github.panpf.android.samples.model.Link

class AnimationsFragment : BaseLinkListFragment() {

    override fun getTitle(): String {
        return "Animations"
    }

    override fun buildLinkList(): List<Link> = listOf(
        Link("AnimatedVisibility", R.id.action_global_animatedVisibilityFragment),
        Link("AnimatedContent", R.id.action_global_animatedContentFragment),
        Link("animatedContentSize", R.id.action_global_animatedContentSizeFragment),
        Link("animate*AsState", R.id.action_global_animateAsStateFragment),
        Link("updateTransition", R.id.action_global_updateTransitionFragment),
        Link("Crossfade", R.id.action_global_crossfadeFragment),
        Link(
            "rememberInfiniteTransition",
            R.id.action_global_rememberInfiniteTransitionFragment
        ),
        Link("AnimationSpec", R.id.action_global_animationSpecFragment),
        Link("Easing", R.id.action_global_easingFragment),
        Link("Animatable", R.id.action_global_animatableFragment),
        Link("Animation", R.id.action_global_animationFragment),
    )
}