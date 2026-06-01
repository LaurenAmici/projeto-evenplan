package com.evenplan.projeto_evenplan.repository;

// Alunas: Giulia Lain, Isabelle de Oliveira e Lauren Amici

import com.evenplan.projeto_evenplan.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {

    List<Evento> findByNomeContainingIgnoreCase(String nome);

    List<Evento> findByCategoriaId(Long categoriaId);
}