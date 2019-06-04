package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }


    // TODO #1 - Create handler to process search request and display results
    @RequestMapping(value = "/results")
    public String searchColumnValues(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {
        model.addAttribute("columns", ListController.columnChoices);

        if (searchType.equals("all")) {
            ArrayList<HashMap<String, String>> results = JobData.findByValue(searchTerm);
            ArrayList<HashMap<String, String>> jobs = JobData.findAll();

            model.addAttribute("title", "All Jobs containing that term");
            model.addAttribute("jobs", jobs);
            model.addAttribute("results", results);
            return "search";
        }



       else {
           ArrayList<HashMap<String, String>> results = JobData.findByColumnAndValue(searchType, searchTerm);
           ArrayList<String> specificJobs = JobData.findAll(searchType);
                model.addAttribute("title", "All " + ListController.columnChoices.get(searchType) + " Values");
                model.addAttribute("searchType", searchType);
                model.addAttribute("specificJobs", specificJobs);
                model.addAttribute("results", results);
            return "search";
       }


    }
}

