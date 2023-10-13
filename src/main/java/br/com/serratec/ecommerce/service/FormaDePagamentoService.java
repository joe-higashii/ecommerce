package br.com.serratec.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.ecommerce.model.FormaDePagamento;
import br.com.serratec.ecommerce.repository.FormaDePagamentoRepository;

@Service
public class FormaDePagamentoService {
    
    @Autowired
    private FormaDePagamentoRepository formaDePagamentoRepository;

    public List<FormaDePagamento> obterTodos(){
        return formaDePagamentoRepository.findAll();
    }

    public FormaDePagamento obterPorId(long id){
        Optional<FormaDePagamento> optFormaPgt = formaDePagamentoRepository.findById(id);

        if(optFormaPgt.isEmpty()){
            throw new RuntimeException("Nenhum registro encontrado para o ID: " + id);
        }

        return optFormaPgt.get();
    }

    public FormaDePagamento adicionar(FormaDePagamento formaDePagamento){
        formaDePagamento.setPgtId((long) 0);
        return formaDePagamentoRepository.save(formaDePagamento);
    }

    public FormaDePagamento atualizar(long id, FormaDePagamento formaDePagamento){

        // Se não lançar exception é porque o cara existe no banco.
        obterPorId(id);

        formaDePagamento.setPgtId(id);
        return formaDePagamentoRepository.save(formaDePagamento);
    }

    public void deletar(Long id){
        obterPorId(id);

        formaDePagamentoRepository.deleteById(id);
    }

}
