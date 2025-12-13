import kotlin.math.abs
import kotlin.math.sqrt

data class Point1(val x: Double, val y: Double)

class Triangle(val vertexA: Point1, val vertexB: Point1, val vertexC: Point1) {
    private fun area(a: Point1, b: Point1, c: Point1): Double {
        return abs((b.x - a.x) * (c.y - a.y) - (c.x - a.x) * (b.y - a.y)) / 2.0
    }

    fun Check(): Boolean {
        return area(vertexA, vertexB, vertexC) == 0.0
    }

    fun contains(queryPoint: Point1): Boolean {
        val fullArea = area(vertexA, vertexB, vertexC)
        val area1 = area(queryPoint, vertexB, vertexC)
        val area2 = area(vertexA, queryPoint, vertexC)
        val area3 = area(vertexA, vertexB, queryPoint)
        val totalArea = area1 + area2 + area3
        return totalArea == fullArea
    }
}

fun readPoint1(label: String): Point1 {
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
            return Point1(x, y)
        } catch (e: NumberFormatException) {
            println("Ошибка: введите числа! Попробуйте снова.")
        }
    }
}

fun mainTask1() {
    println("Введите координаты трёх вершин треугольника:")

    val vertexA = readPoint1("Вершина A")
    val vertexB = readPoint1("Вершина B")
    val vertexC = readPoint1("Вершина C")
    val queryPoint = readPoint1("Проверяемая точка")

    val triangle = Triangle(vertexA, vertexB, vertexC)

    if (triangle.Check()) {
        println("Ошибка: три точки лежат на одной прямой — треугольник не существует.")
        return
    }

    if (triangle.contains(queryPoint)) {
        println("Точка находится внутри или на границе треугольника.")
    } else {
        println("Точка находится снаружи треугольника.")
    }
}

data class Point2(val x: Double, val y: Double) {
    fun distanceTo(other: Point2): Double {
        val dx = this.x - other.x
        val dy = this.y - other.y
        return sqrt(dx * dx + dy * dy)
    }
}

fun readPoint2(label: String): Point2 {
    while (true) {
        println("\n$label:")
        print("x = ")
        val xInput = readln()
        print("y = ")
        val yInput = readln()

        try {
            val x = xInput.trim().toDouble()
            val y = yInput.trim().toDouble()
            return Point2(x, y)
        } catch (e: NumberFormatException) {
            println("Ошибка: введите числа! Попробуйте снова.")
        }
    }
}

fun mainTask2() {
    println("Введите координаты двух точек:")

    val firstPoint = readPoint2("Первая точка")
    val secondPoint = readPoint2("Вторая точка")

    val distance = firstPoint.distanceTo(secondPoint)

    println("\nРасстояние между точками: %.4f".format(distance))
}

data class Point3(val x: Double, val y: Double) {
    fun distanceTo(other: Point3): Double {
        val dx = x - other.x
        val dy = y - other.y
        return sqrt(dx * dx + dy * dy)
    }
}

fun readPointCount3(): Int {
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

fun readPoint3(index: Int): Point3 {
    while (true) {
        println("\nТочка $index:")
        print("x = ")
        val xInput = readln()
        print("y = ")
        val yInput = readln()

        try {
            val x = xInput.trim().toDouble()
            val y = yInput.trim().toDouble()
            return Point3(x, y)
        } catch (e: NumberFormatException) {
            println("  Ошибка: введите числа! Попробуйте снова.")
        }
    }
}

fun mainTask3() {
    val n = readPointCount3()
    val points = mutableListOf<Point3>()

    println("Введите координаты $n точек:")

    for (i in 1..n) {
        points.add(readPoint3(i))
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

fun main() {
    while (true) {
        println("Меню проекта:")
        println("1. Проверка принадлежности точки треугольнику")
        println("2. Расстояние между двумя точками")
        println("3. Минимальное и максимальное расстояние среди N точек")
        println("0. Выход")
        print("Выберите номер задачи: ")

        val choice = readln().trim()
        when (choice) {
            "1" -> mainTask1()
            "2" -> mainTask2()
            "3" -> mainTask3()
            "0" -> {
                println("Выход из программы.")
                break
            }
            else -> println("Неверный ввод. Попробуйте снова.")
        }
    }
}