package com.example.demo.negocio;

import com.example.demo.Models.Fila;
import com.example.demo.Models.Pacientes;

import java.util.*;

public class FilaNegocio extends Fila {
    private int size;
    private ArrayList<Pacientes> pacientesFila;
    public FilaNegocio() {
        ArrayList<Pacientes> pacientesFila = new ArrayList<>();
    }

    public int getSize(){
        return pacientesFila.size();
    }

    public void add(Pacientes paciente){
        if(paciente != null){
            if(pacientesFila != null) {
                pacientesFila.add(paciente);
            }
            throw new RuntimeException("Não existe fila");
        }
        throw new IllegalArgumentException("Paciente não existe");
    }
    public void remover(Integer index){
        if(index instanceof Integer){
            if(index>-1){
                pacientesFila.remove(index);
            }
        }
        else{
            throw new IllegalArgumentException("Index inválido");
        }
    }
    public void update(String nome, Pacientes novo) throws Exception {
        if(!nome.isBlank()) {
            Integer indexUpdate = getPacienteNaFila(nome);
            pacientesFila.set(indexUpdate, novo);
        }
    }

    public Integer getPacienteNaFila(String nome) throws Exception {
        for (int i=0; i< pacientesFila.size(); i++){
            if(Objects.equals(pacientesFila.get(i).getNome(), nome)){
                return i;
            }
        }
        throw new Exception("Paciente não está na lista");
    }

    public Pacientes pop(){
        if(!pacientesFila.isEmpty()){
            Pacientes proximo = pacientesFila.get(0);
            pacientesFila.remove(0);
            return proximo;
        }
        throw new RuntimeException("Fila vazia!!");
    }

    //Parte logica aqui abaixo




}
