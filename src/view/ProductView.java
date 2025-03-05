package view;

import controller.ProductController;
import model.Product;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;
import utils.PlaceholderTextField;

public class ProductView extends JFrame {

    private ProductController controller;
    private JTable table;
    private DefaultTableModel model;
    private PlaceholderTextField txtNombre, txtPrecio;
    private JButton btnAgregar, btnActualizar, btnEliminar;

    public ProductView() {
        setTitle("Gestión de Productos");
        setSize(800, 600); // 📌 Pantalla más grande
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // 📌 Panel principal
        JPanel panelMain = new JPanel(new GridLayout(1, 2, 10, 10));
        panelMain.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelMain.setBackground(new Color(240, 240, 240));

        // 📌 Panel Izquierdo (Formulario y botones)
        // 📌 Panel Izquierdo (Formulario y botones)
        JPanel panelIzquierdo = new JPanel();
        panelIzquierdo.setLayout(new BoxLayout(panelIzquierdo, BoxLayout.Y_AXIS));
        panelIzquierdo.setBackground(new Color(230, 230, 250));

        JLabel lblTitulo = new JLabel("Nuevo Producto");
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(54, 69, 79));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);


        txtNombre = new PlaceholderTextField("Nombre del producto");
        txtPrecio = new PlaceholderTextField("Precio");


        btnAgregar = createStyledButton("➕ Agregar", new Color(46, 204, 113));
        btnActualizar = createStyledButton("✏️ Actualizar", new Color(241, 196, 15));
        btnEliminar = createStyledButton("❌ Eliminar", new Color(231, 76, 60));

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelBotones.setBackground(new Color(230, 230, 250));
        panelBotones.add(btnAgregar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);


        panelIzquierdo.add(lblTitulo);
        panelIzquierdo.add(Box.createVerticalStrut(10)); 
        panelIzquierdo.add(txtNombre);
        panelIzquierdo.add(Box.createVerticalStrut(5)); 
        panelIzquierdo.add(txtPrecio);
        panelIzquierdo.add(Box.createVerticalStrut(15)); 
        panelIzquierdo.add(panelBotones); 


        JPanel panelDerecho = new JPanel(new BorderLayout());
        panelDerecho.setBackground(Color.WHITE);

        model = new DefaultTableModel(new Object[]{"ID", "Nombre", "Precio"}, 0);
        table = new JTable(model);
        table.setFont(new Font("SansSerif", Font.PLAIN, 14));
        table.setRowHeight(25);
        table.setSelectionBackground(new Color(173, 216, 230));
        table.setSelectionForeground(Color.BLACK);

     
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(52, 152, 219));
        table.getTableHeader().setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(table);
        panelDerecho.add(scrollPane, BorderLayout.CENTER);

        // 📌 Agregar ambos paneles al layout principal
        panelMain.add(panelIzquierdo);
        panelMain.add(panelDerecho);
        add(panelMain);

  
        btnAgregar.addActionListener(e -> agregarProducto());
        btnActualizar.addActionListener(e -> actualizarProducto());
        btnEliminar.addActionListener(e -> eliminarProducto());

      
        table.getSelectionModel().addListSelectionListener(e -> seleccionarProducto());

        setVisible(true);
    }

    private void setupBotonesPanel(JPanel panelIzquierdo) {
     
        JPanel panelBotones = new JPanel(new GridLayout(1, 3, 10, 10));
        panelBotones.setBorder(new EmptyBorder(10, 0, 0, 0));
        panelBotones.setBackground(new Color(230, 230, 250));

        btnAgregar = createStyledButton("➕ Agregar", new Color(46, 204, 113));
        btnActualizar = createStyledButton("✏️ Actualizar", new Color(241, 196, 15));
        btnEliminar = createStyledButton("❌ Eliminar", new Color(231, 76, 60));

        panelBotones.add(btnAgregar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);

        panelIzquierdo.add(panelBotones);
    }

    public void setController(ProductController controller) {
        this.controller = controller;
    }

    public void mostrarProductos(List<Product> productos) {
        model.setRowCount(0);
        for (Product p : productos) {
            model.addRow(new Object[]{p.getId(), p.getNombre(), p.getPrecio()});
        }
    }

    private void seleccionarProducto() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            txtNombre.setText(model.getValueAt(selectedRow, 1).toString());
            txtPrecio.setText(model.getValueAt(selectedRow, 2).toString());
        }
    }

    private void agregarProducto() {
        String nombre = txtNombre.getText().trim();
        String precioText = txtPrecio.getText().trim();

        if (nombre.isEmpty() || precioText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "⚠️ Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double precio = Double.parseDouble(precioText);
            controller.agregarProducto(nombre, precio);
            limpiarCampos();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese un precio válido.", "⚠️ Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarProducto() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un producto de la tabla.", "⚠️ Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id = (int) model.getValueAt(selectedRow, 0);
        String nombre = txtNombre.getText().trim();
        String precioText = txtPrecio.getText().trim();

        if (nombre.isEmpty() || precioText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "⚠️ Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double precio = Double.parseDouble(precioText);
            controller.actualizarProducto(id, nombre, precio);
            limpiarCampos();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese un precio válido.", "⚠️ Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarProducto() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un producto de la tabla.", "⚠️ Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id = (int) model.getValueAt(selectedRow, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar este producto?", "❓ Confirmar", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            controller.eliminarProducto(id);
            limpiarCampos();
        }
    }

    private void limpiarCampos() {
        txtNombre.resetPlaceholder();
        txtPrecio.resetPlaceholder();
        table.clearSelection();
    }

    private JButton createStyledButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(color);
        return button;
    }
}
