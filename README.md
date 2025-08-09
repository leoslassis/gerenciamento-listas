# Serviço de Listas Dinâmicas

Este projeto implementa um microsserviço para gerenciamento de listas dinâmicas e seus respectivos itens, seguindo os princípios da Clean Architecture, utilizando Java 21, Spring Boot, MongoDB e Docker.

---

## ✨ Funcionalidades

- Criar listas com schema dinâmico
- Adicionar itens validados pelo schema
- Buscar, atualizar e remover itens com filtros dinâmicos
- Validação de tipos e campos obrigatórios
- Observabilidade (logs estruturados e tempo de execução)
- Integração com Docker Compose
- Importação pronta no Insomnia

---

## 📦 Arquitetura e Decisões Técnicas

### Clean Architecture
- Separação clara entre camadas de domínio, aplicação, infraestrutura e interfaces.
- Entidades de domínio puras (sem anotações do Spring).
- Repositórios implementados via mapeadores entre entidades de domínio e persistência.

### MongoDB com Spring Data
- Utilizado pela flexibilidade para armazenar schemas dinâmicos.
- Estrutura de listas: coleção `listas`
- Estrutura de itens: coleção `itens_lista`
- Campos indexáveis e consultas performáticas.

### Performance
- Filtros dinâmicos são aplicados diretamente via `Criteria` do MongoDB.
- O tempo de busca depende da criação de índices apropriados por campo (recomendado).
- Complexidade esperada: O(1) com índice, O(n) sem índice.

### Logs e Observabilidade
- Logs estruturados com SLF4J
- Correlation ID suportado com header `X-Correlation-ID`
- Anotações personalizadas (`@LogTempoExecucao`) para monitorar latência

### Tratamento de Erros
- `ValidacaoException`: quando falha validação de dados
- `ListaNaoEncontradaException`: para listas inexistentes
- Mapeamento via `@ControllerAdvice` com status apropriados (400, 404, 500)

### Docker Compose
- Serviço Java
- MongoDB
- Frontend Angular (se necessário)

### Frontend Angular (opcional)
- Interface com Angular + Angular Material
- Consumo de endpoints REST
- CRUD completo para listas e itens

---

## 📘 Requisitos Técnicos

- Java 21
- Maven
- MongoDB
- Docker e Docker Compose

---

## ▶️ Executar Localmente

```bash
# Subir MongoDB e serviço
cd servico-listas
./mvnw clean package
java -jar target/servico-listas.jar

# Ou via Docker Compose
cd servico-listas
docker-compose up --build
```

---

## 🧪 Insomnia
Importe o arquivo `servico-listas-insomnia.json` no Insomnia.

---

## 📁 Estrutura de Pacotes

```
com.bancoacme.servicolistas
├── api.controller
├── api.handler
├── application.usecase
├── domain.model
├── domain.exception
├── domain.service
├── infrastructure.repository
│   ├── entity
│   └── mapper
└── main
```

---

## 🧠 Justificativas Técnicas

| Decisão | Justificativa |
|--------|---------------|
| **MongoDB** | Permite schemas dinâmicos com performance, ideal para listas genéricas. |
| **Mapeamento via Mapper** | Garante isolamento entre domínio e infraestrutura (Clean Architecture). |
| **Validation via Entidade** | Responsabilidade da entidade de validar seus dados. |
| **Correlation ID** | Rastreabilidade entre serviços, ideal para uso futuro com orquestrador. |
| **Logs estruturados** | Para observabilidade e troubleshooting em produção. |
| **Spring Boot + Java 21** | Moderno, robusto, com suporte amplo e recursos como virtual threads (caso queira usar futuramente). |

---

## ✅ Próximos passos sugeridos

- Serviço de Motor de Decisão
- Serviço de Orquestrador Dinâmico
- Cache Redis para listas de leitura intensa
- Testes automatizados (unitários e de integração)
- Autenticação e autorização com JWT
