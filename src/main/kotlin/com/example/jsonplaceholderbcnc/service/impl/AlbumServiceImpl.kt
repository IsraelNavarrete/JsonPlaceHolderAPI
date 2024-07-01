package com.example.jsonplaceholderbcnc.service.impl

import com.example.jsonplaceholderbcnc.model.Album
import com.example.jsonplaceholderbcnc.model.CustomException
import com.example.jsonplaceholderbcnc.model.Photo
import com.example.jsonplaceholderbcnc.service.AlbumService
import com.example.jsonplaceholderbcnc.util.EndpointUtil
import com.example.jsonplaceholderbcnc.util.Endpoints
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.*
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.net.URI
import java.util.*


@Service
@Slf4j
class AlbumServiceImpl : AlbumService {
    @Autowired
    private lateinit var restTemplate: RestTemplate
    private val entity = HttpEntity<Unit>(HttpHeaders())

    /**
     * @return A list of aLbums with a detail of each photo from an album
     */
    //TODO falta añadir fotos
    @Throws(CustomException::class)
    override fun listAlbum(): List<Album> {

        val uri = EndpointUtil.getEndpoint(Endpoints.ALBUMS.name)

        val albums: ResponseEntity<Array<Album>> = restTemplate.exchange(
            uri, HttpMethod.GET, entity, Array<Album>::class.java
        )

        return if (albums.body != null) {
            albums.body!!.asList()
        } else {
            throw CustomException("There is no info on this endpoint: $uri", HttpStatus.NOT_FOUND)
        }
    }

    private val photos: List<Photo>
        get() {
            val uri: URI = EndpointUtil.getEndpoint(Endpoints.ALBUMS.name)
            val responseEntity = restTemplate.exchange(
                uri, HttpMethod.GET, entity, Array<Photo>::class.java
            )
            return if (responseEntity.body != null) {
                responseEntity.body!!.asList()
            } else {
                throw CustomException("There is no info on this endpoint: $uri", HttpStatus.NOT_FOUND)
            }
        }
}