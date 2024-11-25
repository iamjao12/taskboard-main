package com.taskboard.taskboard.controllers;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.taskboard.taskboard.models.Membro;
import com.taskboard.taskboard.models.Projeto;
import com.taskboard.taskboard.services.MembroService;
import com.taskboard.taskboard.services.ProjetoService;

@Controller
@RequestMapping("/membros")
public class MembrosController{

    private final MembroService ms; 
    private final ProjetoService ps;

    public MembrosController(MembroService ms, ProjetoService ps) {
        this.ms = ms;
        this.ps = ps;
    }

    @GetMapping("/novo/{idProjeto}")
    public ModelAndView novo(@PathVariable String idProjeto) {
        ModelAndView mv = new ModelAndView("/membros/novo.html");
        Membro membro = new Membro();
        Projeto projeto = ps.buscaPorId(idProjeto)
            .orElseThrow(() -> new IllegalArgumentException("Projeto não encontrado"));
        membro.setProjeto(projeto);
        mv.addObject("membro", membro);
        mv.addObject("projeto", projeto);
        
        return mv;
    }

    @PostMapping("/novo/{idProjeto}")
    public ModelAndView novo(Membro obj, @PathVariable String idProjeto) {
        ModelAndView mv = new ModelAndView("redirect:/membros/listar/{idProjeto}");
        Projeto projeto = ps.buscaPorId(idProjeto)
            .orElseThrow(() -> new IllegalArgumentException("Projeto não encontrado"));
        obj.setProjeto(projeto);
        obj = ms.novo(obj);
        mv.addObject("membro", obj);
        return mv;
    }


    @GetMapping("/listar/{idProjeto}")
    public ModelAndView linkarProjeto(@PathVariable String idProjeto) {
        ModelAndView mv = new ModelAndView("/membros/index.html");
        Optional<Projeto> optionalProjeto = ps.buscaPorId(idProjeto);
        if (optionalProjeto.isPresent()) {
            Projeto projeto = optionalProjeto.get();
            List<Membro> membros = ms.findByProjetoId(projeto.getId());
            mv.addObject("projeto", projeto);
            mv.addObject("membros", membros);
        } else {
            mv.addObject("error", "Projeto não encontrado");
        }
        return mv;
    }

    @GetMapping("/editar/{chave}")
    public ModelAndView editar(@PathVariable String chave) {
        ModelAndView mv = new ModelAndView("membros/editar.html");
        mv.addObject("membro", ms.buscaPorId(chave));
        return mv;
    }

    @PostMapping("/editar")
    public ModelAndView editar(Membro obj, String chave) {
        obj = ms.atualizar(obj);
        ModelAndView mv = new ModelAndView("redirect:/projetos/");
        mv.addObject("membro", obj);
        return mv;
    }

    @GetMapping("/excluir/{chave}")
    public ModelAndView excluir(@PathVariable String chave) {
        Optional<Membro> membro = ms.buscaPorId(chave);
        ModelAndView mv;
        if (membro.isPresent()){
            mv = new ModelAndView("membros/excluir.html");
            mv.addObject("membro", membro);
        }
        else{
            mv = new ModelAndView("redirect:/projetos/");
        }
        return mv;
    }

    
    @PostMapping("/excluir")
    public ModelAndView excluir(Membro obj, String chave) {
        ModelAndView mv  = new ModelAndView("redirect:/projetos/");
        ms.excluir(obj);
        return mv;
    }
    
}