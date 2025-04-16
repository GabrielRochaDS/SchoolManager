package com.gabrielrocha.model;

import com.gabrielrocha.util.Id;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Professor implements Serializable {
    private int id;
    private String nome;
    private String email;
    private List<Turma> turma;

    public Professor(String nome, String email){
        this.nome = nome;
        this.email = email;
        this.turma = new ArrayList<>();
    }

    public String toString(){
        return "Numero = " + id + '\n' +
                "Nome = " + nome + '\n' +
                "Email = " + email + '\n';
    }

    public String getNome() {
        return nome;
    }

    @Id
    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public List<Turma> getTurma() {
        return turma;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTurma(List<Turma> turma) {
        this.turma = turma;
    }
}
