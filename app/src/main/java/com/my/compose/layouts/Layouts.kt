package com.my.compose.layouts

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Spa
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.my.compose.R
import com.my.compose.ui.theme.ComposeTheme

// Step: App
@Composable
fun Layouts(windowSize: WindowSizeClass) {
    when(windowSize.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            LayoutsPortrait()
        }
        WindowWidthSizeClass.Expanded -> {
            LayoutsLandscape()
        }
    }
}

// Step: Search bar - Modifiers
@Composable
fun SearchBar(
    modifier: Modifier = Modifier
) {
    TextField(
        value = "",
        onValueChange = {},
        leadingIcon = {
            Icon(imageVector = Icons.Outlined.Search, contentDescription = null)
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedContainerColor = MaterialTheme.colorScheme.surface
        ),
        placeholder = {
            Text(text = stringResource(id = R.string.placeholder_search))
        },
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
    )
}

// Step: Align your body - Alignment
@Composable
fun AlignYourBodyElement(
    @DrawableRes drawable: Int,
    @StringRes text: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = drawable),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape)
        )
        Text(
            text = stringResource(id = text),
            modifier = Modifier.paddingFromBaseline(top = 24.dp, bottom = 8.dp),
            style = MaterialTheme.typography.bodyMedium

        )
    }
}

// Step: Favorite collection card - Material Surface
@Composable
fun FavoriteCollectionCard(
    @DrawableRes drawable: Int,
    @StringRes text: Int,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.width(255.dp)
        ) {
            Image(
                painter = painterResource(id = drawable),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(80.dp)

                )
            Text(
                text = stringResource(id = text),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

// Step: Align your body row - Arrangements
@Composable
fun AlignYourBodyRow(
    modifier: Modifier = Modifier
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ) {
        items(alignYourBodyData) { item ->
            AlignYourBodyElement(drawable = item.drawable, text = item.text)
        }
    }
}

// Step: Favorite collections grid - LazyGrid
@Composable
fun FavouriteCollectionsGrid(
    modifier: Modifier = Modifier
) {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.height(168.dp)
    ) {
        items(favouriteCollectionsData) { item ->
            FavoriteCollectionCard(drawable = item.drawable, text = item.text, Modifier.height(80.dp))
        }
    }
}

// Step: Home section - Slot APIs
@Composable
fun HomeSection(
    @StringRes title: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(modifier) {
        Text(
            text = stringResource(id = title),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .paddingFromBaseline(top = 40.dp, bottom = 16.dp)
        )
        content()
    }
}

// Step: Home screen - Scrolling
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier.verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        SearchBar(Modifier.padding(horizontal = 16.dp))
        HomeSection(title = R.string.align_your_body) {
            AlignYourBodyRow()
        }
        HomeSection(title = R.string.favorite_collections) {
            FavouriteCollectionsGrid()
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

// Step: Bottom navigation - Material
@Composable
fun LayoutsBottomNavigation(
    modifier: Modifier = Modifier
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier
    ) {
        NavigationBarItem(
            selected = true,
            onClick = { },
            label = {
                Text(text = stringResource(id = R.string.bottom_navigation_home))
            },
            icon = {
                Icon(imageVector = Icons.Outlined.Spa, contentDescription = null)
            }
        )
        NavigationBarItem(
            selected = false,
            onClick = { },
            label = {
                Text(text = stringResource(id = R.string.bottom_navigation_profile))
            },
            icon = {
                Icon(imageVector = Icons.Outlined.AccountCircle, contentDescription = null)
            }
        )
    }
}

// Step: App - Scaffold
@Composable
fun LayoutsPortrait() {
    ComposeTheme {
        Scaffold(
            bottomBar = { LayoutsBottomNavigation() }
        ) { padding ->
            HomeScreen(Modifier.padding(padding))
        }
    }
}

// Step: Rail navigation - Material
@Composable
fun LayoutsNavigationRail(
    modifier: Modifier = Modifier
) {
    NavigationRail(
        modifier = modifier.padding(start = 8.dp, end = 8.dp),
        containerColor = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NavigationRailItem(
                selected = true,
                onClick = {  },
                label = {
                    Text(text = stringResource(id = R.string.bottom_navigation_home))
                },
                icon = {
                    Icon(imageVector = Icons.Outlined.Spa, contentDescription = null)
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
            NavigationRailItem(
                selected = false,
                onClick = {  },
                label = {
                    Text(text = stringResource(id = R.string.bottom_navigation_profile))
                },
                icon = {
                    Icon(imageVector = Icons.Outlined.AccountCircle, contentDescription = null)
                }
            )
        }
    }
}

// Step: Landscape Mode
@Composable
fun LayoutsLandscape() {
    ComposeTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            Row {
                LayoutsNavigationRail()
                HomeScreen()
            }
        }
    }
}

private val alignYourBodyData = listOf(
    R.drawable.ic1 to R.string.ic1,
    R.drawable.ic2 to R.string.ic2,
    R.drawable.ic3 to R.string.ic3,
    R.drawable.ic4 to R.string.ic4,
    R.drawable.ic5 to R.string.ic5
).map { DrawableStringPair(drawable = it.first, text = it.second) }

private val favouriteCollectionsData = listOf(
    R.drawable.ic6 to R.string.ic6,
    R.drawable.ic7 to R.string.ic7,
    R.drawable.ic8 to R.string.ic8,
    R.drawable.ic9 to R.string.ic9,
    R.drawable.ic10 to R.string.ic10
).map { DrawableStringPair(drawable = it.first, text = it.second) }

private data class DrawableStringPair(
    @DrawableRes val drawable: Int,
    @StringRes val text: Int
)

@Preview(showBackground = true)
@Composable
private fun SearchBarPreview() {
    ComposeTheme {
        SearchBar()
    }
}

@Preview(showBackground = true)
@Composable
private fun AlignYourBodyElementPreview() {
    ComposeTheme {
        AlignYourBodyElement(
            drawable = R.drawable.ic1,
            text = R.string.ic1
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun FavoriteCollectionCardPreview() {
    ComposeTheme {
        FavoriteCollectionCard(
            drawable = R.drawable.ic7,
            text = R.string.ic7,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AlignYourBodyRowPreview() {
    ComposeTheme {
        AlignYourBodyRow()
    }
}

@Preview(showBackground = true)
@Composable
private fun FavouriteCollectionsGridPreview() {
    ComposeTheme {
        FavouriteCollectionsGrid()
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeSectionPreview() {
    ComposeTheme {
        HomeSection(title = R.string.align_your_body) {
            AlignYourBodyRow()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    ComposeTheme {
        HomeScreen()
    }
}

@Preview(showBackground = true)
@Composable
private fun LayoutsBottomNavigationPreview() {
    ComposeTheme {
        LayoutsBottomNavigation()
    }
}

@Preview(widthDp = 360, heightDp = 640)
@Composable
private fun LayoutsPortraitPreview() {
    ComposeTheme {
        LayoutsPortrait()
    }
}

@Preview(showBackground = true)
@Composable
private fun LayoutsNavigationRailPreview() {
    ComposeTheme {
        LayoutsNavigationRail()
    }
}

@Preview(widthDp = 640, heightDp = 360)
@Composable
private fun LayoutsLandscapePreview() {
    ComposeTheme {
        LayoutsLandscape()
    }
}