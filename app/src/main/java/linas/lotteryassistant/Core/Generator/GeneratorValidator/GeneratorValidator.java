package linas.lotteryassistant.Core.Generator.GeneratorValidator;

//Created by Linas on 2016-11-20.

import java.util.List;
import linas.lotteryassistant.Config.AppConfig;
import linas.lotteryassistant.Core.Generator.GeneratorSettings.GeneratorControlSettings;
import linas.lotteryassistant.Core.Generator.GeneratorSettings.GeneratorSettings;

public class GeneratorValidator
{
    public static boolean IsCombinationNumbersSumRangeValid(int sumRangeFrom, int sumRangeTo, String numbersType)
    {
        if (numbersType.equals("MainNumbers"))
        {
            if (sumRangeFrom > sumRangeTo || AppConfig.minMainNumbersSum > sumRangeFrom || AppConfig.maxMainNumbersSum < sumRangeTo)
            {
                return false;
            }
        }
        else if (numbersType.equals("AdditionalNumbers"))
        {
            if (sumRangeFrom > sumRangeTo || sumRangeFrom < AppConfig.minAdditionalNumbersSum  || sumRangeTo > AppConfig.maxAdditionalNumbersSum)
            {
                return false;
            }
        }

        return true;
    }

    public static boolean IsGeneratedCombinationValid(List<Integer> generatedCombination, String numberType)
    {
        int highNumberCount = 0;
        int evenNumberCount = 0;
        int mainNumberCombinationSum = 0;
        int additionalNumberCombinationSum = 0;

        if (numberType.equals("MainNumbers"))
        {
            for (Integer mainCombinationNumber : generatedCombination)
            {
                mainNumberCombinationSum += mainCombinationNumber;

                if (mainCombinationNumber % 2 == 0)
                {
                    evenNumberCount++;
                }

                if (mainCombinationNumber >= AppConfig.midMainNumber)
                {
                    highNumberCount++;
                }
            }

            if (GeneratorControlSettings.isMainNumbersMostPopularEvenOddRatiosBoxChecked)
            {
                if (!GeneratorSettings.mainEvenNumberCountInCombination.contains(evenNumberCount))
                {
                    return false;
                }
            }

            if (GeneratorControlSettings.isMainNumbersMostPopularHighLowRatiosBoxChecked)
            {
                if (!GeneratorSettings.mainHighNumberCountInCombination.contains(highNumberCount))
                {
                    return false;
                }
            }

            if (GeneratorControlSettings.isMainNumbersSumRangeBoxChecked)
            {
                if (mainNumberCombinationSum < GeneratorSettings.mainNumbersCombinationSumRange.get(0) || mainNumberCombinationSum > GeneratorSettings.mainNumbersCombinationSumRange.get(1))

                {
                    return false;
                }
            }
        }
        else if (numberType.equals("AdditionalNumbers"))
        {
            for (Integer additionalCombinationNumber : generatedCombination)
            {
                additionalNumberCombinationSum += additionalCombinationNumber;
            }

            if (GeneratorControlSettings.isAdditionalNumbersSumRangeBoxChecked)
            {
                if (additionalNumberCombinationSum < GeneratorSettings.additionalNumbersCombinationSumRange.get(0) || additionalNumberCombinationSum > GeneratorSettings.additionalNumbersCombinationSumRange.get(1))
                {
                    return false;
                }
            }
        }

        return true;
    }
}
