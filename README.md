# Smart Budget API

Projeto de estudo: API inteligente de orçamento financeiro usando Spring Boot, Spring AI, ChatClient, Tool Calling, JPA/H2 e áudio.

## Tecnologias

- Java 21
- Spring Boot 4.0.8
- Spring AI 2.0.1
- Spring Web
- Spring Data JPA
- H2
- OpenAI

## Como executar

1. Tenha Java 21 e Maven instalados.
2. Configure a variável de ambiente `OPENAI_API_KEY`.
3. Execute:

```bash
mvn spring-boot:run
```

## Primeiro teste

Abra:

`GET http://localhost:8080/health`

Deve retornar:

`Smart Budget API funcionando!`

## Criar gasto

`POST http://localhost:8080/api/expenses`

JSON:

```json
{
  "description": "Supermercado",
  "amount": 150.90,
  "category": "Alimentação",
  "date": "2026-09-29"
}
```

## Listar gastos

`GET http://localhost:8080/api/expenses`

## Conversar com a IA

`POST http://localhost:8080/api/ai/chat`

JSON:

```json
{
  "message": "Quanto eu gastei nos últimos dias?"
}
```

A IA recebe a ferramenta `consultarGastos` e pode acioná-la quando a pergunta exigir dados reais da aplicação.

## Próximas etapas

- adicionar endpoint de upload de áudio;
- integrar transcrição com `TranscriptionModel`;
- enviar a transcrição para o `ChatClient`;
- converter a resposta em áudio com `TextToSpeechModel`;
- criar fluxo único `/api/voice`;
- adicionar testes;
- substituir H2 por PostgreSQL e migrations quando necessário.
