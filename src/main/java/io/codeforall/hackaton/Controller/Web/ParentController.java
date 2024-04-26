package io.codeforall.hackaton.Controller.Web;
import io.codeforall.hackaton.Exception.KidNotFoundException;
import io.codeforall.hackaton.command.ParentDto;
import io.codeforall.hackaton.converter.KidToKidDto;
import io.codeforall.hackaton.converter.ParentDtoToParent;
import io.codeforall.hackaton.converter.ParentToParentDto;
import io.codeforall.hackaton.persistence.model.Kid;
import io.codeforall.hackaton.persistence.model.Parent;
import io.codeforall.hackaton.service.KidService;
import io.codeforall.hackaton.service.ParentService;
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
import java.util.List;

@Controller
@RequestMapping("/kid")
public class ParentController {

        private ParentService parentService;
        private KidService kidService;
        private ParentToParentDto parentToParentDto;
        private ParentDtoToParent parentDtoToParent;
        private KidToKidDto kidToKidDto;

        @Autowired
        public void setParentService(ParentService parentService) {
            this.parentService = parentService;
        }


        @Autowired
        public void setKidService(KidService kidService) {
            this.kidService = kidService;
        }

        @Autowired
        public void setParentToParentDto(ParentToParentDto parentToParentDto) {
            this.parentToParentDto = parentToParentDto;
        }


        @Autowired
        public void setParentDtoToParent(ParentDtoToParent parentDtoToParent) {
            this.parentDtoToParent = parentDtoToParent;
        }


        @Autowired
        public void setKidToKidDto(KidToKidDto kidToKidDto) {
            this.kidToKidDto = kidToKidDto;
        }

        @RequestMapping(method = RequestMethod.GET, path = {"/{cid}/parent", "/{cid}/parent/list"})
        public String listParents(@PathVariable Integer cid, Model model) {

            try {

                List<Parent> parents = kidService.listParents(cid);
                Kid kid = kidService.get(cid);

                model.addAttribute("parents", parentToParentDto.convert(parents));
                model.addAttribute("kid", kidToKidDto.convert(kid));

                return "parent/list";

            } catch (KidNotFoundException ex) {
                return "redirect:/";
            }
        }

        @RequestMapping(method = RequestMethod.GET, path = "/{cid}/parent/add")
        public String addParent(@PathVariable Integer cid, Model model) {

            model.addAttribute("kid", kidToKidDto.convert(kidService.get(cid)));
            model.addAttribute("parent", new ParentDto());

            return "parent/add-update";
        }

        @RequestMapping(method = RequestMethod.GET, path = "/{cid}/parent/{id}/edit")
        public String editParent(@PathVariable Integer cid, @PathVariable Integer id, Model model) {
            model.addAttribute("kid", kidToKidDto.convert(kidService.get(cid)));
            model.addAttribute("parent", parentToParentDto.convert(parentService.get(id)));
            return "parent/add-update";
        }

        @RequestMapping(method = RequestMethod.POST, path = {"/{cid}/parent/", "/{cid}/parent"}, params = "action=save")
        public String saveParent(Model model, @PathVariable Integer cid, @Valid @ModelAttribute("parent") ParentDto parentDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

            model.addAttribute("kid", kidToKidDto.convert(kidService.get(cid)));

            if (bindingResult.hasErrors()) {
                return "parent/add-update";
            }

            try {

                Parent parent = parentDtoToParent.convert(parentDto);
                kidService.addParent(cid, parent);

                redirectAttributes.addFlashAttribute("lastAction", "Saved " + parentDto.getName());
                return "redirect:/kid/" + cid + "/parent";

            } catch (KidNotFoundException ex) {

                return "redirect:/kid/" + cid;
            }
        }

        @RequestMapping(method = RequestMethod.POST, path = {"/{cid}/parent/", "/{cid}/parent"}, params = "action=cancel")
        public String cancelSaveParent(@PathVariable Integer cid) {

            return "redirect:/kid/" + cid;
        }
}

