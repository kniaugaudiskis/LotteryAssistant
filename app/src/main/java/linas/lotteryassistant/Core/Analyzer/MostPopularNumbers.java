package linas.lotteryassistant.Core.Analyzer;

//Created by Linas on 2016-10-04.

import java.util.ArrayList;
import java.util.List;
import linas.lotteryassistant.Components.LotteryNumber;
import linas.lotteryassistant.Helpers.NumberFormat;
import linas.lotteryassistant.Config.AppConfig;
import linas.lotteryassistant.Helpers.NumberSort;
import linas.lotteryassistant.Repositories.GamesRepository;

public class MostPopularNumbers
{
    private List<LotteryNumber> mostPopularNumbersList = new ArrayList<>();

    public List<String> GetMostPopularNumbers(String numberType)
    {
        List<String> mostPopularNumbers = new ArrayList<>();

        mostPopularNumbersList = GenerateMostPopularNumbers(numberType);

        if (numberType.equals("MainNumbers"))
        {
            mostPopularNumbers.add("Main Numbers:");
        }
        else if (numberType.equals("AdditionalNumbers"))
        {
            mostPopularNumbers.add("Additional Numbers:");
        }

        for (int i = 0; i < mostPopularNumbersList.size(); i++)
        {
            mostPopularNumbers.add("Number: " + mostPopularNumbersList.get(i).GetNumber() + "   " + NumberFormat.DoubleOneDecimalPlace(mostPopularNumbersList.get(i).GetPercentage()) + "%   (" +
                                    NumberFormat.DoubleNoDecimalPlaces(mostPopularNumbersList.get(i).GetHitCount()) + " hits)");
        }

        return mostPopularNumbers;
    }

    public List<LotteryNumber> GenerateMostPopularNumbers(String numberType)
    {
        ClearMostPopularNumbersList();

        InitializeLotteryNumbersList(numberType);

        if (numberType.equals("MainNumbers"))
        {
            for (int i = 0; i < GamesRepository.gamesList.size(); i++)
            {
                List<Integer> tempList = GamesRepository.GetGameNumbersByGameId(i, "MainNumbers");

                for (int j = 0; j < tempList.size(); j++)
                {
                    UpdateMostPopularNumberStats(tempList.get(j));
                }
            }
        }
        else if (numberType.equals("AdditionalNumbers"))
        {
            for (int i = 0; i < GamesRepository.gamesList.size(); i++)
            {
                List<Integer> tempList = GamesRepository.GetGameNumbersByGameId(i, "AdditionalNumbers");

                for (int j = 0; j < tempList.size(); j++)
                {
                    UpdateMostPopularNumberStats(tempList.get(j));
                }
            }
        }

        ComputeOccurrencePercentage(numberType);
        mostPopularNumbersList = NumberSort.SortMostPopularNumberList(mostPopularNumbersList);

        return mostPopularNumbersList;
    }

    private void InitializeLotteryNumbersList(String numberType)
    {
        if (AppConfig.gameName.equals("EuroJackpot"))
        {
            if (numberType.equals("MainNumbers"))
            {
                for (int i = AppConfig.minMainNumber; i <= AppConfig.maxMainNumber; i++)
                {
                    mostPopularNumbersList.add(new LotteryNumber(i));
                }
            }
            else if (numberType.equals("AdditionalNumbers"))
            {
                for (int i = AppConfig.minAdditionalNumber; i <= AppConfig.maxAdditionalNumber; i++)
                {
                    mostPopularNumbersList.add(new LotteryNumber(i));
                }
            }
        }
        else if (AppConfig.gameName.equals("VikingLotto"))
        {
            if (numberType.equals("MainNumbers"))
            {
                for (int i = AppConfig.minMainNumber; i <= AppConfig.maxMainNumber; i++)
                {
                    mostPopularNumbersList.add(new LotteryNumber(i));
                }
            }
        }
    }

    private void UpdateMostPopularNumberStats(int lotteryNumber)
    {
        for (int i = 0; i < mostPopularNumbersList.size(); i++)
        {
            if (mostPopularNumbersList.get(i).GetNumber() == lotteryNumber)
            {
                mostPopularNumbersList.get(i).IncreaseHitCount();
                break;
            }
        }
    }

    private void ComputeOccurrencePercentage(String numberType)
    {
        double totalLotteryNumberCount = 0;

        if (numberType.equals("MainNumbers"))
        {
            totalLotteryNumberCount = GamesRepository.gamesList.size() * AppConfig.mainNumbersCombinationLength;
        }
        else if (numberType.equals("AdditionalNumbers"))
        {
            totalLotteryNumberCount = GamesRepository.gamesList.size() * AppConfig.additionalNumbersCombinationLength;
        }

        for (int i = 0; i < mostPopularNumbersList.size(); i++)
        {
            double tempPercentage = mostPopularNumbersList.get(i).GetHitCount() * 100 / totalLotteryNumberCount;
            mostPopularNumbersList.get(i).SetPercentage(tempPercentage);
        }
    }

    public void ClearMostPopularNumbersList()
    {
        mostPopularNumbersList.clear();
    }
}
