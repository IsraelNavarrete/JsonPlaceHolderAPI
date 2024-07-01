package com.example.jsonplaceholderbcnc.util

import org.springframework.web.util.UriComponentsBuilder
import java.net.URI

object EndpointUtil {
    fun getEndpoint(endpoint: String): URI {
        return UriComponentsBuilder.fromUriString("https://jsonplaceholder.typicode.com$endpoint")
            .build().encode().toUri()
    }
}