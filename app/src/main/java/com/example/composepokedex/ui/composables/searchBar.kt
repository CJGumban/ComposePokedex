package com.example.composepokedex.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composepokedex.R
import com.example.composepokedex.ui.theme.Body3
import com.example.composepokedex.ui.theme.ComposePokedexTheme
import com.example.composepokedex.ui.theme.Headline
import com.example.composepokedex.ui.theme.Primary


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokeSearchBar(
    value: String = "Choose your pokemon",
    onValueChange: (String) -> Unit = {},

) {
    Column(Modifier
        .background(Primary)
        .padding(bottom = 24.dp,top = 12.dp, start = 12.dp, end = 12.dp)
    ) {
        Row(
            Modifier
                .padding(2.dp)
                ,
            verticalAlignment = Alignment.CenterVertically

        ) {
            Icon(
                painter = painterResource(R.drawable.ic_pokeball_plain),
                tint = Color.White,
                contentDescription = "ic_pokeball",
                modifier = Modifier.size(24.dp)
            )
            Spacer(Modifier.width(16.dp))
            Text(
                text = stringResource(R.string.Pokedex),
                style = Headline.copy(color = Color.White)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        BasicTextField(value = value,
            modifier = Modifier
                .fillMaxWidth()
                .shadow(8.dp,CircleShape,false)
                .clip(RoundedCornerShape(30.dp))
                //.padding(horizontal = 16.dp, vertical = 14.dp)
            , onValueChange = { onValueChange( it) },
            textStyle = Body3
            ,singleLine = true
            ,decorationBox = { innerTextField ->
                // Because the decorationBox is used, the whole Row gets the same behaviour as the
                // internal input field would have otherwise. For example, there is no need to add a
                // Modifier. clickable to the Row anymore to bring the text field into focus when user
                // taps on a larger text field area which includes paddings and the icon areas.

                if (value.isEmpty()) {
                    Row(
                        Modifier
                            .padding(2.dp)
                            .shadow(3.dp,CircleShape,false)
                            .background(Color.White)
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically

                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_search),
                            tint = Primary,
                            contentDescription = "ic_search",
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(Modifier.width(8.dp))
                        Text(
                            text = stringResource(R.string.search_hint),
                            style = Body3
                        )
                    }
                } else {
                    Row(
                        Modifier.padding(2.dp)
                            .shadow(3.dp,CircleShape)
                            .background(Color.White)
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_search),
                            tint = Primary,
                            contentDescription = "ic_search",
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(Modifier.width(8.dp))
                        innerTextField()
                    }
                }
            })
    }

    /*    BasicTextField(
            value = value,
            onValueChange = {newValue->
                value = newValue
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(horizontal = 16.dp, vertical = 14.dp)
                .clip(shape = CircleShape.copy(CornerSize(30)))
                .border(
                    width = 1.dp,
                    Brush.horizontalGradient(listOf(Grey200)),
                    shape = CircleShape.copy(CornerSize(30.dp))
                )
        )*/
}

@Composable
@Preview(showBackground = true)
fun SearchBarPreview() {
    ComposePokedexTheme {
        PokeSearchBar()
    }
}