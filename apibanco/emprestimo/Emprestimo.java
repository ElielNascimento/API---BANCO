package com.example.apibanco.emprestimo;

import com.example.apibanco.enums.EmprestimoEnums;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "emprestimo")
public class Emprestimo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    private Date dataEmprestimo;

    private double valorEmprestimo;

    @Enumerated(EnumType.STRING)
    private EmprestimoEnums emprestimoEnums;

    private double juros;

    public Emprestimo() {
    }

    public Emprestimo(Date dataEmprestimo, double valorEmprestimo, EmprestimoEnums emprestimoEnums, double juros) {
        this.dataEmprestimo = dataEmprestimo;
        this.valorEmprestimo = valorEmprestimo;
        this.emprestimoEnums = emprestimoEnums;
        this.juros = juros;
    }

    public Long getId() {
        return id;
    }

    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public double getValorEmprestimo() {
        return valorEmprestimo;
    }

    public void setValorEmprestimo(double valorEmprestimo) {
        this.valorEmprestimo = valorEmprestimo;
    }

    public EmprestimoEnums getEmprestimoEnums() {
        return emprestimoEnums;
    }

    public void setEmprestimoEnums(EmprestimoEnums emprestimoEnums) {
        this.emprestimoEnums = emprestimoEnums;
    }

    public double getJuros() {
        return juros;
    }

    public void setJuros(double juros) {
        this.juros = juros;
    }
}
