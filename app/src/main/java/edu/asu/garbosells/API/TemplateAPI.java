package edu.asu.garbosells.API;

import java.util.List;

import edu.asu.garbosells.API.Interfaces.ITemplateAPI;
import edu.asu.garbosells.API.Providers.Mocks.MockTemplateProvider;
import edu.asu.garbosells.Template.Category;
import edu.asu.garbosells.Template.Subcategory;
import edu.asu.garbosells.Template.Template;

public class TemplateAPI implements ITemplateAPI {
    private ITemplateAPI templateProvider = new MockTemplateProvider();

    @Override
    public List<Category> GetCategories() {
        return templateProvider.GetCategories();
    }

    @Override
    public List<Subcategory> GetSubcategoriesByCategoryId(long categoryId) {
        return templateProvider.GetSubcategoriesByCategoryId(categoryId);
    }

    @Override
    public Template GetTemplateBySubcategoryId(long subcategoryId) {
        return templateProvider.GetTemplateBySubcategoryId(subcategoryId);
    }
}
