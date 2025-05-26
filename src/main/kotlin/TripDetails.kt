package org.example

import java.time.LocalDateTime

data class TripDetails(
    val id: Int,
    val tripName: String,
    val source: String,
    val destination: String,
    val startDate: LocalDateTime = LocalDateTime.now().plusHours(2),
    val endDate: LocalDateTime = LocalDateTime.now().plusDays(2),
    val isFavouriteTrip:Boolean = false,
    val isJoinAllowed: Boolean,

    //I forgot adding these
    val slotsAvailable:Int,
    var joinedMembersList: List<String> = emptyList()
)
