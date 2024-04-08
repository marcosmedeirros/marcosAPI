package com.br.marcos.controllers;

import com.br.marcos.models.Usuario;
import com.br.marcos.services.UsuarioService;
import jakarta.servlet.Servlet;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuario")
@Validated
public class UsuarioControle {

    @Autowired
   private UsuarioService usuarioService;

    //localhost:8080/usuario/1
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable Long id){
        Usuario u = this.usuarioService.findById(id);
        return ResponseEntity.ok().body(u);
    }

    @PostMapping
    @Validated(Usuario.CreateUsuario.class)
    public ResponseEntity<Void> create(@Valid @RequestBody Usuario u){
        this.usuarioService.create(u);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(u.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    @Validated(Usuario.UpdateUsuario.class)
    public ResponseEntity<Void> update(@Valid @RequestBody Usuario u, @PathVariable Long id){
        u.setId(id);
        this.usuarioService.update(u);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
