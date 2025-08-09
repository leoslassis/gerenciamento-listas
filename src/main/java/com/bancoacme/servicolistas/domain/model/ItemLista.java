package com.bancoacme.servicolistas.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemLista {
    private String id;
    private String idLista;
    private Map<String, Object> dados;
}