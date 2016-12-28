package linas.lotteryassistant.Activities.AnalyzerActivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Toast;
import java.util.List;
import linas.lotteryassistant.Config.AppConfig;
import linas.lotteryassistant.Core.Analyzer.SumDistribution;
import linas.lotteryassistant.R;

public class SumDistributionActivity extends AppCompatActivity
{
    private SumDistribution sumDistribution;
    private SeekBar mainNumbersSumDistributionSeekBar, additionalNumbersSumDistributionSeekBar;
    private RadioGroup radioGroup;
    private RadioButton mainNumbersRadioButton, additionalNumbersRadioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sum_distribution);

        mainNumbersSumDistributionSeekBar = (SeekBar)findViewById(R.id.MainNumbersSumDistributionSeekBar);
        additionalNumbersSumDistributionSeekBar = (SeekBar)findViewById(R.id.AdditionalNumbersSumDistributionSeekBar);
        radioGroup = (RadioGroup)findViewById(R.id.SumDistributionRadioGroup);
        mainNumbersRadioButton = (RadioButton)findViewById(R.id.MainNumbersRadioButton);
        additionalNumbersRadioButton = (RadioButton)findViewById(R.id.AdditionalNumbersRadioButton);

        sumDistribution = new SumDistribution();
        SetupSeekBars();
        if (AppConfig.gameName.equals("EuroJackpot"))
        {
            SetupRadioButtons();
        }

        int progress = sumDistribution.GetMainNumbersPredefinedProgress(mainNumbersSumDistributionSeekBar.getProgress());
        Print(sumDistribution.GetSumDistribution(progress, "MainNumbers"));
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
                    mainNumbersSumDistributionSeekBar.setVisibility(View.VISIBLE);
                    additionalNumbersSumDistributionSeekBar.setVisibility(View.INVISIBLE);
                    mainNumbersSumDistributionSeekBar.setProgress(AppConfig.defaultProgressMainNumbers);
                    Print(sumDistribution.GetSumDistribution(AppConfig.defaultProgressMainNumbers, "MainNumbers"));
                }
                else if (additionalNumbersRadioButton.isChecked())
                {
                    mainNumbersSumDistributionSeekBar.setVisibility(View.INVISIBLE);
                    additionalNumbersSumDistributionSeekBar.setVisibility(View.VISIBLE);
                    additionalNumbersSumDistributionSeekBar.setProgress(AppConfig.defaultProgressAdditionalNumbers);
                    Print(sumDistribution.GetSumDistribution(AppConfig.defaultProgressAdditionalNumbers, "AdditionalNumbers"));
                }
            }
        });
    }

    private void SetupSeekBars()
    {
        mainNumbersSumDistributionSeekBar.setMax(AppConfig.maxProgressMainNumbers);
        mainNumbersSumDistributionSeekBar.setProgress(AppConfig.defaultProgressMainNumbers);

        radioGroup.setVisibility(View.INVISIBLE);
        additionalNumbersSumDistributionSeekBar.setVisibility(View.INVISIBLE);

        if (AppConfig.gameName.equals("EuroJackpot"))
        {
            radioGroup.setVisibility(View.VISIBLE);

            additionalNumbersSumDistributionSeekBar.setMax(AppConfig.maxProgressAdditionalNumbers);
            additionalNumbersSumDistributionSeekBar.setProgress(AppConfig.defaultProgressAdditionalNumbers);

            additionalNumbersSumDistributionSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
            {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {}

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {}

                @Override
                public void onStopTrackingTouch(SeekBar seekBar)
                {
                    int progress = sumDistribution.GetAdditionalNumbersPredefinedProgress(additionalNumbersSumDistributionSeekBar.getProgress());
                    additionalNumbersSumDistributionSeekBar.setProgress(progress);
                    Toast.makeText(SumDistributionActivity.this, "Sum distribution step size: " + Integer.toString(seekBar.getProgress()), Toast.LENGTH_SHORT).show();
                    Print(sumDistribution.GetSumDistribution(progress, "AdditionalNumbers"));
                }
            });
        }

        mainNumbersSumDistributionSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {}

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {
                int progress = sumDistribution.GetMainNumbersPredefinedProgress(mainNumbersSumDistributionSeekBar.getProgress());
                mainNumbersSumDistributionSeekBar.setProgress(progress);
                Toast.makeText(SumDistributionActivity.this, "Sum distribution step size: " + Integer.toString(seekBar.getProgress()), Toast.LENGTH_SHORT).show();
                Print(sumDistribution.GetSumDistribution(progress, "MainNumbers"));
            }
        });
    }

    private void Print(List<String> evenOddStringList)
    {
        ListView listView = (ListView) findViewById(R.id.SumDistributionListView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.custom_listview_lucky_numbers, evenOddStringList);
        listView.setAdapter(adapter);
    }
}
