package com.bancoacme.servicolistas.infrastructure.repository;

import com.bancoacme.servicolistas.domain.model.ItemLista;
import com.bancoacme.servicolistas.domain.repository.ItemListaRepository;
import com.bancoacme.servicolistas.infrastructure.log.LogTempoExecucao;
import com.bancoacme.servicolistas.infrastructure.repository.entity.ItemListaMongo;
import com.bancoacme.servicolistas.infrastructure.repository.mapper.ItemListaMapper;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ItemListaRepositoryImpl implements ItemListaRepository {

    private final ItemListaMongoRepository repository;
    private final MongoTemplate mongoTemplate;

    @LogTempoExecucao("CRIA_ITEM_LISTA_MONGO")
    @Override
    public ItemLista salvar(ItemLista item) {
        ItemListaMongo mongo = ItemListaMapper.toMongo(item);
        return ItemListaMapper.toDomain(repository.save(mongo));
    }

    @LogTempoExecucao("BUSCAR_ITENS_LISTA_MONGO")
    @Override
    public List<ItemLista> buscar(String idLista, Map<String, Object> filtros) {
        Query query = new Query();
        query.addCriteria(Criteria.where("idLista").is(idLista));

        filtros.forEach((chave, valor) ->
                query.addCriteria(Criteria.where("dados." + chave).is(valor))
        );

        return mongoTemplate.find(query, ItemListaMongo.class).stream()
                .map(ItemListaMapper::toDomain)
                .toList();
    }

    @LogTempoExecucao("ATUALIZAR_ITEM_LISTA_MONGO")
    @Override
    public Optional<ItemLista> atualizar(String idLista, String idItem, Map<String, Object> dadosAtualizados) {
        Optional<ItemListaMongo> existente = repository.findById(new ObjectId(idItem));

        if (existente.isEmpty() || !existente.get().getIdLista().equals(idLista)) {
            return Optional.empty();
        }

        ItemListaMongo atualizado = existente.get();
        atualizado.setDados(dadosAtualizados);
        return Optional.of(ItemListaMapper.toDomain(repository.save(atualizado)));
    }

    @LogTempoExecucao("REMOVER_ITEM_LISTA_MONGO")
    @Override
    public void remover(String idLista, String idItem) {
        Optional<ItemListaMongo> existente = repository.findById(new ObjectId(idItem));

        if (existente.isPresent() && existente.get().getIdLista().equals(idLista)) {
            repository.deleteById(new ObjectId(idItem));
        }
    }

    @LogTempoExecucao("VERIFICAR_ITEM_LISTA_MONGO")
    @Override
    public boolean verificarExistencia(String idLista, Map<String, Object> dados) {
        Query query = new Query();
        query.addCriteria(Criteria.where("idLista").is(idLista));

        dados.forEach((chave, valor) ->
                query.addCriteria(Criteria.where("dados." + chave).is(valor))
        );

        return mongoTemplate.exists(query, ItemListaMongo.class);
    }

    @LogTempoExecucao("BUSCAR_ITEM_LISTA_ID_MONGO")
    @Override
    public Optional<ItemLista> buscarPorId(String id) {
        return repository.findById(new ObjectId(id))
                .map(ItemListaMapper::toDomain);
    }
}
