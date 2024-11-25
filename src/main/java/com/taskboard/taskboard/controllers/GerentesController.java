package com.taskboard.taskboard.controllers;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.taskboard.taskboard.models.Gerente;
import com.taskboard.taskboard.models.Projeto;
import com.taskboard.taskboard.services.GerenteService;
import com.taskboard.taskboard.services.ProjetoService;

@Controller
@RequestMapping("/gerentes")
public class GerentesController{

    private final GerenteService gs; 
    private final ProjetoService ps;

    public GerentesController(GerenteService gs, ProjetoService ps) {
        this.gs = gs;
        this.ps = ps;
    }

    @GetMapping("/novo/{idProjeto}")
    public ModelAndView novo(@PathVariable String idProjeto) {
        ModelAndView mv = new ModelAndView("/gerentes/novo.html");
        Gerente gerente = new Gerente();
        Projeto projeto = ps.buscaPorId(idProjeto)
            .orElseThrow(() -> new IllegalArgumentException("Projeto não encontrado"));
        gerente.setProjeto(projeto);
        mv.addObject("gerente", gerente);
        mv.addObject("projeto", projeto);
        
        return mv;
    }

    @PostMapping("/novo/{idProjeto}")
    public ModelAndView novo(Gerente obj, @PathVariable String idProjeto) {
        ModelAndView mv = new ModelAndView("redirect:/gerentes/listar/{idProjeto}");
        Projeto projeto = ps.buscaPorId(idProjeto)
            .orElseThrow(() -> new IllegalArgumentException("Projeto não encontrado"));
        obj.setProjeto(projeto);
        obj = gs.novo(obj);
        mv.addObject("gerente", obj);
        return mv;
    }


    @GetMapping("/listar/{idProjeto}")
    public ModelAndView linkarProjeto(@PathVariable String idProjeto) {
        ModelAndView mv = new ModelAndView("/gerentes/index.html");
        Optional<Projeto> optionalProjeto = ps.buscaPorId(idProjeto);
        if (optionalProjeto.isPresent()) {
            Projeto projeto = optionalProjeto.get();
            List<Gerente> gerentes = gs.findByProjetoId(projeto.getId());
            mv.addObject("projeto", projeto);
            mv.addObject("gerentes", gerentes);
        } else {
            mv.addObject("error", "Projeto não encontrado");
        }
        return mv;
    }

    @GetMapping("/editar/{chave}")
    public ModelAndView editar(@PathVariable String chave) {
        ModelAndView mv = new ModelAndView("gerentes/editar.html");
        mv.addObject("gerente", gs.buscaPorId(chave));
        return mv;
    }

    @PostMapping("/editar")
    public ModelAndView editar(Gerente obj, String chave) {
        obj = gs.atualizar(obj);
        ModelAndView mv = new ModelAndView("redirect:/projetos/");
        mv.addObject("gerente", obj);
        return mv;
    }

    @GetMapping("/excluir/{chave}")
    public ModelAndView excluir(@PathVariable String chave) {
        Optional<Gerente> gerente = gs.buscaPorId(chave);
        ModelAndView mv;
        if (gerente.isPresent()){
            mv = new ModelAndView("gerentes/excluir.html");
            mv.addObject("gerente", gerente);
        }
        else{
            mv = new ModelAndView("redirect:/projetos/");
        }
        return mv;
    }

    
    @PostMapping("/excluir")
    public ModelAndView excluir(Gerente obj, String chave) {
        ModelAndView mv  = new ModelAndView("redirect:/projetos/");
        gs.excluir(obj);
        return mv;
    }
    
}