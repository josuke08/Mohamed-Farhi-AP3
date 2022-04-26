package com.example.atelier3.web;

import com.example.atelier3.Entites.Medecin;
import com.example.atelier3.Entites.Patient;
import com.example.atelier3.Repositories.MedecinRepository;
import com.example.atelier3.Repositories.PatientRepository;
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
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@AllArgsConstructor
public class PatientController {
    @Autowired
    private PatientRepository patientRepository;
    /*@Autowired
    private MedecinRepository medecinRepository;*/

    @GetMapping(path = "/user/index")
    public String patients(Model model,
                           @RequestParam(name = "page", defaultValue = "0") int page,
                           @RequestParam(name = "size", defaultValue = "10")int size,
                           @RequestParam(name = "keyword", defaultValue = "")String keyword,
                           @RequestParam(name = "malade", defaultValue = "off")boolean malade,
                           @RequestParam(name = "dateNaissance", defaultValue = "") String dateNaissance) throws ParseException {
        Date date = (!dateNaissance.equals(""))? new SimpleDateFormat("yyyy-MM-dd").parse(dateNaissance): null;
        Page<Patient> pagePatients;
        if(malade == true && date == null)
            pagePatients = patientRepository.findByNomContainsAndMalade(keyword, true, PageRequest.of(page, size));
        else if (malade == true && date != null)
            pagePatients = patientRepository.findByNomContainsAndMaladeAndDateNaissance(keyword, true, date, PageRequest.of(page, size));
        else if (malade != true && date != null)
            pagePatients = patientRepository.findByNomContainsAndDateNaissance(keyword, date, PageRequest.of(page, size));
        else
            pagePatients = patientRepository.findByNomContains(keyword, PageRequest.of(page, size));
        model.addAttribute("listPatients", pagePatients.getContent());
        model.addAttribute("pages", new int[pagePatients.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        model.addAttribute("malade", malade);
        model.addAttribute("dateNaissance", dateNaissance);
        return "patient/patients";
    }

    @GetMapping("/admin/delete")
    public String delete(Long id, String keyword, int page, boolean malade){
        patientRepository.deleteById(id);
        return "redirect:/user/index?page="+page+"&keyword="+keyword+"&malade="+malade;
    }

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/user/patients")
    @ResponseBody
    public List<Patient> listPatients(){
        return patientRepository.findAll();
    }

    @GetMapping("/admin/formPatients")
    public String formPatients( Model model){
        model.addAttribute("patient",new Patient());
        return "patient/formPatients";

    }
    @PostMapping(path="/admin/save")
    public String save(Model model, @Valid Patient patient, BindingResult bindingResult,
                       @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "") String keyword,
                       @RequestParam(defaultValue = "off") boolean malade){
        if(bindingResult.hasErrors()) return "patient/formPatients";
        patientRepository.save(patient);
        return "redirect:/user/index?page="+page+"&keyword="+keyword+"&malade="+malade;
    }

    @GetMapping("/admin/editPatient")
    public String editPatient( Model model,Long id,String keyword,int page, boolean malade){
        Patient patient=patientRepository.findById(id).orElse(null);
        if(patient==null) throw new RuntimeException("Patient introuvable");
        model.addAttribute("patient",patient);
        model.addAttribute("page",page);
        model.addAttribute("keyword",keyword);
        model.addAttribute("malade",malade);
        return "patient/editPatient";

    }

}
