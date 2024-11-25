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
import com.taskboard.taskboard.models.Membro;
import com.taskboard.taskboard.models.Projeto;
import com.taskboard.taskboard.services.GerenteService;
import com.taskboard.taskboard.services.MembroService;
import com.taskboard.taskboard.services.ProjetoService;

@Controller
@RequestMapping("/projetos")
public class ProjetosController{

    private final ProjetoService ps; 
    private final GerenteService gs;
    private final MembroService ms;

    public ProjetosController(ProjetoService ps, GerenteService gs, MembroService ms) {
        this.ps = ps;
        this.gs = gs;
        this.ms = ms;
    }

    @GetMapping("/novo")
    public ModelAndView novo() {
        ModelAndView mv = new ModelAndView("/projetos/novo.html");
        mv.addObject("projeto", new Projeto());
        return mv;
    }

    @PostMapping("/novo")
    public ModelAndView novo(Projeto obj) {
      obj = ps.novo(obj);
      ModelAndView mv = new ModelAndView("redirect:/projetos/");
      mv.addObject("projeto", obj);
      return mv;
    }

    @GetMapping("/")
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("index.html"); 
        mv.addObject("projetos", ps.todos());
        return mv;
    }
    
    @GetMapping("/editar/{chave}")
    public ModelAndView editar(@PathVariable String chave) {
        ModelAndView mv = new ModelAndView("projetos/editar.html");
        mv.addObject("projeto", ps.buscaPorId(chave));
        return mv;
    }

    @PostMapping("/editar")
    public ModelAndView editar(Projeto obj, String id) {
        ModelAndView mv  = new ModelAndView("redirect:/projetos/");
        obj = ps.atualizar(obj);
        mv.addObject("projeto",obj);
        return mv;
    }

    @GetMapping("/detalhar/{chave}")
    public ModelAndView detalhes(@PathVariable String chave) {
        ModelAndView mv = new ModelAndView("projetos/detalhar.html");
        Projeto projeto = ps.buscaPorId(chave)
                            .orElseThrow(() -> new RuntimeException("Projeto não encontrado"));
        List<Gerente> gerentes = gs.findByProjetoId(chave);
        List<Membro> membros = ms.findByProjetoId(chave);
        mv.addObject("projeto", projeto);
        mv.addObject("gerentes", gerentes);
        mv.addObject("membros", membros);
        return mv;
    }


    @GetMapping("/excluir/{chave}")
    public ModelAndView excluir(@PathVariable String chave) {
        Optional<Projeto> projeto = ps.buscaPorId(chave);
        ModelAndView mv;
        if (projeto.isPresent()){
            mv = new ModelAndView("projetos/excluir.html");
            mv.addObject("projeto", projeto);
        }
        else{
            mv = new ModelAndView("redirect:/projetos/");
        }
        return mv;
    }

    
    @PostMapping("/excluir")
    public ModelAndView excluir(Projeto obj, String chave) {
        ModelAndView mv  = new ModelAndView("redirect:/projetos/");
        ps.excluir(obj);
        return mv;
    }
    
}