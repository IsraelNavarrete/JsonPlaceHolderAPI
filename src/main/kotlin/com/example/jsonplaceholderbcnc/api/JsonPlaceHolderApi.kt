package com.example.jsonplaceholderbcnc.api

import com.example.jsonplaceholderbcnc.model.Album
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping


@Api(value = "JsonPlaceHolderApi", produces = "application/json;charset=utf-8", consumes = "")
@RequestMapping("v1/jsonplaceholder")
fun interface JsonPlaceHolderApi {
    @ApiOperation(
        value = "Retrieve a List of albums",
        notes = "This operation returns a List of Albums and details about photos related to each album",
        response = Album::class,
        responseContainer = "List",
        tags = ["Albums"]
    )
    @ApiResponses(
        value = [ApiResponse(
            code = 200,
            message = "Success",
            response = Album::class,
            responseContainer = "List"
        ), ApiResponse(
            code = 400,
            message = "Bad Request",
            response = Error::class,
            responseContainer = "List"
        ), ApiResponse(
            code = 200,
            message = "Success",
            response = Error::class,
            responseContainer = "List"
        ), ApiResponse(code = 200, message = "Success", response = Error::class, responseContainer = "List")]
    )
    @GetMapping(value = ["/album"], produces = ["application/json;charset=utf-8"])
    fun listAlbum(): ResponseEntity<List<Album>>
}