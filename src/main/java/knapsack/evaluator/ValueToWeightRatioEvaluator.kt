package knapsack.evaluator

import knapsack.Knapsack

class ValueToWeightRatioEvaluator : HeuristicEvaluator {

    override fun evaluate(knapsack: Knapsack) = knapsack.calculateValueDividedByWeight()

}