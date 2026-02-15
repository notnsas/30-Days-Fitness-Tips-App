package com.example.a30daysfitnesstips

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.a30daysfitnesstips.data.Tip
import com.example.a30daysfitnesstips.ui.theme._30DaysFitnessTipsTheme
import com.example.a30daysfitnesstips.data.tips
import android.util.Log
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.BorderStroke
//import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.GenericFontFamily
import androidx.compose.ui.text.style.TextOverflow
//import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import coil3.compose.AsyncImage
import kotlin.collections.listOf

//import coil3.compose.AsyncImage

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            _30DaysFitnessTipsTheme(darkTheme = true) {
                FitnessApp()
            }
        }
    }
}

/**
 * Composable that displays an app bar and a list of dogs.
 */
@Composable
fun FitnessApp() {
    val lazyListState = rememberLazyListState()
    Scaffold(
        topBar = {
            TopBarFitness()
        }
    ) { it ->
        InnerScaffold(lazyListState, it)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBarFitness(
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        modifier = modifier
            .clip(RoundedCornerShape(10))
            .dropShadow(
                shape = RoundedCornerShape(20.dp),
                shadow = Shadow(
                    radius = 20.dp,
                    spread = 10.dp,
                    color = Color(0x40000000),
                    offset = DpOffset(x = 4.dp, 4.dp)
                )
            ),
        title = {
            Text(
                text = stringResource(R.string.app_name),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineLarge,
                fontFamily = FontFamily.Serif,
                overflow = TextOverflow.Ellipsis
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
//            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        )
    )
}


@Composable
private fun InnerScaffold(
    lazyListState: LazyListState,
    innerContent: PaddingValues,
) {
    LazyRow(
        state = lazyListState,
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = innerContent,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        itemsIndexed(tips) { index, data ->
            val isVisible = checkVisibility(lazyListState, index)
            val itemSize = calculateSize(index, isVisible)
            val textDay = if (isVisible) "Day " + index.toString() else ""

            Column(
                verticalArrangement = Arrangement.Center,
//                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(modifier = Modifier
                    .size(itemSize[2], 45.dp)
                    .padding(5.dp),
                    colors = CardDefaults.cardColors(MaterialTheme.colorScheme.tertiaryContainer),
//                    border = BorderStroke(2.dp,MaterialTheme.colorScheme.tertiary),
                    shape = RoundedCornerShape(10)
                ) {
                    Text(
                        modifier = Modifier
                            .padding(5.dp)
                            .fillMaxSize(),
                        text = textDay,
                        textAlign = TextAlign.Center,
                        style = LocalTextStyle.current.copy(
                            letterSpacing = TextUnit.Unspecified
                        ),
                        color = MaterialTheme.colorScheme.onTertiaryContainer
                    )
                }
                FitnessItem(
                    tip = data,
                    modifier = Modifier
                        .padding(5.dp)
                        .size(itemSize[0], itemSize[1])
//                        .align(CenterHorizontally)
                )
            }
        }
    }
}

@Composable
private fun checkVisibility(
    lazyListState: LazyListState,
    index: Int
) : Boolean {
    val isVisible by remember {
        derivedStateOf {

            val layoutInfo = lazyListState.layoutInfo
            val visibleItemsInfo = layoutInfo.visibleItemsInfo
            val itemInfo = visibleItemsInfo.firstOrNull { it.index == index}

            itemInfo?.let {

                val delta = it.size/2 //use your custom logic
                val center = lazyListState.layoutInfo.viewportEndOffset / 2
                val childCenter = it.offset + it.size / 2
                val target = childCenter - center
                if (target in -delta..delta) return@derivedStateOf true
            }
            false
        }
    }
    return isVisible
}
@Composable
private fun calculateSize(
    index: Int,
    isVisible: Boolean
) : List<Dp> {
    val fitnessWidthSize by animateDpAsState(
        targetValue = if (isVisible) 350.dp else 300.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioNoBouncy,
            stiffness = Spring.StiffnessMedium
        ),
        label = "Box Size Animation"
    )
    val fitnessHeightSize by animateDpAsState(
        targetValue = if (isVisible) 500.dp else 300.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioNoBouncy,
            stiffness = Spring.StiffnessMedium
        ),
        label = "Box Size Animation"
    )
    val dayWidth by animateDpAsState(
        targetValue = if (isVisible) 100.dp else 25.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioNoBouncy,
            stiffness = Spring.StiffnessMedium
        ),
        label = "Box Size Animation"
    )
    return listOf(fitnessWidthSize, fitnessHeightSize, dayWidth)
}

/**
 * Composable that displays a list item containing a dog icon and their information.
 *
 * @param dog contains the data that populates the list item
 * @param modifier modifiers to set to this composable
 */
@Composable
fun FitnessItem(
    tip: Tip,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    Card(
        modifier = modifier
//            .animateContentSize(
//                animationSpec = spring(
//                    dampingRatio = Spring.DampingRatioHighBouncy,
//                    stiffness = 0.1f
//                )
//            )
//            .size(300.dp, 300.dp)
    ) {
        Column(
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )
            ,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                modifier = Modifier.weight(0.65f),
                model = tip.imageResourceId,
                contentDescription = null,
            )

            Text(
                modifier = Modifier.padding(5.dp)
                    .weight(0.35f),
                text = stringResource(tip.tipText),
                textAlign = TextAlign.Justify,
                style = LocalTextStyle.current.copy(
                    letterSpacing = TextUnit.Unspecified
                )
            )
            }

//            if (expanded) {
////                DogHobby(
////                    dog.hobbies, modifier = Modifier.padding(
////                        start = dimensionResource(R.dimen.padding_medium),
////                        top = dimensionResource(R.dimen.padding_small),
////                        bottom = dimensionResource(R.dimen.padding_medium),
////                        end = dimensionResource(R.dimen.padding_medium)
//                    )
//                )
//            }
//        }
    }
}

//@Composable
//fun WoofTopAppBar(modifier: Modifier = Modifier) {
//    CenterAlignedTopAppBar(
//        title = {
//            Row(
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Image(
//                    modifier = Modifier
//                        .size(dimensionResource(R.dimen.image_size))
//                        .padding(dimensionResource(R.dimen.padding_small)),
//                    painter = painterResource(R.drawable.ic_woof_logo),
//
//                    // Content Description is not needed here - image is decorative, and setting a
//                    // null content description allows accessibility services to skip this element
//                    // during navigation.
//
//                    contentDescription = null
//                )
//                Text(
//                    text = stringResource(R.string.app_name),
//                    style = MaterialTheme.typography.displayLarge
//                )
//            }
//        },
//        modifier = modifier
//    )
//}

@Preview
@Composable
fun WoofPreview() {
    _30DaysFitnessTipsTheme(darkTheme = true) {
//        FitnessItem(tip = tips[0])
        FitnessApp()
    }

}

/**
 * Composable that displays what the UI of the app looks like in dark theme in the design tab.
 */
//@Preview
//@Composable
//fun WoofDarkThemePreview() {
//    WoofTheme(darkTheme = true) {
//        WoofApp()
//    }
//}