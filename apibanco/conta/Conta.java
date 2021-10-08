package com.example.apibanco.conta;


import com.example.apibanco.emprestimo.Emprestimo;
import com.example.apibanco.movimentacao.Movimentacao;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "conta")
public class Conta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer agencia;
    private Integer conta;
    private double saldo = 10;

    @OneToMany
    @JoinColumn(name = "conta_id")
    private List<Movimentacao> movimentacaoList;

    @OneToMany
    private List<Emprestimo> emprestimoList;


    public Conta() {
    }

    public Conta(Integer agencia, Integer conta, double saldo, List<Movimentacao> movimentacaoList) {
        this.agencia = agencia;
        this.conta = conta;
        this.saldo = saldo;
        this.movimentacaoList = movimentacaoList;
    }

    public void quitarJuros(Integer quitar) {

    }

    public Integer getAgencia() {
        return agencia;
    }

    public void setAgencia(Integer agencia) {
        this.agencia = agencia;
    }

    public Integer getConta() {
        return conta;
    }

    public void setConta(Integer conta) {
        this.conta = conta;
    }

    public Long getId() {
        return id;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public List<Movimentacao> getMovimentacaoList() {
        return movimentacaoList;
    }

    public void setMovimentacaoList(List<Movimentacao> movimentacaoList) {
        this.movimentacaoList = movimentacaoList;
    }

    public List<Emprestimo> getEmprestimoList() {
        return emprestimoList;
    }

    public void setEmprestimoList(List<Emprestimo> emprestimoList) {
        this.emprestimoList = emprestimoList;
    }


}

