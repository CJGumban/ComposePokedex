package com.example.composepokedex.ui.pokemonstats

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composepokedex.data.handlers.LogHandler
import com.example.composepokedex.repository.PokemonRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ph.theorangeco.data.models.states.UiState
import ph.theorangeplatform.camtime.ui.common.singleOrError
import javax.inject.Inject

@HiltViewModel
class PokemonStatsViewmodel @Inject constructor(
    private val repo: PokemonRepo,
): ViewModel(){
    private var _pokemonStats:  MutableStateFlow<UiState> = MutableStateFlow(UiState.Initial)
    val pokemonStats = _pokemonStats.asStateFlow()
    var job: Job? = null



    fun getPokemonStats(url: String){
        job?.cancel()
        job = viewModelScope.launch(Dispatchers.IO) {
           _pokemonStats.value = UiState.Loading
            repo.getPokemonInfo(url).singleOrError(
                {
                    _pokemonStats.value = UiState.Success(it)
                    LogHandler.d("pokemonStats: $it")

                },{e->
                    _pokemonStats.value = UiState.Failed(e.message.toString())
                }
            )
        }
    }

}