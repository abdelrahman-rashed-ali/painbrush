package PaintBrush;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

enum ShapeType {
    Oval, Line, Pencil, Eraser, Rectangle;
}

abstract class Shape {
    int x1, y1, x2, y2;
    String Mode;
    Color PaintColor;

    Shape(int x1, int y1, int x2, int y2, String mode, Color paintColor) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.Mode = mode;
        this.PaintColor = paintColor;
    }

    abstract void draw(Graphics2D g);
}

class Line extends Shape {
    Line(int x1, int y1, int x2, int y2, String mode, Color paintColor) {
        super(x1, y1, x2, y2, mode, paintColor);
    }

    @Override
    void draw(Graphics2D g) {
        g.setColor(this.PaintColor);
        if (Mode.equals("Dotted")) {
            float[] dashPattern = {5f, 5f};
            g.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10, dashPattern, 0));
        } else {
            g.setStroke(new BasicStroke(2));
        }
        g.drawLine(x1, y1, x2, y2);
    }
}

class Rectangle extends Shape {
    Rectangle(int x1, int y1, int width, int height, String mode, Color paintColor) {
        super(x1, y1, width, height, mode, paintColor);
    }

    @Override
    void draw(Graphics2D g) {
        g.setColor(this.PaintColor);
        if (Mode.equals("Fill")) {
            g.fillRect(x1, y1, x2, y2);
        } else {
            g.drawRect(x1, y1, x2, y2);
        }
    }
}

class Oval extends Shape {
    Oval(int x1, int y1, int width, int height, String mode, Color paintColor) {
        super(x1, y1, width, height, mode, paintColor);
    }

    @Override
    void draw(Graphics2D g) {
        g.setColor(this.PaintColor);
        if (Mode.equals("Fill")) {
            g.fillOval(x1, y1, x2, y2);
        } else {
            g.drawOval(x1, y1, x2, y2);
        }
    }
}

class DrawingPanel extends JPanel {
    private ArrayList<Shape> shapes;

    public DrawingPanel(ArrayList<Shape> shapes) {
        this.shapes = shapes;
        setDoubleBuffered(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        for (Shape shape : shapes) {
            shape.draw(g2d);
        }
    }
}

public class PaintBrush extends JFrame {
    int x1, y1, x2, y2;
    ShapeType type;
    Color paintColor = Color.BLACK;
    String mode = "Draw";
    ArrayList<Shape> shapes = new ArrayList<>();
    DrawingPanel drawingPanel;

    public PaintBrush() {
        initComponents();
        drawingPanel = new DrawingPanel(shapes);
        add(drawingPanel);

        drawingPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                x1 = e.getX();
                y1 = e.getY();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                x2 = e.getX();
                y2 = e.getY();
                createShape();
                drawingPanel.repaint();
            }
        });

        drawingPanel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                x2 = e.getX();
                y2 = e.getY();
                drawingPanel.repaint();
            }
        });
    }

    private void createShape() {
        switch (type) {
            case Line:
                shapes.add(new Line(x1, y1, x2, y2, mode, paintColor));
                break;
            case Rectangle:
                shapes.add(new Rectangle(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x2 - x1), Math.abs(y2 - y1), mode, paintColor));
                break;
            case Oval:
                shapes.add(new Oval(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x2 - x1), Math.abs(y2 - y1), mode, paintColor));
                break;
            // Add other shape types as needed
        }
    }

    private void initComponents() {
        // Initialize UI components
        setTitle("Paint Brush");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        JPanel controlPanel = new JPanel();
        JButton clearButton = new JButton("Clear");
        JButton undoButton = new JButton("Undo");
        JButton lineButton = new JButton("Line");
        JButton rectButton = new JButton("Rectangle");
        JButton ovalButton = new JButton("Oval");
        JButton pencilButton = new JButton("Pencil");
        JButton eraserButton = new JButton("Eraser");

        clearButton.addActionListener(e -> {
            shapes.clear();
            drawingPanel.repaint();
        });

        undoButton.addActionListener(e -> {
            if (!shapes.isEmpty()) {
                shapes.remove(shapes.size() - 1);
                drawingPanel.repaint();
            }
        });

        lineButton.addActionListener(e -> type = ShapeType.Line);
        rectButton.addActionListener(e -> type = ShapeType.Rectangle);
        ovalButton.addActionListener(e -> type = ShapeType.Oval);
        pencilButton.addActionListener(e -> type = ShapeType.Pencil);
        eraserButton.addActionListener(e -> type = ShapeType.Eraser);

        controlPanel.add(clearButton);
        controlPanel.add(undoButton);
        controlPanel.add(lineButton);
        controlPanel.add(rectButton);
        controlPanel.add(ovalButton);
        controlPanel.add(pencilButton);
        controlPanel.add(eraserButton);

        add(controlPanel, BorderLayout.NORTH);
        add(drawingPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PaintBrush().setVisible(true);
        });
    }
}
