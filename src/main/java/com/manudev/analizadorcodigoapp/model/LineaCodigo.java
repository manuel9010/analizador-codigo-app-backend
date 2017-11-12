package com.manudev.analizadorcodigoapp.model;

public class LineaCodigo {
    
    private String contenido;
    private boolean valida;
    
    

    public LineaCodigo(String contenido, boolean valida) {
        this.contenido = contenido;
        this.valida = valida;
    }
    
    

    /**
     * @return the contenido
     */
    public String getContenido() {
        return contenido;
    }

    /**
     * @param contenido the contenido to set
     */
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    /**
     * @return the valida
     */
    public boolean isValida() {
        return valida;
    }

    /**
     * @param valida the valida to set
     */
    public void setValida(boolean valida) {
        this.valida = valida;
    }
    
}