package com.aleksic.medapp.services;

import com.aleksic.medapp.models.HealthCheck;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class PaginationService {
    private Map<String, Object> convertEntitiesToMap(Page page) {
        long count = page.getTotalElements();
        int pageForMeta = page.getNumber() + 1;
        double countDivided = (double) count / page.getSize();
        int noPages = (int) Math.ceil(countDivided);

        HashMap<String, Object> newResponseMeta = new HashMap<>();
        newResponseMeta.put("count", count);
        newResponseMeta.put("page", pageForMeta);
        newResponseMeta.put("noPages", noPages);

        return newResponseMeta;
    }

    public Map<String, Object> getPagedEntites(Page pagedResult) {
        HashMap<String, Object> defaultMeta = new HashMap<>();

        if (pagedResult.hasContent()) {
            HashMap<String, Object> responseWithMeta = new HashMap<>();
            responseWithMeta.put("data", pagedResult.getContent());
            responseWithMeta.put("meta", convertEntitiesToMap(pagedResult));

            return responseWithMeta;
        } else {
            HashMap<String, Object> responseWithMeta = new HashMap<>();
            defaultMeta.put("count", 1);
            defaultMeta.put("page", 1);
            defaultMeta.put("noPages", 1);
            responseWithMeta.put("data", new ArrayList<HealthCheck>());
            responseWithMeta.put("meta", defaultMeta);

            return responseWithMeta;
        }
    }
}
