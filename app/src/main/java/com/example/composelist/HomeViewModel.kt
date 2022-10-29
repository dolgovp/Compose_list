package com.example.composelist

import android.text.TextUtils.lastIndexOf
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    private val _numbers = mutableStateListOf<Int>()
    val numbers: List<Int> = _numbers

    fun addElement() {
        _numbers.add(_numbers.lastIndex+1)
    }
}