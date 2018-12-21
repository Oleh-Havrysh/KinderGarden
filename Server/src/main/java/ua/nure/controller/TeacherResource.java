package ua.nure.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.nure.model.Human;
import ua.nure.service.HumanService;
import ua.nure.util.HeaderUtil;
import ua.nure.util.PaginationUtil;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * REST controller for managing Teacher.
 */
@RestController
@RequestMapping("/teachers")
public class TeacherResource {

    private final Logger log = LoggerFactory.getLogger(TeacherResource.class);

    private static final String ENTITY_NAME = "human";

    private final HumanService humanService;

    public TeacherResource(HumanService humanService) {
        this.humanService = humanService;
    }

    /**
     * POST  /teachers : Create a new human.
     *
     * @param human the human to create
     * @return the ResponseEntity with status 201 (Created) and with body the new human, or with status 400 (Bad Request) if the human has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping
    public ResponseEntity<Human> createHuman(@RequestBody Human human) throws URISyntaxException {
        human.setRole(1);
        log.debug("REST request to save Human : {}", human);

        Human result = humanService.save(human);
        return ResponseEntity.created(new URI("/teachers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * PUT  /teachers : Updates an existing human.
     *
     * @param human the human to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated human,
     * or with status 400 (Bad Request) if the human is not valid,
     * or with status 500 (Internal Server Error) if the human couldn't be updated
     */
    @PutMapping
    public ResponseEntity<Human> updateHuman(@RequestBody Human human) {
        log.debug("REST request to update Human : {}", human);
        Human result = humanService.save(human);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, human.getId()))
            .body(result);
    }

    /**
     * GET  /teachers : get all the humans.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of humans in body
     */
    @GetMapping
    public ResponseEntity<List<Human>> getAllHumans(Pageable pageable) {
        log.debug("REST request to get a page of Humans");
        Page<Human> page = humanService.findAllTeachers(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/teachers");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /teachers/:id : get the "id" human.
     *
     * @param id the id of the human to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the human, or with status 404 (Not Found)
     */
    @GetMapping("/{id}")
    public ResponseEntity<Human> getHuman(@PathVariable String id) {
        log.debug("REST request to get Human : {}", id);
        return ResponseEntity.ok(humanService.findById(id));
    }

    /**
     * DELETE  /teachers/:id : delete the "id" human.
     *
     * @param id the id of the human to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHuman(@PathVariable String id) {
        log.debug("REST request to delete Human : {}", id);
        humanService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
