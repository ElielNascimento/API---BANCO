package com.example.apibanco.movimentacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movimentacao")
public class MovimentacaoController {

    @Autowired
    private MovimentacaoService movimentacaoService;

    @GetMapping
    public List<Movimentacao> buscarTodos() {
        return movimentacaoService.buscarMovimentacao();
    }

    @GetMapping("/{id}")
    private Movimentacao buscarfuncId(@PathVariable Long id) {
        return movimentacaoService.buscarPeloId(id);
    }

    @PostMapping
    private Movimentacao addFunco(@RequestBody Movimentacao Movimentacao) {
        return movimentacaoService.adicionarMovimentacao(Movimentacao);
    }

    @PostMapping("/{contaId}/{valor}/deposito")
    private Movimentacao deposito(@PathVariable Long contaId, @PathVariable double valor) {
        return movimentacaoService.deposito(contaId, valor);
    }

    @PostMapping("/{contaId}/{valor}/saque")
    public Movimentacao saque(@PathVariable Long contaId, @PathVariable double valor) {
        return movimentacaoService.saque(contaId, valor);

    }

    @PostMapping("/{contaEnviada}/{contaRecebida}/{valor}/transferencia")
    public Movimentacao transferenciaEntrada(@PathVariable Long contaEnviada, @PathVariable Long contaRecebida, @PathVariable double valor) {
        return movimentacaoService.transferenciaEntrada(contaEnviada, contaRecebida, valor);
    }
}
