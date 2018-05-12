package nqueens.heuristic.genetic

import java.security.SecureRandom

class QueenMutator(
        private val mutateProbability: Double
) {

    private val secureRandom = SecureRandom()

    fun mutate(array: IntArray): IntArray {
        return if (isSatisfyingProbe(mutateProbability)) {
            mutateRandomIndex(array)
        } else {
            array
        }
    }

    private fun mutateRandomIndex(array: IntArray): IntArray {
        val arraySize = array.size
        val copy = array.copyOf()
        copy[secureRandom.nextInt(arraySize)] = secureRandom.nextInt(arraySize)
        return copy
    }

    private fun isSatisfyingProbe(prob: Double) = prob >= secureRandom.nextDouble()

}
