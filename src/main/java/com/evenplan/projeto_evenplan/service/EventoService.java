package com.evenplan.projeto_evenplan.service;

import com.evenplan.projeto_evenplan.model.Evento;
import com.evenplan.projeto_evenplan.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    public List<Evento> listarTodos() {
        return eventoRepository.findAll();
    }

    public Optional<Evento> buscarPorId(Long id) {
        return eventoRepository.findById(id);
    }

    public List<Evento> buscarPorNome(String nome) {
        return eventoRepository.findByNomeContainingIgnoreCase(nome);
    }

    public List<Evento> listarPorCategoria(Long categoriaId) {
        return eventoRepository.findByCategoriaId(categoriaId);
    }

    public void salvar(Evento evento) {
        eventoRepository.save(evento);
    }

    public void excluir(Long id) {
        eventoRepository.deleteById(id);
    }
}