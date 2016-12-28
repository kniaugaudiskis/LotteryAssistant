package linas.lotteryassistant.Core.Generator;

//Created by Linas on 2016-10-29.

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import linas.lotteryassistant.Components.GeneratedCombination;
import linas.lotteryassistant.Config.AppConfig;
import linas.lotteryassistant.Core.Generator.GeneratorSettings.GeneratorSettings;
import linas.lotteryassistant.Core.Generator.GeneratorValidator.GeneratorValidator;
import linas.lotteryassistant.Helpers.NumberSort;

public class Generator
{
    protected List<GeneratedCombination> generatedCombinationsList = new ArrayList<>();

    private List<Integer> mainNumbersBackup = new ArrayList<>();
    private List<Integer> additionalNumbersBackup = new ArrayList<>();

    public String GetGeneratedCombinations(int combinationCountToGenerate, String generatorType)
    {
        String generatedCombinationsString = "";

        generatedCombinationsList = GenerateAndGetCombinationsList(combinationCountToGenerate, generatorType);

        for (GeneratedCombination generatedCombination : generatedCombinationsList)
        {
            List<Integer> tempMainNumbersCombination = generatedCombination.GetMainNumbers();

            String combinationString = "";

            for (Integer mainLotteryNumber : tempMainNumbersCombination)
            {
                if (mainLotteryNumber < 10)
                {
                    combinationString += "0" + mainLotteryNumber + "-";
                }
                else
                {
                    combinationString += mainLotteryNumber + "-";
                }
            }

            combinationString = combinationString.substring(0, combinationString.length() - 1);

            if (AppConfig.gameName.equals("EuroJackpot"))
            {
                List<Integer> tempAdditionalNumbersCombination = generatedCombination.GetAdditionalNumbers();

                combinationString += "    ";

                for (Integer additionalLotteryNumber : tempAdditionalNumbersCombination)
                {
                    if (additionalLotteryNumber < 10)
                    {
                        combinationString += "0" + additionalLotteryNumber + "-";
                    }
                    else
                    {
                        combinationString += additionalLotteryNumber + "-";
                    }
                }

                combinationString = combinationString.substring(0, combinationString.length() - 1);
            }

            generatedCombinationsString += combinationString + "\n";
        }

        return generatedCombinationsString;
    }

    private List<GeneratedCombination> GenerateAndGetCombinationsList(int combinationsAmountToGenerate, String generatorType)
    {
        generatedCombinationsList.clear();

        BackupNumbers(generatorType);

        Random rand = new Random();

        for (int i = 0; i < combinationsAmountToGenerate; i++)
        {
            int failedGeneratorAttempts = 0;

            GeneratedCombination generatedCombination = new GeneratedCombination();

            //Main numbers
            List<Integer> mainNumbersGeneratedCombination = new ArrayList<>();
            int maxIndexMainNumbers = AppConfig.maxMainNumber - 1;

            for (int j = 0; j < AppConfig.mainNumbersCombinationLength; j++)
            {
                int randomIndex = 0 + rand.nextInt((maxIndexMainNumbers - 0) + 1); //minimum + rand.nextInt((maximum - minimum) + 1);
                int lotteryNumber = GetLotteryNumberByIndex(randomIndex, maxIndexMainNumbers, "MainNumbers", generatorType);

                mainNumbersGeneratedCombination.add(lotteryNumber);
                maxIndexMainNumbers--;
            }

            mainNumbersGeneratedCombination = NumberSort.SortIntegerList(mainNumbersGeneratedCombination, "Asc");
            generatedCombination.AddMainNumberCombination(mainNumbersGeneratedCombination);

            //Additional numbers
            if (AppConfig.gameName.equals("EuroJackpot"))
            {
                List<Integer> additionalNumbersGeneratedCombination = new ArrayList<>();
                int maxIndexAdditionalNumbers = AppConfig.maxAdditionalNumber - 1;

                for (int j = 0; j < AppConfig.additionalNumbersCombinationLength; j++)
                {
                    int randomIndex = 0 + rand.nextInt((maxIndexAdditionalNumbers - 0) + 1); //minimum + rand.nextInt((maximum - minimum) + 1);
                    int lotteryNumber = GetLotteryNumberByIndex(randomIndex, maxIndexAdditionalNumbers, "AdditionalNumbers", generatorType);

                    additionalNumbersGeneratedCombination.add(lotteryNumber);
                    maxIndexAdditionalNumbers--;
                }

                additionalNumbersGeneratedCombination = NumberSort.SortIntegerList(additionalNumbersGeneratedCombination, "Asc");
                generatedCombination.AddAdditionalNumberCombination(additionalNumbersGeneratedCombination);
            }

            //Validation
            if (generatorType.equals("AdvancedGenerator"))
            {
                if (GeneratorValidator.IsGeneratedCombinationValid(generatedCombination))
                {
                    generatedCombinationsList.add(generatedCombination);
                }
                else
                {
                    failedGeneratorAttempts++;

                    if (failedGeneratorAttempts == AppConfig.failedCombinationGenerationToleranceCount)
                    {
                        return generatedCombinationsList;
                    }

                    combinationsAmountToGenerate--;
                }
            }
            else if (generatorType.equals("RandomGenerator"))
            {
                generatedCombinationsList.add(generatedCombination);
            }

            RestoreNumbers(generatorType);
        }

        return generatedCombinationsList;
    }

    private int GetLotteryNumberByIndex(int randomIndex, int currentMaxIndex, String numberType, String generatorType)
    {
        if (generatorType.equals("RandomGenerator"))
        {
            if (numberType.equals("MainNumbers"))
            {
                int lotteryNumber = GeneratorSettings.randomGeneratorMainNumbers.get(randomIndex);
                int lotteryNumberFromMaxIndex = GeneratorSettings.randomGeneratorMainNumbers.get(currentMaxIndex);

                GeneratorSettings.randomGeneratorMainNumbers.set(randomIndex, lotteryNumberFromMaxIndex);

                return lotteryNumber;
            }
            else if (numberType.equals("AdditionalNumbers"))
            {
                int lotteryNumber = GeneratorSettings.randomGeneratorAdditionalNumbers.get(randomIndex);
                int lotteryNumberFromMaxIndex = GeneratorSettings.randomGeneratorAdditionalNumbers.get(currentMaxIndex);

                GeneratorSettings.randomGeneratorAdditionalNumbers.set(randomIndex, lotteryNumberFromMaxIndex);

                return lotteryNumber;
            }
        }
        else if (generatorType.equals("AdvancedGenerator"))
        {
            if (numberType.equals("MainNumbers"))
            {
                int lotteryNumber = GeneratorSettings.advancedGeneratorMainNumbers.get(randomIndex);
                int lotteryNumberFromMaxIndex = GeneratorSettings.advancedGeneratorMainNumbers.get(currentMaxIndex);

                GeneratorSettings.advancedGeneratorMainNumbers.set(randomIndex, lotteryNumberFromMaxIndex);

                return lotteryNumber;
            }
            else if (numberType.equals("AdditionalNumbers"))
            {
                int lotteryNumber = GeneratorSettings.advancedGeneratorAdditionalNumbers.get(randomIndex);
                int lotteryNumberFromMaxIndex = GeneratorSettings.advancedGeneratorAdditionalNumbers.get(currentMaxIndex);

                GeneratorSettings.advancedGeneratorAdditionalNumbers.set(randomIndex, lotteryNumberFromMaxIndex);

                return lotteryNumber;
            }
        }

        return 0;
    }

    private void BackupNumbers(String generatorType)
    {
        if (generatorType.equals("RandomGenerator"))
        {
            for (Integer lotteryNumber : GeneratorSettings.randomGeneratorMainNumbers)
            {
                mainNumbersBackup.add(lotteryNumber);
            }

            if (AppConfig.gameName.equals("EuroJackpot"))
            {
                for (Integer lotteryNumber : GeneratorSettings.randomGeneratorAdditionalNumbers)
                {
                    additionalNumbersBackup.add(lotteryNumber);
                }
            }
        }
        else if (generatorType.equals("AdvancedGenerator"))
        {
            for (Integer lotteryNumber : GeneratorSettings.advancedGeneratorMainNumbers)
            {
                mainNumbersBackup.add(lotteryNumber);
            }

            if (AppConfig.gameName.equals("EuroJackpot"))
            {
                for (Integer lotteryNumber : GeneratorSettings.advancedGeneratorAdditionalNumbers)
                {
                    additionalNumbersBackup.add(lotteryNumber);
                }
            }
        }
    }

    private void RestoreNumbers(String generatorType)
    {
        if (generatorType.equals("RandomGenerator"))
        {
            GeneratorSettings.randomGeneratorMainNumbers.clear();

            for (Integer lotteryNumber : mainNumbersBackup)
            {
                GeneratorSettings.randomGeneratorMainNumbers.add(lotteryNumber);
            }

            if (AppConfig.gameName.equals("EuroJackpot"))
            {
                GeneratorSettings.randomGeneratorAdditionalNumbers.clear();

                for (Integer lotteryNumber : additionalNumbersBackup)
                {
                    GeneratorSettings.randomGeneratorAdditionalNumbers.add(lotteryNumber);
                }
            }
        }
        else if (generatorType.equals("AdvancedGenerator"))
        {
            GeneratorSettings.advancedGeneratorMainNumbers.clear();

            for (Integer lotteryNumber : mainNumbersBackup)
            {
                GeneratorSettings.advancedGeneratorMainNumbers.add(lotteryNumber);
            }

            if (AppConfig.gameName.equals("EuroJackpot"))
            {
                GeneratorSettings.advancedGeneratorAdditionalNumbers.clear();

                for (Integer lotteryNumber : additionalNumbersBackup)
                {
                    GeneratorSettings.advancedGeneratorAdditionalNumbers.add(lotteryNumber);
                }
            }
        }
    }
}
