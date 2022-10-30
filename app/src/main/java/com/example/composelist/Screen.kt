package com.example.composelist

import android.content.ContentValues.TAG
import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

/*@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun ShowList(){
    val numbers = listOf<Int>(1,2,3,4,5,6,7,8,9)
    ItemsGrid(numbers)
}*/
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ItemsGrid(/* numbers: List<Int>*/listViewModel: HomeViewModel) {
    val boxModifier = Modifier
        .padding(5.dp)
        .size(integerResource(id = R.integer.NumberBoxSize).dp)

    val numbers = listViewModel.numbers
    val configuration = LocalConfiguration.current
    var gridWeight = (stringResource(id = R.string.GridPortraitWeight)).toFloat()
    var buttonWeight = (stringResource(id = R.string.BtnPortraitWeight)).toFloat()
    var numbersAmount = integerResource(id = R.integer.portrait)
    if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE ){
        gridWeight = (stringResource(id = R.string.GridLandscapeWeight)).toFloat()
        buttonWeight = (stringResource(id = R.string.BtnLandscapeWeight)).toFloat()
        numbersAmount = integerResource(id = R.integer.landscape)
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        LazyVerticalGrid(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            cells = GridCells.Fixed(numbersAmount),
            modifier = Modifier.weight(gridWeight),
        ) {
            items(numbers.size){ index ->
                if (numbers[index]%2==1){
                    // Вынести вызов Box в функцию
                    Box(
                        boxModifier
                            .background(Color.Blue),
                        contentAlignment = Alignment.Center
                    ){
                        Text("${numbers[index]}",textAlign = TextAlign.Center)
                    }
                }else{
                    Box(
                        boxModifier
                            .background(Color.Red),
                        contentAlignment = Alignment.Center

                    ){
                        Text("${numbers[index]}",textAlign = TextAlign.Center)
                    }
                }
            }
        }
        Button(
            onClick = { listViewModel.addElement() },
            modifier = Modifier
                .padding(7.dp)
                .widthIn(min = integerResource(id = R.integer.ButtonSize).dp)
                .weight(buttonWeight),
        )
        {
            Text(
                text = "Add box",
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
    }
}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Screen_v2(){
    val buttonModifier = Modifier
    val numbers = remember{
        mutableStateListOf(emptyList<Int>())
    }
    val configuration = LocalConfiguration.current
    val numbersAmount = when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            integerResource(id = R.integer.landscape)
        }
        else -> {
            integerResource(id = R.integer.portrait)
        }
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        LazyVerticalGrid(
            cells = GridCells.Fixed(numbersAmount)
        ) {
            items(numbers.size){ index ->
                    Box(
                        buttonModifier
                            .size(integerResource(id = R.integer.NumberBoxSize).dp)
                            .background(Color.Red)
                    ){
                        Text("${numbers[index]}")
                        Log.d(TAG, "Вывод ${numbers[index]}")
                    }
                }
            }
        Button(onClick = {numbers.add(listOf(numbers.lastIndex + 1))},
            modifier = Modifier
                .padding(5.dp)
                .size(integerResource(id = R.integer.ButtonSize).dp),)
        {
            Text("Add box")
        }
        }
}

    
 
