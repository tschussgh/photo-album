package com.anderson.techshowcase.photoalbum.config

import com.anderson.techshowcase.photoalbum.PhotoAlbumApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer

class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		application.sources(PhotoAlbumApplication)
	}

}
