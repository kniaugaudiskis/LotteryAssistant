package linas.lotteryassistant.Components;

//Created by Linas on 2016-09-11.

public class SumDistributionSection
{
    private int lowerExcludingBound = 0, higherIncludingBound = 0;
    private double hitCount = 0;
    private double percentage = 0;

    public SumDistributionSection(int lowerExcludingBound, int higherIncludingBound)
    {
        this.lowerExcludingBound = lowerExcludingBound;
        this.higherIncludingBound = higherIncludingBound;
    }

    public int GetLowerExcludingBound()
    {
        return this.lowerExcludingBound;
    }

    public int GetHigherIncludingBound()
    {
        return this.higherIncludingBound;
    }

    public void IncreaseHitCount()
    {
        this.hitCount++;
    }

    public double GetHitCount() { return this.hitCount; }

    public void SetPercentage(double percentage) { this.percentage = percentage; }

    public double GetPercentage() { return this.percentage; }
}
