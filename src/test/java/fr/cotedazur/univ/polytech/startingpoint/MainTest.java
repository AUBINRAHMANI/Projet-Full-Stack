package fr.cotedazur.univ.polytech.startingpoint;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class MainTest {

    @Test
    void getTwoThousandConfig() {
        Main main = new Main();
        assertFalse(main.getTwoThousandConfig());
    }

    @Test
    void getDemo() {
        Main main = new Main();
        assertFalse(main.getDemo());
    }

    @Test
    void getTwoThousand() {
        Main main = new Main();
        assertFalse(main.getTwoThousand());
    }

    @Test
    void getDemoWarning() {
        Main main = new Main();
        assertFalse(main.getDemoWarning());
    }

    @Test
    void getDemoFine() {
        Main main = new Main();
        assertFalse(main.getDemoFine());
    }

    @Test
    void getDemoFiner() {
        Main main = new Main();
        assertFalse(main.getDemoFiner());
    }

    @Test
    void getDemoFinest() {
        Main main = new Main();
        assertFalse(main.getDemoFinest());
    }

    @Test
    void getTwoThousandWarning() {
        Main main = new Main();
        assertFalse(main.getTwoThousandWarning());
    }

    @Test
    void getTwoThousandFine() {
        Main main = new Main();
        assertFalse(main.getTwoThousandFine());
    }

    @Test
    void getTwoThousandFiner() {
        Main main = new Main();
        assertFalse(main.getTwoThousandFiner());
    }

    @Test
    void getTwoThousandFinest() {
        Main main = new Main();
        assertFalse(main.getTwoThousandFinest());
    }

    @Test
    void getCSV() {
        Main main = new Main();
        assertFalse(main.getCsv());
    }
}