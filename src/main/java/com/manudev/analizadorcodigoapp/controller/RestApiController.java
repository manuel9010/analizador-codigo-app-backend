package com.manudev.analizadorcodigoapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.manudev.analizadorcodigoapp.model.dto.GenerarCodigoDTO;
import com.manudev.analizadorcodigoapp.service.AnalizadorCodigoService;
import com.manudev.analizadorcodigoapp.util.UtilError;

@RestController
@RequestMapping("/api")
public class RestApiController {

	public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);

	@Autowired
	AnalizadorCodigoService analizadorCodigoService;

	@RequestMapping(value = "/generar-codigo/", method = RequestMethod.POST)
	public ResponseEntity<?> generarCodigo(@RequestBody GenerarCodigoDTO data) {
		GenerarCodigoDTO response = new GenerarCodigoDTO();
		String codigoHtml = analizadorCodigoService.generarCodigoHtml(data.getCodigo());
		if (codigoHtml == null) {

			return new ResponseEntity<Object>(new UtilError("Ocurrio un error inesperado."),
					HttpStatus.INTERNAL_SERVER_ERROR);

		}
		response.setCodigo(codigoHtml);
		return new ResponseEntity<GenerarCodigoDTO>(response, HttpStatus.OK);
	}

}