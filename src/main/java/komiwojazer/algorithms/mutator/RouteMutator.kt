package komiwojazer.algorithms.mutator

import komiwojazer.Route

interface RouteMutator {

    fun mutate(route: Route): Route

}