package com.example.jsonplaceholderbcnc.service.impl

import com.example.jsonplaceholderbcnc.model.Album
import com.example.jsonplaceholderbcnc.model.CustomException
import com.example.jsonplaceholderbcnc.model.Photo
import com.example.jsonplaceholderbcnc.service.AlbumService
import com.example.jsonplaceholderbcnc.util.EndpointUtil
import com.example.jsonplaceholderbcnc.util.Endpoints
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.*
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.net.URI
import java.util.*


@Service
class AlbumServiceImpl : AlbumService {

    @Autowired
    private lateinit var restTemplate: RestTemplate
    private val entity = HttpEntity<Unit>(HttpHeaders())

    private final val logger = LoggerFactory.getLogger(this::class.java)

    /**
     * @return A list of aLbums with a detail of each photo from an album
     */
    @Throws(CustomException::class)
    override fun listAlbum(): List<Album> {

        val uri = EndpointUtil.getEndpoint(Endpoints.ALBUMS.value)

        val response: ResponseEntity<Array<Album>> = restTemplate.exchange(
            uri, HttpMethod.GET, entity, Array<Album>::class.java
        )
        try {
            if (response.body != null) {
                val albums = response.body!!.asList().toMutableList()

                logger.info("Retrieved albums: $albums")

                return addPhotosInfo(albums, photos)

            } else {
                throw CustomException("There is no info on this endpoint: $uri", HttpStatus.NOT_FOUND)
            }
        } catch (e: java.lang.Exception) {
            logger.error("Unexpected error occurred while calling this endpoint: $uri, error: ${e.message}")
            throw CustomException("Unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR)
        }

    }

    /**
     * Recieves a list of albums and photos. Groups all photos by album's id and
     * then adds them to the album list of photos
     */
    private fun addPhotosInfo(albums: MutableList<Album>, photos: List<Photo>): List<Album> {

        val groupedPhotos = photos.groupBy { it.albumId }

        albums.forEach { album ->
            album.photos = groupedPhotos[album.id] ?: emptyList()
        }

        return albums

    }

    /**
     * Retrieves a list of photos from Json Placeholder
     */
    private val photos: List<Photo>
        get() {

            val uri: URI = EndpointUtil.getEndpoint(Endpoints.PHOTOS.value)

            try {
                val responseEntity = restTemplate.exchange(
                    uri, HttpMethod.GET, entity, Array<Photo>::class.java
                )

                if (responseEntity.body != null) {

                    val result = responseEntity.body!!.asList()

                    logger.info("Retrieved photos: $result")

                    return result
                } else {
                    throw CustomException("There is no info on this endpoint: $uri", HttpStatus.NOT_FOUND)
                }

            } catch (e: java.lang.Exception) {
                logger.error("Unexpected error occurred while calling this endpoint: $uri, error: ${e.message}")
                throw CustomException("Unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR)
            }
        }
}