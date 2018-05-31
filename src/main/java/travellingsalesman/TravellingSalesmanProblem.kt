package travellingsalesman

import travellingsalesman.algorithms.crossover.OrderCrossover
import travellingsalesman.algorithms.crossover.PartiallyMatchedCrossover
import travellingsalesman.algorithms.crossover.RouteCrossover
import travellingsalesman.algorithms.mutator.RandomCityNumberMutator
import travellingsalesman.algorithms.mutator.RouteMutator
import travellingsalesman.algorithms.mutator.TwoCityRouteMutator
import travellingsalesman.algorithms.random.NearbyPairsGenerator
import travellingsalesman.algorithms.random.SecureRandomPairGenerator
import travellingsalesman.utils.FileWriter
import travellingsalesman.utils.RouteGenerator
import travellingsalesman.utils.calculateRouteLength

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
    private const val NUMBER_OF_GENERATIONS = 100
    private const val NUMBER_OF_CROSS_IN_SINGLE_ITERATION = 30

    // narzedzia
    private val randomPairGenerator = SecureRandomPairGenerator()
    private val writer = FileWriter("wyniki.txt")

    @JvmStatic
    fun main(args: Array<String>) {
        runOrderCrossover()
        runPartiallyMatchedCrossover()
        runTwoCityMutator()
        runRandomCityMutator()
    }

    private fun runOrderCrossover() {
        run(OrderCrossover(), null)
    }

    private fun runPartiallyMatchedCrossover() {
        run(PartiallyMatchedCrossover(SecureRandomPairGenerator()), null)
    }

    private fun runTwoCityMutator() {
        run(null, TwoCityRouteMutator(NearbyPairsGenerator()))
    }

    private fun runRandomCityMutator() {
        run(null, RandomCityNumberMutator())
    }

    private fun run(routeCrossover: RouteCrossover?, mutator: RouteMutator?) {
        val routeCross = routeCrossover
        val routeMutator = mutator
        var routes: MutableList<Route> = RouteGenerator.generateRoutes()
        (0..NUMBER_OF_GENERATIONS).forEach {
            (0 until NUMBER_OF_CROSS_IN_SINGLE_ITERATION).forEach {
                // cross random routes
                routeCross?.let {
                    val (first, second) = Zad2.randomPairGenerator.getRandomPair(routes.size)
                    val f = routeCross.cross(routes[first], routes[second])
                    val s = routeCross.cross(routes[second], routes[first])
                    routes.add(f)
                    routes.add(s)
                }
                // swap cities in route
                routeMutator?.let {
                    val (i, route) = routes.getRandomElementWithIndex()
                    routes.add(routeMutator.mutate(route))
                }
            }
            //sort routes
            routes.sortBy { calculateRouteLength(it) }

            //remove the worst
            routes = routes.take(NUMBER_OF_CROSS_IN_SINGLE_ITERATION*2).toMutableList()
        }
        val properties = GeneticAlgorithmProperties(
                NUMBER_OF_GENERATIONS,
                NUMBER_OF_CROSS_IN_SINGLE_ITERATION,
                getAlgorithmName(routeCross?.javaClass ?: routeMutator!!.javaClass))
        writer.writeToFileWithTimeStamp(routes[0], properties)
    }

    private fun getAlgorithmName(clazz: Class<*>): String = clazz.simpleName
}

data class GeneticAlgorithmProperties(
        val numberOfGenerations: Int,
        val numberOfCrossesInSingleGeneration: Int,
        val algorithmName: String
)

fun <T> MutableList<T>.getRandomElementWithIndex(): Pair<Int, T> {
    val index = secureRandom.nextInt(size - 1)
    val element = get(index)
    return Pair(index, element)
}

