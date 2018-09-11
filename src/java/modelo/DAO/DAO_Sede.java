/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.DAO;

import Estructura.Arbol_Archivo_IdLong;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import modelo.Sede;

/**
 *
 * @author Jhoan Saavedra
 */
public class DAO_Sede implements DAO<Sede> {

    private RandomAccessFile archivo;
    private Arbol_Archivo_IdLong arbol;

    public DAO_Sede() throws FileNotFoundException {
        archivo = new RandomAccessFile("Sede", "rw");
        arbol = new Arbol_Archivo_IdLong("Sede");
    }

    @Override
    public boolean crear(Sede sede) throws FileNotFoundException, IOException {
        archivo.seek(archivo.length());

        if (arbol.añadir(sede.getCodigo(), (int) archivo.length())) {
            archivo.writeInt(sede.getCodigo());
            return true;
        }

        return false;
    }

    @Override
    public Sede buscar(Object sede) throws FileNotFoundException, IOException {
        int pos = (int) arbol.getPosArchivo((int) sede);
        if (pos != -1) {
            archivo.seek(pos);
            return new Sede(archivo.readInt());
        }
        return null;

    }

    @Override
    public boolean actualizar(Sede ob) throws FileNotFoundException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminar(Object id) throws FileNotFoundException, IOException {
        if (archivo.length() != 0 && arbol.eliminar((int) id)) {
            return true;
        }
        return false;

    }

}
