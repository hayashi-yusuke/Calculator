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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculatorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // innerPadding を渡すことで、画面上下のステータスバーやバーとの被りを防止します
                    CulculatorScreen(
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
        CulculatorScreen()
    }
}

fun add(a: Int, b: Int): Int {
    return a + b
}

@Composable
fun CulculatorScreen(modifier: Modifier = Modifier) {
    var inputA by remember { mutableStateOf("") }
    var inputB by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Column(modifier = modifier.padding(16.dp)) {
        TextField(
            value = inputA,
            onValueChange = { inputA = it },
            label = { Text("1つ目の数字") }
        )

        TextField(
            value = inputB,
            onValueChange = { inputB = it },
            label = { Text("2つ目の数字") }
        )

        Button(onClick = {
            // 空文字のままボタンを押してもアプリがクラッシュしないよう safely に数値変換（toIntOrNull）
            val a = inputA.toIntOrNull() ?: 0
            val b = inputB.toIntOrNull() ?: 0
            result = add(a, b).toString()
        }) {
            Text("足し算")
        }
        Text("結果: $result")
    }
}