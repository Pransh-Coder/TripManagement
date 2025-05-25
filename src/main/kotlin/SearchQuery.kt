package org.example

import java.time.LocalDateTime

data class SearchQuery(
    val query:String? = null,
    val dateRange: List<LocalDateTime>? = null
)
