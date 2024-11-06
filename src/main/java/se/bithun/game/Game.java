package se.bithun.game;

import se.bithun.game.model.*;
import java.util.Scanner;

public class Game {
    private Resident resident;   // attributes
    private Burglar burglar;
    private boolean running;
    private boolean foundBurglar;
    private boolean informedPolice;
    private boolean inLivingRoom;
    private boolean defeatedburglar;

    public Game() {
        resident = new Resident();  // constructor setting values of game class attributes.
        burglar = new Burglar();
        running = true;
        foundBurglar = false;
        informedPolice = false;
        inLivingRoom = true;
        defeatedburglar = false;

    }

    public void start() {
        System.out.println("Welcome to the adventure game! You are in a house at night...");
        Scanner scanner = new Scanner(System.in);

        while (running) {
            if (inLivingRoom) {
                System.out.println("You are in the living room. Where would you like to go? (kitchen / bedroom / office / hall / exit)");
                String input = scanner.nextLine();

                switch (input.toLowerCase()) {
                    case "kitchen":
                        visitKitchen();
                        inLivingRoom = false;
                        break;
                    case "bedroom":
                        System.out.println("The bedroom is quiet. There's nothing here.");
                        inLivingRoom = false;
                        break;
                    case "office":
                        visitOffice();
                        inLivingRoom = false;
                        break;
                    case "hall":
                        visitHall();
                        inLivingRoom = false;
                        break;
                    case "exit":
                        running = false;
                        System.out.println("You left the house. Game over.");
                        break;
                    default:
                        System.out.println("Invalid choice. Please choose a valid room.");
                }
            } else {
                System.out.println("You must return to the living room before going elsewhere. Type 'back' to go back to the living room.");
                String input = scanner.nextLine();

                if (input.equalsIgnoreCase("back")) {
                    inLivingRoom = true;
                } else {
                    System.out.println("Invalid choice. Type 'back' to go back to the living room.");
                }
            }

            if (foundBurglar && informedPolice) {
                System.out.println("Congratulations! You defeated the burglar and called the police. You win!");
                running = false;
            }
        }
        scanner.close();
    }

    private void visitKitchen() {
        System.out.println("You enter the kitchen.");
        if (!resident.hasWeapon()) {
            resident.findWeapon();
            System.out.println("You found a frying pan to use as a weapon.");
        } else {
            System.out.println("You've already taken the frying pan.");
        }
    }

    private void visitHall() {
        System.out.println("You enter the hall.");
        if (!foundBurglar) {
            encounterBurglar();  // Trigger the burglar encounter when entering the hall
        } else {
            System.out.println("The hall is quiet. The burglar has already been defeated.");
        }
    }

    private void encounterBurglar() {
        System.out.println("You encounter the burglar! A fight begins.");
        foundBurglar = true;  // Set foundBurglar to true, as the encounter has begun

        while (resident.isAlive() && burglar.isAlive()) {
            // Resident attacks Burglar
            System.out.println("You hit the burglar.");
            burglar.takeDamage(resident.getDamage());
            System.out.println("Burglar's health: " + burglar.getHealth());

            // Check if the burglar is defeated
            if (!burglar.isAlive()) {
                defeatedburglar = true;
                System.out.println("You defeated the burglar. Now inform the police.");
                break;
            }

            // Burglar attacks Resident
            System.out.println("The burglar hits you.");
            resident.takeDamage(burglar.getDamage());
            System.out.println("Your health: " + resident.getHealth());

            // Check if the resident is defeated
            if (!resident.isAlive()) {
                System.out.println("You have been defeated by the burglar. Game over.");
                running = false;
                break;
            }
        }
    }

    private void visitOffice() {
        System.out.println("You enter the office room.");

        if (defeatedburglar && !informedPolice) {
            System.out.println("You call the police to report the incident.");
            informedPolice = true;
        } else if (informedPolice) {
            System.out.println("You have already informed the police.");
        } else {
            System.out.println("It’s quiet here, and there is nothing of interest.");
        }
    }
}
