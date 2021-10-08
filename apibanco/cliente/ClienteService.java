package com.example.apibanco.cliente;

import com.example.apibanco.conta.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ContaRepository contaRepository;


    public List<Cliente> buscarTodosClientes() {
        return clienteRepository.findAll();
    }

    public Cliente buscarClientePeloId(Long id) {
        return clienteRepository.findById(id).get();
    }

    public Cliente adicionarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

}
