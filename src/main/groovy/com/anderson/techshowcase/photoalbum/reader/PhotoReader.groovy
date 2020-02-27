package com.anderson.techshowcase.photoalbum.reader

import com.anderson.techshowcase.photoalbum.config.AlbumProperties
import com.anderson.techshowcase.photoalbum.data.Photo
import groovy.util.logging.Slf4j
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate

@Service
@Slf4j
class PhotoReader {
    RestTemplate restTemplate
    AlbumProperties albumProperties

    PhotoReader(RestTemplateBuilder restTemplateBuilder, AlbumProperties albumProperties){
        this.restTemplate = restTemplateBuilder.build()
        this.albumProperties = albumProperties
    }

    List<Photo> retrievePhotosByAlbumId(Integer albumId) {
        try {
            restTemplate.getForObject(albumProperties.getAlbumURI(albumId), Photo[])
        } catch (HttpClientErrorException e) {
            log.error('Error calling album', e)
            []
        }
    }
}
