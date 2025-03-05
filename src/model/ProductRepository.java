/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */


public class ProductRepository implements CrudRepository<Product> {
    private List<Product> productos = new ArrayList<>();
    private int nextId = 1;

    @Override
    public void create(Product producto) {
        producto.setId(nextId++);
        productos.add(producto);
    }

    @Override
    public List<Product> readAll() {
        return productos;
    }

    @Override
    public void update(Product producto) {
        for (Product p : productos) {
            if (p.getId() == producto.getId()) {
                p.setNombre(producto.getNombre());
                p.setPrecio(producto.getPrecio());
                break;
            }
        }
    }

    @Override
    public void delete(int id) {
        productos.removeIf(p -> p.getId() == id);
    }
}
