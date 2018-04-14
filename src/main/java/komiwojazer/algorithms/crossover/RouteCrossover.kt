package komiwojazer.algorithms.crossover

import komiwojazer.Route

interface RouteCrossover {

    fun cross(first: Route, second: Route): Route

}