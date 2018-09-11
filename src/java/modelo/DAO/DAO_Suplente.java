/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.DAO;

import Estructura.Arbol_Archivo_IdString;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import modelo.Operador;
import modelo.Suplente;

/**
 *
 * @author Jhoan Saavedra
 */
public class DAO_Suplente implements DAO<Suplente> {

    private RandomAccessFile archivo;
    private Arbol_Archivo_IdString arbol;

    public DAO_Suplente() throws FileNotFoundException {
        archivo = new RandomAccessFile("Empleado", "rw");
        arbol = new Arbol_Archivo_IdString("Empleado");
    }

    @Override
    public boolean crear(Suplente suplente) throws FileNotFoundException, IOException {
        archivo.seek(archivo.length());
        Operador operador = (new DAO_Operador()).buscar(suplente.getOperador().getCorreo());

        if (operador != null && arbol.añadir(suplente.getCorreo(), (int) archivo.length())) {
            archivo.writeUTF(suplente.getCorreo());
            archivo.writeUTF(suplente.getNombre());
            archivo.writeUTF(suplente.getContraseña());
            archivo.writeUTF(suplente.getCargo());
            archivo.writeUTF(operador.getCorreo());
            archivo.writeUTF(suplente.getFechaInicio());
            archivo.writeUTF(suplente.getFechaFin());
            archivo.writeInt(operador.getSede().getCodigo());
            return true;
        }
        return false;

    }

    @Override
    public Suplente buscar(Object correo) throws FileNotFoundException, IOException {
        int pos = (int) arbol.getPosArchivo((String) correo);
        if (pos != -1) {
            archivo.seek(pos);
            return new Suplente(archivo.readUTF(), archivo.readUTF(), archivo.readUTF(), archivo.readUTF(), (new DAO_Operador()).buscar(archivo.readUTF()), archivo.readUTF(), archivo.readUTF());

        }

        return null;

    }

    @Override
    public boolean actualizar(Suplente suplente) throws FileNotFoundException, IOException {

        int pos = (int) arbol.getPosArchivo(suplente.getCorreo());
        Operador operador = (new DAO_Operador()).buscar(suplente.getOperador().getCorreo());
        if (pos != -1 && operador != null) {
            archivo.writeUTF(suplente.getCorreo());
            archivo.writeUTF(suplente.getNombre());
            archivo.writeUTF(suplente.getContraseña());
            archivo.writeUTF(suplente.getCargo());
            archivo.writeUTF(operador.getCorreo());
            archivo.writeUTF(suplente.getFechaInicio());
            archivo.writeUTF(suplente.getFechaFin());
            archivo.writeInt(operador.getSede().getCodigo());
            return true;
        }
        return false;

    }

    @Override
    public boolean eliminar(Object correo) throws FileNotFoundException, IOException {
        if (archivo.length() != 0 && arbol.eliminar((String) correo)) {
            return true;
        }
        return false;

    }

}
