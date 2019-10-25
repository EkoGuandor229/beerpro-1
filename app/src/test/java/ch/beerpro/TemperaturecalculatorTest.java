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
    float relativeTemperatureDeviation = 0.2f;
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

//////////////////////////////////////////////////////////////////////////////////////////////////////

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

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void ThirtyDegreeLagerCanInFridge(){
        float result =  temperaturecalculator.calculateOptimalTemperature(
                temperaturecalculator.getContainerCoefficient("Dose"),
                temperaturecalculator.getInitialBeerTemperature("Aussentemperatur (30°C)"),
                temperaturecalculator.getFridgeTemperature("Kühlschrank (4°C)"),
                temperaturecalculator.getOptimalBeerTemperature("Lager")
        );
        Assert.assertEquals(3600f, result, 3600f*relativeTemperatureDeviation);
    }

    @Test
    public void ThirtyDegreeStoutCanInFridge(){
        float result =  temperaturecalculator.calculateOptimalTemperature(
                temperaturecalculator.getContainerCoefficient("Dose"),
                temperaturecalculator.getInitialBeerTemperature("Aussentemperatur (30°C)"),
                temperaturecalculator.getFridgeTemperature("Kühlschrank (4°C)"),
                temperaturecalculator.getOptimalBeerTemperature("Irish Stout")
        );
        Assert.assertEquals(1500f, result, 1500f*relativeTemperatureDeviation);
    }

    @Test
    public void ThirtyDegreeAleCanInFridge(){
        float result =  temperaturecalculator.calculateOptimalTemperature(
                temperaturecalculator.getContainerCoefficient("Dose"),
                temperaturecalculator.getInitialBeerTemperature("Aussentemperatur (30°C)"),
                temperaturecalculator.getFridgeTemperature("Kühlschrank (4°C)"),
                temperaturecalculator.getOptimalBeerTemperature("Ale")
        );
        Assert.assertEquals(2040f, result, 2040f*relativeTemperatureDeviation);
    }
    @Test
    public void ThirtyDegreeLagerCanInFreezer(){
        float result =  temperaturecalculator.calculateOptimalTemperature(
                temperaturecalculator.getContainerCoefficient("Dose"),
                temperaturecalculator.getInitialBeerTemperature("Aussentemperatur (30°C)"),
                temperaturecalculator.getFridgeTemperature("Tiefkühler (-18°C)"),
                temperaturecalculator.getOptimalBeerTemperature("Lager")
        );
        Assert.assertEquals(960f, result, 960f*relativeTemperatureDeviation);
    }

    @Test
    public void ThirtyDegreeStoutCanInFreezer(){
        float result =  temperaturecalculator.calculateOptimalTemperature(
                temperaturecalculator.getContainerCoefficient("Dose"),
                temperaturecalculator.getInitialBeerTemperature("Aussentemperatur (30°C)"),
                temperaturecalculator.getFridgeTemperature("Tiefkühler (-18°C)"),
                temperaturecalculator.getOptimalBeerTemperature("Irish Stout")
        );
        Assert.assertEquals(600f, result, 600f*relativeTemperatureDeviation);
    }

    @Test
    public void ThirtyDegreeAleCanInFreezer(){
        float result =  temperaturecalculator.calculateOptimalTemperature(
                temperaturecalculator.getContainerCoefficient("Dose"),
                temperaturecalculator.getInitialBeerTemperature("Aussentemperatur (30°C)"),
                temperaturecalculator.getFridgeTemperature("Tiefkühler (-18°C)"),
                temperaturecalculator.getOptimalBeerTemperature("Ale")
        );
        Assert.assertEquals(780f, result, 7800f*relativeTemperatureDeviation);
    }

////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void TwelfeDegreeLagerBottleInFridge(){
        float result =  temperaturecalculator.calculateOptimalTemperature(
                temperaturecalculator.getContainerCoefficient("Glasflasche"),
                temperaturecalculator.getInitialBeerTemperature("Kellertemperatur (12°C)"),
                temperaturecalculator.getFridgeTemperature("Kühlschrank (4°C)"),
                temperaturecalculator.getOptimalBeerTemperature("Lager")
        );
        Assert.assertEquals(11220f, result, 11220f*relativeTemperatureDeviation);
    }

    @Test
    public void TwelveDegreeStoutBottleInFridge(){
        float result =  temperaturecalculator.calculateOptimalTemperature(
                temperaturecalculator.getContainerCoefficient("Glasflasche"),
                temperaturecalculator.getInitialBeerTemperature("Kellertemperatur (12°C)"),
                temperaturecalculator.getFridgeTemperature("Kühlschrank (4°C)"),
                temperaturecalculator.getOptimalBeerTemperature("Irish Stout")
        );
        Assert.assertEquals(-1337, result, 0f);
    }

    @Test
    public void TwelveDegreeAleBottleInFridge(){
        float result =  temperaturecalculator.calculateOptimalTemperature(
                temperaturecalculator.getContainerCoefficient("Glasflasche"),
                temperaturecalculator.getInitialBeerTemperature("Kellertemperatur (12°C)"),
                temperaturecalculator.getFridgeTemperature("Kühlschrank (4°C)"),
                temperaturecalculator.getOptimalBeerTemperature("Ale")
        );
        Assert.assertEquals(2340f, result, 2340f*relativeTemperatureDeviation);
    }
    @Test
    public void TwelfeDegreeLagerBottleInFreezer(){
        float result =  temperaturecalculator.calculateOptimalTemperature(
                temperaturecalculator.getContainerCoefficient("Glasflasche"),
                temperaturecalculator.getInitialBeerTemperature("Kellertemperatur (12°C)"),
                temperaturecalculator.getFridgeTemperature("Tiefkühler (-18°C)"),
                temperaturecalculator.getOptimalBeerTemperature("Lager")
        );
        Assert.assertEquals(1800f, result, 1800f*relativeTemperatureDeviation);
    }

    @Test
    public void TwelveDegreeStoutBottleInFreezer(){
        float result =  temperaturecalculator.calculateOptimalTemperature(
                temperaturecalculator.getContainerCoefficient("Glasflasche"),
                temperaturecalculator.getInitialBeerTemperature("Kellertemperatur (12°C)"),
                temperaturecalculator.getFridgeTemperature("Tiefkühler (-18°C)"),
                temperaturecalculator.getOptimalBeerTemperature("Irish Stout")
        );
        Assert.assertEquals(-1337, result, 0f);
    }

    @Test
    public void TwelveDegreeAleBottleInFreezer(){
        float result =  temperaturecalculator.calculateOptimalTemperature(
                temperaturecalculator.getContainerCoefficient("Glasflasche"),
                temperaturecalculator.getInitialBeerTemperature("Kellertemperatur (12°C)"),
                temperaturecalculator.getFridgeTemperature("Tiefkühler (-18°C)"),
                temperaturecalculator.getOptimalBeerTemperature("Ale")
        );
        Assert.assertEquals(540f, result, 540f*relativeTemperatureDeviation);
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void TwentyDegreeLagerBottleInFridge(){
        float result =  temperaturecalculator.calculateOptimalTemperature(
                temperaturecalculator.getContainerCoefficient("Glasflasche"),
                temperaturecalculator.getInitialBeerTemperature("Raumtemperatur (20°C)"),
                temperaturecalculator.getFridgeTemperature("Kühlschrank (4°C)"),
                temperaturecalculator.getOptimalBeerTemperature("Lager")
        );
        Assert.assertEquals(16860f, result, 16860f*relativeTemperatureDeviation);
    }

    @Test
    public void TwentyDegreeStoutBottleInFridge(){
        float result =  temperaturecalculator.calculateOptimalTemperature(
                temperaturecalculator.getContainerCoefficient("Glasflasche"),
                temperaturecalculator.getInitialBeerTemperature("Raumtemperatur (20°C)"),
                temperaturecalculator.getFridgeTemperature("Kühlschrank (4°C)"),
                temperaturecalculator.getOptimalBeerTemperature("Irish Stout")
        );
        Assert.assertEquals(4680f, result, 4680f*relativeTemperatureDeviation);
    }

    @Test
    public void TwentyDegreeAleBottleInFridge(){
        float result =  temperaturecalculator.calculateOptimalTemperature(
                temperaturecalculator.getContainerCoefficient("Glasflasche"),
                temperaturecalculator.getInitialBeerTemperature("Raumtemperatur (20°C)"),
                temperaturecalculator.getFridgeTemperature("Kühlschrank (4°C)"),
                temperaturecalculator.getOptimalBeerTemperature("Ale")
        );
        Assert.assertEquals(7980f, result, 7980f*relativeTemperatureDeviation);
    }
    @Test
    public void TwentyDegreeLagerBottleInFreezer(){
        float result =  temperaturecalculator.calculateOptimalTemperature(
                temperaturecalculator.getContainerCoefficient("Glasflasche"),
                temperaturecalculator.getInitialBeerTemperature("Raumtemperatur (20°C)"),
                temperaturecalculator.getFridgeTemperature("Tiefkühler (-18°C)"),
                temperaturecalculator.getOptimalBeerTemperature("Lager")
        );
        Assert.assertEquals(3720f, result, 3720f*relativeTemperatureDeviation);
    }

    @Test
    public void TwentyDegreeStoutBottleInFreezer(){
        float result =  temperaturecalculator.calculateOptimalTemperature(
                temperaturecalculator.getContainerCoefficient("Glasflasche"),
                temperaturecalculator.getInitialBeerTemperature("Raumtemperatur (20°C)"),
                temperaturecalculator.getFridgeTemperature("Tiefkühler (-18°C)"),
                temperaturecalculator.getOptimalBeerTemperature("Irish Stout")
        );
        Assert.assertEquals(1680f, result, 1680f*relativeTemperatureDeviation);
    }

    @Test
    public void TwentyDegreeAleBottleInFreezer(){
        float result =  temperaturecalculator.calculateOptimalTemperature(
                temperaturecalculator.getContainerCoefficient("Glasflasche"),
                temperaturecalculator.getInitialBeerTemperature("Raumtemperatur (20°C)"),
                temperaturecalculator.getFridgeTemperature("Tiefkühler (-18°C)"),
                temperaturecalculator.getOptimalBeerTemperature("Ale")
        );
        Assert.assertEquals(2460f, result, 2460f*relativeTemperatureDeviation);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void ThirtyDegreeLagerBottleInFridge(){
        float result =  temperaturecalculator.calculateOptimalTemperature(
                temperaturecalculator.getContainerCoefficient("Glasflasche"),
                temperaturecalculator.getInitialBeerTemperature("Aussentemperatur (30°C)"),
                temperaturecalculator.getFridgeTemperature("Kühlschrank (4°C)"),
                temperaturecalculator.getOptimalBeerTemperature("Lager")
        );
        Assert.assertEquals(20820f, result, 20820f*relativeTemperatureDeviation);
    }

    @Test
    public void ThirtyDegreeStoutBottleInFridge(){
        float result =  temperaturecalculator.calculateOptimalTemperature(
                temperaturecalculator.getContainerCoefficient("Glasflasche"),
                temperaturecalculator.getInitialBeerTemperature("Aussentemperatur (30°C)"),
                temperaturecalculator.getFridgeTemperature("Kühlschrank (4°C)"),
                temperaturecalculator.getOptimalBeerTemperature("Irish Stout")
        );
        Assert.assertEquals(8580f, result, 8580f*relativeTemperatureDeviation);
    }

    @Test
    public void ThirtyDegreeAleBottleInFridge(){
        float result =  temperaturecalculator.calculateOptimalTemperature(
                temperaturecalculator.getContainerCoefficient("Glasflasche"),
                temperaturecalculator.getInitialBeerTemperature("Aussentemperatur (30°C)"),
                temperaturecalculator.getFridgeTemperature("Kühlschrank (4°C)"),
                temperaturecalculator.getOptimalBeerTemperature("Ale")
        );
        Assert.assertEquals(11880f, result, 11880f*relativeTemperatureDeviation);
    }
    @Test
    public void ThirtyDegreeLagerBottleInFreezer(){
        float result =  temperaturecalculator.calculateOptimalTemperature(
                temperaturecalculator.getContainerCoefficient("Glasflasche"),
                temperaturecalculator.getInitialBeerTemperature("Aussentemperatur (30°C)"),
                temperaturecalculator.getFridgeTemperature("Tiefkühler (-18°C)"),
                temperaturecalculator.getOptimalBeerTemperature("Lager")
        );
        Assert.assertEquals(5640f, result, 5640f*relativeTemperatureDeviation);
    }

    @Test
    public void ThirtyDegreeStoutBottleInFreezer(){
        float result =  temperaturecalculator.calculateOptimalTemperature(
                temperaturecalculator.getContainerCoefficient("Glasflasche"),
                temperaturecalculator.getInitialBeerTemperature("Aussentemperatur (30°C)"),
                temperaturecalculator.getFridgeTemperature("Tiefkühler (-18°C)"),
                temperaturecalculator.getOptimalBeerTemperature("Irish Stout")
        );
        Assert.assertEquals(3540f, result, 3540f*relativeTemperatureDeviation);
    }

    @Test
    public void ThirtyDegreeAleBottleInFreezer(){
        float result =  temperaturecalculator.calculateOptimalTemperature(
                temperaturecalculator.getContainerCoefficient("Glasflasche"),
                temperaturecalculator.getInitialBeerTemperature("Aussentemperatur (30°C)"),
                temperaturecalculator.getFridgeTemperature("Tiefkühler (-18°C)"),
                temperaturecalculator.getOptimalBeerTemperature("Ale")
        );
        Assert.assertEquals(4380f, result, 4380f*relativeTemperatureDeviation);
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void  ConverterWithArbitraryCode() {
        String result = temperaturecalculator.convertCoolingTimeIntToString(-1337);
        Assert.assertTrue(result.equals("Bier ist bereits optimal gekühlt"));
    }

    @Test
    public void ConverterWithNegativeInput() {
        String result = temperaturecalculator.convertCoolingTimeIntToString(-42);
        Assert.assertTrue(result.equals("Fehler: Fehlerhafte Eingabe"));
    }

    @Test
    public void ConverterWithCorrectInput(){
        String result = temperaturecalculator.convertCoolingTimeIntToString(666);
        Assert.assertTrue(result.equals("Ungefähre Kühlzeit: 0 Stunden, 11 Minuten und 6 Sekunden"));
    }
}
