package com.example.jsonplaceholderbcnc.service

import com.example.jsonplaceholderbcnc.model.Album

fun interface AlbumService {
    fun listAlbum(): List<Album>

}