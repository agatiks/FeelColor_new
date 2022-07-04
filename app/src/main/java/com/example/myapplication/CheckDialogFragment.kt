package com.example.myapplication

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.example.myapplication.databinding.CheckDialogBinding

class CheckDialogFragment(val question: ColoredObject, val answer: ColoredObject): DialogFragment() {
    private lateinit var binding: CheckDialogBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = requireActivity().layoutInflater
        binding = CheckDialogBinding.inflate(LayoutInflater.from(context))
        binding.questionCheck.setColorFilter(question.getColor())
        binding.answerCheck.setColorFilter(answer.getColor())
        val dE = answer.dE(question)
        binding.equality.text = toAnswer(dE)
        binding.dE.text = dE.toString()
        return activity?.let { AlertDialog.Builder(it).apply { setView(binding.root) }.create()}
            ?: throw IllegalStateException("Activity cannot be null")
    }

    private fun toAnswer(dE: Double): String {
        if (dE > 20) {
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