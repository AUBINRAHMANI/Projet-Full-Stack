package fr.cotedazur.univ.polytech.startingpoint;

import java.util.Random;

import static fr.cotedazur.univ.polytech.startingpoint.WeatherType.*;

public class Weather {
    private WeatherType weatherType;


public Weather(){
        this.weatherType = NOMETEO;
    }

    public WeatherType getWeather(){
        return this.weatherType;
    }

    public void setWeatherType(WeatherType weatherType) {
        this.weatherType = weatherType;
    }


}
