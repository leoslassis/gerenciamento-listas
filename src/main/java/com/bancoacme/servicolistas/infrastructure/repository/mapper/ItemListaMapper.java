package com.bancoacme.servicolistas.infrastructure.repository.mapper;

import com.bancoacme.servicolistas.domain.model.ItemLista;
import com.bancoacme.servicolistas.infrastructure.repository.entity.ItemListaMongo;

public class ItemListaMapper {

    public static ItemListaMongo toMongo(ItemLista item) {
        return new ItemListaMongo(
                item.getId(),
                item.getIdLista(),
                item.getDados()
        );
    }

    public static ItemLista toDomain(ItemListaMongo mongo) {
        return new ItemLista(
                mongo.getId(),
                mongo.getIdLista(),
                mongo.getDados()
        );
    }
}