#Desafio Técnico Processamento de Vendas

Desenvolvimento de um desafio técnico que processa arquivos do diretório de entrada e apresenta
os resultados em arquivos no diretório de saída.

O arquivo de entrada deve estar no diretório "data/in" com extensão ".in" na raiz da aplicação.

Exemplo 1 de Arquivo de entrada: (dados_fornecidos.in)

001ç1234567891234çPedroç50000<br/>
001ç3245678865434çPauloç40000.99<br/>
002ç2345675434544345çJose da SilvaçRural<br/>
002ç2345675433444345çEduardo PereiraçRural<br/>
003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro<br/>
003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo

Saída Esperada: (dados_fornecidos.out)

"Quantidade de clientes no arquivo de entrada:2<br/>
Quantidade de vendedores no arquivo de entrada:2<br/>
ID da venda mais cara:10<br/>
O pior vendedor: Paulo"

Exemplo 2 de Arquivo de entrada: (dados_criados.in)

001ç7829482638479çLucasç6000<br/>
001ç1849301938492çJoseç2300.99<br/>
001ç1738492818382çMatheusç4300.31<br/>
001ç9483928479283çMariaç4800.42<br/>
001ç3849275628374çJuliaç3700.60<br/>
002ç8392710493851398çFabio PereiraçComercial<br/>
002ç3749283748925829çJulio MachadoçIndustrial<br/>
002ç3729384623767287çJoana Da SilvaçBeleza<br/>
002ç8573947293472968çMateus De SouzaçRural<br/>
003ç01ç[1-12-123,2-45-22.50,3-14-3.10,4-10-0.99]çMatheus<br/>
003ç02ç[1-32-3,2-19-10.5,3-15-34.7]çJose<br/>
003ç03ç[1-19-3.23,2-14-31.2,3-10-9.99]çMaria<br/>
003ç04ç[1-3-5.25,2-2-19.99,3-17-4.56,4-11-02.33]çJulia<br/>
003ç05ç[1-5-3.56,2-8-8.42,3-9-23.4,4-7-7.23,5-19-3.24]çJose<br/>
003ç06ç[1-9-24.52,2-21-4.45,3-56-13.4]çLucas<br/>
003ç07ç[1-10-93.33,2-17-41.23,3-52-17.28,4-87-24.87,5-24-73.22]çMatheus<br/>
003ç08ç[1-43-24.53,2-45-53.88,3-18-92.78,4-23-5.55]çMaria<br/>
003ç09ç[1-2-42.2,2-4-5.32,3-4-2]çMatheus

Saída Esperada: (dados_criados.out)

"Quantidade de clientes no arquivo de entrada:4<br/>
Quantidade de vendedores no arquivo de entrada:5<br/>
ID da venda mais cara:07<br/>
O pior vendedor: Julia"
