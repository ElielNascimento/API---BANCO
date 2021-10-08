package com.example.apibanco.conta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/conta")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @GetMapping
    private List<Conta> getContas() {
        return contaService.buscarTodasContas();
    }

    @GetMapping("/{id}")
    private Conta getContaId(@PathVariable Long id) {
        return contaService.buscarContaPeloId(id);
    }

    @PostMapping
    private Conta postAdicionar(@RequestBody Conta conta) {
        return contaService.adicionarConta(conta);
    }

    @PostMapping("/{clienteId}/fisica")
    private Conta adicionarClienteFisica(@PathVariable Long clienteId, @RequestBody Conta conta) {
        return contaService.adicionarClientePf(clienteId, conta);
    }

    @PostMapping("/{clienteId}/juridica")
    private Conta adicionarClientePj(@PathVariable Long clienteId, @RequestBody Conta conta) {
        return contaService.adicionarClientePj(clienteId, conta);
    }
}
