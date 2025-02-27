## Modelo Descritivo

* Objetivo do Projeto
> Desenvolver um programa de **An�lise de Risco de Cr�dito**, com o objetivo de calcular o Score de Cr�dito de clientes. Esse score ajudar� os funcion�rios do Agibank a tomar decis�es r�pidas e precisas sobre a concess�o de cr�dito.

* Descri��o do sistema

> O sistema ser� uma aplica��o que recebe um identificador �nico do cidad�o, baseando-se nisso realiza o c�lculo automatizado do Score de Cr�dito a partir de dados financeiros e pessoais do cliente.Por fim, retornando o *Score* e uma recomenda��o perante a decis�o de aprova��o de cr�dito.

### Principais etapas do projeto

**Coleta de Dados**:
   
A ferramenta iniciar� com a entrada do CPF do cliente.

A partir disso:
> * Ser� feita a busca na tabela "usu�rio" para identificar o cliente.
> * Com essa identifica��o, ser�o recuperados diversos dados pessoais e financeiros, como:
  
a. *Dados Pessoais*:

> * Idade (Data de nascimento): Faixa et�ria do solicitante.
> * Estado civil: Casado, solteiro, divorciado, etc.
> * N�mero de dependentes: Quantidade de dependentes financeiros do cliente.
> * Estado Civil: Casados podem ter mais estabilidade financeira, por�m pode enfrentar riscos dependendo das condi��es do casamento (se houver separa��o, pode aumentar o risco).
> * Grau de escolaridade: N�vel de educa��o formal do solicitante.
> * Endere�o: Frequentemente mudar de resid�ncia pode indicar instabilidade financeira. A estabilidade de resid�ncia pode ser vista como um bom sinal.
> * Patrim�nio: Em alguns casos, o banco pode exigir garantias para concess�o de cr�dito, como im�veis, ve�culos ou outros bens. A presen�a de garantias pode reduzir o risco para o banco e facilitar a aprova��o do cr�dito.

b. *Dados de emprego e renda*:

> * Renda mensal: Quanto o cliente recebe mensalmente.
> * Tempo no emprego atual: Quanto tempo o cliente est� no emprego atual.
> * Tipo de contrato de trabalho: Se � CLT, aut�nomo, freelancer, etc.
> * Estabilidade profissional: Indicadores de estabilidade ou mudan�a frequente de emprego.

c. *Dados de Cr�dito*:

> * Hist�rico de cr�dito: Se o cliente j� teve empr�stimos ou financiamentos anteriores.
> * Score de cr�dito: Pontua��o fornecida por ag�ncias de cr�dito que avalia o risco de inadimpl�ncia do cliente.
> * N�mero de consultas de cr�dito: Frequ�ncia com que o cliente tem solicitado cr�dito recentemente.

d. *Dados de Endividamento*:

> * Rela��o d�vida/renda: Percentual da renda comprometido com d�vidas.
> * Parcelas em atraso: Quantidade de parcelas de d�vidas anteriores que o cliente deixou de pagar.
> * Empr�stimos pendentes: N�mero de empr�stimos ou financiamentos que o cliente possui em aberto.

e. *Comportamento de Pagamento*:

> * Hist�rico de inadimpl�ncia: Quantidade de vezes que o cliente ficou inadimplente (com atrasos significativos).
> * Frequ�ncia de pagamentos atrasados: N�mero de vezes que o cliente pagou suas d�vidas com atraso.
> * Quantidade de renegocia��es: Casos em que o cliente precisou renegociar suas d�vidas devido � inadimpl�ncia.

f. *Indicadores Econ�micos*:

> * Infla��o: A infla��o afeta diretamente o poder de compra e pode tornar mais dif�cil para o indiv�duo pagar suas d�vidas.
> * Taxa CAGED: A taxa de empregos formais pode indicar a sa�de econ�mica e estabilidade no mercado de trabalho, impactando a capacidade de pagamento das d�vidas.
> * Taxa Selic: A Selic afeta as taxas de juros, influenciando o custo do cr�dito e a capacidade de pagamento das d�vidas. Altas taxas de juros podem aumentar a inadimpl�ncia.