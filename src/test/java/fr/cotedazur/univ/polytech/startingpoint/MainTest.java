package fr.cotedazur.univ.polytech.startingpoint;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void getTwoThousandConfig() {
        Main main = new Main();
        assertEquals(main.getTwoThousandConfig(), false );
    }

    @Test
    void getDemo() {
        Main main = new Main();
        assertEquals(main.getDemo(), false );
    }

    @Test
    void getTwoThousand() {
        Main main = new Main();
        assertEquals(main.getTwoThousand(), false );
    }

    @Test
    void getDemoWarning() {
        Main main = new Main();
        assertEquals(main.getDemoWarning(), false );
    }

    @Test
    void getDemoFine() {
        Main main = new Main();
        assertEquals(main.getDemoFine(), false );
    }

    @Test
    void getDemoFiner() {
        Main main = new Main();
        assertEquals(main.getDemoFiner(), false );
    }

    @Test
    void getDemoFinest() {
        Main main = new Main();
        assertEquals(main.getDemoFinest(), false );
    }

    @Test
    void getTwoThousandWarning() {
        Main main = new Main();
        assertEquals(main.getTwoThousandWarning(), false );
    }

    @Test
    void getTwoThousandFine() {
        Main main = new Main();
        assertEquals(main.getTwoThousandFine(), false );
    }

    @Test
    void getTwoThousandFiner() {
        Main main = new Main();
        assertEquals(main.getTwoThousandFiner(), false );
    }

    @Test
    void getTwoThousandFinest() {
        Main main = new Main();
        assertEquals(main.getTwoThousandFinest(), false );
    }

    @Test
    void getCSV() {
        Main main = new Main();
        assertEquals(main.getCsv(), false );
    }
}