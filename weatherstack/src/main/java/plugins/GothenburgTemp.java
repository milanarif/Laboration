package plugins;

import spi.WeatherData;

public class GothenburgTemp implements WeatherData {
    @Override
    public void currentTemperature() {
        System.out.println("30 DEGREES CELSIUS");
    }
}
