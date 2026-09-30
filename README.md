# SmartBudget

API inteligente de orçamento financeiro desenvolvida com Spring Boot e Spring AI.

O projeto permite cadastrar e consultar despesas e utilizar Inteligência Artificial para interpretar perguntas financeiras. A aplicação também possui um fluxo completo de interação por voz, permitindo enviar um áudio, transcrevê-lo, consultar dados reais do banco, gerar uma resposta com IA e transformar essa resposta novamente em áudio.

---

## 🎯 Objetivo

Desenvolver uma API capaz de:

- Cadastrar despesas financeiras;
- Consultar despesas armazenadas no banco de dados;
- Interpretar perguntas utilizando Inteligência Artificial;
- Utilizar Tool Calling para consultar dados reais da aplicação;
- Receber comandos de voz;
- Converter áudio em texto;
- Gerar respostas utilizando um LLM local;
- Converter a resposta da IA novamente em áudio.

---

## 🏗️ Arquitetura

O projeto utiliza uma arquitetura organizada em camadas:

```text
src/main/java/com/augusto/smartbudget
│
├── application
│   └── usecase
│
├── domain
│   ├── entity
│   └── repository
│
├── infrastructure
│   ├── ai
│   └── persistence
│
└── interfaces
    └── controller