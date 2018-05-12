package nqueens.heuristic.annealing

import nqueens.heuristic.HeuristicProvider
import nqueens.heuristic.NQueensSolver
import java.security.SecureRandom

class AnnealingSolver(
        private val maxNumOfIterations: Int,
        private val globalTemperature: Double,
        private val coolingFactor: Double,
        private val heuristicProvider: HeuristicProvider
) : NQueensSolver {

    private val secureRandom = SecureRandom()

    override fun solve(n: Int): IntArray? {
        var array = heuristicProvider.generateRandomStateChromosome(n)
        var costToBeat = heuristicProvider.calculateHeuristicCost(array)
        var temperature = 0.0
        var x = 0
        while (x < maxNumOfIterations && costToBeat > 0) {
            array = findNextMove(array, costToBeat, temperature)
            costToBeat = heuristicProvider.calculateHeuristicCost(array)
            temperature = Math.max(globalTemperature * coolingFactor, 0.01)
            x++
        }
        return if (costToBeat == 0) array else null
    }

    private fun findNextMove(array: IntArray, costToBeat: Int, temperature: Double): IntArray {
        val n = array.size
        while (true) {
            val columnNumber = nextInt(n)
            val rowNumber = nextInt(n)
            val tempRow = array[columnNumber]
            array[columnNumber] = rowNumber

            val cost = heuristicProvider.calculateHeuristicCost(array)
            if (cost < costToBeat) {
                return array
            }
            val dE = costToBeat - cost
            val acceptableProbability = Math.min(1.0, Math.exp(dE / temperature))
            if (isAcceptableProbability(acceptableProbability)) {
                return array
            }
            array[columnNumber] = tempRow
        }
    }

    private fun isAcceptableProbability(acceptableProbability: Double) =
            secureRandom.nextDouble() < acceptableProbability

    private fun nextInt(n: Int) = secureRandom.nextInt(n)

}