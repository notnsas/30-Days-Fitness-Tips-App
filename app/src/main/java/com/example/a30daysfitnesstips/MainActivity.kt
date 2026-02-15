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
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.unit.Dp
import coil3.compose.AsyncImage

//import coil3.compose.AsyncImage

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            _30DaysFitnessTipsTheme(darkTheme = false) {
//        FitnessItem(tip = tips[0])
                FitnessApp()
            }
        }
    }
}

/**
 * Composable that displays an app bar and a list of dogs.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FitnessApp() {
    var modifierItem = remember { Modifier.padding(5.dp).size(400.dp, 500.dp) }
//    var middleIndex by remember { mutableStateOf(0) }
    val lazyListState = rememberLazyListState()
//    var widthSize: Dp = remember { 400.dp }
//    var heightSize: Dp = remember { 500.dp }
//    LaunchedEffect(lazyListState) {
//        snapshotFlow { lazyListState.layoutInfo.visibleItemsInfo }
//            .collect { item ->
////                            item.forEachIndexed { index, it -> Log.d("TAG LAYOUTINFO", "$index" + ". " + "index:" + it.index.toString() + "| offset:" + it.offset.toString()) }
////                            Log.d("TAG LAYOUTINFO", test)
//                item.forEachIndexed { index, it ->
//                    if (it.offset.toInt() > 0 && it.offset.toInt() < 300) {
//                        Log.d(
//                            "TAG LAYOUTINFO",
//                            "$index" + ". " + "index:" + it.index.toString() + "| offset:" + it.offset.toString()
//                        )
//                        middleIndex = it.index.toInt()
//                    } else Log.d(
//                        "FAIL TAG LAYOUTINFO",
//                        "$index" + ". " + "index:" + it.index.toString() + "| offset:" + it.offset.toString()
//                    )
//                }
//            }
//    }
//    val fullyVisibleIndices: List<Int> by remember {
//        derivedStateOf {
//            val layoutInfo = lazyListState.layoutInfo
//            val visibleItemsInfo = layoutInfo.visibleItemsInfo
//            if (visibleItemsInfo.isEmpty()) {
//                emptyList()
//            } else {
//                val fullyVisibleItemsInfo = visibleItemsInfo.toMutableList()
//
//                val lastItem = fullyVisibleItemsInfo.last()
//
//                val viewportHeight = layoutInfo.viewportEndOffset + layoutInfo.viewportStartOffset
//
//                if (lastItem.offset + lastItem.size > viewportHeight) {
//                    fullyVisibleItemsInfo.removeLast()
//                }
//
//                val firstItemIfLeft = fullyVisibleItemsInfo.firstOrNull()
//                if (firstItemIfLeft != null && firstItemIfLeft.offset < layoutInfo.viewportStartOffset) {
//                    fullyVisibleItemsInfo.removeFirst()
//                }
//
//                fullyVisibleItemsInfo.map { it.index }
//            }
//        }
//    }
//    Log.d(
//        "Fully visible",
//        fullyVisibleIndices.toString()
//    )


//    val middleIndex by remember {
//        derivedStateOf {
//            val layoutInfo = lazyListState.layoutInfo
//            val visibleItems = layoutInfo.visibleItemsInfo
////            Log.d(
////                "TAG LAYOUTINFO",
////                layoutInfo.viewportEndOffset.toString()
////            )
//            visibleItems.forEachIndexed { index, it ->
//                if (it.offset.toInt() > 0 && it.offset.toInt() < 300) {
//                    Log.d(
//                        "TAG LAYOUTINFO",
//                        "$index" + ". " + "index:" + it.index.toString() + "| offset:" + it.offset.toString()
//                    )
//                    return@derivedStateOf it.index.toInt()
//                } else Log.d(
//                    "FAIL TAG LAYOUTINFO",
//                    "$index" + ". " + "index:" + it.index.toString() + "| offset:" + it.offset.toString()
//                )
//            }
////            if (visibleItems.isEmpty()) return@derivedStateOf 0
//
////            val viewportCenter = layoutInfo.viewportEndOffset / 2
////
////            visibleItems.minByOrNull { item ->
////                val itemCenter = item.offset + item.size / 2
////                kotlin.math.abs(itemCenter - viewportCenter)
////            }?.index ?: 0
//        }
//    }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Test123")
                }
            )
//            WoofTopAppBar()
        }
    ) { it ->
        LazyRow(
            state = lazyListState,
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = it,
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            itemsIndexed(tips) { index, data ->

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

                val widthSize by animateDpAsState(
                    targetValue = if (isVisible) 350.dp else 300.dp,
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessMedium
                    ),
                    label = "Box Size Animation"
                )
                val heightSize by animateDpAsState(
                    targetValue = if (isVisible) 500.dp else 300.dp,
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessMedium
                    ),
                    label = "Box Size Animation"
                )
//                widthSize = if (middleIndex == (index)) 400.dp else 300.dp
//                heightSize = if (middleIndex == (index)) 500.dp else 300.dp
//                if (middleIndex == (index))
                FitnessItem(
                    tip = data,
                    modifier = Modifier.padding(5.dp).size(widthSize, heightSize)
                )
//                else  FitnessItem(
//                    tip = data,
//                    modifier = Modifier.padding(5.dp).size(widthSize, heightSize)
//                )

//                Log.d(
//                    "TAG LAYOUTINFO Middle index",
//                    "middleindex:" + middleIndex.toString() + "| index: $index"
//                )

            }
        }
    }
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
//            Image(
//                modifier = Modifier.weight(0.65f),
//                painter = painterResource(tip.imageResourceId),
//                contentDescription = null
//            )
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
    _30DaysFitnessTipsTheme(darkTheme = false) {
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