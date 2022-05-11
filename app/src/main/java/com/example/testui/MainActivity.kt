package com.example.testui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.testui.ui.theme.TestUITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestUITheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Counter()
                }
            }
        }
    }
}

@Composable
fun Counter() {

    Log.d("@@@@@@", "Counter3 ran")

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val counter: MutableState<Int> = remember { mutableStateOf(0) }
        Box(modifier = Modifier)
        Text(
            text = counter.value.toString(),
            modifier = Modifier.padding(16.dp)
        )
        Button(
            onClick = {
                counter.value++
                Log.d("@@@@@@", "Button clicked. Counter = ${counter.value}")
            }
        ) {
            Text(
                text = "Increment"
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TestUITheme {
        Counter()
    }
}