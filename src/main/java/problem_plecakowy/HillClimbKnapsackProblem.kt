package problem_plecakowy

import problem_plecakowy.evaluator.HeuristicEvaluator
import problem_plecakowy.neighbor.NeighborGenerator

class HillClimbKnapsackProblem(
        private val heuristicEvaluator: HeuristicEvaluator,
        private val neighborGenerator: NeighborGenerator
) {

    fun findSolution(items: MutableList<Item>, knapsack: Knapsack): Knapsack {
        var bestKnapsack = knapsack

        while (bestKnapsack.canBeAddedAnyOf(items) && !bestKnapsack.isFull()) {
            val possibleNewItems = generateNeighbors(items, bestKnapsack)

            if (possibleNewItems.isNotEmpty()) {
                val sortedByHeuristic = sortByHeuristicEvaluate(possibleNewItems)
                bestKnapsack = sortedByHeuristic[0]
                items.remove(bestKnapsack.items.last())
            }
        }
        bestKnapsack.printSummary()
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