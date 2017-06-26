package fcefyn.fcefynapp.Model;



import org.json.JSONObject;

import fcefyn.fcefynapp.Interfaces.Paquete;

public class PublicacionClientSide implements Paquete {
    private String titulo;
    private String descripcion;
    private String clasificador;
    private String id;


    public PublicacionClientSide(String titulo,String id, String clasificador,String descripcion){
        this.titulo=titulo;
        this.id=id;
        this.clasificador=clasificador;
        this.descripcion=descripcion;
    }

    public PublicacionClientSide(JSONObject json){

        try{
            setTitulo(json.getString("title"));
            setId(json.getString("id"));
            setClasificador(json.getString("clasificador"));
            setDescripcion(json.getString("content"));

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public String getTitulo(){
        return titulo;

    }

    public String getId(){
        return id;
    }

    public String getDescripcion(){
        return descripcion;
    }

    public String getClasificacion(){
        return clasificador;
    }



    public void setTitulo(String titulo){
        this.titulo=titulo;
    }
    public void setDescripcion(String descripcion){
        this.descripcion=descripcion;
    }
    public void setClasificador(String titulo){
        this.clasificador=clasificador;
    }
    public void setId(String id){
        this.id=id;
    }
}
