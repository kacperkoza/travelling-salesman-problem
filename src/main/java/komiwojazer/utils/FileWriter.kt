package komiwojazer.utils

import komiwojazer.GeneticAlgorithmProperties
import komiwojazer.Route
import org.apache.commons.io.FileUtils
import org.joda.time.DateTime
import java.io.File

class FileWriter(fileName: String) {

    private val file = File(fileName)

    fun writeToFileWithTimeStamp(route: Route, properties: GeneticAlgorithmProperties) {
        val currentDate = DateTime()
        FileUtils.writeStringToFile(file, "Result: ${calculateRouteLength(route)}\n", true)
        FileUtils.writeStringToFile(file, "Number of generations: ${properties.numberOfGenerations}. " +
                "number of crosses in single iteration: ${properties.numberOfCrossesInSingleGeneration}\n", true)
        FileUtils.writeStringToFile(file, "$route on ${currentDate.toString("dd-MM-yyyy HH:mm")}", true)
        FileUtils.writeStringToFile(file, "\n\n", true)
    }
}