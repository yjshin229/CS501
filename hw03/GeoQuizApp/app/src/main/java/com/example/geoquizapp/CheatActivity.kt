package com.example.geoquizapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.geoquizapp.databinding.ActivityCheatBinding
import androidx.activity.viewModels

const val EXTRA_ANSWER_SHOWN = "com.example.geoquizapp.answer_shown"
private const val EXTRA_ANSWER_IS_TRUE =
    "com.example.geoquizapp.answer_is_true"
class CheatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCheatBinding
    private val cheatViewModel: CheatViewModel by viewModels()

    private var answerIsTrue = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cheatViewModel.isAnswerShown.takeIf { it }?.let {
            showAnswer()
        }

        answerIsTrue = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)

        binding.showAnswerButton.setOnClickListener {
            showAnswer()
            setAnswerShownResult(true)
        }

    }

    private fun showAnswer() {
        val answerText = when {
            answerIsTrue -> R.string.true_button
            else -> R.string.false_button
        }
        binding.answerTextView.setText(answerText)
        cheatViewModel.isAnswerShown = true
    }

    private fun setAnswerShownResult(isAnswerShown: Boolean) {
        val data = Intent().apply {
            putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown)
        }
        setResult(Activity.RESULT_OK, data)
    }

    companion object {
        fun newIntent(packageContext: Context, answerIsTrue: Boolean): Intent {
            return Intent(packageContext, CheatActivity::class.java).apply {
                putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)
            }
        }
    }
}