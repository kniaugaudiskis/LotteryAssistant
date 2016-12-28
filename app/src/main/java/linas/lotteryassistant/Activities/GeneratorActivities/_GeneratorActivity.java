package linas.lotteryassistant.Activities.GeneratorActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import linas.lotteryassistant.R;

public class _GeneratorActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottery_numbers_generator);
    }

    public void RandomNumbersGenerator_Click(View v)
    {
        Intent intent = new Intent("RandomNumbersGeneratorActivity");
        startActivity(intent);
    }

    public void AdvancedNumbersGenerator_Click(View v)
    {
        Intent intent = new Intent("AdvancedNumbersGeneratorActivity");
        startActivity(intent);
    }
}
