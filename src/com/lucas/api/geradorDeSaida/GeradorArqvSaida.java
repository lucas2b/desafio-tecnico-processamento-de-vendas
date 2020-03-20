package com.lucas.api.geradorDeSaida;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.lucas.api.processador.Processador;

public class GeradorArqvSaida {
	
	private static final String 	NOME_DIRETORIO_DATA = "data";
	private static final String NOME_DIRETORIO_DE_SAIDA = "out";
	private static final String    PATH_DIRETORIO_SAIDA = System.getProperty("user.dir").concat(File.separator).
														  concat(NOME_DIRETORIO_DATA).concat(File.separator).
														  concat(NOME_DIRETORIO_DE_SAIDA);
	
	public void gerarArquivoSaida(String nomeArqvEntrada, Processador processador) {
		
		try {
			  final String nomeArqvSaidaSemExtensao = nomeArqvEntrada.substring(0, nomeArqvEntrada.lastIndexOf("."));
			  final String 		   caminhoArqvSaida = PATH_DIRETORIO_SAIDA.concat(File.separator).concat(nomeArqvSaidaSemExtensao.concat(".out"));
			
		      FileWriter myWriter = new FileWriter(caminhoArqvSaida);
		      
		      myWriter.write("Quantidade de clientes no arquivo de entrada:" + processador.quantidadeDeClientes());
		      myWriter.write(System.getProperty( "line.separator" ));
		      myWriter.write("Quantidade de vendedores no arquivo de entrada:" + processador.quantidadeDeVendedores());
		      myWriter.write(System.getProperty( "line.separator" ));
		      myWriter.write("ID da venda mais cara:" + processador.idDaVendaMaisCara());
		      myWriter.write(System.getProperty( "line.separator" ));
		      myWriter.write("O pior vendedor: " + processador.piorVendedor());
		      myWriter.close();
		      
		    } catch (IOException e) {
		      System.out.println("Ocorreu um erro ao gravar o arquivo.");
		      e.printStackTrace();
		    }
	}

}
