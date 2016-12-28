package linas.lotteryassistant.Core.Analyzer;

//Created by Linas on 2016-11-19.

import java.util.List;
import linas.lotteryassistant.Repositories.GamesRepository;

public class RepeatHitNumbers
{
    public double GetDirectHitNumberProbability(String numberType)
    {
        double directHitNumbersCount = 0;

        for (int i = GamesRepository.gamesList.size() - 1; i > 0; i--)
        {
            if (numberType.equals("MainNumbers"))
            {
                List<Integer> currentCombination = GamesRepository.GetGameNumbersByGameId(i, "MainNumbers");
                List<Integer> previousCombination = GamesRepository.GetGameNumbersByGameId(i - 1, "MainNumbers");

                if (IsMatch(currentCombination, previousCombination))
                {
                    directHitNumbersCount++;
                }

            }
            else if (numberType.equals("AdditionalNumbers"))
            {
                List<Integer> currentCombination = GamesRepository.GetGameNumbersByGameId(i, "AdditionalNumbers");
                List<Integer> previousCombination = GamesRepository.GetGameNumbersByGameId(i - 1, "AdditionalNumbers");

                if (IsMatch(currentCombination, previousCombination))
                {
                    directHitNumbersCount++;
                }
            }
        }

        return (directHitNumbersCount * 100 / (double)(GamesRepository.gamesList.size()));
    }

    private boolean IsMatch(List<Integer> currentCombination, List<Integer> previousCombination)
    {
        for (int i = 0; i < currentCombination.size(); i++)
        {
            for (int j = 0; j < previousCombination.size(); j++)
            {
                if (currentCombination.get(i) == previousCombination.get(j))
                {
                    return true;
                }
            }
        }

        return false;
    }
}
