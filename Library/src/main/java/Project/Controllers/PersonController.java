package Project.Controllers;

import Project.DAO.PersonDAO;
import Project.Models.Person;
import Project.utils.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PersonController {
    private final PersonValidator personValidator;
    private final PersonDAO personDAO;
    @Autowired
    public PersonController(PersonValidator personValidator, PersonDAO personDAO){
        this.personValidator = personValidator;
        this.personDAO = personDAO;
    }
    @GetMapping()
    public String index(Model model){
        model.addAttribute("people",personDAO.index());

        return "people/index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id")int id, Model model){
        model.addAttribute("person",personDAO.show(id));
        model.addAttribute("books",personDAO.getPersonBooks(id));
        return "people/show";
    }
    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult){
        personValidator.validate(person,bindingResult);
        if(bindingResult.hasErrors()){
            return "people/new";
        }
        else{
            personDAO.save(person);
            return "redirect:/people";
        }
    }
    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person){
        return "people/new";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        Person person = personDAO.show(id);
        model.addAttribute("person",person);
        return "people/edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person,
                         @PathVariable("id") int id,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "/people/edit";
        }
        else{
            personDAO.update(id,person);
            return "redirect:/people";
        }
    }
    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") int id){
        personDAO.delete(id);
        return "redirect:/people";
    }
}
