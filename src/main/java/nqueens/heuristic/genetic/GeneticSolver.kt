package nqueens.heuristic.genetic

import nqueens.heuristic.HeuristicProvider
import nqueens.heuristic.NQueensSolver

class GeneticSolver(
        private val numOfGenerations: Int,
        populationSize: Int,
        private val queenCrossover: QueenCrossover,
        private val queenMutator: QueenMutator,
        private val heuristicProvider: HeuristicProvider
) : NQueensSolver {

    private val populationSize = populationSize - populationSize % 2

    override fun solve(n: Int): IntArray? {
        val population = heuristicProvider.generatePopulation(n, populationSize)
        val maxFitness = getMaxFitness(n)
        for (i in 0 until numOfGenerations) {
            val sortedByFitness = sortByFitness(population, maxFitness)
            val afterCrossovers = queenCrossover.makeCrossovers(sortedByFitness).toMutableList()
            for (j in 0 until populationSize) {
                val currentSolution = afterCrossovers[j]
                val fitnessAfterCrossover = maxFitness - heuristicProvider.calculateHeuristicCost(currentSolution)
                if (fitnessAfterCrossover == maxFitness) {
                    return currentSolution
                } else {
                    afterCrossovers[j] = queenMutator.mutate(currentSolution)
                    val fitnessAfterMutation = maxFitness - heuristicProvider.calculateHeuristicCost(currentSolution)
                    if (fitnessAfterMutation == maxFitness) {
                        return currentSolution
                    }
                }
            }
        }
        return null
    }

    private fun getMaxFitness(n: Int): Int = n * (n - 1) / 2

    private fun sortByFitness(population: List<IntArray>, maxFitness: Int): List<IntArray> =
            population.sortedBy { maxFitness - heuristicProvider.calculateHeuristicCost(it) }

}

