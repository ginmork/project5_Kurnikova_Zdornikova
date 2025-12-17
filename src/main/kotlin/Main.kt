import kotlin.math.sqrt
fun readPoint(label: String): Point {
    while (true) {
        println("\n$label:")
        print("x = ")
        val xInput = readln()
        print("y = ")
        val yInput = readln()

        val x = xInput.toDoubleOrNull()
        val y = yInput.toDoubleOrNull()

        if (x == null || y == null) {
            println("Ошибка: введите числа! Попробуйте снова")
        } else {
            return Point(x, y)
        }
    }
}

fun mainTask1() {
    println("Введите координаты трёх вершин треугольника:")

    val vertexA = readPoint("Вершина A")
    val vertexB = readPoint("Вершина B")
    val vertexC = readPoint("Вершина C")
    val queryPoint = readPoint("Проверяемая точка")

    val triangle = Triangle(vertexA, vertexB, vertexC)

    if (triangle.Check()) {
        println("Ошибка: три точки лежат на одной прямой — треугольник не существует")
        return
    }

    println(if (triangle.contains(queryPoint))
        "Точка находится внутри или на границе треугольника"
    else "Точка находится снаружи треугольника")
}

fun mainTask2() {
    println("Введите координаты двух точек:")

    val firstPoint = readPoint("Первая точка")
    val secondPoint = readPoint("Вторая точка")
    val dx = firstPoint.x - secondPoint.x
    val dy = firstPoint.y - secondPoint.y
    val distance = sqrt(dx * dx + dy * dy)
    println("\nРасстояние между точками: %.4f".format(distance))
}

fun readPointCount(): Int {
    while (true) {
        print("Введите количество точек (должно быть больше 2): ")
        val n = readln().toInt()
        if (n > 2) return n
        println("Ошибка: введите целое число больше 2")
    }
}

fun mainTask3() {
    val n = readPointCount()
    val points = mutableListOf<Point>()

    println("Введите координаты $n точек:")

    for (i in 1..n) {
        points.add(readPoint("Точка $i"))
    }

    for (i in points.indices) {
        for (j in i + 1 until points.size) {
            if (points[i] == points[j]) {
                println("Ошибка: точки не должны совпадать! Точка ${i + 1} и ${j + 1} одинаковые")
                return
            }
        }
    }

    var minDist = Double.MAX_VALUE
    var maxDist = 0.0

    for (i in points.indices) {
        for (j in i + 1 until points.size) {
            val dx = points[i].x - points[j].x
            val dy = points[i].y - points[j].y
            val dist = sqrt(dx * dx + dy * dy)

            if (dist < minDist) minDist = dist
            if (dist > maxDist) maxDist = dist
        }
    }

    println("\nМинимальное расстояние между точками: %.4f".format(minDist))
    println("Максимальное расстояние между точками: %.4f".format(maxDist))
}

fun main() {
    while (true) {
        println("\nМеню:")
        println("1. Проверка принадлежности точки треугольнику")
        println("2. Расстояние между двумя точками")
        println("3. Минимальное и максимальное расстояние среди N точек")
        println("0. Выход")
        print("Выберите номер задачи: ")

        when (readln()) {
            "1" -> mainTask1()
            "2" -> mainTask2()
            "3" -> mainTask3()
            "0" -> {
                println("Выход из программы.")
                return
            }
            else -> println("Неверный ввод. Попробуйте снова")
        }
    }
}