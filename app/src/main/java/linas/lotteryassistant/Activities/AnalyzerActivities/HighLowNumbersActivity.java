package linas.lotteryassistant.Activities.AnalyzerActivities;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.List;

import linas.lotteryassistant.Config.AppConfig;
import linas.lotteryassistant.Core.Analyzer.EvenOddNumbers;
import linas.lotteryassistant.Core.Analyzer.HighLowNumbers;
import linas.lotteryassistant.R;

public class HighLowNumbersActivity extends AppCompatActivity
{
    private HighLowNumbers highLowNumbers;
    private RadioGroup radioGroup;
    private RadioButton mainNumbersRadioButton, additionalNumbersRadioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_low_numbers);

        radioGroup = (RadioGroup)findViewById(R.id.HighLowNumbersRadioGroup);
        mainNumbersRadioButton = (RadioButton)findViewById(R.id.HighLowMainNumbers);
        additionalNumbersRadioButton = (RadioButton)findViewById(R.id.HighLowAdditionalNumbers);

        highLowNumbers = new HighLowNumbers();

        if (AppConfig.gameName.equals("EuroJackpot"))
        {
            SetupRadioButtons();
        }
        else if (AppConfig.gameName.equals("VikingLotto"))
        {
            radioGroup.setVisibility(View.INVISIBLE);
        }

        Print(highLowNumbers.GetHighLowNumbersDistribution("MainNumbers"));
    }

    private void SetupRadioButtons()
    {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                highLowNumbers.ClearHighLowNumbersSectionList();

                if (mainNumbersRadioButton.isChecked())
                {
                    Print(highLowNumbers.GetHighLowNumbersDistribution("MainNumbers"));
                }
                else if (additionalNumbersRadioButton.isChecked())
                {
                    Print(highLowNumbers.GetHighLowNumbersDistribution("AdditionalNumbers"));
                }
            }
        });
    }

    private void Info_Click(View v)
    {
        String infoMessage = "The section displays the distribution of even and odd numbers. All lucky number combinations are included. \n \n";

        AlertDialog.Builder aBuilder = new AlertDialog.Builder(HighLowNumbersActivity.this);
        aBuilder.setMessage(infoMessage).setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener()
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

    private void Print(List<String> evenOddStringList)
    {
        ListView listView = (ListView) findViewById(R.id.HighLowNumbersListView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.custom_listview_lucky_numbers, evenOddStringList);
        listView.setAdapter(adapter);
    }
}
