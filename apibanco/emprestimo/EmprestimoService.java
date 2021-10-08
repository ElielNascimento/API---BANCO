package com.example.apibanco.emprestimo;

import com.example.apibanco.conta.Conta;
import com.example.apibanco.conta.ContaRepository;
import com.example.apibanco.enums.EmprestimoEnums;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Service
public class EmprestimoService implements Serializable {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private ContaRepository contaRepository;

    public List<Emprestimo> buscarEmprestimos() {
        return emprestimoRepository.findAll();
    }

    public Emprestimo buscarPeloId(Long id) {
        return emprestimoRepository.findById(id).orElseThrow(()
                -> new RuntimeException("nao encontrado"));
    }

    public Emprestimo adicionarEmprestimo(Emprestimo emprestimo) {
        return emprestimoRepository.save(emprestimo);
    }

    public Emprestimo pegarEmprestimo(Long contaId, double valorEmprestimo) {
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setValorEmprestimo(valorEmprestimo);
        emprestimo.setEmprestimoEnums(EmprestimoEnums.ABERTO);
        emprestimoRepository.save(emprestimo);

        Conta conta = contaRepository.getById(contaId);
        conta.setSaldo(conta.getSaldo() + valorEmprestimo);
        conta.getEmprestimoList().add(emprestimo);
        contaRepository.save(conta);

        return emprestimo;
    }

    public Emprestimo pagarEmprestimo(Long contaId, Long emprestimoId) {

        Emprestimo emprestimo = emprestimoRepository.findById(emprestimoId).get();

        int diasAcumulados = diferencaDias(emprestimo.getDataEmprestimo(), new Date());

        if (emprestimo.getEmprestimoEnums() == EmprestimoEnums.PAGO) {
            throw new RuntimeException("ja foi pago");
        }
        emprestimo.setEmprestimoEnums(EmprestimoEnums.PAGO);
        emprestimo.setJuros(diasAcumulados * 2);
        emprestimoRepository.save(emprestimo);

        Conta conta = contaRepository.findById(contaId).get();
        conta.setSaldo(emprestimo.getValorEmprestimo() - emprestimo.getJuros());


        contaRepository.save(conta);

        return emprestimo;
    }

    public int diferencaDias(Date data, Date data1) {
        Calendar cal1 = new GregorianCalendar();
        Calendar cal2 = new GregorianCalendar();

        cal1.setTime(data);
        cal2.setTime(data1);

        return (int) ((data1.getTime() - data.getTime()) / (1000 * 60 * 60 * 24));
    }
}

