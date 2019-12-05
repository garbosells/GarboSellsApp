package edu.asu.garbosells.API.Interfaces;

import edu.asu.garbosells.Item.PostListingRequest;

public interface IThirdPartyListingAPI {
    String PostListing(PostListingRequest request);
}
