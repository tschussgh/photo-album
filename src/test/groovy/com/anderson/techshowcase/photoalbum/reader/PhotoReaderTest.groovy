package com.anderson.techshowcase.photoalbum.reader


import com.anderson.techshowcase.photoalbum.config.TestConfig
import com.anderson.techshowcase.photoalbum.data.Photo
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.test.web.client.MockRestServiceServer

import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo
import static org.springframework.test.web.client.response.MockRestResponseCreators.withBadRequest
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess

@Import(TestConfig)
@RestClientTest(PhotoReader)
class PhotoReaderTest {
    @Autowired PhotoReader photoReader
    @Autowired ObjectMapper objectMapper
    @Autowired MockRestServiceServer server

    Integer albumId = -1
    Integer id = -1
    String title = 'Title'
    URI url = new URI('https://via.placeholder.com/600/92c952')
    URI thumbnailUrl = new URI('https://via.placeholder.com/600/92c952')
    String albumUrl = "https://jsonplaceholder.typicode.com/photos?albumId=${albumId}"

    @Test
    void retrievePhotosByAlbumId_Happy_Path() {
        String photoJson = objectMapper.writeValueAsString([new Photo(albumId: albumId, id: id, title: title, url: url, thumbnailUrl: thumbnailUrl)])

        this.server.expect(requestTo(albumUrl))
                .andRespond(withSuccess(photoJson, MediaType.APPLICATION_JSON))
        def photos = photoReader.retrievePhotosByAlbumId(albumId)
        assert photos
        assert photos[0].albumId == albumId
    }

    @Test
    void retrievePhotosByAlbumId_error() {
        this.server.expect(requestTo(albumUrl))
                .andRespond(withBadRequest())
        def photos = photoReader.retrievePhotosByAlbumId(albumId)
        assert photos.size() == 0
    }
}
