package com.dataiku.millennium.core;


import com.dataiku.millennium.db.entity.Route;
import com.dataiku.millennium.db.repository.RouteRepository;
import com.dataiku.millennium.model.EmpireContext;
import com.dataiku.millennium.model.config.MillenniumConfig;
import jakarta.annotation.PostConstruct;

import java.util.List;
import java.util.logging.Logger;

public class GraphService {
    private static final Logger LOG = Logger.getLogger(GraphService.class.getName());

    private final Graph graph;
    private final RouteRepository routeRepository;

    /**
     * GraphService is the core service that is called to used to
     * @param millenniumConfig
     * @param routeRepository
     */
    public GraphService(MillenniumConfig millenniumConfig, RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
        this.graph = new Graph(millenniumConfig.getGraphProperties());
        LOG.info("Initialized GraphService with config: " + millenniumConfig);
    }

    /**
     * Traverses though a graph given a context of bounty hunters and a countdown.
     * @param context
     * @return probability of success truncated to an integer.
     */
    public int traverse(EmpireContext context) {
        return graph.traverse(context);
    }

    // Spring automagically calls this method right after the beans are initialized.
    @PostConstruct
    private void initializeGraph() {
        graph.initializeGraph(getRoutes());
    }

    private List<Route> getRoutes() {
        return routeRepository.findAll().stream().toList();
    }
}
