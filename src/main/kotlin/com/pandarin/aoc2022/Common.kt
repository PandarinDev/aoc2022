package com.pandarin.aoc2022

class Common private constructor() {

    companion object {
        fun readInput(file: String) = object{}.javaClass.getResource(file).readText()
    }

}