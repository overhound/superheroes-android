package com.superheroes.android.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.superheroes.android.R
import com.superheroes.android.api.model.Superhero
import com.superheroes.android.viewmodel.MainActivityViewModel

@Composable
fun SuperheroScreen(state: MainActivityViewModel.SuperheroState, onSearch: (String) -> Unit) {
    Column {
        SuperheroTitle()
        Divider()
        SuperheroSearchBar(onSearch)
        SuperheroList(state)
    }
}

@Composable
fun SuperheroTitle() {
    Text("Superheroes", style = MaterialTheme.typography.h4)
}

@Composable
fun SuperheroSearchBar(onSearch: (String) -> Unit) {
    val searchQuery = remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = searchQuery.value,
            onValueChange = { newValue -> searchQuery.value = newValue },
            label = { Text(stringResource(R.string.search)) },
            singleLine = true,
            modifier = Modifier.weight(1f)
        )

        Button(
            onClick = { onSearch(searchQuery.value) },
            modifier = Modifier
                .padding(start = 8.dp, top = 8.dp)
        ) {
            Text(stringResource(R.string.go))
        }
    }
}

@Composable
fun SuperheroList(heroes: MainActivityViewModel.SuperheroState) {
    LazyColumn {
        items(heroes.data) { hero ->
            SuperheroItem(hero)
            Divider()
        }
    }
}


@Composable
fun SuperheroItem(hero: Superhero) {
    Row {
        AsyncImage(model = hero.image.url, contentDescription = hero.name, modifier = Modifier.width(96.dp))
        Text(hero.name, modifier = Modifier.padding(horizontal = 16.dp))
        with(hero.powerstats) {
            when {
                (speed.toIntOrNull() ?: 0) > 50 -> Text("[Fast]")
                (power.toIntOrNull() ?: 0) > 55 -> Text("[Strong]")
                (intelligence.toIntOrNull() ?: 0) > 60 -> Text("[Smart]")
                (combat.toIntOrNull() ?: 0) > 30 -> Text(text = "[Fighter]", modifier = Modifier.padding(end = 8.dp))
            }
        }

    }
}