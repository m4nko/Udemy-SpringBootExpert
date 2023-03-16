package io.github.m4nko;

import io.github.m4nko.model.Cliente;
import io.github.m4nko.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import java.lang.ref.Cleaner;
import java.util.List;

@SpringBootApplication
@RestController
public class VendasApplication {

    @Bean
    public CommandLineRunner init(@Autowired ClienteRepository clienteRepository){
        return args -> {
            Cliente cliente = new Cliente("Carlos");
            clienteRepository.salvar(cliente);
            Cliente cliente2 = new Cliente("Clara");
            clienteRepository.salvar(cliente2);
        };
    }
    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
