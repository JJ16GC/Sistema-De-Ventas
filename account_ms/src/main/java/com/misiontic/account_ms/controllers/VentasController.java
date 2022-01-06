package com.misiontic.account_ms.controllers;

import com.misiontic.account_ms.exceptions.AccountNotFoundException;
import com.misiontic.account_ms.models.Ventas;
import com.misiontic.account_ms.repositories.VentasRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class VentasController {

    private final VentasRepository ventasRepository;

    public VentasController(VentasRepository ventasRepository) {
        this.ventasRepository = ventasRepository;
    }

    @GetMapping("/ventas/{username}")
    Ventas getVentas(@PathVariable String username){
        return ventasRepository.findById(username)
                .orElseThrow(() -> new AccountNotFoundException("No se encontro una venta con el username: " + username));
    }

    @PostMapping("/ventas")
    Ventas newVentas(@RequestBody Ventas ventas){
        return ventasRepository.save(ventas);
    }


}
