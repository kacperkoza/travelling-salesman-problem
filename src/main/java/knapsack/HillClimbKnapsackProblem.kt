package knapsack

import knapsack.evaluator.HeuristicEvaluator
import knapsack.neighbor.NeighborGenerator
import java.security.SecureRandom

class HillClimbKnapsackProblem(
        private val heuristicEvaluator: HeuristicEvaluator,
        private val neighborGenerator: NeighborGenerator
) {

    private val secureRandom = SecureRandom()

    fun findSolution(items: MutableList<Item>, knapsack: Knapsack): Knapsack {
        var bestKnapsack = knapsack
        val randomIndex = secureRandom.nextInt(items.size)
        bestKnapsack.put(items[randomIndex])
        while (bestKnapsack.canBeAddedAnyOf(items) && !bestKnapsack.isFull()) {
            val possibleNewItems = generateNeighbors(items, bestKnapsack)

            if (possibleNewItems.isNotEmpty()) {
                val sortedByHeuristic = sortByHeuristicEvaluate(possibleNewItems)
                bestKnapsack = sortedByHeuristic[0]
                items.remove(bestKnapsack.items.last())
            }
        }
        return bestKnapsack
    }

    private fun generateNeighbors(items: MutableList<Item>, knapsack: Knapsack) =
            neighborGenerator.generate(items, knapsack)

    private fun sortByHeuristicEvaluate(items: List<Knapsack>) =
            items
                    .sortedByDescending {
                        heuristicEvaluator.evaluate(it)
                    }
                    .toMutableList()

}