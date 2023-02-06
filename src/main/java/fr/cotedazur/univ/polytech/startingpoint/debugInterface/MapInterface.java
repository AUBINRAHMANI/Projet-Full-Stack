package fr.cotedazur.univ.polytech.startingpoint.debugInterface;
import fr.cotedazur.univ.polytech.startingpoint.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ConcurrentModificationException;
import static java.lang.Math.sqrt;

public class MapInterface extends JFrame {

    final static int HEXAGONE_SIZE = 40;

    List<Plot> map;
    Position center;
    volatile boolean next;
    List<Position> positionsToAdd;
    List<Integer> correspondingNbBambous;
    List<Plot> plotsDrawen;
    List<Color> colorsToAdd;
    Position gardenerPosition;
    Position pandaPosition;
    Toolkit toolkit;
    GPanel panel;

    public MapInterface(){
        setSize(960, 540);
        next = false;
        toolkit = getToolkit();
        positionsToAdd = new ArrayList<>();
        correspondingNbBambous = new ArrayList<>();
        plotsDrawen         = new ArrayList<>();
        colorsToAdd = new ArrayList<>();
        gardenerPosition    = new Position(0,0);
        pandaPosition       = new Position(0, 0);
        map = new ArrayList<>();
        updateSize();

        JButton nextButton=new JButton("Next");
        nextButton.setBounds((getWidth()/2)-(140/2),getHeight()-100,140,30);

        nextButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                next = true;
            }
        });
        add(nextButton);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                nextButton.setBounds((getWidth()/2)-(140/2),getHeight()-100,140,30);
            }
        });



        panel = new GPanel();
        getContentPane().add(panel);
        panel.setLayout(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public boolean next(){
        if(next)
        {
            next = false;
            return true;
        }
        else {
            return false;
        }
    }

    public void updateSize(){
        center = new Position(getWidth()/2, (int)(getHeight()/2.7));
    }

    public void drawMap(Map map, Position gardenePosition, Position pandaPosition){
        this.map = map.getMapPlots();
        this.gardenerPosition   = gardenePosition;
        this.pandaPosition      = pandaPosition;
        List<Plot> plots = map.getMapPlots();
        List<Plot> plotsToRemove = new ArrayList<>();
        for(Plot plot1 : plots){
            for(Plot plot2 : plotsDrawen){
                if(plot1.getPosition().equals(plot2.getPosition()) && plot1.isIrrigated()!=plot2.isIrrigated()){
                    plotsToRemove.add(plot2);
                    colorsToAdd.remove(positionsToAdd.indexOf(plot2.getPosition()));
                    positionsToAdd.remove(plot2.getPosition());
                }
            }
        }
        plotsDrawen.removeAll(plotsToRemove);
        plots.removeAll(plotsDrawen);
        for(Plot plot : plots){
            drawHexagon(plot);
            plotsDrawen.add(new Plot(plot));
        }
        try {
            paintComponents(getGraphics());
            repaint();
        }catch (ConcurrentModificationException e){System.out.println("Arretez de modifier en meme temps");}
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

        positionsToAdd.add(position);
        if(plot.isIrrigated()==false){
            color = color.darker();
            color = color.darker();
        }
        colorsToAdd.add(color);
        correspondingNbBambous.add(nbBambous);
    }

    private class GPanel extends JPanel{

        public void paintComponent(Graphics graphics){
            super.paintComponent(graphics);
            updateSize();
            // new Color(0, 115,255, 163)
            List<Position> positions = new ArrayList<>(positionsToAdd);
            for(Position position : positions){
                Polygon hexagon = getHexagon(position);
                try {
                    Thread.sleep(1);
                }catch (Exception exception){assert false;}
                graphics.setColor(Color.BLACK);
                graphics.drawPolygon(hexagon);

                graphics.setColor(colorsToAdd.get(positionsToAdd.indexOf(position)));
                graphics.fillPolygon(hexagon);

                graphics.setColor(Color.WHITE);

                for (Plot plot : plotsDrawen){
                    if(plot.getNumberOfBambou() >0){
                        Position stringPosition = getPlotPositionInGrid(plot.getPosition());
                        graphics.drawString(String.valueOf(plot.getNumberOfBambou()), stringPosition.getX(), stringPosition.getY());
                    }
                }
                Position gardenerPositionInGrid = getPlotPositionInGrid(gardenerPosition);
                graphics.drawString("G", gardenerPositionInGrid.getX()-12, gardenerPositionInGrid.getY());

                Position pandaPositionInGrid = getPlotPositionInGrid(pandaPosition);
                graphics.drawString("P", pandaPositionInGrid.getX()-4, pandaPositionInGrid.getY()+15);
            }
        }

        private Polygon getHexagon(Position position) {
            int x = (int)((HEXAGONE_SIZE/2) * (3./2 * position.getQ())) + center.getX();
            int y = (int)((HEXAGONE_SIZE/2) * (sqrt(3)/2 * position.getQ() + sqrt(3) * position.getR())) + center.getY();
            int xPoints[] = {x+HEXAGONE_SIZE/4, x+HEXAGONE_SIZE/2, x+HEXAGONE_SIZE/4,   x-HEXAGONE_SIZE/4,  x-HEXAGONE_SIZE/2, x-HEXAGONE_SIZE/4};
            int yPoints[] = {(int)(y+(HEXAGONE_SIZE*(0.42))),                 y, (int) (y-(HEXAGONE_SIZE*(0.42))), (int) (y-(HEXAGONE_SIZE*(0.42))),                  y, (int) (y+(HEXAGONE_SIZE*(0.42)))};
            return new Polygon(xPoints, yPoints, 6);
        }

        private Position getPlotPositionInGrid(Position position){
            int x = (int)((HEXAGONE_SIZE/2) * (3./2 * position.getQ())) + center.getX();
            int y = (int)((HEXAGONE_SIZE/2) * (sqrt(3)/2 * position.getQ() + sqrt(3) * position.getR())) + center.getY();
            return new Position(x, y);
        }
    }
}




