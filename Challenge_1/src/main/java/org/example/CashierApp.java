package org.example;

import org.example.repository.ListFood;
import org.example.repository.OrderFood;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CashierApp {
    private List<ListFood> listFood;
    private List<OrderFood> orderFood;

    public CashierApp() {
        // Initialize listFood variable with list of ListFood Object
        listFood = List.of(
                new ListFood(1, "Nasi Goreng", 15000),
                new ListFood(2, "Mie Goreng", 13000),
                new ListFood(3, "Nasi + Ayam", 18000),
                new ListFood(4, "Es Teh Manis", 3000),
                new ListFood(5, "Es Jeruk", 5000)
        );
        // Initialize orderFood variable with empty ArrayList
        orderFood = new ArrayList<>();
    }

    public void run() {
        while (true) {
            showWelcomeMessage();
            showFoodItems();
            System.out.println("99. Pesan dan Bayar");
            System.out.println("0. Keluar Aplikasi \n");
            System.out.print("=> ");
            int choice = getUserChoice();

            switch (choice) {
                case 1, 2, 3, 4, 5:
                    confirmOrder(choice);
                    break;
                case 99:
                    processPayment();
                    break;
                case 0:
                    exitApplication();
                default:
                    System.out.println("Pilihan yang anda masukkan salah. Coba Lagi! \n");
            }
        }
    }

    // Welcome Message
    private void showWelcomeMessage() {
        System.out.println("===============================");
        System.out.println("Selamat Datang di BnarFud");
        System.out.println("=============================== \n");
        System.out.println("Silahkan Pesan makanan : ");
    }

    // Show list food menu
    private void showFoodItems() {
        for (ListFood menu : listFood) {
            System.out.printf("%d. %-19s | %s \n", menu.getId(), menu.getName(), convertCurrency(menu.getPrice()));
        }
    }

    // Get User Input
    private int getUserChoice() {
        return new Scanner(System.in).nextInt();
    }

    // Convert Pattern for RP
    private String convertCurrency (long price) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator('.');
        DecimalFormat formatPattern = new DecimalFormat("#,###.000", symbols);
        return formatPattern.format(price / 1000);
    }

    // Exit Application
    private void exitApplication() {
        System.exit(0);
    }

    // Order confirmation
    private void confirmOrder(int choice) {
        ListFood selectedFood = listFood.get(choice - 1);
        System.out.println("===============================");
        System.out.println("Berapa pesanan anda");
        System.out.println("=============================== \n");
        System.out.printf("%-17s | %s\n", selectedFood.getName(), convertCurrency(selectedFood.getPrice()));
        System.out.println("(input 0 untuk kembali) \n");
        System.out.print("qty => ");
        int quantity = getUserChoice();
        long totalPrice = selectedFood.getPrice() * quantity;

        // Check existing order with name
        OrderFood existingOrder = findExistingOrder(selectedFood.getName());
        if (existingOrder != null) {
            // Update existing orders
            existingOrder.setAmount(existingOrder.getAmount() + quantity);
            existingOrder.setPrice(existingOrder.getPrice() + totalPrice);
        } else {
            // Add new order
            orderFood.add(new OrderFood(selectedFood.getName(), quantity, totalPrice));
        }

        System.out.println();
    }

    // Check existing order
    private OrderFood findExistingOrder(String foodName) {
        for (OrderFood existingOrder : orderFood) {
            if (existingOrder.getName().equalsIgnoreCase(foodName)) {
                return existingOrder;
            }
        }
        return null;   // If no existing order
    }

    // Payment process
    private void processPayment() {
        System.out.println("================================");
        System.out.println("Konfirmasi dan Pembayaran");
        System.out.println("================================ \n");

        int totalQuantity = 0;
        long totalAmount = 0;

        for (OrderFood orderFood : this.orderFood) {
            System.out.printf("%-12s %4d %13s \n", orderFood.getName(), orderFood.getAmount(), convertCurrency(orderFood.getPrice()));
            totalQuantity += orderFood.getAmount();
            totalAmount += orderFood.getPrice();
        }

        System.out.println("------------------------------- +");
        System.out.printf("%-12s %4d %13s \n\n", "Total", totalQuantity, convertCurrency(totalAmount));
        System.out.println("1. Konfirmasi dan Bayar");
        System.out.println("2. Kembali ke Manu Utama");
        System.out.println("0. Keluar Aplikasi \n");
        System.out.print("=> ");

        int choice = getUserChoice();
        switch (choice) {
            case 1:
                try {
                    generateInvoice(totalQuantity, totalAmount);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                exitApplication();
                break;
            case 2:
                run();
                break;
            case 0:
                exitApplication();
            default:
                System.out.println("Pilihan yang anda masukkan salah. Coba Lagi! \n");

        }

    }

    // Get format date for invoice
    private String getCurrentFormatDate() {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        return currentTime.format(dateFormat);
    }

    // Generate Invoice
    private void generateInvoice(int totalQuantity, long totalAmount) throws IOException {
        String formattedDate = getCurrentFormatDate();
        String fileName = "invoice_order-".concat(formattedDate) + ".txt";
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write("================================   \n");
        writer.write("BinarFud \n");
        writer.write("================================   \n\n");
        writer.write("Terima kasih sudah memesan \ndi BinarFud \n\n");
        writer.write("Di bawah ini adalah pesanan anda \n\n");

        for (OrderFood orderFood : this.orderFood) {
            writer.write(String.format("%-12s %4d %13s \n", orderFood.getName(), orderFood.getAmount(), convertCurrency(orderFood.getPrice())));
        }

        writer.write("------------------------------- + \n");
        writer.write(String.format("%-12s %4d %13s \n\n\n", "Total", totalQuantity, convertCurrency(totalAmount)));
        writer.write("Pembayaran : Binar Cash \n\n");
        writer.write("================================   \n");
        writer.write("Simpan struk ini sebagai \nBukti Pembayaran \n");
        writer.write("================================  ");
        writer.close();
    }
}
