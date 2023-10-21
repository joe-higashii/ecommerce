package br.com.serratec.ecommerce.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.ecommerce.dto.formaDePagamento.FormaDePagamentoRequestDTO;
import br.com.serratec.ecommerce.dto.formaDePagamento.FormaDePagamentoResponseDTO;
import br.com.serratec.ecommerce.model.FormaDePagamento;
import br.com.serratec.ecommerce.repository.FormaDePagamentoRepository;

@Service
public class FormaDePagamentoService {

    @Autowired
    private FormaDePagamentoRepository formaDePagamentoRepository;

    @Autowired
    private ModelMapper mapper;

    public List<FormaDePagamentoResponseDTO> obterTodos() {

        List<FormaDePagamento> formaDePagamentos = formaDePagamentoRepository.findAll();

        return formaDePagamentos
                .stream()
                .map(formaDePagamento -> mapper.map(formaDePagamento, FormaDePagamentoResponseDTO.class))
                .collect(Collectors.toList());
    }

    public FormaDePagamentoResponseDTO obterPorId(long id) {

        Optional<FormaDePagamento> optFormaPgt = formaDePagamentoRepository.findById(id);

        if (optFormaPgt.isEmpty()) {
            throw new RuntimeException("Nenhum registro encontrado para o ID: " + id);
        }

        return mapper.map(optFormaPgt.get(), FormaDePagamentoResponseDTO.class);
    }

    public FormaDePagamentoResponseDTO adicionar(FormaDePagamentoRequestDTO formaDePagamentoRequest) {

        FormaDePagamento formaDePagamento = mapper.map(formaDePagamentoRequest, FormaDePagamento.class);

        formaDePagamento.setPgtId((long) 0);

        formaDePagamento = formaDePagamentoRepository.save(formaDePagamento);

        return mapper.map(formaDePagamento, FormaDePagamentoResponseDTO.class);
    }

    public FormaDePagamentoResponseDTO atualizar(long id, FormaDePagamentoRequestDTO formaDePagamentoRequest) {

        // Se não lançar exception é porque o cara existe no banco.
        obterPorId(id);

        FormaDePagamento formaDePagamento = mapper.map(formaDePagamentoRequest, FormaDePagamento.class);

        formaDePagamento.setPgtId(id);

        formaDePagamento = formaDePagamentoRepository.save(formaDePagamento);

        return mapper.map(formaDePagamento, FormaDePagamentoResponseDTO.class);
    }

    public void deletar(Long id) {
        
        obterPorId(id);

        formaDePagamentoRepository.deleteById(id);
    }

}
