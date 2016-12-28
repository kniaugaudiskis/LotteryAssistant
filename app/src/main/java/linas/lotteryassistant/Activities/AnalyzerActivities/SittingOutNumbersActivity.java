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
import linas.lotteryassistant.Core.Analyzer.SittingOutNumbers;
import linas.lotteryassistant.R;

public class SittingOutNumbersActivity extends AppCompatActivity
{
    private RadioGroup radioGroup;
    private RadioButton mainNumbersRadioButton, additionalNumbersRadioButton;
    private SeekBar seekBar;

    private SittingOutNumbers sittingOutNumbers;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sitting_out_numbers);

        sittingOutNumbers = new SittingOutNumbers();

        radioGroup = (RadioGroup)findViewById(R.id.SittingOutNumbersRadioGroup);
        mainNumbersRadioButton = (RadioButton)findViewById(R.id.SittingOutMainNumbers);
        additionalNumbersRadioButton = (RadioButton)findViewById(R.id.SittingOutAdditionalNumbers);
        seekBar = (SeekBar) findViewById(R.id.SittingOutNumbersSeekBar);

        SetupSeekBar();

        if (AppConfig.gameName.equals("EuroJackpot"))
        {
            SetupRadioButtons();
        }
        else if (AppConfig.gameName.equals("VikingLotto"))
        {
            radioGroup.setVisibility(View.INVISIBLE);
        }

        Print(sittingOutNumbers.GetSittingOutNumbers(AppConfig.defaultSittingOutNumbersProgress, "MainNumbers"));
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
                    Print(sittingOutNumbers.GetSittingOutNumbers(AppConfig.defaultSittingOutNumbersProgress, "MainNumbers"));
                }
                else if (additionalNumbersRadioButton.isChecked())
                {
                    Print(sittingOutNumbers.GetSittingOutNumbers(AppConfig.defaultSittingOutNumbersProgress, "AdditionalNumbers"));
                }
            }
        });
    }

    private void SetupSeekBar()
    {
        seekBar.setMax(AppConfig.maxSittingOutNumbersProgress);
        seekBar.setProgress(AppConfig.defaultSittingOutNumbersProgress);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {}

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {
                int progress = (seekBar.getProgress());

                if (progress == 0)
                {
                    progress = 1;
                    seekBar.setProgress(progress);
                }

                if (progress == 1)
                {
                    Toast.makeText(SittingOutNumbersActivity.this, "Numbers sitting out for: " + Integer.toString(seekBar.getProgress()) + " drawing", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(SittingOutNumbersActivity.this, "Numbers sitting out for: " + Integer.toString(seekBar.getProgress()) + " drawings", Toast.LENGTH_SHORT).show();
                }

                if (mainNumbersRadioButton.isChecked())
                {
                    Print(sittingOutNumbers.GetSittingOutNumbers(progress, "MainNumbers"));
                }
                else if (additionalNumbersRadioButton.isChecked())
                {
                    Print(sittingOutNumbers.GetSittingOutNumbers(progress, "AdditionalNumbers"));
                }
            }
        });
    }

    private void Print(List<String> evenOddStringList)
    {
        ListView listView = (ListView) findViewById(R.id.SittingOutNumbersListView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.custom_listview_lucky_numbers, evenOddStringList);
        listView.setAdapter(adapter);
    }
}
