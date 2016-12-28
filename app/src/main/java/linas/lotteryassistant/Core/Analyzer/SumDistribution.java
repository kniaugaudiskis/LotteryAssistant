package linas.lotteryassistant.Core.Analyzer;

//Created by Linas on 2016-09-11

import java.util.ArrayList;
import java.util.List;

import linas.lotteryassistant.Helpers.NumberFormat;
import linas.lotteryassistant.Components.SumDistributionSection;
import linas.lotteryassistant.Config.AppConfig;
import linas.lotteryassistant.Repositories.GamesRepository;

public class SumDistribution
{
    private List<SumDistributionSection> sumDistributionSectionList;

    public List<String> GetSumDistribution(int progress, String numberType)
    {
        List<String> resultStringList = new ArrayList<>();

        sumDistributionSectionList = GenerateSumDistribution(progress, numberType);

        if (numberType.equals("MainNumbers"))
        {
            resultStringList.add("Main numbers");
        }
        else if (numberType.equals("AdditionalNumbers"))
        {
            resultStringList.add("Additional numbers");
        }

        for (int i = 0; i < sumDistributionSectionList.size(); i++)
        {
            resultStringList.add("(" + sumDistributionSectionList.get(i).GetLowerExcludingBound() + "-" + sumDistributionSectionList.get(i).GetHigherIncludingBound() + "]  " + NumberFormat.DoubleOneDecimalPlace(sumDistributionSectionList.get(i).GetPercentage()) + "%   ("+ NumberFormat.DoubleNoDecimalPlaces(sumDistributionSectionList.get(i).GetHitCount()) + " hits)");
        }

        return resultStringList;
    }

    public List<SumDistributionSection> GenerateSumDistribution(int progress, String numberType)
    {
        List<Integer> tempIntList;
        InitializeSumDistributionSectionList(progress, numberType);

        for (int i = 0; i < GamesRepository.gamesList.size(); i++)
        {
            int tempSum = 0;
            if (numberType.equals("MainNumbers"))
            {
                tempIntList = GamesRepository.GetGameNumbersByGameId(i, "MainNumbers");
                for (int j = 0; j < tempIntList.size(); j++)
                {
                    tempSum += tempIntList.get(j);
                }
                UpdateNumbersSumDistribution(tempSum);
            }
            else if (numberType.equals("AdditionalNumbers"))
            {
                tempIntList = GamesRepository.GetGameNumbersByGameId(i, "AdditionalNumbers");
                for (int j = 0; j < tempIntList.size(); j++)
                {
                    tempSum += tempIntList.get(j);
                }
                UpdateNumbersSumDistribution(tempSum);
            }
        }

        ComputeOccurrencePercentage();

        return sumDistributionSectionList;
    }

   public int GetMainNumbersPredefinedProgress(int selectedProgress)
   {
       if (AppConfig.gameName.equals("VikingLotto"))
       {
           if       (selectedProgress <= 40) { return 23; }
           else if  ((selectedProgress > 40) && (selectedProgress <= 60)) { return 46; }
           else if  ((selectedProgress > 60) && (selectedProgress <= 80)) { return 69; }
           else if  ((selectedProgress > 80) && (selectedProgress <= 100))  { return 91; }
           else if  (selectedProgress > 100) { return  136; }
       }
       else if (AppConfig.gameName.equals("EuroJackpot"))
       {
           if       (selectedProgress <= 20) { return 20; }
           else if ((selectedProgress > 20) && (selectedProgress <= 30)) { return 30; }
           else if ((selectedProgress > 30) && (selectedProgress <= 40)) { return 40; }
           else if ((selectedProgress > 40) && (selectedProgress <= 60)) { return 60; }
           else if ((selectedProgress > 60) && (selectedProgress <= 80)) { return 80; }
           else if ((selectedProgress > 80) ) { return 120; }
       }
       return 0;
   }

    public int GetAdditionalNumbersPredefinedProgress(int selectedProgress)
    {
        if (AppConfig.gameName.equals("EuroJackpot"))
        {
            if       (selectedProgress <= 4) { return 4; }
            else if  ((selectedProgress > 4) && (selectedProgress <= 7)) { return 7; }
            else if  (selectedProgress > 7)  { return 10; }
        }
        return 0;
    }

    private void UpdateNumbersSumDistribution(int tempSum)
    {
        for (int i = 0; i < sumDistributionSectionList.size(); i++)
        {
            if ((tempSum > sumDistributionSectionList.get(i).GetLowerExcludingBound()) && (tempSum <= sumDistributionSectionList.get(i).GetHigherIncludingBound()))
            {
                sumDistributionSectionList.get(i).IncreaseHitCount();
            }
        }
    }

    private void InitializeSumDistributionSectionList(int progress, String numberType)
    {
        this.sumDistributionSectionList = new ArrayList<>();

        if (numberType.equals("MainNumbers"))
        {
            for (int i = AppConfig.minMainNumbersSum; i < AppConfig.maxMainNumbersSum; i += progress)
            {
                if (i + progress <= AppConfig.maxMainNumbersSum)
                {
                    this.sumDistributionSectionList.add(new SumDistributionSection(i, i + progress));
                }
                else
                {
                    this.sumDistributionSectionList.add(new SumDistributionSection(i, AppConfig.maxMainNumbersSum));
                    break;
                }
            }
        }

        if (numberType.equals("AdditionalNumbers"))
        {
            if (AppConfig.gameName.equals("EuroJackpot"))
            {
                for (int i = AppConfig.minAdditionalNumbersSum; i < AppConfig.maxAdditionalNumbersSum; i += progress)
                {
                    if (i + progress <= AppConfig.maxAdditionalNumbersSum)
                    {
                        this.sumDistributionSectionList.add(new SumDistributionSection(i, i + progress));
                    }
                    else
                    {
                        this.sumDistributionSectionList.add(new SumDistributionSection(i, AppConfig.maxAdditionalNumbersSum));
                        break;
                    }
                }
            }
        }
    }

    private void ComputeOccurrencePercentage()
    {
        double gameCount = GamesRepository.gamesList.size();

        for (int i = 0; i < sumDistributionSectionList.size(); i++)
        {
            double hitCount = sumDistributionSectionList.get(i).GetHitCount();
            double percentage = hitCount * 100 / gameCount;
            sumDistributionSectionList.get(i).SetPercentage(percentage);
        }
    }
}
