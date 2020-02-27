package com.anderson.techshowcase.photoalbum.data

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class Photo {
    Integer albumId
    Integer id
    String title
    URI url
    URI thumbnailUrl
}
