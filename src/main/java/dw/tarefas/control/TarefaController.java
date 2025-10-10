package dw.tarefas.control;

import dw.tarefas.model.Tarefa;
import dw.tarefas.repository.TarefaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class TarefaController {

    @Autowired
    TarefaRepository rep;

    @GetMapping("/")
    public ResponseEntity<List<Tarefa>> getAllTarefas(@RequestParam(required = false) String descricao) {
        try {
            List<Tarefa> lista = new ArrayList<Tarefa>();
            if (descricao == null) {
                rep.findAll().forEach(lista::add);
            } else {
                rep.findByDescricaoContaining(descricao).forEach(lista::add);
            }

            if (lista.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Tarefa> createTarefa(@Valid @RequestBody Tarefa ta) {
        try {
            Tarefa t = rep.save(new Tarefa(ta.getDescricao(), ta.getPrioridade(), ta.isConcluida()));
            return new ResponseEntity<>(t, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> getTarefaById(@PathVariable("id") long id){
        try{
            Optional<Tarefa> data = rep.findById(id);

            if(data.isPresent()){
                return new ResponseEntity<>(data.get(), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> updateTarefa(@PathVariable("id") Long id, @Valid @RequestBody Tarefa t){
        try {
            Optional<Tarefa> data = rep.findById(id);

            if(data.isPresent()){

                Tarefa ta = data.get();
                ta.setConcluida(t.isConcluida());
                ta.setDescricao(t.getDescricao());
                ta.setPrioridade(t.getPrioridade());

                return new ResponseEntity<>(rep.save(ta), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Tarefa> deleteTarefa(@PathVariable("id") Long id){
        try{
            rep.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/pendentes")
    public ResponseEntity<List<Tarefa>> getPendentes(){
        try {
            List<Tarefa> lista = new ArrayList<Tarefa>();

            lista = rep.findByConcluida(false);

            if (lista.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{id}/concluir")
    public ResponseEntity<Tarefa> concluirTarefa(@PathVariable("id") Long id){
        try {
            Optional<Tarefa> data = rep.findById(id);
            if(data.isPresent()){
                Tarefa t = data.get();
                t.setConcluida(true);
                return updateTarefa(id, t);
            } else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
