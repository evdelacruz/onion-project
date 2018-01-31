package com.aysoft.onionproject.dist.rest.sales;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import static java.util.List.of;

import com.aysoft.onionproject.application.module.sales.OrderFacade;
import com.aysoft.onionproject.dist.rest.commons.CatalogController;
import com.aysoft.onionproject.domain.module.sales.contract.vo.OrderCriteriaTO;
import com.aysoft.onionproject.domain.module.sales.contract.vo.OrderTO;
import com.aysoft.onionproject.infrastructure.seedwork.web.Controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return this.wrapPagedList(facade -> facade.searchOrders(criteria), OrderTO::getId);
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable int id) {
        return this.wrap(
                facade -> facade.loadOrder(id),
                order -> of(
                        linkTo(methodOn(CatalogController.class).get("order-states", order.getState())).withRel("state")
                )
        );
    }
}
