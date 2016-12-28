package linas.lotteryassistant.Activities.AnalyzerActivities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import java.util.List;
import linas.lotteryassistant.Config.AppConfig;
import linas.lotteryassistant.Core.Analyzer.EvenOddNumbers;
import linas.lotteryassistant.R;

public class EvenOddNumbersActivity extends AppCompatActivity
{
    private EvenOddNumbers evenOddNumbers;
    private RadioGroup radioGroup;
    private RadioButton mainNumbersRadioButton, additionalNumbersRadioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_even_odd_numbers);

        radioGroup = (RadioGroup)findViewById(R.id.EvenOddNumbersRadioGroup);
        mainNumbersRadioButton = (RadioButton)findViewById(R.id.EvenOddNumbersMainNumbers);
        additionalNumbersRadioButton = (RadioButton)findViewById(R.id.EvenOddNumbersAdditionalNumbers);

        evenOddNumbers = new EvenOddNumbers();
        if (AppConfig.gameName.equals("EuroJackpot"))
        {
            SetupRadioButtons();
        }
        else if (AppConfig.gameName.equals("VikingLotto"))
        {
            radioGroup.setVisibility(View.INVISIBLE);
        }

        Print(evenOddNumbers.GetEvenOddNumbersDistribution("MainNumbers"));
    }

    private void SetupRadioButtons()
    {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                evenOddNumbers.ClearEvenOddNumbersSectionList();

                if (mainNumbersRadioButton.isChecked())
                {
                    Print(evenOddNumbers.GetEvenOddNumbersDistribution("MainNumbers"));
                }
                else if (additionalNumbersRadioButton.isChecked())
                {
                    Print(evenOddNumbers.GetEvenOddNumbersDistribution("AdditionalNumbers"));
                }
            }
        });
    }

    private void Info_Click(View v)
    {
        String infoMessage = "The section displays the distribution of even and odd numbers. All lucky number combinations are included. \n \n";

        AlertDialog.Builder aBuilder = new AlertDialog.Builder(EvenOddNumbersActivity.this);
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
        ListView listView = (ListView) findViewById(R.id.EvenOddNumbersListView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.custom_listview_lucky_numbers, evenOddStringList);
        listView.setAdapter(adapter);
    }
}
