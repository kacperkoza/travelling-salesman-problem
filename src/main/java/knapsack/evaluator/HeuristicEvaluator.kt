package knapsack.evaluator

import knapsack.Knapsack

interface HeuristicEvaluator {

    fun evaluate(knapsack: Knapsack): Int

}