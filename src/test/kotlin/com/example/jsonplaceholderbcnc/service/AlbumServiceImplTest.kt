package com.example.jsonplaceholderbcnc.service

import com.example.jsonplaceholderbcnc.model.Album
import com.example.jsonplaceholderbcnc.model.CustomException
import com.example.jsonplaceholderbcnc.model.Photo
import com.example.jsonplaceholderbcnc.service.impl.AlbumServiceImpl
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.function.Executable
import org.mockito.ArgumentMatchers
import org.mockito.InjectMocks
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate
import java.net.URI

@ExtendWith(MockitoExtension::class)
class AlbumServiceImplTest {

    @InjectMocks
    private lateinit var albumServiceImpl : AlbumServiceImpl

    private var restTemplate : RestTemplate = Mockito.mock(RestTemplate::class.java)

    @Test
    fun listAlbumOKTest(){

        Mockito.`when`(restTemplate.exchange(
            ArgumentMatchers.any(URI::class.java), ArgumentMatchers.any(HttpMethod::class.java)
        ,ArgumentMatchers.any(HttpEntity::class.java), ArgumentMatchers.eq(Array<Album>::class.java)))
            .thenReturn(getAlbumList())

        Mockito.`when`(restTemplate.exchange(
            ArgumentMatchers.any(URI::class.java), ArgumentMatchers.any(HttpMethod::class.java)
            ,ArgumentMatchers.any(HttpEntity::class.java), ArgumentMatchers.eq(Array<Photo>::class.java)))
            .thenReturn(getPhotoList())

        val result = albumServiceImpl.listAlbum()

        Assertions.assertNotNull(result)
        Assertions.assertTrue(result[0].title == "title1")
        Assertions.assertTrue(result[0].id == 1)
        result[0].photos?.let { Assertions.assertTrue(it.isNotEmpty()) }

    }

    @Test
    fun listPhotoNotFound(){

        Mockito.`when`(restTemplate.exchange(
            ArgumentMatchers.any(URI::class.java), ArgumentMatchers.any(HttpMethod::class.java)
            ,ArgumentMatchers.any(HttpEntity::class.java), ArgumentMatchers.eq(Array<Album>::class.java)))
            .thenReturn(getAlbumList())

        Mockito.`when`(restTemplate.exchange(
            ArgumentMatchers.any(URI::class.java), ArgumentMatchers.any(HttpMethod::class.java)
            ,ArgumentMatchers.any(HttpEntity::class.java), ArgumentMatchers.eq(Array<Photo>::class.java)))
            .thenReturn(ResponseEntity.notFound().build())

        Assertions.assertThrows(CustomException::class.java) { albumServiceImpl.listAlbum() }
    }

    @Test
    fun listPhotoError(){

        Mockito.`when`(restTemplate.exchange(
            ArgumentMatchers.any(URI::class.java), ArgumentMatchers.any(HttpMethod::class.java)
            ,ArgumentMatchers.any(HttpEntity::class.java), ArgumentMatchers.eq(Array<Album>::class.java)))
            .thenReturn(getAlbumList())

        Mockito.`when`(restTemplate.exchange(
            ArgumentMatchers.any(URI::class.java), ArgumentMatchers.any(HttpMethod::class.java)
            ,ArgumentMatchers.any(HttpEntity::class.java), ArgumentMatchers.eq(Array<Photo>::class.java)))
            .thenThrow(HttpClientErrorException::class.java)

        Assertions.assertThrows(CustomException::class.java) { albumServiceImpl.listAlbum() }
    }

    @Test
    fun listAlbumNotFound(){

        Mockito.`when`(restTemplate.exchange(
            ArgumentMatchers.any(URI::class.java), ArgumentMatchers.any(HttpMethod::class.java)
            ,ArgumentMatchers.any(HttpEntity::class.java), ArgumentMatchers.eq(Array<Album>::class.java)))
            .thenReturn(ResponseEntity.notFound().build())

        Assertions.assertThrows(CustomException::class.java) { albumServiceImpl.listAlbum() }
    }



    private fun getPhotoList(): ResponseEntity<Array<Photo>> {

        return ResponseEntity.ok(arrayOf(Photo(1, 1, "title1", "url", "thumbnail")))

    }

    private fun getAlbumList(): ResponseEntity<Array<Album>> {

       return ResponseEntity.ok(arrayOf(Album(1, 1, "title1", listOf())))

    }

}