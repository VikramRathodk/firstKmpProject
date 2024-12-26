package org.devvikram.firstpmpproject

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(
    BatteryManager: BatteryManager,
    networkInfo: NetworkInfo
) {
    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colors.background
        ) {


            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ){
                    Text("The current Battery Level is : ${BatteryManager.getBatteryLevel()}")

                    if(networkInfo.isNetworkAvailable()){
                        Text("Network is Available")
                    }else{
                        Text("Network is not Available")
                    }
                }


            }



//            var shouldShowOnboarding by remember { mutableStateOf(true) }
//            if (shouldShowOnboarding) {
//                OnBoardingScreen(
//                    onContinueClicked = {
//                        shouldShowOnboarding = false
//                    }
//                )
//            } else {
//                Greetings()
//            }
        }
    }
}

@Composable
fun Greetings(namesList: List<String> = List(1000){"$it"}) {

    LazyColumn {
        items(items = namesList) { name ->
            GreetingCard(name = name)
        }
    }
}

@Composable
fun GreetingCard(name: String) {
    val expanded = remember { mutableStateOf(false) }
    val extraPadding by animateDpAsState(
        if (expanded.value) 48.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ), label = "To Expand From bottom"
    )
    Row(
        modifier = Modifier.padding(24.dp),
    ) {
        Column(modifier = Modifier
            .weight(1f)
            .padding(bottom = extraPadding.coerceAtLeast(0.dp))) {
            Text(text = "Hello", textAlign = TextAlign.Center)
            Text(text = name, textAlign = TextAlign.Center)
        }
        OutlinedButton(
            onClick = {
                expanded.value = !expanded.value
            }
        ) {
            Text(if (expanded.value) "Show less" else "Show more")
        }

    }

}

@Composable
fun OnBoardingScreen(
    modifier: Modifier = Modifier,
    onContinueClicked: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Welcome to the Basics Jetpack Compose!")
        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = onContinueClicked
        ) {
            Text(text = "Continue")
        }


    }
}

