package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.databinding.ActivityMainBinding

@SuppressLint("SetTextI18n")
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.buttonPlay.setOnClickListener { _ ->
            if (binding.editTextName.length() < 1) {
                binding.textViewStatus.text = "請輸入玩家姓名"
                return@setOnClickListener
            }

            val playerName = binding.editTextName.text.toString()
            binding.textViewName.text = "名字\n$playerName"

            val playerChoice = when {
                binding.radioButtonScissors.isChecked -> Choice.SCISSORS
                binding.radioButtonRock.isChecked -> Choice.ROCK
                else -> Choice.PAPER
            }
            binding.textViewPlayerPlayed.text = "我方出拳\n$playerChoice"

            val computerChoice = Choice.entries.random()
            binding.textViewComputerPlayed.text = "電腦出拳\n$computerChoice"

            when (determineWinner(playerChoice, computerChoice)) {
                Result.WIN -> {
                    binding.textViewWinner.text = "勝利者\n$playerName"
                    binding.textViewStatus.text = "恭喜您獲勝了！！！"
                }

                Result.LOSE -> {
                    binding.textViewWinner.text = "勝利者\n電腦"
                    binding.textViewStatus.text = "可惜，電腦獲勝了！"
                }

                Result.TIE -> {
                    binding.textViewWinner.text = "勝利者\n平手"
                    binding.textViewStatus.text = "平局，請再試一次！"
                }
            }
        }
    }
}

enum class Choice {
    ROCK, PAPER, SCISSORS;

    override fun toString() = when (this) {
        ROCK -> "石頭"
        PAPER -> "布"
        SCISSORS -> "剪刀"
    }
}

enum class Result { WIN, LOSE, TIE }

fun determineWinner(player: Choice, computer: Choice): Result = when {
    player == computer -> Result.TIE
    (player == Choice.SCISSORS && computer == Choice.PAPER) ||
            (player == Choice.ROCK && computer == Choice.SCISSORS) ||
            (player == Choice.PAPER && computer == Choice.ROCK) -> Result.WIN

    else -> Result.LOSE
}
