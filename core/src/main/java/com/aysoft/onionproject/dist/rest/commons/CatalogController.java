package com.aysoft.onionproject.dist.rest.commons;

import com.aysoft.onionproject.infrastructure.seedwork.service.repository.domain.AbstractEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//TODO Catalog Service, entitymanager repository
@RestController
@RequestMapping("/catalogs")
public class CatalogController {

    @GetMapping("/{name}")
    public ResponseEntity get(@PathVariable String name) {
        return null;
    }

    @GetMapping("/{name}/{id}")
    public ResponseEntity get(@PathVariable String name, @PathVariable int id) {
        return null;
    }

    private <E extends AbstractEntity> Class<E> getClass(String name) {
        switch (name) {
            default:
                return null;
        }
    }
}
