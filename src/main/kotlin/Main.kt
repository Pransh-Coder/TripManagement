package org.example

import java.time.LocalDateTime
/* Data classes

TripDetails
id
name
source
destination
startDate
endDate
isJoinAllowed
isFavouriteTrip
slotsAvailable
joinedMembersList


SearchQuery
query(source/destination)
List<Dates>
 */
fun main() {

    val tripRepository = TripRepository()

    val trip1 = TripDetails(
        id = 1,
        tripName = "PranshTrip",
        source = "Lucknow",
        destination = "Kashmir",
        isFavouriteTrip = false,
        isJoinAllowed = true,
        slotsAvailable = 3,
    )

    tripRepository.createTrip(trip1)

    val trip2 = TripDetails(
        id = 2,
        tripName = "SaurabhTrip",
        source = "Gurgaon",
        destination = "Ladakh",
        isFavouriteTrip = false,
        isJoinAllowed = true,
        slotsAvailable = 1,
    )

    tripRepository.createTrip(trip2)

    val trip3 = TripDetails(
        id = 3,
        tripName = "PranshTrip",
        source = "Lucknow",
        destination = "Kasol",
        isFavouriteTrip = false,
        isJoinAllowed = false,
        slotsAvailable = 3,
    )

    tripRepository.createTrip(trip3)

    val trip4 = TripDetails(
        id = 4,
        tripName = "PranshTrip",
        source = "Lucknow",
        destination = "Manali",
        isFavouriteTrip = false,
        isJoinAllowed = false,
        slotsAvailable = 0,
    )

    tripRepository.createTrip(trip4)

    println("GET ALL TRIPS")
    val tripList = tripRepository.getAllTrips()
    println("tripList: tripListSize = ${tripList.size} || $tripList")

    println()
    tripRepository.searchTripBySourceDestination(searchQuery = SearchQuery(query = "Kas"))

    println()
    tripRepository.searchTripBySourceDestination(searchQuery = SearchQuery(query = "Lucknow"))

    tripRepository.searchTripByDateRange(searchQuery = SearchQuery(dateRange = listOf(LocalDateTime.now().plusMinutes(5),
        LocalDateTime.now().plusDays(2))))

    println()
    val result = tripRepository.joinTrip(id = 1)
    println("joined trip successfully: ${result.joinedMembersList}")

    println()
    tripRepository.addTripToFavouritesById(id = 2)
    tripRepository.addTripToFavouritesById(id = 3)

    println()
    val favTrips = tripRepository.getFavouriteTrips()
    println("favTrips = listSize = ${favTrips.size} || $favTrips")

    println()
    tripRepository.removeTripFromFavourites(2)
    val favTrips2 = tripRepository.getFavouriteTrips()
    println("favTrips2 = listSize = ${favTrips2.size} || $favTrips2")

    tripRepository.deleteTrip(4)
}