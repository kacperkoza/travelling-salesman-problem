package travellingsalesman.algorithms.crossover

import travellingsalesman.Route

interface RouteCrossover {

    fun cross(first: Route, second: Route): Route

}