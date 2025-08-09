package com.bancoacme.servicolistas.domain.repository;

import com.bancoacme.servicolistas.domain.model.ItemLista;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ItemListaRepository {
    ItemLista salvar(ItemLista item);
    List<ItemLista> buscar(String idLista, Map<String, Object> filtros);
    Optional<ItemLista> atualizar(String idLista, String idItem, Map<String, Object> dadosAtualizados);
    void remover(String idLista, String idItem);
    boolean verificarExistencia(String idLista, Map<String, Object> dados);
    Optional<ItemLista> buscarPorId(String id);
}