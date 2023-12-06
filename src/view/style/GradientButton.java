package view.style;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JButton;
import javax.swing.JPanel;

public class GradientButton extends JButton {

    private static final long serialVersionUID = 1L;
    Color color2;Color color1;
    /**Color color1
     * Create the panel.
     */
    public GradientButton(Color color1, Color color2) {
    	
        super();        
        this.color1=color1;
    	this.color2=color2;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Ép kiểu đối tượng Graphics thành Graphics2D
        Graphics2D g2d = (Graphics2D) g;
        
        // Tạo gradient từ màu trên cùng đến màu dưới cùng
        
        GradientPaint gradient = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
        
        // Sơn gradient trên nền của panel
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }
}
