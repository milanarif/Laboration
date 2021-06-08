module core {
    requires spi;
    uses spi.WeatherData;
    requires persistence;
    requires com.google.gson;
}