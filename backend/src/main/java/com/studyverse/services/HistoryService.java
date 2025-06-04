package com.studyverse.services;

import com.studyverse.models.History;
import com.studyverse.repositories.HistoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HistoryService {
    
    @Autowired
    private HistoryRepository historyRepository;

    public List<History> findAll() {
        return historyRepository.findAll();
    }

    public Optional<History> findById(Long id) {
        return historyRepository.findById(id);
    }

    public History save(History history) {
        return historyRepository.save(history);
    }

    public void deleteById(Long id) {
        historyRepository.deleteById(id);
    }
}
