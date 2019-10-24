package ch.beerpro;

import org.junit.Assert;
import org.junit.Test;

import ch.beerpro.Temperaturecalculator;
import ch.beerpro.testBuilder.CalculatorTestBuilder;

public class TemperaturecalculatorTest {
    private String containerType;
    private String initialTemperature;
    private String fridgeTemperature;
    private String desiredTemperature;

    CalculatorTestBuilder testBuilder;
    Temperaturecalculator temperaturecalculator = new Temperaturecalculator();
    @Test
    public void correctCalculation(){
        containerType = "Dose";
        initialTemperature = "Kellertemperatur (12°C)";
        fridgeTemperature = "Kühlschrank (4°C)";
        desiredTemperature = "Lager";

        temperaturecalculator.calculateOptimalTemperature();
        Assert.assertTrue("false", false);

    }
}
