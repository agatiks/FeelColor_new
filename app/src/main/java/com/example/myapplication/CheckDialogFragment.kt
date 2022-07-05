package com.example.myapplication

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.myapplication.databinding.CheckDialogBinding

class CheckDialogFragment: DialogFragment() {
    private lateinit var binding: CheckDialogBinding
    private val colorStateViewModel: ColorStateViewModel by activityViewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val question = colorStateViewModel.question
        val answer = colorStateViewModel.answer
        binding = CheckDialogBinding.inflate(layoutInflater)
        binding.questionCheck.setColorFilter(question.getColor())
        binding.answerCheck.setColorFilter(answer.getColor())
        val dE = answer.dE(question)
        binding.equality.text = toAnswer(dE)
        binding.dE.text = String.format("%.2f", dE)
        if (binding.next.isClickable) {
            binding.next.setOnClickListener {
                colorStateViewModel.update()
                dismiss()
            }
        }
        return activity?.let { AlertDialog.Builder(it).apply { setView(binding.root) }.create()}
            ?: throw IllegalStateException("Activity cannot be null")
    }

    private fun toAnswer(dE: Double): String {
        if (dE > 20) {
            binding.next.isClickable = false
            //todo: nullable checks not okey
            val color =  context?.let { ContextCompat.getColor(it, R.color.gray) }
            if (color != null) {
                binding.next.setBackgroundColor(color)
            }
            return "Colors are too different!\nTry again!"
        } else if (dE > 10) {
            return "You can do better than that!"
        } else if (dE > 6) {
            return "It's very similar..."
        } else if (dE > 2.3) {
            return "Perfect!"
        } else {
            return "You're machine!"
        }
    }
}