
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShapeAnimation extends JPanel implements ActionListener {
    private int x = 0;
    private int y = 0;
    private int xSpeed;
    private int ySpeed;
    private Color color;
    private int size = 50;
    private Timer timer;

    // Sınırlı renk seçenekleri
    private final Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.ORANGE, Color.MAGENTA};

    public ShapeAnimation(Color color, int xSpeed, int ySpeed) {
        this.color = color;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        timer = new Timer(10, this); 
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(color);
        g.fillOval(x, y, size, size);
    }

    public void actionPerformed(ActionEvent e) {
        // Panel boyutu
        int panelWidth = this.getWidth();
        int panelHeight = this.getHeight();

        // pozisyon güncelleme
        x += xSpeed;
        y += ySpeed;

        // kenarlara değip değmediğini tes
        if (x <= 0 || x >= panelWidth - size) {
            xSpeed *= -1;
        }
        if (y <= 0 || y >= panelHeight - size) {
            ySpeed *= -1;
        }

        repaint();
    }

    public static void main(String[] args) {
        // renk seçimini çağırma komutu
        Color color = getColorChoice();

        // Kullanıcıdan hız bilgilerini alma kodu
        int xSpeed = Integer.parseInt(JOptionPane.showInputDialog("Enter initial x-axis speed:"));
        int ySpeed = Integer.parseInt(JOptionPane.showInputDialog("Enter initial y-axis speed:"));

        // Window ayarları
        JFrame frame = new JFrame("Shape Animation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ShapeAnimation shapeAnimation = new ShapeAnimation(color, xSpeed, ySpeed);
        frame.add(shapeAnimation);
        frame.setSize(800, 600);
        frame.setVisible(true);
    }

    // renk seçeneklerini kullanıcıya sunan metot
    private static Color getColorChoice() {
       
    	Object[] colorOptions = {"Red", "Blue", "Green", "Orange",};
        
    	String input = (String) JOptionPane.showInputDialog(null, "Choose a color:",
                "Color Selection", JOptionPane.QUESTION_MESSAGE, null,
                colorOptions, colorOptions[0]);

        switch (input) {
            case "Red":
                return Color.RED;
            case "Blue":
                return Color.BLUE;
            case "Green":
                return Color.GREEN;
            case "Orange":
                return Color.ORANGE;
            
            default:
                return Color.BLACK; 
        }
    }
}

