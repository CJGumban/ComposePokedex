package com.example.composepokedex.ui.pokemonlist

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.composepokedex.R
import com.example.composepokedex.data.model.PokedexListEntry
import com.example.composepokedex.data.model.response.Result
import com.example.composepokedex.ui.composables.LoadingScreen
import com.example.composepokedex.ui.composables.PokeSearchBar
import com.example.composepokedex.ui.theme.Background
import com.example.composepokedex.ui.theme.Body3
import com.example.composepokedex.ui.theme.Caption
import com.example.composepokedex.ui.theme.ComposePokedexTheme
import com.example.composepokedex.ui.theme.Primary
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import timber.log.Timber
import java.text.DecimalFormat

@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class,
    ExperimentalLayoutApi::class
)
@Composable
fun PokemonListScreens(
    pokemons: Flow<PagingData<Result>> = MutableStateFlow(PagingData.empty()),
    onBackClick: () -> Unit = {},
    onItemClick: () -> Unit = {},
    onSearchBarTextChange: (String) -> Unit = {}
) {
    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    var isImeVisible = WindowInsets.isImeVisible
    var scrollableState = rememberScrollState()
    val scope = rememberCoroutineScope()
    val focusRequester = remember { FocusRequester() }
    val lazyListState: LazyListState = rememberLazyListState()
    var windowInsets = WindowInsets


    LaunchedEffect(isImeVisible) {
        if (isImeVisible) {
            bringIntoViewRequester.bringIntoView()
            lazyListState.animateScrollToItem(15)
            scrollableState.scrollTo(scrollableState.maxValue)

        }
    }

    var windowInsetsBot = WindowInsets.ime.asPaddingValues().calculateBottomPadding()

    var imePadding by remember { mutableStateOf(windowInsetsBot) }
    LaunchedEffect(windowInsetsBot) {
        Timber.d("windowInsetBot: ${windowInsetsBot}")
        imePadding = windowInsetsBot

    }

    Scaffold(

        topBar = {
            Row(
                Modifier
                    .height(30.dp)
                    .background(Color.Black)

                    .fillMaxWidth()
            ) {
                PokeSearchBar()
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(top = paddingValues.calculateTopPadding())
                .fillMaxSize()
                .fillMaxHeight()
                .imePadding()
                .verticalScroll(scrollableState)
        )
        {


            // val item = pagingItems[index]
            Text(
                text = "Delivery Address",
                style = TextStyle(
                    lineHeight = 16.sp,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W700,

                    color = Color.Gray,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .wrapContentHeight(Alignment.CenterVertically, unbounded = true)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = true,
                        role = Role.RadioButton,
                        onClick = {
                        },
                    )
                    .padding(horizontal = 16.dp),
            ) {

            }


            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxHeight()
                    .fillMaxHeight(),
            ) {

                Text(
                    text = "Optional",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 14.sp,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(170.dp)
                )

                BasicTextField(
                    value = "asdl;fjasfal;",
                    onValueChange = {
                    },
                    minLines = 3,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {

                        }
                        //.fillMaxHeight(0.5f)
                        .background(color = Color.White, shape = RoundedCornerShape(8.dp))
                        .border(
                            width = 1.dp,
                            color = Color.Black,
                            shape = RoundedCornerShape(8.dp)
                        ),
                    decorationBox = { innerTextField ->
                        Box(
                            modifier = Modifier.padding(10.dp)
                        ) {

                            innerTextField()
                        }
                    },
                )
                BasicTextField(
                    value = "asdl;fjasfal;",
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    onValueChange = {
                    },
                    minLines = 3,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {

                        }
                        //.fillMaxHeight(0.5f)
                        .background(color = Color.White, shape = RoundedCornerShape(8.dp))
                        .border(
                            width = 1.dp,
                            color = Color.Black,
                            shape = RoundedCornerShape(8.dp)
                        ),
                    decorationBox = { innerTextField ->
                        Box(
                            modifier = Modifier.padding(10.dp)
                        ) {

                            innerTextField()
                        }
                    },
                )


                Text(
                    text = "0/50",
                    modifier = Modifier.fillMaxWidth(),
                    style = TextStyle.Default.copy(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W400,
                        lineHeight = 16.sp,
                        textAlign = TextAlign.End
                    )
                )

            }
            Spacer(Modifier.weight(1f))
            Image(
                painterResource(R.drawable.bulbasaur_1),
                "",
                modifier = Modifier
                    .size(100.dp)
            )

        }


    }
}


@OptIn(ExperimentalFoundationApi::class, ExperimentalLayoutApi::class)
@Composable
fun PokemonListScreen(
    pokemons: Flow<PagingData<PokedexListEntry>> = MutableStateFlow(
        PagingData.from(
            pokemonListPreviewData()
        )
    ),
    onBackClick: () -> Unit = {},
    onItemClick: () -> Unit = {},
    onSearchBarTextChange: (String) -> Unit = {}
) {
    var isDialogVisible by remember { mutableStateOf(true) }

    var searchBarValue by remember { mutableStateOf("") }


    Scaffold(
        topBar = {
            PokeSearchBar(
                searchBarValue,
                onValueChange = { onSearchBarTextChange(it)
                searchBarValue = it}
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier.background(Primary).padding(top = paddingValues.calculateTopPadding())) {
            PokemonList(pokemons, onClick = {isDialogVisible = true})
        }
    }


}

@SuppressLint("SuspiciousIndentation")
@Composable
fun PokemonList(
    pokemons: Flow<PagingData<PokedexListEntry>> = MutableStateFlow(
        PagingData.from(
            pokemonListPreviewData()
        )
    ),
    onClick:()-> Unit = {}
) {

    val lazyPagingItems = pokemons.collectAsLazyPagingItems()
    if (lazyPagingItems.loadState.refresh == LoadState.Loading) {
        LoadingScreen()
    }
    LazyVerticalGrid(

        contentPadding = PaddingValues(16.dp),
        modifier = Modifier


            .padding(4.dp)
            .fillMaxSize()
            .background(Color.White,shape = RoundedCornerShape(8.dp))
            ,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        columns = GridCells.Fixed(3)

    ) {


        if (lazyPagingItems.loadState.refresh == LoadState.Loading) {
            item {
                CircularProgressIndicator(
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        items(count = lazyPagingItems.itemCount) { index ->
            val item = lazyPagingItems[index]
            if (item != null) {
                Timber.d("pokedexEntry(${item})")
                PokedexEntry(item)
            }
        }
        if (lazyPagingItems.loadState.append == LoadState.Loading) {
            item {
                CircularProgressIndicator(
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }


    }
}

@Preview(device = "id:pixel_8_pro", showBackground = true, showSystemUi = true)
@Composable
fun PokedexEntry(
    entry: PokedexListEntry = PokedexListEntry(
        "Bulbasaur",
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/132.png",
        200,
    ),
) {
    val defaultDominantColor = MaterialTheme.colorScheme.surface
    var dominantColor by remember {
        mutableStateOf(defaultDominantColor)
    }
    val context = LocalContext.current


    Box(
        modifier = Modifier

            //When using shadow it should be above the background() to avoid shrinking
            .shadow(8.dp, shape = RoundedCornerShape(8.dp), clip = false)
            .background(Color.White, shape = RoundedCornerShape(8.dp))


    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .heightIn(108.dp)
                .drawBehind {
                    drawRoundRect(
                        color = Background,
                        topLeft = Offset.Zero.copy(y = size.height - (size.height * .45f)),
                        cornerRadius = CornerRadius(8.dp.toPx()),
                        size = size.copy(height = size.height * .45f)
                    )
                }
                .padding(horizontal = 8.dp, vertical = 4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                style = Caption.copy(textAlign = TextAlign.Center),
                modifier = Modifier
                    .align(Alignment.End),
                text = entry.number.toPokeDigit(),
            )
            val imageRequest = ImageRequest
                .Builder(LocalContext.current)
                .data(entry.imageUrl)

                .placeholder(R.drawable.img_loading_placeholder)
                .crossfade(true)
                .build()
            SubcomposeAsyncImage(
                onSuccess = {
                },
                onLoading = {
                },
                model = imageRequest,
                contentDescription = "",
                modifier = Modifier
                    .zIndex(5f)
                    .size(68.dp)
                    .padding(4.dp)
                    .align(Alignment.CenterHorizontally),
                contentScale = ContentScale.FillBounds,
            )
            Text(
                style = Body3,
                modifier = Modifier
                    .zIndex(5f)
                    .align(Alignment.CenterHorizontally),
                text = entry.pokemonName,

                )
        }

    }
}


@Composable
internal fun Dp.dpToPx(): Float {
    return this.value * LocalDensity.current.density
}

private fun Int.toPokeDigit(): String {
    val stringPattern = "000"
    val decimalFormat = DecimalFormat(stringPattern)
    return "#${decimalFormat.format(this)}"
}


fun pokemonListPreviewData(): List<PokedexListEntry> {
    return listOf(
        PokedexListEntry(
            "Bulbasaur",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/132.png",
            200,

        ),PokedexListEntry(
            "Bulbasaur",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/132.png",
            200,

        ),PokedexListEntry(
            "Bulbasaur",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/132.png",
            200,

        ),PokedexListEntry(
            "Bulbasaur",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/132.png",
            200,

        ),PokedexListEntry(
            "Bulbasaur",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/132.png",
            200,

        ),PokedexListEntry(
            "Bulbasaur",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/132.png",
            200,

        ),PokedexListEntry(
            "Bulbasaur",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/132.png",
            200,

        ),PokedexListEntry(
            "Bulbasaur",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/132.png",
            200,

        ),PokedexListEntry(
            "Bulbasaur",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/132.png",
            200,

        ),PokedexListEntry(
            "Bulbasaur",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/132.png",
            200,

        ),PokedexListEntry(
            "Bulbasaur",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/132.png",
            200,

        ),PokedexListEntry(
            "Bulbasaur",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/132.png",
            200,

        ),PokedexListEntry(
            "Bulbasaur",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/132.png",
            200,

        ),PokedexListEntry(
            "Bulbasaur",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/132.png",
            200,

        ),PokedexListEntry(
            "Bulbasaur",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/132.png",
            200,

        ),
    )
}

@Composable
@Preview
fun PokemonListScreenPreview() {
    ComposePokedexTheme {
        PokemonListScreen()
    }
}





