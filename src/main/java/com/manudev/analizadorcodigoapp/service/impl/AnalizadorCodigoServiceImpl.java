package com.manudev.analizadorcodigoapp.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.manudev.analizadorcodigoapp.model.AnalizadorCodigo;
import com.manudev.analizadorcodigoapp.model.LineaCodigo;
import com.manudev.analizadorcodigoapp.service.AnalizadorCodigoService;

@Service("analizadorCodigoService")
public class AnalizadorCodigoServiceImpl implements AnalizadorCodigoService {

	private static AnalizadorCodigo analizadorCodigo;

	static {
		analizadorCodigo = new AnalizadorCodigo("^(#{1,6})\\s\\S.*");
	}

	@Override
	public String generarCodigoHtml(String codigo) {

		String codigoHtml = null;

		try {
			List<LineaCodigo> lineasCodigo = analizadorCodigo.verificarCodigo(codigo);

			codigoHtml = analizadorCodigo.generarCodigoHtml(lineasCodigo);

		} catch (IOException ex) {
			Logger.getLogger(AnalizadorCodigoServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
		}

		return codigoHtml;
	}

}
