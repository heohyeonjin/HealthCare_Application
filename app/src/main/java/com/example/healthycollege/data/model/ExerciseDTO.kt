package com.example.healthycollege.data.model

data class ExerciseDTO (
    var id : Long,
    var name : String,
    var height : Long,
    var weight : Long,
    var walk : Long,
    var goal : Long,
    var gender : String,
    var totalRanking : Int,
    var majorRanking : Int
)