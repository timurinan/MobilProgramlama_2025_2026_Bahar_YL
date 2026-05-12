package org.example.project

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun Chip(içerik:String){

    Button( onClick = {}, colors = ButtonDefaults.buttonColors(containerColor = buton_arkaplan, contentColor = buton_yazi))
    {
        Text(içerik)
    }
}