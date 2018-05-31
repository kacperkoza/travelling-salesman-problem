import knapsack.java.HCKnapsackProblem
import knapsack.java.Item
import knapsack.java.Knapsack
import spock.lang.Specification

class HillClimbKnapsackProblemTest extends Specification {

    HCKnapsackProblem hillClimbKnapsackProblem = new HCKnapsackProblem()

    def 'should evaluate hill climb algorithm taking the best items with value/weight ratio as first'() {
        given:
        def items = generateItems()
        def knapsack = new Knapsack(50)

        when:
        def result = hillClimbKnapsackProblem.findSolution(items, knapsack)

        then:
        println("Value = ${result.calculateValue()}")
        result.printSummary()
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
        result.printSummary()
    }

    private List<Item> generateItems() {
        return [
                new Item(8, 5),
                new Item(7, 6),
                new Item(12, 10),
                new Item(6, 4),
                new Item(2, 1),
                new Item(3, 1),
                new Item(1,3),
                new Item(10,10),
                new Item(5,3),
                new Item(3,3),
                new Item(6,4),
                new Item(3,4),
                new Item(5,7),
                new Item(1,2),
                new Item(4,4),
                new Item(9,13),
                new Item(13,9),
                new Item(15,3),
                new Item(5,15)
        ]
    }

}
