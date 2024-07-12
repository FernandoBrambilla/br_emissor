
package com.fernando.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Year;
import org.springframework.hateoas.RepresentationModel;

@Entity 
@Table(name = "ncm")
public class NCM extends RepresentationModel<NCM> implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY) 
    private Long id;
    
    private String ncm;
    
    private String descricao;
    
    @JsonFormat(pattern="dd/MM/yyyy")
    @Column (name = "data_inicio")
    @Temporal(TemporalType.DATE)
    private LocalDate dataInicio;
    
    @JsonFormat(pattern="dd/MM/yyyy")
    @Column (name = "data_fim")
    @Temporal(TemporalType.DATE)
    private LocalDate dataFim;
    
    private String ato;
    
    private String numero;
    
    @JsonFormat(pattern="yyyy")
    @Temporal(TemporalType.DATE)
    private Year ano;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNcm() {
        return ncm;
    }

    public void setNcm(String ncm) {
        this.ncm = ncm;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public String getAto() {
        return ato;
    }

    public void setAto(String ato) {
        this.ato = ato;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Year getAno() {
        return ano;
    }

    public void setAno(Year ano) {
        this.ano = ano;
    }
    
    
    
    
}
