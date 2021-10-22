package com.example.pokemonsdemo.ui.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.pokemonsdemo.R
import com.example.pokemonsdemo.ui.NavItem
import com.example.pokemonsdemo.ui.theme.PokemonsDemoTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun PokemonsDrawer(
    scope: CoroutineScope,
    scaffoldState: ScaffoldState,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(MaterialTheme.colors.background)
            .fillMaxHeight()
    ) {
        DrawerTop(modifier = modifier)
        Spacer(modifier = modifier.fillMaxWidth().height(5.dp))
        DrawerData(
            scope = scope,
            scaffoldState = scaffoldState,
            navController = navController,
            modifier = modifier
        )
    }
}

@Composable
private fun DrawerTop(
    modifier: Modifier
) {
    Card(
        modifier = modifier
            .padding(8.dp, 8.dp, 8.dp, 0.dp)
            .shadow(10.dp)
            .clip(RoundedCornerShape(corner = CornerSize(8.dp)))
            .fillMaxWidth(),
        elevation = 2.dp,
        shape = RoundedCornerShape(corner = CornerSize(8.dp)),
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.primary)
        ) {
            Image(
                painter = painterResource(
                    id = R.drawable.ic_baseline_catching_pokemon_24
                ),
                contentDescription = "Pokemon",
                modifier = modifier
                    .height(100.dp)
                    .width(100.dp)
                    .padding(10.dp),
                colorFilter = ColorFilter.tint(
                    MaterialTheme.colors.secondary,
                ),
                alignment = Alignment.CenterStart
            )
            Spacer(
                modifier = modifier
                    .width(5.dp)
            )
            Text(
                text = "Pokemons",
                color = MaterialTheme.colors.onPrimary,
                fontSize = 30.sp,
                fontStyle = FontStyle.Normal,
                fontFamily = FontFamily.Serif,
                textAlign = TextAlign.Start,
                modifier = modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            )
        }
    }
}

@Composable
private fun DrawerData(
    scope: CoroutineScope,
    scaffoldState: ScaffoldState,
    navController: NavController,
    modifier: Modifier
) {
    val items = listOf(
        NavItem.PokemonListScreen
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    items.forEach { item ->
        DrawerItem(
            item = item,
            selected = currentRoute == item.route,
            onItemClick = {
                navController.navigate(item.route) {
                    navController.graph.startDestinationRoute?.let { route ->
                        popUpTo(route) {
                            saveState = true
                        }
                    }
                    launchSingleTop = true
                    restoreState = true
                }
                scope.launch {
                    scaffoldState.drawerState.close()
                }
            },
            modifier = modifier
        )
    }
}

@Composable
private fun DrawerItem(
    item: NavItem,
    selected: Boolean,
    onItemClick: (NavItem) -> Unit,
    modifier: Modifier
) {
    val colorSelected = if (selected) MaterialTheme.colors.secondary
    else MaterialTheme.colors.background
    Card(
        modifier = modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .shadow(10.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(corner = CornerSize(8.dp))),
        elevation = 2.dp,
        shape = RoundedCornerShape(corner = CornerSize(8.dp)),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .clickable(onClick = { onItemClick(item) })
                .height(45.dp)
                .background(colorSelected)
        ) {
            Text(
                text = item.title,
                fontSize = 18.sp,
                fontFamily = FontFamily.Serif,
                color = MaterialTheme.colors.onSecondary,
                modifier = Modifier
                    .padding(10.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DrawerPreview() {
    PokemonsDemoTheme(darkTheme = false) {
        val scope = rememberCoroutineScope()
        val scaffoldState = rememberScaffoldState(
            rememberDrawerState(DrawerValue.Closed)
        )
        val navController = rememberNavController()
        PokemonsDrawer(
            scope = scope,
            scaffoldState = scaffoldState,
            navController = navController
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DrawerPreviewDark() {
    PokemonsDemoTheme(darkTheme = true) {
        val scope = rememberCoroutineScope()
        val scaffoldState = rememberScaffoldState(
            rememberDrawerState(DrawerValue.Closed)
        )
        val navController = rememberNavController()
        PokemonsDrawer(
            scope = scope,
            scaffoldState = scaffoldState,
            navController = navController)
    }
}

@Preview
@Composable
private fun DrawerTopPreview() {
    PokemonsDemoTheme(darkTheme = false) {
        DrawerTop(modifier = Modifier)
    }
}

@Preview
@Composable
private fun DrawerTopPreviewDark() {
    PokemonsDemoTheme(darkTheme = true) {
        DrawerTop(modifier = Modifier)
    }
}

@Preview
@Composable
private fun DrawerItemPreview() {
    PokemonsDemoTheme(darkTheme = false) {
        DrawerItem(
            item = NavItem.PokemonListScreen,
            selected = false,
            onItemClick = {},
            modifier = Modifier
        )
    }
}

@Preview
@Composable
private fun DrawerItemPreviewDark() {
    PokemonsDemoTheme(darkTheme = true) {
        DrawerItem(
            item = NavItem.PokemonListScreen,
            selected = false,
            onItemClick = {},
            modifier = Modifier
        )
    }
}

@Preview
@Composable
private fun DrawerItemSelectedPreview() {
    PokemonsDemoTheme(darkTheme = false) {
        DrawerItem(
            item = NavItem.PokemonListScreen,
            selected = true,
            onItemClick = {},
            modifier = Modifier
        )
    }
}

@Preview
@Composable
private fun DrawerItemSelectedPreviewDark() {
    PokemonsDemoTheme(darkTheme = true) {
        DrawerItem(
            item = NavItem.PokemonListScreen,
            selected = true,
            onItemClick = {},
            modifier = Modifier
        )
    }
}