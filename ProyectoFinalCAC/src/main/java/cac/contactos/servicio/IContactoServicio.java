package cac.contactos.servicio;

import cac.contactos.modelo.Contacto;
import java.util.List;

public interface IContactoServicio {
    public List<Contacto> listarContactos();
    public Contacto buscarContactoporID(Integer idContacto);
    public void guardarContacto(Contacto contacto);
    public void eliminarContacto(Contacto contacto);
}
