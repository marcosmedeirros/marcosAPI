package com.br.marcos.services;

import com.br.marcos.models.Task;
import com.br.marcos.models.Usuario;
import com.br.marcos.repositories.TaskRepositorio;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepositorio taskRepositorio;

    @Autowired
    private UsuarioService UsuarioService;

    public Task findById(Long id) {
        Optional<Task> task = this.taskRepositorio.findById(id);
        return task.orElseThrow(() -> new RuntimeException("Tarefa não encontrada, seu id: " + id));
    }

    @Transactional
    public Task create(Task t) {
        Usuario usuario = this.UsuarioService.findById(t.getUsuario().getId());
        t.setId(null);
        t.setUsuario(usuario);
        t = this.taskRepositorio.save(t);
        return t;
    }

    @Transactional
    public Task update(Task t) {
        Task newTask = findById(t.getId());
        newTask.setDescricao(t.getDescricao());
        return this.taskRepositorio.save(newTask);
    }

    @Transactional
    public void delete(Long id) {
        findById(id);
        this.taskRepositorio.deleteById(id);
        try {
            this.taskRepositorio.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Tarefa com Usuario relacionado");
        }
    }
}
