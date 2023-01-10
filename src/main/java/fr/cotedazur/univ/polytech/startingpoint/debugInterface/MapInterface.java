package fr.cotedazur.univ.polytech.startingpoint.debugInterface;
import fr.cotedazur.univ.polytech.startingpoint.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

public class MapInterface extends JFrame {

    final static int HEXAGONE_SIZE = 40;

    Position _center;
    volatile boolean _next;
    ArrayList<Position> _positionsToAdd;
    ArrayList<Integer> correspondingNbBambous;
    ArrayList<Plot> plotsDrawen;
    ArrayList<Color> _colorsToAdd;
    Position gardenerPosition;
    Position pandaPosition;
    Toolkit _toolkit;
    GPanel _panel;

    public MapInterface(){
        setSize(960, 540);
        _next = false;
        _toolkit = getToolkit();
        _positionsToAdd = new ArrayList<>();
        correspondingNbBambous = new ArrayList<>();
        plotsDrawen         = new ArrayList<>();
        _colorsToAdd        = new ArrayList<>();
        gardenerPosition    = new Position(0,0);
        pandaPosition       = new Position(0, 0);
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

    public void drawMap(Map map, Position gardenePosition, Position pandaPosition){
        this.gardenerPosition   = gardenePosition;
        this.pandaPosition      = pandaPosition;
        ArrayList<Plot> plots = map.getMap();
        plots.removeAll(plotsDrawen);
        for(Plot plot : plots){
            plotsDrawen.add(plot);
            drawHexagon(plot);
        }
    }
    private void drawHexagon(Plot plot){
        Position position   = plot.getPosition();
        PlotType plotType   = plot.getType();
        int nbBambous       = plot.getNumberOfBambou();

        Color color;
        switch (plotType.ordinal()){
            case 0:
                color = new Color(0, 52, 192);
                break;
            case 1:
                color = new Color(63, 131, 1);
                break;
            case 2:
                color = new Color(199, 197, 0);
                break;
            case 3:
                color = new Color(196, 2, 2);
                break;
            default:
                color = Color.BLACK;
        }

        _positionsToAdd.add(position);
        _colorsToAdd.add(color);
        correspondingNbBambous.add(nbBambous);
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

                graphics.setColor(Color.WHITE);

                Position stringPosition = getPlotPositionInGrid(position);
                int nbOfBambous = correspondingNbBambous.get(_positionsToAdd.indexOf(position));
                if(nbOfBambous != 0){
                    graphics.drawString(String.valueOf(nbOfBambous), stringPosition.getX(), stringPosition.getY());
                }

                Position gardenerPositionInGrid = getPlotPositionInGrid(gardenerPosition);
                graphics.drawString("G", gardenerPositionInGrid.getX()-12, gardenerPositionInGrid.getY());

                Position pandaPositionInGrid = getPlotPositionInGrid(pandaPosition);
                graphics.drawString("P", pandaPositionInGrid.getX()-4, pandaPositionInGrid.getY()+15);

            }
        }

        private Polygon getHexagon(Position position) {
            int x = (int)((HEXAGONE_SIZE/2) * (3./2 * position.getQ())) + _center.getX();
            int y = (int)((HEXAGONE_SIZE/2) * (sqrt(3)/2 * position.getQ() + sqrt(3) * position.getR())) + _center.getY();
            int xPoints[] = {x+HEXAGONE_SIZE/4, x+HEXAGONE_SIZE/2, x+HEXAGONE_SIZE/4,   x-HEXAGONE_SIZE/4,  x-HEXAGONE_SIZE/2, x-HEXAGONE_SIZE/4};
            int yPoints[] = {(int)(y+(HEXAGONE_SIZE*(0.42))),                 y, (int) (y-(HEXAGONE_SIZE*(0.42))), (int) (y-(HEXAGONE_SIZE*(0.42))),                  y, (int) (y+(HEXAGONE_SIZE*(0.42)))};
            return new Polygon(xPoints, yPoints, 6);
        }

        private Position getPlotPositionInGrid(Position position){
            int x = (int)((HEXAGONE_SIZE/2) * (3./2 * position.getQ())) + _center.getX();
            int y = (int)((HEXAGONE_SIZE/2) * (sqrt(3)/2 * position.getQ() + sqrt(3) * position.getR())) + _center.getY();
            return new Position(x, y);
        }
    }
}




