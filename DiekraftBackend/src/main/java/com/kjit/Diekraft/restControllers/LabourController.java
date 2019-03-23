package com.kjit.Diekraft.restControllers;

import com.kjit.Diekraft.dto.LabourDTO;
import com.kjit.Diekraft.service.LabourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LabourController {

    @Autowired
    private LabourService labourService;

    @PostMapping("/labour/create")
    public ResponseEntity<Void> createLabour(@RequestBody LabourDTO labourDTO){
        labourService.createOrUpdateLabour(labourDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/labour/{id}")
    public ResponseEntity<LabourDTO> getLabour(@PathVariable Long id){
        return new ResponseEntity<>(labourService.getLabourById(id),HttpStatus.OK);
    }

    @GetMapping("/labours")
    public ResponseEntity<List<LabourDTO>> getAllLabours(){
        return new ResponseEntity<>(labourService.getAllLabour(), HttpStatus.OK);
    }
}
