package com.example.ktorexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.ktorexample.ui.theme.KtorExampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KtorExampleTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val products = produceState(
                        initialValue = emptyList<ResponseModel>(),
                        producer = {
                            value = apiService.getProducts()
                        }
                    )

                    LazyColumn {
                        items(products.value) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        bottom = 6.dp,
                                        top = 6.dp,
                                    )
                                    .background(Color.Gray)) {

                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(4.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                ) {
                                    //set the image url
                                    val painter = rememberImagePainter(
                                        data = it.image,
                                        builder = {
                                            error(R.drawable.ic_launcher_background)
                                        }
                                    )

                                    Image(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(150.dp),
                                        contentScale = ContentScale.Crop,
                                        contentDescription = "Coil Image",
                                        painter = painter
                                    )
                                    Spacer(
                                        modifier = Modifier
                                            .height(4.dp)
                                    )
                                    Text(
                                        text = it.title,
                                        fontSize = 18.sp
                                    )
                                    Spacer(
                                        modifier = Modifier
                                            .height(4.dp)
                                    )
                                    Text (
                                        text = it.description,
                                        fontSize = 12.sp
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

    }
    private val apiService by lazy {
        ApiService.create()
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    KtorExampleTheme {
//        Greeting("Android")
    }
}