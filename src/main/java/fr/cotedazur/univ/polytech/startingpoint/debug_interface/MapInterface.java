package fr.cotedazur.univ.polytech.startingpoint.debug_interface;

import fr.cotedazur.univ.polytech.startingpoint.Map;
import fr.cotedazur.univ.polytech.startingpoint.Plot;
import fr.cotedazur.univ.polytech.startingpoint.PlotType;
import fr.cotedazur.univ.polytech.startingpoint.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.sqrt;

public class MapInterface extends JFrame {

    static final int HEXAGON_SIZE = 40;
    private static final List<Position> positionsToAdd = new ArrayList<>();
    private static final List<Integer> correspondingNbBamboos = new ArrayList<>();
    private static final List<Plot> plotsDrawn = new ArrayList<>();
    private static final List<Color> colorsToAdd = new ArrayList<>();
    private static Position center;
    private final GPanel panel;
    volatile boolean next;

    public MapInterface() {
        setSize(960, 540);
        next = false;
        updateSize();

        JButton nextButton = new JButton("Next");
        nextButton.setBounds((getWidth() / 2) - (140 / 2), getHeight() - 100, 140, 30);

        nextButton.addActionListener(listener -> next = true);

        add(nextButton);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                nextButton.setBounds((getWidth() / 2) - (140 / 2), getHeight() - 100, 140, 30);
            }
        });


        panel = new GPanel();
        getContentPane().add(panel);
        panel.setLayout(null);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void updateSize() {
        center = new Position(getWidth() / 2, (int) (getHeight() / 2.7));
    }

    public void drawMap(Map map, Position newGardenerPosition, Position newPandaPosition) {
        List<Plot> plots = map.getMapPlots();
        List<Plot> plotsToRemove = new ArrayList<>();
        for (Plot plot1 : plots) {
            for (Plot plot2 : plotsDrawn) {
                if (plot1.getPosition().equals(plot2.getPosition()) && plot1.isIrrigated() != plot2.isIrrigated()) {
                    plotsToRemove.add(plot2);
                    colorsToAdd.remove(positionsToAdd.indexOf(plot2.getPosition()));
                    positionsToAdd.remove(plot2.getPosition());
                }
            }
        }
        plotsDrawn.removeAll(plotsToRemove);
        plots.removeAll(plotsDrawn);
        for (Plot plot : plots) {
            drawHexagon(plot);
            plotsDrawn.add(new Plot(plot));
        }
        panel.paintComponent(getGraphics(), newGardenerPosition, newPandaPosition);
        repaint();
    }

    private void drawHexagon(Plot plot) {
        Position position = plot.getPosition();
        PlotType plotType = plot.getType();
        int nbBamboos = plot.getNumberOfBamboo();

        Color color = switch (plotType.ordinal()) {
            case 0 -> new Color(0, 52, 192);
            case 1 -> new Color(63, 131, 1);
            case 2 -> new Color(199, 197, 0);
            case 3 -> new Color(196, 2, 2);
            default -> Color.BLACK;
        };

        positionsToAdd.add(position);
        if (!plot.isIrrigated()) {
            color = color.darker();
            color = color.darker();
        }
        colorsToAdd.add(color);
        correspondingNbBamboos.add(nbBamboos);
    }

    private class GPanel extends JPanel {


        public void paintComponent(Graphics graphics, Position gardenerPosition, Position pandaPosition) {
            super.paintComponent(graphics);
            updateSize();
            // new Color(0, 115,255, 163)
            List<Position> positions = new ArrayList<>(positionsToAdd);
            for (Position position : positions) {
                Polygon hexagon = getHexagon(position);
                graphics.setColor(Color.BLACK);
                graphics.drawPolygon(hexagon);

                graphics.setColor(colorsToAdd.get(positionsToAdd.indexOf(position)));
                graphics.fillPolygon(hexagon);

                graphics.setColor(Color.WHITE);

                for (Plot plot : plotsDrawn) {
                    if (plot.getNumberOfBamboo() > 0) {
                        Position stringPosition = getPlotPositionInGrid(plot.getPosition());
                        graphics.drawString(String.valueOf(plot.getNumberOfBamboo()), stringPosition.getX(), stringPosition.getY());
                    }
                }
                Position gardenerPositionInGrid = getPlotPositionInGrid(gardenerPosition);
                graphics.drawString("G", gardenerPositionInGrid.getX() - 12, gardenerPositionInGrid.getY());

                Position pandaPositionInGrid = getPlotPositionInGrid(pandaPosition);
                graphics.drawString("P", pandaPositionInGrid.getX() - 4, pandaPositionInGrid.getY() + 15);
            }
        }

        private Polygon getHexagon(Position position) {
            int x = (int) ((HEXAGON_SIZE / 2.) * (3. / 2 * position.getQ())) + center.getX();
            int y = (int) ((HEXAGON_SIZE / 2.) * (sqrt(3) / 2 * position.getQ() + sqrt(3) * position.getR())) + center.getY();
            int[] xPoints = {x + HEXAGON_SIZE / 4, x + HEXAGON_SIZE / 2, x + HEXAGON_SIZE / 4, x - HEXAGON_SIZE / 4, x - HEXAGON_SIZE / 2, x - HEXAGON_SIZE / 4};
            int[] yPoints = {(int) (y + (HEXAGON_SIZE * (0.42))), y, (int) (y - (HEXAGON_SIZE * (0.42))), (int) (y - (HEXAGON_SIZE * (0.42))), y, (int) (y + (HEXAGON_SIZE * (0.42)))};
            return new Polygon(xPoints, yPoints, 6);
        }

        private Position getPlotPositionInGrid(Position position) {
            int x = (int) ((HEXAGON_SIZE / 2.) * (3. / 2 * position.getQ())) + center.getX();
            int y = (int) ((HEXAGON_SIZE / 2.) * (sqrt(3) / 2 * position.getQ() + sqrt(3) * position.getR())) + center.getY();
            return new Position(x, y);
        }
    }
}




