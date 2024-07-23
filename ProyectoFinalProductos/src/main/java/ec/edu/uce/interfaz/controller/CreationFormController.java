package ec.edu.uce.interfaz.controller;

import ec.edu.uce.interfaz.Interfaces.ControlleableId;
import ec.edu.uce.interfaz.Interfaces.ControlleableName;
import ec.edu.uce.interfaz.service.CreationFormService;
import ec.edu.uce.interfaz.state.CreationForm;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fabric")
public class CreationFormController implements ControlleableId {

    private CreationFormService creationFormService = new CreationFormService();

    @Override
    @PostMapping("/creationform")
    public Object save(@RequestBody Object object) {
        return creationFormService.save(object);
    }

    @Override
    @GetMapping("/creationform/{id}")
    public CreationForm findById(@PathVariable Long id) {
        return creationFormService.findById(id);
    }

    @Override
    @PutMapping("/creationform/{id}")
    public Object update(@PathVariable Long id, @RequestBody Object object) {
        return creationFormService.update(id, object);
    }

    @Override
    @DeleteMapping("/creationform/del/{id}")
    public void delete(@PathVariable Long id) {
        creationFormService.delete(id);
    }
}
