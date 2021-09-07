package br.ufrgs.gssales.ihc_tp2_p1e1

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    var number1: Float? = null
    var number2: Float? = null
    val result: MutableLiveData<Float?> by lazy {
        MutableLiveData<Float?>(null)
    }

    fun sumNumbers() {
        val value1 = number1
        val value2 = number2
        if (value1 != null && value2 != null)
            result.postValue(value1 + value2)
        else
            result.postValue(null)
    }
}