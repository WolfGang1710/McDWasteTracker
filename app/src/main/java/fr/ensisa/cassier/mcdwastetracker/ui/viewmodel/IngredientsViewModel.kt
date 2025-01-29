package fr.ensisa.cassier.mcdwastetracker.ui.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.ensisa.cassier.mcdwastetracker.models.Ingredient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class IngredientsViewModel @Inject constructor() : ViewModel() {
    private val _ingredients = MutableStateFlow<List<Ingredient>>(emptyList())
    val ingredients: StateFlow<List<Ingredient>> = _ingredients

    fun addIngredient(ingredient: Ingredient) {
        _ingredients.update { it + ingredient }
    }

    fun editIngredient(updatedIngredient: Ingredient) {
        _ingredients.update { ingredients ->
            ingredients.map { if (it.id == updatedIngredient.id) updatedIngredient else it }
        }
    }

    fun deleteIngredient(ingredient: Ingredient) {
        _ingredients.update { it.filter { it.id != ingredient.id } }
    }
}