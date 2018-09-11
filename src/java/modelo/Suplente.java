/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Jhoan Saavedra
 */
public class Suplente extends Empleado {

    private Operador operador;
    private String fechaInicio;
    private String fechaFin;

    public Suplente() {
    }

    public Suplente(String correo, String nombre, String contraseña, String cargo, Operador operador, String fechaInicio, String fechaFin) {
        super(correo, nombre, contraseña, cargo);
        this.operador = operador;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public Operador getOperador() {
        return operador;
    }

    public void setOperador(Operador operador) {
        this.operador = operador;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

}
