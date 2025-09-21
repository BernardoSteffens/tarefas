package dw.tarefas.repository;

import dw.tarefas.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    List<Tarefa> findByConcluida(boolean concluida);

    List<Tarefa> findByDescricaoContaining(String descricao);


}
