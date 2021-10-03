package com.wook.rickandmorty.acl.controller;

import com.wook.rickandmorty.acl.config.LoggerConfiguration;
import com.wook.rickandmorty.acl.model.CharacterResponse;
import com.wook.rickandmorty.acl.model.LocationRequest;
import com.wook.rickandmorty.acl.model.LocationResponse;
import com.wook.rickandmorty.acl.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LocationController {

    @Autowired
    private LocationService locationService;

    private LoggerConfiguration logger = LoggerConfiguration.getLogger("LocationController");

    @PostMapping("/location")
    public LocationResponse getLocation(@RequestBody LocationRequest url) {
        logger.info("getLocation start, url = '" + url.getUrl() + "'");
        return locationService.callLocation(url.getUrl());
    }
}
