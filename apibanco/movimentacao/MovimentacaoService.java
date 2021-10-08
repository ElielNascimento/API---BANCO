package com.example.apibanco.movimentacao;

import com.example.apibanco.conta.Conta;
import com.example.apibanco.conta.ContaRepository;
import com.example.apibanco.enums.OperacaoEnums;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovimentacaoService {

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    @Autowired
    private ContaRepository contaRepository;

    public List<Movimentacao> buscarMovimentacao() {
        return movimentacaoRepository.findAll();
    }

    public Movimentacao buscarPeloId(Long id) {
        return movimentacaoRepository.findById(id).orElseThrow(()
                -> new RuntimeException("Não encontrado"));
    }

    public Movimentacao adicionarMovimentacao(Movimentacao dados) {
        return movimentacaoRepository.save(dados);
    }

    public Movimentacao deposito(Long contaId, double valor) {
        Movimentacao movimentacao = new Movimentacao();
        movimentacao.setOperacao(OperacaoEnums.DEPOSITO);
        movimentacao.setValor(valor);
        movimentacaoRepository.save(movimentacao);

        Conta conta = contaRepository.getById(contaId);
        conta.setSaldo(conta.getSaldo() + valor);
        conta.getMovimentacaoList().add(movimentacao);

        contaRepository.save(conta);
        return movimentacao;
    }

    public Movimentacao saque(Long contaId, double valor) {
        Movimentacao movimentacao = new Movimentacao();
        movimentacao.setOperacao(OperacaoEnums.SAQUE);
        movimentacao.setValor(valor);
        movimentacaoRepository.save(movimentacao);

        Conta conta = contaRepository.getById(contaId);
        conta.setSaldo(conta.getSaldo() - valor);
        conta.getMovimentacaoList().add(movimentacao);
        contaRepository.save(conta);
        return movimentacao;

    }

    public Movimentacao transferenciaEntrada(Long contaEnviada, Long contaRecebida, double valor) {
        Conta conta = contaRepository.findById(contaEnviada).get();
        Conta conta1 = contaRepository.findById(contaRecebida).get();

        if (conta.getSaldo() < valor) {
            throw new RuntimeException("saldo insuficiente");
        }

        Movimentacao movimentacao = new Movimentacao();
        movimentacao.setOperacao(OperacaoEnums.TRANSFERENCIA);
        movimentacao.setValor(valor * -1);
        movimentacaoRepository.save(movimentacao);


        conta.setSaldo(conta.getSaldo() - valor);
        conta.getMovimentacaoList().add(movimentacao);
        contaRepository.save(conta);

        Movimentacao movimentacao1 = new Movimentacao();
        movimentacao1.setOperacao(OperacaoEnums.TRANSFERENCIA);
        movimentacao1.setValor(valor);
        movimentacaoRepository.save(movimentacao1);


        conta1.setSaldo(conta1.getSaldo() + valor);
        conta1.getMovimentacaoList().add(movimentacao1);
        contaRepository.save(conta1);


        return movimentacao;

    }
}
