fun readPoint1(label: String): Point {
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


fun readPoint2(label: String): Point {
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

fun mainTask2() {
    println("Введите координаты двух точек:")

    val firstPoint = readPoint2("Первая точка")
    val secondPoint = readPoint2("Вторая точка")

    val distance = firstPoint.distanceTo(secondPoint)

    println("\nРасстояние между точками: %.4f".format(distance))
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

fun readPoint3(index: Int): Point {
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

fun mainTask3() {
    val n = readPointCount3()
    val points = mutableListOf<Point>()

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