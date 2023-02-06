package fr.cotedazur.univ.polytech.startingpoint;

import java.util.Random;

import static fr.cotedazur.univ.polytech.startingpoint.WeatherType.*;

public class Weather {
    private WeatherType weatherType;

    private BotProfil botProfil;

public Weather(){
        this.weatherType = NOMETEO;
    }

    public WeatherType getWeatherType(){
        return this.weatherType;
    }

    public void setWeatherType(WeatherType weatherType) {
        this.weatherType = weatherType;
    }


}
