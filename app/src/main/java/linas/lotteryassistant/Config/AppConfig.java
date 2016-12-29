package linas.lotteryassistant.Config;

//Created by Linas on 2016-08-15.

public class AppConfig
{
    //Configuration
    //public static String gameName = "VikingLotto";
    public static String gameName = "EuroJackpot";
    public static String euroJackpotDbUrl = "http://linas.16mb.com/lottery_assistant/eurojackpot.txt";
    public static String vikingLottoDbUrl = "http://linas.16mb.com/lottery_assistant/vikings.txt";

    //VikingLotto
    private static int vikingLottoMainNumbersMaxSum = 273;
    private static int vikingLottoMainNumbersMinSum =  21;
    private static int vikingLottoMainNumbersMaxProgress = 136;
    private static int vikingLottoMainNumbersDefaultProgress = 69;
    private static int vikingLottoMainNumberMin = 1;
    private static int vikingLottoMainNumberMax = 48;
    private static int vikingLottoMainNumberMid = 24;
    private static int vikingLottoMainNumbersCombinationLength = 6;
    private static int vikingLottoMainNumbersGeneratorSumDistributionProgress = 46;

    //EuroJackpot
    private static int euroJackpotMainNumbersMaxSum = 240;
    private static int euroJackpotMainNumbersMinSum =  15;
    private static int euroJackpotAdditionalNumbersMaxSum = 19;
    private static int euroJackpotAdditionalNumbersMinSum = 3;
    private static int euroJackpotMainNumbersMaxProgress = 120;
    private static int euroJackpotAdditionalNumbersMaxProgress = 10;
    private static int euroJackpotMainNumbersDefaultProgress = 60;
    private static int euroJackpotAdditionalNumbersDefaultProgress = 4;
    private static int euroJackpotMainNumberMin = 1;
    private static int euroJackpotMainNumberMax = 50;
    private static int euroJackpotAdditionalNumberMin = 1;
    private static int euroJackpotAdditionalNumberMax = 10;
    private static int euroJackpotMainNumberMid = 25;
    private static int euroJackpotAdditionalNumberMid = 5;
    private static int euroJackpotMainNumbersGeneratorSumDistributionProgress = 40;
    private static int euroJackpotAdditionalNumbersGeneratorSumDistributionProgress = 4;
    private static double euroJackpotMainNumbersCombinationLength = 5;
    private static double euroJackpotAdditionalNumbersCombinationLength = 2;

    //Shared settings
    public static int defaultSittingOutNumbersProgress = 2;
    public static int maxSittingOutNumbersProgress = 15;
    public static int defaultGeneratorMostPopularValues = 2; //How many even/odd, high/low, sub distribution most popular values will be taken.
    public static int failedCombinationGenerationToleranceCount = 10;

    //Game-based settings (not shared)
    public static int maxMainNumbersSum;
    public static int minMainNumbersSum;
    public static int maxAdditionalNumbersSum;
    public static int minAdditionalNumbersSum;
    public static int maxProgressMainNumbers;
    public static int maxProgressAdditionalNumbers;
    public static int defaultProgressMainNumbers;
    public static int defaultProgressAdditionalNumbers;
    public static int minMainNumber;
    public static int maxMainNumber;
    public static int minAdditionalNumber;
    public static int maxAdditionalNumber;
    public static int midMainNumber;
    public static int midAdditionalNumber;
    public static int mainNumbersGeneratorSumDistributionProgress;
    public static int additionalNumbersGeneratorSumDistributionProgress;
    public static double mainNumbersCombinationLength;
    public static double additionalNumbersCombinationLength;

    public static void InitializeNumberProperties()
    {
        if (gameName.equals("VikingLotto"))
        {
            //Sum distribution
            maxMainNumbersSum = vikingLottoMainNumbersMaxSum;
            minMainNumbersSum = vikingLottoMainNumbersMinSum;
            maxProgressMainNumbers = vikingLottoMainNumbersMaxProgress;
            defaultProgressMainNumbers = vikingLottoMainNumbersDefaultProgress;

            //High/Low numbers
            midMainNumber = vikingLottoMainNumberMid;

            //Most popular numbers
            minMainNumber = vikingLottoMainNumberMin;
            maxMainNumber = vikingLottoMainNumberMax;

            //Combination length
            mainNumbersCombinationLength = vikingLottoMainNumbersCombinationLength;

            //Generator default sum distribution progress
            mainNumbersGeneratorSumDistributionProgress = vikingLottoMainNumbersGeneratorSumDistributionProgress;
        }
        else if (gameName.equals("EuroJackpot"))
        {
            //Sum distribution
            maxMainNumbersSum = euroJackpotMainNumbersMaxSum;
            minMainNumbersSum = euroJackpotMainNumbersMinSum;
            maxAdditionalNumbersSum = euroJackpotAdditionalNumbersMaxSum;
            minAdditionalNumbersSum = euroJackpotAdditionalNumbersMinSum;
            maxProgressMainNumbers = euroJackpotMainNumbersMaxProgress;
            maxProgressAdditionalNumbers = euroJackpotAdditionalNumbersMaxProgress;
            defaultProgressMainNumbers = euroJackpotMainNumbersDefaultProgress;
            defaultProgressAdditionalNumbers = euroJackpotAdditionalNumbersDefaultProgress;

            //High/Low numbers
            midMainNumber = euroJackpotMainNumberMid;
            midAdditionalNumber = euroJackpotAdditionalNumberMid;

            //Most popular numbers
            minMainNumber = euroJackpotMainNumberMin;
            maxMainNumber = euroJackpotMainNumberMax;
            minAdditionalNumber = euroJackpotMainNumberMin;
            maxAdditionalNumber = euroJackpotAdditionalNumberMax;

            //Combination length
            mainNumbersCombinationLength = euroJackpotMainNumbersCombinationLength;
            additionalNumbersCombinationLength = euroJackpotAdditionalNumbersCombinationLength;

            //Generator default sum distribution progress
            mainNumbersGeneratorSumDistributionProgress = euroJackpotMainNumbersGeneratorSumDistributionProgress;
            additionalNumbersGeneratorSumDistributionProgress = euroJackpotAdditionalNumbersGeneratorSumDistributionProgress;
        }
    }
}
