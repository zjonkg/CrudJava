/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import controller.ProductController;
import model.ProductRepository;
import view.ProductView;


/**
 *
 * @author Usuario
 */

public class Main {
    public static void main(String[] args) {
        ProductRepository repository = new ProductRepository();
        ProductView view = new ProductView();
        ProductController controller = new ProductController(repository, view);
        
        view.setController(controller);
        controller.actualizarVista();
    }
}
