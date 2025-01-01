package org.devvikram.firstpmpproject.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.devvikram.Utils.NetworkError
import org.devvikram.Utils.onError
import org.devvikram.Utils.onSuccess
import org.devvikram.firstpmpproject.model.Post
import org.devvikram.firstpmpproject.network.PostClient


@Composable
fun PostScreen(postClient: PostClient) {
    var isLoading by remember {
        mutableStateOf(false)
    }
    var errorMessage by remember {
        mutableStateOf<NetworkError?>(null)
    }
    var posts by remember {
        mutableStateOf<List<Post>>(emptyList())
    }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isLoading) {
            Text("Loading...")
        } else if (errorMessage!= null) {
            Text(errorMessage.toString())
        } else {
            Text("Post Screen")
        }

        Button(onClick = {
            scope.launch {
                isLoading = true
                errorMessage = null

                postClient.getAllPosts()
                    .onSuccess {
                        posts = it
                    }
                    .onError {
                        errorMessage = it
                    }
                isLoading = false
            }

        }){
            Text(
                "Get Posts"
            )
        }

        LazyColumn {
            items(posts.size) {
                PostCard(posts[it])
            }
        }
    }


}

@Composable
fun PostCard(post: Post) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "UserId: ${post.userId}",
                    style = MaterialTheme.typography.body2,
                )
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(start = 16.dp)
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colors.primary)
                ) {
                    Text(
                        text = "${post.id}",
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(horizontal = 8.dp),
                        color = Color.White
                    )
                }

            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = post.title,
                modifier = Modifier.padding(bottom = 4.dp)
            )

            Text(
                text = post.body,
            )
        }
    }
}



