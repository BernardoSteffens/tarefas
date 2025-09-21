package dw.tarefas.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tarefa")
public class Tarefa{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 100)
    private String descricao;

    @Column
    private boolean concluida = false;

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