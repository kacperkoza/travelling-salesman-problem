package komiwojazer

import komiwojazer.algorithms.crossover.OrderCrossover
import komiwojazer.algorithms.crossover.PartiallyMatchedCrossover
import komiwojazer.algorithms.mutator.RandomCityNumberMutator
import komiwojazer.algorithms.mutator.TwoCityRouteMutator
import komiwojazer.algorithms.random.NearbyPairsGenerator
import komiwojazer.algorithms.random.SecureRandomPairGenerator
import komiwojazer.utils.FileWriter
import komiwojazer.utils.RouteGenerator
import komiwojazer.utils.calculateRouteLength

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
    private const val NUMBER_OF_GENERATIONS = 10000000
    private const val NUMBER_OF_CROSS_IN_SINGLE_ITERATION = 30
    private val properties = GeneticAlgorithmProperties(NUMBER_OF_GENERATIONS, NUMBER_OF_CROSS_IN_SINGLE_ITERATION)

    // algorytmy
    private val routeCross = OrderCrossover()
//    private val routeCross = PartiallyMatchedCrossover(SecureRandomPairGenerator())

    private val routeMutator = TwoCityRouteMutator(NearbyPairsGenerator())
//    private val routeMutator = RandomCityNumberMutator()

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
                routes.add(routeCross.cross(routes[first], routes[second]))
            }

            // swap two cities in route
            val (i, route) = routes.getRandomElementWithIndex()
            routes[i] = routeMutator.mutate(route)

            //sort routes
            routes.sortBy { calculateRouteLength(it) }

            //remove the worst
            routes = routes.take(routes.size - NUMBER_OF_CROSS_IN_SINGLE_ITERATION).toMutableList()

        }

        writer.writeToFileWithTimeStamp(routes[0], properties)

    }

}

data class GeneticAlgorithmProperties(
        val numberOfGenerations: Int,
        val numberOfCrossesInSingleGeneration: Int
)

fun <T> MutableList<T>.getRandomElementWithIndex(): Pair<Int, T> {
    val index = secureRandom.nextInt(size - 1)
    val element = get(index)
    return Pair(index, element)
}

