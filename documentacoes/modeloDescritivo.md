## Modelo Descritivo

* Objetivo do Projeto
> Desenvolver um programa de **Análise de Risco de Crédito**, com o objetivo de calcular o Score de Crédito de clientes. Esse score ajudará os funcionários do Agibank a tomar decisões rápidas e precisas sobre a concessão de crédito.

* Descrição do sistema

> O sistema será uma aplicação que recebe um identificador único do cidadão, baseando-se nisso realiza o cálculo automatizado do Score de Crédito a partir de dados financeiros e pessoais do cliente.Por fim, retornando o *Score* e uma recomendação perante a decisão de aprovação de crédito.

### Principais etapas do projeto

**Coleta de Dados**:
   
A ferramenta iniciará com a entrada do CPF do cliente.

A partir disso:
> * Será feita a busca na tabela "usuário" para identificar o cliente.
> * Com essa identificação, serão recuperados diversos dados pessoais e financeiros, como:
  
a. *Dados Pessoais*:

> * Idade (Data de nascimento): Faixa etária do solicitante.
> * Estado civil: Casado, solteiro, divorciado, etc.
> * Número de dependentes: Quantidade de dependentes financeiros do cliente.
> * Estado Civil: Casados podem ter mais estabilidade financeira, porém pode enfrentar riscos dependendo das condições do casamento (se houver separação, pode aumentar o risco).
> * Grau de escolaridade: Nível de educação formal do solicitante.
> * Endereço: Frequentemente mudar de residência pode indicar instabilidade financeira. A estabilidade de residência pode ser vista como um bom sinal.
> * Patrimônio: Em alguns casos, o banco pode exigir garantias para concessão de crédito, como imóveis, veículos ou outros bens. A presença de garantias pode reduzir o risco para o banco e facilitar a aprovação do crédito.

b. *Dados de emprego e renda*:

> * Renda mensal: Quanto o cliente recebe mensalmente.
> * Tempo no emprego atual: Quanto tempo o cliente está no emprego atual.
> * Tipo de contrato de trabalho: Se é CLT, autônomo, freelancer, etc.
> * Estabilidade profissional: Indicadores de estabilidade ou mudança frequente de emprego.

c. *Dados de Crédito*:

> * Histórico de crédito: Se o cliente já teve empréstimos ou financiamentos anteriores.
> * Score de crédito: Pontuação fornecida por agências de crédito que avalia o risco de inadimplência do cliente.
> * Número de consultas de crédito: Frequência com que o cliente tem solicitado crédito recentemente.

d. *Dados de Endividamento*:

> * Relação dívida/renda: Percentual da renda comprometido com dívidas.
> * Parcelas em atraso: Quantidade de parcelas de dívidas anteriores que o cliente deixou de pagar.
> * Empréstimos pendentes: Número de empréstimos ou financiamentos que o cliente possui em aberto.

e. *Comportamento de Pagamento*:

> * Histórico de inadimplência: Quantidade de vezes que o cliente ficou inadimplente (com atrasos significativos).
> * Frequência de pagamentos atrasados: Número de vezes que o cliente pagou suas dívidas com atraso.
> * Quantidade de renegociações: Casos em que o cliente precisou renegociar suas dívidas devido à inadimplência.

f. *Indicadores Econômicos*:

> * Inflação: A inflação afeta diretamente o poder de compra e pode tornar mais difícil para o indivíduo pagar suas dívidas.
> * Taxa CAGED: A taxa de empregos formais pode indicar a saúde econômica e estabilidade no mercado de trabalho, impactando a capacidade de pagamento das dívidas.
> * Taxa Selic: A Selic afeta as taxas de juros, influenciando o custo do crédito e a capacidade de pagamento das dívidas. Altas taxas de juros podem aumentar a inadimplência.