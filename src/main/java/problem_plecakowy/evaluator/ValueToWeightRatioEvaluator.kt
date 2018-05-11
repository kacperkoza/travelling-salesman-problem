package problem_plecakowy.evaluator

import problem_plecakowy.Knapsack

class ValueToWeightRatioEvaluator : HeuristicEvaluator {

    override fun evaluate(knapsack: Knapsack) = knapsack.calculateValueDividedByWeight()

}