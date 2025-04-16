package com.gabrielrocha.model;

import com.gabrielrocha.util.Id;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Disciplina implements Serializable {
    private int id;
    private String nome;
    private int cargaHoraria;
    private List<Disciplina> requisitos;
    private List<Turma> turmas;

    public Disciplina(String nome, int cargaHoraria){
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.requisitos = new ArrayList<>();
        this.turmas = new ArrayList<>();
    }

    public String toString(){
        return "Numero = " + id + '\n'+
                "Nome = " + nome + '\n'+
                "Carga Horaria = " + cargaHoraria;
    }

    @Id
    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public List<Disciplina> getRequisitos() {
        return requisitos;
    }

    public List<Turma> getTurmas() {
        return turmas;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public void setRequisitos(List<Disciplina> requisitos) {
        this.requisitos = requisitos;
    }

    public void setTurmas(List<Turma> turmas) {
        this.turmas = turmas;
    }

}
