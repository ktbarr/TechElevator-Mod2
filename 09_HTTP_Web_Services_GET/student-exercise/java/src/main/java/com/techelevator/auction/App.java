package com.techelevator.auction;

import java.util.Scanner;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class App {

    public static final String API_URL = "http://localhost:3000/auctions";
    public RestTemplate restTemplate = new RestTemplate();

    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

    	App app = new App();
    	app.run();
    }

    public Auction[] listAllAuctions() 
    {
    	String url = API_URL;
    	Auction[] auctions = restTemplate.getForObject(url, Auction[].class);
    	printAuctions(auctions);
		return auctions;
    }

    public Auction listDetailsForAuction(int id) {
        String url = API_URL + "/" + id;
        Auction auction = restTemplate.getForObject(url, Auction.class);
        return auction;
    }

    public Auction[] findAuctionsSearchTitle(String title) {
    	String url = API_URL + "?title_like=" + title;
        Auction[] auction = restTemplate.getForObject(url, Auction[].class);
        return auction; 
        
    }

    public Auction[] findAuctionsSearchPrice(double price) {
    	String url = API_URL + "?currentBid_lte=" + price;
        Auction[] auction = restTemplate.getForObject(url, Auction[].class);
        return auction; 
    }

    private void run() {
        int menuSelection = 999;

        printGreeting();

        while (menuSelection != 5) {
        	menuSelection = promptForInt("Please choose an option: ");
            System.out.println("");
            if (menuSelection == 1) {
                printAuctions(listAllAuctions());
            } else if (menuSelection == 2) {
            	int id = promptForInt("Please enter an auction id to get the details: ");
                printAuction(listDetailsForAuction(id));
            } else if (menuSelection == 3) {
            	String title = promptForText("Please enter a title to search for: ");
                printAuctions(findAuctionsSearchTitle(title));
            } else if (menuSelection == 4) {
            	double price = promptForDouble("Please enter a max price to search for: ");
                printAuctions(findAuctionsSearchPrice(price));
            } else if (menuSelection == 5) {
                scanner.close();
                System.exit(0);
            } else {
                System.out.println("Invalid Selection");
            }
            menuSelection = 999;
            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
            printGreeting();
        }
    }

    private static void printGreeting() {
        System.out.println("");
        System.out.println("Welcome to Online Auctions! Please make a selection: ");
        System.out.println("1: List all auctions");
        System.out.println("2: List details for specific auction");
        System.out.println("3: Find auctions with a specific term in the title");
        System.out.println("4: Find auctions below a specified price");
        System.out.println("5: Exit");
        System.out.println("");
        System.out.print("Please choose an option: ");
    }

    private void printAuctions(Auction[] auctions) {
        if (auctions != null) {
            System.out.println("--------------------------------------------");
            System.out.println("Auctions");
            System.out.println("--------------------------------------------");
            for (Auction auction : auctions) {
                System.out.println(auction.currentBidToString());
            }
        }
    }

    private void printAuction(Auction auction) {
        if (auction != null) {
            System.out.println("--------------------------------------------");
            System.out.println("Auction Details");
            System.out.println("--------------------------------------------");
            System.out.println("Id: " + auction.getId());
            System.out.println("Title: " + auction.getTitle());
            System.out.println("Description: " + auction.getDescription());
            System.out.println("User: " + auction.getUser());
            System.out.println("Current Bid: " + auction.getCurrentBid());
            System.out.println("");
        }
    }

    private int promptForInt(String prompt) {
    	while (true) {
    		System.out.println(prompt);
    		try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException exception) {
                System.out.println("Invalid input. Only input a number.");
            }
    	}
    }
    
    private String promptForText(String prompt) {
    	while (true) {
    		System.out.println(prompt);
    		String text = scanner.nextLine();
    		if ((text != null) && (text.trim().length() > 0)) {
    			return text;
    		}
    		else {
    			System.out.println("Invalid input. Please enter some text.");
    		}
    	}
    }

    private double promptForDouble(String prompt) {
    	while (true) {
    		System.out.println(prompt);
    		try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException exception) {
                System.out.println("Invalid input. Only input a number.");
            }
    	}
    }
}
