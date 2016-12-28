package linas.lotteryassistant.Components;

//Created by Linas on 2016-09-22.

public class EvenOddOrHighLowNumbersSection
{
    private int evenOrHighCount, oddOrLowCount;
    private double hitCount, percentage;

    public EvenOddOrHighLowNumbersSection(int evenOrHighCount, int oddOrLowCount)
    {
        this.evenOrHighCount = evenOrHighCount;
        this.oddOrLowCount = oddOrLowCount;
    }

    public int GetEvenOrHighCount() { return this.evenOrHighCount; }

    public int GetOddOrLowCount() { return this.oddOrLowCount; }

    public double GetPercentage() { return this.percentage; }

    public void SetPercentage(double percentage) {this.percentage = percentage; }

    public void IncreaseHitCount() { this.hitCount++; }

    public double GetHitCount() { return this.hitCount; }
}
