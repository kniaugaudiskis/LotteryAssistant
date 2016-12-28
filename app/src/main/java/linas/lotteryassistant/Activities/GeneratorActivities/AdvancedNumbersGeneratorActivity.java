package linas.lotteryassistant.Activities.GeneratorActivities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import linas.lotteryassistant.Core.Generator.Generator;
import linas.lotteryassistant.Core.Generator.GeneratorSettings.GeneratorSettings;
import linas.lotteryassistant.R;

public class AdvancedNumbersGeneratorActivity extends AppCompatActivity
{
    RadioGroup radioGroup;
    RadioButton advancedSingleCombinationRadioButton, advancedMultipleCombinationsRadioButton;
    Button advancedMultipleCombinationsDecreaseButton, advancedMultipleCombinationsIncreaseButton;
    EditText advancedMultipleCombinationsCountEditText;
    TextView advancedMultipleCombinationsText;

    Generator advancedNumbersGenerator = new Generator();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_numbers_generator);

        radioGroup = (RadioGroup)findViewById(R.id.AdvancedNumbersGeneratorRadioGroup);
        advancedSingleCombinationRadioButton = (RadioButton)findViewById(R.id.AdvancedSingleCombinationRadioButton);
        advancedMultipleCombinationsRadioButton = (RadioButton)findViewById(R.id.AdvancedMultipleCombinationsRadioButton);
        advancedMultipleCombinationsDecreaseButton = (Button)findViewById(R.id.AdvancedMultipleCombinationsDecreaseButton);
        advancedMultipleCombinationsIncreaseButton = (Button)findViewById(R.id.AdvancedMultipleCombinationsIncreaseButton);
        advancedMultipleCombinationsCountEditText = (EditText)findViewById(R.id.AdvancedMultipleCombinationsCountEditText);
        advancedMultipleCombinationsText = (TextView)findViewById(R.id.AdvancedMultipleCombinationsText);

        SetupRadioButtons();

        if (GeneratorSettings.advancedGeneratorMainNumbers.size() == 0)
        {
            GeneratorSettings.InitializeAdvancedNumbersGeneratorDefaultSettings();
        }
    }

    public void GenerateButton_Click(View v)
    {
        String generatedCombinationsString = "";

        if (advancedSingleCombinationRadioButton.isChecked())
        {
            generatedCombinationsString = advancedNumbersGenerator.GetGeneratedCombinations(1, "AdvancedGenerator");
        }
        else if (advancedMultipleCombinationsRadioButton.isChecked())
        {
            int combinationCountToGenerate = Integer.parseInt(advancedMultipleCombinationsCountEditText.getText().toString());

            generatedCombinationsString = advancedNumbersGenerator.GetGeneratedCombinations(combinationCountToGenerate, "AdvancedGenerator");
        }

        Print(generatedCombinationsString);
    }

    public void AdvancedMultipleCombinationsIncreaseButton_Click(View v)
    {
        int currentValue = Integer.parseInt(advancedMultipleCombinationsCountEditText.getText().toString());
        currentValue++;
        advancedMultipleCombinationsCountEditText.setText(Integer.toString(currentValue));
    }

    public void AdvancedMultipleCombinationsDecreaseButton_Click(View v)
    {
        int currentValue = Integer.parseInt(advancedMultipleCombinationsCountEditText.getText().toString());
        currentValue--;
        if (currentValue < 2)
        {
            currentValue = 2;
        }
        advancedMultipleCombinationsCountEditText.setText(Integer.toString(currentValue));
    }

    public void AdvancedGeneratorSettingsButton_Click(View v)
    {
        Intent intent = new Intent("AdvancedNumbersGeneratorSettingsActivity");
        startActivity(intent);
    }

    public void AdvancedGeneratorViewSettingsButton_Click(View v)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(AdvancedNumbersGeneratorActivity.this);
        builder.setMessage("Advanced generator settings");

        String generatorSettings = GeneratorSettings.GetAdvancedGeneratorSettings();
        builder.setMessage(generatorSettings);

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.cancel();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void Print(String generatedCombinationsString)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(AdvancedNumbersGeneratorActivity.this);
        builder.setCancelable(false);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.cancel();
            }
        });

        if (advancedSingleCombinationRadioButton.isChecked())
        {
            builder.setTitle("Advanced combination");
        }
        else if (advancedMultipleCombinationsRadioButton.isChecked())
        {
            builder.setTitle("Advanced combinations");
        }

        if (!generatedCombinationsString.equals(""))
        {
            builder.setMessage(generatedCombinationsString);
        }
        else
        {
            builder.setMessage("Could not generate any combinations using your settings.");
        }

        AlertDialog alert = builder.create();
        alert.show();
    }

    private void SetupRadioButtons()
    {
        advancedMultipleCombinationsDecreaseButton.setVisibility(View.INVISIBLE);
        advancedMultipleCombinationsIncreaseButton.setVisibility(View.INVISIBLE);
        advancedMultipleCombinationsCountEditText.setVisibility(View.INVISIBLE);
        advancedMultipleCombinationsText.setVisibility(View.INVISIBLE);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                if (advancedSingleCombinationRadioButton.isChecked())
                {
                    advancedMultipleCombinationsDecreaseButton.setVisibility(View.INVISIBLE);
                    advancedMultipleCombinationsIncreaseButton.setVisibility(View.INVISIBLE);
                    advancedMultipleCombinationsCountEditText.setVisibility(View.INVISIBLE);
                    advancedMultipleCombinationsText.setVisibility(View.INVISIBLE);
                }
                else if (advancedMultipleCombinationsRadioButton.isChecked())
                {
                    advancedMultipleCombinationsDecreaseButton.setVisibility(View.VISIBLE);
                    advancedMultipleCombinationsIncreaseButton.setVisibility(View.VISIBLE);
                    advancedMultipleCombinationsCountEditText.setVisibility(View.VISIBLE);
                    advancedMultipleCombinationsText.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
