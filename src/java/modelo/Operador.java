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
public class Operador extends Empleado {

    public Operador() {
    }

    public Operador(String correo, String nombre, String contraseña, String cargo, Sede sede) {
        super(correo, nombre, contraseña, cargo, sede);
    }

}
