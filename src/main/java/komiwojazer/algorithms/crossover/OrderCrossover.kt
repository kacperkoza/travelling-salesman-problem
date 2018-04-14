package komiwojazer.algorithms.crossover

import komiwojazer.Route

class OrderCrossover : RouteCrossover {

    override fun cross(first: Route, second: Route): Route {
        val citiesCount = first.cities.size
        val slicedToHalf = first.cities.take(citiesCount / 2).toMutableList()
        val missingCitiesInFirstHalf = second.cities.filter { it !in slicedToHalf }
        slicedToHalf.addAll(missingCitiesInFirstHalf)
        return Route(slicedToHalf)
    }

}