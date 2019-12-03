package edu.asu.garbosells.API.Interfaces;

import java.util.List;

import edu.asu.garbosells.API.Remote.RemoteTemplateAPI;
import edu.asu.garbosells.Template.Category;
import edu.asu.garbosells.Template.Subcategory;
import edu.asu.garbosells.Template.Template;

public interface ITemplateAPI {
     List<Category> GetCategories();
     List<Subcategory> GetSubcategoriesByCategoryId(long categoryId);
     Template GetTemplateBySubcategoryId(long subcategoryId);
}
