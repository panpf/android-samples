package com.github.panpf.android.compose.samples.ui.modifier

import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.list.BaseLinkGroupListFragment
import com.github.panpf.android.samples.model.Link
import com.github.panpf.android.samples.model.LinkGroup

class ModifierFragment : BaseLinkGroupListFragment() {

    override fun getTitle(): String {
        return "Modifier"
    }

    override fun buildLinkGroupList(): List<LinkGroup> = listOf(
        LinkGroup(
            "Actions", listOf(
                Link(
                    "Scope: Any - Modifier.clickable",
                    R.id.action_global_modifierClickableFragment
                ),
                Link("Scope: Any - Modifier.combinedClickable"),// todo Implementation
                Link("Scope: Any - Modifier.draggable"),// todo Implementation
                Link("Scope: Any - Modifier.selectableGroup"),// todo Implementation
                Link("Scope: Any - Modifier.selectable"),// todo Implementation
                Link("Scope: Any - Modifier.swipeable"),// todo Implementation
                Link("Scope: Any - Modifier.toggleable"),// todo Implementation
                Link("Scope: Any - Modifier.triStateToggleable"),// todo Implementation
            )
        ),
        LinkGroup(
            "Alignment", listOf(
                Link("Scope: BoxScope - Modifier.align"),// todo Implementation
                Link("Scope: RowScope - Modifier.alignBy"),// todo Implementation
                Link("Scope: RowScope - Modifier.alignByBaseline"),// todo Implementation
                Link("Scope: ColumnScope - Modifier.align"),// todo Implementation
                Link("Scope: ColumnScope - Modifier.alignBy"),// todo Implementation
            )
        ),
        LinkGroup(
            "Animation", listOf(
                Link("Scope: LazyItemScope - Modifier.animateItemPlacement"),// todo Implementation
                Link("Scope: AnimatedVisibilityScope - Modifier.animateEnterExit"),// todo Implementation
            )
        ),
        LinkGroup(
            "Border", listOf(
                Link("Scope: Any - Modifier.border", R.id.action_global_modifierBorderFragment),
            )
        ),
        LinkGroup(
            "Drawing", listOf(
                Link("Scope: Any - Modifier.alpha", R.id.action_global_modifierAlphaFragment),
                Link(
                    "Scope: Any - Modifier.background",
                    R.id.action_global_modifierBackgroundFragment
                ),
                Link("Scope: Any - Modifier.clip", R.id.action_global_modifierClipFragment),
                Link("Scope: Any - Modifier.clipToBounds"),// todo Implementation
                Link("Scope: Any - Modifier.drawBehind"),// todo Implementation
                Link("Scope: Any - Modifier.drawWithCache"),// todo Implementation
                Link("Scope: Any - Modifier.drawWithContent"),// todo Implementation
                Link("Scope: Any - Modifier.indication"),// todo Implementation
                Link("Scope: Any - Modifier.paint"),// todo Implementation
                Link("Scope: Any - Modifier.shadow", R.id.action_global_modifierShadowFragment),
                Link("Scope: Any - Modifier.safeDrawingPadding"),// todo Implementation
                Link("Scope: Any - Modifier.zIndex"),// todo Implementation
            )
        ),
        LinkGroup(
            "Focus", listOf(
                Link("Scope: Any - Modifier.focusModifier"),// todo Implementation
                Link("Scope: Any - Modifier.focusTarget"),// todo Implementation
                Link("Scope: Any - Modifier.focusOrder"),// todo Implementation
                Link("Scope: Any - Modifier.focusProperties"),// todo Implementation
                Link("Scope: Any - Modifier.focusRequester"),// todo Implementation
                Link("Scope: Any - Modifier.focusGroup"),// todo Implementation
                Link("Scope: Any - Modifier.focusable"),// todo Implementation
                Link("Scope: Any - Modifier.onFocusChanged"),// todo Implementation
                Link("Scope: Any - Modifier.onFocusEvent"),// todo Implementation
                Link("Scope: Any - Modifier.onFocusedBoundsChanged"),// todo Implementation
            )
        ),
        LinkGroup(
            "Graphics", listOf(
                Link("Scope: Any - Modifier.graphicsLayer"),// todo Implementation
                Link("Scope: Any - Modifier.toolingGraphicsLayer"),// todo Implementation
            )
        ),
        LinkGroup(
            "Keyboard", listOf(
                Link("Scope: Any - Modifier.onKeyEvent"),// todo Implementation
                Link("Scope: Any - Modifier.onPreviewKeyEvent"),// todo Implementation
            )
        ),
        LinkGroup(
            "Layout", listOf(
                Link("Scope: Any - Modifier.layoutId"),// todo Implementation
                Link("Scope: Any - Modifier.layout"),// todo Implementation
                Link("Scope: Any - Modifier.onGloballyPositioned"),// todo Implementation
            )
        ),
        LinkGroup(
            "Padding", listOf(
                Link("Scope: Any - Modifier.absolutePadding"),// todo Implementation
                Link("Scope: Any - Modifier.captionBarPadding"),// todo Implementation
                Link("Scope: Any - Modifier.displayCutoutPadding"),// todo Implementation
                Link("Scope: Any - Modifier.imePadding"),// todo Implementation
                Link("Scope: Any - Modifier.mandatorySystemGesturesPadding"),// todo Implementation
                Link("Scope: Any - Modifier.navigationBarsPadding"),// todo Implementation
                Link("Scope: Any - Modifier.paddingFrom"),// todo Implementation
                Link("Scope: Any - Modifier.paddingFromBaseline"),// todo Implementation
                Link(
                    "Scope: Any - Modifier.padding",
                    R.id.action_global_modifierPaddingFragment
                ),
                Link("Scope: Any - Modifier.safeContentPadding"),// todo Implementation
                Link("Scope: Any - Modifier.safeGesturesPadding"),// todo Implementation
                Link("Scope: Any - Modifier.statusBarsPadding"),// todo Implementation
                Link("Scope: Any - Modifier.systemBarsPadding"),// todo Implementation
                Link("Scope: Any - Modifier.systemGesturesPadding"),// todo Implementation
                Link("Scope: Any - Modifier.waterfallPadding"),// todo Implementation
                Link("Scope: Any - Modifier.windowInsetsPadding"),// todo Implementation
            )
        ),
        LinkGroup(
            "Pointer", listOf(
                Link("Scope: Any - Modifier.pointerHoverIcon"),// todo Implementation
                Link("Scope: Any - Modifier.pointerInteropFilter"),// todo Implementation
                Link("Scope: Any - Modifier.pointerInput"),// todo Implementation
            )
        ),
        LinkGroup(
            "Position", listOf(
                Link("Scope: Any - Modifier.absoluteOffset"),// todo Implementation
                Link("Scope: Any - Modifier.offset", R.id.action_global_modifierOffsetFragment),
                Link("Scope: TabRowDefaults - Modifier.tabIndicatorOffset"),// todo Implementation
            )
        ),
        LinkGroup(
            "Semantics", listOf(
                Link("Scope: Any - Modifier.progressSemantics"),// todo Implementation
                Link("Scope: Any - Modifier.clearAndSetSemantics"),// todo Implementation
                Link("Scope: Any - Modifier.semantics"),// todo Implementation
            )
        ),
        LinkGroup(
            "Scroll", listOf(
                Link("Scope: Any - Modifier.clipScrollableContainer"),// todo Implementation
                Link(
                    "Scope: Any - Modifier.horizontalScroll",
                    R.id.action_global_modifierHorizontalScrollFragment
                ),
                Link("Scope: Any - Modifier.imeNestedScroll"),// todo Implementation
                Link("Scope: Any - Modifier.nestedScroll"),// todo Implementation
                Link("Scope: Any - Modifier.overscroll"),// todo Implementation
                Link("Scope: Any - Modifier.onPreRotaryScrollEvent"),// todo Implementation
                Link("Scope: Any - Modifier.onRotaryScrollEvent"),// todo Implementation
                Link("Scope: Any - Modifier.relocationRequester"),// todo Implementation
                Link("Scope: Any - Modifier.scrollAway"),// todo Implementation
                Link("Scope: Any - Modifier.scrollable"),// todo Implementation
                Link(
                    "Scope: Any - Modifier.verticalScroll",
                    R.id.action_global_modifierVerticalScrollFragment
                ),
            )
        ),
        LinkGroup(
            "Size", listOf(
                Link("Scope: Any - Modifier.animateContentSize"),// todo Implementation
                Link(
                    "Scope: Any - Modifier.aspectRatio",
                    R.id.action_global_modifierAspectRatioFragment
                ),
                Link("Scope: Any - Modifier.defaultMinSize"),// todo Implementation
                Link("Scope: Any - Modifier.fillMaxHeight"),// todo Implementation
                Link("Scope: Any - Modifier.fillMaxSize"),// todo Implementation
                Link("Scope: Any - Modifier.fillMaxWidth"),// todo Implementation
                Link("Scope: Any - Modifier.height"),// todo Implementation
                Link("Scope: Any - Modifier.heightIn"),// todo Implementation
                Link("Scope: Any - Modifier.onSizeChanged"),// todo Implementation
                Link("Scope: Any - Modifier.requiredHeight"),// todo Implementation
                Link("Scope: Any - Modifier.requiredHeightIn"),// todo Implementation
                Link("Scope: Any - Modifier.requiredSize"),// todo Implementation
                Link("Scope: Any - Modifier.requiredSizeIn"),// todo Implementation
                Link("Scope: Any - Modifier.requiredWidth"),// todo Implementation
                Link("Scope: Any - Modifier.requiredWidthIn"),// todo Implementation
                Link("Scope: Any - Modifier.size", R.id.action_global_modifierSizeFragment),
                Link("Scope: Any - Modifier.sizeIn"),// todo Implementation
                Link("Scope: Any - Modifier.width"),// todo Implementation
                Link("Scope: Any - Modifier.width"),// todo Implementation
                Link("Scope: Any - Modifier.widthIn"),// todo Implementation
                Link("Scope: Any - Modifier.wrapContentHeight"),// todo Implementation
                Link("Scope: Any - Modifier.wrapContentSize"),// todo Implementation
                Link("Scope: Any - Modifier.wrapContentWidth"),// todo Implementation
                Link("Scope: Any - Modifier.windowInsetsBottomHeight"),// todo Implementation
                Link("Scope: Any - Modifier.windowInsetsEndWidth"),// todo Implementation
                Link("Scope: Any - Modifier.windowInsetsStartWidth"),// todo Implementation
                Link("Scope: Any - Modifier.windowInsetsTopHeight"),// todo Implementation
                Link("Scope: BoxScope - Modifier.matchParentSize"),// todo Implementation
                Link("Scope: ColumnScope - Modifier.weight"),// todo Implementation
                Link("Scope: LazyItemScope - Modifier.fillParentMaxHeight"),// todo Implementation
                Link("Scope: LazyItemScope - Modifier.fillParentMaxSize"),// todo Implementation
                Link("Scope: LazyItemScope - Modifier.fillParentMaxWidth"),// todo Implementation
                Link("Scope: RowScope - Modifier.weight"),// todo Implementation
            )
        ),
        LinkGroup(
            "Testing", listOf(
                Link("Scope: Any - Modifier.testTag"),// todo Implementation
            )
        ),
        LinkGroup(
            "Transformations", listOf(
                Link("Scope: Any - Modifier.rotate", R.id.action_global_modifierRotateFragment),
                Link("Scope: Any - Modifier.scale", R.id.action_global_modifierScaleFragment),
                Link("Scope: Any - Modifier.transformable"),// todo Implementation
            )
        ),
        LinkGroup(
            "Other", listOf(
                Link("Scope: Any - Modifier.basicMarquee"),// todo Implementation
                Link("Scope: Any - Modifier.blur", R.id.action_global_modifierBlurFragment),
                Link("Scope: Any - Modifier.bringIntoViewRequester"),// todo Implementation
                Link("Scope: Any - Modifier.bringIntoViewResponder"),// todo Implementation
                Link("Scope: Any - Modifier.composed"),// todo Implementation
                Link("Scope: Any - Modifier.consumedWindowInsets"),// todo Implementation
                Link("Scope: Any - Modifier.edgeSwipeToDismiss"),// todo Implementation
                Link("Scope: Any - Modifier.excludeFromSystemGesture"),// todo Implementation
                Link("Scope: Any - Modifier.hoverable"),// todo Implementation
                Link("Scope: Any - Modifier.inspectable"),// todo Implementation
                Link("Scope: Any - Modifier.magnifier"),// todo Implementation
                Link("Scope: Any - Modifier.modifierLocalConsumer"),// todo Implementation
                Link("Scope: Any - Modifier.modifierLocalProvider"),// todo Implementation
                Link("Scope: Any - Modifier.onPlaced"),// todo Implementation
                Link("Scope: Any - Modifier.onRelocationRequest"),// todo Implementation
                Link("Scope: Any - Modifier.motionEventSpy"),// todo Implementation
                Link("Scope: Any - Modifier.pullRefreshIndicatorTransform"),// todo Implementation
                Link("Scope: Any - Modifier.pullRefresh"),// todo Implementation
                Link("Scope: Any - Modifier.systemGestureExclusion"),// todo Implementation
                Link("Scope: Any - Modifier.withConsumedWindowInsets"),// todo Implementation
            )
        ),
    )
}