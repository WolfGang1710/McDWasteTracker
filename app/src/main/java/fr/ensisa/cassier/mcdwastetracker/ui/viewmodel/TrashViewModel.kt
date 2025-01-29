package fr.ensisa.cassier.mcdwastetracker.ui.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.ensisa.cassier.mcdwastetracker.models.enums.Quart
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class TrashViewModel @Inject constructor() : ViewModel() {
    private val _currentQuart = MutableStateFlow<Quart?>(null)
    val currentQuart: StateFlow<Quart?> = _currentQuart

    fun openTrash(quart: Quart, time: LocalTime) {
        _currentQuart.value = quart
    }

    fun closeTrash(time: LocalTime) {
        _currentQuart.value = null
    }

    fun resetTrash() {
        _currentQuart.value = null
    }
}
