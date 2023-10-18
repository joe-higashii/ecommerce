package br.com.serratec.ecommerce.service;

import java.util.List;

public interface CRUDService<Req, Res, ID> {

    List<Res> obterTodos();

    Res obterPorId(long id);

    Res adicionar(Req request);

    Res atualizar(long id, Req requestDto);

    void deletar(long id);
}
