package com.example.composelist

import android.content.ContentValues.TAG
import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
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
    val buttonModifier = Modifier
    val numbers = listViewModel.numbers
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
            cells = GridCells.Fixed(numbersAmount),
            modifier = Modifier.weight(0.9F)

        ) {
            items(numbers.size){ index ->
                if (numbers[index]%2==1){
                    Box(
                        buttonModifier
                            .size(integerResource(id = R.integer.NumberBoxSize).dp)
                            .background(Color.Blue)

                    ){
                        Text("${numbers[index]}")
                    }
                }else{
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
        }
        Button(onClick = {listViewModel.addElement()},
            modifier = Modifier.size(integerResource(id = R.integer.ButtonSize).dp).weight(0.1F)) {
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
            modifier = Modifier.size(integerResource(id = R.integer.ButtonSize).dp)) {
        }
        }
}

    
 
