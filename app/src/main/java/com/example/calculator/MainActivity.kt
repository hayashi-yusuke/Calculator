package com.example.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.calculator.ui.theme.CalculatorTheme

// 可変な状態（State）オブジェクトを生成するために必要
import androidx.compose.runtime.mutableStateOf
// `by` 構文でStateから値を読み取る（getValue）ために必要
import androidx.compose.runtime.getValue
// `by` 構文でStateに値を書き込む（setValue）ために必要
import androidx.compose.runtime.setValue
// 余白（paddingなど）の単位（dp）を使用するために必要
import androidx.compose.ui.unit.dp
// Material 3のボタンUI（Button）を使用するために必要
import androidx.compose.material3.Button

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculatorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // innerPadding を渡すことで、画面上下のステータスバーやバーとの被りを防止します
                    CalculatorScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalculatorPreview() {
    CalculatorTheme {
        CalculatorScreen()
    }
}


fun add(a: Double, b: Double): Double = a + b
fun sub(a: Double, b: Double): Double = a - b
fun mul(a: Double, b: Double): Double = a * b
fun div(a: Double, b: Double): String {
    return if (b == 0.0) {
        "エラー (0で割れません) "
    } else {
        formatResult(a / b)
    }
}
fun formatResult(value: Double): String {
    return if (value % 1.0 == 0.0) {
        value.toLong().toString()
    } else {
        value.toString()
    }
}

@Composable
fun CalculatorScreen(modifier: Modifier = Modifier) {
    var inputA by remember { mutableStateOf("") }
    var inputB by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Column(modifier = modifier.padding(16.dp)) {
        TextField(
            value = inputA,
            onValueChange = { inputA = it },
            label = { Text("1つ目の数字") }
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = inputB,
            onValueChange = { inputB = it },
            label = { Text("2つ目の数字") }
        )
        Spacer(modifier = Modifier.height(16.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = {
                val a = inputA.toDoubleOrNull() ?: 0.0
                val b = inputB.toDoubleOrNull() ?: 0.0
                result = formatResult(add(a,b))
            }) {
                Text("+")
            }

            Button(onClick = {
                val a = inputA.toDoubleOrNull() ?:0.0
                val b = inputB.toDoubleOrNull() ?:0.0
                result = formatResult(sub(a, b))
            }) {
                Text("-")
            }

            Button(onClick = {
                val a = inputA.toDoubleOrNull() ?:0.0
                val b = inputB.toDoubleOrNull() ?:0.0
                result = formatResult(mul(a, b))
            }) {
                Text("*")
            }

            Button(onClick = {
                val a = inputA.toDoubleOrNull() ?: 0.0
                val b = inputB.toDoubleOrNull() ?: 0.0
                result = div(a, b)
            }) {
                Text("÷")
            }
        }

        Spacer(modifier = Modifier .height (16.dp))

        Text("結果: $result")
    }
}