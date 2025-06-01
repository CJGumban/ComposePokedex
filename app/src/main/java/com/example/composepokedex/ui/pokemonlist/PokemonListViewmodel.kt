package com.example.composepokedex.ui.pokemonlist

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.palette.graphics.Palette
import com.example.composepokedex.repository.PokemonRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import ph.theorangeco.data.models.states.UiState
import javax.inject.Inject

@HiltViewModel
class PokemonListViewmodel @Inject constructor(
    private val repo: PokemonRepo,
): ViewModel(){

    private var curPage = 0

    //var pokemonList = mutableStateOf<List<PokedexListEntry>>(listOf())
    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(false)
    var endReached = mutableStateOf(false)

   // lateinit var pokemons: Flow<PagingData<PokedexListEntry>>

  //  var pokemonList: Flow<PagingData<PokedexListEntry>>

    private var _pokemonNameList: MutableStateFlow<UiState> = MutableStateFlow(UiState.Initial)
    val pokemonNameList = _pokemonNameList.asStateFlow()
    var job: Job? = null
    val _searchQuery = MutableStateFlow("")
    init {
     /*   loadPokemonPaginated()*/
       /* loadPokemonList()*/
        /*viewModelScope.launch{
            repo.insertPokemonList(
                listOf(PokemonName("Pikachu"), PokemonName("Squirtle"),PokemonName("Baulbasaur"))
            )
        }.invokeOnCompletion {
           // viewModelScope.launch{ pokemonNameList = repo.getPokemonNameList("Pikachu").single() }

           // Timber.tag("PokemonListViewModel").d("pokemonList asf ${ pokemonNameList.toString() }")

        }*/


    }
    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val pokemons = _searchQuery
        .debounce(300)
        .distinctUntilChanged()
        .flatMapLatest{
        repo.getPokemonSearchListViaRoomPaging(it).cachedIn(viewModelScope)
    }



    fun getPokemonNameSuggestion(text: String){
        _searchQuery.value = text

    }
  /*  fun loadPokemonList(){
        viewModelScope.launch {
            val result = repo.getPokemonListf(PAGE_SIZE, 2)
            result.collect(){
                when(it){
                    is Response.Error -> {

                    }
                    is Response.Success -> {
                        val pokedexEntries = it.data?.results!!.mapIndexed{ index: Int, result: Result ->

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
    }*/

/*    fun loadPokedex(){
        viewModelScope.launch {
            val c = PokedexPagingSource(pokemonDao)
            val page = Pager(
                config = PagingConfig(
                    pageSize = 20,
                    prefetchDistance = 5,
                    enablePlaceholders = false,
                    initialLoadSize = 20,
                ),
                pagingSourceFactory = {
                    c
                },
                initialKey = 1
            ).flow


        }
    }*/

   /* fun loadPokemonPaginated() {
        viewModelScope.launch {
            isLoading.value = true
            val result = repo.getPokemonList(PAGE_SIZE, curPage * PAGE_SIZE)
            when(result) {
                is Response.Success -> {
                    endReached.value = curPage * PAGE_SIZE >= result.data!!.count!!
                    val pokedexEntries = result.data.results!!.mapIndexed{ index: Int, result: Result ->

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
    }*/



    fun calcDominantColor(drawable: Drawable, onFinish: (Color) -> Unit){
        val bmp = (drawable as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888, true)
        Palette.from(bmp).generate { palette ->
            palette?.dominantSwatch?.rgb?.let { colorValue ->
                onFinish(Color(colorValue))
            }
        }
    }




}