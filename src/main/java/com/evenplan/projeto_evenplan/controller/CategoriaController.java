package com.evenplan.projeto_evenplan.controller;

// Alunas: Giulia Lain, Isabelle de Oliveira e Lauren Amici

import com.evenplan.projeto_evenplan.model.Categoria;
import com.evenplan.projeto_evenplan.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    // ── LISTAGEM ──────────────────────────────────────────────
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("categorias", categoriaService.listarTodas());
        return "categorias/listar";
    }

    // ── FORMULÁRIO NOVA CATEGORIA ─────────────────────────────
    @GetMapping("/nova")
    public String novaForm(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "categorias/form";
    }

    // ── FORMULÁRIO EDITAR CATEGORIA ───────────────────────────
    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Long id, Model model) {
        Categoria categoria = categoriaService.buscarPorId(id).orElseThrow();
        model.addAttribute("categoria", categoria);
        return "categorias/form";
    }

    // ── SALVAR (nova ou edição) ───────────────────────────────
    @PostMapping("/salvar")
    public String salvar(
            @RequestParam(required = false) Long id,
            @RequestParam String nome,
            @RequestParam(required = false) String descricao) {

        Categoria categoria = (id != null) ? categoriaService.buscarPorId(id).orElseThrow() : new Categoria();
        categoria.setNome(nome);
        categoria.setDescricao(descricao);
        categoriaService.salvar(categoria);
        return "redirect:/categorias";
    }

    // ── EXCLUIR CATEGORIA ─────────────────────────────────────
    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        categoriaService.excluir(id);
        return "redirect:/categorias";
    }
}
