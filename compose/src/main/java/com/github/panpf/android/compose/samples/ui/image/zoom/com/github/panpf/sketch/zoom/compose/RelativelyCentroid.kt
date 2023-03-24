package com.github.panpf.android.compose.samples.ui.image.zoom.com.github.panpf.sketch.zoom.compose

import androidx.annotation.FloatRange
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.isSpecified
import androidx.compose.ui.geometry.isUnspecified
import com.github.panpf.tools4j.date.Datex.y


/**
 * Constructs an RelativelyCentroid from the given x and y percentage
 */
@Stable
fun RelativelyCentroid(
    @FloatRange(from = 0.0, to = 1.0) x: Float,
    @FloatRange(from = 0.0, to = 1.0) y: Float
) = RelativelyCentroid(Offset(x, y))

/**
 * An immutable 2D floating-point percentage.
 */
@Immutable
@JvmInline
value class RelativelyCentroid internal constructor(internal val value: Offset) {

    @Stable
    val x: Float
        get() = value.x

    @Stable
    val y: Float
        get() = value.y

    @Stable
    operator fun component1(): Float = value.component1()

    @Stable
    operator fun component2(): Float = value.component2()

    /**
     * Returns a copy of this RelativelyCentroid instance optionally overriding the
     * x or y parameter
     */
    fun copy(
        @FloatRange(from = 0.0, to = 1.0) x: Float = this.x,
        @FloatRange(from = 0.0, to = 1.0) y: Float = this.y
    ) = RelativelyCentroid(value.copy(x, y))

    companion object {
        /**
         * An offset with zero magnitude.
         *
         * This can be used to represent the origin of a coordinate space.
         */
        @Stable
        val Zero = RelativelyCentroid(Offset.Zero)

//        /**
//         * An offset with infinite x and y components.
//         *
//         * See also:
//         *
//         *  * [isInfinite], which checks whether either component is infinite.
//         *  * [isFinite], which checks whether both components are finite.
//         */
//        // This is included for completeness, because [Size.infinite] exists.
//        @Stable
//        val Infinite = RelativelyCentroid(Offset.Infinite)

        /**
         * Represents an unspecified [RelativelyCentroid] value, usually a replacement for `null`
         * when a primitive value is desired.
         */
        @Stable
        val Unspecified = RelativelyCentroid(Offset.Unspecified)
    }

    @Stable
    fun isValid(): Boolean = value.isValid()

//    /**
//     * The magnitude of the offset.
//     *
//     * If you need this value to compare it to another [RelativelyCentroid]'s distance,
//     * consider using [getDistanceSquared] instead, since it is cheaper to compute.
//     */
//    @Stable
//    fun getDistance() = value.getDistance()
//
//    /**
//     * The square of the magnitude of the offset.
//     *
//     * This is cheaper than computing the [getDistance] itself.
//     */
//    @Stable
//    fun getDistanceSquared() = value.getDistanceSquared()

    /**
     * Unary negation operator.
     *
     * Returns an offset with the coordinates negated.
     *
     * If the [RelativelyCentroid] represents an arrow on a plane, this operator returns the
     * same arrow but pointing in the reverse direction.
     */
    @Stable
    operator fun unaryMinus(): RelativelyCentroid = RelativelyCentroid(value.unaryMinus())

    /**
     * Binary subtraction operator.
     *
     * Returns an offset whose [x] value is the left-hand-side operand's [x]
     * minus the right-hand-side operand's [x] and whose [y] value is the
     * left-hand-side operand's [y] minus the right-hand-side operand's [y].
     */
    @Stable
    operator fun minus(other: RelativelyCentroid): RelativelyCentroid =
        RelativelyCentroid(value.minus(other.value))

    /**
     * Binary addition operator.
     *
     * Returns an offset whose [x] value is the sum of the [x] values of the
     * two operands, and whose [y] value is the sum of the [y] values of the
     * two operands.
     */
    @Stable
    operator fun plus(other: RelativelyCentroid): RelativelyCentroid =
        RelativelyCentroid(value.plus(other.value))

    /**
     * Multiplication operator.
     *
     * Returns an offset whose coordinates are the coordinates of the
     * left-hand-side operand (an RelativelyCentroid) multiplied by the scalar
     * right-hand-side operand (a Float).
     */
    @Stable
    operator fun times(operand: Float): RelativelyCentroid =
        RelativelyCentroid(value.times(operand))

    /**
     * Division operator.
     *
     * Returns an offset whose coordinates are the coordinates of the
     * left-hand-side operand (an RelativelyCentroid) divided by the scalar right-hand-side
     * operand (a Float).
     */
    @Stable
    operator fun div(operand: Float): RelativelyCentroid =
        RelativelyCentroid(value.div(operand))

    /**
     * Modulo (remainder) operator.
     *
     * Returns an offset whose coordinates are the remainder of dividing the
     * coordinates of the left-hand-side operand (an RelativelyCentroid) by the scalar
     * right-hand-side operand (a Float).
     */
    @Stable
    operator fun rem(operand: Float) = RelativelyCentroid(value.rem(operand))

    override fun toString() = value.toString().replace("Offset", "RelativelyCentroid")
}

/**
 * Linearly interpolate between two offsets.
 *
 * The [fraction] argument represents position on the timeline, with 0.0 meaning
 * that the interpolation has not started, returning [start] (or something
 * equivalent to [start]), 1.0 meaning that the interpolation has finished,
 * returning [stop] (or something equivalent to [stop]), and values in between
 * meaning that the interpolation is at the relevant point on the timeline
 * between [start] and [stop]. The interpolation can be extrapolated beyond 0.0 and
 * 1.0, so negative values and values greater than 1.0 are valid (and can
 * easily be generated by curves).
 *
 * Values for [fraction] are usually obtained from an [Animation<Float>], such as
 * an `AnimationController`.
 */
@Stable
fun lerp(start: RelativelyCentroid, stop: RelativelyCentroid, fraction: Float): RelativelyCentroid {
    return RelativelyCentroid(
        androidx.compose.ui.geometry.lerp(
            start = start.value,
            stop = stop.value,
            fraction = fraction
        )
    )
}

///**
// * True if both x and y values of the [RelativelyCentroid] are finite
// */
//@Stable
//val RelativelyCentroid.isFinite: Boolean get() = value.isFinite

/**
 * `false` when this is [RelativelyCentroid.Unspecified].
 */
@Stable
val RelativelyCentroid.isSpecified: Boolean get() = value.isSpecified

/**
 * `true` when this is [RelativelyCentroid.Unspecified].
 */
@Stable
val RelativelyCentroid.isUnspecified: Boolean get() = value.isUnspecified

/**
 * If this [RelativelyCentroid]&nbsp;[isSpecified] then this is returned, otherwise [block] is executed
 * and its result is returned.
 */
inline fun RelativelyCentroid.takeOrElse(block: () -> RelativelyCentroid): RelativelyCentroid =
    if (isSpecified) this else block()