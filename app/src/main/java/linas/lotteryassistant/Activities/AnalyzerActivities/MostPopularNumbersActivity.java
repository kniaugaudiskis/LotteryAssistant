package linas.lotteryassistant.Activities.AnalyzerActivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import java.util.List;
import linas.lotteryassistant.Config.AppConfig;
import linas.lotteryassistant.Core.Analyzer.MostPopularNumbers;
import linas.lotteryassistant.R;

public class MostPopularNumbersActivity extends AppCompatActivity
{
    private RadioGroup radioGroup;
    private RadioButton mainNumbersRadioButton, additionalNumbersRadioButton;
    private MostPopularNumbers mostPopularNumbers;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_most_popular_numbers);

        mostPopularNumbers = new MostPopularNumbers();

        radioGroup = (RadioGroup)findViewById(R.id.MostPopularNumbersRadioGroup);
        mainNumbersRadioButton = (RadioButton)findViewById(R.id.MostPopularNumbersMainNumbers);
        additionalNumbersRadioButton = (RadioButton)findViewById(R.id.MostPopularNumbersAdditionalNumbers);

        if (AppConfig.gameName.equals("EuroJackpot"))
        {
            SetupRadioButtons();
        }
        else if (AppConfig.gameName.equals("VikingLotto"))
        {
            radioGroup.setVisibility(View.INVISIBLE);
        }

        Print(mostPopularNumbers.GetMostPopularNumbers("MainNumbers"));
    }

    private void SetupRadioButtons()
    {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                if (mainNumbersRadioButton.isChecked())
                {
                    Print(mostPopularNumbers.GetMostPopularNumbers("MainNumbers"));
                }
                else if (additionalNumbersRadioButton.isChecked())
                {
                    Print(mostPopularNumbers.GetMostPopularNumbers("AdditionalNumbers"));
                }
            }
        });
    }

    private void Print(List<String> evenOddStringList)
    {
        ListView listView = (ListView) findViewById(R.id.MostPopularNumbersListView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.custom_listview_lucky_numbers, evenOddStringList);
        listView.setAdapter(adapter);
    }
}
