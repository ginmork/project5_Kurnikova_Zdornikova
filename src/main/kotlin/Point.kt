import kotlin.math.sqrt

class Point(val x: Double, val y: Double) {
    fun distanceTo(other: Point): Double {
        val dx = this.x - other.x
        val dy = this.y - other.y
        return sqrt(dx * dx + dy * dy)
    }
}