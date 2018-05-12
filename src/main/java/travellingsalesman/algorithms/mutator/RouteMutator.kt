package travellingsalesman.algorithms.mutator

import travellingsalesman.Route

interface RouteMutator {

    fun mutate(route: Route): Route

}