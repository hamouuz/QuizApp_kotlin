package com.example.quizapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputBinding
import android.widget.Toast
import android.widget.Button
import com.example.quizapp.databinding.ActivityMain2Binding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding

    private val questions = arrayOf("Qui a gagné la Champions League Africaine en 2017 ?",
        "En quelle année le 1er Android a été créer par Google ?",
        "Complete cette phrase : Tata ya tata ya .....")


    private val options = arrayOf(arrayOf("Wydad", "Jahly", "Tarachi"),
        arrayOf("2010", "2006", "2008"),
        arrayOf("ahbabi", "awladi", "asdikayi"))


    private val correctAnswers = arrayOf(0, 2, 1)

    private var currentQuestionsIndex = 0
    private var score = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        displayQuestion()

        binding.option1Button.setOnClickListener{
            checkAnswer(0)
        }

        binding.option2Button.setOnClickListener{
            checkAnswer(1)
        }

        binding.option3Button.setOnClickListener{
            checkAnswer(2)
        }

        binding.restartButton.setOnClickListener{
            restartQuiz()
        }

    }

    private fun  correctButtonColors(buttonIndex: Int){
        when(buttonIndex){
            0 -> binding.option1Button.setBackgroundColor(Color.GREEN)
            1 -> binding.option2Button.setBackgroundColor(Color.GREEN)
            2 -> binding.option3Button.setBackgroundColor(Color.GREEN)
        }
    }

    private fun  wrongButtonColors(buttonIndex: Int){
        when(buttonIndex){
            0 -> binding.option1Button.setBackgroundColor(Color.RED)
            1 -> binding.option2Button.setBackgroundColor(Color.RED)
            2 -> binding.option3Button.setBackgroundColor(Color.RED)
        }
    }

    private fun  resetButtonColors(){
        binding.option1Button.setBackgroundColor(Color.rgb(50, 59, 96))
        binding.option2Button.setBackgroundColor(Color.rgb(50, 59, 96))
        binding.option3Button.setBackgroundColor(Color.rgb(50, 59, 96))
    }

    private fun showResults(){
        Toast.makeText(this, "Ton score: $score sur ${questions.size}", Toast.LENGTH_LONG).show()
        binding.restartButton.isEnabled = true
    }

    private fun displayQuestion(){
        binding.questionText.text = questions[currentQuestionsIndex]
        binding.option1Button.text = questions[currentQuestionsIndex][0].toString()
        binding.option2Button.text = questions[currentQuestionsIndex][1].toString()
        binding.option3Button.text = questions[currentQuestionsIndex][2].toString()
        resetButtonColors()
    }

    private fun  checkAnswer(selectedAnswerIndex: Int){
        val correctAnswerIndex = correctAnswers[currentQuestionsIndex]

        if (selectedAnswerIndex == correctAnswerIndex){
            score++
            correctButtonColors(selectedAnswerIndex)
        } else {
            wrongButtonColors(selectedAnswerIndex)
            correctButtonColors(correctAnswerIndex)
        }

        if (currentQuestionsIndex < questions.size - 1){
            currentQuestionsIndex++
            binding.questionText.postDelayed({displayQuestion()}, 1000)

        } else {
            showResults()
        }
    }

    private fun restartQuiz(){
        currentQuestionsIndex = 0
        score = 0
        displayQuestion()
        binding.restartButton.isEnabled = false
    }

}