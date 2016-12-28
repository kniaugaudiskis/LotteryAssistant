package linas.lotteryassistant.Core.Analyzer;

//Created by Linas on 2016-10-11.

import java.util.ArrayList;
import java.util.List;
import linas.lotteryassistant.Components.LotteryNumber;
import linas.lotteryassistant.Config.AppConfig;
import linas.lotteryassistant.Repositories.GamesRepository;

public class SittingOutNumbers
{
    private List<LotteryNumber> sittingOutNumbersList = new ArrayList<>();

    public List<String> GetSittingOutNumbers(int progress, String numberType)
    {
        List<String> sittingOutNumbersResultList = new ArrayList<>();

        sittingOutNumbersList = GenerateSittingOutNumbers(progress, numberType);

        if (sittingOutNumbersList.size() != 0)
        {
            for (int i = 0; i < sittingOutNumbersList.size(); i++)
            {
                sittingOutNumbersResultList.add("Number: " + sittingOutNumbersList.get(i).GetNumber());

            }
        }
        else
        {
            sittingOutNumbersResultList.add("There are no sitting out numbers");
        }

        return sittingOutNumbersResultList;
    }

    public List<LotteryNumber> GenerateSittingOutNumbers(int progress, String numberType)
    {
        ClearSittingOutNumbersList();

        InitializeSittingOutNumbersList(numberType);

        int oldestGameIndexToInclude = GamesRepository.gamesList.size() - 1 - progress;

        if (numberType.equals("MainNumbers"))
        {
            for (int i = GamesRepository.gamesList.size() - 1; i >= oldestGameIndexToInclude; i--)
            {
                List<Integer> tempCombination = GamesRepository.GetGameNumbersByGameId(i, "MainNumbers");
                for (int j = 0; j < tempCombination.size(); j++)
                {
                    sittingOutNumbersList = RemoveNumber(sittingOutNumbersList, tempCombination.get(j));
                }
            }
        }
        else if (numberType.equals("AdditionalNumbers"))
        {
            for (int i = GamesRepository.gamesList.size() - 1; i >= oldestGameIndexToInclude; i--)
            {
                List<Integer> tempCombination = GamesRepository.GetGameNumbersByGameId(i, "AdditionalNumbers");
                for (int j = 0; j < tempCombination.size(); j++)
                {
                    sittingOutNumbersList = RemoveNumber(sittingOutNumbersList, tempCombination.get(j));
                }
            }
        }

        return sittingOutNumbersList;
    }

    private void InitializeSittingOutNumbersList(String numberType)
    {
        if (numberType.equals("MainNumbers"))
        {
            for (int i = 1; i <= AppConfig.maxMainNumber; i++)
            {
                sittingOutNumbersList.add(new LotteryNumber(i));
            }
        }
        else if (numberType.equals("AdditionalNumbers"))
        {
            for (int i = 1; i <= AppConfig.maxAdditionalNumber; i++)
            {
                sittingOutNumbersList.add(new LotteryNumber(i));
            }
        }
    }

    private List<LotteryNumber> RemoveNumber(List<LotteryNumber> lotteryNumbersList, int lotteryNumberToRemove)
    {
        for (int i = 0; i < lotteryNumbersList.size(); i++)
        {
            if (lotteryNumbersList.get(i).GetNumber() == lotteryNumberToRemove)
            {
                lotteryNumbersList.remove(i);
                break;
            }
        }

        return lotteryNumbersList;
    }

    private void ClearSittingOutNumbersList() { sittingOutNumbersList.clear(); }
}
