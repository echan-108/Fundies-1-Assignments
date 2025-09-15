fun division(): Double {
    val result = 0.0
    var num = 0
    var denom = 0
    while () {
        print("Numerator: ")
        num.let {
            val temp = readln()
            try {
                num = temp.toInt()
                denom 
            } catch {
                println("Not an integer")
            }
        }
    }

    while {
        print("Denominator")
        denom.let {
            val temp = readln()
            try {
                denom = temp.toInt()
                denom
            } catch {
                println("Not an integer")
            }
        }
    }
    return result
}

