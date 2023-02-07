import java.util.Scanner;
import java.util.Arrays;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean runAgain = true;
        while (runAgain) {
            double currencyAmount = printTitle(scanner);
            int sourceCurrencyType = printWhatToConvert(scanner, currencyAmount);
            printConvertTo(currencyAmount, sourceCurrencyType);
            int convertTo = scanner.nextInt();
            double endCurrency = calculatingCurrency(sourceCurrencyType, currencyAmount, convertTo);
            System.out.println(currencyAmount + currencyNames[sourceCurrencyType - 1] + " is equal to " + endCurrency + currencyNames[convertTo - 1]);
            runAgain = systemRunAgain(scanner);
        }
    }


    private static double printTitle(Scanner scannerAmount) {
        double currencyAmount;
        System.out.println("Welcome to my money converter");
        System.out.println("We can convert your money in a lot of currency's");
        System.out.println("Type in the amount you want to convert");
        try {
            currencyAmount = scannerAmount.nextDouble();
        } catch (Exception e) {
            System.out.println("This is the wrong input, please enter a valid number.");
            scannerAmount.nextLine();
            currencyAmount = printTitle(scannerAmount);
        }
        return currencyAmount;
    }

    private static int printWhatToConvert(Scanner scannerToConvert, double currencyAmount) {
        int sourceCurrencyType;
        System.out.println("You want to convert " + currencyAmount);
        System.out.println("Choose the currency you have:");
        displayCurrencies();
        try {
            sourceCurrencyType = scannerToConvert.nextInt();
        } catch (Exception e) {
            System.out.println("This is the wrong input, please enter a valid number.");
            scannerToConvert.nextLine();
            sourceCurrencyType = (int) printTitle(scannerToConvert);
        }
        return sourceCurrencyType;
    }

    private static void printConvertTo(double amountToConvert, int startingCurrency) {
        System.out.println("You want to convert "+ amountToConvert + " " + currencyNames[startingCurrency - 1]);
        System.out.println("Choose where to convert to:");
        displayCurrencies();
    }

    private static void displayCurrencies() {
        for (int i = 0; i < currencyNames.length; i++) {
            System.out.println((i + 1) + ". " + currencyNames[i]);
        }
    }
    private static boolean systemRunAgain(Scanner scannerRunAgain) {
        System.out.println("Would you like to convert another amount? (yes/no): ");
        String anotherConversion = scannerRunAgain.next();
        return anotherConversion.equalsIgnoreCase("yes");
    }

    /*public static boolean keepGoing(Scanner scanner, boolean runAgain) {
        boolean checkContinue = true;
        while(checkContinue) {
            System.out.print("Convert another amount? (y/n): ");
            String anotherConversion = scanner.next();
            if (anotherConversion.equals("n")) {
                scanner.nextLine();
                System.out.println("OK, Goodbye");
                runAgain = false;
                checkContinue = false;
            } else if (!anotherConversion.equals("y")) {
                System.out.println("Wrong input");
            }
            else {
                checkContinue = false;
            }
        }
        return runAgain;
    }*/
    public static double calculatingCurrency(int sourceCurrencyType,  double currencyAmount, int convertTo) {
        double sourceToEurosRate = 1 / conversionRates[sourceCurrencyType - 1];
        double euros = currencyAmount * sourceToEurosRate;
        double targetRate = conversionRates[convertTo - 1];
        double endCurrency = euros * targetRate;
        return endCurrency;
    }

    public static void displayArray() {
        double[][] arr = new double[][]{conversionRates};
        System.out.println("The original order is: ");
        for (double[] num : arr) {
            System.out.println(num + " ");
        }
        Arrays.sort(arr);
        System.out.println("\nThe sorted array is: ");
        for (double[] num : arr) System.out.println(num + " ");
    }

    private static final String[] currencyNames = {"Euro's", "American Dollars", "Japanese Yen", "Rubles", "Rupiah", "Krone", "Chilean Unit of Account", "South korean Won"};
    private static final double[] conversionRates = {1, 1.08, 141.23, 76.70, 16311.27, 10.85, 0.025, 1349.24};
}

