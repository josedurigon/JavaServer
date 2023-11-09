package org.server.entity;


import java.util.Date;

public class Fila {
    private int Posicao;
    private String Id;
    private String Patient_Id;
    private int Priority;
    private String Status;
    private Date AdmissionDateTime;
    private String Nome;
    private String Setor;

    public int getPosicao() {
        return Posicao;
    }

    public void setPosicao(int posicao) {
        Posicao = posicao;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getPatient_Id() {
        return Patient_Id;
    }

    public void setPatient_Id(String patient_Id) {
        Patient_Id = patient_Id;
    }

    public int getPriority() {
        return Priority;
    }

    public void setPriority(int priority) {
        Priority = priority;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public Date getAdmissionDateTime() {
        return AdmissionDateTime;
    }

    public void setAdmissionDateTime(Date admissionDateTime) {
        AdmissionDateTime = admissionDateTime;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getSetor() {
        return Setor;
    }

    public void setSetor(String setor) {
        Setor = setor;
    }
}
