package com.aysoft.onionproject.dist.rest.sales;

import com.aysoft.onionproject.application.module.sales.OrderFacade;
import com.aysoft.onionproject.dist.rest.commons.CatalogController;
import com.aysoft.onionproject.domain.module.sales.contract.to.OrderCriteriaTO;
import com.aysoft.onionproject.domain.module.sales.contract.to.OrderTO;
import com.aysoft.onionproject.infrastructure.seedwork.web.Controller;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import static java.util.List.of;

@RestController
@RequestMapping("/orders")
public class OrderController extends Controller<OrderFacade> {

    @PostMapping
    public ResponseEntity create(@RequestBody OrderTO order) {
        return this.create(facade -> facade.addOrder(order));
    }

    @PatchMapping("/{id}")
    public ResponseEntity update(@PathVariable int id, @RequestBody OrderTO order) {
        return this.perform(facade -> facade.updateOrder(order.setId(id)));
    }

    @GetMapping
    public ResponseEntity search(OrderCriteriaTO criteria) {
        return this.getPagedList(facade -> facade.searchOrders(criteria));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable int id) {
        return this.get(facade -> facade.loadOrder(id), this::getLinks);
    }

    //<editor-fold desc="Support methods">
    private List<Link> getLinks(OrderTO order) {
        return of(linkTo(methodOn(CatalogController.class).get("order-states", order.getState())).withRel("state"));
    }
    //</editor-fold>
}
