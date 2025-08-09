package com.bancoacme.servicolistas.domain.model;

import com.bancoacme.servicolistas.domain.exception.ValidacaoException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class Lista {

    private String nome; // usado como ID
    private String descricao;
    private Map<String, CampoLista> schema = new HashMap<>();

    public void validarDados(Map<String, Object> dados) {
        Set<String> camposRecebidos = dados.keySet();

        for (Map.Entry<String, CampoLista> entry : schema.entrySet()) {
            String campo = entry.getKey();
            CampoLista definicao = entry.getValue();

            if (definicao.isObrigatorio() && !camposRecebidos.contains(campo)) {
                log.warn("Validação falhou: campo obrigatório '{}' ausente na lista '{}'", campo, nome);
                throw new ValidacaoException("Campo obrigatório ausente: " + campo);
            }

            if (camposRecebidos.contains(campo)) {
                Object valor = dados.get(campo);
                if (!tipoEhValido(valor, definicao.getTipo())) {
                    log.warn("Validação falhou: tipo inválido para campo '{}' na lista '{}'. Esperado: {} | Recebido: {}", campo, nome, definicao.getTipo(), valor);
                    throw new ValidacaoException("Tipo inválido para campo: " + campo);
                }
            }
        }
    }

    private boolean tipoEhValido(Object valor, String tipoEsperado) {
        if (valor == null) return true;

        return switch (tipoEsperado.toLowerCase()) {
            case "string" -> valor instanceof String;
            case "number" -> valor instanceof Number;
            case "boolean" -> valor instanceof Boolean;
            default -> true;
        };
    }
}