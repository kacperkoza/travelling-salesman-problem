package knapsack.java;

import java.security.SecureRandom;
import java.util.Comparator;
import java.util.List;

public class HCKnapsackProblem {

    private ValueToWeightEvaluator valueToWeightEvaluator = new ValueToWeightEvaluator();
    private NeighborGenerator neighborGenerator = new NeighborGenerator();
    private SecureRandom secureRandom = new SecureRandom();

    public Knapsack findSolution(List<Item> items, Knapsack knapsack) {
        Knapsack bestKnapsack = knapsack;
        int randomIndex = secureRandom.nextInt(items.size());
        bestKnapsack.put(items.get(randomIndex));

        while (bestKnapsack.canBeAddedAnyOf(items) && !bestKnapsack.isFull()) {
            List<Knapsack> possibleNewItems = generateNeighbors(items, bestKnapsack);

            if (!possibleNewItems.isEmpty()) {
                List<Knapsack> sortedByHeuristic = sortByHeuristicEvaluate(possibleNewItems);
                bestKnapsack = sortedByHeuristic.get(0);
                items.remove(bestKnapsack.getItems().get(bestKnapsack.getItems().size() - 1));
            }
        }
        return bestKnapsack;
    }

    private List<Knapsack> generateNeighbors(List<Item> items, Knapsack knapsack) {
        return neighborGenerator.generate(items, knapsack);
    }

    private List<Knapsack> sortByHeuristicEvaluate(List<Knapsack> items) {
        items.sort(Comparator.comparingDouble(k -> valueToWeightEvaluator.evaluate(k)));
        return items;
    }

}