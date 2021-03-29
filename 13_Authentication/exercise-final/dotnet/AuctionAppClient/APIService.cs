using RestSharp;
using RestSharp.Authenticators;
using System;
using System.Collections.Generic;

namespace AuctionApp
{
    public class APIService
    {
        private readonly static string API_BASE_URL = "https://localhost:44390/";
        private readonly static string AUCTIONS_URL = API_BASE_URL + "auctions";
        private readonly IRestClient client;
        private static API_User user = new API_User();

        public bool LoggedIn { get { return !string.IsNullOrWhiteSpace(user.Token); } }

        public string UNAUTHORIZED_MSG { get { return "Authorization is required for this endpoint. Please log in."; } }
        public string FORBIDDEN_MSG { get { return "You do not have permission to perform the requested action"; } }
        public string OTHER_4XX_MSG { get { return "Error occurred - received non-success response: "; } }


        public APIService()
        {
            client = new RestClient();
        }
        public APIService(IRestClient restClient)
        {
            client = restClient;
        }

        public List<Auction> GetAllAuctions()
        {
            RestRequest request = new RestRequest(AUCTIONS_URL);
            IRestResponse<List<Auction>> response = client.Get<List<Auction>>(request);

            if (response.ResponseStatus != ResponseStatus.Completed || !response.IsSuccessful)
            {
                ProcessErrorResponse(response);
            }
            else
            {
                return response.Data;
            }
            return null;
        }

        public Auction GetDetailsForAuction(int auctionId)
        {
            RestRequest requestOne = new RestRequest(AUCTIONS_URL + "/" + auctionId);
            IRestResponse<Auction> response = client.Get<Auction>(requestOne);

            if (response.ResponseStatus != ResponseStatus.Completed || !response.IsSuccessful)
            {
                ProcessErrorResponse(response);
            }
            else
            {
                return response.Data;
            }
            return null;
        }

        public List<Auction> GetAuctionsSearchTitle(string searchTitle)
        {
            RestRequest request = new RestRequest(AUCTIONS_URL + "?title_like=" + searchTitle);
            IRestResponse<List<Auction>> response = client.Get<List<Auction>>(request);

            if (response.ResponseStatus != ResponseStatus.Completed || !response.IsSuccessful)
            {
                ProcessErrorResponse(response);
            }
            else
            {
                return response.Data;
            }
            return null;
        }

        public List<Auction> GetAuctionsSearchPrice(double searchPrice)
        {
            RestRequest request = new RestRequest(AUCTIONS_URL + "?currentBid_lte=" + searchPrice);
            IRestResponse<List<Auction>> response = client.Get<List<Auction>>(request);

            if (response.ResponseStatus != ResponseStatus.Completed || !response.IsSuccessful)
            {
                ProcessErrorResponse(response);
            }
            else
            {
                return response.Data;
            }
            return null;
        }

        public Auction AddAuction(Auction newAuction)
        {
            RestRequest request = new RestRequest(AUCTIONS_URL);
            request.AddJsonBody(newAuction);
            IRestResponse<Auction> response = client.Post<Auction>(request);

            if (response.ResponseStatus != ResponseStatus.Completed || !response.IsSuccessful)
            {
                ProcessErrorResponse(response);
            }
            else
            {
                return response.Data;
            }
            return null;
        }

        public Auction UpdateAuction(Auction auctionToUpdate)
        {
            RestRequest request = new RestRequest(AUCTIONS_URL + "/" + auctionToUpdate.Id);
            request.AddJsonBody(auctionToUpdate);
            IRestResponse<Auction> response = client.Put<Auction>(request);

            if (response.ResponseStatus != ResponseStatus.Completed || !response.IsSuccessful)
            {
                ProcessErrorResponse(response);
            }
            else
            {
                return response.Data;
            }
            return null;
        }

        public void DeleteAuction(int auctionId)
        {
            RestRequest request = new RestRequest(AUCTIONS_URL + "/" + auctionId);
            IRestResponse response = client.Delete(request);

            if (response.ResponseStatus != ResponseStatus.Completed || !response.IsSuccessful)
            {
                Console.WriteLine(ProcessErrorResponse(response));
            }
            else
            {
                Console.WriteLine("Auction successfully deleted.");
            }
        }

        public string ProcessErrorResponse(IRestResponse response)
        {
            if (response.ResponseStatus != ResponseStatus.Completed)
            {
                return "Error occurred - unable to reach server.";
            }
            else if (!response.IsSuccessful)
            {
                if (response.StatusCode == System.Net.HttpStatusCode.Unauthorized)
                    return UNAUTHORIZED_MSG;
                else if (response.StatusCode == System.Net.HttpStatusCode.Forbidden)
                    return FORBIDDEN_MSG;
                else
                    return OTHER_4XX_MSG + (int)response.StatusCode;
            }
            return "";
        }

        public API_User Login(string submittedName, string submittedPass)
        {
            var credentials = new { username = submittedName, password = submittedPass };
            RestRequest request = new RestRequest(API_BASE_URL + "login");
            request.AddJsonBody(credentials);
            IRestResponse<API_User> response = client.Post<API_User>(request);

            if (response.ResponseStatus != ResponseStatus.Completed)
            {
                Console.WriteLine("An error occurred communicating with the server.");
                return null;
            }
            else if (!response.IsSuccessful)
            {
                if (!string.IsNullOrWhiteSpace(response.Data.Message))
                {
                    Console.WriteLine("An error message was received: " + response.Data.Message);
                }
                else
                {
                    Console.WriteLine("An error response was received from the server. The status code is " + (int)response.StatusCode);
                }
                return null;
            }
            else
            {
                user.Token = response.Data.Token;
                client.Authenticator = new JwtAuthenticator(user.Token);
                return response.Data;
            }
        }

        public void Logout()
        {
            user = new API_User();
            client.Authenticator = null;
        }
    }
}
