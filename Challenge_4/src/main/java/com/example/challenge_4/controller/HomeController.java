package com.example.challenge_4.controller;

import com.example.challenge_4.util.Helper;
import com.example.challenge_4.view.HomeMenu;
import com.example.challenge_4.view.MerchantView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class HomeController {

    @Autowired
    MerchantController merchantController;
    UserController userController;
    ProductController productController;
    OrderController orderController;

    public void home() {
        HomeMenu.welcomeMessage();
        HomeMenu.mainMenuOption();
        selectMainMenu();
    }

    private void selectMainMenu() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Pilih menu: ");
        int mainMenuSelect = scan.nextInt();
        if (mainMenuSelect==1) {
            merchantController.index();
        } else if (mainMenuSelect==2){
            productController.index();
        } else if (mainMenuSelect==3) {
            userController.index();
        } else if (mainMenuSelect==4) {
            orderController.index();
        } else {
            Helper.exitApplication();
        }
    }
}
