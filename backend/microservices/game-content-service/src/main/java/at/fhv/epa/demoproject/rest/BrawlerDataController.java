package at.fhv.epa.demoproject.rest;


import at.fhv.epa.demoproject.domain.model.BrawlerData;
import at.fhv.epa.demoproject.service.BrawlerDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brawlers")
public class BrawlerDataController {
    private final BrawlerDataService brawlerService;

    public BrawlerDataController(BrawlerDataService brawlerService) {
        this.brawlerService = brawlerService;
    }

    @GetMapping
    public List<BrawlerData> getAll() {
        return brawlerService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrawlerData> getById(@PathVariable String id) {
        return brawlerService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BrawlerData> create(@RequestBody BrawlerData data) {
        return ResponseEntity.ok(brawlerService.save(data));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BrawlerData> update(@PathVariable String id, @RequestBody BrawlerData data) {
        data.setId(id);
        return ResponseEntity.ok(brawlerService.save(data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        brawlerService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
