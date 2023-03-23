package com.superheroes.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.superheroes.android.api.ApiService
import com.superheroes.android.api.model.Superhero
import com.superheroes.android.ui.theme.SuperheroesAndroidTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuperheroesAndroidTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    val heroes = remember { mutableStateOf(listOf<Superhero>()) }

                    LaunchedEffect(key1 = Unit) {
                        heroes.value = apiService.searchByName("bat").results ?: emptyList()
                    }

                    Column {
                        Text("Superheroes", style = MaterialTheme.typography.h4)
                        Divider()
                        LazyColumn {
                            items(heroes.value) { hero ->
                                Row {
                                    AsyncImage(model = hero.image.url, contentDescription = hero.name, modifier = Modifier.width(96.dp))
                                    Text(hero.name, modifier = Modifier.padding(horizontal = 16.dp))
                                    if ((hero.powerstats.speed.toIntOrNull() ?: 0) > 50) {
                                        Text("[Fast]")
                                    } else if ((hero.powerstats.power.toIntOrNull()?: 0) > 55) {
                                        Text("[Strong]")
                                    } else if ((hero.powerstats.intelligence.toIntOrNull()?: 0) > 60) {
                                        Text("[Smart]")
                                    }
                                }
                                Divider()
                            }
                        }
                    }
                }
            }
        }
    }
}