/* import kotlin.math.abs
data class Point(val x: Double, val y: Double)

class Triangle(val p1: Point, val p2: Point, val p3: Point) {
    private fun area(a: Point, b: Point, c: Point): Double {
        return abs((b.x - a.x) * (c.y - a.y) - (c.x - a.x) * (b.y - a.y)) / 2.0
    }

    fun Check(): Boolean {
        return area(p1, p2, p3) == 0.0
    }

    fun contains(point: Point): Boolean {
        val bigArea = area(p1, p2, p3)
        val a1 = area(point, p2, p3)
        val a2 = area(p1, point, p3)
        val a3 = area(p1, p2, point)
        val sum = a1 + a2 + a3

        return sum == bigArea
    }
}


fun readPoint(label: String): Point {
    while (true) {
        println("\n$label:")
        print("x = ")
        val xInput = readln()
        print("y = ")
        val yInput = readln()

        if (xInput == null || yInput == null) {
            println("Ошибка: пустой ввод. Попробуйте снова.")
            continue
        }

        try {
            val x = xInput.trim().toDouble()
            val y = yInput.trim().toDouble()
            return Point(x, y)
        } catch (e: NumberFormatException) {
            println("Ошибка: введите числа! Попробуйте снова.")
        }
    }
}

fun main() {
    println("Введите координаты трёх вершин треугольника:")

    val p1 = readPoint("Первая точка")
    val p2 = readPoint("Вторая точка")
    val p3 = readPoint("Третья точка")
    val testPoint = readPoint("Точка для проверки")

    val triangle = Triangle(p1, p2, p3)

    if (!triangle.Check()) {
        println("Ошибка: три точки лежат на одной прямой — треугольник не существует.")
        return
    }

    if (triangle.contains(testPoint)) {
        println("Точка находится внутри или на границе треугольника.")
    } else {
        println("Точка находится снаружи треугольника.")
    }
}
*/

/* import kotlin.math.sqrt

data class Point(val x: Double, val y: Double) {
    fun distanceTo(other: Point): Double {
        val dx = this.x - other.x
        val dy = this.y - other.y
        return sqrt(dx * dx + dy * dy)
    }
}

fun readPoint(label: String): Point {
    while (true) {
        println("\n$label:")
        print("x = ")
        val xInput = readln()
        print("y = ")
        val yInput = readln()

        try {
            val x = xInput.trim().toDouble()
            val y = yInput.trim().toDouble()
            return Point(x, y)
        } catch (e: NumberFormatException) {
            println("Ошибка: введите числа! Попробуйте снова.")
        }
    }
}

fun main() {
    println("Введите координаты двух точек:")

    val point1 = readPoint("Первая точка")
    val point2 = readPoint("Вторая точка")

    val distance = point1.distanceTo(point2)

    println("\nРасстояние между точками: %.4f".format(distance))
}
 */

/* import kotlin.math.sqrt

data class Point(val x: Double, val y: Double) {
    fun distanceTo(other: Point): Double {
        val dx = x - other.x
        val dy = y - other.y
        return sqrt(dx * dx + dy * dy)
    }
}

fun readPointCount(): Int {
    while (true) {
        print("Введите количество точек (должно быть больше 2): ")
        try {
            val n = readln().trim().toInt()
            if (n > 2) return n
            println("Ошибка: количество должно быть больше 2. Попробуйте снова.")
        } catch (e: NumberFormatException) {
            println("Ошибка: введите целое число!")
        }
    }
}

fun readPoint(index: Int): Point {
    while (true) {
        println("\nТочка $index:")
        print("x = ")
        val xInput = readln()
        print("y = ")
        val yInput = readln()

        try {
            val x = xInput.trim().toDouble()
            val y = yInput.trim().toDouble()
            return Point(x, y)
        } catch (e: NumberFormatException) {
            println("  Ошибка: введите числа! Попробуйте снова.")
        }
    }
}

fun main() {
    val n = readPointCount()
    val points = mutableListOf<Point>()

    println("Введите координаты $n точек:")

    for (i in 1..n) {
        points.add(readPoint(i))
    }

    for (i in points.indices) {
        for (j in i + 1 until points.size) {
            if (points[i].x == points[j].x && points[i].y == points[j].y) {
                println("Ошибка: точки не должны совпадать! Точка ${i + 1} и ${j + 1} одинаковые.")
                return
            }
        }
    }

    var minDist = Double.MAX_VALUE
    var maxDist = 0.0

    for (i in points.indices) {
        for (j in i + 1 until points.size) {
            val dist = points[i].distanceTo(points[j])
            if (dist < minDist) minDist = dist
            if (dist > maxDist) maxDist = dist
        }
    }

    println("\nМинимальное расстояние между точками: %.4f".format(minDist))
    println("Максимальное расстояние между точками: %.4f".format(maxDist))
}
*/