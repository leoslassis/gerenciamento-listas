package com.bancoacme.servicolistas.infrastructure.repository;

import com.bancoacme.servicolistas.domain.model.Lista;
import com.bancoacme.servicolistas.domain.repository.ListaRepository;
import com.bancoacme.servicolistas.infrastructure.log.LogTempoExecucao;
import com.bancoacme.servicolistas.infrastructure.repository.entity.ListaMongo;
import com.bancoacme.servicolistas.infrastructure.repository.mapper.ListaMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ListaRepositoryImpl implements ListaRepository {

    private final ListaMongoRepository repository;

    public ListaRepositoryImpl(ListaMongoRepository repository) {
        this.repository = repository;
    }

    @LogTempoExecucao("CRIAR_LISTA_MONGO")
    @Override
    public Lista salvar(Lista lista) {
        return ListaMapper.toDomain(repository.save(ListaMapper.toMongo(lista)));
    }

    @LogTempoExecucao("BUSCAR_LISTA_POR_NOME_MONGO")
    @Override
    public Optional<Lista> buscarPorNome(String nome) {
        return repository.findById(nome).map(ListaMapper::toDomain);
    }

    @LogTempoExecucao("BUSCAR_TODAS_LISTAS_MONGO")
    @Override
    public List<Lista> listarTodas() {
        return repository.findAll().stream().map(ListaMapper::toDomain).toList();
    }
}
