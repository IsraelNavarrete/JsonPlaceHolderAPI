package com.example.jsonplaceholderbcnc.model

import org.springframework.http.HttpStatus

data class CustomException(override val message : String, val status : HttpStatus) : Exception()
