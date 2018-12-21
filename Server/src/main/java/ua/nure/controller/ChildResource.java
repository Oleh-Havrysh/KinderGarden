package ua.nure.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.nure.model.Child;
import ua.nure.service.ChildService;
import ua.nure.util.HeaderUtil;
import ua.nure.util.PaginationUtil;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * REST controller for managing Child.
 */
@RestController
@RequestMapping("/children")
public class ChildResource {

    private final Logger log = LoggerFactory.getLogger(ChildResource.class);

    private static final String ENTITY_NAME = "child";

    private final ChildService childService;

    public ChildResource(ChildService childService) {
        this.childService = childService;
    }

    /**
     * POST  /children : Create a new child.
     *
     * @param child the child to create
     * @return the ResponseEntity with status 201 (Created) and with body the new child, or with status 400 (Bad Request) if the child has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping
    public ResponseEntity<Child> createChild(@RequestBody Child child) throws URISyntaxException {
        log.debug("REST request to save Child : {}", child);
        Child result = childService.save(child);

        return ResponseEntity.created(new URI("/children/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * PUT  /children : Updates an existing child.
     *
     * @param child the child to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated child,
     * or with status 400 (Bad Request) if the child is not valid,
     * or with status 500 (Internal Server Error) if the child couldn't be updated
     */
    @PutMapping
    public ResponseEntity<Child> updateChild(@RequestBody Child child) {
        log.debug("REST request to update Child : {}", child);
        Child result = childService.save(child);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, child.getId()))
            .body(result);
    }

    /**
     * GET  /children : get all the children.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of children in body
     */
    @GetMapping
    public ResponseEntity<List<Child>> getAllChildren(Pageable pageable) {
        log.debug("REST request to get a page of Children");
        Page<Child> page = childService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/children");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * DELETE  /children/:id : delete the "id" child.
     *
     * @param id the id of the child to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChild(@PathVariable String id) {
        log.debug("REST request to delete Child : {}", id);
        childService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
