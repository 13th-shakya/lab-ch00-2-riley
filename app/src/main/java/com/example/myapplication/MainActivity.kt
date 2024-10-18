package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Connect XML components
        val editTextName = findViewById<EditText>(R.id.editTextName)
        val textViewStatus = findViewById<TextView>(R.id.textViewStatus)
        val radioButtonScissors = findViewById<RadioButton>(R.id.radioButtonScissors)
        val radioButtonRock = findViewById<RadioButton>(R.id.radioButtonRock)
        val radioButtonPaper = findViewById<RadioButton>(R.id.radioButtonPaper)
        val buttonPlay = findViewById<Button>(R.id.buttonPlay)
        val textViewName = findViewById<TextView>(R.id.textViewName)
        val textViewWinner = findViewById<TextView>(R.id.textViewWinner)
        val textViewPlayerPlayed = findViewById<TextView>(R.id.textViewPlayerPlayed)
        val textViewComputerPlayed = findViewById<TextView>(R.id.textViewComputerPlayed)

        buttonPlay.setOnClickListener { _ ->
            if (editTextName.length() < 1) {
                textViewStatus.text = "請輸入玩家姓名"
                return@setOnClickListener
            }

            textViewName.text = "名字\n" + editTextName.text

            if (radioButtonScissors.isChecked) {
                textViewPlayerPlayed.text = "我方出拳\n剪刀"
            } else if (radioButtonRock.isChecked) {
                textViewPlayerPlayed.text = "我方出拳\n石頭"
            } else {
                textViewPlayerPlayed.text = "我方出拳\n布"
            }

            val computerRandom = (Math.random() * 3).toInt()
            if (computerRandom == 0) {
                textViewComputerPlayed.text = "電腦出拳\n剪刀"
            } else if (computerRandom == 1) {
                textViewComputerPlayed.text = "電腦出拳\n石頭"
            } else {
                textViewComputerPlayed.text = "電腦出拳\n布"
            }

            if ((radioButtonScissors.isChecked && computerRandom == 2) ||
                (radioButtonRock.isChecked && computerRandom == 0) ||
                (radioButtonPaper.isChecked && computerRandom == 1)) {
                textViewWinner.text = "勝利者\n" + editTextName.text
                textViewStatus.text = "恭喜您獲勝了！！！"
            } else if ((radioButtonScissors.isChecked && computerRandom == 1) ||
                (radioButtonRock.isChecked && computerRandom == 2) ||
                (radioButtonPaper.isChecked && computerRandom == 0)) {
                textViewWinner.text = "勝利者\n電腦"
                textViewStatus.text = "可惜，電腦獲勝了！"
            } else {
                textViewWinner.text = "勝利者\n平手"
                textViewStatus.text = "平局，請再試一次！"
            }
        }

    }
}
