package org.example.controller;

import org.example.model.ListFood;
import org.example.model.OrderFood;
import org.example.service.FoodService;
import org.example.service.OrderService;
import org.example.util.Helper;
import org.example.view.CashierView;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

import static org.example.util.Helper.exitApplication;

public class CashierController {
    private FoodService foodService;
    private OrderService orderService;

    private static final Logger logger = Logger.getLogger(CashierController.class.getName());

    public CashierController(FoodService foodService, OrderService orderService) {
        this.foodService = foodService;
        this.orderService = orderService;
    }

    private boolean exit = false;
    public void run() {
        while (!exit) {
            CashierView.showWelcomeMessage();
            CashierView.showFoodItems(foodService.getListFood());
            System.out.println("99. Pesan dan Bayar");
            System.out.println("0. Keluar Aplikasi \n");

            try {
                int choice = CashierView.getUserChoice();
                if (isValidChoice(choice)) {
                    switch (choice) {
                        case 1, 2, 3, 4, 5 -> orderConfirmation(choice);
                        case 99 -> paymentProcess();
                        case 0 -> exit = true;
                        default -> System.out.println("Pilihan yang anda masukkan tidak ada di sistem. Coba Lagi! \n");
                    }
                } else {
                    handleInvalidInput();
                }
            } catch (Exception e) {
                handleInvalidInput();
            }
        }
    }

    // Is valid choice
    private boolean isValidChoice(int choice) {
        return choice >= 0 && choice <= 99;
    }

    // Order Confirmation
    private void orderConfirmation(int choice) {
        ListFood selectedFood = foodService.getListFood().get(choice-1);
        System.out.println("===============================");
        System.out.println("Berapa pesanan anda");
        System.out.println("=============================== \n");
        System.out.printf("%-17s | %s%n", selectedFood.getName(), Helper.convertCurrency(selectedFood.getPrice()));
        System.out.println("(input 0 untuk kembali) \n");
        System.out.print("qty => ");
        int quantity = Helper.getUserChoice();
        long totalPrice = selectedFood.getPrice() * quantity;
        String description = CashierView.getDetailOrder();

        OrderFood existingOrder = orderService.findExistingOrder(selectedFood.getName());

        if (existingOrder != null) {
            existingOrder.setAmount(existingOrder.getAmount() + quantity);
            existingOrder.setPrice(existingOrder.getPrice() + totalPrice);
            existingOrder.setDescription(description);
        } else {
            orderService.addOrder(new OrderFood(selectedFood, quantity,description));
        }
    }

    // Handle invalid input
    private void handleInvalidInput() {
        CashierView.showInvalidInputConfirmation();
        String confirmation = Helper.getUserInputInvalid().trim().toLowerCase();
        if (!confirmation.equals("y")) {
            Helper.exitApplication();
        }
    }

    // Payment process
    private void paymentProcess() {

        int totalQuantity = 0;
        int totalAmount = 0;

        System.out.println("================================");
        System.out.println("Konfirmasi dan Pembayaran");
        System.out.println("================================ \n");

        for (OrderFood orderedFood : orderService.getOrderFood()) {
            System.out.printf("%-12s %4d %13s %n", orderedFood.getName(), orderedFood.getAmount(), Helper.convertCurrency(orderedFood.getPrice()));
            System.out.println("Catatan: " + orderedFood.getDescription());
            totalQuantity += orderedFood.getAmount();
            totalAmount += orderedFood.getPrice();
        }

        if(totalQuantity  < 1) {
            CashierView.showMinimalOrder();
            return;
        }


        System.out.println("------------------------------- +");
        System.out.printf("%-12s %4d %13s %n%n", "Total", totalQuantity, Helper.convertCurrency(totalAmount));
        System.out.println("1. Konfirmasi dan Bayar");
        System.out.println("2. Kembali ke Manu Utama");
        System.out.println("0. Keluar Aplikasi \n");
        System.out.print("=> ");

        int choice = Helper.getUserChoice();
        switch (choice) {
            case 1 -> {
                generateInvoice(totalQuantity, totalAmount);
                Helper.exitApplication();
            }
            case 2 -> run();
            case 0 -> exitApplication();
            default -> logger.warning("Pilihan yang anda masukkan salah. Coba Lagi! \n");
        }
    }

    // Generate Invoice
    private void generateInvoice(int totalQuantity, long totalAmount) {
        String formattedDate = Helper.getCurrentFormatDate();
        String fileName = "invoice_order-".concat(formattedDate) + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {

            writer.write("================================   \n");
            writer.write("BinarFud \n");
            writer.write("================================   \n\n");
            writer.write("Terima kasih sudah memesan \ndi BinarFud \n\n");
            writer.write("Di bawah ini adalah pesanan anda \n\n");

            for (OrderFood orderedFood : orderService.getOrderFood()) {
                writer.write(String.format("%-12s %4d %13s %n", orderedFood.getName(), orderedFood.getAmount(), Helper.convertCurrency(orderedFood.getPrice())));
                writer.write("Catatan: " + orderedFood.getDescription() + "\n\n");
            }

            writer.write("------------------------------- + \n");
            writer.write(String.format("%-12s %4d %13s %n%n%n", "Total", totalQuantity, Helper.convertCurrency(totalAmount)));
            writer.write("Pembayaran : Binar Cash \n\n");
            writer.write("================================   \n");
            writer.write("Simpan struk ini sebagai \nBukti Pembayaran \n");
            writer.write("================================  ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
