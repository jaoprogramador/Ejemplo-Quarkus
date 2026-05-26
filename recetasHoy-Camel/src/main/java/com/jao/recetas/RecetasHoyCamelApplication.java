package com.jao.recetas;

import java.util.Arrays;
import java.util.concurrent.Exchanger;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RecetasHoyCamelApplication extends RouteBuilder{
	private static String RECETAS_HOY_ORIGEN="file:C:/Users/user/Documents/recetasCamel/origen?noop=true";
	private static String RECETAS_HOY_DESTINO="file:C:/Users/user/Documents/recetasCamel/destino";
	
	private static String ORIGEN="file:origen?noop=true";
	private static String DESTINO="file:destino";
	public static void main(String[] args) {
		SpringApplication.run(RecetasHoyCamelApplication.class, args);
	}

	@Override
	public void configure() throws Exception {
		System.out.println("Mover recestas HOY :::INI");
		//MOVER TODOS LOS FICHEROS
		//moverTodasRecetas();
		//MOVER FICHERO BEBIDAS
		//moverBebidas("Bebidas");
		//MOVER FICHERO SI CONTIENE CONTENIDO COCA
		//moverBebidasConContenido("Coca");
		//leer fichero y generar un CSV separado por comas
		//leerFichero();
		//PROCESAR DE PLATOS DE CARTA y sustituye " " em destino separado por ","
		//procesarRegistros();
		//leerFichero();
	    //PROCESAR EL ESTADO DE PLATOS DE CARTA y sustituye " " em destino separado por ","
		//procesarEstadoRegistrosCSV();
		//PROCESAR EL ESTADO DE PLATOS DE CARTA EN DIFERENTES FICHEROS SEGUN ESTADO
		procesarEstadoRegistrosMultiplesCSV();
		System.out.println("Mover recestas HOY :::FIN");
		
	}

	private void moverTodasRecetas() {
		from(RECETAS_HOY_ORIGEN).to(RECETAS_HOY_DESTINO);
	}
	private void moverBebidas(String tipo) {
		from(RECETAS_HOY_ORIGEN).filter(header(Exchange.FILE_NAME).startsWith(tipo))
		.to(RECETAS_HOY_DESTINO);
	}
	private void moverBebidasConContenido(String contenido) {
		from(ORIGEN)
		.convertBodyTo(String.class) 
        .filter(body().contains(contenido)) 
        .to(DESTINO);
	}
	
	public void leerFichero() {
		from(ORIGEN).process(p -> {
			String body = p.getIn().getBody(String.class);
			StringBuilder sb = new StringBuilder();
			Arrays.stream(body.split(" ")).forEach(s -> {
				sb.append(s + "|");
			});

			p.getIn().setBody(sb);
		}).to(DESTINO+"?fileName=records.csv");
	}

	public void procesarRegistros() {
		from(ORIGEN).process(p ->{
			String body = p.getIn().getBody(String.class);
			StringBuilder sb = new StringBuilder();
			Arrays.stream(body.split(" ")).forEach(s -> {
				sb.append(s + ",");
			});
			p.getIn().setBody(sb);
		}).to(DESTINO);
		
	}
	public void procesarEstadoRegistrosCSV() {
		
		from(ORIGEN).process(p ->{
			String body = p.getIn().getBody(String.class);
			StringBuilder sb = new StringBuilder();
			Arrays.stream(body.split(" ")).forEach(s -> {
				sb.append(s + ",");
			});
			p.getIn().setBody(sb);
		}).to(DESTINO+"?fileName=Pedido.csv");
		
	}
	public void procesarEstadoRegistrosMultiplesCSV() {
		
		from(ORIGEN).unmarshal().csv().split(body().tokenize(",")).choice()
		.when(body().contains("En carta")).to(DESTINO+"?fileName=EnCarta.csv")
		.when(body().contains("Sin existencias")).to(DESTINO+"?fileName=SinExistencias.csv")
		.when(body().contains("Pedido")).to(DESTINO+"?fileName=Pedido.csv");
	
		
	}

}
