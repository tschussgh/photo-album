package com.anderson.techshowcase.photoalbum.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.web.util.UriComponentsBuilder

@Configuration
@ConfigurationProperties(prefix = "album.url")
class AlbumProperties {
    String scheme
    String host
    String path
    String parm

    String getAlbumURI(Integer albumId){
        UriComponentsBuilder.newInstance()
                .scheme(scheme).host(host).path(path).queryParam(parm, albumId).toUriString()
    }
}
