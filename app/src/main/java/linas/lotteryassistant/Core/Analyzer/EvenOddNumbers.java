package linas.lotteryassistant.Core.Analyzer;

//Created by Linas on 2016-09-24.

import java.util.ArrayList;
import java.util.List;
import linas.lotteryassistant.Components.EvenOddOrHighLowNumbersSection;
import linas.lotteryassistant.Helpers.NumberFormat;
import linas.lotteryassistant.Config.AppConfig;
import linas.lotteryassistant.Repositories.GamesRepository;

public class EvenOddNumbers
{
    private List<EvenOddOrHighLowNumbersSection> evenOddNumbersSectionList;

    public List<String> GetEvenOddNumbersDistribution(String numberType)
    {
        List<String> resultStringList = new ArrayList<>();

        evenOddNumbersSectionList = GenerateEvenOddNumbersDistribution(numberType);

        if (numberType.equals("MainNumbers"))
        {
            resultStringList.add("Main Numbers:");
        }
        else if (numberType.equals("AdditionalNumbers"))
        {
            resultStringList.add("Additional Numbers:");
        }

        for (int i = 0; i < evenOddNumbersSectionList.size(); i++)
        {
            resultStringList.add(evenOddNumbersSectionList.get(i).GetEvenOrHighCount() + " even / " + evenOddNumbersSectionList.get(i).GetOddOrLowCount() + " odd numbers:  " +
                    NumberFormat.DoubleOneDecimalPlace(evenOddNumbersSectionList.get(i).GetPercentage()) + "%  (" + NumberFormat.DoubleNoDecimalPlaces(evenOddNumbersSectionList.get(i).GetHitCount()) + " hits)");
        }

        return resultStringList;
    }

    public List<EvenOddOrHighLowNumbersSection> GenerateEvenOddNumbersDistribution(String numberType)
    {
        InitializeEvenOddList(numberType);

        for (int i = 0; i < GamesRepository.gamesList.size(); i++)
        {
            int evenCount = 0, oddCount = 0;
            if (numberType.equals("MainNumbers"))
            {
                List<Integer> mainNumbersList = GamesRepository.GetGameNumbersByGameId(i, "MainNumbers");
                for (int j = 0; j < mainNumbersList.size(); j++)
                {
                    if (mainNumbersList.get(j) % 2 == 0)
                    {
                        evenCount++;
                    }
                    else
                    {
                        oddCount++;
                    }
                }
            }
            else if (numberType.equals("AdditionalNumbers"))
            {
                List<Integer> additionalNumbersList = GamesRepository.GetGameNumbersByGameId(i, "AdditionalNumbers");
                for (int j = 0; j < additionalNumbersList.size(); j++)
                {
                    if (additionalNumbersList.get(j) % 2 == 0)
                    {
                        evenCount++;
                    }
                    else
                    {
                        oddCount++;
                    }
                }
            }
            UpdateEvenOddNumberStats(evenCount, oddCount);
        }

        ComputeOccurrencePercentage();

        return evenOddNumbersSectionList;
    }

    private void UpdateEvenOddNumberStats(int evenCount, int oddCount)
    {
        for (int i = 0; i < evenOddNumbersSectionList.size(); i++)
        {
            if ((evenOddNumbersSectionList.get(i).GetEvenOrHighCount() == evenCount) && (evenOddNumbersSectionList.get(i).GetOddOrLowCount() == oddCount))
            {
                evenOddNumbersSectionList.get(i).IncreaseHitCount();
                return;
            }
        }
    }

    private void ComputeOccurrencePercentage()
    {
        double gameCount = GamesRepository.gamesList.size();

        for (int i = 0; i < evenOddNumbersSectionList.size(); i++)
        {
            double hitCount = evenOddNumbersSectionList.get(i).GetHitCount();
            double percentage = hitCount * 100 / gameCount;
            evenOddNumbersSectionList.get(i).SetPercentage(percentage);
        }
    }

    private void InitializeEvenOddList(String numberType)
    {
        evenOddNumbersSectionList = new ArrayList<>();

        if (AppConfig.gameName.equals("EuroJackpot"))
        {
            if (numberType.equals("MainNumbers"))
            {
                evenOddNumbersSectionList.add(new EvenOddOrHighLowNumbersSection(5, 0));
                evenOddNumbersSectionList.add(new EvenOddOrHighLowNumbersSection(4, 1));
                evenOddNumbersSectionList.add(new EvenOddOrHighLowNumbersSection(3, 2));
                evenOddNumbersSectionList.add(new EvenOddOrHighLowNumbersSection(2, 3));
                evenOddNumbersSectionList.add(new EvenOddOrHighLowNumbersSection(1, 4));
                evenOddNumbersSectionList.add(new EvenOddOrHighLowNumbersSection(0, 5));
            }
            else if (numberType.equals("AdditionalNumbers"))
            {
                evenOddNumbersSectionList.add(new EvenOddOrHighLowNumbersSection(2, 0));
                evenOddNumbersSectionList.add(new EvenOddOrHighLowNumbersSection(1, 1));
                evenOddNumbersSectionList.add(new EvenOddOrHighLowNumbersSection(0, 2));
            }
        }
        else if (AppConfig.gameName.equals("VikingLotto"))
        {
            evenOddNumbersSectionList.add(new EvenOddOrHighLowNumbersSection(6, 0));
            evenOddNumbersSectionList.add(new EvenOddOrHighLowNumbersSection(5, 1));
            evenOddNumbersSectionList.add(new EvenOddOrHighLowNumbersSection(4, 2));
            evenOddNumbersSectionList.add(new EvenOddOrHighLowNumbersSection(3, 3));
            evenOddNumbersSectionList.add(new EvenOddOrHighLowNumbersSection(2, 4));
            evenOddNumbersSectionList.add(new EvenOddOrHighLowNumbersSection(1, 5));
            evenOddNumbersSectionList.add(new EvenOddOrHighLowNumbersSection(0, 6));
        }
    }

    public void ClearEvenOddNumbersSectionList()
    {
        this.evenOddNumbersSectionList.clear();
    }
}
