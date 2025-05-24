package com.example.composepokedex.ui.pokemonlist

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.palette.graphics.Palette
import com.example.composepokedex.data.model.PokedexListEntry
import com.example.composepokedex.data.remote.PokeService
import com.example.composepokedex.repository.PokemonRepo
import com.example.composepokedex.util.Constants.PAGE_SIZE
import com.example.composepokedex.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ph.theorangeco.data.models.states.UiState
import javax.inject.Inject

@HiltViewModel
class PokemonListViewmodel @Inject constructor(
    private val repo: PokemonRepo,
    private val pokeApi: PokeService
): ViewModel(){

    private var curPage = 0

    var pokemonList = mutableStateOf<List<PokedexListEntry>>(listOf())
    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(false)
    var endReached = mutableStateOf(false)

    var pokemons: Flow<PagingData<com.example.composepokedex.data.model.response.Result>>
    private var _pokemonNameList: MutableStateFlow<UiState> = MutableStateFlow(UiState.Initial)
    val pokemonNameList = _pokemonNameList.asStateFlow()
    var job: Job? = null
    init {
     /*   loadPokemonPaginated()*/
       /* loadPokemonList()*/
        pokemons = repo.getPokemonListViaPaging().cachedIn(viewModelScope)
        /*viewModelScope.launch{
            repo.insertPokemonList(
                listOf(PokemonName("Pikachu"), PokemonName("Squirtle"),PokemonName("Baulbasaur"))
            )
        }.invokeOnCompletion {
           // viewModelScope.launch{ pokemonNameList = repo.getPokemonNameList("Pikachu").single() }

           // Timber.tag("PokemonListViewModel").d("pokemonList asf ${ pokemonNameList.toString() }")

        }*/


    }

    fun getPokemonNameSuggestion(text: String){
        job?.cancel()

        job = viewModelScope.launch(Dispatchers.IO) {
                _pokemonNameList.value = UiState.Processing
                delay(3000)
                val local = repo.getPokemonNameList(text)
                local.collectLatest {
                    _pokemonNameList.value = UiState.Success(it)
                }
            }

    }
    fun loadPokemonList(){
        viewModelScope.launch {
            val result = repo.getPokemonListf(PAGE_SIZE, 2)
            result.collect(){
                when(it){
                    is Response.Error -> {

                    }
                    is Response.Success -> {
                        val pokedexEntries = it.data?.results!!.mapIndexed{ index: Int, result: com.example.composepokedex.data.model.response.Result ->

                            val number = if(result.url.endsWith("/")){
                                result.url.dropLast(1).takeLastWhile { it.isDigit() }
                            }else{
                                result.url.takeLastWhile { it.isDigit() }
                            }

                            val url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${number}.png"
                            PokedexListEntry(number = number.toInt(), pokemonName =  result.name, imageUrl =  url)
                        }
                        curPage++

                        loadError.value= ""
                        isLoading.value=false
                        pokemonList.value += pokedexEntries
                        Log.d("PokemonListViewModel", pokemonList.toString())
                    }
                }
            }
        }
    }


    fun loadPokemonPaginated() {
        viewModelScope.launch {
            isLoading.value = true
            val result = repo.getPokemonList(PAGE_SIZE, curPage * PAGE_SIZE)
            when(result) {
                is Response.Success -> {
                    endReached.value = curPage * PAGE_SIZE >= result.data!!.count!!
                    val pokedexEntries = result.data.results!!.mapIndexed{ index: Int, result: com.example.composepokedex.data.model.response.Result ->

                        val number = if(result.url.endsWith("/")){
                            result.url.dropLast(1).takeLastWhile { it.isDigit() }
                        }else{
                            result.url.takeLastWhile { it.isDigit() }
                        }

                        val url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${number}.png"
                        PokedexListEntry(number = number.toInt(), pokemonName =  result.name, imageUrl =  url)
                    }
                    curPage++

                    loadError.value= ""
                    isLoading.value=false
                    pokemonList.value += pokedexEntries
                    Log.d("PokemonListViewModel", pokemonList.toString())

                }

                is Response.Error -> {
                    loadError.value = result.message!!
                    isLoading.value = false
                }


            }
        }
    }



    fun calcDominantColor(drawable: Drawable, onFinish: (Color) -> Unit){
        val bmp = (drawable as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888, true)
        Palette.from(bmp).generate(){palette ->
            palette?.dominantSwatch?.rgb?.let { colorValue ->
                onFinish(Color(colorValue))
            }
        }
    }


}