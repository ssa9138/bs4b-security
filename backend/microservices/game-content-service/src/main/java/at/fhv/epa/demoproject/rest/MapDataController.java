package at.fhv.epa.demoproject.rest;

import at.fhv.epa.demoproject.domain.model.MapData;
import at.fhv.epa.demoproject.service.MapDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/maps")
public class MapDataController {
    private final MapDataService mapService;

    public MapDataController(MapDataService mapService) {
        this.mapService = mapService;
    }

    @GetMapping
    public List<MapData> getAll() {
        return mapService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MapData> getById(@PathVariable String id) {
        return mapService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MapData> create(@RequestBody MapData data) {
        return ResponseEntity.ok(mapService.save(data));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MapData> update(@PathVariable String id, @RequestBody MapData data) {
        data.setId(id);
        return ResponseEntity.ok(mapService.save(data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        mapService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
