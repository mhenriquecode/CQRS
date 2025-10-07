package org.com.cqrsmonolith.query.queryController;

import org.com.cqrsmonolith.dto.ProductDTO;
import org.com.cqrsmonolith.query.queryService.QueryService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class QueryController {

    private final QueryService queryService;

    public QueryController(QueryService queryService) {
        this.queryService = queryService;
    }

    @QueryMapping
    public List<ProductDTO> products() {
        return queryService.findAll();
    }

    @QueryMapping
    public ProductDTO productById(@Argument long id) {
        return queryService.findById(id);
    }
}
