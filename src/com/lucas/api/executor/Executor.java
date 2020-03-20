package com.lucas.api.executor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;

import com.lucas.api.geradorDeSaida.GeradorArqvSaida;
import com.lucas.api.processador.Processador;

import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class Executor {
	
	private static final String NOME_DIRETORIO_DATA 	  = "data";
	private static final String NOME_DIRETORIO_DE_ENTRADA = "in";
	private static final String PATH_DIRETORIO_ENTRADA 	  = System.getProperty("user.dir").concat(File.separator).
															concat(NOME_DIRETORIO_DATA).concat(File.separator).
															concat(NOME_DIRETORIO_DE_ENTRADA);

	public static void main(String[] args) throws IOException, InterruptedException {
		
		BufferedReader 	 br;
		Processador 	 processador;
		GeradorArqvSaida geradorArvSaida;
		
		
		// Primeira leitura do diretório
		
		final File folder = new File(PATH_DIRETORIO_ENTRADA);
		for(final File fileEntry : folder.listFiles()) {
	            
			String extension = retornaExtensaoDeArqv(fileEntry.getName());
			
			if(extension.equalsIgnoreCase("IN")) {
				
				String caminhoCompletoArqvEntrada = folder.toString().concat(File.separator).concat(fileEntry.getName());
				br = new BufferedReader(new FileReader(caminhoCompletoArqvEntrada)); 
				processador = new Processador();
				
				String st;
		          while ((st = br.readLine()) != null) {
		            processador.parserLinhaEntrada(st);
		            
		          }
		          
		          geradorArvSaida = new GeradorArqvSaida();
		          geradorArvSaida.gerarArquivoSaida(fileEntry.getName(), processador);
		          
		          System.out.println("Processamento do ARQUIVO " + fileEntry.getName() + " finalizado.");
				
			}
			
	    }
		
		
		
		// Leitura contínua do diretório 
		
		try (WatchService watchService = FileSystems.getDefault().newWatchService()) {

		      Path path = Paths.get(PATH_DIRETORIO_ENTRADA);
		      path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
		      

		      while (true) {
		        WatchKey key = watchService.take();

		        for (WatchEvent<?> watchEvent : key.pollEvents()) {

		          Kind<?> kind = watchEvent.kind();
		          Path context = (Path) watchEvent.context();
		          
		          String extension = retornaExtensaoDeArqv(context.toString());
		          
		          if(extension.equalsIgnoreCase("IN")) {
			          
			          String caminhoCompletoArqvEntrada = path.toString().concat(File.separator).concat(context.toString());
			          
			          br = new BufferedReader(new FileReader(caminhoCompletoArqvEntrada)); 
			          
			          processador = new Processador();
			          
			          String st; 
			          while ((st = br.readLine()) != null) {
			            processador.parserLinhaEntrada(st);
			            
			          }
			          
			          geradorArvSaida = new GeradorArqvSaida();
			          geradorArvSaida.gerarArquivoSaida(context.toString(), processador);
			          
			          System.out.println("Processamento do ARQUIVO " + context.toString() + " finalizado.");
		        	  
		          }

		        }

		        boolean valid = key.reset();
		        if (!valid) {
		          break;
		        }
		        
		      }
		    }
	}
	
	private static String retornaExtensaoDeArqv(String arqv) {
		
        String extension = "";

        int i = arqv.lastIndexOf('.');
        if (i > 0) {
            extension = arqv.substring(i+1);
        }
        
        return extension;
	}
}