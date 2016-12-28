package linas.lotteryassistant.Core.Analyzer;

//Created by Linas on 2016-09-05.

import java.util.ArrayList;
import java.util.List;
import linas.lotteryassistant.Components.EvenOddOrHighLowNumbersSection;
import linas.lotteryassistant.Helpers.NumberFormat;
import linas.lotteryassistant.Config.AppConfig;
import linas.lotteryassistant.Repositories.GamesRepository;

public class HighLowNumbers
{
    private List<EvenOddOrHighLowNumbersSection> highLowNumbersSectionList;

    public List<String> GetHighLowNumbersDistribution(String numberType)
    {
        List<String> resultStringList = new ArrayList<>();

        highLowNumbersSectionList = GenerateHighLowNumbersDistribution(numberType);

        if (numberType.equals("MainNumbers"))
        {
            resultStringList.add("Main Numbers:");
        }
        else if (numberType.equals("AdditionalNumbers"))
        {
            resultStringList.add("Additional Numbers:");
        }

        for (int i = 0; i < highLowNumbersSectionList.size(); i++)
        {
            resultStringList.add(highLowNumbersSectionList.get(i).GetEvenOrHighCount() + " high / " + highLowNumbersSectionList.get(i).GetOddOrLowCount() + " low numbers:  " +
                    NumberFormat.DoubleOneDecimalPlace(highLowNumbersSectionList.get(i).GetPercentage()) + "%  (" + NumberFormat.DoubleNoDecimalPlaces(highLowNumbersSectionList.get(i).GetHitCount()) + " hits)");
        }

        return resultStringList;
    }

    public List<EvenOddOrHighLowNumbersSection> GenerateHighLowNumbersDistribution(String numberType)
    {
        InitializeHighLowList(numberType);

        for (int i = 0; i < GamesRepository.gamesList.size(); i++)
        {
            int highCount = 0;
            int lowCount = 0;

            if (numberType.equals("MainNumbers"))
            {
                List<Integer> mainNumbersList = GamesRepository.GetGameNumbersByGameId(i, "MainNumbers");
                for (int j = 0; j < mainNumbersList.size(); j++)
                {
                    if (mainNumbersList.get(j) >= AppConfig.midMainNumber)
                    {
                        highCount++;
                    }
                    else
                    {
                        lowCount++;
                    }
                }
            }
            else if (numberType.equals("AdditionalNumbers"))
            {
                List<Integer> additionalNumbersList = GamesRepository.GetGameNumbersByGameId(i, "AdditionalNumbers");
                for (int j = 0; j < additionalNumbersList.size(); j++)
                {
                    if (additionalNumbersList.get(j) >= AppConfig.midAdditionalNumber)
                    {
                        highCount++;
                    }
                    else
                    {
                        lowCount++;
                    }
                }
            }
            UpdateHighLowNumberStats(highCount, lowCount);
        }

        ComputeOccurrencePercentage();

        return highLowNumbersSectionList;
    }

    private void UpdateHighLowNumberStats(int evenCount, int oddCount)
    {
        for (int i = 0; i < highLowNumbersSectionList.size(); i++)
        {
            if ((highLowNumbersSectionList.get(i).GetEvenOrHighCount() == evenCount) && (highLowNumbersSectionList.get(i).GetOddOrLowCount() == oddCount))
            {
                highLowNumbersSectionList.get(i).IncreaseHitCount();
                return;
            }
        }
    }

    private void ComputeOccurrencePercentage()
    {
        double gameCount = GamesRepository.gamesList.size();

        for (int i = 0; i < highLowNumbersSectionList.size(); i++)
        {
            double hitCount = highLowNumbersSectionList.get(i).GetHitCount();
            double percentage = hitCount * 100 / gameCount;
            highLowNumbersSectionList.get(i).SetPercentage(percentage);
        }
    }

    private void InitializeHighLowList(String numberType)
    {
        highLowNumbersSectionList = new ArrayList<>();

        if (AppConfig.gameName.equals("EuroJackpot"))
        {
            if (numberType.equals("MainNumbers"))
            {
                highLowNumbersSectionList.add(new EvenOddOrHighLowNumbersSection(5, 0));
                highLowNumbersSectionList.add(new EvenOddOrHighLowNumbersSection(4, 1));
                highLowNumbersSectionList.add(new EvenOddOrHighLowNumbersSection(3, 2));
                highLowNumbersSectionList.add(new EvenOddOrHighLowNumbersSection(2, 3));
                highLowNumbersSectionList.add(new EvenOddOrHighLowNumbersSection(1, 4));
                highLowNumbersSectionList.add(new EvenOddOrHighLowNumbersSection(0, 5));
            }
            else if (numberType.equals("AdditionalNumbers"))
            {
                highLowNumbersSectionList.add(new EvenOddOrHighLowNumbersSection(2, 0));
                highLowNumbersSectionList.add(new EvenOddOrHighLowNumbersSection(1, 1));
                highLowNumbersSectionList.add(new EvenOddOrHighLowNumbersSection(0, 2));
            }
        }
        else if (AppConfig.gameName.equals("VikingLotto"))
        {
            highLowNumbersSectionList.add(new EvenOddOrHighLowNumbersSection(6, 0));
            highLowNumbersSectionList.add(new EvenOddOrHighLowNumbersSection(5, 1));
            highLowNumbersSectionList.add(new EvenOddOrHighLowNumbersSection(4, 2));
            highLowNumbersSectionList.add(new EvenOddOrHighLowNumbersSection(3, 3));
            highLowNumbersSectionList.add(new EvenOddOrHighLowNumbersSection(2, 4));
            highLowNumbersSectionList.add(new EvenOddOrHighLowNumbersSection(1, 5));
            highLowNumbersSectionList.add(new EvenOddOrHighLowNumbersSection(0, 6));
        }
    }

    public void ClearHighLowNumbersSectionList()
    {
        this.highLowNumbersSectionList.clear();
    }
}
