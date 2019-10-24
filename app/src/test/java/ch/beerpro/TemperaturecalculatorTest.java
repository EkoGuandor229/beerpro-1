package ch.beerpro;

import org.junit.Assert;
import org.junit.Test;

// container_types
// Dose
// Glasflasche

// initial_temperature
// Raumtemperatur (20°C)
// Aussentemperatur (30°C)
// Kellertemperatur (12°C)

// cooling_variant
// Kühlschrank (4°C)</item>
// Tiefkühler (-18°C)</item>

// beer_type
// Lager
// Irish Stout
// Ale


public class TemperaturecalculatorTest {
    float relativeTemperatureDeviation = 0.20f;
    Temperaturecalculator temperaturecalculator = new Temperaturecalculator();
    @Test
    public void TwelfeDegreeLagerCanInFridge(){
        float result =  temperaturecalculator.calculateOptimalTemperature(
                temperaturecalculator.getContainerCoefficient("Dose"),
                temperaturecalculator.getInitialBeerTemperature("Kellertemperatur (12°C)"),
                temperaturecalculator.getFridgeTemperature("Kühlschrank (4°C)"),
                temperaturecalculator.getOptimalBeerTemperature("Lager")
        );
        Assert.assertEquals(1980f, result, 1980f*relativeTemperatureDeviation);
    }

    @Test
    public void TwelveDegreeStoutCanInFridge(){
        float result =  temperaturecalculator.calculateOptimalTemperature(
                temperaturecalculator.getContainerCoefficient("Dose"),
                temperaturecalculator.getInitialBeerTemperature("Kellertemperatur (12°C)"),
                temperaturecalculator.getFridgeTemperature("Kühlschrank (4°C)"),
                temperaturecalculator.getOptimalBeerTemperature("Irish Stout")
        );
        Assert.assertEquals(-1337, result, 0f);
    }

    @Test
    public void TwelveDegreeAleCanInFridge(){
        float result =  temperaturecalculator.calculateOptimalTemperature(
                temperaturecalculator.getContainerCoefficient("Dose"),
                temperaturecalculator.getInitialBeerTemperature("Kellertemperatur (12°C)"),
                temperaturecalculator.getFridgeTemperature("Kühlschrank (4°C)"),
                temperaturecalculator.getOptimalBeerTemperature("Ale")
        );
        Assert.assertEquals(420f, result, 420f*relativeTemperatureDeviation);
    }
    @Test
    public void TwelfeDegreeLagerCanInFreezer(){
        float result =  temperaturecalculator.calculateOptimalTemperature(
                temperaturecalculator.getContainerCoefficient("Dose"),
                temperaturecalculator.getInitialBeerTemperature("Kellertemperatur (12°C)"),
                temperaturecalculator.getFridgeTemperature("Tiefkühler (-18°C)"),
                temperaturecalculator.getOptimalBeerTemperature("Lager")
        );
        Assert.assertEquals(300f, result, 300f*relativeTemperatureDeviation);
    }

    @Test
    public void TwelveDegreeStoutCanInFreezer(){
        float result =  temperaturecalculator.calculateOptimalTemperature(
                temperaturecalculator.getContainerCoefficient("Dose"),
                temperaturecalculator.getInitialBeerTemperature("Kellertemperatur (12°C)"),
                temperaturecalculator.getFridgeTemperature("Tiefkühler (-18°C)"),
                temperaturecalculator.getOptimalBeerTemperature("Irish Stout")
        );
        Assert.assertEquals(-1337, result, 0f);
    }

    @Test
    public void TwelveDegreeAleCanInFreezer(){
        float result =  temperaturecalculator.calculateOptimalTemperature(
                temperaturecalculator.getContainerCoefficient("Dose"),
                temperaturecalculator.getInitialBeerTemperature("Kellertemperatur (12°C)"),
                temperaturecalculator.getFridgeTemperature("Tiefkühler (-18°C)"),
                temperaturecalculator.getOptimalBeerTemperature("Ale")
        );
        Assert.assertEquals(120f, result, 120f*relativeTemperatureDeviation);
    }



    @Test
    public void TwentyDegreeLagerCanInFridge(){
        float result =  temperaturecalculator.calculateOptimalTemperature(
                temperaturecalculator.getContainerCoefficient("Dose"),
                temperaturecalculator.getInitialBeerTemperature("Raumtemperatur (20°C)"),
                temperaturecalculator.getFridgeTemperature("Kühlschrank (4°C)"),
                temperaturecalculator.getOptimalBeerTemperature("Lager")
        );
        Assert.assertEquals(2940f, result, 2940f*relativeTemperatureDeviation);
    }

    @Test
    public void TwentyDegreeStoutCanInFridge(){
        float result =  temperaturecalculator.calculateOptimalTemperature(
                temperaturecalculator.getContainerCoefficient("Dose"),
                temperaturecalculator.getInitialBeerTemperature("Raumtemperatur (20°C)"),
                temperaturecalculator.getFridgeTemperature("Kühlschrank (4°C)"),
                temperaturecalculator.getOptimalBeerTemperature("Irish Stout")
        );
        Assert.assertEquals(840f, result, 840f*relativeTemperatureDeviation);
    }

    @Test
    public void TwentyDegreeAleCanInFridge(){
        float result =  temperaturecalculator.calculateOptimalTemperature(
                temperaturecalculator.getContainerCoefficient("Dose"),
                temperaturecalculator.getInitialBeerTemperature("Raumtemperatur (20°C)"),
                temperaturecalculator.getFridgeTemperature("Kühlschrank (4°C)"),
                temperaturecalculator.getOptimalBeerTemperature("Ale")
        );
        Assert.assertEquals(1380f, result, 1380f*relativeTemperatureDeviation);
    }
    @Test
    public void TwentyDegreeLagerCanInFreezer(){
        float result =  temperaturecalculator.calculateOptimalTemperature(
                temperaturecalculator.getContainerCoefficient("Dose"),
                temperaturecalculator.getInitialBeerTemperature("Raumtemperatur (20°C)"),
                temperaturecalculator.getFridgeTemperature("Tiefkühler (-18°C)"),
                temperaturecalculator.getOptimalBeerTemperature("Lager")
        );
        Assert.assertEquals(660f, result, 660f*relativeTemperatureDeviation);
    }

    @Test
    public void TwentyDegreeStoutCanInFreezer(){
        float result =  temperaturecalculator.calculateOptimalTemperature(
                temperaturecalculator.getContainerCoefficient("Dose"),
                temperaturecalculator.getInitialBeerTemperature("Raumtemperatur (20°C)"),
                temperaturecalculator.getFridgeTemperature("Tiefkühler (-18°C)"),
                temperaturecalculator.getOptimalBeerTemperature("Irish Stout")
        );
        Assert.assertEquals(300f, result, 300f*relativeTemperatureDeviation);
    }

    @Test
    public void TwentyDegreeAleCanInFreezer(){
        float result =  temperaturecalculator.calculateOptimalTemperature(
                temperaturecalculator.getContainerCoefficient("Dose"),
                temperaturecalculator.getInitialBeerTemperature("Raumtemperatur (20°C)"),
                temperaturecalculator.getFridgeTemperature("Tiefkühler (-18°C)"),
                temperaturecalculator.getOptimalBeerTemperature("Ale")
        );
        Assert.assertEquals(420f, result, 420f*relativeTemperatureDeviation);
    }

}
