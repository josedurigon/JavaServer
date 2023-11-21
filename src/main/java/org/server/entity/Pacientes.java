package org.server.entity;

import java.io.Serializable;

public class Pacientes implements Serializable {

    private String Id;
    private String Nome;
    private String Endereco;
    private String Contato;
    private String Historico_Medico;

    public Pacientes(String nome, String endereco, String contato, String historico_Medico) {
        this.Id = null;
        this.Nome = nome;
        this.Endereco = endereco;
        this.Contato = contato;
        this.Historico_Medico = historico_Medico;
    }
    public Pacientes(){}

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getEndereco() {
        return Endereco;
    }

    public void setEndereco(String endereco) {
        Endereco = endereco;
    }

    public String getContato() {
        return Contato;
    }

    public void setContato(String contato) {
        Contato = contato;
    }

    public String getHistorico_Medico() {
        return Historico_Medico;
    }

    public void setHistorico_Medico(String historico_Medico) {
        Historico_Medico = historico_Medico;
    }

}
