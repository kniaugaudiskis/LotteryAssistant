package linas.lotteryassistant.Activities.GeneratorActivities;

import android.content.DialogInterface;
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

public class RandomNumbersGeneratorActivity extends AppCompatActivity
{
    RadioGroup radioGroup;
    RadioButton randomSingleCombinationRadioButton, randomMultipleCombinationsRadioButton;
    Button randomMultipleCombinationsDecreaseButton, randomMultipleCombinationsIncreaseButton;
    EditText randomMultipleCombinationsCountEditText;
    TextView randomMultipleCombinationsText;

    Generator randomNumbersGenerator = new Generator();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_numbers_generator);

        radioGroup = (RadioGroup)findViewById(R.id.RandomNumbersGeneratorRadioGroup);
        randomSingleCombinationRadioButton = (RadioButton)findViewById(R.id.RandomSingleCombinationRadioButton);
        randomMultipleCombinationsRadioButton = (RadioButton)findViewById(R.id.RandomMultipleCombinationRadioButton);
        randomMultipleCombinationsDecreaseButton = (Button)findViewById(R.id.RandomMultipleCombinationsDecreaseButton);
        randomMultipleCombinationsIncreaseButton = (Button)findViewById(R.id.RandomMultipleCombinationsIncreaseButton);
        randomMultipleCombinationsCountEditText = (EditText)findViewById(R.id.RandomMultipleCombinationsCountEditText);
        randomMultipleCombinationsText = (TextView)findViewById(R.id.RandomMultipleCombinationsText);

        SetupRadioButtons();

        if (GeneratorSettings.randomGeneratorMainNumbers.size() == 0)
        {
            GeneratorSettings.InitializeRandomNumbersGenerator();
        }
    }

    public void GenerateButton_Click(View v)
    {
        String generatedCombinationsString = "";

        if (randomSingleCombinationRadioButton.isChecked())
        {
            generatedCombinationsString = randomNumbersGenerator.GetGeneratedCombinations(1, "RandomGenerator");
        }
        else if (randomMultipleCombinationsRadioButton.isChecked())
        {
            int combinationCountToGenerate = Integer.parseInt(randomMultipleCombinationsCountEditText.getText().toString());

            generatedCombinationsString = randomNumbersGenerator.GetGeneratedCombinations(combinationCountToGenerate, "RandomGenerator");
        }

        Print(generatedCombinationsString);
    }

    public void RandomMultipleCombinationsIncreaseButton_Click(View v)
    {
        int currentValue = Integer.parseInt(randomMultipleCombinationsCountEditText.getText().toString());

        currentValue++;
        randomMultipleCombinationsCountEditText.setText(Integer.toString(currentValue));
    }

    public void RandomMultipleCombinationsDecreaseButton_Click(View v)
    {
        int currentValue = Integer.parseInt(randomMultipleCombinationsCountEditText.getText().toString());

        currentValue--;

        if (currentValue < 2)
        {
            currentValue = 2;
        }
        randomMultipleCombinationsCountEditText.setText(Integer.toString(currentValue));
    }

    private void Print(String generatedCombinationsString)
    {
        AlertDialog.Builder aBuilder = new AlertDialog.Builder(RandomNumbersGeneratorActivity.this);
        aBuilder.setMessage(generatedCombinationsString).setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.cancel();
            }
        });
        AlertDialog alert = aBuilder.create();
        if (randomSingleCombinationRadioButton.isChecked())
        {
            alert.setTitle("Random combination");
        }
        else if (randomMultipleCombinationsRadioButton.isChecked())
        {
            alert.setTitle("Random combinations");
        }
        alert.show();
    }

    private void SetupRadioButtons()
    {
        randomMultipleCombinationsDecreaseButton.setVisibility(View.INVISIBLE);
        randomMultipleCombinationsIncreaseButton.setVisibility(View.INVISIBLE);
        randomMultipleCombinationsCountEditText.setVisibility(View.INVISIBLE);
        randomMultipleCombinationsText.setVisibility(View.INVISIBLE);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                if (randomSingleCombinationRadioButton.isChecked())
                {
                    randomMultipleCombinationsDecreaseButton.setVisibility(View.INVISIBLE);
                    randomMultipleCombinationsIncreaseButton.setVisibility(View.INVISIBLE);
                    randomMultipleCombinationsCountEditText.setVisibility(View.INVISIBLE);
                    randomMultipleCombinationsText.setVisibility(View.INVISIBLE);
                }
                else if (randomMultipleCombinationsRadioButton.isChecked())
                {
                    randomMultipleCombinationsDecreaseButton.setVisibility(View.VISIBLE);
                    randomMultipleCombinationsIncreaseButton.setVisibility(View.VISIBLE);
                    randomMultipleCombinationsCountEditText.setVisibility(View.VISIBLE);
                    randomMultipleCombinationsText.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
