package plugins;

import spi.WeatherData;

public class StockholmTemp implements WeatherData {
    @Override
    public void currentTemperature() {
        System.out.println("25 DEGREES CELSIUS");
    }
}
