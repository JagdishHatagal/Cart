package org.example;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {


    private final Map<String, Integer> listofItemAndQuantity = new HashMap<String, Integer>();
    final String[] items = { "0", "Bananas", "Apples", "Melons", "Limes" };


    public static void main(String[] args) {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        final Map<String, Double> itemPriceMap;
        itemPriceMap = new HashMap<String, Double>();
        itemPriceMap.put("Bananas", 0.20);
        itemPriceMap.put("Apples", 0.35);
        itemPriceMap.put("Melons", 0.50);
        itemPriceMap.put("Limes", 0.15);


        System.out.printf("Hello and welcome!");

        // Press Shift+F10 or click the green arrow button in the gutter to run the code.

        Main mainObject= new Main();

        Map<String, Integer> finalList= mainObject.getCart();
         double totalCost = mainObject.getTotal(finalList, itemPriceMap);
        System.out.println(String.format("The total cost is: $%.2f", totalCost));

    }

    double getTotal(final Map<String, Integer> b, final Map<String, Double> ipp) {
        double total = 0.0;
        System.out.println("Using the following prices for each item:");
        for (final Map.Entry<String, Integer> kvp : b.entrySet()) {

            total += ipp.get(kvp.getKey()) * kvp.getValue();
            int  offerQuantity=offer(kvp.getKey(), kvp.getValue());


            System.out.println(String.format("%s: $%.2f (%d items)", kvp.getKey(), ipp.get(kvp.getKey()) * kvp.getValue(),
                    offerQuantity));
        }
        return total;
    }

    public Map<String, Integer> getCart() {

        final Scanner sc = new Scanner(System.in);
        try {
            while (true) {
                System.out.println("Enter the number (1 - 5) of the item to purchase and quantity required:");
                System.out.println("1. Bananas");
                System.out.println("2. Apples");
                System.out.println("3. Melons");
                System.out.println("4. Limes");
                System.out.println("5. Exit");

                final int selectedItem = getInt(sc);
                if (selectedItem < 1 || selectedItem > 5) {
                    System.out.println("Choice must be between 1 - 5");
                } else if (selectedItem == 5) {
                    break;
                } else {

                    final int quantity = getInt(sc);

                    if (quantity <= 0) {
                        System.out.println("Quantity must be positive..!");
                    } else {
                        int updatedCount = quantity;
                        if (listofItemAndQuantity.containsKey(items[selectedItem])) {
                            final int currentCount = listofItemAndQuantity.get(items[selectedItem]);
                            updatedCount += currentCount;
                        }
                        listofItemAndQuantity.put(items[selectedItem], updatedCount);
                        System.out.println("You can enter more items or exit..!");
                    }

                }
            }
        } finally {
            sc.close();
        }

        return listofItemAndQuantity;
    }

    int offer(String items, int quantity){

        int extraquantity=0;
        if (items.equals("Melons"))
        {
            quantity=quantity*2;
        }
        if (items.equals("Limes")){

            if (quantity%2 != 0){
                quantity=quantity-1;
                extraquantity=quantity/2;
                quantity=quantity+extraquantity+1;

            }else{
                extraquantity=quantity/2;
                quantity=quantity+extraquantity;

            }

        }
        return quantity;
    }

    int getInt(final Scanner sc) {
        while (true) {
            try {
                final int num = sc.nextInt();
                return num;
            } catch (final InputMismatchException e) {
                System.out.println("Please enter integer..!");
                sc.nextLine();
            }
        }
    }



}