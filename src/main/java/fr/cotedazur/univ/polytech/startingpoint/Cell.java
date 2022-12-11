package fr.cotedazur.univ.polytech.startingpoint;

public class Cell {
    private static int s = 0;    // length of one side
    private static int t = 0;    // short side of 30o triangle outside of each hex
    private static int r = 0;    // radius of inscribed circle (centre to middle of each side). r= h/2
    private static int h = 0;    // height. Distance between centres of two adjacent hexes. Distance between two opposite sides in a hex.

    public static void setSide(int side) {
        s = side;
        t = (int) (s / 2);            //t = s sin(30) = (int) CalculateH(s);
        r = (int) (s * 0.8660254037844);    //r = s cos(30) = (int) CalculateR(s);
        h = 2 * r;
    }

    public static void setHeight(int height) {
        h = height;            // h = basic dimension: height (distance between two adj centresr aka size)
        r = h / 2;            // r = radius of inscribed circle
        s = (int) (h / 1.73205);    // s = (h/2)/cos(30)= (h/2) / (sqrt(3)/2) = h / sqrt(3)
        t = (int) (r / 1.73205);    // t = (h/2) tan30 = (h/2) 1/sqrt(3) = h / (2 sqrt(3)) = r / sqrt(3)
    }
}