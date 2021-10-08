package com.example.apibanco.emprestimo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping("/api/emprestimo")
public class EmprestimoController implements Serializable {

    @Autowired
    private EmprestimoService emprestimoService;

    @GetMapping
    private List<Emprestimo> buscarTodosEmprestimos() {
        return emprestimoService.buscarEmprestimos();
    }

    @GetMapping("/{id}")
    private Emprestimo buscarPeloId(@PathVariable Long id) {
        return emprestimoService.buscarPeloId(id);
    }

    @PostMapping
    private Emprestimo adicionarEmprestimo(@RequestBody Emprestimo emprestimo) {
        return emprestimoService.adicionarEmprestimo(emprestimo);
    }

    @PostMapping("/{contaId}/{valorEmprestimo}/emprestimo")
    public Emprestimo emprestimo(@PathVariable Long contaId, @PathVariable double valorEmprestimo) {
        return emprestimoService.pegarEmprestimo(contaId, valorEmprestimo);
    }

    @PostMapping("/{contaId}/{emprestimoId}/pagar")
    public Emprestimo emprestimo(@PathVariable Long contaId, @PathVariable Long emprestimoId) {
        return emprestimoService.pagarEmprestimo(contaId, emprestimoId);
    }
}
