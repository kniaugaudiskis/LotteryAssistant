package linas.lotteryassistant.Components;

//Created by Linas on 2016-10-07.
//Used for most popular numbers determination.

public class LotteryNumber
{
    private int number;
    private double percentage;
    private double hitCount = 0;

    public LotteryNumber(int number) { this.number = number; }

    public void IncreaseHitCount() { this.hitCount++; }

    public void SetPercentage(double percentage) { this.percentage = percentage; }

    public int GetNumber() { return this.number; }

    public double GetHitCount() { return this.hitCount; }

    public double GetPercentage() { return this.percentage; }
}
