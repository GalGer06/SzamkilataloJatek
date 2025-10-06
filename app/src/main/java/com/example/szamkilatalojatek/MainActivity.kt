package com.example.szamkilatalojatek

import android.R.attr.enabled
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.szamkilatalojatek.ui.theme.SzamkilataloJatekTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SzamkilataloJatekTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        modifier = Modifier.padding(innerPadding)
                            .fillMaxSize()
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    var randomSzam by remember { mutableIntStateOf((1..100).random()) }
    var jatekosTipp by remember { mutableStateOf("") }
    var elet by remember { mutableIntStateOf(10) }
    var uzenet by remember { mutableStateOf("Tippelj egy számra 1 és 100 között!") }
    var jatekVeg by remember { mutableStateOf(false) }

    Column (
        modifier = modifier.padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        Text("Életek száma: $elet")

        Spacer(Modifier.height(16.dp))

        TextField(
            value = jatekosTipp,
            onValueChange = {jatekosTipp = it},
            label = {Text("Írj egy tippet 1-től, 100-ig")},
            enabled = !jatekVeg
        )

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = {
                val jatekos = jatekosTipp.toIntOrNull()

                if (jatekos == null){
                    uzenet = "Egy érvényes számot adj meg!"
                    return@Button
                }

                if (jatekos < randomSzam){
                    uzenet = "Nagyobb számra gondoltam!"
                    elet --
                } else if (jatekos > randomSzam){
                    uzenet = "Kisebb számra gondoltam!"
                    elet --
                } else {
                    uzenet = "Eltaláltad, nyertél!"
                    jatekVeg = true
                }

                if (elet <= 0 && jatekos != randomSzam){
                    uzenet="Vesztettél, mit ki kellett volna találnod: $randomSzam"
                    jatekVeg = true
                }
            },
            enabled = !jatekVeg
        ) {
            Text("Tipp")
        }
        Spacer(Modifier.height(16.dp))

        Text(text = uzenet)

        Spacer(Modifier.height(20.dp))
        Button(

            onClick = {
                randomSzam = (1..100).random()
                jatekosTipp=""
                elet=10
                jatekVeg=false
            }
        ) {
            Text("Újrakezdés")
        }
    }
}
