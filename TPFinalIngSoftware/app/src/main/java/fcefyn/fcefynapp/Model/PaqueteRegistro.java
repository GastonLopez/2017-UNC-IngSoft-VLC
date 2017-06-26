package fcefyn.fcefynapp.Model;


import fcefyn.fcefynapp.Interfaces.Paquete;

public class PaqueteRegistro implements Paquete {
    private String email;
    private String password;
    private String nombre;

    public PaqueteRegistro(String email, String encriptado, String nombre){
        this.email=email;
        this.password=encriptado;
        this.nombre=nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getNombre() {
        return nombre;
    }
}
