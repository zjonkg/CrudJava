/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

/**
 *
 * @author Usuario
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class PlaceholderTextField extends JTextField {
    private String placeholder;
    private boolean showingPlaceholder;

    public PlaceholderTextField(String placeholder) {
        this.placeholder = placeholder;
        this.showingPlaceholder = true;
        setForeground(Color.GRAY);
        setText(placeholder);

        // 📌 Evento cuando obtiene o pierde foco
        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (showingPlaceholder) {
                    setText("");
                    setForeground(Color.BLACK);
                    showingPlaceholder = false;
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (getText().isEmpty()) {
                    setForeground(Color.GRAY);
                    setText(placeholder);
                    showingPlaceholder = true;
                }
            }
        });
    }

    public String getRealText() {
        return showingPlaceholder ? "" : getText();
    }

    public void resetPlaceholder() {
        setForeground(Color.GRAY);
        setText(placeholder);
        showingPlaceholder = true;
    }
}
