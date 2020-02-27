package com.anderson.techshowcase.photoalbum.config

import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean

@TestConfiguration
class TestConfig {
    @Bean
    AlbumProperties albumProperties() {
        new AlbumProperties(scheme: 'https', host: 'jsonplaceholder.typicode.com', path: '/photos', parm: 'albumId')
    }
}
