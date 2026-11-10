package br.com.ordensservico.aberturaordensservico.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.ordensservico.aberturaordensservico.model.Equipamento;
import br.com.ordensservico.aberturaordensservico.model.OrdemServico;
import br.com.ordensservico.aberturaordensservico.repository.EquipamentoRepository;
import br.com.ordensservico.aberturaordensservico.repository.OrdemServicoRepository;

@Service 
public class OrdemServicoService {
    private final OrdemServicoRepository ordemServicoRepository;
    private final EquipamentoRepository equipamentoRepository;

    public OrdemServicoService(OrdemServicoRepository ordemServicoRepository, EquipamentoRepository equipamentoRepository) {
        this.ordemServicoRepository = ordemServicoRepository;
        this.equipamentoRepository = equipamentoRepository;
    }

    public Optional<OrdemServico> cadastrar(Integer equipamentoId, String descricao) {
        Optional<Equipamento> equipamentoOpt = equipamentoRepository.findById(equipamentoId);
        if (equipamentoOpt.isEmpty()) {
            return Optional.empty(); 
        }

        OrdemServico ordemServico = new OrdemServico();
        ordemServico.setEquipamento(equipamentoOpt.get());
        ordemServico.setDescricao(descricao);
        ordemServico.setDataAbertura(LocalDateTime.now());

        return Optional.of(ordemServicoRepository.save(ordemServico));
    }

    public List<OrdemServico> listar() {
        return ordemServicoRepository.findAll();
    }

    public Optional<OrdemServico> buscarPorId(Integer id) {
        return ordemServicoRepository.findById(id);
    }

    public Optional<OrdemServico> atualizar(Integer id, OrdemServico novosDados) {
        Optional<OrdemServico> ordemServicoEncontrada = ordemServicoRepository.findById(id);

        if (ordemServicoEncontrada.isEmpty()) {
            return Optional.empty();
        }

        OrdemServico ordemServico = ordemServicoEncontrada.get();

        ordemServico.setDescricao(novosDados.getDescricao());
        ordemServico.setEquipamento(novosDados.getEquipamento());

        return Optional.of(ordemServicoRepository.save(ordemServico));
    }
}
