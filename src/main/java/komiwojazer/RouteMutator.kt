package komiwojazer

import komiwojazer.random.RandomPairGenerator

class RouteMutator(
        private val randomPairGenerator: RandomPairGenerator
) {

    fun swapTwoCities(route: Route): Route {
        val (firstIndex, secondIndex) = randomPairGenerator.getRandomPair(route.cities.size - 1)
        val cities = route.cities.swap(firstIndex, secondIndex)
        return Route(cities)
    }

}


fun <T> MutableList<T>.swap(firstIndex: Int, secondIndex: Int): MutableList<T> {
    val temp: T = this[firstIndex]
    this[firstIndex] = this[secondIndex]
    this[secondIndex] = temp
    return this
}

fun <T> MutableList<T>.getRandomElementWithIndex(): Pair<Int, T> {
    val index = secureRandom.nextInt(size - 1)
    val element = get(index)
    return Pair(index, element)
}