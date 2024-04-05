package com.br.marcos.services;

import com.br.marcos.models.Usuario;
import com.br.marcos.repositories.TaskRepositorio;
import com.br.marcos.repositories.UsuarioRepositorio;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;


    public Usuario findById(Long id) {
        Optional<Usuario> usuario = this.usuarioRepositorio.findById(id);
        return usuario.orElseThrow(() -> new RuntimeException("Usuário não encontrado, seu id: " + id));
    }

    @Transactional
    public Usuario create(Usuario u) {
        u.setId(null);
        u = this.usuarioRepositorio.save(u);
        return u;
    }

    @Transactional
    public Usuario update(Usuario u) {
        Usuario newUser = this.findById(u.getId());
        newUser.setPassword(u.getPassword());
        return this.usuarioRepositorio.save(newUser);
    }

    @Transactional
    public void delete(Long id) {
        findById(id);
        try {
            this.usuarioRepositorio.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Usuario com Tarefas relacionadas");
        }
    }

}
