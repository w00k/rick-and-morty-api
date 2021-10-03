package com.wook.rickandmorty.acl.service;

import com.wook.rickandmorty.acl.config.LoggerConfiguration;
import com.wook.rickandmorty.acl.model.LocationResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LocationServiceImpl implements LocationService {

    private LoggerConfiguration logger = LoggerConfiguration.getLogger("LocationServiceImpl");

    RestTemplate restTemplate = new RestTemplate();

    public LocationResponse callLocation (String urlCharacter) {

        logger.info("callLocation start");
        LocationResponse location = new LocationResponse();
        try {
            location = restTemplate.getForObject(urlCharacter, LocationResponse.class);
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }

        return location;
    }
}
