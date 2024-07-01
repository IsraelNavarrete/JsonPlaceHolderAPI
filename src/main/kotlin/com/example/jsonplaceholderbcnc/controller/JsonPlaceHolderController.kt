package com.example.jsonplaceholderbcnc.controller

import com.example.jsonplaceholderbcnc.api.JsonPlaceHolderApi
import com.example.jsonplaceholderbcnc.model.Album
import lombok.extern.slf4j.Slf4j
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController


@RestController
@Slf4j
class JsonPlaceHolderController : JsonPlaceHolderApi {
    /**
     * @return A list of Albums with details about each photo inside an album
     */
    override fun listAlbum(): ResponseEntity<Album> {
        return ResponseEntity.ok(Album(1,1,"", mutableListOf()))
    }
}