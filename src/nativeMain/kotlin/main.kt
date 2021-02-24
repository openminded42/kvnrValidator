import kotlin.random.Random

fun main(args: Array<String>) {
    val chars = hashMapOf(
        "A" to "01",
        "B" to "02",
        "C" to "03",
        "D" to "04",
        "E" to "05",
        "F" to "06",
        "G" to "07",
        "H" to "08",
        "I" to "09",
        "J" to "10",
        "K" to "11",
        "L" to "12",
        "M" to "13",
        "N" to "14",
        "O" to "15",
        "P" to "16",
        "Q" to "17",
        "R" to "18",
        "S" to "19",
        "T" to "20",
        "U" to "21",
        "V" to "22",
        "W" to "23",
        "X" to "24",
        "Y" to "25",
        "Z" to "26"
    )

    var kvnr = ""
    if (args.size == 0) {
        println("Generate random Krankenversicherungsnummer")
        val randomChar = (chars.keys).shuffled().first()
        val randomNumber = (10000000..99999999).random()
        kvnr = randomChar + randomNumber.toString()
    } else {
        kvnr = args[0]
    }

    println("Input " + kvnr)

    // Validity checks
    if (kvnr.length < 9) {
        println("The Krankenversicheurngsnummer is not long enough. Expected a minimum of 9 characters.")
    }

    if(kvnr.length > 10) {
        println("The Krankenkversicherungsnummer is too long. Expected a maximum auf 10 characters (including checksum)")
    }

    val firstChar = kvnr[0]
    if(!chars.keys.contains(firstChar.toString())) {
       println("The first character of the Krankenversicherungsnummer is invalid. Expected an upper case letter form A to Z.")
    }

    // Convert first Character (A, B, C, D, ...) to a number
    val firstCharDigits = chars.get(firstChar.toString());
    val allDigits = firstCharDigits + kvnr.substring(1,9)
    println("Digits are ${allDigits}")

    // Multiply digits with values (1,2,1,2,1,2,1,2,1,2)
    val multipliedValues = mutableListOf<Int>()
    val multiplicator = listOf(1,2,1,2,1,2,1,2,1,2)
    println("Multiply Vector is ${multiplicator}")

    for (i in (0..9)) {
        multipliedValues.add(allDigits[i].toString().toInt() * multiplicator[i])
    }
    println("Multiplied digits are ${multipliedValues}")

    // Calculate Cross Sum
    val crossSums = mutableListOf<Int>()
    for (elem in multipliedValues) {
        var crossSum = 0
        for (digit in elem.toString()) {
            crossSum += digit.toString().toInt()
        }
        crossSums.add(crossSum)
    }
    println("Cross sums are are ${crossSums}")

    // sum up cross sums
    var sum = 0
    for (elem in crossSums) {
        sum += elem
    }
    println("Total sum is ${sum}")

    // modulo 10
    val errorCheckingNumber = sum % 10

    // Output
    println("Error Checking Number is ${errorCheckingNumber}")

    // Check if correct
    if(kvnr.length == 10) {
        val originalErrorCheckingNumer = kvnr[9]
        if(originalErrorCheckingNumer.toString().toInt() == errorCheckingNumber) {
            println("The Krankenversicherungsnummer ${kvnr} is VALID!")
        } else {
            println("The Krankenversicherungsnummer ${kvnr} is INVALID!")
        }
    }

    if(args.size == 0 || args[0].length == 9) {
        println("Krankenversicherungsnummer is ${kvnr}${errorCheckingNumber}")
    }
}