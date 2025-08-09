package com.bancoacme.servicolistas.interfaceadapter.controller;

import com.bancoacme.servicolistas.domain.model.ItemLista;
import com.bancoacme.servicolistas.domain.model.Lista;
import com.bancoacme.servicolistas.domain.service.ListaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/listas")
@RequiredArgsConstructor
public class ListaController {

    private final ListaService listaService;

    @GetMapping
    @Operation(summary = "Listar todas as listas", description = "Retorna todas as listas cadastradas.")
    public ResponseEntity<List<Lista>> listarTodas() {
        return ResponseEntity.ok(listaService.listarTodas());
    }

    @Operation(summary = "Cria uma nova lista com schema dinâmico")
    @PostMapping
    public ResponseEntity<Lista> criarLista(
            @RequestBody Lista lista,
            @RequestHeader(value = "X-Correlation-Id", required = false)
            @Parameter(description = "ID de correlação para rastreio de requisições") String correlationId) {

        Lista criada = listaService.criarLista(lista);
        return ResponseEntity.created(URI.create("/listas/" + criada.getNome())).body(criada);
    }

    @Operation(summary = "Busca o schema de uma lista pelo nome")
    @GetMapping("/{nome}")
    public ResponseEntity<Lista> buscarLista(
            @PathVariable String nome,
            @RequestHeader(value = "X-Correlation-Id", required = false)
            @Parameter(description = "ID de correlação para rastreio de requisições") String correlationId) {
        return ResponseEntity.ok(listaService.buscarListaPorNome(nome));
    }

    @Operation(summary = "Adiciona um item a uma lista existente, validando com base no schema")
    @PostMapping("/{nome}/itens")
    public ResponseEntity<ItemLista> adicionarItem(
            @PathVariable String nome,
            @RequestBody Map<String, Object> dados,
            @RequestHeader(value = "X-Correlation-Id", required = false)
            @Parameter(description = "ID de correlação para rastreio de requisições") String correlationId) {

        return ResponseEntity.ok(listaService.adicionarItem(nome, dados));
    }

    @Operation(summary = "Busca itens de uma lista aplicando filtros nos campos do schema")
    @GetMapping("/{nome}/itens")
    public ResponseEntity<List<ItemLista>> buscarItens(
            @PathVariable String nome,
            @RequestParam Map<String, Object> filtros,
            @RequestHeader(value = "X-Correlation-Id", required = false)
            @Parameter(description = "ID de correlação para rastreio de requisições") String correlationId) {

        return ResponseEntity.ok(listaService.buscarItens(nome, filtros));
    }

    @Operation(summary = "Atualiza um item da lista com novos dados, respeitando o schema")
    @PatchMapping("/{nome}/itens/{idItem}")
    public ResponseEntity<ItemLista> atualizarItem(
            @PathVariable String nome,
            @PathVariable String idItem,
            @RequestBody Map<String, Object> dados,
            @RequestHeader(value = "X-Correlation-Id", required = false)
            @Parameter(description = "ID de correlação para rastreio de requisições") String correlationId) {

        return ResponseEntity.ok(listaService.atualizarItem(nome, idItem, dados));
    }

    @Operation(summary = "Remove um item de uma lista pelo seu ID")
    @DeleteMapping("/{nome}/itens/{idItem}")
    public ResponseEntity<Void> removerItem(
            @PathVariable String nome,
            @PathVariable String idItem,
            @RequestHeader(value = "X-Correlation-Id", required = false)
            @Parameter(description = "ID de correlação para rastreio de requisições") String correlationId) {

        listaService.removerItem(nome, idItem);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Verifica se um item com os dados informados já existe na lista")
    @PostMapping("/{nome}/verificar")
    public ResponseEntity<Boolean> verificarExistencia(
            @PathVariable String nome,
            @RequestBody Map<String, Object> dados,
            @RequestHeader(value = "X-Correlation-Id", required = false)
            @Parameter(description = "ID de correlação para rastreio de requisições") String correlationId) {

        boolean existe = listaService.verificarExistencia(nome, dados);
        return ResponseEntity.ok(existe);
    }
}
