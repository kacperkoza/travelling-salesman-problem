package komiwojazer


fun distanceBetweenCities(first: City, second: City): Double {
    return Math.sqrt(
            Math.pow(first.x.toDouble() - second.x.toDouble(), 2.0) + Math.pow(first.y.toDouble() - second.y.toDouble(), 2.0)
    )
}

fun calculateRouteLength(route: Route): Double = (0..route.cities.size - 2).sumByDouble { distanceBetweenCities(route.cities[it], route.cities[it + 1]) }