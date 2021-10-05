package br.com.impacta.exercicioLab.controller;

import br.com.impacta.exercicioLab.dto.OrderServiceDTO;
import br.com.impacta.exercicioLab.exception.EntityNotFoundException;
import br.com.impacta.exercicioLab.exception.InvalidRegistryException;
import br.com.impacta.exercicioLab.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderServiceController {

    @Autowired
    private OrderService service;

    @GetMapping("/findById/{idOrder}")
    public ResponseEntity findById(@PathVariable("idOrder") int idOrder) throws EntityNotFoundException {
        OrderServiceDTO order = service.findById(idOrder);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody OrderServiceDTO order) {
        service.save(order);
        return new ResponseEntity<>("http://localhost:8080/findById/" + (service.getCurrentId() - 1),
                HttpStatus.ACCEPTED);
    }

    @PostMapping("/update/{idOrder}")
    public ResponseEntity<OrderServiceDTO> update(@PathVariable("idOrder") int idOrder, @RequestBody OrderServiceDTO order)
            throws EntityNotFoundException, InvalidRegistryException {
        service.update(idOrder, order);
        return new ResponseEntity<>(order, HttpStatus.ACCEPTED);
    }

    @GetMapping("/delete/{idOrder}")
    public ResponseEntity delete(@PathVariable("idOrder") int idOrder) {
        OrderServiceDTO order = service.delete(idOrder);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}
