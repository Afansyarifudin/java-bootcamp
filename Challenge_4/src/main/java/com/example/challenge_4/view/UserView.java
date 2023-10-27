package com.example.challenge_4.view;

import com.example.challenge_4.util.Helper;

public class UserView {
    public static void showMenuOption() {
        System.out.println("Silahkan pilih menu");
        System.out.println("1. Register User");
        System.out.println("2. Edit Email");
        System.out.println("3. Delete User");
        Helper.menuBasic();
    }
}
