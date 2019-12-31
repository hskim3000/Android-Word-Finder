package com.araujo.jordan.wordfindify

import android.widget.TextView
import java.util.*
import kotlin.random.Random

class BoardBuilder(val board: ArrayList<ArrayList<TextView>>) {

    private val source = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    private val width = board.size
    private val height = board[0].size

    fun build(word: Array<String>) {

        val wordsAvailable = ArrayList<String>()
        wordsAvailable.addAll(word)

        while (wordsAvailable.size > 0) {
            val wordToAdd = wordsAvailable.first()
            if (Random.nextBoolean()) {
                canAddVertically(wordToAdd)?.let { arrayOfAvailablePosition ->
                    addVertically(wordToAdd, arrayOfAvailablePosition)
                    wordsAvailable.removeAt(0)
                }
            } else {
                canAddHorizontally(wordToAdd)?.let { arrayOfAvailablePosition ->
                    addHorizontally(wordToAdd, arrayOfAvailablePosition)
                    wordsAvailable.removeAt(0)
                }
            }
        }

        for (w in 0 until width)
            for (h in 0 until height)
                if (board[w][h].text.isEmpty())
                    board[w][h].text = source.random().toString()
    }

    private fun canAddVertically(wordToPut: String): Array<Int>? {

        val posX = Random.nextInt(0, width - 1)
        val posY = Random.nextInt(0, height - 1)

        try {
            //check if is possible to put in it
            for (position in wordToPut.indices) {
                if (board[posX + position][posY].text.toString() == "" || board[posX + position][posY].char == wordToPut[position].toString()) {
                    // can put it here
                } else {
                    return null
                }
            }
            return arrayOf(posX, posY)
        } catch (err: Exception) { //out of bonds
            return null
        }
    }

    private fun addVertically(wordToPut: String, positionToPut: Array<Int>) {
        for (offset in wordToPut.indices)
            board[positionToPut[0] + offset][positionToPut[1]]
    }

    private fun canAddHorizontally(wordToPut: String): Array<Int>? {

        val posX = Random.nextInt(0, width - 1)
        val posY = Random.nextInt(0, height - 1)

        try {
            //check if is possible to put in it
            for (position in wordToPut.indices) {
                if (board[posX][posY + position].text.toString() == "" || board[posX][posY + position].char == wordToPut[position].toString()) {
                    // can put it here
                } else {
                    return null
                }
            }
            return arrayOf(posX, posY)
        } catch (err: Exception) { //out of bonds
            return null
        }
    }

    private fun addHorizontally(wordToPut: String, positionToPut: Array<Int>) {
        for (offset in wordToPut.indices)
            board[positionToPut[0]][positionToPut[1] + offset]
    }

    var TextView.char
        get() = this.text.toString()
        set(value) {
            this.setText(value)
        }

}