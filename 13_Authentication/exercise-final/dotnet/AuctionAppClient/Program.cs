using RestSharp;
using System;
using System.Collections.Generic;

namespace AuctionApp
{
    public class Program
    {
        private static readonly ConsoleService console = new ConsoleService();
        private static readonly APIService api = new APIService();

        static void Main(string[] args)
        {
            Run();
        }
        private static void Run()
        {
            Console.WriteLine("Welcome to Online Auctions! Please make a selection:");
            MenuSelection();
        }

        private static void MenuSelection()
        {
            int menuSelection = -1;
            while (menuSelection != 0)
            {
                string logInOut = api.LoggedIn ? "Log out" : "Log in";

                Console.WriteLine("");
                Console.WriteLine("Welcome to Online Auctions! Please make a selection: ");
                Console.WriteLine("1: List all auctions");
                Console.WriteLine("2: List details for specific auction");
                Console.WriteLine("3: Find auctions with a specific term in the title");
                Console.WriteLine("4: Find auctions below a specified price");
                Console.WriteLine("5: Create a new auction");
                Console.WriteLine("6: Modify an auction");
                Console.WriteLine("7: Delete an auction");
                Console.WriteLine("8: " + logInOut);
                Console.WriteLine("0: Exit");
                Console.WriteLine("---------");
                Console.Write("Please choose an option: ");

                if (!int.TryParse(Console.ReadLine(), out menuSelection))
                {
                    Console.WriteLine("Invalid input. Please enter only a number.");
                }
                else if (menuSelection == 1)
                {
                    List<Auction> auctions = api.GetAllAuctions();
                    if (auctions != null)
                    {
                        console.PrintAuctions(auctions);
                    }
                }
                else if (menuSelection == 2)
                {
                    List<Auction> auctions = api.GetAllAuctions();
                    if (auctions != null && auctions.Count > 0)
                    {
                        int auctionId = console.PromptForAuctionID(auctions, "get the details");
                        if (auctionId != 0)
                        {
                            Auction auction = api.GetDetailsForAuction(auctionId);
                            if (auction != null)
                            {
                                console.PrintAuction(auction);
                            }
                        }
                    }
                }
                else if (menuSelection == 3)
                {
                    string searchTitle = console.PromptForSearchTitle();
                    if (! string.IsNullOrWhiteSpace(searchTitle))
                    {
                        List<Auction> auctions = api.GetAuctionsSearchTitle(searchTitle);
                        if (auctions != null)
                        {
                            console.PrintAuctions(auctions);
                        }
                        else
                        {
                            Console.WriteLine("No auction found for search title: " + searchTitle);
                        }
                    }
                }
                else if (menuSelection == 4)
                {
                    double maxPrice = console.PromptForMaxPrice();
                    if (maxPrice > 0) {
                        List<Auction> auctions = api.GetAuctionsSearchPrice(maxPrice);
                        if (auctions != null)
                        {
                            console.PrintAuctions(auctions);
                        }
                        else
                        {
                            Console.WriteLine("No auctions found under max price: " + maxPrice.ToString("C"));
                        }
                    }
                }
                else if (menuSelection == 5)
                {
                    string newAuctionString = console.PromptForAuctionData();
                    Auction auctionToAdd = new Auction(newAuctionString);
                    if (auctionToAdd.IsValid)
                    {
                        Auction addedAuction = api.AddAuction(auctionToAdd);
                        if (addedAuction != null)
                        {
                            Console.WriteLine("Auction successfully added.");
                        }
                        else
                        {
                            Console.WriteLine("Auction not added.");
                        }
                    }
                }
                else if (menuSelection == 6)
                {
                    // Update an existing auction
                    List<Auction> auctions = api.GetAllAuctions();
                    if (auctions != null)
                    {
                        int auctionId = console.PromptForAuctionID(auctions, "update");
                        Auction oldAuction = api.GetDetailsForAuction(auctionId);
                        if (oldAuction != null)
                        {
                            string updatedAuctionString = console.PromptForAuctionData(oldAuction);
                            Auction auctionToUpdate = new Auction(updatedAuctionString);
                            if (auctionToUpdate.IsValid)
                            {
                                Auction updatedAuction = api.UpdateAuction(auctionToUpdate);
                                if (updatedAuction != null)
                                {
                                    Console.WriteLine("Auction successfully updated.");
                                }
                                else
                                {
                                    Console.WriteLine("Auction not updated.");
                                }
                            }
                        }
                    }
                }
                else if (menuSelection == 7)
                {
                    // Delete auction
                    List<Auction> auctions = api.GetAllAuctions();
                    if (auctions != null)
                    {
                        int auctionId = console.PromptForAuctionID(auctions, "delete");
                        api.DeleteAuction(auctionId);
                    }
                }
                else if (menuSelection == 8)
                {
                    if (api.LoggedIn)
                    {
                        api.Logout();
                        Console.WriteLine("You are now logged out");
                    }
                    else
                    {
                        Console.Write("Please enter username: ");
                        string username = Console.ReadLine();
                        Console.Write("Please enter password: ");
                        string password = Console.ReadLine();
                        var user = api.Login(username, password);
                        if (user != null)
                        {
                            Console.WriteLine("You are now logged in");
                        }
                    }
                }
                else
                {
                    Console.WriteLine("Goodbye!");
                    Environment.Exit(0);
                }
            }
        }

    }
}
