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

    @RequestMapping(value = "search")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results
    @RequestMapping(value = "search/results")
    public String searchColumnValues(Model model, @RequestParam String column, @RequestParam String searchTerm) {


        if (column.equals("all")) {
            ArrayList<HashMap<String, String>> results = new ArrayList<>();
            ArrayList<HashMap<String, String>> jobs = JobData.findAll();

            for (HashMap job : jobs) {
                if (job.containsValue(searchTerm)) {
                    results.add(job);
                    model.addAttribute("title", "All Jobs containing that term");
                    model.addAttribute("jobs", jobs);

                }
            }
            return "search";
        } else {
                    ArrayList<String> results = new ArrayList<>();
                    ArrayList<String> specificJobs = JobData.findAll(column);
                    for (String job : specificJobs) {
                        if (job.contains(searchTerm))  {
                            results.add(job);

                            model.addAttribute("title", "All " + ListController.columnChoices.get(column) + " Values");
                            model.addAttribute("column", column);
                            model.addAttribute("specificJobs", specificJobs);

                        }


                    }
                    return "search";
                }
    }
}
