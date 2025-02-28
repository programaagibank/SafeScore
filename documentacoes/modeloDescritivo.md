**MODELO DESCRITIVO**

Modelo Descritivo do Projeto (SafeScore): Análise de Risco de Crédito

Principais Etapas do Projeto:

1. Coleta de Dados:

A ferramenta iniciará com a entrada do CPF do cliente. A partir disso:

- Será feita a busca na tabela "usuário" para identificar o cliente.

Com essa identificação, serão recuperados diversos dados pessoais e financeiros, como:

1. Dados Pessoais:  
   1. Usuário:   
      1. CPF: Número único de identificação do usuário.  
      2. Nome: Nome completo do usuário.  
      3. Data de Nascimento: Data de nascimento (D/M/A) do usuário.  
      4. Dependentes:  Quantidade de dependentes financeiros do cliente.  
   2. Estado Civil:  
      1. Estado civil: Descrição do estado civil do usuário (solteiro, casado, divorciado etc.).  Casados podem ter mais estabilidade financeira, porém pode enfrentar riscos dependendo das condições do casamento (se houver separação, pode aumentar o risco).  
   3. Escolaridade:  
      1. Grau de escolaridade: Nível de educação formal do usuário.  
   4. Endereço:  
      1. CEP: Código postal do endereço.  
   5. Contrato Residencial:  
      1. Data inicial de moradia no endereço: Data de início do contrato residencial.  
      2. Data final de moradia endereço: Data de término do contrato residencial.  
   6. Tipo de contrato:  
      1.  Tipo de contrato: Tipo de contrato de moradia (aluguel, financiamento, posse etc.).  
   7. Emprego:  
      1. Salário: Média do salário mensal.  
      2. Data de início do emprego: Data de início do emprego atual.  
      3. Data final do emprego: Data de término do emprego (se aplicável).  
   8. Vínculo Profissional:  
      1. Vínculo profissional: Se é CLT, autônomo, aposentado, etc.  
           
2. Informações financeiras:  
   1. Patrimônio:  
      1. Montante de investimentos: Valor total investido pelo usuário em aplicações financeiras.  
      2. Montante de bens: Valor total dos bens físicos e imóveis do usuário.  
      3. Saldo do patrimônio: Saldo total disponível no patrimônio do usuário.  
   2. Histórico de Crédito:  
      1. Valor da parcela: Valor de cada parcela do crédito tomado pelo usuário.  
      2. Meses em atraso: Quantidade de meses que o usuário está em atraso com pagamentos.  
      3. Número de parcelas restantes: Número de parcelas restantes para quitar o crédito.  
      4. Valor restante do crédito: Valor total que ainda precisa ser pago pelo usuário.  
      5. Valor do crédito: Valor total do crédito adquirido pelo usuário.  
      6. Se está Inadimplente: Indica se o usuário está inadimplente.  
   3. Transações:  
      1. Se o salário está incluso:  Indica se nas transações, houve entrada de salário.  
      2. Data do recorte das transações: Data no formato (M/A) para cada transação.  
      3. Valores de entrada: Valor de entrada na conta do usuário.  
      4. Valores de saída: Valor de saída na conta do usuário.  
3. Informações internas do programa:  
   1. Acessos:   
      1. Número de acessos: A quantidade de acessos pelo score de um usuário específico, o que pode significar uma instabilidade.  
   2. Score:   
      1. Valor calculado do score: Armazenamento dos scores, que será calculado no primeiro acesso do dia para evitar gasto de processamento em eventuais acessos no mesmo dia.  
      2.   
4. Indicadores Econômicos:  
   1. Inflação: A inflação afeta diretamente o poder de compra e pode tornar mais difícil para o indivíduo pagar suas dívidas.  
   2. Taxa CAGED: A taxa de empregos formais pode indicar a saúde econômica e estabilidade no mercado de trabalho, impactando a capacidade de pagamento das dívidas.  
   3. Taxa Selic: A Selic afeta as taxas de juros, influenciando o custo do crédito e a capacidade de pagamento das dívidas. Altas taxas de juros podem aumentar a inadimplência.

2\. Cálculo do Score de Crédito:

Um algoritmo será desenvolvido para calcular o Score de Crédito com base nos dados coletados utilizando machine learning.

O Score de Crédito será calculado dentro de uma faixa numérica específica, de 0 a 1000\. O resultado determinará o risco associado à concessão de crédito ao cliente.

3\. Resultado do Score de Crédito:

O sistema exibirá o Score de Crédito do cliente. Juntamente com o score, o sistema fornecerá uma recomendação automatizada sobre a concessão de crédito, com base no score gerado:

1. "Risco baixo" \[701-1000\];  
2. "Risco moderado" \[401-700\];  
3. “Risco alto” \[0-400\].

O Score de Crédito será retornado ao funcionário do banco em tempo real, permitindo uma decisão rápida.

4. Principais Características do Sistema:

* Automatização do cálculo de risco de crédito: O sistema será capaz de calcular automaticamente o risco de crédito baseado em diferentes variáveis.  
* Algoritmos de análise de dados: O cálculo do Score de Crédito será realizado com base machine learning, programados em Java.  
* Resultados imediatos: O sistema fornecerá os resultados rapidamente, com feedback em tempo real sobre a análise de risco.

5. Tecnologias Utilizadas:

   

* Linguagem de Programação: Java será a principal linguagem de desenvolvimento do sistema.  
* Banco de Dados: Será utilizado o DBeaver e o MySQL para gerenciamento do banco de dados.  
* Versionamento e colaboração: GitHub para versionamento de código e colaboração entre desenvolvedores, proporcionando controle de versão eficiente, rastreamento de alterações e integração contínua.  
* Bibliotecas para Análise de Dados:  
*   Apache Commons Math para processamento matemático.  
*   Weka (caso seja necessário aplicar algoritmos de aprendizado de máquina).

6. Expectativas de Resultados:

   

* Eficiência na tomada de decisão: O sistema permitirá decisões rápidas sobre concessão de crédito, aumentando a eficiência do processo.  
* Precisão na análise de risco: O algoritmo desenvolvido em Java será capaz de calcular um Score de Crédito, com base nos dados fornecidos.  
* Facilidade de uso: A simplicidade do programa garantirá que os funcionários possam usar o sistema sem dificuldades, permitindo uma experiência intuitiva e sem erros.

