// GameLogic.kt
package com.example.myfirstapplication

class TicTacToeGame {

    var currentPlayer = 'X'
    private val board = Array(9) { "" }
    var winner: Char? = null
    var isDraw = false
    fun reset() {
        for (i in board.indices) {
            board[i] = ""
        }
        currentPlayer = 'X'
        winner = null
        isDraw = false
    }

    fun makeMove(index: Int): Boolean {
        if (index < 0 || index >= 9) return false
        if (board[index] != "") return false

        board[index] = currentPlayer.toString()

        if (checkWinner()) {
            winner = currentPlayer
        } else if (board.all { it != "" }) {
            isDraw = true
        } else {
            switchPlayer()
        }
        return true
    }

    private fun switchPlayer() {
        currentPlayer = if (currentPlayer == 'X') 'O' else 'X'
    }

    private fun checkWinner(): Boolean {
        val winPositions = arrayOf(
            intArrayOf(0,1,2), intArrayOf(3,4,5), intArrayOf(6,7,8),//Rows
            intArrayOf(0,3,6), intArrayOf(1,4,7), intArrayOf(2,5,8),//Cols
            intArrayOf(0,4,8), intArrayOf(2,4,6)//Diago
        )
        for (pos in winPositions) {
            if (board[pos[0]] == currentPlayer.toString() &&
                board[pos[1]] == currentPlayer.toString() &&
                board[pos[2]] == currentPlayer.toString()) {
                return true
            }
        }
        return false
    }
    fun getCellValue(index: Int): String {
        return if (index in 0..8) board[index] else ""
    }
}
