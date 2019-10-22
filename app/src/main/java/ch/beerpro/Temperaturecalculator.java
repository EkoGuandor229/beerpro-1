package ch.beerpro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

public class Temperaturecalculator extends AppCompatActivity {
    private String containerType;
    private String initialTemperature;
    private String fridgeTemperature;
    private String desiredTemperature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperaturecalculator);

        Spinner containerSpinner = (Spinner) findViewById(R.id.container_type);
        Spinner initialTemperatureSpinner = (Spinner) findViewById(R.id.initial_temperature);
        Spinner fridgeTemperatureSpinner = (Spinner) findViewById(R.id.fridge_temperature);
        Spinner desiredTemperatureSpinner = (Spinner) findViewById(R.id.desired_temperature);

        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> containerAdapter = ArrayAdapter
                .createFromResource(this, R.array.container_types,
                        android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> initialTempAdapter = ArrayAdapter
                .createFromResource(this, R.array.initial_temperature,
                        android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> fridgeTempAdapter = ArrayAdapter
                .createFromResource(this, R.array.cooling_variant,
                        android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> desiredTempAdapter = ArrayAdapter
                .createFromResource(this, R.array.beer_type,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        containerAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        initialTempAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fridgeTempAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        desiredTempAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapters to the spinners
        containerSpinner.setAdapter(containerAdapter);
        initialTemperatureSpinner.setAdapter(initialTempAdapter);
        fridgeTemperatureSpinner.setAdapter(fridgeTempAdapter);
        desiredTemperatureSpinner.setAdapter(desiredTempAdapter);

        containerSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                containerType = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                containerType = parent.getItemAtPosition(0).toString();
            }
        });
        initialTemperatureSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                initialTemperature = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                initialTemperature = parent.getItemAtPosition(0).toString();
            }
        });
        fridgeTemperatureSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                fridgeTemperature = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                fridgeTemperature = parent.getItemAtPosition(0).toString();
            }
        });
        desiredTemperatureSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                desiredTemperature = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                desiredTemperature = parent.getItemAtPosition(0).toString();
            }
        });
        Button calculationButton = findViewById(R.id.calculationbutton);
        calculationButton.setOnClickListener(view -> calculateOptimalTemperature());
    }

    private void calculateOptimalTemperature() {
        //TODO: Refactor
        /*
        * To add more different variables, add them as Items in the strings.xml.
        * IMPORTANT: The first item in the strings.xml is the default variable for the
        * calculation. Add new items at the end of the strings-array
        */
        double containerCoefficient;
        int initialBeerTemperature;
        int fridgeTemperature;
        int optimalBeerTemperature;
        int timeToCoolInSeconds;

        if (containerType.equals("Glasflasche")) {
            containerCoefficient = -0.00012d;
        } else {
            containerCoefficient = -0.0007d;
        }
        if (initialTemperature.equals("Aussentemperatur (30°C)")) {
            initialBeerTemperature = 30;
        } else if (initialTemperature.equals("Kellertemperatur (12°C)")) {
            initialBeerTemperature = 12;
        } else {
            initialBeerTemperature = 20;
        }
        if (this.fridgeTemperature.equals("Tiefkühler (-18°C)")) {
            fridgeTemperature = -18;
        } else {
            fridgeTemperature = 4;
        }
        if (desiredTemperature.equals("Ale")) {
            optimalBeerTemperature = 10;
        } else if (desiredTemperature.equals(("Irish Stout"))) {
            optimalBeerTemperature = 13;
        } else {
            optimalBeerTemperature = 6;
        }
        //time = (ln((T2-T0)/(T1-T0))/-k
        int formuladivisor = Math.abs(optimalBeerTemperature-fridgeTemperature);
        int formuladividend = Math.abs(initialBeerTemperature-fridgeTemperature);
        double logarithmOfDivision = Math.log((double)formuladivisor/(double)formuladividend);
        timeToCoolInSeconds = (int) Math.round(logarithmOfDivision/containerCoefficient);
        int hoursToCool = (timeToCoolInSeconds/3600);
        int minutesToCool =((timeToCoolInSeconds%3600)/60);
        int secondsToCool = (timeToCoolInSeconds%60);
        String timeToCoolAsString = "Time to Cool: " + hoursToCool + " hours, " + minutesToCool + " minutes and " + secondsToCool + " seconds";
        Toast.makeText(this, (timeToCoolAsString), Toast.LENGTH_LONG).show();

    }
}
