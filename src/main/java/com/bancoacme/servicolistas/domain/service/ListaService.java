package com.bancoacme.servicolistas.domain.service;

import com.bancoacme.servicolistas.domain.model.Lista;
import com.bancoacme.servicolistas.domain.model.ItemLista;
import com.bancoacme.servicolistas.domain.repository.ListaRepository;
import com.bancoacme.servicolistas.domain.repository.ItemListaRepository;
import com.bancoacme.servicolistas.domain.exception.ListaNaoEncontradaException;
import com.bancoacme.servicolistas.domain.exception.ValidacaoException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ListaService {

    private final ListaRepository listaRepository;
    private final ItemListaRepository itemListaRepository;

    public List<Lista> listarTodas() {
        log.info("Listando todas as listas");
        return listaRepository.listarTodas();
    }

    public Lista criarLista(Lista lista) {
        log.info("Criando nova lista: {}", lista.getNome());
        return listaRepository.salvar(lista);
    }

    public Lista buscarListaPorNome(String nome) {
        log.info("Buscando lista com nome: {}", nome);
        return listaRepository.buscarPorNome(nome)
                .orElseThrow(() -> new ListaNaoEncontradaException(nome));
    }

    public ItemLista adicionarItem(String nomeLista, Map<String, Object> dados) {
        log.info("Adicionando item na lista '{}': {}", nomeLista, dados);
        Lista lista = buscarListaPorNome(nomeLista);
        lista.validarDados(dados); // logs de validação e exception estão dentro da entidade
        return itemListaRepository.salvar(new ItemLista(null, nomeLista, dados));
    }

    public List<ItemLista> buscarItens(String nomeLista, Map<String, Object> filtros) {
        log.info("Buscando itens da lista '{}' com filtros: {}", nomeLista, filtros);
        return itemListaRepository.buscar(nomeLista, filtros);
    }

    public ItemLista atualizarItem(String nomeLista, String idItem, Map<String, Object> dadosAtualizados) {
        log.info("Atualizando item '{}' na lista '{}': {}", idItem, nomeLista, dadosAtualizados);
        Lista lista = buscarListaPorNome(nomeLista);
        lista.validarDados(dadosAtualizados);
        return itemListaRepository.atualizar(nomeLista, idItem, dadosAtualizados)
                .orElseThrow(() -> new ValidacaoException("Item não encontrado ou não pôde ser atualizado."));
    }

    public void removerItem(String nomeLista, String idItem) {
        log.info("Removendo item '{}' da lista '{}'", idItem, nomeLista);
        itemListaRepository.remover(nomeLista, idItem);
    }

    public boolean verificarExistencia(String nomeLista, Map<String, Object> dados) {
        log.info("Verificando existência do item na lista '{}': {}", nomeLista, dados);
        return itemListaRepository.verificarExistencia(nomeLista, dados);
    }
}
