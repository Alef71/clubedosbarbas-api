package br.com.clubedosbarbas.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.clubedosbarbas.api.domain.Barbeiro.repository.BarbeiroRepository;
import br.com.clubedosbarbas.api.domain.BloqueioHorario.BloqueioHorario;
import br.com.clubedosbarbas.api.domain.BloqueioHorario.dto.DadosCadastroBloqueio;
import br.com.clubedosbarbas.api.domain.BloqueioHorario.repository.BloqueioHorarioRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("bloqueios-horario")
public class BloqueioHorarioController {

    @Autowired
    private BloqueioHorarioRepository bloqueioHorarioRepository;
    @Autowired
    private BarbeiroRepository barbeiroRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<Void> cadastrar(@RequestBody @Valid DadosCadastroBloqueio dados) {
        var barbeiro = barbeiroRepository.getReferenceById(dados.idBarbeiro());
        var bloqueio = new BloqueioHorario(null, barbeiro, dados.dataHoraInicio(), dados.dataHoraFim(), dados.motivo()); // <-- CORRIGIDO AQUI
        bloqueioHorarioRepository.save(bloqueio);
        return ResponseEntity.ok().build();
    }
}