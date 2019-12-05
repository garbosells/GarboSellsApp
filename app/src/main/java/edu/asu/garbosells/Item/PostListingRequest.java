package edu.asu.garbosells.Item;

public class PostListingRequest {
    public boolean postToEbay;
    public boolean postToEtsy;
    public Listing listing;

    public PostListingRequest(boolean postToEbay, boolean postToEtsy, Listing listing) {
        this.postToEbay = postToEbay;
        this.postToEtsy = postToEtsy;
        this.listing = listing;
    }

    public PostListingRequest(){}
}
