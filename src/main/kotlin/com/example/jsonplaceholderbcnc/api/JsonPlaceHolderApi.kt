package com.example.jsonplaceholderbcnc.api

import com.example.jsonplaceholderbcnc.model.Album
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping


@RequestMapping("v1/jsonplaceholder")
fun interface JsonPlaceHolderApi {

    @GetMapping(value = ["/album"], produces = ["application/json;charset=utf-8"])
    fun listAlbum(): ResponseEntity<List<Album>>
}