package komiwojazer.algorithms.mutator

import komiwojazer.Route
import komiwojazer.secureRandom

class RandomCityNumberMutator : RouteMutator {

    override fun mutate(route: Route): Route {
        val cities = route.cities
        var citiesCountToMutate = secureRandom.nextInt(cities.size - 1)
        while (citiesCountToMutate == 0) {
            citiesCountToMutate = secureRandom.nextInt(cities.size - 1)
        }
        val indexesToMutate = mutableListOf<Int>()

        while (indexesToMutate.size < citiesCountToMutate) {
            val index = secureRandom.nextInt(cities.size - 1)
            if (index !in indexesToMutate) {
                indexesToMutate.add(index)
            }
        }

        (0 until indexesToMutate.size - 1).forEach {
            val firstIndex = indexesToMutate[secureRandom.nextInt(indexesToMutate.size - 1)]
            val secondIndex = indexesToMutate[secureRandom.nextInt(indexesToMutate.size - 1)]
            cities.swap(firstIndex, secondIndex)
        }

        return Route(cities)
    }

}