package com.aysoft.onionproject.dist.rest.access;

import com.aysoft.onionproject.application.module.access.RoleFacade;
import com.aysoft.onionproject.domain.module.access.contract.to.PermissionCriteriaTO;
import com.aysoft.onionproject.domain.module.access.contract.to.PermissionTO;
import com.aysoft.onionproject.infrastructure.seedwork.web.Controller;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import static java.util.List.of;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping("/permissions")
public class PermissionController extends Controller<RoleFacade> {
    private static final String LOCALIZATIONS_REL = "localizations";

    @GetMapping
    public ResponseEntity search(PermissionCriteriaTO criteria) {
        return this.getPagedList(facade -> facade.searchPermissions(criteria), this::getLinks);
    }

    @GetMapping("/{id}/localizations")
    public ResponseEntity getLocalizations(@PathVariable int id) {
        return this.get(facade -> facade.loadPermissionLocalizations(id));
    }

    //<editor-fold desc="Support methods">
    private List<Link> getLinks(PermissionTO permission) {
        return of(linkTo(this.getClass()).slash(permission.getId()).slash(LOCALIZATIONS_REL).withRel(LOCALIZATIONS_REL));
    }
    //</editor-fold>
}
