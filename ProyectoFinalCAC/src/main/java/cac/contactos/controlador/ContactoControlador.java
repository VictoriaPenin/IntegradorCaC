package cac.contactos.controlador;

import cac.contactos.modelo.Contacto;
import cac.contactos.servicio.ContactoServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ContactoControlador {

    private static final Logger logguer = LoggerFactory.getLogger(ContactoControlador.class);

    @Autowired
    ContactoServicio contactoServicio;

    @GetMapping("/")
    public String iniciar(ModelMap modelo) {
        List<Contacto> contactos = contactoServicio.listarContactos();
        contactos.forEach((contacto) -> logguer.info(contacto.toString()));
        modelo.put("contactos", contactos);
        return "index"; //index.hmtl
    }

    @GetMapping("/agregar")
    public String mostrarAgregar() {
        return "agregar"; //agregar.hmtl
    }

    @PostMapping("/agregar")
    public String agregar(@ModelAttribute("contactoForm") Contacto contacto) {
        logguer.info("Contacto a agregar:" + contacto);
        contactoServicio.guardarContacto(contacto);
        return "redirect:/"; //redirijo al path de inicio "/"
    }

    @GetMapping("/editar/{id}")
    public String mostrarEditar(@PathVariable(value = "id") int idContacto, ModelMap modelo) {
        Contacto contacto = contactoServicio.buscarContactoporID(idContacto);
        logguer.info("Contacto a editar (mostrar):" + contacto);
        modelo.put("contacto", contacto);
        return "editar"; //editar.hmtl
    }

    @PostMapping("/editar")
    public String editar(@ModelAttribute("contacto") Contacto contacto) {
        logguer.info("Contacto a guardar (editar):" + contacto);
        contactoServicio.guardarContacto(contacto);
        return "redirect:/"; //redirijo al path de inicio "/"
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id") int idContacto) {
        Contacto contacto = new Contacto();
        contacto.setIdContacto(idContacto);
        contactoServicio.eliminarContacto(contacto);
        return "redirect:/"; //redirijo al path de inicio "/"
    }

}//Class
