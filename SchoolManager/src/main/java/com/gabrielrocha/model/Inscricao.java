package com.gabrielrocha.model;

import com.gabrielrocha.util.Id;

import java.io.Serializable;

public class Inscricao implements Serializable {
    private int id;
    private int nota;
    private String data;
    private Aluno aluno;
    private Turma turma;

    public Inscricao(Aluno aluno, Turma turma, String data){
        this.nota = -1;
        this.data = data;
        this.aluno = aluno;
        this.turma = turma;
    }
    public Inscricao(Aluno aluno, Turma turma, String data, int nota){
        this.nota = nota;
        this.data = data;
        this.aluno = aluno;
        this.turma = turma;
    }

    public String toString(){
        return "Turma id = " + id + '\n' +
                "Disciplina = " + turma.getDisciplina().getNome() + '\n' +
                "Nota = " + nota + '\n' +
                "Aluno = " + getAluno().getNome() + '\n' +
                "Turma: " + '\n' + getTurma().toString() + '\n';
    }

    @Id
    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getNota() {
        return nota;
    }

    public String getData() {
        return data;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }
}
