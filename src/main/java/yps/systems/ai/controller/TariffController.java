package yps.systems.ai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yps.systems.ai.model.Tariff;
import yps.systems.ai.repository.ITariffRepository;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET})
@RestController
@RequestMapping("/tariffService")
public class TariffController {

    private final ITariffRepository tariffRepository;

    @Autowired
    public TariffController(ITariffRepository tariffRepository) {
        this.tariffRepository = tariffRepository;
    }

    @GetMapping
    ResponseEntity<List<Tariff>> getAll() {
        return ResponseEntity.ok(tariffRepository.findAll());
    }

    @GetMapping("/{elementId}")
    ResponseEntity<Tariff> getByElementId(@PathVariable String elementId) {
        Optional<Tariff> optionalTariff = tariffRepository.findById(elementId);
        return optionalTariff.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    ResponseEntity<String> save(@RequestBody Tariff tariff) {
        Tariff savedTariff = tariffRepository.save(Tariff);
        return new ResponseEntity<>("Tariff saved with ID: " + savedTariff.getElementId(), HttpStatus.CREATED);
    }

    @DeleteMapping("/{elementId}")
    ResponseEntity<String> delete(@PathVariable String elementId) {
        Optional<Tariff> optionalTariff = tariffRepository.findById(elementId);
        if (optionalTariff.isPresent()) {
            tariffRepository.deleteById(elementId);
            return new ResponseEntity<>("Tariff deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Tariff not founded", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{elementId}")
    ResponseEntity<String> update(@PathVariable String elementId, @RequestBody Tariff tariff) {
        Optional<Tariff> optionalTariff = tariffRepository.findById(elementId);
        if (optionalTariff.isPresent()) {
            tariff.setElementId(optionalTariff.get().getElementId());
            tariffRepository.save(tariff);
            return new ResponseEntity<>("Tariff updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Tariff not founded", HttpStatus.NOT_FOUND);
    }

}
