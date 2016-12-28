package linas.lotteryassistant.Helpers;

//Created by Linas on 2016-09-13.

public class NumberFormat
{
    public static String DoubleNoDecimalPlaces(double number)
    {
        java.text.DecimalFormat df = new java.text.DecimalFormat("#");

        return String.valueOf(df.format(number));
    }

    public static String DoubleOneDecimalPlace(double number)
    {
        java.text.DecimalFormat df = new java.text.DecimalFormat("#.#");

        return String.valueOf(df.format(number));
    }
}
