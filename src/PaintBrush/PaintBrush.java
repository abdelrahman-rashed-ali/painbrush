package PaintBrush;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.awt.Color;



enum ShapeType {
    Oval,
    Line,
    Pencil,
    Eraser,
    Rectangle; 
}



/**
 *
 * @author AKZM
 */
public class PaintBrush extends javax.swing.JFrame {
    int x1,y1,x2,y2;
    ShapeType Type;
    Color PaintColor = Color.BLACK;
    String Mode = "Draw";
    String MouseMode = "";
    ArrayList<Shape> Shapes = new ArrayList();
    ArrayList<Rectangle> objects = new ArrayList();


    public PaintBrush() {
        initComponents();
        
        jComboBox2.addActionListener((ActionEvent e) -> {
            if(jComboBox2.getSelectedItem() == "Normal"){
                Mode = "Draw";
                repaint();
            } else if (jComboBox2.getSelectedItem() == "Solid"){
                Mode = "Fill";
                repaint();
            } else {
                Mode = "Dotted";
                repaint();
            }
        });
        UndoButton.addActionListener((ActionEvent e) -> {
            if(!Shapes.isEmpty()){
                Shapes.remove(0);
                repaint();
            }
        });

        ClearButton.addActionListener((ActionEvent e) -> {
            if(!Shapes.isEmpty()){
                Shapes.clear();
                repaint();
            }
        });

        LineButton.addActionListener((ActionEvent e) -> {
            Type = ShapeType.Line;
        });
        
        RectButton.addActionListener((ActionEvent e) -> {
            Type = ShapeType.Rectangle;
        });
        
        OvalButton.addActionListener((ActionEvent e) -> {
            Type = ShapeType.Oval;
        });
        
        PencilButton.addActionListener((ActionEvent e) -> {
            Type = ShapeType.Pencil;
        });
        
        EraserButton.addActionListener((ActionEvent e) -> {
            Type = ShapeType.Eraser;
        });
        
        // Colors Buttons
        
        BlackButton.addActionListener((ActionEvent e) -> {
            PaintColor = Color.BLACK;
        });
        
        RedButton.addActionListener((ActionEvent e) -> {
            PaintColor = Color.RED;
        });
        
        WhiteButton.addActionListener((ActionEvent e) -> {
            PaintColor = Color.WHITE;
        });
        
        GreenButton.addActionListener((ActionEvent e) -> {
            PaintColor = Color.GREEN;
        });
        
        BlueButton.addActionListener((ActionEvent e) -> {
            PaintColor = Color.BLUE;
        });
        
        
        addMouseListener(new MouseListener() {
            @Override
            public void mousePressed(MouseEvent e) {
                MouseMode = "Pressed";
                x1 = e.getX();
                y1 = e.getY();
                if(Type.equals(ShapeType.Pencil)){
                    objects.add(new Rectangle(x1,y1,2,2,"Fill",PaintColor));
                }else if (Type.equals(ShapeType.Eraser)){
                    objects.add(new Rectangle(x1, y1, 2, 2, "Fill", getBackground()));
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                MouseMode = "Released";
                x2 = e.getX();
                y2 = e.getY();
                switch (Type) {
                    case Line:
                        if(Mode.equals("Fill"))
                            Shapes.add(0,new Line(x1,y1,x2,y2,"Draw",PaintColor));
                        else
                            Shapes.add(0,new Line(x1,y1,x2,y2,Mode,PaintColor));
                        break;
                    case Rectangle:
                        Shapes.add(0,new Rectangle(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x2 - x1), Math.abs(y2 - y1),Mode,PaintColor));
                        break;
                    case Oval:
                        Shapes.add(0,new Oval(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x2 - x1), Math.abs(y2 - y1),Mode,PaintColor));
                        break;
                    case Pencil:
                        Shapes.add(0, new Pencil(new ArrayList<>(objects))); 
                        objects.clear();
                        break;
                    case Eraser:
                        Shapes.add(0, new Eraser(new ArrayList<>(objects)));
                        objects.clear();
                        break;
                    default:
                        break;
                }
                repaint();
            }

            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });

        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if(MouseMode.equals("Pressed") || MouseMode.equals("Dragged")){
                    MouseMode = "Dragged";
                    x2 = e.getX();
                    y2 = e.getY();
                    if (Type.equals(ShapeType.Pencil)) {
                        objects.add(new Rectangle(x2, y2, 2, 2, "Fill", PaintColor));
                    } else if (Type.equals(ShapeType.Eraser)){
                        objects.add(new Rectangle(x2, y2, 2, 2, "Fill", getBackground()));
                    }
                    repaint();
                }  
            }

            @Override
            public void mouseMoved(MouseEvent e) {}
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        ClearButton = new javax.swing.JButton();
        UndoButton = new javax.swing.JButton();
        LineButton = new javax.swing.JButton();
        RectButton = new javax.swing.JButton();
        OvalButton = new javax.swing.JButton();
        PencilButton = new javax.swing.JButton();
        BlackButton = new javax.swing.JButton();
        RedButton = new javax.swing.JButton();
        GreenButton = new javax.swing.JButton();
        BlueButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        EraserButton = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox<>();
        WhiteButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        ClearButton.setText("Clear");

        UndoButton.setText("Undo");

        LineButton.setText("Line");
        LineButton.setToolTipText("");
        LineButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LineButtonActionPerformed(evt);
            }
        });

        RectButton.setText("Rectangle");

        OvalButton.setText("Oval");

        PencilButton.setText("Pencil");

        BlackButton.setBackground(new java.awt.Color(0, 0, 0));
        BlackButton.setForeground(new java.awt.Color(240, 240, 240));
        BlackButton.setText("Black");

        RedButton.setBackground(new java.awt.Color(255, 0, 51));
        RedButton.setText("Red");

        GreenButton.setBackground(new java.awt.Color(0, 255, 0));
        GreenButton.setText("Green");

        BlueButton.setBackground(new java.awt.Color(0, 0, 255));
        BlueButton.setText("Blue");

        jLabel1.setText("Functions : ");

        jLabel2.setText("Paint Mode : ");

        jLabel3.setText("Colors : ");

        EraserButton.setText("Eraser");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Normal", "Solid", "Dotted" }));

        WhiteButton.setText("White");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(ClearButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(UndoButton)
                .addGap(9, 9, 9)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LineButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RectButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(OvalButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PencilButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(EraserButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(WhiteButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BlackButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RedButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(GreenButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BlueButton)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ClearButton)
                    .addComponent(UndoButton)
                    .addComponent(LineButton)
                    .addComponent(RectButton)
                    .addComponent(OvalButton)
                    .addComponent(PencilButton)
                    .addComponent(BlackButton)
                    .addComponent(RedButton)
                    .addComponent(GreenButton)
                    .addComponent(BlueButton)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(EraserButton)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(WhiteButton)
                    .addComponent(jLabel3))
                .addContainerGap(392, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    private void LineButtonActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
    }                                          

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PaintBrush.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PaintBrush.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PaintBrush.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PaintBrush.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new PaintBrush().setVisible(true);
        });
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
        for (int i = Shapes.size()-1 ; i >= 0 ; i--){
            Shape a = Shapes.get(i);
            if (a instanceof Rectangle){
                Rectangle obj = (Rectangle)a;
                g2d.setColor(obj.PaintColor);
                switch (obj.Mode) {
                    case "Draw":
                        g2d.setStroke(new BasicStroke(2));
                        g2d.drawRect(obj.x1, obj.y1, obj.x2, obj.y2);
                        break;
                    case "Fill":
                        g2d.fillRect(obj.x1, obj.y1, obj.x2, obj.y2);
                        break;
                    default:
                        float[] dashPattern = {5f, 5f}; // 5 pixels on, 5 pixels off
                        g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10, dashPattern, 0));
                        g2d.drawRect(obj.x1, obj.y1, obj.x2, obj.y2);
                        break;
                }
            } else if (a instanceof Line){
                Line obj = (Line)a;
                g2d.setColor(obj.PaintColor);
                if (obj.Mode.equals("Dotted" )){
                    float[] dashPattern = {5f, 5f}; // 5 pixels on, 5 pixels off
                    g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10, dashPattern, 0));
                    g2d.drawLine(obj.x1, obj.y1, obj.x2, obj.y2);
                }else
                    g2d.setStroke(new BasicStroke(2));
                    g2d.drawLine(obj.x1, obj.y1, obj.x2, obj.y2);
                      
            } else if (a instanceof Oval){
                Oval obj = (Oval)a;
                g2d.setColor(obj.PaintColor);
                switch (obj.Mode) {
                    case "Draw":
                        g2d.setStroke(new BasicStroke(2));
                        g2d.drawOval(obj.x1, obj.y1, obj.x2, obj.y2);
                        break;
                    case "Fill":
                        g2d.fillOval(obj.x1, obj.y1, obj.x2, obj.y2);
                        break;
                    default:   
                        float[] dashPattern = {5f, 5f}; // 5 pixels on, 5 pixels off
                        g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10, dashPattern, 0));
                        g2d.drawOval(obj.x1, obj.y1, obj.x2, obj.y2);
                        break;
                }
            } else if (a instanceof Pencil){
                Pencil obj = (Pencil)a;
                g2d.setColor(obj.Objects.get(0).PaintColor);
                g2d.setStroke(new BasicStroke(2));
                for (int j = 0 ; j < obj.Objects.size() - 1 ; j++){
                    Rectangle b = obj.Objects.get(j);
                    Rectangle bb = obj.Objects.get(j+1);
                    g2d.drawLine(b.x1, b.y1, bb.x1, bb.y1);
                }
            } else if (a instanceof Eraser){
                Eraser obj = (Eraser)a; 
                g2d.setStroke(new BasicStroke(5));
                for (int j = 0 ; j < obj.Objects.size() - 1 ; j++){
                    Rectangle b = obj.Objects.get(j);
                    Rectangle bb = obj.Objects.get(j+1);
                    g2d.setColor(b.PaintColor);
                    g2d.drawLine(b.x1, b.y1, bb.x1, bb.y1);
                }
            }
        }
        
        if(MouseMode.equals("Dragged")){
            g2d.setColor(PaintColor);
            if(Type.equals(ShapeType.Line)){
                if (Mode.equals("Dotted" )){
                    float[] dashPattern = {5f, 5f}; // 5 pixels on, 5 pixels off
                    g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10, dashPattern, 0));
                    g2d.drawLine(x1, y1, x2, y2);
                }else
                    g2d.setStroke(new BasicStroke(2));
                    g2d.drawLine(x1, y1, x2, y2);
            }
            switch (Mode) {
                case "Draw":
                    g2d.setStroke(new BasicStroke(2));
                    if (Type.equals(ShapeType.Rectangle)) {
                        g2d.drawRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x2 - x1), Math.abs(y2 - y1));
                    } else if (Type.equals(ShapeType.Oval)) {
                        g2d.drawOval(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x2 - x1), Math.abs(y2 - y1));
                    }   break;
                case "Fill":
                    if (Type.equals(ShapeType.Rectangle)) {
                        g2d.fillRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x2 - x1), Math.abs(y2 - y1));
                    } else if (Type.equals(ShapeType.Oval)) {
                        g2d.fillOval(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x2 - x1), Math.abs(y2 - y1));
                    }   break;
                default:
                    float[] dashPattern = {5f, 5f}; // 5 pixels on, 5 pixels off
                    g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10, dashPattern, 0));
                    if (Type.equals(ShapeType.Rectangle)) {
                        g2d.drawRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x2 - x1), Math.abs(y2 - y1));
                    } else if (Type.equals(ShapeType.Oval)) {
                        g2d.drawOval(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x2 - x1), Math.abs(y2 - y1));
                    }   break;
            }
            
            if (Type.equals(ShapeType.Pencil)) {
                g2d.setStroke(new BasicStroke(2));
                for(int i = 0 ; i < objects.size()-1 ; i ++){
                    Rectangle b = objects.get(i);
                    Rectangle bb = objects.get(i+1);
                    g2d.drawLine(b.x1, b.y1, bb.x1, bb.y1);
                }
            } else if (Type.equals(ShapeType.Eraser)) {
                g2d.setColor(getBackground());
                g2d.setStroke(new BasicStroke(5));
                for(int i = 0 ; i < objects.size()-1 ; i ++){
                    Rectangle b = objects.get(i);
                    Rectangle bb = objects.get(i+1);
                    g2d.drawLine(b.x1, b.y1, bb.x1, bb.y1);
                }
            }
            
        }
    }
    
    
    // Variables declaration - do not modify                     
    private javax.swing.JButton BlackButton;
    private javax.swing.JButton BlueButton;
    private javax.swing.JButton ClearButton;
    private javax.swing.JButton EraserButton;
    private javax.swing.JButton GreenButton;
    private javax.swing.JButton LineButton;
    private javax.swing.JButton OvalButton;
    private javax.swing.JButton PencilButton;
    private javax.swing.JButton RectButton;
    private javax.swing.JButton RedButton;
    private javax.swing.JButton UndoButton;
    private javax.swing.JButton WhiteButton;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration                   
}

