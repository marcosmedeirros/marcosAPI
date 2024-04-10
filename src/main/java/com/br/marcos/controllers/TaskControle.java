package com.br.marcos.controllers;

import com.br.marcos.models.Task;
import com.br.marcos.services.TaskService;
import com.br.marcos.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/task")
@Validated
public class TaskControle {

    @Autowired
    private TaskService taskService;
    @GetMapping("/{id}")
    public ResponseEntity<Task> findById(@PathVariable Long id){
        Task t = this.taskService.findById(id);
        return ResponseEntity.ok(t);
    }

    @GetMapping("/usuariotarefa/{usuario_Id}")
    public ResponseEntity<List<Task>> findAllByUsuario_Id(@PathVariable Long usuario_Id){
        List<Task> t = this.taskService.findAllByUsuario_Id(usuario_Id);
        return ResponseEntity.ok().body(t);
    }

    @PostMapping
    @Validated
    public ResponseEntity<Task> create(@Valid @RequestBody Task t){
        this.taskService.create(t);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(t.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    @Validated
    public ResponseEntity<Void> update(@Valid @RequestBody Task t, @PathVariable Long id){
        t.setId(id);
        this.taskService.update(t);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.taskService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
