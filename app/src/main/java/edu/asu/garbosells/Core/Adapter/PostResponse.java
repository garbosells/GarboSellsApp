package edu.asu.garbosells.Core.Adapter;

public class PostResponse {
    public SiteResponse EBay;
    public SiteResponse Etsy;

    class SiteResponse {
        public long listingId;
        public boolean isSuccess;
        public String errorMessage;
    }
}