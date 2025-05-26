package org.example

class TripRepository {

    private val tripList: ArrayList<TripDetails> = ArrayList()

    fun getAllTrips() : List<TripDetails> = tripList

    fun createTrip(tripDetail: TripDetails){
        if (tripList.contains(tripDetail)){
            throw Exception("Trip already exists")
        }
        tripList.add(tripDetail)
    }

    fun deleteTrip(id: Int){
        val tripData = tripList.find { it.id == id }
        if (tripList.isNotEmpty()){
            val isSuccess = tripList.remove(tripData)
            println("Trip Deletion Success: $isSuccess")
        }
        else{
            throw Exception("No trips exists")
        }
    }

    fun searchTripBySourceDestination(searchQuery: SearchQuery){
        if (tripList.isNotEmpty()){
            val tripSearchResults = tripList.filter {
                it.destination.startsWith(searchQuery.query!!) || it.source == searchQuery.query
            }
            println("Search results for query = *${searchQuery.query}* are trips = $tripSearchResults")
        }else{
            throw Exception("No trips created yet!")
        }
    }

    fun searchTripByDateRange(searchQuery: SearchQuery){
        if (tripList.isNotEmpty()){
            val dateRange = searchQuery.dateRange
            if (dateRange?.size != 2){
                throw Exception("Date range is not valid")
            } else{
                val startDate = dateRange.get(0)
                val endDate = dateRange.get(1)

                val searchResults = tripList.filter {
                    it.startDate in startDate..endDate
                }
                println("Search results for dateRange = $dateRange are trips = $searchResults")
            }
        } else{
            throw Exception("No trips created yet!")
        }
    }

    fun joinTrip(id:Int) : TripDetails {
        val tripToJoinData = tripList.find {
            it.id == id
        }

        if (tripToJoinData?.slotsAvailable == 0){
            throw Exception("No slots available!")
        }

        if (tripToJoinData != null) {
            if (tripToJoinData.isJoinAllowed){
                tripToJoinData.joinedMembersList = listOf("memb1")
                return tripToJoinData
            }else{
                throw Exception("Trip Joining not allowed for $id")
            }
        }
        else{
            throw Exception("No trip with id $id")
        }
    }

    fun addTripToFavouritesById(id: Int){
        val trip = tripList.find {
            it.id == id
        }

        val idxOf = tripList.indexOfFirst { it.id == id }

        if (trip!=null){
            val newTripData = trip.copy(isFavouriteTrip = true)
            tripList.set(idxOf, newTripData)
        }
    }

    fun removeTripFromFavourites(id:Int){
        val tripInfo = tripList.find {
            it.id == id
        }

        val idxOf = tripList.indexOfFirst { it.id == id }

        val newTripInfo = tripInfo?.copy(isFavouriteTrip = false)
        if (newTripInfo != null) {
            tripList.set(idxOf, newTripInfo)
        }
    }

    fun getFavouriteTrips() : List<TripDetails>{
        val favTrips =  tripList.filter { it.isFavouriteTrip }
        return favTrips
    }
}