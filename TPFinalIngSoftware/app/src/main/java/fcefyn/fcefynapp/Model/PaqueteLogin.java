package fcefyn.fcefynapp.Model;


import fcefyn.fcefynapp.Interfaces.Paquete;

public class PaqueteLogin implements Paquete {
    private String email;
    private String password;
    public PaqueteLogin(String email, String encriptado){
        this.email=email;
        this.password=encriptado;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
