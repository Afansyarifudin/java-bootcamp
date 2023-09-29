package org.example.view;

import org.example.model.ListFood;
import org.example.util.Helper;

import java.util.List;
import java.util.Scanner;

public class CashierView {


    // show welcome message
    public static void showWelcomeMessage() {
        System.out.println("===============================");
        System.out.println("Selamat Datang di BnarFud");
        System.out.println("=============================== \n");
        System.out.println("Silahkan Pesan makanan : ");
    }

    // Show menus
    public static void showFoodItems(List<ListFood> foodItems) {
        for (ListFood menu : foodItems) {
            System.out.printf("%d. %-19s | %s %n", menu.getId(), menu.getName(), Helper.convertCurrency(menu.getPrice()));
        }
    }

    public static int getUserChoice() {
        System.out.print("=> ");
        return Helper.getUserChoice();
    }

    public static void showInvalidInputConfirmation() {
        System.out.println("======================");
        System.out.println("Mohon masukkan input");
        System.out.println("pilihan anda");
        System.out.println("======================");
        System.out.println("(Y) untuk lanjut");
        System.out.println("(n) untuk keluar ");
        System.out.print("\n=> ");
    }

    public static String getDetailOrder() {
        System.out.print("Deskripsi Pesanan: ");
        return new Scanner(System.in).nextLine().trim();
    }

    public static void showMinimalOrder() {
        System.out.println("======================");
        System.out.println("Minimal 1 jumlah");
        System.out.println("pesanan!");
        System.out.println("======================");
    }

}
