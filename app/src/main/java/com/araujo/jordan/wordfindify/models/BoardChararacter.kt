package com.araujo.jordan.wordfindify.models

data class BoardChararacter(
    var char: String,
    val position: Array<Int>,
    var selected: Boolean = false
)