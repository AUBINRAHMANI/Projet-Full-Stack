package fr.cotedazur.univ.polytech.startingpoint;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WeatherTypeTest {

    @Test
    void testOfWeatherTypeEnumValues() {
        WeatherType weatherTypeNoMeteo = WeatherType.NOMETEO;
        assertEquals(WeatherType.valueOf("NOMETEO"), weatherTypeNoMeteo);

        WeatherType weatherTypeSun = WeatherType.SUN;
        assertEquals(WeatherType.valueOf("SUN"), weatherTypeSun);

        WeatherType weatherTypeRain = WeatherType.RAIN;
        assertEquals(WeatherType.valueOf("RAIN"), weatherTypeRain);

        WeatherType weatherTypeWind = WeatherType.WIND;
        assertEquals(WeatherType.valueOf("WIND"), weatherTypeWind);

        WeatherType weatherTypeThunder = WeatherType.THUNDER;
        assertEquals(WeatherType.valueOf("THUNDER"), weatherTypeThunder);

        WeatherType weatherTypeQuestionMark = WeatherType.QUESTIONMARK;
        assertEquals(WeatherType.valueOf("QUESTIONMARK"), weatherTypeQuestionMark);

        WeatherType weatherTypeCloud = WeatherType.CLOUD;
        assertEquals(WeatherType.valueOf("CLOUD"), weatherTypeCloud);

    }
}