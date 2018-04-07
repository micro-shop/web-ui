package cz.microshop.webui.controller;

import cz.microshop.webui.model.HealthCheck;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class HealthCheckController {

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, path = "/health")
    @ResponseBody
    public Map<String, List<HealthCheck>> getHealth() {
        Map<String, List<HealthCheck>> map = new HashMap<>();
        List<HealthCheck> healthChecks = new ArrayList<>();
        Date dateNow = Calendar.getInstance().getTime();

        HealthCheck app = new HealthCheck("web-ui", "OK", dateNow);
        HealthCheck version = new HealthCheck("version", "0.0.2-SNAPSHOT", dateNow);
        healthChecks.add(app);
        healthChecks.add(version);

        map.put("health", healthChecks);
        return map;
    }
}