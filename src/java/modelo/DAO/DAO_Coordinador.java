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
import modelo.Coordinador;

/**
 *
 * @author Jhoan Saavedra
 */
public class DAO_Coordinador implements DAO<Coordinador> {

    private RandomAccessFile archivo;
    private Arbol_Archivo_IdString arbol;

    public DAO_Coordinador() throws FileNotFoundException {
        archivo = new RandomAccessFile("Empleado", "rw");
        arbol = new Arbol_Archivo_IdString("Empleado");
    }

    @Override
    public boolean crear(Coordinador coordinador) throws FileNotFoundException, IOException {
        archivo.seek(archivo.length());
        if (arbol.añadir(coordinador.getCorreo(), (int) archivo.length())) {
            archivo.writeUTF(coordinador.getCorreo());
            archivo.writeUTF(coordinador.getNombre());
            archivo.writeUTF(coordinador.getContraseña());
            archivo.writeUTF(coordinador.getCargo());
            archivo.writeUTF("-                                        ".substring(0, 35));
            archivo.writeUTF("-                                       ".substring(0, 8));
            archivo.writeUTF("-                                        ".substring(0, 8));
            archivo.writeInt(0);
            return true;
        }
        return false;

    }

    @Override
    public Coordinador buscar(Object correo) throws FileNotFoundException, IOException {
        int pos = (int) arbol.getPosArchivo((String) correo);
        if (pos != -1) {
            archivo.seek(pos);
            return new Coordinador(archivo.readUTF(), archivo.readUTF(), archivo.readUTF(), archivo.readUTF());

        }
        return null;

    }

    @Override
    public boolean actualizar(Coordinador coordinador) throws FileNotFoundException, IOException {
        int pos = (int) arbol.getPosArchivo(coordinador.getCorreo());
        if (pos != -1) {
            archivo.seek(pos);
            archivo.writeUTF(coordinador.getCorreo());
            archivo.writeUTF(coordinador.getNombre());
            archivo.writeUTF(coordinador.getContraseña());
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
