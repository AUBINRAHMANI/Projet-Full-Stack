package fr.cotedazur.univ.polytech.startingpoint.debugInterface;
import fr.cotedazur.univ.polytech.startingpoint.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class MapInterface extends JFrame {

    final static int HEXAGONE_SIZE = 40;

    Position _center;
    volatile boolean _next;
    ArrayList<Position> _positionsToAdd;
    ArrayList<Color> _colorsToAdd;
    Toolkit _toolkit;
    GPanel _panel;

    public MapInterface(){
        setSize(960, 540);
        _next = false;
        _toolkit = getToolkit();
        _positionsToAdd = new ArrayList<>();
        _colorsToAdd = new ArrayList<>();
        updateSize();

        JButton nextButton=new JButton("Next");
        nextButton.setBounds((getWidth()/2)-(140/2),getHeight()-100,140,30);

        nextButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                _next = true;
            }
        });
        add(nextButton);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                nextButton.setBounds((getWidth()/2)-(140/2),getHeight()-100,140,30);
            }
        });



        _panel = new GPanel();
        getContentPane().add(_panel);
        _panel.setLayout(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        _colorsToAdd.add(new Color(0, 85, 189, 163));
        setVisible(true);
    }

    public boolean next(){
        if(_next)
        {
            _next = false;
            return true;
        }
        else {
            return false;
        }
    }

    public void updateSize(){
        _center     = new Position(getWidth()/2, (int)(getHeight()/2.7));
    }

    public void drawHexagon(Position position){
        drawHexagon(position, new Color(4, 145, 11));
    }
    public void drawHexagon(Position position, Color color){
        _positionsToAdd.add(position);
        _colorsToAdd.add(color);
        try {
            paintComponents(getGraphics());
        }catch (ConcurrentModificationException e){System.out.println("Arretez de modifier en meme temps");}

    }



    public static void main(String[] args){
        MapInterface mapInterface = new MapInterface();
    }


    private class GPanel extends JPanel{

        public void paintComponent(Graphics graphics){
            super.paintComponent(graphics);
            updateSize();
            // new Color(0, 115,255, 163)


            for(Position position : _positionsToAdd.toArray(new Position[0])){
                Polygon hexagon = getHexagon(position);
                try {
                    Thread.sleep(1);
                }catch (Exception exception){assert false;}
                graphics.setColor(Color.BLACK);
                graphics.drawPolygon(hexagon);
                graphics.setColor(_colorsToAdd.get(_positionsToAdd.indexOf(position)));
                graphics.fillPolygon(hexagon);
            }
        }

        private Polygon getHexagon(Position position) {
            int y = (int) (position.getY()*(HEXAGONE_SIZE/(1.33)) + _center.getY());
            int x;
            if(position.getY()%2 >0){
                x = (int) ((position.getX()+0.5)*HEXAGONE_SIZE + _center.getX());
            }
            else {
                x = position.getX()*HEXAGONE_SIZE + _center.getX() ;
            }
            int xPoints[] = {x, x+HEXAGONE_SIZE/2, x+HEXAGONE_SIZE/2, x, x-HEXAGONE_SIZE/2, x-HEXAGONE_SIZE/2};
            int yPoints[] = {y+HEXAGONE_SIZE/2, y+HEXAGONE_SIZE/4, y-HEXAGONE_SIZE/4, y-HEXAGONE_SIZE/2, y-HEXAGONE_SIZE/4, y+HEXAGONE_SIZE/4};
            return new Polygon(xPoints, yPoints, 6);
        }
    }
}




