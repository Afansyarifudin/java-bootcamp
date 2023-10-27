package com.example.challenge_4.controller;

import com.example.challenge_4.model.Merchant;
import com.example.challenge_4.service.MerchantService;
import com.example.challenge_4.service.MerchantServiceImpl;
import com.example.challenge_4.view.MerchantView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

@Controller
public class MerchantController {

    @Autowired
    MerchantServiceImpl merchantService;

    private final static Logger logger = LoggerFactory.getLogger(MerchantController.class);


    public void index() {
        List<Merchant> merchantList = merchantService.getAll();
        MerchantView.showAllMerchant(merchantList);
        MerchantView.showMerchantMenuOption();
        selectMerchantMenu();
    }

    private void selectMerchantMenu() {
        Scanner scan = new Scanner(System.in);
        try {
            int merchantMenuSelect = scan.nextInt();
            if (merchantMenuSelect==1) {
                addMerchant();
            } else if (merchantMenuSelect==2) {
                editStatusMerchant();
            } else {

            }
        } catch (InputMismatchException e) {
            logger.error(e.toString());
        }
    }

    private void editStatusMerchant() {
        Scanner scan = new Scanner(System.in);

        System.out.print("Nama: ");
        String name = scan.nextLine();

        System.out.print("T(Tutup) atau B(Buka): ");
        String statusInput = scan.nextLine();
        boolean status = statusInput.equalsIgnoreCase("T") ? false:true;

        merchantService.editStatusMerchantByName(name, status);
        index();

    }

    private void addMerchant() {
        Merchant merchant = new Merchant();
        Scanner scan = new Scanner(System.in);

        System.out.print("Nama: ");
        String name = scan.nextLine();
        merchant.setName(name);

        System.out.print("Location: ");
        String location = scan.nextLine();
        merchant.setLocation(location);

        merchantService.create(merchant);
        index();
    }
}
