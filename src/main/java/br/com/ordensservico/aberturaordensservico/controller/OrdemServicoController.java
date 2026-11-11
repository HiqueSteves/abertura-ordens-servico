package br.com.ordensservico.aberturaordensservico.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ordensservico.aberturaordensservico.dto.EncerramentoOrdemServicoRequest;
import br.com.ordensservico.aberturaordensservico.dto.OrdemServicoRequest;
import br.com.ordensservico.aberturaordensservico.model.OrdemServico;
import br.com.ordensservico.aberturaordensservico.service.OrdemServicoService;
import jakarta.validation.Valid;




@RestController 
@RequestMapping ("/ordens-servico")
public class OrdemServicoController {

    private final OrdemServicoService ordemServicoService;

    public OrdemServicoController(OrdemServicoService ordemServicoService) {
        this.ordemServicoService = ordemServicoService;
    }

@PostMapping
public ResponseEntity<OrdemServico> cadastrar(@Valid @RequestBody OrdemServicoRequest request) {

        Integer equipamentoId = request.getEquipamentoId();
        String descricao = request.getDescricao();

        if (equipamentoId == null || descricao == null) {
            return ResponseEntity.badRequest().build();
        }

        Optional<OrdemServico> ordemServicoSalva = ordemServicoService.cadastrar(equipamentoId, descricao);

        if (ordemServicoSalva.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ordemServicoSalva.get());
    }

    @GetMapping
    public ResponseEntity<List<OrdemServico>> listar() {
        List<OrdemServico> ordensServico = ordemServicoService.listar();
        return ResponseEntity.ok(ordensServico);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdemServico> buscarPorId(@PathVariable Integer id) {
        Optional<OrdemServico> ordemServico = ordemServicoService.buscarPorId(id);

        if (ordemServico.isPresent()) {
            return ResponseEntity.ok(ordemServico.get());
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping ("/ordens-servico")
    public ResponseEntity<List<OrdemServico>> listarOrdensServico() {
        List<OrdemServico> ordensServico = ordemServicoService.listarAbertas();
        return ResponseEntity.ok(ordensServico);
    }
    
    @GetMapping ("/ordens-servico/abertas")
    public ResponseEntity<List<OrdemServico>> listarOrdensServicoAbertas() {
        List<OrdemServico> ordensServicoAbertas = ordemServicoService.listarAbertas();
        return ResponseEntity.ok(ordensServicoAbertas);
    }    

    @PutMapping("/{id}")
    public ResponseEntity<OrdemServico> atualizar(
            @PathVariable Integer id,
            @Valid @RequestBody OrdemServico novosDados) {
        Optional<OrdemServico> ordemServicoAtualizada = ordemServicoService.atualizar(id, novosDados);

        if (ordemServicoAtualizada.isPresent()) {
            return ResponseEntity.ok(ordemServicoAtualizada.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}/encerramento") 
    public ResponseEntity<OrdemServico> encerrar(
            @PathVariable Integer id,
            @Valid @RequestBody EncerramentoOrdemServicoRequest dados) {
        Optional<OrdemServico> ordemServicoEncerrada = ordemServicoService.encerrar(id, dados);

        if (ordemServicoEncerrada.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(ordemServicoEncerrada.get());
    }
}
