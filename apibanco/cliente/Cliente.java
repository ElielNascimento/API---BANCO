package com.example.apibanco.cliente;

import com.example.apibanco.conta.Conta;
import com.example.apibanco.enums.PessoaEnums;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private PessoaEnums tipoPessoa;

    private String nomeEmpresa;

    @OneToMany
    private List<Conta> contaList;

    public Cliente() {
    }

    public Cliente(String nomeEmpresa, PessoaEnums tipoPessoa) {
        this.nomeEmpresa = nomeEmpresa;
        this.tipoPessoa = tipoPessoa;
    }

    public Long getId() {
        return id;
    }

    public PessoaEnums getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(PessoaEnums tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public List<Conta> getContaList() {
        return contaList;
    }

    public void setContaList(Conta conta) {
        this.contaList.add(conta);
    }
}
