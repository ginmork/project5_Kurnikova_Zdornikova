import kotlin.math.abs

class Triangle(
    val vertexA: Point,
    val vertexB: Point,
    val vertexC: Point
) {
    fun contains(queryPoint: Point): Boolean {
        val fullArea = area(vertexA, vertexB, vertexC)
        val area1 = area(queryPoint, vertexB, vertexC)
        val area2 = area(vertexA, queryPoint, vertexC)
        val area3 = area(vertexA, vertexB, queryPoint)
        val totalArea = area1 + area2 + area3
        return totalArea == fullArea
    }
    fun Check(): Boolean {
        return area(vertexA, vertexB, vertexC) == 0.0
    }
    private fun area(p1: Point, p2: Point, p3: Point): Double {
        return abs((p1.x * (p2.y - p3.y) + p2.x * (p3.y - p1.y) + p3.x * (p1.y - p2.y)) / 2.0)
    }
}