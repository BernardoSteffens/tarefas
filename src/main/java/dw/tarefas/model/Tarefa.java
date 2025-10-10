package dw.tarefas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "tarefa")
public class Tarefa{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Adicione uma descrição")
    @Column(nullable = false, length = 100)
    private String descricao;

    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean concluida = false;

    @Min(value = 1, message = "A prioridade mínima é 1")
    @Max(value = 5, message = "A prioridade máxima é 5")
    @Column(nullable = false)
    private int prioridade;

    public Tarefa(String descricao, int prioridade, boolean concluida) {
        this.descricao = descricao;
        this.prioridade = prioridade;
        this.concluida = concluida;
    }

    public Tarefa() {

    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isConcluida() {
        return concluida;
    }

    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }
}