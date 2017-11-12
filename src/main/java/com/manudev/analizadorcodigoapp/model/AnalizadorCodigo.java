package com.manudev.analizadorcodigoapp.model;


import static j2html.TagCreator.body;
import static j2html.TagCreator.h1;
import static j2html.TagCreator.h2;
import static j2html.TagCreator.h3;
import static j2html.TagCreator.h4;
import static j2html.TagCreator.h5;
import static j2html.TagCreator.h6;
import static j2html.TagCreator.head;
import static j2html.TagCreator.html;
import static j2html.TagCreator.meta;
import static j2html.TagCreator.text;
import static j2html.TagCreator.title;
import  j2html.tags.ContainerTag;
import j2html.tags.DomContent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.manudev.analizadorcodigoapp.util.Constantes;


public class AnalizadorCodigo {
	
	  private String regla;

	    public AnalizadorCodigo(String regla) {
	        this.regla = regla;
	    }

	    /**
	     * @return the regla
	     */
	    public String getRegla() {
	        return regla;
	    }

	    /**
	     * @param regla the regla to set
	     */
	    public void setRegla(String regla) {
	        this.regla = regla;
	    }

	    public List<LineaCodigo> verificarCodigo(String codigo) throws IOException {
	        //  String regla = "^(#{1,6})\\s\\S.*";
	        Pattern patron = Pattern.compile(this.regla);
	        String linea;
	        LineaCodigo lineaCodigo = null;
	        List<LineaCodigo> lineasCodigo = new ArrayList<LineaCodigo>();

	        BufferedReader bufReader = new BufferedReader(new StringReader(codigo));

	        while ((linea = bufReader.readLine()) != null) {

	            Matcher matcher = patron.matcher(linea.trim());
	            if (matcher.find()) {
	                lineaCodigo = new LineaCodigo(matcher.group(), true);
	            } else {
	                lineaCodigo = new LineaCodigo(linea, false);
	            }

	            lineasCodigo.add(lineaCodigo);
	        }

	        return lineasCodigo;

	    }

	    public String generarCodigoHtml(List<LineaCodigo> lineasCodigo) {
	        String codigoHtml = "";
	        List<DomContent> encabezados = new ArrayList<DomContent>();

	        for (int i = 0; i < lineasCodigo.size(); i++) {
	            LineaCodigo lineaCodigo = lineasCodigo.get(i);
	            if (lineaCodigo.isValida()) {
	                encabezados.add(obtenerEncabezado(lineaCodigo.getContenido()));
	            } else {
	                encabezados.add(text(lineaCodigo.getContenido()));
	            }
	        }

	        codigoHtml = "<!DOCTYPE html>\n" + html(
	                head(
	                        meta().attr("charset", "utf-8"),
	                        title("")
	                ),
	                body().with(encabezados)
	        ).renderFormatted();
	        

	        return codigoHtml;
	    }

	    private ContainerTag obtenerEncabezado(String lineaCodigo) {
	        ContainerTag encabezado = null;
	        String contenidoLinea = "";
	        int numerosNumeral = lineaCodigo.split("#").length - 1;

	        Matcher matcher = Pattern.compile("^(#{1,6})\\s").matcher(lineaCodigo);
	        if (matcher.find()) {
	            contenidoLinea = lineaCodigo.substring(matcher.end()).trim();
	        }
	        switch (numerosNumeral) {
	            case Constantes.ENCABEZADO_H1:
	                encabezado = h1(contenidoLinea);
	                break; // optional

	            case Constantes.ENCABEZADO_H2:
	                encabezado = h2(contenidoLinea);
	                break; // optional

	            case Constantes.ENCABEZADO_H3:
	                encabezado = h3(contenidoLinea);
	                break; // optional

	            case Constantes.ENCABEZADO_H4:
	                encabezado = h4(contenidoLinea);
	                break; // optional

	            case Constantes.ENCABEZADO_H5:
	                encabezado = h5(contenidoLinea);
	                break; // optional

	            case Constantes.ENCABEZADO_H6:
	                encabezado = h6(contenidoLinea);
	                break; // optional

	        }
	        return encabezado;
	    }

}
