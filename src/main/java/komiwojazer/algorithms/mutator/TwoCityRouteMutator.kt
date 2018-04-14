package komiwojazer.algorithms.mutator

import komiwojazer.Route
import komiwojazer.algorithms.random.RandomPairGenerator

class TwoCityRouteMutator(
        private val randomPairGenerator: RandomPairGenerator
): RouteMutator {

    override fun mutate(route: Route): Route {
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




