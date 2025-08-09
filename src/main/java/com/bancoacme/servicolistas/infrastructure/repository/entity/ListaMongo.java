package com.bancoacme.servicolistas.infrastructure.repository.entity;

import com.bancoacme.servicolistas.domain.model.CampoLista;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "listas")
public class ListaMongo {

    @Id
    private String nome;

    private String descricao;

    private Map<String, CampoLista> schema;
}