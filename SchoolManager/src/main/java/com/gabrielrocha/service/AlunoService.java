package com.gabrielrocha.service;

import com.gabrielrocha.dao.AlunoDAO;
import com.gabrielrocha.exception.EntidadeNaoEncontradaException;
import com.gabrielrocha.exception.RemocaoNaoAutorizada;
import com.gabrielrocha.model.Aluno;
import com.gabrielrocha.util.FabricaDeDaos;

import java.util.List;

public class AlunoService {
    private final AlunoDAO alunoDAO = FabricaDeDaos.getDAO(AlunoDAO.class);

    public void incluir(Aluno aluno){
        alunoDAO.incluir(aluno);
    }
    public void remover(int id){
        if(!alunoDAO.recuperarPorId(id).getInscricaos().isEmpty()){
            throw new RemocaoNaoAutorizada("Remocao nao autorizada pois o mesmo esta associado a outro elemento");
        }
        else{
            alunoDAO.remover(id);
        }
    }

    public void alterar(Aluno aluno){
        alunoDAO.alterar(aluno);
    }

    public Aluno recuperarPorId(int id){
        if(alunoDAO.recuperarPorId(id) == null){
            throw new EntidadeNaoEncontradaException("Nao existe aluno com esse id");
        }
        else {
            return alunoDAO.recuperarPorId(id);
        }
    }

    public List<Aluno> recuperarTodos(){
        return alunoDAO.recuperarTodos();
    }


}
