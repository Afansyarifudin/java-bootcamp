package com.example.challenge_4.view;

import com.example.challenge_4.model.Merchant;

import java.util.List;

public class MerchantView {
    public static void showAllMerchant(List<Merchant> merchantList) {
        merchantList.forEach(merchant -> {
            System.out.println("Nama: "+ merchant.getName());
            System.out.println("Location: "+ merchant.getLocation());
            System.out.println("Status: "+ (Boolean.TRUE.equals(merchant.getOpen())? "Buka":"Tutup"));
        });
    }

    public static void showMerchantMenuOption() {
        System.out.println("Silahkan pilih menu");
        System.out.println("1. Tambah Merchant");
        System.out.println("2. Edit Status");
        System.out.println("3. Tampilkan merchant yang sedang buka");
    }
}
