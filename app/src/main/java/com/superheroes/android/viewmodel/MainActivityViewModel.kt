package com.superheroes.android.viewmodel

import androidx.lifecycle.viewModelScope
import com.superheroes.android.api.data.Repository
import com.superheroes.android.api.model.Superhero
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val repository: Repository) : BaseViewModel<MainActivityViewModel.SuperheroState>(SuperheroState(emptyList())) {


    fun fetchSuperheros(name: String) = viewModelScope.launch(Dispatchers.Default) {
        val result = withContext(Dispatchers.IO) { repository.getSuperheroByName(name) }
        result.results?.let { setState { copy(data = it) } }
    }

    data class SuperheroState(val data: List<Superhero>)
}

