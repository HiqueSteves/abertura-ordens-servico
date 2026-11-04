package br.com.ordensservico.aberturaordensservico.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "equipamento")
public class Equipamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "O nome do equipamento é obrigatório")
    private String nome;

    @NotBlank(message = "O número do equipamento é obrigatório")
    private String numeroPatrimonio;

    @ManyToOne 
    @JoinColumn (name = "setor_id", referencedColumnName = "id")
    private Setor setor;

    public Equipamento() {
    }

    public Equipamento(String nome, String numeroPatrimonio, Setor setor) {
        this.nome = nome;
        this.numeroPatrimonio = numeroPatrimonio;
        this.setor = setor;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNumeroPatrimonio(String numeroPatrimonio) {
        this.numeroPatrimonio = numeroPatrimonio;
    }

    public String getNumeroPatrimonio() {
        return numeroPatrimonio;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }
}
