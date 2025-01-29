package fr.ensisa.cassier.mcdwastetracker.ui.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.ensisa.cassier.mcdwastetracker.models.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SandwichesViewModel @Inject constructor() : ViewModel() {
    private val _sandwiches = MutableStateFlow<List<Product>>(emptyList())
    val sandwiches: StateFlow<List<Product>> = _sandwiches

    fun addSandwich(sandwich: Product) {
        _sandwiches.update { it + sandwich }
    }

    fun editSandwich(updatedSandwich: Product) {
        _sandwiches.update { sandwiches ->
            sandwiches.map { if (it.id == updatedSandwich.id) updatedSandwich else it }
        }
    }

    fun deleteSandwich(sandwich: Product) {
        _sandwiches.update { it.filter { it.id != sandwich.id } }
    }
}
