package com.studyverse.controllers;

import com.studyverse.dto.HistoryDTO;
import com.studyverse.exceptions.HistoryNotFoundException;
import com.studyverse.models.History;
import com.studyverse.services.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/history")
public class HistoryController {

    @Autowired
    private HistoryService historyService;

    // Endpoint pentru obținerea tuturor istoricului
    @GetMapping
    public List<History> getAllHistory() {
        return historyService.findAll();
    }

    // Endpoint pentru crearea unui nou istoric
    @PostMapping
    public History createHistory(@RequestBody @Valid HistoryDTO historyDTO) {
        History history = new History();
        history.setUserId(historyDTO.getUserId());
        history.setLessonId(historyDTO.getLessonId());
        history.setQuizId(historyDTO.getQuizId());

        return historyService.save(history);
    }

    // Endpoint pentru obținerea istoricului unui utilizator
    @GetMapping("/{id}")
    public History getHistoryById(@PathVariable Long id) {
        return historyService.findById(id).orElseThrow(() -> new HistoryNotFoundException("History not found with id " + id));
    }

    // Endpoint pentru actualizarea unui istoric
    @PutMapping("/{id}")
    public History updateHistory(@PathVariable Long id, @RequestBody @Valid HistoryDTO historyDTO) {
        History history = historyService.findById(id).orElseThrow(() -> new HistoryNotFoundException("History not found with id " + id));
        history.setUserId(historyDTO.getUserId());
        history.setLessonId(historyDTO.getLessonId());
        history.setQuizId(historyDTO.getQuizId());

        return historyService.save(history);
    }

    // Endpoint pentru ștergerea unui istoric
    @DeleteMapping("/{id}")
    public void deleteHistory(@PathVariable Long id) {
        historyService.deleteById(id);
    }
}
