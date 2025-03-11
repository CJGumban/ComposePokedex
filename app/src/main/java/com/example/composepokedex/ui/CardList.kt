package com.example.composepokedex.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.calculateTargetValue
import androidx.compose.animation.core.snap
import androidx.compose.animation.core.tween
import androidx.compose.animation.splineBasedDecay
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.drag
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.input.pointer.util.VelocityTracker
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.composepokedex.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.math.absoluteValue

@Composable
fun PxToDp(px: Float): Dp {
    val density = LocalDensity.current
    return with(density) { px.toDp() }
}

enum class Swipe {
    Left,
    Right,
    Done,
}

data class Card(
    val color: Color,
    val name: String,
    var position: Int
)

@SuppressLint("MutableCollectionMutableState")
@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwipeableCardScreen() {
    val scope = rememberCoroutineScope()
    var swipeCard by remember { mutableStateOf(Swipe.Done) }
    var isAnimating by remember { mutableStateOf(false) }
    var orangeScope = rememberCoroutineScope()
    var list by remember { mutableStateOf(mutableListOf(
        Card(
            color = Color.Black,
            name = "Black",
            position = 1
        ),
        Card(
            color = Color.Blue,
            name = "Blue",
            position = 2
        ),
        Card(
            color = Color.DarkGray,
            name = "DarkGray",
            position = 3
        ),
        Card(
            color = Color.Magenta,
            name = "Magenta",
            position = 4
        ),
        Card(
            color = Color.Green,
            name = "Green",
            position = 5
        ),


    )) }

    Scaffold(
        containerColor = Color.White,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Row(horizontalArrangement = Arrangement.Center) {
                        Text(
                            "Card Design",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = TextStyle.Default.copy(
                                color = Color.White,
                                fontWeight = FontWeight.W700,
                                textAlign = TextAlign.Center,
                                fontSize = 18.sp,
                                lineHeight = 24.sp
                            ),

                            modifier = Modifier
                                .fillMaxWidth()


                        )
                    }
                },

                )
        }
    ) { paddingValues ->

        Box(Modifier.fillMaxSize()) {
            Canvas(modifier = Modifier
                // I posted this to show that size of Canvas doesn't matter
                // If you don't need to use size or center params
                .size(0.dp)
                .offset(x = (0).dp, y = (50).dp)
                .align(alignment = Alignment.TopCenter),
                onDraw = {
                    drawCircle(color = Color.Black, radius = 269.dp.toPx())
                }
            )

        }
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(top = paddingValues.calculateTopPadding())
                .fillMaxSize()
        ) {
            var classicOrange by remember { mutableStateOf(true) }
            var swipeOrange by remember { mutableStateOf(false) }
            var swipeBlack by remember { mutableStateOf(false) }
            Box(
                contentAlignment = Alignment.TopCenter, modifier = Modifier
                    .padding(top = PxToDp(160f))
                    .fillMaxWidth()
            ) {
                LaunchedEffect(list) {
                }
                list.forEachIndexed { index, s ->


                    ClassicOrangeCardV2(
                        card = s,
                        classicOrange,
                        clicked = { click ->
                            classicOrange = click
                        },
                        scope, swipeAnimation = swipeCard,
                        onManualSwipe = {
                            Log.d("cardTesting","list before swipe: ${list} ")
                            var newlist = mutableListOf<Card>()


                            list[index] = s.copy(position = s.position-list.size)
                            /*list.removeAt(list.lastIndex)*/
                            list.forEachIndexed{index, s ->
                                if (s.position==5){
                                    list[index] = s.copy(position = 0)
                                }
                                else{
                                    list[index] = s.copy(position = s.position+1)
                                }
                            }
                            swipeCard = Swipe.Done
                            Log.d("cardTesting","list after swipe: ${list} ")


                        },
                        animationListener = {
                            isAnimating = it
                        },
                        isAnimating = isAnimating, orangeScope = orangeScope,
                        plus = s.position.toFloat(),

                        )
                }
            }


        }


    }

}


@Composable
fun ClassicOrangeCardV2(
    card: Card,
    orange: Boolean = true,
    clicked: (Boolean) -> Unit = {},
    scope: CoroutineScope,
    orangeScope: CoroutineScope,
    swipeAnimation: Swipe = Swipe.Done,
    onManualSwipe: (Boolean) -> Unit = {},
    isAnimating: Boolean = false,
    animationListener: (Boolean) -> Unit = {},
    plus: Float = 1f

) {
//initial
    val scopes = rememberCoroutineScope()
    val scope1 = rememberCoroutineScope()
    val yOffset = remember { Animatable(-30f * plus) }
    val xOffset = remember { Animatable(0f) }
    val rotation = remember { Animatable(0f) }
    val zIndex = remember { Animatable(plus) }
    val alphaAnimation = remember { Animatable(1f) }
    //  var isAnimating by remember { mutableStateOf(false) }
    var isFromManualSwipe by remember { mutableStateOf(false) }
    var pluss by remember { mutableStateOf(plus) }

    LaunchedEffect(plus) {
        Timber.tag("card").d("Orange: " + plus)
        pluss = plus
        scopes.launch {
            launch {
                zIndex.animateTo(plus, snap())
                xOffset.animateTo(0f, snap())
            }
            launch {
                yOffset.animateTo(-30f * plus)
            }
            rotation.animateTo(
                0f, snap()
            )
        }
    }

    LaunchedEffect(swipeAnimation) {
        if (swipeAnimation == Swipe.Right) {

            scope.launch {
                launch {
                    xOffset.animateTo(
                        450f, tween(150)
                    )
                }
                launch {
                    yOffset.animateTo(
                        90f, tween(150)
                    )
                }
                launch {
                    rotation.animateTo(
                        27f, tween(150)
                    )
                    onManualSwipe(false)

                }

            }
        }

    }

    Box(
        modifier = Modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                enabled = false
            ) {


            }
            .graphicsLayer {
                translationX = xOffset.value
                translationY = yOffset.value
            }
            .rotate(rotation.value)
            .zIndex(zIndex.value)

            .background(card.color)

            .pointerInput(Unit) {
                // Used to calculate fling decay.
                val decay = splineBasedDecay<Float>(this)
                // Use suspend functions for touch events and the Animatable.
                scope1.launch {
                    while (swipeAnimation == Swipe.Done) {
                        val velocityTracker = VelocityTracker()
                        // Stop any ongoing animation.
                        xOffset.stop()
                        awaitPointerEventScope {
                            // Detect a touch down event.
                            val pointerId = awaitFirstDown().id

                            drag(pointerId) { change ->
                                // Update the animation value with touch events.
                                launch {
                                    xOffset.snapTo(
                                        xOffset.value + change.positionChange().x
                                    )
                                }
                                launch {
                                    yOffset.snapTo(
                                        yOffset.value + change.positionChange().y
                                    )
                                }
                                velocityTracker.addPosition(
                                    change.uptimeMillis,
                                    change.position
                                )
                            }
                        }
                        // No longer receiving touch events. Prepare the animation.
                        val velocity = velocityTracker.calculateVelocity().x
                        val targetOffsetX = decay.calculateTargetValue(
                            xOffset.value,
                            velocity
                        )
                        // The animation stops when it reaches the bounds.
                        /*xOffset.updateBounds(
                                lowerBound = (-150-size.width).toFloat(),
                                upperBound = (150+size.width).toFloat()
                            )*/
                        launch {
                            if (targetOffsetX.absoluteValue <= size.width) {
                                // Not enough velocity; Slide back.
                                launch {
                                    xOffset.animateTo(
                                        targetValue = 0f,
                                        initialVelocity = velocity
                                    )
                                }
                                launch {

                                    yOffset.animateTo(
                                        targetValue = -30f * pluss,
                                        initialVelocity = velocity
                                    )
                                }
                            } else {
                                // The element was swiped away.
                                launch {
                                    isFromManualSwipe = true

                                    xOffset.animateDecay(velocity, decay)
                                    //onDismissed()
                                }
                                scopes
                                    .launch {
                                        //        isAnimating = true
                                        animationListener(true)

                                        /*    zIndex.animateTo(
                                            plus, snap()
                                        )

                                        alphaAnimation.animateTo(1f, tween(150))
*/
                                        onManualSwipe(false)

                                        /* xOffset.animateTo(0f, snap())
                                        rotation.animateTo(0f, snap())
                                        yOffset.animateTo(
                                            0f, snap()
                                        )*/
                                        /*  onManualSwipe(false)

                                        alphaAnimation.animateTo(1f, tween(100))
                                        isFromManualSwipe = false*/
                                    }
                                    .invokeOnCompletion {
                                        animationListener(false)
                                    }
                            }
                        }
                    }
                }


            }
    ) {
        Text(card.name.toString(),
            Modifier
                .align(Alignment.Center)
                .zIndex(2f))
        Image(
            painter = painterResource(id = R.drawable.vector),
            contentDescription = "classic orange",
            modifier = Modifier
                .border(2.dp, color = Color.Black)
                .height(353.dp)
                .width(224f.dp)
                .zIndex(1f)
                .alpha(alphaAnimation.value),
            contentScale = ContentScale.FillHeight

        )
    }


}