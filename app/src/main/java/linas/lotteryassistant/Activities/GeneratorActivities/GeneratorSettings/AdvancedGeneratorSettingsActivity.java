package linas.lotteryassistant.Activities.GeneratorActivities.GeneratorSettings;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import linas.lotteryassistant.Config.AppConfig;
import linas.lotteryassistant.Core.Generator.GeneratorSettings.GeneratorControlSettings;
import linas.lotteryassistant.Core.Generator.GeneratorSettings.GeneratorSettings;
import linas.lotteryassistant.Core.Generator.GeneratorValidator.GeneratorValidator;
import linas.lotteryassistant.R;

public class AdvancedGeneratorSettingsActivity extends AppCompatActivity
{
    private Button mainNumbersMostPopularEvenOddRatiosCustomizeButton, mainNumbersMostPopularHighLowRatiosCustomizeButton, mainNumbersSumRangeCustomizeButton,
            additionalNumbersSumRangeCustomizeButton, numberPickerButton;
    private Button mainNumbersMostPopularRatiosInfoButton, mainNumbersMostPopularHighLowRatiosInfoButton, mainNumbersSumRangeInfoButton, additionalNumbersSumRangeInfoButton, numberPickerInfoButton;
    private Button restoreGeneratorDefaultsButton;
    private CheckBox mainNumbersMostPopularEvenOddRatiosCheckbox, mainNumbersMostPopularHighLowRatiosCheckbox, mainNumbersSumRangeCheckbox, additionalNumbersSumRangeCheckbox, numberPickerCheckbox;
    private View additionalNumbersSumRangeSeparator;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generator_settings);

        mainNumbersMostPopularEvenOddRatiosCustomizeButton = (Button)findViewById(R.id.MainNumbersMostPopularEvenOddRatiosCustomizeButton);
        mainNumbersMostPopularHighLowRatiosCustomizeButton = (Button)findViewById(R.id.MainNumbersMostPopularHighLowRatiosCustomizeButton);
        mainNumbersSumRangeCustomizeButton = (Button)findViewById(R.id.MainNumbersSumRangeCustomizeButton);
        additionalNumbersSumRangeCustomizeButton = (Button)findViewById(R.id.AdditionalNumbersSumRangeCustomizeButton);
        numberPickerButton = (Button)findViewById(R.id.NumberPickerButton);
        mainNumbersMostPopularRatiosInfoButton = (Button)findViewById(R.id.MainNumbersMostPopularRatiosInfoButton);
        mainNumbersMostPopularHighLowRatiosInfoButton = (Button)findViewById(R.id.MainNumbersMostPopularHighLowRatiosInfoButton);
        mainNumbersSumRangeInfoButton = (Button)findViewById(R.id.MainNumbersSumRangeInfoButton);
        additionalNumbersSumRangeInfoButton = (Button)findViewById(R.id.AdditionalNumbersSumRangeInfoButton);
        numberPickerInfoButton = (Button)findViewById(R.id.NumberPickerInfoButton);
        restoreGeneratorDefaultsButton = (Button)findViewById(R.id.RestoreGeneratorDefaultsButton);

        mainNumbersMostPopularEvenOddRatiosCheckbox = (CheckBox) findViewById(R.id.MainNumbersMostPopularEvenOddRatiosCheckbox);
        mainNumbersMostPopularHighLowRatiosCheckbox = (CheckBox) findViewById(R.id.MainNumbersMostPopularHighLowRatiosCheckbox);
        mainNumbersSumRangeCheckbox = (CheckBox) findViewById(R.id.MainNumbersSumRangeCheckbox);
        additionalNumbersSumRangeCheckbox = (CheckBox) findViewById(R.id.AdditionalNumbersSumRangeCheckbox);
        numberPickerCheckbox = (CheckBox) findViewById(R.id.NumberPickerCheckbox);

        additionalNumbersSumRangeSeparator = (View)findViewById(R.id.AdditionalNumbersSumRangeSeparator);

        InitializeCheckBoxes();

        if (AppConfig.gameName.equals("VikingLotto"))
        {
            additionalNumbersSumRangeCustomizeButton.setVisibility(View.GONE);
            additionalNumbersSumRangeInfoButton.setVisibility(View.GONE);
            additionalNumbersSumRangeCheckbox.setVisibility(View.GONE);
            additionalNumbersSumRangeSeparator.setVisibility(View.GONE);

            GeneratorControlSettings.isAdditionalNumbersSumRangeBoxChecked = false;
        }
    }

    private void InitializeCheckBoxes()
    {
        mainNumbersMostPopularEvenOddRatiosCheckbox.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                if (mainNumbersMostPopularEvenOddRatiosCheckbox.isChecked())
                {
                    mainNumbersMostPopularEvenOddRatiosCustomizeButton.setEnabled(true);
                    GeneratorControlSettings.isMainNumbersMostPopularEvenOddRatiosBoxChecked = true;
                }
                else
                {
                    mainNumbersMostPopularEvenOddRatiosCustomizeButton.setEnabled(false);
                    GeneratorControlSettings.isMainNumbersMostPopularEvenOddRatiosBoxChecked = false;
                }
            }
        });

        mainNumbersMostPopularHighLowRatiosCheckbox.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                if (mainNumbersMostPopularHighLowRatiosCheckbox.isChecked())
                {
                    mainNumbersMostPopularHighLowRatiosCustomizeButton.setEnabled(true);
                    GeneratorControlSettings.isMainNumbersMostPopularHighLowRatiosBoxChecked = true;
                }
                else
                {
                    mainNumbersMostPopularHighLowRatiosCustomizeButton.setEnabled(false);
                    GeneratorControlSettings.isMainNumbersMostPopularHighLowRatiosBoxChecked = false;
                }
            }
        });

        mainNumbersSumRangeCheckbox.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                if (mainNumbersSumRangeCheckbox.isChecked())
                {
                    mainNumbersSumRangeCustomizeButton.setEnabled(true);
                    GeneratorControlSettings.isMainNumbersSumRangeBoxChecked = true;
                }
                else
                {
                    mainNumbersSumRangeCustomizeButton.setEnabled(false);
                    GeneratorControlSettings.isMainNumbersSumRangeBoxChecked = false;
                }
            }
        });

        additionalNumbersSumRangeCheckbox.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                if (additionalNumbersSumRangeCheckbox.isChecked())
                {
                    additionalNumbersSumRangeCustomizeButton.setEnabled(true);
                    GeneratorControlSettings.isAdditionalNumbersSumRangeBoxChecked = true;
                }
                else
                {
                    additionalNumbersSumRangeCustomizeButton.setEnabled(false);
                    GeneratorControlSettings.isAdditionalNumbersSumRangeBoxChecked = false;
                }
            }
        });

        numberPickerCheckbox.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                if (numberPickerCheckbox.isChecked())
                {
                    numberPickerButton.setEnabled(true);
                    GeneratorControlSettings.isNumberPickerBoxChecked = true;
                }
                else
                {
                    numberPickerButton.setEnabled(false);
                    GeneratorControlSettings.isNumberPickerBoxChecked = false;
                }
            }
        });

        if (GeneratorControlSettings.isMainNumbersMostPopularEvenOddRatiosBoxChecked) { mainNumbersMostPopularEvenOddRatiosCheckbox.setChecked(true); }
        else { mainNumbersMostPopularEvenOddRatiosCustomizeButton.setEnabled(false); }

        if (GeneratorControlSettings.isMainNumbersMostPopularHighLowRatiosBoxChecked) { mainNumbersMostPopularHighLowRatiosCheckbox.setChecked(true); }
        else { mainNumbersMostPopularHighLowRatiosCustomizeButton.setEnabled(false); }

        if (GeneratorControlSettings.isMainNumbersSumRangeBoxChecked) { mainNumbersSumRangeCheckbox.setChecked(true); }
        else { mainNumbersSumRangeCustomizeButton.setEnabled(false); }

        if (GeneratorControlSettings.isAdditionalNumbersSumRangeBoxChecked) { additionalNumbersSumRangeCheckbox.setChecked(true); }
        else { additionalNumbersSumRangeCustomizeButton.setEnabled(false); }

        if (GeneratorControlSettings.isNumberPickerBoxChecked) { numberPickerCheckbox.setChecked(true); }
        else { numberPickerButton.setEnabled(false); }
    }

    //********************/
    /* Customize buttons */
    //*******************/
    public void MostPopularEvenOddRatiosCustomizeButton_Click(View v)
    {
        String[] selectionItems = null;

        if (AppConfig.gameName.equals("VikingLotto"))
        {
            String[] items = { "0 even - 6 odd",
                               "1 even - 5 odd",
                               "2 even - 4 odd",
                               "3 even - 3 odd",
                               "4 even - 2 odd",
                               "5 even - 1 odd",
                               "6 even - 0 odd" };
            selectionItems = items;
        }
        else if (AppConfig.gameName.equals("EuroJackpot"))
        {
            String[] items = { "0 even - 5 odd",
                               "1 even - 4 odd",
                               "2 even - 3 odd",
                               "3 even - 2 odd",
                               "4 even - 1 odd",
                               "5 even - 0 odd" };
            selectionItems = items;
        }

        boolean[] checkedValues = CreateCheckedValuesArray("MainEvenNumbers");

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Set the ratios of even and odd numbers in a combination");
        builder.setMultiChoiceItems(selectionItems, checkedValues, new DialogInterface.OnMultiChoiceClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int indexSelected, boolean isChecked)
            {
                if (isChecked)
                {
                    GeneratorSettings.UpdateMainEvenNumberCount(indexSelected, "Add");
                }
                else
                {
                    GeneratorSettings.UpdateMainEvenNumberCount(indexSelected, "Remove");
                }
            }
        }).setPositiveButton("OK", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int id)
            {
                if (GeneratorSettings.mainEvenNumberCountInCombination.isEmpty())
                {
                    mainNumbersMostPopularEvenOddRatiosCheckbox.setChecked(false);
                    mainNumbersMostPopularEvenOddRatiosCustomizeButton.setEnabled(false);
                }

                Toast.makeText(AdvancedGeneratorSettingsActivity.this, "Customized successfully.", Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void MostPopularHighLowRatiosCustomizeButton_Click(View v)
    {
        String[] selectionItems = null;

        if (AppConfig.gameName.equals("VikingLotto"))
        {
            String[] items = { "0 high - 6 low",
                               "1 high - 5 low",
                               "2 high - 4 low",
                               "3 high - 3 low",
                               "4 high - 2 low",
                               "5 high - 1 low",
                               "6 high - 0 low" };
            selectionItems = items;
        }
        else if (AppConfig.gameName.equals("EuroJackpot"))
        {
            String[] items = { "0 high - 5 low",
                               "1 high - 4 low",
                               "2 high - 3 low",
                               "3 high - 2 low",
                               "4 high - 1 low",
                               "5 high - 0 low" };
            selectionItems = items;
        }

        boolean[] checkedValues = CreateCheckedValuesArray("MainHighNumbers");

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Set the ratios of high and low numbers in a combination");
        builder.setMultiChoiceItems(selectionItems, checkedValues, new DialogInterface.OnMultiChoiceClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int indexSelected, boolean isChecked)
            {
                if (isChecked)
                {
                    GeneratorSettings.UpdateMainHighNumberCount(indexSelected, "Add");
                }
                else
                {
                    GeneratorSettings.UpdateMainHighNumberCount(indexSelected, "Remove");
                }
            }
        }).setPositiveButton("OK", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int id)
            {
                if (GeneratorSettings.mainHighNumberCountInCombination.isEmpty())
                {
                    mainNumbersMostPopularHighLowRatiosCheckbox.setChecked(false);
                    mainNumbersMostPopularHighLowRatiosCustomizeButton.setEnabled(false);
                }

                Toast.makeText(AdvancedGeneratorSettingsActivity.this, "Customized successfully.", Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void MainNumbersSumRangeCustomizeButton_Click(View v)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        String title = "Main numbers minimal sum: " + AppConfig.minMainNumbersSum + "\n";
        title += "Main numbers maximum sum: " + AppConfig.maxMainNumbersSum + "\n";
        title += "Set combination sum range:";

        TextView textView = new TextView(this);
        textView.setText(title);
        builder.setCustomTitle(textView);

        final EditText mainNumbersSumRangeFromEditText = new EditText(this);
        final EditText mainNumbersSumRangeToEditText = new EditText(this);

        if (GeneratorSettings.mainNumbersCombinationSumRange.size() != 0)
        {
            mainNumbersSumRangeFromEditText.setText(GeneratorSettings.mainNumbersCombinationSumRange.get(0).toString());
            mainNumbersSumRangeToEditText.setText(GeneratorSettings.mainNumbersCombinationSumRange.get(1).toString());
        }
        else
        {
            mainNumbersSumRangeFromEditText.setText("Sum range from");
            mainNumbersSumRangeToEditText.setText("Sum range to");
        }

        mainNumbersSumRangeFromEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
        mainNumbersSumRangeToEditText.setInputType(InputType.TYPE_CLASS_NUMBER);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.addView(mainNumbersSumRangeFromEditText);
        layout.addView(mainNumbersSumRangeToEditText);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int id)
            {
                try
                {
                    int mainNumbersSumRangeFrom = Integer.parseInt(mainNumbersSumRangeFromEditText.getText().toString());
                    int mainNumbersSumRangeTo = Integer.parseInt(mainNumbersSumRangeToEditText.getText().toString());

                    if (GeneratorValidator.IsCombinationNumbersSumRangeValid(mainNumbersSumRangeFrom, mainNumbersSumRangeTo, "MainNumbers"))
                    {
                        GeneratorSettings.UpdateSumRange(mainNumbersSumRangeFrom, mainNumbersSumRangeTo, "MainNumbers");
                        Toast.makeText(AdvancedGeneratorSettingsActivity.this, "Customized successfully.", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(AdvancedGeneratorSettingsActivity.this, "Incorrect sum range specified.", Toast.LENGTH_SHORT).show();
                    }

                    dialog.cancel();
                }
                catch (Exception ex)
                {
                    Toast.makeText(AdvancedGeneratorSettingsActivity.this, "Could not customize.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        AlertDialog dialog = builder.create();
        dialog.setView(layout);
        dialog.show();
    }

    public void AdditionalNumbersSumRangeCustomizeButton_Click(View v)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Additional numbers minimal sum: " + AppConfig.minAdditionalNumbersSum);
        builder.setTitle("Additional numbers maximum sum: " + AppConfig.maxAdditionalNumbersSum);
        builder.setTitle("Set combination sum range:");

        final EditText additionalNumbersSumRangeFromEditText = new EditText(this);
        final EditText additionalNumbersSumRangeToEditText = new EditText(this);

        if (GeneratorSettings.mainNumbersCombinationSumRange.size() != 0)
        {
            additionalNumbersSumRangeFromEditText.setText(GeneratorSettings.additionalNumbersCombinationSumRange.get(0).toString());
            additionalNumbersSumRangeToEditText.setText(GeneratorSettings.additionalNumbersCombinationSumRange.get(1).toString());
        }
        else
        {
            additionalNumbersSumRangeFromEditText.setText("Sum range from");
            additionalNumbersSumRangeToEditText.setText("Sum range to");
        }

        additionalNumbersSumRangeFromEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
        additionalNumbersSumRangeToEditText.setInputType(InputType.TYPE_CLASS_NUMBER);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.addView(additionalNumbersSumRangeFromEditText);
        layout.addView(additionalNumbersSumRangeToEditText);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int id)
            {
                try
                {
                    int additionalNumbersSumRangeFrom = Integer.parseInt(additionalNumbersSumRangeFromEditText.getText().toString());
                    int additionalNumbersSumRangeTo = Integer.parseInt(additionalNumbersSumRangeToEditText.getText().toString());

                    if (GeneratorValidator.IsCombinationNumbersSumRangeValid(additionalNumbersSumRangeFrom, additionalNumbersSumRangeTo, "AdditionalNumbers"))
                    {
                        GeneratorSettings.UpdateSumRange(additionalNumbersSumRangeFrom, additionalNumbersSumRangeTo, "AdditionalNumbers");
                        Toast.makeText(AdvancedGeneratorSettingsActivity.this, "Customized successfully.", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(AdvancedGeneratorSettingsActivity.this, "Incorrect sum range specified.", Toast.LENGTH_SHORT).show();
                    }

                    dialog.cancel();
                }
                catch (Exception ex)
                {
                    Toast.makeText(AdvancedGeneratorSettingsActivity.this, "Could not customize.", Toast.LENGTH_SHORT).show();
                }

                dialog.cancel();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.setView(layout);
        dialog.show();
    }

    public void NumberPickerButton_Click(View v)
    {
        Intent intent = new Intent("AdvancedNumbersGeneratorNumberPickerActivity");
        startActivity(intent);
    }

    //****************/
    /* Info buttons */
    //**************/
    public  void MostPopularEvenOddRatiosInfoButton_Click(View v)
    {
        String message = "TODO";

        AlertDialog.Builder aBuilder = new AlertDialog.Builder(AdvancedGeneratorSettingsActivity.this);
        aBuilder.setMessage(message).setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.cancel();
            }
        });
        AlertDialog alert = aBuilder.create();
        alert.setTitle("Information");
        alert.show();
    }

    public void MostPopularHighLowRatiosInfoButton_Click(View v)
    {
        String message = "TODO";

        AlertDialog.Builder aBuilder = new AlertDialog.Builder(AdvancedGeneratorSettingsActivity.this);
        aBuilder.setMessage(message).setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.cancel();
            }
        });
        AlertDialog alert = aBuilder.create();
        alert.setTitle("Information");
        alert.show();
    }

    public void MainNumbersSumRangeInfoButton_Click(View v)
    {
        String message = "TODO";

        AlertDialog.Builder aBuilder = new AlertDialog.Builder(AdvancedGeneratorSettingsActivity.this);
        aBuilder.setMessage(message).setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.cancel();
            }
        });
        AlertDialog alert = aBuilder.create();
        alert.setTitle("Information");
        alert.show();
    }

    public void AdditionalNumbersSumRangeInfoButton_Click(View v)
    {
        String message = "TODO";

        AlertDialog.Builder aBuilder = new AlertDialog.Builder(AdvancedGeneratorSettingsActivity.this);
        aBuilder.setMessage(message).setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.cancel();
            }
        });
        AlertDialog alert = aBuilder.create();
        alert.setTitle("Information");
        alert.show();
    }

    public void LotteryNumbersPickerInfoButton_Click(View v)
    {
        String message = "TODO";

        AlertDialog.Builder aBuilder = new AlertDialog.Builder(AdvancedGeneratorSettingsActivity.this);
        aBuilder.setMessage(message).setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.cancel();
            }
        });
        AlertDialog alert = aBuilder.create();
        alert.setTitle("Information");
        alert.show();
    }

    //Methods
    private boolean[] CreateCheckedValuesArray(String type)
    {
        boolean[] checkedValues = new boolean[(int)AppConfig.mainNumbersCombinationLength + 1];

        if (type.equals("MainEvenNumbers"))
        {
            for (int i = 0; i < GeneratorSettings.mainEvenNumberCountInCombination.size(); i++)
            {
                checkedValues[GeneratorSettings.mainEvenNumberCountInCombination.get(i)] = true;
            }
        }
        else if (type.equals("MainHighNumbers"))
        {
            for (int i = 0; i < GeneratorSettings.mainHighNumberCountInCombination.size(); i++)
            {
                checkedValues[GeneratorSettings.mainHighNumberCountInCombination.get(i)] = true;
            }
        }

        return checkedValues;
    }
}
