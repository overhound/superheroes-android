package com.superheroes.android

import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.superheroes.android.api.model.Biography
import com.superheroes.android.api.model.Image
import com.superheroes.android.api.model.PowerStats
import com.superheroes.android.api.model.Superhero
import com.superheroes.android.ui.theme.SuperheroesAndroidTheme
import com.superheroes.android.view.*
import com.superheroes.android.viewmodel.MainActivityViewModel
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SuperheroScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun superheroTitleIsDisplayed() {
        composeTestRule.activity.setContent {
            SuperheroesAndroidTheme {
                SuperheroTitle()
            }
        }
        composeTestRule.onNodeWithText("Superheroes").assertIsDisplayed()
    }

    @Test
    fun superheroListDisplaysSuperheroNames() {
        composeTestRule.activity.setContent {
            SuperheroesAndroidTheme {
                val state = remember { MainActivityViewModel.SuperheroState(data = sampleSuperheroes) }
                SuperheroList(heroes = state)
            }
        }

        sampleSuperheroes.forEach { superhero ->
            composeTestRule.onNodeWithText(superhero.name).assertIsDisplayed()
        }
    }

    @Test
    fun superheroListDisplaysFastTag() {
        composeTestRule.activity.setContent {
            SuperheroesAndroidTheme {
                val state = remember { MainActivityViewModel.SuperheroState(data = sampleSuperheroes) }
                SuperheroList(heroes = state)
            }
        }

        sampleSuperheroes.forEach { superhero ->
            val speed = superhero.powerstats.speed.toIntOrNull() ?: 0
            if (speed > 50) {
                composeTestRule.onNodeWithText("[Fast]").assertIsDisplayed()
            } else {
                composeTestRule.onNodeWithText("[Fast]").assertDoesNotExist()
            }
        }
    }

    @Test
    fun superheroListDisplaysStrongTag() {
        composeTestRule.activity.setContent {
            SuperheroesAndroidTheme {
                val state = remember { MainActivityViewModel.SuperheroState(data = sampleSuperheroes) }
                SuperheroList(heroes = state)
            }
        }

        sampleSuperheroes.forEach { superhero ->
            val power = superhero.powerstats.power.toIntOrNull() ?: 0
            if (power > 55) {
                composeTestRule.onNodeWithText("[Strong]").assertIsDisplayed()
            } else {
                composeTestRule.onNodeWithText("[Strong]").assertDoesNotExist()
            }
        }
    }

    @Test
    fun superheroListDisplaysSmartTag() {
        composeTestRule.activity.setContent {
            SuperheroesAndroidTheme {
                val state = remember { MainActivityViewModel.SuperheroState(data = sampleSuperheroes) }
                SuperheroList(heroes = state)
            }
        }

        sampleSuperheroes.forEach { superhero ->
            val intelligence = superhero.powerstats.intelligence.toIntOrNull() ?: 0
            if (intelligence > 60) {
                composeTestRule.onNodeWithText("[Smart]").assertIsDisplayed()
            } else {
                composeTestRule.onNodeWithText("[Smart]").assertDoesNotExist()
            }
        }
    }

    private val sampleSuperheroes = listOf(
        Superhero(
            id = "1",
            name = "Superhero One",
            biography = Biography(
                fullName = "Superhero One",
                alterEgos = "No alter egos",
                placeOfBirth = "Universe One",
                publisher = "Publisher One"
            ),
            image = Image("https://example.com/superhero_one.jpg"),
            powerstats = PowerStats(
                intelligence = "60",
                strength = "50",
                speed = "40",
                durability = "50",
                power = "30",
                combat = "60"
            )
        ),
        Superhero(
            id = "2",
            name = "Superhero Two",
            biography = Biography(
                fullName = "Superhero Two",
                alterEgos = "No alter egos",
                placeOfBirth = "Universe Two",
                publisher = "Publisher Two"
            ),
            image = Image("https://example.com/superhero_two.jpg"),
            powerstats = PowerStats(
                intelligence = "50",
                strength = "40",
                speed = "30",
                durability = "40",
                power = "20",
                combat = "50"
            )
        )
    )
}
