package com.pandarin.aoc2022

import java.lang.IllegalArgumentException

enum class Outcome(val score: Int, val shapeToChoose: (Shape) -> Shape) {
    LOSE(0, shapeToChoose = {
        when (it) {
            Shape.ROCK -> Shape.SCISSOR
            Shape.PAPER -> Shape.ROCK
            Shape.SCISSOR -> Shape.PAPER
        }
    }),
    DRAW(3, shapeToChoose = { it }),
    WIN(6, shapeToChoose = {
        when (it) {
            Shape.ROCK -> Shape.PAPER
            Shape.PAPER -> Shape.SCISSOR
            Shape.SCISSOR -> Shape.ROCK
        }
    });

    companion object {

        fun decrypt(encryptedOutcome: String) = when (encryptedOutcome) {
            "X" -> LOSE
            "Y" -> DRAW
            "Z" -> WIN
            else -> throw IllegalArgumentException()
        }

    }

}

enum class Shape(val score: Int, val outcomeAgainst: (Shape) -> Outcome) {

    ROCK(1, outcomeAgainst = {
        when (it) {
            ROCK -> Outcome.DRAW
            PAPER -> Outcome.LOSE
            SCISSOR -> Outcome.WIN
        }
    }),
    PAPER(2, outcomeAgainst = {
        when (it) {
            ROCK -> Outcome.WIN
            PAPER -> Outcome.DRAW
            SCISSOR -> Outcome.LOSE
        }
    }),
    SCISSOR(3, outcomeAgainst = {
        when (it) {
            ROCK -> Outcome.LOSE
            PAPER -> Outcome.WIN
            SCISSOR -> Outcome.DRAW
        }
    });

    companion object {

        fun decrypt(encryptedShape: String) = when (encryptedShape) {
            "A", "X" -> ROCK
            "B", "Y" -> PAPER
            "C", "Z" -> SCISSOR
            else -> throw IllegalArgumentException()
        }

    }

}

fun main() {
    val inputLines = Common.readInput("/day2.txt").split("\n").filter { it.isNotEmpty() }
    var part1Score = 0
    var part2Score = 0
    for (line in inputLines) {
        val (enemyShapeEncrypted, myActionEncrypted) = line.split(" ")
        val enemyShape = Shape.decrypt(enemyShapeEncrypted)
        val myShapePart1 = Shape.decrypt(myActionEncrypted)
        val outcomePart1 = myShapePart1.outcomeAgainst.invoke(enemyShape)
        val outcomePart2 = Outcome.decrypt(myActionEncrypted)
        val myShapePart2 = outcomePart2.shapeToChoose.invoke(enemyShape)
        part1Score += myShapePart1.score + outcomePart1.score
        part2Score += myShapePart2.score + outcomePart2.score
    }
    println("Part1: $part1Score")
    println("Part2: $part2Score")
}