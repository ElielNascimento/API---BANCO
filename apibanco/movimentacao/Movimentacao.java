package com.example.apibanco.movimentacao;

import com.example.apibanco.enums.OperacaoEnums;

import javax.persistence.*;

@Entity
@Table(name = "movimentacao")
public class Movimentacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private OperacaoEnums operacao;

    private double valor;

    public Movimentacao() {
    }

    public Movimentacao(OperacaoEnums operacao, double valor) {
        this.operacao = operacao;
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public OperacaoEnums getOperacao() {
        return operacao;
    }

    public void setOperacao(OperacaoEnums operacao) {
        this.operacao = operacao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
