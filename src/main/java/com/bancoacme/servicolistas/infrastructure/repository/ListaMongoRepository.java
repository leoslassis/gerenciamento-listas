package com.bancoacme.servicolistas.infrastructure.repository;

import com.bancoacme.servicolistas.infrastructure.repository.entity.ListaMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ListaMongoRepository extends MongoRepository<ListaMongo, String> {
    // Nome é o _id
}
