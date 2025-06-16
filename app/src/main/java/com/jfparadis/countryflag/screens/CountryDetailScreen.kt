package com.jfparadis.countryflag.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.jfparadis.countryflag.viewModel.CountryDetailViewModel

// Simple Detail view to show the flag and some basic information on the country
// Clearly this is programmer's art and would need a better Design.
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryDetailScreen(
        countryName: String,
        viewModel: CountryDetailViewModel = hiltViewModel(),
        navigateBack: () -> Unit,)
{
    val country by viewModel.selectedCountry.collectAsState()

    // Load when this screen appears
    LaunchedEffect(countryName) {
        viewModel.loadCountryDetails(countryName)
    }

    Scaffold(
    topBar = {
        TopAppBar(
            title = { Text("Detail for $countryName") },
            navigationIcon = {
                IconButton(onClick = navigateBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            },
        )
    })
    { padding ->

        country?.let {
            Column(
                modifier = Modifier
                    .background(color = Color.LightGray)
                    .padding(5.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            )
            {
                AsyncImage(
                    model = it.flags.png,
                    contentDescription = "Country Flag",

                    modifier = Modifier
                        .padding(start = 10.dp, end= 10.dp, top=25.dp, bottom=0.dp)
                        .size(width = 150.dp, height = 175.dp).fillMaxSize()
                )

                Text(
                    "Country: ${it.name.common}",
                    style = MaterialTheme.typography.headlineSmall
                )
                Text(
                    "Continent: ${it.region ?: "N/A"}",
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    "Population: ${it.population?.toString() ?: "N/A"}",
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    "Capital: ${it.capital?.joinToString() ?: "N/A"}",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        } ?: run {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            )
            {
                Text("Loading country details... for $countryName")
            }
        }

    }
}