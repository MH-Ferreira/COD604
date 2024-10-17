package com.restaurante.almoco;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("restaurante")
public class RestauranteController {

	@Autowired
    private RestauranteRepository restauranteRepository;

    @PostMapping
    public ResponseEntity<Restaurante> criarRestaurante(@RequestBody Restaurante restaurante) {
        Restaurante novoRestaurante = restauranteRepository.save(restaurante);
        return ResponseEntity.status(201).body(novoRestaurante);
    }

    @GetMapping
    public List<Restaurante> listarRestaurantes() {
        return restauranteRepository.findAll();
    }
    
    @PutMapping("/{put}")
    public ResponseEntity<Restaurante> atualizarRestaurante(@PathVariable Long id, @RequestBody Restaurante restauranteAtualizado) {
        Optional<Restaurante> restauranteOpt = restauranteRepository.findById(id);
        if (restauranteOpt.isPresent()) {
            Restaurante restaurante = restauranteOpt.get();
            // Atualiza os campos aqui
            restaurante.setNome(restauranteAtualizado.getNome());
            restaurante.setFibra(restauranteAtualizado.getFibra());
            restaurante.setProteina(restauranteAtualizado.getProteina());
            restaurante.setCarboidrato(restauranteAtualizado.getCarboidrato());
            restaurante.setGordura(restauranteAtualizado.getGordura());
            restaurante.setSobremesa(restauranteAtualizado.getSobremesa());
            restaurante.setBebida(restauranteAtualizado.getBebida());
            restauranteRepository.save(restaurante);
            return ResponseEntity.ok(restaurante);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    
    @DeleteMapping("/{delete}")
    public ResponseEntity<Void> deletarRestaurante(@PathVariable Long id) {
        if (restauranteRepository.existsById(id)) {
            restauranteRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
