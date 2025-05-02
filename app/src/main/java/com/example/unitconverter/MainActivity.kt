package com.example.unitconverter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import com.google.android.gms.fitness.data.Value
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConverterTheme {
               Surface(modifier = Modifier.fillMaxSize(),
                   color =  MaterialTheme.colorScheme.background){
                    UnitConverter()
               }
            }
        }
    }
}

@Composable
fun UnitConverter(){

    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Meters") }
    var outputUnit by remember { mutableStateOf("Meters") }
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    var conversionFactor = remember { mutableDoubleStateOf(1.00) }
    var oconversionFactor = remember { mutableDoubleStateOf(1.00) }

    val customTextStyle = TextStyle(
        fontFamily = FontFamily.Cursive,
        fontSize =  45.sp,
        color = Color(0xFFe64a19)
    )


    fun convertUnits(){
        //elvis operator it is a if statement agar null hua to
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * conversionFactor.value * 100.0/ oconversionFactor.value).roundToInt() / 100.0
        outputValue = result.toString()
    }



    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){

        Text("Unit Converter",
            modifier = Modifier.padding(30.dp), style = customTextStyle
            )

        OutlinedTextField(value =  inputValue,
            onValueChange = {inputValue=it},
            label = {Text("Enter value")})

        Spacer(modifier = Modifier.height(16.dp))

        Row(Modifier.padding(16.dp)){
//            val context = LocalContext.current
//            Button(onClick = {Toast.makeText(context,
//                "You clicked me",
//                Toast.LENGTH_LONG).show()} ) {
//                Text("Click")
//            }

            //Boxes are used for the drop down menu.
            Box {
                Button(onClick = {iExpanded = true}  ) {
//                    Text("Select")
                    Text(inputUnit)
                    Icon(Icons.Default.KeyboardArrowDown, contentDescription = "")
                }

                DropdownMenu(expanded = iExpanded , onDismissRequest = {iExpanded = false}) {
                    DropdownMenuItem(text = {Text("Centimeters")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "Centimetres"
                            conversionFactor.value = 0.01
                            convertUnits()
                            }
                    )
                    DropdownMenuItem(text = {Text("Meters")}, onClick = {
                        iExpanded = false
                        inputUnit = "Meters"
                        conversionFactor.value = 1.0
                        convertUnits()
                    })
                    DropdownMenuItem(text = {Text("Feet")}, onClick = {
                        iExpanded = false
                        inputUnit = "Feet"
                        conversionFactor.value = 0.3048
                        convertUnits()
                    } )
                    DropdownMenuItem(text = {Text("Millimeters")}, onClick = {
                        iExpanded = false
                        inputUnit = "Millimetres"
                        conversionFactor.value = 0.001
                        convertUnits()
                    } )
                }
            }

            Spacer(modifier = Modifier.width(30.dp))

            Box {
                Button(onClick = {oExpanded = true} ) {
                    Text(outputUnit)
                    Icon(Icons.Default.KeyboardArrowDown, contentDescription = "")
                }

                    DropdownMenu(expanded = oExpanded , onDismissRequest = {oExpanded = false}) {
                        DropdownMenuItem(text = {Text("Centimeters")}, onClick = {
                            oExpanded = false
                            outputUnit = "Centimetres"
                            oconversionFactor.value = 0.01
                            convertUnits()
                        } )
                        DropdownMenuItem(text = {Text("Meters")}, onClick = {
                            oExpanded = false
                            outputUnit = "Meters"
                            oconversionFactor.value = 1.0
                            convertUnits()
                        })
                        DropdownMenuItem(text = {Text("Feet")}, onClick = {
                            oExpanded = false
                            outputUnit = "Feet"
                            oconversionFactor.value = 0.3048
                            convertUnits()
                        } )
                        DropdownMenuItem(text = {Text("Millimeters")}, onClick = {
                            oExpanded = false
                            outputUnit = "Millimeters"
                            oconversionFactor.value = 0.001
                            convertUnits()
                        } )
                    }

            }
        }


        Text("Value: $outputValue $outputUnit ",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.headlineSmall)
    }
}


@Preview(showBackground = true)
@Composable
fun NewPreview(){
    UnitConverter()
}