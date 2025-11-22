/* fun main() {
    println("ИГРА КАМЕНЬ-НОЖНИЦЫ-БУМАГА")
    println("Ходы:")
    println("1 - Камень")
    println("2 - Ножницы")
    println("3 - Бумага")

    var continuePlaying = true

    while (continuePlaying) {
        playRound()

        println("\nХотите сыграть еще раз? (Да/Нет)")
        val input = readln()
        if (input == "Нет") {
            println("Спасибо за игру")
            continuePlaying = false
        } else if (input == "Да") {
            continue
        } else {
            println("Ошибка, введите правильный вариант ответа")
        }
    }
}

fun playRound() {
    var playerChoice: Int
    var computerChoice: Int
    var isDraw: Boolean

    do {
        playerChoice = getPlayerChoice()

        computerChoice = (1 until 4).random()

        println("\nВаш выбор: ${getChoiceName(playerChoice)}")
        println("Выбор ПК: ${getChoiceName(computerChoice)}")

        isDraw = playerChoice == computerChoice

        if (isDraw) {
            println("Ничья! Играем еще")
        }

    } while (isDraw)

    val winner = determineWinner(playerChoice, computerChoice)

    when (winner) {
        "player" -> println("Вы победили! ${getWinReason(playerChoice, computerChoice)}")
        "computer" -> println("Победил компьютер! ${getWinReason(computerChoice, playerChoice)}")
    }
}

fun getPlayerChoice(): Int {
    while (true) {
        println("\nСделайте ваш выбор (1-3):")
        print("1 - Камень, 2 - Ножницы, 3 - Бумага: ")
        val choice = readln().toIntOrNull() ?: 0
        if (choice in 1..3) {
            return choice
        } else {
            println("Неверно! Введите 1, 2 или 3")
        }
    }
}

fun getChoiceName(choice: Int): String {
    return when (choice) {
        1 -> "Камень"
        2 -> "Ножницы"
        3 -> "Бумага"
        else -> "Неизвестно"
    }
}

fun determineWinner(playerChoice: Int, computerChoice: Int): String {
    return when {
        playerChoice == 1 && computerChoice == 2 -> "player"
        playerChoice == 2 && computerChoice == 3 -> "player"
        playerChoice == 3 && computerChoice == 1 -> "player"
        else -> "computer"
    }
}

fun getWinReason(winnerChoice: Int, loserChoice: Int): String {
    return when {
        winnerChoice == 1 && loserChoice == 2 -> "Камень ломает ножницы"
        winnerChoice == 2 && loserChoice == 3 -> "Ножницы режут бумагу"
        winnerChoice == 3 && loserChoice == 1 -> "Бумага оборачивает камень"
        else -> ""
    }
} */
import kotlin.random.Random

fun main() {
    println("=== БИГРАММНЫЙ ШИФР ПОРТЫ ===")

    // Запрос исходных данных
    print("Введите сообщение для шифрования: ")
    val message = readLine()?.uppercase() ?: ""

    print("Введите вспомогательный символ: ")
    val fillerChar = readLine()?.uppercase()?.firstOrNull() ?: 'Я'

    print("Использовать типовую таблицу? (да/нет): ")
    val useStandardTable = readLine()?.equals("да", ignoreCase = true) ?: true

    // Создание таблицы
    val table = if (useStandardTable) createStandardTable() else createRandomTable()

    // Шифрование
    val encrypted = encryptMessage(message, fillerChar, table)

    // Вывод результатов
    printResults(message, encrypted, table)
}

// Русский алфавит (33 буквы)
val alphabet = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ"

data class BigramTable(val table: Map<Pair<Char, Char>, String>)

fun createStandardTable(): BigramTable {
    val table = mutableMapOf<Pair<Char, Char>, String>()
    var counter = 1

    for (i in alphabet.indices) {
        for (j in alphabet.indices) {
            val pair = Pair(alphabet[i], alphabet[j])
            table[pair] = counter.toString().padStart(3, '0')
            counter++
        }
    }
    return BigramTable(table)
}

fun createRandomTable(): BigramTable {
    val numbers = (1..999).shuffled()
    val table = mutableMapOf<Pair<Char, Char>, String>()
    var index = 0

    for (i in alphabet.indices) {
        for (j in alphabet.indices) {
            val pair = Pair(alphabet[i], alphabet[j])
            table[pair] = numbers[index].toString().padStart(3, '0')
            index++
        }
    }
    return BigramTable(table)
}

fun encryptMessage(message: String, fillerChar: Char, table: BigramTable): String {
    // Очистка сообщения (оставляем только буквы алфавита)
    val cleanMessage = message.filter { it in alphabet }

    // Разбивка на пары с добавлением fillerChar при необходимости
    val pairs = mutableListOf<Pair<Char, Char>>()
    var i = 0

    while (i < cleanMessage.length) {
        val first = cleanMessage[i]
        val second = if (i + 1 < cleanMessage.length) cleanMessage[i + 1] else fillerChar
        pairs.add(Pair(first, second))
        i += 2
    }

    // Шифрование пар
    return pairs.joinToString(" ") { pair ->
        table.table[pair] ?: "000"
    }
}

fun printResults(originalMessage: String, encryptedMessage: String, table: BigramTable) {
    println("\n" + "=".repeat(50))
    println("РЕЗУЛЬТАТЫ ШИФРОВАНИЯ")
    println("=".repeat(50))

    // Очистка оригинального сообщения
    val cleanMessage = originalMessage.filter { it in alphabet }

    // Разбивка на пары для отображения
    val pairs = mutableListOf<String>()
    var i = 0
    while (i < cleanMessage.length) {
        val pair = if (i + 1 < cleanMessage.length) {
            "${cleanMessage[i]}${cleanMessage[i + 1]}"
        } else {
            "${cleanMessage[i]}*"
        }
        pairs.add(pair)
        i += 2
    }

    println("Исходное сообщение: ${pairs.joinToString(" ")}")
    println("Зашифрованное сообщение: $encryptedMessage")

    // Выравнивание для визуального сопоставления
    println("\nСопоставление:")
    val encryptedParts = encryptedMessage.split(" ")
    for (j in pairs.indices) {
        println("${pairs[j].padEnd(6)} -> ${encryptedParts.getOrElse(j) { "   " }}")
    }

    // Вывод части таблицы (первые 5x5 для примера)
    println("\n" + "=".repeat(30))
    println("ЧАСТЬ ТАБЛИЦЫ ШИФРОВАНИЯ (5x5)")
    println("=".repeat(30))

    print("    ")
    for (j in 0..4) {
        print("${alphabet[j]}   ")
    }
    println()

    for (i in 0..4) {
        print("${alphabet[i]} | ")
        for (j in 0..4) {
            val pair = Pair(alphabet[i], alphabet[j])
            print("${table.table[pair]} ")
        }
        println()
    }
    println("...")
}

// Функция для дешифрования (дополнительно)
fun decryptMessage(encryptedMessage: String, table: BigramTable): String {
    val reverseTable = table.table.entries.associate { (key, value) -> value to key }
    val encryptedParts = encryptedMessage.split(" ").filter { it.isNotBlank() }

    val result = StringBuilder()
    for (part in encryptedParts) {
        val pair = reverseTable[part]
        if (pair != null) {
            result.append(pair.first)
            result.append(pair.second)
        }
    }
    return result.toString()
}
