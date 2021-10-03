package com.wook.rickandmorty.bbs.service;

import com.wook.rickandmorty.bbs.config.LoggerConfiguration;
import com.wook.rickandmorty.bbs.model.*;
import com.wook.rickandmorty.bbs.util.Util;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RickAndMortyServiceImpl implements RickAndMortyService {

    private LoggerConfiguration logger = LoggerConfiguration.getLogger("RickAndMortyServiceImpl");

    RestTemplate restTemplate = new RestTemplate();

    @Value("${app.endpoint.routers.character.url}")
    private String character;

    @Value("${app.endpoint.routers.location.url}")
    private String location;

    public RickAndMortyResponse callAclService(int idCharacter) {

        logger.info("callAclService start");

        CharacterResponse characterResponse = new CharacterResponse();
        LocationResponse locationResponse = new LocationResponse();
        LocationRequest locationRequest = new LocationRequest();
        Util util = new Util();

        try {
            logger.info("call ACL CharacterService");
            characterResponse = restTemplate.getForObject(character + idCharacter, CharacterResponse.class);

            //get URL for Location
            locationRequest.setUrl(characterResponse.getLocation().getUrl().toString());

            if (!util.validUrl(locationRequest.getUrl())) {
                logger.error("URL Location is not valid");
                new Exception("URL not valid!!!");
            }

            logger.info("call ACL LocationService");
            locationResponse = restTemplate.postForObject(location, locationRequest, LocationResponse.class);

        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }

        return rickAndMortyResponseObject(characterResponse, locationResponse);
    }

    public RickAndMortyResponse rickAndMortyResponseObject(CharacterResponse characterResponse, LocationResponse locationResponse) {

        logger.info("rickAndMortyResponseObject start");

        RickAndMortyResponse rickAndMortyResponse = new RickAndMortyResponse();
        Origin origin = new Origin();

        //set Origin object
        if (locationResponse != null) {
            origin.setName(locationResponse.getName());
            origin.setUrl(locationResponse.getUrl());
            origin.setDimension(locationResponse.getDimension());
            origin.setResidents(locationResponse.getResidents());
        }

        //set RickAndMortyResponse object
        rickAndMortyResponse.setId(characterResponse.getId());
        rickAndMortyResponse.setName(characterResponse.getName());
        rickAndMortyResponse.setStatus(characterResponse.getStatus());
        rickAndMortyResponse.setSpecies(characterResponse.getSpecies());
        rickAndMortyResponse.setType(characterResponse.getType());
        rickAndMortyResponse.setEpisodeCount(String.valueOf(characterResponse.getEpisode().size()));
        rickAndMortyResponse.setOrigin(origin);

        return rickAndMortyResponse;
    }
}
