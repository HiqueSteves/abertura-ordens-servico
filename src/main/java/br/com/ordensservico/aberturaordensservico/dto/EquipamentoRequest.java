package br.com.ordensservico.aberturaordensservico.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class EquipamentoRequest {
    @NotNull
    private Integer setorId;

    @NotBlank
    private String nome;

    @NotBlank
    private String numeroPatrimonio;

    public EquipamentoRequest() {
        
    }

    public Integer getSetorId() {
        return setorId;
    }

     
    public void setSetorId(Integer setorId) {
        this.setorId = setorId;
    }

    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumeroPatrimonio() {
        return numeroPatrimonio;
    }

    public void setNumeroPatrimonio(String numeroPatrimonio) {
        this.numeroPatrimonio = numeroPatrimonio;
    }
}
