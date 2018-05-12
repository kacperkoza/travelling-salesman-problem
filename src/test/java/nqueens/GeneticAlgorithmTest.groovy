package nqueens

import nqueens.heuristic.HeuristicProvider
import nqueens.heuristic.NQueensSolver
import nqueens.heuristic.annealing.AnnealingSolver
import nqueens.heuristic.genetic.GeneticSolver
import nqueens.heuristic.genetic.QueenCrossover
import nqueens.heuristic.genetic.QueenMutator
import spock.lang.Specification

class HeuristicAlgorithmTest extends Specification {

    def 'should find n queens solutions'() {
        given:
        NQueensSolver solver = new GeneticSolver(
                100,
                3000,
                new QueenCrossover(),
                new QueenMutator(0.1d),
                new HeuristicProvider()
        )

        when:
        def result = solver.solve(8)

        then:
        println result
        UtilsKt.draw(result)
    }

    def 'should resolve n queens problem'() {
        given:
        NQueensSolver solver = new AnnealingSolver(
                10000,
                1.0,
                0.1,
                new HeuristicProvider()
        )

        when:
        def result = solver.solve(8)

        then:
        println result
        UtilsKt.draw(result)
    }

}
