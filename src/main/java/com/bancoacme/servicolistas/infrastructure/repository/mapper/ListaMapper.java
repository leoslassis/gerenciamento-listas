package com.bancoacme.servicolistas.infrastructure.repository.mapper;

import com.bancoacme.servicolistas.domain.model.Lista;
import com.bancoacme.servicolistas.infrastructure.repository.entity.ListaMongo;

public class ListaMapper {

    public static ListaMongo toMongo(Lista lista) {
        return new ListaMongo(
                lista.getNome(),
                lista.getDescricao(),
                lista.getSchema()
        );
    }

    public static Lista toDomain(ListaMongo mongo) {
        return new Lista(
                mongo.getNome(),
                mongo.getDescricao(),
                mongo.getSchema()
        );
    }
}