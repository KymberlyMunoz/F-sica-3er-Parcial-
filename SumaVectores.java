import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

public class SumaVectores extends JFrame {
    private JPanel panel;
    private JTextField vector1XField, vector1YField, vector2XField, vector2YField;
    private JButton sumButton;

    private int vector1X, vector1Y, vector2X, vector2Y;

    public SumaVectores() {
        super("Suma de Vectores");

        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                // Dibujar los ejes coordenados
                g2d.setColor(Color.BLACK);
                g2d.drawLine(100, 300, 500, 300); // eje x
                g2d.drawLine(300, 100, 300, 500); // eje y

                // Dibujar los vectores
                drawVector(g2d, 300, 300, vector1X, vector1Y, "A");
                drawVector(g2d, 300, 300, vector2X, vector2Y, "B");
                drawVector(g2d, 300, 300, vector1X + vector2X, vector1Y + vector2Y, "C");
            }

            private void drawVector(Graphics2D g2d, int startX, int startY, int dx, int dy, String label) {
                int endX = startX + dx;
                int endY = startY - dy; // Invertimos la coordenada y porque en pantalla aumenta hacia abajo

                g2d.setColor(Color.BLUE);
                g2d.drawLine(startX, startY, endX, endY); // Dibujar el vector
                g2d.fill(new Ellipse2D.Double(endX - 3, endY - 3, 6, 6)); // Dibujar el punto final del vector
                g2d.drawString(label, endX + 5, endY - 5); // Etiqueta del vector
            }
        };

        vector1XField = new JTextField(5);
        vector1YField = new JTextField(5);
        vector2XField = new JTextField(5);
        vector2YField = new JTextField(5);
        sumButton = new JButton("Sumar");

        sumButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    vector1X = Integer.parseInt(vector1XField.getText());
                    vector1Y = Integer.parseInt(vector1YField.getText());
                    vector2X = Integer.parseInt(vector2XField.getText());
                    vector2Y = Integer.parseInt(vector2YField.getText());
                    panel.repaint();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Ingrese valores numéricos válidos para los vectores.");
                }
            }
        });

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Vector 1 (x, y):"));
        inputPanel.add(vector1XField);
        inputPanel.add(new JLabel(","));
        inputPanel.add(vector1YField);
        inputPanel.add(new JLabel("Vector 2 (x, y):"));
        inputPanel.add(vector2XField);
        inputPanel.add(new JLabel(","));
        inputPanel.add(vector2YField);
        inputPanel.add(sumButton);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(inputPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SumaVectores sumaVectores = new SumaVectores();
            sumaVectores.setVisible(true);
        });
    }
}