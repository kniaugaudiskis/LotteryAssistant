package linas.lotteryassistant.Components;

//Created by Linas on 2016-10-24.

import java.util.ArrayList;
import java.util.List;

public class GeneratedCombination
{
    private List<Integer> mainNumbersList = new ArrayList<>();
    private List<Integer> additionalNumbersList = new ArrayList<>();

    public void AddMainNumberCombination(List<Integer> mainNumbersList)
    {
        for (Integer mainCombinationNumber : mainNumbersList)
        {
            this.mainNumbersList.add(mainCombinationNumber);
        }
    }

    public void AddAdditionalNumberCombination(List<Integer> additionalNumbersList)
    {
        for (Integer additionalCombinationNumber : additionalNumbersList)
        {
            this.additionalNumbersList.add(additionalCombinationNumber);
        }
    }

    public List<Integer> GetMainNumbers()
    {
        return this.mainNumbersList;
    }

    public List<Integer> GetAdditionalNumbers()
    {
        return this.additionalNumbersList;
    }
}
