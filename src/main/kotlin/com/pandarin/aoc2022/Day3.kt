package com.pandarin.aoc2022

import java.util.stream.Collectors

fun main() {
    val inputLines = Common.readInput("/day3.txt").split("\n").filter { it.isNotEmpty() }
    val itemPriorities = generateItemPriorities()

    // First part
    var totalPriority = 0
    for (line in inputLines) {
        val halfLength = line.length / 2
        val firstCompartment = line.substring(0, halfLength)
        val secondCompartment = line.substring(halfLength)
        val matchingItems = findMatchingItems(firstCompartment, secondCompartment)
        totalPriority += matchingItems.sumOf { itemPriorities[it]!! }
    }
    println("Part1: $totalPriority")

    // Second part
    totalPriority = 0
    for ((first, second, third) in inputLines.chunked(3)) {
        val matchingItems = findMatchingItems(first, second, third)
        totalPriority += matchingItems.sumOf { itemPriorities[it]!! }
    }
    println("Part2: $totalPriority")
}

fun findMatchingItems(vararg items: String): Set<Char> {
    return items[0].chars()
        .mapToObj { it.toChar() }
        .filter { char: Char ->
            items.all { it.contains(char) }
        }
        .collect(Collectors.toUnmodifiableSet())
}

fun generateItemPriorities(): Map<Char, Int> {
    val itemPriorities = mutableMapOf<Char, Int>()
    // Lowercase letters
    for ((index, char) in ('a'..'z').withIndex()) {
        itemPriorities[char] = index + 1
    }
    // Uppercase letters
    for ((index, char) in ('A'..'Z').withIndex()) {
        itemPriorities[char] = index + 27
    }
    return itemPriorities
}