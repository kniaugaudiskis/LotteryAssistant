package linas.lotteryassistant.Activities.AnalyzerActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import linas.lotteryassistant.R;

public class _AnalyzerActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottery_numbers_analyzer);
    }

    public void LuckyNumbersViewer_Click(View v)
    {
        Intent intent = new Intent("LuckyNumbersViewerActivity");
        startActivity(intent);

    }

    public void EvenOddNumbers_Click(View v)
    {
        Intent intent = new Intent("EvenOddNumbersActivity");
        startActivity(intent);
    }

    public void HighLowNumbers_Click(View v)
    {
        Intent intent = new Intent("HighLowNumbersActivity");
        startActivity(intent);
    }

    public void SumDistribution_Click(View v)
    {
        Intent intent = new Intent("SumDistributionActivity");
        startActivity(intent);
    }

    public void MostPopularNumbers_Click(View v)
    {
        Intent intent = new Intent("MostPopularNumbersActivity");
        startActivity(intent);
    }

    public void SittingOutNumbers_Click(View v)
    {
        Intent intent = new Intent("SittingOutNumbersActivity");
        startActivity(intent);
    }
}
