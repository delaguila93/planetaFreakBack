package com.api.planetafreak.controllers;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.api.planetafreak.models.entity.Usuario;
import com.api.planetafreak.service.IUsuarioService;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@CrossOrigin(origins= {"http://localhost:4200"})
@RequestMapping("/api")
public class UsuarioController {
	
	@Autowired
	private IUsuarioService usuarioService;

	@GetMapping("/usuarios")
	@ApiOperation(value = "Devuelve el listado completo de los clientes", notes = "Devuelve el listado completo con los datos de los clientes", response = Usuario.class)
	public List<Usuario> listarTodos() {
		return usuarioService.findAll();
	}

	@GetMapping("/usuarios/{id}")
	@ApiOperation(value = "Devuelve la informacion de un usuario", notes = "Devuelve la informacion completa de un cliente",
			response = Usuario.class)
	public ResponseEntity<?> show(@ApiParam(value = "El id del usuario", required = true) @PathVariable Long id) {
		Usuario usuario = null;
		Map<String, Object> response = new HashMap<>();

		try {
			usuario = usuarioService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar consulta en base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (usuario == null) {
			response.put("mensaje", "El Usuario ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}

	@PostMapping("/crear")
	@ApiOperation(value = "Devuelve el listado completo de los clientes", notes = "Devuelve el listado completo con los datos de los clientes", response = Usuario.class)
	public ResponseEntity<?> create(@RequestBody Usuario usuario) {
		Usuario usuarioGuardado = null;
		Map<String, Object> response = new HashMap<>();

		try {
			usuarioGuardado = usuarioService.crearUsuario(usuario);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al insertar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El cliente con ID: " + usuarioGuardado.getId() + " ha sido creado en la BBDD");
		response.put("cliente", usuarioGuardado);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/usuarios/{id}")
	@ApiOperation(value = "Devuelve el listado completo de los clientes", notes = "Devuelve el listado completo con los datos de los clientes", response = Usuario.class)
	public ResponseEntity<?> update(@RequestBody Usuario usuario, @PathVariable Long id) {
		Usuario usuarioUpdate = usuarioService.findById(id);
		Usuario usuarioActualizado = null;
		Map<String, Object> response = new HashMap<>();

		if (usuarioUpdate == null) {
			response.put("mensaje", "El cliente no existe en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {

			if (usuario.getNombre() != "") {
				usuarioUpdate.setNombre(usuario.getNombre());
			}
			if (usuario.getApellidos() != "") {
				usuarioUpdate.setApellidos(usuario.getApellidos());
			}
			if (usuario.getEmail() != "") {
				usuarioUpdate.setEmail(usuario.getEmail());
			}
			if (usuario.getUsername() != "") {
				usuarioUpdate.setUsername(usuario.getUsername());
			}
			
			usuarioActualizado = usuarioService.actualizar(usuarioUpdate);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El usuario con ID: " + usuarioActualizado.getId() + " ha sido modificado en la BBDD");
		response.put("usuario", usuarioActualizado);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/usuarios/{id}")
	@ApiOperation(value = "Devuelve el listado completo de los clientes", notes = "Devuelve el listado completo con los datos de los clientes", response = Usuario.class)
	public ResponseEntity<?> delete(@PathVariable Long id) {

		Map<String, Object> response = new HashMap<>();

		try {
			usuarioService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al borrar el usuario en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El cliente ha sido borrado de la base de datos");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}
	
	@PostMapping("/usuarios/uploads")
	@ApiOperation(value = "Devuelve el listado completo de los clientes", notes = "Devuelve el listado completo con los datos de los clientes", response = Usuario.class)
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id) {

		Map<String, Object> response = new HashMap<>();

		Usuario usuario = usuarioService.findById(id);
		if (!archivo.isEmpty()) {
			String nombreArchivo = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename().replace(" ", "");
			Path rutaArchivo = Paths.get("uploads").resolve(nombreArchivo).toAbsolutePath();

			try {
				Files.copy(archivo.getInputStream(), rutaArchivo);

			} catch (IOException e) {
				response.put("mensaje", "Error al borrar el cliente en la base de datos");
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			String nombreFotoAnterior = usuario.getImagen();

			if (nombreFotoAnterior != null && nombreFotoAnterior.length() > 0) {
				Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
				File archivoFotoAnterior = rutaFotoAnterior.toFile();

				if (archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
					archivoFotoAnterior.delete();
				}

			}

			usuario.setImagen(nombreArchivo);
			usuarioService.actualizar(usuario);

			response.put("usuario", usuario);
			response.put("mensaje", "Has subido correctamente la imagen " + nombreArchivo);

		}
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@GetMapping("/usuarios/uploads/img/{nombreFoto:.+}")
	@ApiOperation(value = "Devuelve el listado completo de los clientes", notes = "Devuelve el listado completo con los datos de los clientes", response = Usuario.class)
	public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto) {
		Path rutaArchivo = Paths.get("uploads").resolve(nombreFoto).toAbsolutePath();
		Resource recurso = null;

		try {
			recurso = new UrlResource(rutaArchivo.toUri());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		if (!recurso.exists() && !recurso.isReadable()) {
			throw new RuntimeException("Error no se puede cargar la imagen" + nombreFoto);
		}

		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename=\""+recurso.getFilename()+"\"");
		return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);

	}


}
