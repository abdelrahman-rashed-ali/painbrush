/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PaintBrush;

import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author AKZM
 */
class Shape {
    public int x1;
    public int y1;
    public int x2;
    public int y2;
    
    Shape(){
        
    }
    
    Shape(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2= y2;     
    }
}

class Line extends Shape{
    Color PaintColor;
    String Mode;
    public Line(int x1, int y1, int x2, int y2,String Mode, Color PaintColor) {
        super(x1, y1, x2, y2);
        this.PaintColor = PaintColor;
        this.Mode = Mode ;
    }
    
}

class Rectangle extends Shape{
    String Mode;
    Color PaintColor;
    public Rectangle(int x1, int y1, int x2, int y2, String Mode, Color PaintColor) {
        super(x1,y1,x2,y2);
        this.Mode = Mode;
        this.PaintColor = PaintColor;
    }
    
}

class Oval extends Shape{
    String Mode;
    Color PaintColor;
    public Oval(int x1, int y1, int x2, int y2, String Mode, Color PaintColor) {
        super(x1,y1,x2,y2);
        this.Mode = Mode;
        this.PaintColor = PaintColor;
    }  
}

class Pencil extends Shape{
    ArrayList<Rectangle> Objects = new ArrayList();
    
    public Pencil(ArrayList<Rectangle> e) {
        this.Objects = e;

    }
    
}

class Eraser extends Shape{
    ArrayList<Rectangle> Objects = new ArrayList();
  
    public Eraser(ArrayList<Rectangle> e) {
        Objects = e;
    }
    
}
