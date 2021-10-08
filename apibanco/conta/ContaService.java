package com.example.apibanco.conta;

import com.example.apibanco.cliente.Cliente;
import com.example.apibanco.cliente.ClienteRepository;
import com.example.apibanco.enums.PessoaEnums;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Conta> buscarTodasContas() {
        return contaRepository.findAll();
    }

    public Conta buscarContaPeloId(Long id) {
        return contaRepository.findById(id).orElse(null);
    }

    public Conta adicionarConta(Conta conta) {
        return contaRepository.save(conta);
    }


    public Conta adicionarClientePf(Long clienteId, Conta conta) {
        contaRepository.save(conta);
        Cliente cliente = clienteRepository.getById(clienteId);
        cliente.setTipoPessoa(PessoaEnums.FISICA);
        cliente.getContaList().add(conta);
        clienteRepository.save(cliente);

        return conta;
    }

    public Conta adicionarClientePj(Long clienteId, Conta conta) {
        contaRepository.save(conta);
        Cliente cliente = clienteRepository.getById(clienteId);
        cliente.setTipoPessoa(PessoaEnums.JURIDICA);
        cliente.getContaList().add(conta);
        clienteRepository.save(cliente);

        return conta;
    }
}





