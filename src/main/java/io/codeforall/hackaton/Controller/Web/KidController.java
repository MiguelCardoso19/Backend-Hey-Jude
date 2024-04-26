package io.codeforall.hackaton.Controller.Web;

import io.codeforall.hackaton.Exception.KidNotFoundException;
import io.codeforall.hackaton.command.KidDto;
import io.codeforall.hackaton.converter.KidToKidDto;
import io.codeforall.hackaton.converter.KidDtoToKid;
import io.codeforall.hackaton.persistence.model.Kid;
import io.codeforall.hackaton.service.KidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Controller
@RequestMapping("/kid")
public class KidController {

    private KidService kidService;

    private KidToKidDto kidToKidDto;
    private KidDtoToKid kidDtoToKid;


    @Autowired
    public void setKidService(KidService kidService) {
        this.kidService = kidService;
    }


    @Autowired
    public void setKidToKidDto(KidToKidDto kidToKidDto) {
        this.kidToKidDto = kidToKidDto;
    }


    @Autowired
    public void setKidDtoToKid(KidDtoToKid kidDtoToKid) {
        this.kidDtoToKid = kidDtoToKid;
    }


    @RequestMapping(method = RequestMethod.GET, path = {"/list", "/", ""})
    public String listKids(Model model) {
        model.addAttribute("kids", kidToKidDto.convert(kidService.list()));
        return "kid/list";
    }


    @RequestMapping(method = RequestMethod.GET, path = "/add")
    public String addCustomer(Model model) {
        model.addAttribute("kid", new KidDto());
        return "kid/add-update";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}/edit")
    public String editCustomer(@PathVariable Integer id, Model model) {
        model.addAttribute("kid", kidToKidDto.convert(kidService.get(id)));
        return "kid/add-update";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public String showKid(@PathVariable Integer id, Model model) throws Exception {

        Kid kid = kidService.get(id);

        model.addAttribute("kids", kidToKidDto.convert(kid));

        return "kid/show";
    }


    @RequestMapping(method = RequestMethod.POST, path = {"/", ""}, params = "action=save")
    public String saveKid(@Valid @ModelAttribute("kid") KidDto kidDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "kid/add-update";
        }

        Kid savedKid = kidService.save(kidDtoToKid.convert(kidDto));

        redirectAttributes.addFlashAttribute("lastAction", "Saved " + savedKid.getName());
        return "redirect:/kid/" + savedKid.getId();
    }

    @RequestMapping(method = RequestMethod.POST, path = {"/", ""}, params = "action=cancel")
    public String cancelSaveKid() {
        return "redirect:/kid/";
    }

    @RequestMapping(method = RequestMethod.GET, path = "{id}/delete")
    public String deleteKid(@PathVariable Integer id, RedirectAttributes redirectAttributes) throws KidNotFoundException {
        Kid kid = kidService.get(id);
        kidService.delete(id);
        redirectAttributes.addFlashAttribute("lastAction", "Deleted " + kid.getName());
        return "redirect:/kid";
    }
}
