package com.example.challenge_4.controller;

import com.example.challenge_4.service.UserServiceImpl;
import com.example.challenge_4.util.Helper;
import com.example.challenge_4.view.UserView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.yaml.snakeyaml.nodes.ScalarNode;

import java.sql.SQLOutput;
import java.util.InputMismatchException;
import java.util.Scanner;

@Controller
public class UserController {
    @Autowired
    UserServiceImpl userService;

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    public void index() {
        UserView.showMenuOption();
        selectUserMenu();
    }

    private void selectUserMenu() {
        Scanner scan = new Scanner(System.in);
        try {
            int userMenuSelect = scan.nextInt();
            if (userMenuSelect==1) {
                registerUser();
            } else if (userMenuSelect==2) {
                editEmail();
            } else if (userMenuSelect==3) {
                deleteUser();
            } else if(userMenuSelect==99) {

            } else {
                Helper.exitApplication();
            }
        } catch (InputMismatchException e) {
            logger.error(e.toString());
        }
    }

    private void deleteUser() {
        Scanner scan = new Scanner(System.in);

        System.out.print("Masukan Username: ");
        String username = scan.nextLine();
        userService.deleteUser(username);
    }

    private void editEmail() {
        Scanner scan = new Scanner(System.in);

        System.out.print("Masukan Username: ");
        String username = scan.nextLine();
        System.out.print("Masukkan email baru: ");
        String email = scan.nextLine();
        userService.updateEmail(username, email);
    }

    private void registerUser() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Email: ");
        String email = scan.nextLine();

        System.out.print("Username: ");
        String username = scan.nextLine();

        System.out.print("Password: ");
        String password = scan.nextLine();

        userService.registerUser(email, username, password);

    }
}
