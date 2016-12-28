package linas.lotteryassistant.Activities.ApplicationEntryActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import linas.lotteryassistant.Config.AppConfig;
import linas.lotteryassistant.R;
import linas.lotteryassistant.Repositories.GamesRepository;

public class ApplicationEntryActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_entry);
        AppConfig.InitializeNumberProperties();
        ReadDataFile();
    }

    private void ReadDataFile()
    {
        Thread getFileThread = new Thread(new Runnable()
        {
            public void run()
            {
                try
                {
                    android.os.StrictMode.ThreadPolicy policy = new android.os.StrictMode.ThreadPolicy.Builder().permitAll().build();
                    android.os.StrictMode.setThreadPolicy(policy);

                    String url = "";
                    if (AppConfig.gameName.equals("EuroJackpot"))
                    {
                        url = AppConfig.euroJackpotDbUrl;
                    }
                    else if (AppConfig.gameName.equals("VikingLotto"))
                    {
                        url = AppConfig.vikingLottoDbUrl;
                    }
                    URL dbUrl = new URL(url);
                    //make a request to server
                    HttpURLConnection con = (HttpURLConnection) dbUrl.openConnection();
                    InputStream is = con.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    int currentLine = 0;
                    String textLine;

                    while ((textLine = br.readLine()) != null)
                    {
                        if (currentLine == 0)
                        {
                            GamesRepository.dbDate = textLine; //the first line is always a db version date
                        } else
                        {
                            GamesRepository.ProcessAndAddGame(textLine);
                        }
                        currentLine++;
                    }
                    br.close();
                }
                catch (Exception exp) { }
            }
        });
        getFileThread.start();

        try
        {
            getFileThread.join(); //waiting for the getFileThread to finish its job and join the main thread.
        }
        catch(Exception exp) {}
    }

    //Button controls
    public void Analyzer_Click(View v)
    {
        Intent intent = new Intent("_AnalyzerActivity");
        startActivity(intent);
    }

    public void Generator_Click(View v)
    {
        Intent intent = new Intent("_GeneratorActivity");
        startActivity(intent);
    }

    public void About_Click(View v)
    {

    }

    public void Quit_Click(View v)
    {

    }
}
