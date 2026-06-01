package com.evenplan.projeto_evenplan.repository;

// Alunas: Giulia Lain, Isabelle de Oliveira e Lauren Amici

import com.evenplan.projeto_evenplan.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}