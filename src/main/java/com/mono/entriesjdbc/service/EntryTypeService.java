package com.mono.entriesjdbc.service;

import com.mono.entriesjdbc.error.CustomException;
import com.mono.entriesjdbc.model.EntryType;
import com.mono.entriesjdbc.repository.EntryTypesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntryTypeService {
    private final EntryTypesRepository entryTypesRepository;

    @Autowired
    public EntryTypeService(EntryTypesRepository entryTypesRepository) {
        this.entryTypesRepository = entryTypesRepository;
    }

    public List<EntryType> getAll() {
        return entryTypesRepository.getAll();
    }

    public EntryType getByType(String type) {
        return entryTypesRepository.getByType(type).
                orElseThrow(() -> new CustomException("NOT_FOUND", "Entry type with type=" + type + " not found"));
    }
}
