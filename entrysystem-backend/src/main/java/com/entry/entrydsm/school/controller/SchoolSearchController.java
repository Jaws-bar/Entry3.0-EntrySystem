package com.entry.entrydsm.school.controller;

import com.entry.entrydsm.common.response.RestResponse;
import com.entry.entrydsm.school.domain.School;
import com.entry.entrydsm.school.domain.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/schools/search")
public class SchoolSearchController {

    @Autowired
    private SchoolRepository schoolRepository;

    @GetMapping
    public RestResponse<List<School>> searchSchool(@RequestParam(name = "government", defaultValue = "") String government,
                                                   @RequestParam(name = "name", defaultValue = "") String name) {
        return RestResponse.success(schoolRepository.findByGovernmentContainingAndNameContaining(government, name));
    }
}
