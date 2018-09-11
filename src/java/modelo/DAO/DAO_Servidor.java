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
import modelo.Servidor;

/**
 *
 * @author Jhoan Saavedra
 */
public class DAO_Servidor implements DAO<Servidor> {

    private RandomAccessFile archivo;
    private Arbol_Archivo_IdLong arbol;

    public DAO_Servidor() throws FileNotFoundException {
        archivo = new RandomAccessFile("Servidor", "rw");
        arbol = new Arbol_Archivo_IdLong("Servidor");
    }

    @Override
    public boolean crear(Servidor servidor) throws FileNotFoundException, IOException {
        archivo.seek(archivo.length());

        if ((new DAO_Empresa()).buscar(servidor.getEmpresa().getCodigo()) != null && arbol.añadir(servidor.getCodigo(), (int) archivo.length())) {
            archivo.writeInt(servidor.getCodigo());
            archivo.writeUTF(servidor.getNombre());
            archivo.writeInt(servidor.getEmpresa().getCodigo());
            return true;
        }

        return false;
    }

    @Override
    public Servidor buscar(Object codigo) throws FileNotFoundException, IOException {
        int pos = (int) arbol.getPosArchivo((int) codigo);
        if (pos != -1) {
            archivo.seek(pos);
            return new Servidor(archivo.readInt(), archivo.readUTF(), (new DAO_Empresa()).buscar(archivo.readInt()));

        }
        return null;
    }

    @Override
    public boolean actualizar(Servidor servidor) throws FileNotFoundException, IOException {

        int pos = (int) arbol.getPosArchivo((int) servidor.getCodigo());
        if ((new DAO_Empresa()).buscar(servidor.getEmpresa().getCodigo()) != null && pos != -1) {
            archivo.writeInt(servidor.getCodigo());
            archivo.writeUTF(servidor.getNombre());
            archivo.writeInt(servidor.getEmpresa().getCodigo());
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
