package com.wook.rickandmorty.bbs.controller;

import com.wook.rickandmorty.bbs.model.RickAndMortyResponse;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;

@SwaggerDefinition(
        info = @Info(
                description = "Service how create a resume of two APIs",
                version = "V1.0.0",
                title = "Rick and Morty generador ",
                termsOfService = "https://github.com/w00k/rick-and-morty-api",
                contact = @Contact(
                        name = "w00k micro servicios",
                        email = "wook82@gmail.com",
                        url = "https://github.com/w00k/rick-and-morty-api"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://github.com/w00k/rick-and-morty-api/blob/main/LICENSE"
                )
        ),
        consumes = {"application/json"},
        produces = {"application/json"},
        schemes = {SwaggerDefinition.Scheme.HTTP, SwaggerDefinition.Scheme.HTTPS},
        tags = {
                @Tag(name = "Private", description = "tag descriptivo para pruebas")
        }
)
public interface IGenerateRickAndMortyController {

    public ResponseEntity<RickAndMortyResponse> generateOtp(RickAndMortyResponse request);
}
