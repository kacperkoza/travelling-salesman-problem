package komiwojazer

import komiwojazer.crossover.SimpleRouteCrossover
import komiwojazer.random.NearbyPairsGenerator
import komiwojazer.random.SecureRandomPairGenerator

/**
 * algorytm genetyczny dla problemu komiwojazera
 */

/**
 * http://www.michalbereta.pl/dydaktyka/alg_imm/klasyczny_algorytm_genetyczny.pdf
 *
 * http://algorytmy.ency.pl/tutorial/problem_komiwojazera_algorytm_genetyczny
 */

object Zad2 {

    // dane wejściowe
    private const val NUMBER_OF_GENERATIONS = 1000
    private const val NUMBER_OF_CROSS_IN_SINGLE_ITERATION = 30
    private val properties = GeneticAlgorithmProperties(NUMBER_OF_GENERATIONS, NUMBER_OF_CROSS_IN_SINGLE_ITERATION)

    // algorytmy
    private val routeCross = SimpleRouteCrossover()
    private val routeMutator = RouteMutator(NearbyPairsGenerator())

    // narzedzia
    private val randomPairGenerator = SecureRandomPairGenerator()
    private val writer = FileWriter("wyniki.txt")

    @JvmStatic
    fun main(args: Array<String>) {
        var routes: MutableList<Route> = RouteGenerator.generateRoutes()

        (0..NUMBER_OF_GENERATIONS).forEach {
            // cross random routes
            (0 until NUMBER_OF_CROSS_IN_SINGLE_ITERATION).forEach {
                val (first, second) = randomPairGenerator.getRandomPair(routes.size)
                val newRoute = routeCross.cross(routes[first], routes[second])
                routes.add(newRoute)
            }

            // swap two cities in route
            val (i, route) = routes.getRandomElementWithIndex()
            routes[i] = routeMutator.swapTwoCities(route)

            //sort routes
            routes.sortBy { calculateRouteLength(it) }

            //remove the worst
            routes = routes.take(routes.size - NUMBER_OF_CROSS_IN_SINGLE_ITERATION).toMutableList()

//            println(routes.writeAsString())
        }

        writer.writeToFileWithTimeStamp(routes[0], properties)

    }

}

data class GeneticAlgorithmProperties(
        val numberOfGenerations: Int,
        val numberOfCrossesInSingleGeneration: Int
)

