/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import model.Product;
import model.ProductRepository;
import view.ProductView;


/**
 *
 * @author Usuario
 */


import java.util.List;

public class ProductController {
    private ProductRepository repository;
    private ProductView view;

    public ProductController(ProductRepository repository, ProductView view) {
        this.repository = repository;
        this.view = view;
    }

    public void agregarProducto(String nombre, double precio) {
        Product producto = new Product(0, nombre, precio);
        repository.create(producto);
        actualizarVista();
    }

    public void actualizarProducto(int id, String nombre, double precio) {
        Product producto = new Product(id, nombre, precio);
        repository.update(producto);
        actualizarVista();
    }

    public void eliminarProducto(int id) {
        repository.delete(id);
        actualizarVista();
    }

    public List<Product> obtenerProductos() {
        return repository.readAll();
    }

    public void actualizarVista() {
        view.mostrarProductos(obtenerProductos());
    }
}
