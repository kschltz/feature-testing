package com.kschltz.unleash


fun main() {
    val reduce = assesToggles(10000000)

    print("time:${reduce.first} - count: ${reduce.second}")


}

private fun assesToggles(count: Int): Pair<Long, Int> {
    return generateSequence { 1 }
        .take(count)
        .map {
            measureTimeVal {
                UnleashClient("togglexpto")
            }
        }
        .filter { it.second }
        .map {it.first to 1}
        .reduce { p1, p2 -> p1 + p2}
}




operator fun Pair<Long, Int>.plus(p2: Pair<Long, Int>): Pair<Long, Int> {
    val time = this.first + p2.first
    val count = this.second + p2.second

    return time to count
}

private inline fun <V> measureTimeVal(block: () -> V): Pair<Long, V> {
    val start = System.currentTimeMillis()
    val result = block()
    val timeSpent = System.currentTimeMillis() - start
    return timeSpent to result
}


