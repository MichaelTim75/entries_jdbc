package com.mono.entriesjdbc.controller;

import com.mono.entriesjdbc.model.EntryType;
import com.mono.entriesjdbc.service.EntryTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/entry_type")
public class EntryTypesController {
    private final EntryTypeService entryTypeService;

    @Autowired
    public EntryTypesController(EntryTypeService entryTypeService) {
        this.entryTypeService = entryTypeService;
    }

    @GetMapping
    public Iterable<EntryType> getAll(){
        return entryTypeService.getAll();
    }

    @GetMapping(value="/{type}")
    public ResponseEntity<EntryType> getByType(@PathVariable String type){
        EntryType entryType =entryTypeService.getByType(type);
        return new ResponseEntity<>(entryType, HttpStatus.OK);
    }
}
