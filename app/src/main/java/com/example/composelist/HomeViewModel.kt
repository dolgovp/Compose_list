package com.example.composelist

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    private val _numbers = mutableStateListOf<Int>()
    val numbers: List<Int> = _numbers

    fun addElement() {
        _numbers.add(_numbers.lastIndex+1)
    }
    fun restoreElement(int: Int) {
        _numbers.add(int)
    }
}