package br.com.clubedosbarbas.api.domain.Barbeiro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.clubedosbarbas.api.domain.Barbeiro.Barbeiro;
import br.com.clubedosbarbas.api.domain.Barbeiro.dto.DadosCadastroBarbeiro;
import br.com.clubedosbarbas.api.domain.Barbeiro.repository.BarbeiroRepository;
import br.com.clubedosbarbas.api.infra.exception.ValidacaoException;

@Service
public class BarbeiroService {

    @Autowired
    private BarbeiroRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Barbeiro cadastrar(DadosCadastroBarbeiro dados) {
        // Validação de username existente
        if (repository.findByUsername(dados.username()) != null) {
            throw new ValidacaoException("Username já existe.");
        }

        // Adiciona a nova validação de celular existente
        if (dados.celular() != null && !dados.celular().isBlank() && repository.existsByCelular(dados.celular())) {
            throw new ValidacaoException("Celular já cadastrado para outro barbeiro.");
        }

        // Criptografa a senha antes de guardar
        var senhaCriptografada = passwordEncoder.encode(dados.senha());
        
        var barbeiro = new Barbeiro(dados);
        barbeiro.setSenha(senhaCriptografada); // Define a senha criptografada

        repository.save(barbeiro);
        return barbeiro;
    }
}