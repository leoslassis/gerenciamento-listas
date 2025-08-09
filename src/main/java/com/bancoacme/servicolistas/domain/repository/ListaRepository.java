package com.bancoacme.servicolistas.domain.repository;

import com.bancoacme.servicolistas.domain.model.Lista;

import java.util.Optional;
import java.util.List;

public interface ListaRepository {
    Lista salvar(Lista lista);
    Optional<Lista> buscarPorNome(String nome);
    List<Lista> listarTodas();
}