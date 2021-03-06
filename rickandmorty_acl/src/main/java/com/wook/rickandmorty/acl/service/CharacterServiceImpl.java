package com.wook.rickandmorty.acl.service;

import com.wook.rickandmorty.acl.config.LoggerConfiguration;
import com.wook.rickandmorty.acl.exception.CommunicationException;
import com.wook.rickandmorty.acl.exception.ObjectNotFoundException;
import com.wook.rickandmorty.acl.model.CharacterResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

@Service
public class CharacterServiceImpl implements CharacterService {

    private LoggerConfiguration logger = LoggerConfiguration.getLogger("CharacterServiceImpl");

    RestTemplate restTemplate = new RestTemplate();

    @Value("${app.endpoint.routers.character.url}")
    private String url;

    public CharacterResponse callCharacter (int idCharacter) {

        logger.info("callCharacter start");
        CharacterResponse character = new CharacterResponse();
        try {
            character = restTemplate.getForObject(url + idCharacter, CharacterResponse.class);
        } catch (RestClientResponseException e) {
            logger.error(e.getMessage());
            throw new ObjectNotFoundException("Character not found");
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new CommunicationException("Communication error");
        }

        return character;
    }
}
