package linas.lotteryassistant.Repositories;

import java.util.ArrayList;
import java.util.List;

import linas.lotteryassistant.Components.GameEntry;

//Created by Linas on 2016-08-15.

public class GamesRepository
{
    public static  String dbDate = "";
    public static List<GameEntry> gamesList = new ArrayList<>();

    public static void ProcessAndAddGame(String gameEntry)
    {
        List<Integer> mainNumbersList = new ArrayList<>();
        List<Integer> additionalNumbersList = new ArrayList<>();
        String gameDate = "";
        Integer goldenNumber = 0;

        String[] gameEntryArray = gameEntry.split("\\s+");
        for (int i = 0; i < gameEntryArray.length; i++)
        {
            if (i == 0)
            {
                gameDate = gameEntryArray[0];
            }
            else if (i == 1)
            {
                String[] mainNumbersArray = gameEntryArray[1].split("-");
                for (int j = 0; j < mainNumbersArray.length; j++)
                {
                    mainNumbersList.add(Integer.parseInt(mainNumbersArray[j]));
                }
            }
            else if (i == 2)
            {
                String[] additionalNumbersArray = gameEntryArray[2].split("-");
                for (int j = 0; j < additionalNumbersArray.length; j++)
                {
                    additionalNumbersList.add(Integer.parseInt(additionalNumbersArray[j]));
                }
            }
            else if (i == 3)
            {
                goldenNumber = Integer.parseInt(gameEntryArray[3]);
            }
        }

        gamesList.add(new GameEntry(gameDate, mainNumbersList, additionalNumbersList, goldenNumber));
    }

    public static List<Integer> GetGameNumbersByGameId(int gameId, String numberType)
    {
        if (numberType.equals("MainNumbers"))
        {
            return gamesList.get(gameId).mainNumbersIntList;
        }
        else if (numberType.equals("AdditionalNumbers"))
        {
            return gamesList.get(gameId).additionalNumbersIntList;
        }

        return null;
    }

    public static Integer GetGoldenNumberByGameId(int gameId)
    {
        return gamesList.get(gameId).goldenNumber;
    }
}
//2004-08-18  03-07-14-18-22-24  05-41  39 - "VikingLotto"
//2012-03-23 05-08-21-37-46 6-8 - EuroJackpot
