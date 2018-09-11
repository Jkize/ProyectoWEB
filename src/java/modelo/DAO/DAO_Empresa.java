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
import modelo.Empresa;

/**
 *
 * @author Jhoan Saavedra
 */
public class DAO_Empresa implements DAO<Empresa> {

    private RandomAccessFile archivo;
    private Arbol_Archivo_IdLong arbol;

    public DAO_Empresa() throws FileNotFoundException {
        archivo = new RandomAccessFile("Empresa", "rw");
        arbol = new Arbol_Archivo_IdLong("Empresa");
    }

    @Override
    public boolean crear(Empresa empresa) throws FileNotFoundException, IOException {
        archivo.seek(archivo.length());

        if ((new DAO_Sede()).buscar(empresa.getSede().getCodigo()) != null && arbol.añadir(empresa.getCodigo(), (int) archivo.length())) {
            archivo.writeInt(empresa.getCodigo());
            archivo.writeUTF(empresa.getNombre());
            archivo.writeInt(empresa.getSede().getCodigo());
            return true;
        }

        return false;

    }

    @Override
    public Empresa buscar(Object codigo) throws FileNotFoundException, IOException {
        int pos = (int) arbol.getPosArchivo((int) codigo);
        if (pos != -1) {
            archivo.seek(pos);
            return new Empresa(archivo.readInt(), archivo.readUTF(), (new DAO_Sede()).buscar(archivo.readInt()));
        }
        return null;
    }

    @Override
    public boolean actualizar(Empresa empresa) throws FileNotFoundException, IOException {

        int pos = (int) arbol.getPosArchivo((int) empresa.getCodigo());
        if ((new DAO_Sede()).buscar(empresa.getSede().getCodigo()) != null && pos != -1) {
            archivo.writeInt(empresa.getCodigo());
            archivo.writeUTF(empresa.getNombre());
            archivo.writeInt(empresa.getSede().getCodigo());
            return true;
        }
        return false;
    }

    @Override
    public boolean eliminar(Object codigo) throws FileNotFoundException, IOException {
        if (archivo.length() != 0 && arbol.eliminar((int) codigo)) {
            return true;
        }
        return false;
    }
}
