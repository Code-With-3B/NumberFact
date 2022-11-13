package com.codewith3b.numberfact

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codewith3b.numberfact.ui.theme.NumberFactTheme
import com.codewith3b.numberfact.ui.theme.SearchTextFieldColor
import com.codewith3b.numberfact.view.AdView
import com.codewith3b.numberfact.viewmodel.NumberViewModel


class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Log.e("IN CONTENT", "IN")
            App(NumberViewModel())
        }
    }
}

@Composable
fun App(numberViewModel: NumberViewModel) {

    NumberFactTheme {
        val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Open))
        Scaffold(
            backgroundColor = Color.Black,
            scaffoldState = scaffoldState,
            topBar = {
               AdView(1)
            },
            content = {

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {

                    Text(
                        text = numberViewModel.fact,
                        modifier = Modifier.padding(20.dp, 10.dp),
                        fontSize = 24.sp,
                        fontFamily = FontFamily.Monospace,
                        color = Color.Yellow
                    )

                    SearchView(numberViewModel)

                    Text(text = "OR", color = Color.Gray, modifier = Modifier.padding(10.dp))

                    Button(
                        onClick = { changeRandomFact(numberViewModel) },
                        colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.DarkGray),
                        border = BorderStroke(0.5.dp, Color.Yellow),
                        modifier = Modifier.background(
                            color = Color.Transparent,
                            shape = RoundedCornerShape(40)
                        )
                    ) {
                        Text(
                            text = "Show Random Fact", color = Color.White
                        )
                    }
                }

            },
            bottomBar = {
                AdView(2)
            }
        )
    }
}


fun changeRandomFact(numberViewModel: NumberViewModel) = numberViewModel.getRandomFact()


@Composable
fun SearchView(numberViewModel: NumberViewModel) {

    var query: String by rememberSaveable { mutableStateOf("") }
    val numberPattern = remember { Regex("^\\d+\$") }

    TextField(
        value = query,
        onValueChange = { onQueryChanged ->
            query = onQueryChanged.trim()

            if (query.isNotEmpty() && query.matches(numberPattern)) {
                try {
                    numberViewModel.getNumberFact(query.toLong())
                } catch (_: java.lang.Exception) {
                    numberViewModel.fact = "NO RESULT FOUND !"
                }
            } else
                numberViewModel.fact = "NO RESULT FOUND !"
        },
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            focusedIndicatorColor = Color.Red
        ),
        placeholder = { Text(text = stringResource(R.string.hint_search_query)) },
        textStyle = MaterialTheme.typography.subtitle1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = Modifier
            .padding(10.dp)
            .background(color = SearchTextFieldColor, shape = RoundedCornerShape(20))
            .height(50.dp),
    )

}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun MyViewPreview() {
    App(numberViewModel = NumberViewModel())
}

