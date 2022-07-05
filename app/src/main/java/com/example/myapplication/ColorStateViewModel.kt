package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ColorStateViewModel: ViewModel() {
    private val _nextColorEvent = MutableLiveData<Boolean>()
    var nextColorEvent: LiveData<Boolean> = _nextColorEvent
    private fun nextColorEvent(state: Boolean) {
        _nextColorEvent.value = state
    }
    lateinit var question: ColoredObject
    lateinit var answer: ColoredObject

    init {
        update()
    }

    fun update() {
        question = ColoredObject()
        answer = ColoredObject(0, 0, 0)

        nextColorEvent(true)
    }
}