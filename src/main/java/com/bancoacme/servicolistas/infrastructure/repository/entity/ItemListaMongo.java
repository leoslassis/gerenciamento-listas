package com.bancoacme.servicolistas.infrastructure.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "itens_lista")
public class ItemListaMongo {

    @Id
    private String id;

    private String idLista;

    private Map<String, Object> dados;
}
