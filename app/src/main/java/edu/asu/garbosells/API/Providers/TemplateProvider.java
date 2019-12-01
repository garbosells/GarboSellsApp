package edu.asu.garbosells.API.Providers;

import java.util.List;

import edu.asu.garbosells.API.Interfaces.ITemplateAPI;
import edu.asu.garbosells.API.Providers.Mocks.MockTemplateProvider;
import edu.asu.garbosells.API.Remote.RemoteTemplateAPI;
import edu.asu.garbosells.Template.Category;
import edu.asu.garbosells.Template.Subcategory;
import edu.asu.garbosells.Template.Template;

public class TemplateProvider implements ITemplateAPI {
    private ITemplateAPI api = new MockTemplateProvider(); //new RemoteTemplateAPI();


    @Override
    public List<Category> GetCategories() {
        return api.GetCategories();
    }

    @Override
    public List<Subcategory> GetSubcategoriesByCategoryId(long categoryId) {
        return api.GetSubcategoriesByCategoryId(categoryId);
    }

    @Override
    public Template GetTemplateBySubcategoryId(long subcategoryId) {
        return api.GetTemplateBySubcategoryId(subcategoryId);
    }
}
