package com.pandarin.aoc2022

fun main() {
    val inputLines = Common.readInput("/day1.txt").split("\n")
    val entries = mutableListOf<Int>()
    var accumulator = 0
    for (line in inputLines) {
        if (line.isEmpty()) {
            entries.add(accumulator)
            accumulator = 0
            continue
        }
        accumulator += line.toInt()
    }
    // We know there is an empty line at the end of the input,
    // so last entry does not need to be added here after the loop
    println("Part1: ${entries.max()}")

    val topThreeElvesSum = entries.stream()
        .sorted(Comparator.reverseOrder())
        .limit(3)
        .toList()
        .sum()
    println("Part2: $topThreeElvesSum")
}