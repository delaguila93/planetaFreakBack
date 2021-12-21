package com.api.planetafreak.controllers;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.api.planetafreak.models.entity.Producto;
import com.api.planetafreak.service.IProductoService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@CrossOrigin(origins= {"http://localhost:4200"})
@RequestMapping("/api")
public class ProductoController {

	
	@Autowired
	private IProductoService productoService;
	
	@GetMapping("/productos")
	@ApiOperation(value = "Devuelve el listado completo de los clientes", notes = "Devuelve el listado completo con los datos de los clientes", response = Producto.class)
	public List<Producto> listarTodos() {
		return productoService.findAll();
	}
	
	@GetMapping("/productos/{id}")
	@ApiOperation(value = "Devuelve la informacion de un prooducto", notes = "Devuelve la informacion completa de un producto",
			response = Producto.class)
	public ResponseEntity<?> show(@ApiParam(value = "El id del producto", required = true) @PathVariable Long id) {
		Producto cliente = null;
		Map<String, Object> response = new HashMap<>();

		try {
			cliente = productoService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar consulta en base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (cliente == null) {
			response.put("mensaje", "El cliente ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Producto>(cliente, HttpStatus.OK);
	}
	
	@PutMapping("/productos/{id}")
	@ApiOperation(value = "Devuelve el listado completo de los clientes", notes = "Devuelve el listado completo con los datos de los clientes", response = Producto.class)
	public ResponseEntity<?> update(@RequestBody Producto producto, @PathVariable Long id) {
		Producto productoUpdate = productoService.findById(id);
		Producto productoActualizado = null;
		Map<String, Object> response = new HashMap<>();

		if (productoUpdate == null) {
			response.put("mensaje", "El cliente no existe en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {

			if (producto.getNombre() != "") {
				productoUpdate.setNombre(producto.getNombre());
			}
			if (producto.getCantidad() != 0) {
				productoUpdate.setNombre(producto.getNombre());
			}

			if (producto.getCategoria() != "") {
				productoUpdate.setCategoria(producto.getCategoria());
			}
			if (producto.getDescripcion() != null) {
				productoUpdate.setDescripcion(producto.getDescripcion());
			}
			productoActualizado = productoService.save(productoUpdate);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El cliente con ID: " + productoActualizado.getId() + " ha sido modificado en la BBDD");
		response.put("cliente", productoActualizado);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PostMapping("/productos/uploads")
	@ApiOperation(value = "Devuelve el listado completo de los clientes", notes = "Devuelve el listado completo con los datos de los clientes", response = Producto.class)
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id) {

		Map<String, Object> response = new HashMap<>();

		Producto cliente = productoService.findById(id);
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

			String nombreFotoAnterior = cliente.getImagen();

			if (nombreFotoAnterior != null && nombreFotoAnterior.length() > 0) {
				Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
				File archivoFotoAnterior = rutaFotoAnterior.toFile();

				if (archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
					archivoFotoAnterior.delete();
				}

			}

			cliente.setImagen(nombreArchivo);
			productoService.save(cliente);

			response.put("cliente", cliente);
			response.put("mensaje", "Has subido correctamente la imagen " + nombreArchivo);

		}
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@GetMapping("/productos/uploads/img/{nombreFoto:.+}")
	@ApiOperation(value = "Devuelve el listado completo de los clientes", notes = "Devuelve el listado completo con los datos de los clientes", response = Producto.class)
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
	
	@GetMapping("/productos/buscar-nombre/{nombre}")
	public ResponseEntity<?> buscarNombre(@ApiParam(value = "El id del producto", required = true) @PathVariable String nombre) {
		List<Producto> productos = new ArrayList<>();
		Map<String, Object> response = new HashMap<>();

		try {
			productos = productoService.buscarNombre(nombre);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar consulta en base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}



		return new ResponseEntity<List<Producto> >(productos, HttpStatus.OK);
	}
	
	@GetMapping("/productos/buscar-categoria/{categoria}")
	public ResponseEntity<?> buscarCategoria(@ApiParam(value = "El id del producto", required = true) @PathVariable String categoria) {
		List<Producto> productos = new ArrayList<>();
		Map<String, Object> response = new HashMap<>();

		try {
			productos = productoService.buscarCategoria(categoria);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar consulta en base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}



		return new ResponseEntity<List<Producto> >(productos, HttpStatus.OK);
	}
	
	@PutMapping("/productos/comprar/{username}")
	public ResponseEntity<?> comprar(@PathVariable String username,@RequestBody Producto producto){
		Producto p = productoService.findById(producto.getId());
		Producto productoComprar = null;
		
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			productoComprar = productoService.comprar(p,username);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al actualizar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
}
