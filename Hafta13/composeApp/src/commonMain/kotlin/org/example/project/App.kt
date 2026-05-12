package org.example.project

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource

import hafta13.composeapp.generated.resources.Res
import hafta13.composeapp.generated.resources.compose_multiplatform
import hafta13.composeapp.generated.resources.pizza_png

@Composable
@Preview
fun App() {
   MaterialTheme {
        Column(modifier = Modifier.fillMaxSize().background(color = Color.Black),
            verticalArrangement = Arrangement.SpaceEvenly, horizontalAlignment = Alignment.CenterHorizontally)
        {
            Text("Pizza", fontSize = 50.sp, fontFamily = FontFamily.Monospace, fontStyle = FontStyle.Italic, color = buton_arkaplan)
            Image(painter = painterResource(Res.drawable.pizza_png), contentDescription = "Lezzetli Pizza")
            Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly)
            {
                Chip("Peynir")
                Chip("Sosis")
                Chip("Zeytin")
                Chip("Biber")

            }
            Text("20 min", color = text_yazi, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text("Teslimat", color = buton_arkaplan, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text("Harika pizza seçenekleri seni bekliyor, gel alda özel kampanyalar", color = text_yazi, fontSize = 20.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly)
            {
                Text(" 20 TL", color = buton_arkaplan, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Button(onClick = {}, colors = ButtonDefaults.buttonColors(containerColor = buton_arkaplan, contentColor = buton_yazi))
                {
                    Text("Satın Al")
                }
            }
        }
   }
}