package nqueens

fun List<Int>.draw() {
    val globalBuilder = StringBuilder()
    for (i in 7 downTo 0) {
        val local = StringBuilder()
        for (j in 7 downTo 0) {
            if (this.get(j) == i) {
                local.append("X ")
            } else {
                local.append("- ")
            }
        }
        globalBuilder.append(local.toString().reversed())
        globalBuilder.append("\n")
    }
    println(globalBuilder.toString())
}

fun IntArray.draw() {
    val globalBuilder = StringBuilder()
    for (i in 7 downTo 0) {
        val local = StringBuilder()
        for (j in 7 downTo 0) {
            if (this.get(j) == i) {
                local.append("X ")
            } else {
                local.append("- ")
            }
        }
        globalBuilder.append(local.toString().reversed())
        globalBuilder.append("\n")
    }
    println(globalBuilder.toString())
}