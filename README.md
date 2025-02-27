# **SafeScore**

### Grupo:
> - Angelo Scarpetta
> - Mateus Santis
> - Natalia Pereira
> - Rita Braga
> - Rodrigo Secco

[Link da apresentação](https://www.canva.com/design/DAGfjV1RWsU/nKGBeEqB3O0EstHfblp6ig/view?utm_content=DAGfjV1RWsU&utm_campaign=designshare&utm_medium=link2&utm_source=uniquelinks&utlId=h1a22001f43#1) da primeira entrega (explicação conceitual do projeto)

## Modelo Descritivo

* Objetivo do Projeto
> Desenvolver um programa de **Análise de Risco de Crédito**, com o objetivo de calcular o Score de Crédito de clientes. Esse score ajudará os funcionários do Agibank a tomar decisões rápidas e precisas sobre a concessão de crédito.

* Descrição do sistema

> O sistema será uma aplicação que recebe um identificador único do cidadão, baseando-se nisso realiza o cálculo automatizado do Score de Crédito a partir de dados financeiros e pessoais do cliente.Por fim, retornando o *Score* e uma recomendação perante a decisão de aprovação de crédito.

### Principais etapas do projeto

1. **Coleta de Dados**:
> A ferramenta iniciará com a entrada do CPF do cliente.
> 
> A partir disso:
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





