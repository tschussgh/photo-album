package com.anderson.techshowcase.photoalbum.controller

import com.anderson.techshowcase.photoalbum.data.Photo
import com.anderson.techshowcase.photoalbum.reader.PhotoReader
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
class AlbumController {
    PhotoReader reader

    AlbumController(PhotoReader reader) {
        assert reader
        this.reader = reader
    }

    @GetMapping(value = "/albums/{albumId}")
    @ResponseBody List<Photo> retrieveAlbum(@PathVariable Integer albumId) {
        List<Photo> results = reader.retrievePhotosByAlbumId(albumId)
        if (results) {
            results
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST)
        }
    }
}
