package org.devvikram.firstpmpproject.presentation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material.Colors
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.devvikram.firstpmpproject.R

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    Column (
        modifier = modifier
           .verticalScroll(rememberScrollState())
    ){
        Spacer(modifier = Modifier.height(16.dp))
        SearchBar(modifier = Modifier.padding(horizontal = 16.dp))
        HomeSection(title = "Align Your Body") {
            AlignYourBodyRow()
        }
        HomeSection(title = "Favourites Collection") {
            FavoriteCollectionsGrid()
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun HomeSection(
     title: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {

    Column(modifier) {
        Text(
            text = title,
            style = MaterialTheme.typography.h5,
            modifier = modifier
                .paddingFromBaseline(top = 16.dp, bottom = 16.dp)
                .padding(16.dp)

        )
        content()
    }

}


@Composable
fun SearchBar(modifier: Modifier = Modifier) {
    TextField(
        value = "",
        onValueChange = {},
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = null)
        },
        colors = TextFieldDefaults.textFieldColors(
            unfocusedIndicatorColor = MaterialTheme.colors.surface,
            focusedIndicatorColor = MaterialTheme.colors.surface
        ),
        placeholder = {
            Text(stringResource(R.string.placeholder_search))
        },
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
    )


}

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
            painter = painterResource(drawable), contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape)
        )
        Text(
            text = stringResource(text),
            style = MaterialTheme.typography.subtitle1,
            modifier = modifier.paddingFromBaseline(
                top = 24.dp, bottom = 8.dp
            )
        )
    }
}


// favoriate collection card
@Composable
fun FavoriteCollectionCard(
    @DrawableRes drawable: Int,
     text: String,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        color = Color.LightGray,
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.width(255.dp)
        ) {
            Image(
                painter = painterResource(drawable),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(88.dp)
            )
            Text(
                text = text,
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}


@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun FavoriteCollectionCardPreview() {
    FavoriteCollectionCard(
        text ="",
        drawable = R.drawable.ic_launcher_background,
        modifier = Modifier.padding(8.dp)
    )

}

data class Favorite(
    @DrawableRes val drawable: Int,
    val text: String
)

@Composable
fun FavoriteCollectionsGrid(
    modifier: Modifier = Modifier
) {
    val favorites = listOf(
        Favorite(
            text = "Nature",
            drawable = R.drawable.ic_nature
        ),
        Favorite(
            text = "Short Mantras",
            drawable = R.drawable.ic_mantra
        ),
        Favorite(
            text = "Meditation",
            drawable = R.drawable.ic_mediation
        ),
        Favorite(
            text = "Yoga Asanas",
            drawable = R.drawable.ic_yoga
        )
    )


    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 8.dp),
        modifier = modifier.height(168.dp)
    ) {
        items(favorites) { favorite ->
            FavoriteCollectionCard(
                text = favorite.text,
                drawable = favorite.drawable,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}


data class AlignYourBodyData(
    @DrawableRes val drawable: Int,
    @StringRes val text: Int
)


@Composable
fun AlignYourBodyRow(modifier: Modifier = Modifier) {
    val alignYourBodyData = listOf(
        AlignYourBodyData(R.drawable.yoga_pose_tree, R.string.yoga_tree_pose),
        AlignYourBodyData(R.drawable.yoga_handstand, R.string.yoga_handstand),
        AlignYourBodyData(R.drawable.yoga_lotus_pose, R.string.yoga_lotus_pose),
        AlignYourBodyData(R.drawable.yoga_forward_bend, R.string.yoga_forward_bend)
    )

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ) {
        items(alignYourBodyData) { item ->
            AlignYourBodyElement(
                drawable = item.drawable,
                text = item.text,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}


@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun AlignYourBodyElementPreview() {
    AlignYourBodyElement(
        drawable = R.drawable.ic_launcher_background,
        text = R.string.app_name, modifier = Modifier.padding(8.dp)
    )
}
