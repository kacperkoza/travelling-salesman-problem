package knapsack

import knapsack.evaluator.ValueToWeightRatioEvaluator
import knapsack.neighbor.SimpleNeighborGenerator
import spock.lang.Specification

class HillClimbKnapsackProblemTest extends Specification {

    HillClimbKnapsackProblem hillClimbKnapsackProblem = new HillClimbKnapsackProblem(
            new ValueToWeightRatioEvaluator(),
            new SimpleNeighborGenerator()
    )

    def 'should evaluate hill climb algorithm taking the best items with value/weight ratio as first'() {
        given:
        def items = generateItems()
        def knapsack = new Knapsack(16)

        when:
        def result = hillClimbKnapsackProblem.findSolution(items, knapsack)

        then:
        result.calculateValue() == 20
        result.items.collect({ it.value }) == [3, 2, 8, 7]
        result.items.collect({ it.weight }) == [1, 1, 5, 6]
    }

    def 'should stop when knapstack is full'() {
        given:
        def knapsack = new Knapsack(6)
        def items = []
        5.times { items.add(new Item(3, 2)) }

        when:
        def result = hillClimbKnapsackProblem.findSolution(items, knapsack)

        then:
        result.items.size() == 3
        result.calculateValue() == 9
    }

    private List<Item> generateItems() {
        return [
                new Item(8, 5),
                new Item(7, 6),
                new Item(12, 10),
                new Item(6, 4),
                new Item(2, 1),
                new Item(3, 1)
        ]
    }

}
