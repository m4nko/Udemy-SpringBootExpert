package io.github.m4nko.controller;

import io.github.m4nko.model.Cliente;
import io.github.m4nko.repository.ClienteRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/api/clientes")
public class ClienteController {

    private ClienteRepository clienteRepository;

    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @GetMapping("/{id}") // Equivalente ao @RequestMapping com método get
    //@ResponseBody
    public ResponseEntity<Cliente> getClienteById(@PathVariable Integer id){ // Parâmetro com anotação @RequestBody diz ao Spring para esperar no corpo da requisição um objeto Cliente (no formato JSON ou XML conforme parâmetro consume)
        Optional<Cliente> result = clienteRepository.findById(id);

        if(result.isPresent()) return ResponseEntity.ok(result.get());

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity saveCliente(@RequestBody Cliente cliente){
        Cliente result = clienteRepository.save(cliente);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCliente(@PathVariable Integer id){
            Optional<Cliente> result = clienteRepository.findById(id);

            if(result.isPresent()){
                clienteRepository.delete(result.get());
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}") // Qualquer dado enviado como nulo será atualizado para nulo na base (Update integral)
    @ResponseBody
    public ResponseEntity updateCliente(@PathVariable Integer id, @RequestBody Cliente cliente){
        return clienteRepository
                .findById(id)
                .map(clienteExistente -> {
                    cliente.setId(clienteExistente.getId());
                    clienteRepository.save(cliente);
                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}


