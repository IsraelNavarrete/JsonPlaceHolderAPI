package com.example.jsonplaceholderbcnc.controller

import com.example.jsonplaceholderbcnc.model.Album
import com.example.jsonplaceholderbcnc.service.impl.AlbumServiceImpl
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.RequestBuilder
import org.springframework.test.web.servlet.get
import java.net.URI

@ExtendWith(SpringExtension::class)
@SpringBootTest
@AutoConfigureMockMvc
class JsonPlaceHolderControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    private val uri = URI("/v1/jsonplaceholder/album")

    @Test
    fun listAlbumOK(){

        mockMvc.get(uri).andExpect {
            status { isOk() }
            content { contentType(MediaType.APPLICATION_JSON_UTF8) }
        }

    }

    private fun getAlbumList(): List<Album> {

        return listOf(Album(1, 1, "title1", listOf()))

    }

}