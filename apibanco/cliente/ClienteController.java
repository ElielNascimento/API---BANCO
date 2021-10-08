package com.example.apibanco.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    private List<Cliente> buscarTodosClienteCadastrados() {
        return clienteService.buscarTodosClientes();
    }

    @GetMapping("/{id}")
    private Cliente buscarClientePeloId(@PathVariable Long id) {
        return clienteService.buscarClientePeloId(id);
    }

    @PostMapping
    private Cliente adicionarClientes(@RequestBody Cliente cliente) {
        return clienteService.adicionarCliente(cliente);
    }

}
