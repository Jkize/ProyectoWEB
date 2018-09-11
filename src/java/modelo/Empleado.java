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
public class Empleado {

    private String correo;
    private String nombre;
    private String contraseña;
    private String cargo;
    private Sede sede;

    public Empleado() {
    }

    public Empleado(String correo, String nombre, String contraseña, String cargo, Sede sede) {
        this.correo = correo;
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.cargo = cargo;
        this.sede = sede;
    }

    public Empleado(String correo, String nombre, String contraseña, String cargo) {
        this.correo = correo;
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.cargo = cargo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Sede getSede() {
        return sede;
    }

    public void setSede(Sede sede) {
        this.sede = sede;
    }

}
