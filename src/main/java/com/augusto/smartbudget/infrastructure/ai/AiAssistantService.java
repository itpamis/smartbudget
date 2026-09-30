package com.augusto.smartbudget.infrastructure.ai;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class AiAssistantService {

    private final ChatClient chatClient;
    private final FinancialTools financialTools;

    public AiAssistantService(
            ChatClient.Builder builder,
            FinancialTools financialTools) {

        this.chatClient = builder.build();
        this.financialTools = financialTools;
    }

    public String ask(String question) {

        return chatClient.prompt()
                .system("""
                        Você é o assistente financeiro do Smart Budget.

                        Responda sempre em português do Brasil.

                        Você possui acesso a uma ferramenta chamada consultarGastos.

                        REGRAS IMPORTANTES:

                        1. Sempre que o usuário perguntar sobre gastos,
                           despesas, valores gastos ou orçamento,
                           USE a ferramenta consultarGastos.

                        2. Nunca invente valores financeiros.

                        3. Nunca estime valores financeiros.

                        4. Use exatamente os valores retornados pela ferramenta.

                        5. A ferramenta recebe duas datas:
                           inicio e fim.

                        6. Quando o usuário informar um período,
                           converta as datas para o formato correto.

                        7. Depois de receber o resultado da ferramenta,
                           responda de forma objetiva em português.

                        8. Se a pergunta não for financeira,
                           responda normalmente.
                        """)
                .user(question)
                .tools(financialTools)
                .call()
                .content();
    }
}