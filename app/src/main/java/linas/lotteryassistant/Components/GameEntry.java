package linas.lotteryassistant.Components;

import java.util.ArrayList;
import java.util.List;

//Created by Linas on 2016-08-15.

public class GameEntry
{
    public List<Integer> mainNumbersIntList = new ArrayList<>();
    public List<Integer> additionalNumbersIntList = new ArrayList<>();
    public String gameDate = "";
    public Integer goldenNumber = 0;
    public Integer mainNumbersCount = 0;

    public GameEntry(String gameDate, List<Integer> mainNumbersIntList, List<Integer> additionalNumbersIntList, Integer goldenNumber)
    {
        for (int i = 0; i < mainNumbersIntList.size(); i++)
        {
            int number = mainNumbersIntList.get(i);
            this.mainNumbersIntList.add(number);
        }

        for (int i = 0; i < additionalNumbersIntList.size(); i++)
        {
            int number = additionalNumbersIntList.get(i);
            this.additionalNumbersIntList.add(number);
        }

        this.gameDate = gameDate;
        this.goldenNumber = goldenNumber;
        this.mainNumbersCount = this.mainNumbersIntList.size();
    }
}
