package com.evenplan.projeto_evenplan.controller;

// Alunas: Giulia Lain, Isabelle de Oliveira e Lauren Amici

import com.evenplan.projeto_evenplan.model.Evento;
import com.evenplan.projeto_evenplan.service.CategoriaService;
import com.evenplan.projeto_evenplan.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;

@Controller
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @Autowired
    private CategoriaService categoriaService;

    // ── LISTAGEM ──────────────────────────────────────────────
    @GetMapping
    public String listar(
            @RequestParam(required = false) String busca,
            @RequestParam(required = false) Long categoriaId,
            Model model) {

        if (busca != null && !busca.isBlank()) {
            model.addAttribute("eventos", eventoService.buscarPorNome(busca));
            model.addAttribute("busca", busca);
        } else if (categoriaId != null) {
            model.addAttribute("eventos", eventoService.listarPorCategoria(categoriaId));
            model.addAttribute("categoriaAtiva", categoriaService.buscarPorId(categoriaId).orElse(null));
        } else {
            model.addAttribute("eventos", eventoService.listarTodos());
        }

        model.addAttribute("categorias", categoriaService.listarTodas());
        return "eventos/listar";
    }

    // ── FORMULÁRIO NOVO EVENTO ────────────────────────────────
    @GetMapping("/novo")
    public String novoForm(Model model) {
        model.addAttribute("evento", new Evento());
        model.addAttribute("categorias", categoriaService.listarTodas());
        return "eventos/form";
    }

    // ── FORMULÁRIO EDITAR EVENTO ──────────────────────────────
    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Long id, Model model) {
        Evento evento = eventoService.buscarPorId(id).orElseThrow();
        model.addAttribute("evento", evento);
        model.addAttribute("categorias", categoriaService.listarTodas());
        return "eventos/form";
    }

    // ── DETALHES DO EVENTO ────────────────────────────────────
    @GetMapping("/{id}")
    public String detalhes(@PathVariable Long id, Model model) {
        Evento evento = eventoService.buscarPorId(id).orElseThrow();
        model.addAttribute("evento", evento);
        return "eventos/detalhes";
    }

    // ── SALVAR (novo ou edição) ───────────────────────────────
    @PostMapping("/salvar")
    public String salvar(
            @RequestParam(required = false) Long id,
            @RequestParam String nome,
            @RequestParam String local,
            @RequestParam String data,
            @RequestParam String hora,
            @RequestParam Long categoriaId,
            @RequestParam(required = false) String descricao) {

        Evento evento = (id != null) ? eventoService.buscarPorId(id).orElseThrow() : new Evento();
        evento.setNome(nome);
        evento.setLocal(local);
        evento.setDescricao(descricao);
        evento.setData(LocalDateTime.of(
                LocalDate.parse(data),
                LocalTime.parse(hora)
        ));
        evento.setCategoria(categoriaService.buscarPorId(categoriaId).orElseThrow());

        eventoService.salvar(evento);
        return "redirect:/eventos";
    }

    // ── EXCLUIR EVENTO ────────────────────────────────────────
    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        eventoService.excluir(id);
        return "redirect:/eventos";
    }
}
