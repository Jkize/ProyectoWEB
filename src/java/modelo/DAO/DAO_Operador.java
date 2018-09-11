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

/**
 *
 * @author Jhoan Saavedra
 */
public class DAO_Operador implements DAO<Operador> {

    private RandomAccessFile archivo;
    private Arbol_Archivo_IdString arbol;

    public DAO_Operador() throws FileNotFoundException {
        archivo = new RandomAccessFile("Empleado", "rw");
        arbol = new Arbol_Archivo_IdString("Empleado");
    }

    @Override
    public boolean crear(Operador operador) throws FileNotFoundException, IOException {
        archivo.seek(archivo.length());
        if ((new DAO_Sede()).buscar(operador.getSede().getCodigo()) != null && arbol.añadir(operador.getCorreo(), (int) archivo.length())) {
            archivo.writeUTF(operador.getCorreo());
            archivo.writeUTF(operador.getNombre());
            archivo.writeUTF(operador.getContraseña());
            archivo.writeUTF(operador.getCargo());
            archivo.writeUTF("-                                        ".substring(0, 35));
            archivo.writeUTF("-                                       ".substring(0, 8));
            archivo.writeUTF("-                                        ".substring(0, 8));
            archivo.writeInt(operador.getSede().getCodigo());
            return true;
        }
        return false;

    }

    @Override
    public Operador buscar(Object correo) throws FileNotFoundException, IOException {
        int pos = (int) arbol.getPosArchivo((String) correo);
        if (pos != -1) {
            archivo.seek(pos);
            Operador operador = new Operador();
            operador.setCorreo(archivo.readUTF());
            operador.setNombre(archivo.readUTF());
            operador.setContraseña(archivo.readUTF());
            operador.setCargo(archivo.readUTF());
            archivo.skipBytes(37 + 10 + 10);
            operador.setSede((new DAO_Sede()).buscar(archivo.readInt()));

        }

        return null;

    }

    @Override
    public boolean actualizar(Operador operador) throws FileNotFoundException, IOException {
        int pos = (int) arbol.getPosArchivo(operador.getCorreo());
        if (pos != -1 && (new DAO_Sede()).buscar(operador.getSede().getCodigo()) != null) {
            archivo.writeUTF(operador.getCorreo());
            archivo.writeUTF(operador.getNombre());
            archivo.writeUTF(operador.getContraseña());
            archivo.writeUTF(operador.getCargo());
            archivo.writeUTF("-                                        ".substring(0, 35));
            archivo.writeUTF("-                                       ".substring(0, 8));
            archivo.writeUTF("-                                        ".substring(0, 8));
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
