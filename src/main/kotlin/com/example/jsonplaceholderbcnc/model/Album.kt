package com.example.jsonplaceholderbcnc.model

data class Album(val userId : Int, val id : Int, val title: String, var list: MutableList<Photo>)

