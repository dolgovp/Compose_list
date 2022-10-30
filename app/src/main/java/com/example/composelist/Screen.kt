package com.example.composelist

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.composelist.ui.theme.*

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ItemsGrid(listViewModel: HomeViewModel) {
    val numbers = listViewModel.numbers
    val configuration = LocalConfiguration.current
    var gridWeight = GridPortraitWeight
    var buttonWeight = BtnPortraitWeight
    var numbersAmount = portrait
    if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE ){
        gridWeight = GridLandscapeWeight
        buttonWeight = BtnLandscapeWeight
        numbersAmount = landscape
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        LazyVerticalGrid(
            contentPadding = PaddingValues(VertPadding.dp, HorPadding.dp),
            cells = GridCells.Fixed(numbersAmount),
            modifier = Modifier.weight(gridWeight),
        ) {
            items(numbers.size){ index ->
                if (numbers[index]%2==1) MakeBox(BlueBoxClr, numbers[index])
                else MakeBox(RedBoxClr, numbers[index])
            }
        }
        Button(
            onClick = { listViewModel.addElement() },
            modifier = Modifier
                .padding(ButtonPadding.dp)
                .widthIn(ButtonSize.dp)
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
@Composable
fun MakeBox(color: Color, number: Int){
    val boxModifier = Modifier
        .padding(BoxPadding.dp)
        .size(NumberBoxSize.dp)
    Box(
        boxModifier
            .background(color),
        contentAlignment = Alignment.Center

    ){
        Text("$number",textAlign = TextAlign.Center)
    }
}


    
 
