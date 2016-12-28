package linas.lotteryassistant.Core.Generator.GeneratorSettings;

//Created by Linas on 2016-11-04.

import java.util.ArrayList;
import java.util.List;
import linas.lotteryassistant.Components.EvenOddOrHighLowNumbersSection;
import linas.lotteryassistant.Components.SumDistributionSection;
import linas.lotteryassistant.Config.AppConfig;
import linas.lotteryassistant.Core.Analyzer.EvenOddNumbers;
import linas.lotteryassistant.Core.Analyzer.HighLowNumbers;
import linas.lotteryassistant.Core.Analyzer.SumDistribution;
import linas.lotteryassistant.Helpers.NumberSort;

public class GeneratorSettings
{
    public static List<Integer> advancedGeneratorMainNumbers = new ArrayList<>();
    public static List<Integer> advancedGeneratorAdditionalNumbers = new ArrayList<>();
    public static List<Integer> randomGeneratorMainNumbers = new ArrayList<>();
    public static List<Integer> randomGeneratorAdditionalNumbers = new ArrayList<>();

    public static List<Integer> mainEvenNumberCountInCombination = new ArrayList<>();
    public static List<Integer> mainHighNumberCountInCombination = new ArrayList<>();
    public static List<Integer> mainNumbersCombinationSumRange = new ArrayList<>();

    public static List<Integer> additionalEvenNumberCountInCombination = new ArrayList<>();
    public static List<Integer> additionalHighNumberCountInCombination = new ArrayList<>();
    public static List<Integer> additionalNumbersCombinationSumRange = new ArrayList<>();

    public static void InitializeRandomNumbersGenerator()
    {
        InitializeLotteryNumbers("RandomGenerator");
    }

    public static void InitializeAdvancedNumbersGeneratorDefaultSettings()
    {
        InitializeLotteryNumbers("AdvancedGenerator");

        HighLowNumbers highLowNumbers = new HighLowNumbers();
        EvenOddNumbers evenOddNumbers = new EvenOddNumbers();
        SumDistribution sumDistribution = new SumDistribution();

        List<EvenOddOrHighLowNumbersSection> mainNumbersHighLowList = NumberSort.SortHighLowOrEvenOddNumberList(highLowNumbers.GenerateHighLowNumbersDistribution("MainNumbers"));
        List<EvenOddOrHighLowNumbersSection> mainNumbersEvenOddList = NumberSort.SortHighLowOrEvenOddNumberList(evenOddNumbers.GenerateEvenOddNumbersDistribution("MainNumbers"));
        List<SumDistributionSection> mainNumbersSumDistributionList = NumberSort.SortNumberSumDistributionList(sumDistribution.GenerateSumDistribution(AppConfig.mainNumbersGeneratorSumDistributionProgress, "MainNumbers"));

        List<EvenOddOrHighLowNumbersSection> additionalNumbersHighLowList = new ArrayList<>();
        List<EvenOddOrHighLowNumbersSection> additionalNumbersEvenOddMainList = new ArrayList<>();
        List<SumDistributionSection> additionalNumbersSumDistributionList = new ArrayList<>();

        if (AppConfig.gameName.equals("EuroJackpot"))
        {
            additionalNumbersHighLowList = NumberSort.SortHighLowOrEvenOddNumberList(highLowNumbers.GenerateHighLowNumbersDistribution("AdditionalNumbers"));
            additionalNumbersEvenOddMainList = NumberSort.SortHighLowOrEvenOddNumberList(evenOddNumbers.GenerateEvenOddNumbersDistribution("AdditionalNumbers"));
            additionalNumbersSumDistributionList = NumberSort.SortNumberSumDistributionList(sumDistribution.GenerateSumDistribution(AppConfig.additionalNumbersGeneratorSumDistributionProgress, "AdditionalNumbers"));
        }

        SetMostPopularValues(mainNumbersHighLowList, mainNumbersEvenOddList, mainNumbersSumDistributionList, additionalNumbersHighLowList, additionalNumbersEvenOddMainList, additionalNumbersSumDistributionList);
    }

    private static void SetMostPopularValues(List<EvenOddOrHighLowNumbersSection> mainNumbersHighLowList, List<EvenOddOrHighLowNumbersSection> mainNumbersEvenOddList, List<SumDistributionSection> mainNumbersSumDistributionList,
                                             List<EvenOddOrHighLowNumbersSection> additionalNumbersHighLowList, List<EvenOddOrHighLowNumbersSection> additionalNumbersEvenOddList,
                                             List<SumDistributionSection> additionalNumbersSumDistributionList)
    {
        int tempMainNumbersMinSumBoundary = mainNumbersSumDistributionList.get(0).GetLowerExcludingBound();
        int tempMainNumbersMaxSumBoundary = mainNumbersSumDistributionList.get(0).GetHigherIncludingBound();

        for (int i = 0; i < AppConfig.defaultGeneratorMostPopularValues; i++)
        {
            mainHighNumberCountInCombination.add(mainNumbersHighLowList.get(i).GetEvenOrHighCount());
            mainEvenNumberCountInCombination.add(mainNumbersEvenOddList.get(i).GetEvenOrHighCount());

            if (tempMainNumbersMinSumBoundary > mainNumbersSumDistributionList.get(i).GetLowerExcludingBound())
            {
                tempMainNumbersMinSumBoundary = mainNumbersSumDistributionList.get(i).GetLowerExcludingBound();
            }

            if (tempMainNumbersMaxSumBoundary < mainNumbersSumDistributionList.get(i).GetHigherIncludingBound())
            {
                tempMainNumbersMaxSumBoundary = mainNumbersSumDistributionList.get(i).GetHigherIncludingBound();
            }
        }

        mainNumbersCombinationSumRange.add(tempMainNumbersMinSumBoundary);
        mainNumbersCombinationSumRange.add(tempMainNumbersMaxSumBoundary);

        if (AppConfig.gameName.equals("EuroJackpot"))
        {
            int tempAdditionalNumbersMinSumBoundary = additionalNumbersSumDistributionList.get(0).GetLowerExcludingBound();
            int tempAdditionalNumbersMaxSumBoundary = additionalNumbersSumDistributionList.get(0).GetHigherIncludingBound();

            for (int i = 0; i < AppConfig.defaultGeneratorMostPopularValues; i++)
            {
                additionalHighNumberCountInCombination.add(additionalNumbersHighLowList.get(i).GetEvenOrHighCount());
                additionalEvenNumberCountInCombination.add(additionalNumbersEvenOddList.get(i).GetEvenOrHighCount());

                if (tempAdditionalNumbersMinSumBoundary > additionalNumbersSumDistributionList.get(i).GetLowerExcludingBound())
                {
                    tempAdditionalNumbersMinSumBoundary = additionalNumbersSumDistributionList.get(i).GetLowerExcludingBound();
                }

                if (tempAdditionalNumbersMaxSumBoundary < additionalNumbersSumDistributionList.get(i).GetHigherIncludingBound())
                {
                    tempAdditionalNumbersMaxSumBoundary = additionalNumbersSumDistributionList.get(i).GetHigherIncludingBound();
                }
            }

            additionalNumbersCombinationSumRange.add(tempAdditionalNumbersMinSumBoundary);
            additionalNumbersCombinationSumRange.add(tempAdditionalNumbersMaxSumBoundary);
        }
    }

    public static void InitializeLotteryNumbers(String generatorType)
    {
        for (int i = AppConfig.minMainNumber; i <= AppConfig.maxMainNumber; i++)
        {
            if (generatorType.equals("RandomGenerator"))
            {
                randomGeneratorMainNumbers.add(i);
            }
            else if (generatorType.equals("AdvancedGenerator"))
            {
                advancedGeneratorMainNumbers.add(i);
            }
        }

        if (AppConfig.gameName.equals("EuroJackpot"))
        {
            for (int i = AppConfig.minAdditionalNumber; i <= AppConfig.maxAdditionalNumber; i++)
            {
                if (generatorType.equals("RandomGenerator"))
                {
                    randomGeneratorAdditionalNumbers.add(i);
                }
                else if (generatorType.equals("AdvancedGenerator"))
                {
                    advancedGeneratorAdditionalNumbers.add(i);
                }
            }
        }
    }

    public static void UpdateMainEvenNumberCount(int evenNumberRatioIndex, String action)
    {
        if (action.equals("Add"))
        {
            mainEvenNumberCountInCombination.add(evenNumberRatioIndex);
        }
        else if (action.equals("Remove"))
        {
            int indexToRemove = 0;

            for (int i = 0; i < mainEvenNumberCountInCombination.size(); i++)
            {
                if (mainEvenNumberCountInCombination.get(i) == evenNumberRatioIndex)
                {
                    indexToRemove = i;
                    break;
                }
            }

            mainEvenNumberCountInCombination.remove(indexToRemove);
        }
    }

    public static void UpdateMainHighNumberCount(int highNumberRatioIndex, String action)
    {
        if (action.equals("Add"))
        {
            mainHighNumberCountInCombination.add(highNumberRatioIndex);
        }
        else if (action.equals("Remove"))
        {
            int indexToRemove = 0;

            for (int i = 0; i < mainHighNumberCountInCombination.size(); i++)
            {
                if (mainHighNumberCountInCombination.get(i) == highNumberRatioIndex)
                {
                    indexToRemove = i;
                    break;
                }
            }

            mainHighNumberCountInCombination.remove(indexToRemove);
        }
    }

    public static void UpdateSumRange(int sumRangeFrom, int sumRangeTo, String numberType)
    {
        if (numberType.equals("MainNumbers"))
        {
            mainNumbersCombinationSumRange.clear();
            mainNumbersCombinationSumRange.add(sumRangeFrom);
            mainNumbersCombinationSumRange.add(sumRangeTo);
        }
        else if (numberType.equals("AdditionalNumbers"))
        {
            additionalNumbersCombinationSumRange.clear();
            additionalNumbersCombinationSumRange.add(sumRangeFrom);
            additionalNumbersCombinationSumRange.add(sumRangeTo);
        }
    }

    public static String GetAdvancedGeneratorSettings()
    {
        String resultString = "";

        if (GeneratorControlSettings.isMainNumbersMostPopularEvenOddRatiosBoxChecked)
        {
            resultString += "Use main numbers even-odd ratios: YES \n";

            mainEvenNumberCountInCombination = NumberSort.SortIntegerList(mainEvenNumberCountInCombination, "Desc");

            for (Integer evenNumbersCount : mainEvenNumberCountInCombination)
            {
                resultString += evenNumbersCount + " even - " + (int)(AppConfig.mainNumbersCombinationLength - evenNumbersCount) + " odd numbers \n";
            }
        }
        else
        {
            resultString += "Main numbers even-odd ratios: NO \n";
        }

        resultString += "\n";

        if (GeneratorControlSettings.isMainNumbersMostPopularHighLowRatiosBoxChecked)
        {
            resultString += "Use main numbers high-low ratios: YES \n";

            mainHighNumberCountInCombination = NumberSort.SortIntegerList(mainHighNumberCountInCombination, "Desc");

            for (Integer highNumbersCount : mainHighNumberCountInCombination)
            {
                resultString += highNumbersCount + " high - " + (int)(AppConfig.mainNumbersCombinationLength - highNumbersCount) + " low numbers \n";
            }
        }
        else
        {
            resultString += "Use main numbers high-low ratios: NO \n";
        }

        resultString += "\n";

        if (GeneratorControlSettings.isMainNumbersSumRangeBoxChecked)
        {
            resultString += "Use main numbers sum range: YES \n";
            resultString += "Sum range: " + mainNumbersCombinationSumRange.get(0) + " - " + mainNumbersCombinationSumRange.get(1);
        }
        else
        {
            resultString += "Use main numbers sum range: NO \n";
        }

        resultString += "\n\n";

        if (AppConfig.gameName.equals("EuroJackpot"))
        {
            if (GeneratorControlSettings.isAdditionalNumbersSumRangeBoxChecked)
            {
                resultString += "Use additional numbers sum range: YES \n";
                resultString += "Sum range: " + additionalNumbersCombinationSumRange.get(0) + " - " + additionalNumbersCombinationSumRange.get(1);
            }
            else
            {
                resultString += "Use additional numbers sum range: NO \n";
            }
        }

        return resultString;
    }
}


