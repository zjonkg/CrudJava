/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.util.List;

/**
 *
 * @author Usuario
 */

public interface CrudRepository<T> {
    void create(T entity);
    List<T> readAll();
    void update(T entity);
    void delete(int id);
}

