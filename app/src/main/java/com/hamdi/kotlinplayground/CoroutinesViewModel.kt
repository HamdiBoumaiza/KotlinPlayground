package com.hamdi.kotlinplayground

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CoroutinesViewModel : ViewModel() {


    val live = liveData(Dispatchers.IO) {
        emit("")
    }

    fun setUserId(userId: String) {
        viewModelScope.launch {

        }
    }

}















