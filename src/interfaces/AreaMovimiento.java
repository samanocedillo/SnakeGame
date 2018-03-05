/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import Objects.Variables;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;
import javax.accessibility.AccessibleContext;
import javax.imageio.ImageIO;
import javax.naming.Context;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author marti
 */
public final class AreaMovimiento extends JPanel implements Runnable {

    int vidas = 3;
    int cambio = 10;
    int coorX, tempX;
    int coorY, tempY;
    int velocidad = 1000;
    boolean inicio = true;
    String direccion = "abajo";
    Point coordenada = new Point();
    Point num = new Point();
    Point temp = new Point();

    Image avion;

    public Image getAvion() {
        return avion;
    }

    public void setAvion(Image avion) {
        this.avion = avion;
    }
    

    Thread proceso = new Thread(this);
    JLabel lives;

    public AreaMovimiento(JLabel lives) {
        this.iniciarProceso();
        this.lives = lives;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void iniciarProceso() {
        this.proceso.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (this.inicio) {
            Metodo();

            avion = new ImageIcon(getClass().getResource("/img/abajo.png")).getImage()
                    .getScaledInstance(10, -1, Image.SCALE_DEFAULT);
        }

        //g.fillOval(coorX, coorY, 10, 10);
        //g.drawImage(this.figura, coorX, coorY, this);
        //g.drawImage(this.coordenada.x, this.coordenada.y, 10, 10);
        g.fillOval(this.coordenada.x, this.coordenada.y, 10, 10);
        g.drawString("9", coorX, coorY);

        if (coordenada.x <= -14 || coordenada.y <= -14 || coordenada.x >= 492 || coordenada.y >= 448) {
            vidas = vidas - 1;
            Metodo();
        }
    }

    @Override
    public void run() {
        do {
            switch (this.direccion) {
                case "arriba":
                    this.coordenada.y -= this.cambio;
                    break;
                case "abajo":
                    this.coordenada.y += this.cambio;
                    break;
                case "derecha":
                    this.coordenada.x += this.cambio;
                    break;
                case "izqierda":
                    this.coordenada.x -= this.cambio;
                    break;
            }
            try {
                this.proceso.sleep(this.velocidad);
            } catch (InterruptedException e) {
                break;
            }
            repaint();
        } while (vidas > 0);
    }

    public void Metodo() {
        coorX = Aleatorio();
        coorY = Aleatorio();
        this.coordenada.x = this.getSize().width / 2;
        this.coordenada.y = this.getSize().height / 2;
        this.inicio = false;
    }

    public int Aleatorio() {
        return (-14 + (int) (Math.random() * 491));
    }
}
