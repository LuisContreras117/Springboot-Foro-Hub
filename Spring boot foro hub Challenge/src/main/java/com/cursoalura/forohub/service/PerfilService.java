package com.cursoalura.forohub.service;

import com.cursoalura.forohub.dto.perfil.DetallePerfilDTO;
import com.cursoalura.forohub.repository.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerfilService {
    @Autowired
    private PerfilRepository perfilRepository;

    public List<DetallePerfilDTO> listar() {
        return perfilRepository.findAll().stream()
                .map(DetallePerfilDTO::new)
                .toList();
    }
}
