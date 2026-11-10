package br.com.ordensservico.aberturaordensservico.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class OrdemServicoRequest {
    
    @NotBlank (message = "A descrição não pode ser nula ou vazia")
    String descricao;

    @NotNull (message = "O ID do equipamento não pode ser nulo")
    Integer equipamentoId;

    public OrdemServicoRequest() {
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getEquipamentoId() {
        return equipamentoId;
    }

    
}
