package edu.asu.garbosells.Core.Adapter;

public class PostResponse {
    public SiteResponse postEbayListingResponse;
    public SiteResponse postEtsyListingResponse;

    public class SiteResponse {
        public String listingId;
        public Boolean isSuccess;
        public String errorMessage;

        public String getListingId() {
            return listingId;
        }
    }
    public Boolean isSuccess;
    public String errorMessage;
}