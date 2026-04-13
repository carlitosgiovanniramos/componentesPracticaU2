/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ComponentesPropiosAPE2;

import javax.swing.JComboBox;

/**
 *
 * @author Lenovo LOQ
 */
public class jcbxEnteros extends JComboBox{

    public jcbxEnteros() {
        llenarComboBox();
    }
    
    private void llenarComboBox() {
        this.removeAllItems();
        
        for (int i = 2; i < 20; i++) {
            if (i % 2 == 0) {
                this.addItem(i);
            }
        }
    }
    
    public Integer getValor() {
        return (Integer) this.getSelectedItem();
    }
    
    public void setValor(Integer valor) {
        this.setSelectedItem(selectedItemReminder);
    }
}
