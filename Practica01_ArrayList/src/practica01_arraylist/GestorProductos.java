package practica01_arraylist;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class GestorProductos {
    private ArrayList<Producto> listaProductos;
    private File archivoProductos;

    public GestorProductos() {
        this.listaProductos = new ArrayList<>();
        this.archivoProductos = new File("src/files/productos.txt");
        cargarProductos();
    }

    public void registrarProducto(Producto p) {
        listaProductos.add(p);
        guardarProductos();
    }

    public Producto buscarPorId(String id) {
        for (var p : listaProductos) {
            if (p.getId().equalsIgnoreCase(id)) {
                return p;
            }
        }
        return null;
    }

    public ArrayList<Producto> getListaProductos() {
        return listaProductos;
    }

    public void cargarProductos() {
        listaProductos.clear();
        try {
            if (!archivoProductos.exists()) {
                archivoProductos.getParentFile().mkdirs();
                archivoProductos.createNewFile();
                return;
            }
            try (var scanner = new Scanner(archivoProductos)) {
                while (scanner.hasNextLine()) {
                    var linea = scanner.nextLine().trim();
                    if (linea.isEmpty()) continue;
                    var datos = linea.split(",");
                    if (datos.length == 4) {
                        var id = datos[0];
                        var nombre = datos[1];
                        var precio = Double.parseDouble(datos[2]);
                        var stock = Integer.parseInt(datos[3]);
                        listaProductos.add(new Producto(id, nombre, precio, stock));
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error al cargar productos: " + e.getMessage());
        }
    }

    public void guardarProductos() {
        try (var pw = new PrintWriter(new FileWriter(archivoProductos, false))) {
            for (var p : listaProductos) {
                pw.printf("%s,%s,%.2f,%d%n", p.getId(), p.getNombre(), p.getPrecio(), p.getStock());
            }
        } catch (IOException e) {
            System.err.println("Error al guardar productos: " + e.getMessage());
        }
    }
}