package linas.lotteryassistant.Activities.AnalyzerActivities;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import linas.lotteryassistant.Config.AppConfig;
import linas.lotteryassistant.Core.Analyzer.LuckyNumbersViewer;
import linas.lotteryassistant.R;

public class LuckyNumbersViewerActivity extends AppCompatActivity
{
    public static final String TAG = "eueu";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lucky_number_viewer);
        LuckyNumbersViewer luckyNumbersViewer = new LuckyNumbersViewer();
        Print(luckyNumbersViewer.ParseGamesRepositoryToStringList());
    }

    public void Print(List<String> luckyNumbersStringList)
    {
        ListView listView = (ListView) findViewById(R.id.LuckyNumbersListView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.custom_listview_lucky_numbers, luckyNumbersStringList);
        listView.setAdapter(adapter);
    }

    public void Info_Click(View v)
    {
        String infoMessage = "The section displays the lucky numbers history in the following format: \n \n";
        if (AppConfig.gameName.equals("EuroJackpot"))
        {
            infoMessage += "<Game Date>  <Main Numbers>  <Additional Numbers> \n";
        }
        else if (AppConfig.gameName.equals("VikingLotto"))
        {
            infoMessage += "<Game Date>  <Main Numbers>  <Additional Numbers>  <Golden Number> \n";
        }

        AlertDialog.Builder aBuilder = new AlertDialog.Builder(LuckyNumbersViewerActivity.this);
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
}
