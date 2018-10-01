package com.entry.entrydsm.document.controller;

import com.entry.entrydsm.common.config.AuthRequired;
import com.entry.entrydsm.common.response.RestResponse;
import com.entry.entrydsm.common.service.CalculationProxy;
import com.entry.entrydsm.document.domain.Document;
import com.entry.entrydsm.document.dto.DocumentDTO;
import com.entry.entrydsm.user.domain.User;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/me/document")
public class DocumentController {

    @GetMapping
    @AuthRequired(allowSubmitted = true)
    public RestResponse<Document> get(User user) {
        return RestResponse.success(user.getDocument());
    }

    @PutMapping
    @AuthRequired
    @Transactional
    public RestResponse<Document> put(User user, @Valid @RequestBody DocumentDTO documentDto) {
        Document document = user.getDocument();
        document.update(documentDto);
        CalculationProxy.calculateIfValid(user);
        return RestResponse.success(document);
    }
}
