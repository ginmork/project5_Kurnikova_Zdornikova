fun main() {
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
}