/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Beans/Bean.java to edit this template
 */
package bean;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.*;
import java.io.Serializable;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.Timer;

/**
 *
 * @author Juan Carlos Vilarrubia
 */
public class BotonEstrella extends JButton implements Serializable {

    private int velocidad = 1;
    private Timer timer;
    private Image byke;
    private Image moto;
    private boolean visible = true;

    private final PropertyChangeSupport propertySupport;

    public BotonEstrella() {

        byke = new ImageIcon(getClass().getResource("bicycle.png")).getImage();
        moto = new ImageIcon(getClass().getResource("motorcycle.png")).getImage();

        // para un botón
        this.setIcon(new ImageIcon(byke));
        this.setVerticalTextPosition(SwingConstants.BOTTOM);
        this.setHorizontalTextPosition(SwingConstants.CENTER);

        this.propertySupport = new PropertyChangeSupport(this);
        timer = new javax.swing.Timer(5000 / velocidad, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                blink();
            }
        });

        timer.start();
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int value) {
        int oldValue = velocidad;
        velocidad = value;
        if (velocidad > 0) {
            timer.setDelay(5000 / velocidad);
        }
        propertySupport.firePropertyChange("velocidad", oldValue, velocidad);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        if (propertySupport != null) {
            this.propertySupport.addPropertyChangeListener(listener);
        }
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        if (propertySupport != null) {
            this.propertySupport.removePropertyChangeListener(listener);
        }
    }

    public void blink() {

        if (visible) {
            this.setIcon(new ImageIcon(byke));
        } else {
            this.setIcon(new ImageIcon(moto));
        }

        visible = !visible;

    }

}
