package edu.asu.garbosells.API.Interfaces;

import edu.asu.garbosells.Core.Activity.AddItem.InputWizardActivity;
import edu.asu.garbosells.Item.PostListingRequest;

public interface IThirdPartyListingAPI {
    String PostListing(PostListingRequest request, InputWizardActivity activity);
}
