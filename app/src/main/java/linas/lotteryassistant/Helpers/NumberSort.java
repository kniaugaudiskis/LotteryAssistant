package linas.lotteryassistant.Helpers;

//Created by Linas on 2016-10-09.

import java.util.List;
import linas.lotteryassistant.Components.EvenOddOrHighLowNumbersSection;
import linas.lotteryassistant.Components.LotteryNumber;
import linas.lotteryassistant.Components.SumDistributionSection;

public class NumberSort
{
    public static List<Integer> SortIntegerList(List<Integer> integerList, String sortType)
    {
        int tempIndex1 = 0;
        int tempIndex2 = 1;
        int nonChangeCounter = 0;

        if (integerList.size() > 1)
        {
            while (true)
            {
                if (sortType.equals("Asc"))
                {
                    if (integerList.get(tempIndex1) > integerList.get(tempIndex2))
                    {
                        int temp1LotteryNumber = integerList.get(tempIndex1);
                        int temp2LotteryNumber = integerList.get(tempIndex2);

                        integerList.set(tempIndex1, temp2LotteryNumber);
                        integerList.set(tempIndex2, temp1LotteryNumber);

                        nonChangeCounter = 0;
                    } else
                    {
                        nonChangeCounter++;
                    }
                } else if (sortType.equals("Desc"))
                {
                    if (integerList.get(tempIndex1) < integerList.get(tempIndex2))
                    {
                        int temp1LotteryNumber = integerList.get(tempIndex1);
                        int temp2LotteryNumber = integerList.get(tempIndex2);

                        integerList.set(tempIndex1, temp2LotteryNumber);
                        integerList.set(tempIndex2, temp1LotteryNumber);

                        nonChangeCounter = 0;
                    } else
                    {
                        nonChangeCounter++;
                    }
                }

                if (nonChangeCounter == integerList.size())
                {
                    break;
                }

                tempIndex1++;
                tempIndex2++;

                if (tempIndex2 == integerList.size())
                {
                    tempIndex1 = 0;
                    tempIndex2 = 1;
                }
            }
        }

        return integerList;
    }

    public static List<LotteryNumber> SortMostPopularNumberList(List<LotteryNumber> mostPopularNumberList)
    {
        int tempIndex1 = 0;
        int tempIndex2 = 1;
        int nonChangeCounter = 0;

        while (true)
        {
            if (mostPopularNumberList.get(tempIndex1).GetHitCount() < mostPopularNumberList.get(tempIndex2).GetHitCount())
            {
                LotteryNumber temp1LotteryNumber = mostPopularNumberList.get(tempIndex1);
                LotteryNumber temp2LotteryNumber = mostPopularNumberList.get(tempIndex2);

                mostPopularNumberList.set(tempIndex1, temp2LotteryNumber);
                mostPopularNumberList.set(tempIndex2, temp1LotteryNumber);

                nonChangeCounter = 0;
            }
            else
            {
                nonChangeCounter++;
            }

            if (nonChangeCounter == mostPopularNumberList.size())
            {
                break;
            }

            tempIndex1++;
            tempIndex2++;

            if (tempIndex2 == mostPopularNumberList.size())
            {
                tempIndex1 = 0;
                tempIndex2 = 1;
            }
        }

        return mostPopularNumberList;
    }

    //
    public static List<EvenOddOrHighLowNumbersSection> SortHighLowOrEvenOddNumberList(List<EvenOddOrHighLowNumbersSection> highLowEvenOddNumberList)
    {
        int tempIndex1 = 0;
        int tempIndex2 = 1;
        int nonChangeCounter = 0;

        while (true)
        {
            if (highLowEvenOddNumberList.get(tempIndex1).GetHitCount() < highLowEvenOddNumberList.get(tempIndex2).GetHitCount())
            {
                EvenOddOrHighLowNumbersSection temp1LotteryNumber = highLowEvenOddNumberList.get(tempIndex1);
                EvenOddOrHighLowNumbersSection temp2LotteryNumber = highLowEvenOddNumberList.get(tempIndex2);

                highLowEvenOddNumberList.set(tempIndex1, temp2LotteryNumber);
                highLowEvenOddNumberList.set(tempIndex2, temp1LotteryNumber);

                nonChangeCounter = 0;
            }
            else
            {
                nonChangeCounter++;
            }

            if (nonChangeCounter == highLowEvenOddNumberList.size())
            {
                break;
            }

            tempIndex1++;
            tempIndex2++;

            if (tempIndex2 == highLowEvenOddNumberList.size())
            {
                tempIndex1 = 0;
                tempIndex2 = 1;
            }
        }

        return highLowEvenOddNumberList;
    }

    //
    public static List<SumDistributionSection> SortNumberSumDistributionList(List<SumDistributionSection> numberSumDistributionList)
    {
        int tempIndex1 = 0;
        int tempIndex2 = 1;
        int nonChangeCounter = 0;

        while (true)
        {
            if (numberSumDistributionList.get(tempIndex1).GetHitCount() < numberSumDistributionList.get(tempIndex2).GetHitCount())
            {
                SumDistributionSection temp1LotteryNumber = numberSumDistributionList.get(tempIndex1);
                SumDistributionSection temp2LotteryNumber = numberSumDistributionList.get(tempIndex2);

                numberSumDistributionList.set(tempIndex1, temp2LotteryNumber);
                numberSumDistributionList.set(tempIndex2, temp1LotteryNumber);

                nonChangeCounter = 0;
            }
            else
            {
                nonChangeCounter++;
            }

            if (nonChangeCounter == numberSumDistributionList.size())
            {
                break;
            }

            tempIndex1++;
            tempIndex2++;

            if (tempIndex2 == numberSumDistributionList.size())
            {
                tempIndex1 = 0;
                tempIndex2 = 1;
            }
        }

        return numberSumDistributionList;
    }
}
