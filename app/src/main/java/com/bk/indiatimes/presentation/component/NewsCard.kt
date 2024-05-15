package com.bk.indiatimes.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.bk.indiatimes.data.model.NewsItem

@Composable
fun NewsCard(newsItem: NewsItem, modifier: Modifier = Modifier , onOpenDetailNews: (category: String) -> Unit) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .clickable {
            if(!newsItem.readMoreUrl.isNullOrEmpty())
                onOpenDetailNews(newsItem.readMoreUrl)
            },
        elevation = CardDefaults.cardElevation(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp)
        ) {
            SubcomposeAsyncImage(
                model = newsItem.imageUrl,
                loading = {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 6.dp),
                contentScale = ContentScale.FillWidth,
                contentDescription = null
            )

            Text(text = newsItem.title, fontWeight = FontWeight.Bold, fontSize = 20.sp, modifier = Modifier.padding(top = 10.dp))
            Text(text = newsItem.content, modifier = Modifier.padding(top = 6.dp))
            Text(
                text = buildString {
                    append(newsItem.author)
                    append(", ")
                    append(newsItem.date)
                    append(", ")
                    append(newsItem.time)
                },
                modifier = Modifier.padding(top = 6.dp),
                fontSize = 10.sp
            )
        }
    }
}


