package yps.systems.ai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yps.systems.ai.model.Objective;
import yps.systems.ai.repository.IObjectiveRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/objectiveService")
public class ObjectiveController {

    private final IObjectiveRepository objectiveRepository;

    @Autowired
    public ObjectiveController(IObjectiveRepository objectiveRepository) {
        this.objectiveRepository = objectiveRepository;
    }

    @GetMapping
    ResponseEntity<List<Objective>> getAll() {
        return ResponseEntity.ok(objectiveRepository.findAll());
    }

    @GetMapping("/{elementId}")
    ResponseEntity<Objective> getByElementId(@PathVariable String elementId) {
        Optional<Objective> optionalObjective = objectiveRepository.findById(elementId);
        return optionalObjective.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    ResponseEntity<String> save(@RequestBody Objective objective) {
        Objective savedObjective = objectiveRepository.save(objective);
        return new ResponseEntity<>("Objective saved with ID: " + savedObjective.getElementId(), HttpStatus.CREATED);
    }

    @DeleteMapping("/{elementId}")
    ResponseEntity<String> delete(@PathVariable String elementId) {
        Optional<Objective> optionalObjective = objectiveRepository.findById(elementId);
        if (optionalObjective.isPresent()) {
            objectiveRepository.deleteById(elementId);
            return new ResponseEntity<>("Objective deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Objective not founded", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{elementId}")
    ResponseEntity<String> update(@PathVariable String elementId, @RequestBody Objective objective) {
        Optional<Objective> optionalObjective = objectiveRepository.findById(elementId);
        if (optionalObjective.isPresent()) {
            objective.setElementId(optionalObjective.get().getElementId());
            objectiveRepository.save(objective);
            return new ResponseEntity<>("Objective updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Objective not founded", HttpStatus.NOT_FOUND);
    }

}
