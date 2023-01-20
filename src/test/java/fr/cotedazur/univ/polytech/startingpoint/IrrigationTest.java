package fr.cotedazur.univ.polytech.startingpoint;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IrrigationTest {

    @Test
    void isLink() {
        Irrigation irrigation = new Irrigation();
        assertTrue(Irrigation.isLink(Irrigation));

    }

    @Test
    void getPosition() {
    }
}