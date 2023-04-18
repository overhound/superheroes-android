package com.superheroes.android.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.superheroes.android.api.data.ApiService
import com.superheroes.android.ui.theme.SuperheroesAndroidTheme
import com.superheroes.android.viewmodel.MainActivityViewModel
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
                    val viewModel = hiltViewModel<MainActivityViewModel>()
                    val heroes by viewModel.state.collectAsState()

                    SuperheroScreen(heroes, onSearch = { query ->
                        viewModel.fetchSuperheros(query)
                    })
                }
            }
        }
    }
}