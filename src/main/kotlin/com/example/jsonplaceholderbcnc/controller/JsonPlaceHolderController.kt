package com.example.jsonplaceholderbcnc.controller

import com.example.jsonplaceholderbcnc.api.JsonPlaceHolderApi
import com.example.jsonplaceholderbcnc.model.Album
import com.example.jsonplaceholderbcnc.service.AlbumService
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController


/**
 * Recieves all valid petitions to the microservice
 */
@RestController
@Slf4j
class JsonPlaceHolderController : JsonPlaceHolderApi {

    @Autowired
    private lateinit var albumService: AlbumService

    /**
     * @return A list of Albums with details about each photo inside an album
     */
    override fun listAlbum(): ResponseEntity<List<Album>> {
        return ResponseEntity.ok(albumService.listAlbum())
    }
}