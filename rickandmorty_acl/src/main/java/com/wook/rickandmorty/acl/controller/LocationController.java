package com.wook.rickandmorty.acl.controller;

import com.wook.rickandmorty.acl.config.LoggerConfiguration;
import com.wook.rickandmorty.acl.model.CharacterResponse;
import com.wook.rickandmorty.acl.model.LocationResponse;
import com.wook.rickandmorty.acl.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LocationController {

    @Autowired
    private LocationService locationService;

    private LoggerConfiguration logger = LoggerConfiguration.getLogger("LocationController");

    @GetMapping("/location/{id}")
    public LocationResponse getLocation(@PathVariable("id") int id) {
        logger.info("getLocation id = '" + id + "'");
        return locationService.callLocation(id);
    }
}
