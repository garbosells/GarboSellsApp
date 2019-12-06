package edu.asu.garbosells.Core.Adapter;

public class PostResponse {
    public SiteResponse postEbayListingResponse;
    public SiteResponse postEtsyListingResponse;

    public class SiteResponse {
        public long listingId;
        public boolean isSuccess;
        public String errorMessage;

        public long getListingId() {
            return listingId;
        }

        public String getlistingId() {
            return Long.toString(listingId);
        }
    }
}