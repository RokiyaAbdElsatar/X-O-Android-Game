package com.example.myfirstapplication

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var game: TicTacToeGame
    private lateinit var buttons: Array<Button>
    private lateinit var playerText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = "X-O Game"

        game = TicTacToeGame()

        buttons = arrayOf(
            findViewById(R.id.btn00),
            findViewById(R.id.btn01),
            findViewById(R.id.btn02),
            findViewById(R.id.btn10),
            findViewById(R.id.btn11),
            findViewById(R.id.btn12),
            findViewById(R.id.btn20),
            findViewById(R.id.btn21),
            findViewById(R.id.btn22)
        )

        playerText = findViewById(R.id.currentPlayer)
        val playAgain = findViewById<Button>(R.id.retry)

        for (i in buttons.indices) {
            buttons[i].setOnClickListener {
                onButtonClicked(i)
            }
        }

        playAgain.setOnClickListener {
            game.reset()
            updateUI()
        }

        updateUI()
    }

    private fun onButtonClicked(index: Int) {
        if (game.makeMove(index)) {
            updateUI()
            if (game.winner != null) {
                showBigToast("Player ${game.winner} wins!")
                disableButtons()
            } else if (game.isDraw) {
                showBigToast("Draw!")
            }
        }
    }

    private fun showBigToast(message: String) {
        val inflater = layoutInflater
        val layout = inflater.inflate(R.layout.custom_toast, null)
        val textView = layout.findViewById<TextView>(R.id.toast_text)
        textView.text = message

        val toast = Toast(applicationContext)
        toast.duration = Toast.LENGTH_SHORT
        toast.view = layout
        toast.show()
    }

    private fun updateUI() {
        for (i in buttons.indices) {
            buttons[i].text = game.getCellValue(i)
            buttons[i].isEnabled = (game.getCellValue(i) == "" && game.winner == null)
        }
        playerText.text = "The Player: ${game.currentPlayer}"
    }

    private fun disableButtons() {
        for (button in buttons) {
            button.isEnabled = false
        }
    }
}
