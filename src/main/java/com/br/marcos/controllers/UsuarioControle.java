package com.br.marcos.controllers;

import com.br.marcos.models.Usuario;
import com.br.marcos.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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



}
