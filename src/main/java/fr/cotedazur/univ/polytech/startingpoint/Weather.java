package fr.cotedazur.univ.polytech.startingpoint;

import java.util.Random;

import static fr.cotedazur.univ.polytech.startingpoint.WeatherType.*;

public class Weather {
    private WeatherType weatherType;



    public WeatherType getWeather(){
        return this.weatherType;
    }

    public void setWeatherType(WeatherType weatherType) {
        this.weatherType = weatherType;
    }

    Random rand = new Random();
    int max = 6;
    int choseNumber = rand.nextInt(max+1);
    public WeatherType drawWeather(){
        switch (choseNumber){
            case 1 :
                return this.weatherType = SUN;
            case 2 :
                return this.weatherType = RAIN;
            case 3 :
                return this.weatherType = CLOUD;
            case 4 :
                return this.weatherType = WIND;
            case 5 :
                return this.weatherType = THUNDER;
            case 6 :
                return this.weatherType = QUESTIONMARK;
        }
        return null;
    }

}
