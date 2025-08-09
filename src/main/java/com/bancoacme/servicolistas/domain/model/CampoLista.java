package com.bancoacme.servicolistas.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CampoLista {
    private String tipo;
    private boolean obrigatorio = false;
}