package br.com.clubedosbarbas.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.clubedosbarbas.api.domain.Barbeiro.repository.BarbeiroRepository;
import br.com.clubedosbarbas.api.domain.HorarioTrabalho.HorarioTrabalho;
import br.com.clubedosbarbas.api.domain.HorarioTrabalho.dto.DadosCadastroHorarioTrabalho;
import br.com.clubedosbarbas.api.domain.HorarioTrabalho.repository.HorarioTrabalhoRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("horarios-trabalho")
public class HorarioTrabalhoController {

    @Autowired
    private HorarioTrabalhoRepository horarioTrabalhoRepository;
    @Autowired
    private BarbeiroRepository barbeiroRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<Void> cadastrar(@RequestBody @Valid DadosCadastroHorarioTrabalho dados) {
        var barbeiro = barbeiroRepository.getReferenceById(dados.idBarbeiro());
        var horario = new HorarioTrabalho(null, barbeiro, dados.diaDaSemana(), dados.horarioInicio(), dados.horarioFim()); 
        horarioTrabalhoRepository.save(horario);
        return ResponseEntity.ok().build();
    }
}
