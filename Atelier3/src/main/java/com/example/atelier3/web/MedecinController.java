package com.example.atelier3.web;

import com.example.atelier3.Entites.Medecin;
import com.example.atelier3.Repositories.MedecinRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class MedecinController {
    @Autowired
    private MedecinRepository medecinRepository;

    @GetMapping(path = "/user/medecin/index")
    public String patients(Model model,
                           @RequestParam(name = "page", defaultValue = "0") int page,
                           @RequestParam(name = "size", defaultValue = "10")int size,
                           @RequestParam(name = "keyword", defaultValue = "")String keyword){
        Page<Medecin> pageMedecins = medecinRepository.findByNomContains(keyword, PageRequest.of(page, size));
        model.addAttribute("listMedecins", pageMedecins.getContent());
        model.addAttribute("pages", new int[pageMedecins.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "medecin/medecins";
    }

    @GetMapping("/admin/medecin/delete")
    public String delete(Long id, String keyword, int page){
        medecinRepository.deleteById(id);
        return "redirect:/user/medecin/index?page="+page+"&keyword="+keyword;
    }

    @PostMapping(path="/admin/medecin/save")
    public String save(Model model, @Valid Medecin medecin, BindingResult bindingResult,
                       @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "") String keyword){
        if(bindingResult.hasErrors()) return "medecin/formMedecin";
        medecinRepository.save(medecin);
        return "redirect:/user/medecin/index?page="+page+"&keyword="+keyword;
    }

    @GetMapping("/admin/editMedecin")
    public String edit( Model model,Long id,String keyword,int page){
        Medecin medecin=medecinRepository.findById(id).orElse(null);
        if(medecin==null) throw new RuntimeException("Medecin introuvable");
        model.addAttribute("medecin",medecin);
        model.addAttribute("page",page);
        model.addAttribute("keyword",keyword);
        return "medecin/editMedecin";

    }
    @GetMapping("/admin/formMedecin")
    public String formMedecin( Model model){
        model.addAttribute("medecin",new Medecin());
        return "medecin/formMedecin";

    }
}
