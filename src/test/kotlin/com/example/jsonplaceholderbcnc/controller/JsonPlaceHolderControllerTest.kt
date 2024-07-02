package com.example.jsonplaceholderbcnc.controller

import com.example.jsonplaceholderbcnc.model.Album
import com.example.jsonplaceholderbcnc.service.impl.AlbumServiceImpl
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class JsonPlaceHolderControllerTest {

    @InjectMocks
    private lateinit var jsonPlaceHolderController : JsonPlaceHolderController

    private val albumServiceImpl = Mockito.mock(AlbumServiceImpl::class.java)


    @Test
    fun listAlbumOK(){

        Mockito.`when`(albumServiceImpl.listAlbum()).thenReturn(getAlbumList())

        assertDoesNotThrow { jsonPlaceHolderController.listAlbum() }

    }

    private fun getAlbumList(): List<Album> {

        return listOf(Album(1, 1, "title1", listOf()))

    }

}