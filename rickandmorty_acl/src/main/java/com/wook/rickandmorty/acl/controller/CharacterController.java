package com.wook.rickandmorty.acl.controller;

import com.wook.rickandmorty.acl.config.LoggerConfiguration;
import com.wook.rickandmorty.acl.model.CharacterResponse;
import com.wook.rickandmorty.acl.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    private LoggerConfiguration logger = LoggerConfiguration.getLogger("CharacterController");

    @GetMapping("/character/{id}")
    public CharacterResponse getCharacter(@PathVariable("id") int id) {
        logger.info("getCharacter id = '" + id + "'");
        return characterService.callCharacter(id);
    }
}
