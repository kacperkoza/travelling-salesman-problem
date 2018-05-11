package problem_plecakowy.evaluator

import problem_plecakowy.Knapsack

interface HeuristicEvaluator {

    fun evaluate(knapsack: Knapsack): Int

}