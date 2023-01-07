package fr.cotedazur.univ.polytech.startingpoint;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GardenerTest {

    @Test
    public void setPositionTest() {
        Gardener gardener = new Gardener();
        Position position = new Position(2,1);
        gardener.setPosition(position);
        assertEquals(position, gardener.getPosition());
    }
}
