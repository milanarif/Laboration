module weatherstack.plugins {
    requires spi;
    provides spi.WeatherData with plugins.GothenburgTemp, plugins.StockholmTemp;
    opens plugins to core;
}