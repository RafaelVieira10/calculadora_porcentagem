package com.example.calculadora_gorjeta

import android.icu.number.FormattedNumber
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculadora_gorjeta.ui.theme.Calculadora_gorjetaTheme
import java.text.Format
import java.text.NumberFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Calculadora_gorjetaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppCalculadora()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppCalculadora() {

    var valorEntrada by remember {mutableStateOf("")}
    var porcentagemGorjeta by remember {mutableStateOf("")}
    var gorjeta by remember { mutableStateOf(0.0)}

    gorjeta = CalcularGorjeta(valorEntrada, porcentagemGorjeta)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "CALCULADORA DE PORCENTAGEM",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
        )
        TextField(
            value = valorEntrada,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            label = {
                Text(text = "Valor de entrada")
                    },
            onValueChange = {valorEntrada = it},
            modifier = Modifier
                .padding(top = 30.dp)

        )
        TextField(
            value = porcentagemGorjeta,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            label = {
                Text(text = "Valor da porcentagem")
            },
            onValueChange = {porcentagemGorjeta = it},
            modifier = Modifier
                .padding(top = 30.dp)

        )
        Text(
            text = "Resultado: $porcentagemGorjeta% de $valorEntrada =  ${NumberFormat.getCurrencyInstance().format(gorjeta)}",
            fontSize = 22.sp,
            modifier = Modifier
                .padding(top = 30.dp)
        )

    }
}

fun CalcularGorjeta(valorEntrada : String, porcentagemGorjeta: String) : Double {
    return (valorEntrada.toDoubleOrNull()?: 0.0) * ((porcentagemGorjeta.toDoubleOrNull() ?: 0.0) / 100)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Calculadora_gorjetaTheme {
        AppCalculadora()
    }
}