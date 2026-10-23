package practica01_arraylist;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private int idPedido;
    private List<Producto> productos;
    private List<Integer> cantidades;

    public Pedido(int idPedido) {
        this.idPedido = idPedido;
        this.productos = new ArrayList<>();
        this.cantidades = new ArrayList<>();
    }

    public int getIdPedido() {
        return idPedido;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public List<Integer> getCantidades() {
        return cantidades;
    }

    public void agregarProducto(Producto p, int cantidad) {
        this.productos.add(p);
        this.cantidades.add(cantidad);
    }

    public double calcularTotal() {
        double total = 0.0;
        for (int i = 0; i < productos.size(); i++) {
            total += productos.get(i).getPrecio() * cantidades.get(i);
        }
        return total;
    }
}