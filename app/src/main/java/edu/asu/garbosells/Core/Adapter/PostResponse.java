package edu.asu.garbosells.Core.Adapter;

public class PostResponse {
    public boolean isSuccess;
    public String errorMessage;

    public boolean postToEbay;
    public boolean postToEtsy;

    //Ebay Stuff
    public long EbaylistingId;
    public boolean EbayisSuccess;
    public String EbayerrorMessage;

    //Etsy Stuff
    public long EtsylistingId;
    public boolean EtsyisSuccess;
    public String EtsyerrorMessage;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public boolean isPostToEbay() {
        return postToEbay;
    }

    public void setPostToEbay(boolean postToEbay) {
        this.postToEbay = postToEbay;
    }

    public boolean isPostToEtsy() {
        return postToEtsy;
    }

    public void setPostToEtsy(boolean postToEtsy) {
        this.postToEtsy = postToEtsy;
    }

    public long getEbaylistingId() {
        return EbaylistingId;
    }

    public void setEbaylistingId(long ebaylistingId) {
        EbaylistingId = ebaylistingId;
    }

    public boolean isEbayisSuccess() {
        return EbayisSuccess;
    }

    public void setEbayisSuccess(boolean ebayisSuccess) {
        EbayisSuccess = ebayisSuccess;
    }

    public String getEbayerrorMessage() {
        return EbayerrorMessage;
    }

    public void setEbayerrorMessage(String ebayerrorMessage) {
        EbayerrorMessage = ebayerrorMessage;
    }

    public long getEtsylistingId() {
        return EtsylistingId;
    }

    public void setEtsylistingId(long etsylistingId) {
        EtsylistingId = etsylistingId;
    }

    public boolean isEtsyisSuccess() {
        return EtsyisSuccess;
    }

    public void setEtsyisSuccess(boolean etsyisSuccess) {
        EtsyisSuccess = etsyisSuccess;
    }

    public String getEtsyerrorMessage() {
        return EtsyerrorMessage;
    }

    public void setEtsyerrorMessage(String etsyerrorMessage) {
        EtsyerrorMessage = etsyerrorMessage;
    }
}
