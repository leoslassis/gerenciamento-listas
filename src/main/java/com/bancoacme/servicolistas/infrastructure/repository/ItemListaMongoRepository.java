package com.bancoacme.servicolistas.infrastructure.repository;

import com.bancoacme.servicolistas.infrastructure.repository.entity.ItemListaMongo;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ItemListaMongoRepository extends MongoRepository<ItemListaMongo, ObjectId> {
}
