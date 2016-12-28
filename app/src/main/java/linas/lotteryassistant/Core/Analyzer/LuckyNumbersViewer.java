package linas.lotteryassistant.Core.Analyzer;

//Created by Linas on 2016-08-17.

import java.util.ArrayList;
import java.util.List;

import linas.lotteryassistant.Repositories.GamesRepository;

public class LuckyNumbersViewer
{
    public List<String> ParseGamesRepositoryToStringList()
    {
        List<String> gamesRepositoryStringList = new ArrayList<>();
        for (int i = 0; i < GamesRepository.gamesList.size(); i++)
        {
            String gameDateString = GamesRepository.gamesList.get(i).gameDate;
            String mainNumbersString = "", additionalNumbersString = "";
            String goldenNumberString = VerifyLeadingZeros(GamesRepository.gamesList.get(i).goldenNumber);

            List<Integer> mainNumbers = GamesRepository.gamesList.get(i).mainNumbersIntList;
            mainNumbersString = ParseNumbersListToString(mainNumbers);

            List<Integer> additionalNumbers = GamesRepository.gamesList.get(i).additionalNumbersIntList;
            additionalNumbersString = ParseNumbersListToString(additionalNumbers);

            String gameString = gameDateString + "   " + mainNumbersString + "   " + additionalNumbersString;
            if (!goldenNumberString.equals("00"))
            {
                gameString += "   " + goldenNumberString;
            }
            gamesRepositoryStringList.add(gameString);
        }
        return gamesRepositoryStringList;
    }

    private String ParseNumbersListToString(List<Integer> numbersIntList)
    {
        String numbersString = "";
        for (int i = 0; i < numbersIntList.size(); i++)
        {
            if (i < numbersIntList.size() - 1)
            {
                numbersString += VerifyLeadingZeros(numbersIntList.get(i)) + "-";
            }
            else
            {
                numbersString += VerifyLeadingZeros(numbersIntList.get(i));
            }
        }
        return numbersString;
    }

    private String VerifyLeadingZeros(Integer number)
    {
        if (number < 10)
        {
            return "0" + number.toString();
        }
        return number.toString();
    }
}
