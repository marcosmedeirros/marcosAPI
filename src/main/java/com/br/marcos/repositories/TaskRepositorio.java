package com.br.marcos.repositories;

import com.br.marcos.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepositorio extends JpaRepository<Task, Long> {

    List<Task> findByUsuario_Id(Long id);

    //@Query(value = "SELECT * FROM task t WHERE t.usuario_id = :id", nativeQuery = true)
    //List<Task> findByUsuario_Id(@Param("id") Long id);

}
