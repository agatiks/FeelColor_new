package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.SeekBar
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val colorStateViewModel: ColorStateViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        colorStateViewModel.nextColorEvent.observe(this, { isUpd -> if(isUpd) update() })

        colorStateViewModel.update()

        //todo: A lot of copypaste
        binding.redSeekBar2.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar, progress: Int, fromUser: Boolean) {
                colorStateViewModel.answer.setR(progress)
                setChangedInfo(colorStateViewModel.answer)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })

        binding.greenSeekBar2.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar, progress: Int, fromUser: Boolean) {
                colorStateViewModel.answer.setG(progress)
                setChangedInfo(colorStateViewModel.answer)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })

        binding.blueSeekBar2.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar, progress: Int, fromUser: Boolean) {
                colorStateViewModel.answer.setB(progress)
                setChangedInfo(colorStateViewModel.answer)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })
        binding.check.setOnClickListener {
            val dialog = CheckDialogFragment()
            val manager = supportFragmentManager
            dialog.show(manager, "checkDialog") //todo: create key
        }
    }

    private fun setChangedInfo(answer: ColoredObject) {
        binding.tr.text = answer.getR().toString()
        binding.tg.text = answer.getG().toString()
        binding.tb.text = answer.getB().toString()
        binding.argb.text = answer.toString()
        binding.answerView.setColorFilter(answer.getColor())
    }

    private fun update() {
        binding.redSeekBar2.progress = 0 //todo: custom seekBarView or other kind of color choosing interface
        binding.greenSeekBar2.progress = 0
        binding.blueSeekBar2.progress = 0
        binding.questionView.setColorFilter(colorStateViewModel.question.getColor())
        binding.answerView.setColorFilter(colorStateViewModel.answer.getColor())
    }
}