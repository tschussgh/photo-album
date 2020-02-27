package com.anderson.techshowcase.photoalbum.controller

import com.anderson.techshowcase.photoalbum.data.Photo
import com.anderson.techshowcase.photoalbum.reader.PhotoReader
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc

import static org.mockito.Mockito.when
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(AlbumController)
class AlbumControllerTest {

    @Autowired MockMvc mockMvc
    @MockBean PhotoReader photoReader

    @Test
    void happy_path() {
        when(photoReader.retrievePhotosByAlbumId(1)).thenReturn([new Photo()])

        mockMvc.perform(get('/albums/1').contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
    }

    @Test
    void no_data() {
        when(photoReader.retrievePhotosByAlbumId(-1)).thenReturn([])

        mockMvc.perform(get('/albums/-1').contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest())
    }
}