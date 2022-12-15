package com.pandarin.aoc2022

class Range(val from: Int, val until: Int)
typealias RangePair = Pair<Range, Range>

fun RangePair.fullyOverlaps(): Boolean {
    return (first.from >= second.from && first.until <= second.until) ||
            (second.from >= first.from && second.until <= first.until)
}

fun RangePair.hasOverlap(): Boolean {
    return (first.from >= second.from && first.from <= second.until) ||
            (second.from >= first.from && second.from <= first.until)
}

fun parseRangePair(entry: String): RangePair {
    val (first, second) = entry.split(",")
    val (firstFrom, firstUntil) = first.split("-")
    val (secondFrom, secondUntil) = second.split("-")
    return RangePair(
        first = Range(firstFrom.toInt(), firstUntil.toInt()),
        second = Range(secondFrom.toInt(), secondUntil.toInt()))
}

fun main() {
    val inputLines = Common.readInput("/day4.txt").split("\n").filter { it.isNotEmpty() }
    val rangePairs = inputLines.stream().map { parseRangePair(it) }.toList()
    val numberOfFullyOverlappingRanges = rangePairs.count(RangePair::fullyOverlaps)
    println("Part1: $numberOfFullyOverlappingRanges")

    val numberOfPartiallyOverlappingRanges = rangePairs.count(RangePair::hasOverlap)
    println("Part2: $numberOfPartiallyOverlappingRanges")
}