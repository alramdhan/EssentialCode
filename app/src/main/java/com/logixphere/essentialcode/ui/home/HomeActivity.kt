package com.logixphere.essentialcode.ui.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.logixphere.essentialcode.data.models.BaseResponse
import com.logixphere.essentialcode.data.models.movies.MovieResponse
import com.logixphere.essentialcode.ui.theme.Purple40
import com.logixphere.essentialcode.ui.theme.Purple80
import com.logixphere.essentialcode.utils.AppScreen
import com.logixphere.essentialcode.viewmodels.MovieViewModel

@Composable
fun HomeScreen(navController: NavController) {
    val viewModel: MovieViewModel = hiltViewModel()
    val showLoading by viewModel.showLoading.observeAsState()
    val dataMovie by viewModel.dataMovie.observeAsState()
    val navSelectedItem by viewModel.navSelectedItem.observeAsState()

    LaunchedEffect(Unit) {
        viewModel.getData()
    }

    if(!showLoading!!) {
        Scaffold(
            bottomBar = {
                NavigationBar {
                    BottomNavigationItem().bottomNavigationItems().forEachIndexed { index, navigationItem ->
                        NavigationBarItem(
                            selected = index == navSelectedItem,
                            label = {
                                Text(navigationItem.label)
                            },
                            icon = {
                                Icon(
                                    navigationItem.icon,
                                    contentDescription = navigationItem.label
                                )
                            },
                            onClick = {

                            }
                        )
                    }
                }
            }
        ) { contentPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(contentPadding)
            ) {
                AppBarHome()
                SectionHeader("New Showing")
                LazyRow(
                    modifier = Modifier.padding(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    (dataMovie as BaseResponse.Success<List<MovieResponse>>).body?.let {
                        items(6) { item ->
                            val row = it[item]
                            Column(
                                modifier = Modifier.width(150.dp)
                            ) {
                                AsyncImage(
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data("https://i.pinimg.com/originals/14/38/99/143899bf551d5d73c0993a2e35bf2418.jpg")
                                        .build(),
                                    contentDescription = "poster movie",
                                    contentScale = ContentScale.FillBounds,
                                    modifier = Modifier
                                        .height(200.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                )
                                Text(
                                    row.title,
                                    fontSize = 16.sp,
                                    textAlign = TextAlign.Start,
                                    fontWeight = FontWeight.SemiBold,
                                )
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.Star,
                                        "star rating",
                                        tint = Color(0xFFFFD702),
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Text("${row.rating}/10", fontSize = 12.sp)
                                }
                            }
                        }
                    }
                }
                Box(modifier = Modifier.height(20.dp))
                SectionHeader("Popular")
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    (dataMovie as BaseResponse.Success<List<MovieResponse>>).body?.let {
                        items(it.size) { item ->
                            val row = it[item]
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(120.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween // Control the header Alignment over here.
                            ) {
                                AsyncImage(
//                                        model = "https://i.pinimg.com/originals/14/38/99/143899bf551d5d73c0993a2e35bf2418.jpg",
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data("https://i.pinimg.com/originals/14/38/99/143899bf551d5d73c0993a2e35bf2418.jpg")
                                        .build(),
                                    contentDescription = "poster movie",
                                    contentScale = ContentScale.FillBounds,
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .width(100.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                )
                                Box(modifier = Modifier.width(10.dp))
                                Column(
                                    modifier = Modifier.fillMaxSize()
                                        .padding(top = 4.dp),
                                    horizontalAlignment = Alignment.Start,
                                    verticalArrangement = Arrangement.Top
                                ) {
                                    Text(
                                        text = row.title,
                                        fontSize = 20.sp,
                                        textAlign = TextAlign.Start,
                                        fontWeight = FontWeight.SemiBold,
                                    )
                                    Row(
                                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(
                                            imageVector = Icons.Filled.Star,
                                            "star rating",
                                            tint = Color(0xFFFFD702),
                                            modifier = Modifier.size(16.dp)
                                        )
                                        Text("${row.rating}/10", fontSize = 12.sp)
                                    }
                                    LazyRow(
                                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                                    ) {
                                        items(row.genre.size) {
                                            Box(
                                                modifier = Modifier.background(Purple80.copy(alpha = .5f), shape = RoundedCornerShape(50))
                                                    .padding(horizontal = 10.dp),
                                            ) {
                                                Text(row.genre[it], fontSize = 10.sp, color = Purple40)
                                            }
                                        }
                                    }
                                    Text("runtime: ${row.runtime}", fontSize = 11.sp)
                                }
                            }
                        }
                    }
                }
            }
        }
    } else {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator()
        }
    }
}

@Composable
fun AppBarHome() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(75.dp)
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.size(25.dp))
        Text(
            "Movie",
            fontSize = 24.sp
        )
        Icon(
            imageVector = Icons.Outlined.Notifications,
            "icon notification"
        )
    }
}

@Composable
fun SectionHeader(title: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 14.dp, end = 14.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            title,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        OutlinedButton(
            modifier = Modifier
                .clip(RoundedCornerShape(30.dp))
                .height(30.dp),
            contentPadding = PaddingValues(horizontal = 20.dp),
            border = BorderStroke(width = 1.dp, color = Color.Gray.copy(alpha = 0.5f)),
            onClick = { }
        ) {
            Text("See More",
                fontSize = 12.sp,
                color = Color.Gray.copy(alpha = .75f)
            )
        }
    }
}

data class BottomNavigationItem(
    val label: String = "",
    val icon: ImageVector = Icons.Filled.Home,
    val route: String = ""
) {
    fun bottomNavigationItems(): List<BottomNavigationItem> {
        return listOf(
            BottomNavigationItem(
                "Beranda",
                route = AppScreen.HOME.route
            ),
            BottomNavigationItem(
                "Notifikasi",
                Icons.Filled.Notifications,
                AppScreen.DETAIL_MOVIE.route
            ),
            BottomNavigationItem(
                "Pengaturan",
                Icons.Filled.Settings,
                AppScreen.DETAIL_MOVIE.route
            ),
        )
    }
}
