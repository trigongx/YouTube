package com.example.youtube.data.model

import java.io.Serializable

data class PlaylistsModel(
    val etag: String,
    val items: List<Item>,
    val kind: String,
    val nextPageToken: String,
    val pageInfo: PageInfo
) {
    data class Item(
        val contentDetails: ContentDetails,
        val etag: String,
        val id: String,
        val kind: String,
        val snippet: Snippet
    ) : Serializable {
        data class ContentDetails(
            val videoId: String?,
            val itemCount: Int
        ):Serializable

        data class Snippet(
            val channelId: String,
            val channelTitle: String,
            val description: String,
            val localized: Localized?,
            val publishedAt: String,
            val thumbnails: Thumbnails,
            val title: String
        ) {
            data class Localized(
                val description: String,
                val title: String
            )

            data class Thumbnails(
                val default: Default,
                val high: High,
                val maxres: Maxres,
                val medium: Medium,
                val standard: Standard
            ) {
                data class Default(
                    val height: Int,
                    val url: String,
                    val width: Int
                )

                data class High(
                    val height: Int,
                    val url: String,
                    val width: Int
                )

                data class Maxres(
                    val height: Int,
                    val url: String,
                    val width: Int
                )

                data class Medium(
                    val height: Int,
                    val url: String,
                    val width: Int
                )

                data class Standard(
                    val height: Int,
                    val url: String,
                    val width: Int
                )
            }
        }
    }

    data class PageInfo(
        val resultsPerPage: Int,
        val totalResults: Int
    )
}