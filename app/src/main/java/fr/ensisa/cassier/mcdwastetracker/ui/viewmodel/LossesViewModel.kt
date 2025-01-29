package fr.ensisa.cassier.mcdwastetracker.ui.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.ensisa.cassier.mcdwastetracker.models.Ingredient
import fr.ensisa.cassier.mcdwastetracker.models.enums.IngredientCategory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LossesViewModel @Inject constructor() : ViewModel() {

    private val _ingredients = MutableStateFlow<List<Ingredient>>(emptyList())
    val ingredients: StateFlow<List<Ingredient>> = _ingredients

    private val _quantities = MutableStateFlow<Map<Int, Int>>(emptyMap())
    val quantities: StateFlow<Map<Int, Int>> = _quantities

    fun incrementQuantity(ingredient: Ingredient) {
        _quantities.update { quantities ->
            quantities.toMutableMap().apply {
                this[ingredient.id] = (this[ingredient.id] ?: 0) + 1
            }
        }
    }

    fun decrementQuantity(ingredient: Ingredient) {
        _quantities.update { quantities ->
            quantities.toMutableMap().apply {
                val current = this[ingredient.id] ?: 0
                if (current > 0) this[ingredient.id] = current - 1
            }
        }
    }

    fun loadIngredients() {
        _ingredients.value = listOf(
            Ingredient(1, "Nuggets", IngredientCategory.PROTEINS),
            Ingredient(2, "Pain Royal", IngredientCategory.BREADS)
        )
    }
}
