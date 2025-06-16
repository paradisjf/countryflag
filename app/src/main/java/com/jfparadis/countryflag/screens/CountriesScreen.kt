package com.jfparadis.countryflag.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import androidx.hilt.navigation.compose.hiltViewModel
import com.jfparadis.countryflag.viewModel.CountryViewModel

// Home page: all the flags are shown with the country name.
// Some things to improve:
//  - Add pagination on the network call
//  - Add better error handling
//  - Fix all the UI glitches.
//
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CountriesScreen(onItemClick: (String) -> Unit, viewModel: CountryViewModel = hiltViewModel() ) {
    val countries by viewModel.countries.collectAsState()

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(countries) { country ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .clickable { onItemClick(country.name.common) }
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                ) {
                    AsyncImage(
                        model = country.flags.png,
                        contentDescription = "The country flag",
                    )
                }
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                    Text(text = country.name.common, style = MaterialTheme.typography.bodyLarge)
                }
            }
        }
    }
}
