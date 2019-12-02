package edu.asu.garbosells.API.Providers.Mocks;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import edu.asu.garbosells.API.Interfaces.ITemplateAPI;
import edu.asu.garbosells.API.Remote.RemoteTemplateAPI;
import edu.asu.garbosells.Template.Attribute;
import edu.asu.garbosells.Template.Category;
import edu.asu.garbosells.Template.Recommendation;
import edu.asu.garbosells.Template.Subcategory;
import edu.asu.garbosells.Template.Template;

public class MockTemplateProvider implements ITemplateAPI {
    private ITemplateAPI api = new RemoteTemplateAPI();

    ArrayList<Category> categories = null;
    ArrayList<Subcategory> subcategories = null;
    ArrayList<Template> templates = null;

    public MockTemplateProvider() {
        Category shirt = new Category();
        shirt.id=0;
        shirt.description="Shirts";
        shirt.hasMeasurements=true;
        shirt.hasSizing=true;

        Category hat = new Category();
        hat.id=1;
        hat.description="Hats";
        hat.hasSizing=false;
        hat.hasMeasurements=false;

        Category[] categoryArray = new Category[]{shirt, hat};
        categories = new ArrayList<>();
        categories.addAll(Arrays.asList(categoryArray));

        Attribute tshirtAttribute = new Attribute();
        tshirtAttribute.id=new Long(0);
        tshirtAttribute.description = "Tshirt attribute";
        tshirtAttribute.hintText="Hint for attribute";
        tshirtAttribute.displayText= tshirtAttribute.description;
        tshirtAttribute.isRequired=false;
        tshirtAttribute.uiInputId=0;
        tshirtAttribute.recommendations = new ArrayList<>();
        Recommendation[] tshirtRecommendationArray = new Recommendation[] {
            new Recommendation(0, "recommendation1"), new Recommendation(1, "recommendation2")
        };
        tshirtAttribute.recommendations.addAll(Arrays.asList(tshirtRecommendationArray));

        Attribute hatAttribute = new Attribute();
        hatAttribute.id=new Long(1);
        hatAttribute.description= "Hat attribute";
        hatAttribute.hintText="Hint for hat attribute";
        hatAttribute.displayText = hatAttribute.description;
        hatAttribute.isRequired=false;
        hatAttribute.uiInputId=1;


        Subcategory tshirt = new Subcategory();
        tshirt.id = 0;
        tshirt.description = "Men's Tee";
        tshirt.category=shirt;
        tshirt.attributes= Arrays.asList(new Attribute[] {tshirtAttribute});

        Subcategory tshirt2 = new Subcategory();
        tshirt2.id=3;
        tshirt2.description="Unisex tee";
        tshirt2.category=shirt;
        tshirt.attributes= Arrays.asList(new Attribute[] {tshirtAttribute});

        Subcategory truckerHat = new Subcategory();
        truckerHat.id = 1;
        truckerHat.description = "Trucker hat";
        truckerHat.category=hat;
        truckerHat.attributes= Arrays.asList(new Attribute[] {hatAttribute});

        Subcategory beanie = new Subcategory();
        beanie.id = 2;
        beanie.description="Beanie";
        beanie.category = hat;

        Subcategory[] subcategoryArray = new Subcategory[] {tshirt, tshirt2, truckerHat, beanie};
        subcategories = new ArrayList<>();
        subcategories.addAll(Arrays.asList(subcategoryArray));

        Type TemplateType = new TypeToken<Template>(){}.getType();
        Template shirtTemplate = new Gson().fromJson(getShirtJson(), TemplateType);
        Template hatTemplate = new Gson().fromJson(getHatJson(), TemplateType);
        Template[] templateArray = new Template[] {shirtTemplate, hatTemplate};
        templates = new ArrayList<>();
        templates.addAll(Arrays.asList(templateArray));
    }

    @Override
    public List<Category> GetCategories() {
        return categories;
    }

    @Override
    public List<Subcategory> GetSubcategoriesByCategoryId(long categoryId) {
        ArrayList<Subcategory> results = new ArrayList<>();

        subcategories.forEach(s -> {
            if(s.category.id == categoryId)
                results.add(s);
        });

        return results;
    }

    @Override
    public Template GetTemplateBySubcategoryId(long subcategoryId) {
        ArrayList<Template> results = new ArrayList<>();

        templates.forEach(t -> {
            if(t.subcategory.id == subcategoryId)
                results.add(t);
        });
        return results.get(0);
    }

    private String getShirtJson() {
        return "{\n" +
                "    \"category\": {\n" +
                "        \"id\": 0,\n" +
                "        \"description\": \"T-shirts\",\n" +
                "        \"hasSizing\": true,\n" +
                "        \"hasMeasurements\": true\n" +
                "    },\n" +
                "    \"subcategory\": {\n" +
                "        \"id\": 0,\n" +
                "        \"description\": \"Men's T-Shirts\",\n" +
                "        \"categoryId\": 0,\n" +
                "        \"category\": {\n" +
                "            \"id\": 0,\n" +
                "            \"description\": \"T-shirts\",\n" +
                "            \"hasSizing\": true,\n" +
                "            \"hasMeasurements\": true\n" +
                "        },\n" +
                "        \"attributes\": [\n" +
                "            {\n" +
                "                \"id\": 0,\n" +
                "                \"description\": \"T-shirt Neckline\",\n" +
                "                \"displayText\": \"Neckline\",\n" +
                "                \"hintText\": null,\n" +
                "                \"valueType\": \"string\",\n" +
                "                \"isRequired\": false,\n" +
                "                \"uiInputId\": 0,\n" +
                "                \"valueLimit\": null,\n" +
                "                \"recommendations\": [\n" +
                "                    {\n" +
                "                        \"id\": 0,\n" +
                "                        \"description\": \"Collared\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"id\": 1,\n" +
                "                        \"description\": \"Crew\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"id\": 2,\n" +
                "                        \"description\": \"Scoop\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"id\": 3,\n" +
                "                        \"description\": \"V-Neck\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"id\": 4,\n" +
                "                        \"description\": \"Henley\"\n" +
                "                    }\n" +
                "                ]\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    \"generalAttributes\": {\n" +
                "        \"primaryColor\": {\n" +
                "            \"id\": null,\n" +
                "            \"description\": \"Primary Color\",\n" +
                "            \"displayText\": \"Primary Color\",\n" +
                "            \"hintText\": null,\n" +
                "            \"valueType\": \"string\",\n" +
                "            \"isRequired\": false,\n" +
                "            \"uiInputId\": 0,\n" +
                "            \"valueLimit\": null,\n" +
                "            \"recommendations\": [\n" +
                "                {\n" +
                "                    \"id\": 0,\n" +
                "                    \"description\": \"Beige\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 1,\n" +
                "                    \"description\": \"Black\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 2,\n" +
                "                    \"description\": \"Blue\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 3,\n" +
                "                    \"description\": \"Brown\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 4,\n" +
                "                    \"description\": \"Gold\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 5,\n" +
                "                    \"description\": \"Gray\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 6,\n" +
                "                    \"description\": \"Green\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 7,\n" +
                "                    \"description\": \"Orange\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 8,\n" +
                "                    \"description\": \"Pink\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 9,\n" +
                "                    \"description\": \"Purple\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 10,\n" +
                "                    \"description\": \"Red\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 11,\n" +
                "                    \"description\": \"Silver\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 12,\n" +
                "                    \"description\": \"White\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 13,\n" +
                "                    \"description\": \"Yellow\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        \"secondaryColor\": {\n" +
                "            \"id\": null,\n" +
                "            \"description\": \"Secondary Color\",\n" +
                "            \"displayText\": \"Secondary Color\",\n" +
                "            \"hintText\": null,\n" +
                "            \"valueType\": \"string\",\n" +
                "            \"isRequired\": false,\n" +
                "            \"uiInputId\": 0,\n" +
                "            \"valueLimit\": null,\n" +
                "            \"recommendations\": [\n" +
                "                {\n" +
                "                    \"id\": 0,\n" +
                "                    \"description\": \"Beige\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 1,\n" +
                "                    \"description\": \"Black\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 2,\n" +
                "                    \"description\": \"Blue\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 3,\n" +
                "                    \"description\": \"Brown\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 4,\n" +
                "                    \"description\": \"Gold\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 5,\n" +
                "                    \"description\": \"Gray\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 6,\n" +
                "                    \"description\": \"Green\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 7,\n" +
                "                    \"description\": \"Orange\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 8,\n" +
                "                    \"description\": \"Pink\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 9,\n" +
                "                    \"description\": \"Purple\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 10,\n" +
                "                    \"description\": \"Red\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 11,\n" +
                "                    \"description\": \"Silver\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 12,\n" +
                "                    \"description\": \"White\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 13,\n" +
                "                    \"description\": \"Yellow\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        \"era\": {\n" +
                "            \"id\": null,\n" +
                "            \"description\": \"Era\",\n" +
                "            \"displayText\": \"Decade\",\n" +
                "            \"hintText\": null,\n" +
                "            \"valueType\": \"string\",\n" +
                "            \"isRequired\": false,\n" +
                "            \"uiInputId\": 0,\n" +
                "            \"valueLimit\": null,\n" +
                "            \"recommendations\": [\n" +
                "                {\n" +
                "                    \"id\": 0,\n" +
                "                    \"description\": \"Pre 1900\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 1,\n" +
                "                    \"description\": \"1900-1909\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 2,\n" +
                "                    \"description\": \"1910-1919\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 3,\n" +
                "                    \"description\": \"1920-1929\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 4,\n" +
                "                    \"description\": \"1930-1939\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 5,\n" +
                "                    \"description\": \"1940-1949\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 6,\n" +
                "                    \"description\": \"1950-1959\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 7,\n" +
                "                    \"description\": \"1960-1969\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 8,\n" +
                "                    \"description\": \"1970-1979\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 9,\n" +
                "                    \"description\": \"1980-1989\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 10,\n" +
                "                    \"description\": \"1990-1999\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 11,\n" +
                "                    \"description\": \"Before 2000\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 12,\n" +
                "                    \"description\": \"After 2000\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        \"material\": {\n" +
                "            \"id\": null,\n" +
                "            \"description\": \"Material\",\n" +
                "            \"displayText\": \"Material\",\n" +
                "            \"hintText\": null,\n" +
                "            \"valueType\": \"string\",\n" +
                "            \"isRequired\": false,\n" +
                "            \"uiInputId\": 0,\n" +
                "            \"valueLimit\": null,\n" +
                "            \"recommendations\": [\n" +
                "                {\n" +
                "                    \"id\": 0,\n" +
                "                    \"description\": \"100% Cotton\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 1,\n" +
                "                    \"description\": \"Cotton Blend\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 3,\n" +
                "                    \"description\": \"Synthetic\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 4,\n" +
                "                    \"description\": \"Silk\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 5,\n" +
                "                    \"description\": \"Leather\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 6,\n" +
                "                    \"description\": \"Wool\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 2,\n" +
                "                    \"description\": \"Polyester\"\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    },\n" +
                "    \"sizeOptions\": [\n" +
                "        {\n" +
                "            \"sizeTypeId\": 0,\n" +
                "            \"sizeTypeDescription\": \"US Numeric\",\n" +
                "            \"isNumeric\": true,\n" +
                "            \"recommendations\": [\n" +
                "                {\n" +
                "                    \"id\": 7,\n" +
                "                    \"description\": \"0\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 8,\n" +
                "                    \"description\": \"1\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 9,\n" +
                "                    \"description\": \"2\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 10,\n" +
                "                    \"description\": \"3\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 11,\n" +
                "                    \"description\": \"4\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 12,\n" +
                "                    \"description\": \"5\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 13,\n" +
                "                    \"description\": \"6\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 14,\n" +
                "                    \"description\": \"7\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 15,\n" +
                "                    \"description\": \"8\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 16,\n" +
                "                    \"description\": \"9\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 17,\n" +
                "                    \"description\": \"10\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 18,\n" +
                "                    \"description\": \"11\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 19,\n" +
                "                    \"description\": \"12\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 20,\n" +
                "                    \"description\": \"13\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 21,\n" +
                "                    \"description\": \"14\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 22,\n" +
                "                    \"description\": \"15\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 23,\n" +
                "                    \"description\": \"16\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 24,\n" +
                "                    \"description\": \"17\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 25,\n" +
                "                    \"description\": \"18\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 26,\n" +
                "                    \"description\": \"19\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 27,\n" +
                "                    \"description\": \"20\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 28,\n" +
                "                    \"description\": \"21\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 29,\n" +
                "                    \"description\": \"22\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 31,\n" +
                "                    \"description\": \"24\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 33,\n" +
                "                    \"description\": \"26\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 35,\n" +
                "                    \"description\": \"28\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 37,\n" +
                "                    \"description\": \"30\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"sizeTypeId\": 1,\n" +
                "            \"sizeTypeDescription\": \"US Letter\",\n" +
                "            \"isNumeric\": false,\n" +
                "            \"recommendations\": [\n" +
                "                {\n" +
                "                    \"id\": 0,\n" +
                "                    \"description\": \"XS\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 1,\n" +
                "                    \"description\": \"S\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 2,\n" +
                "                    \"description\": \"M\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 3,\n" +
                "                    \"description\": \"L\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 4,\n" +
                "                    \"description\": \"XL\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 5,\n" +
                "                    \"description\": \"2XL\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 6,\n" +
                "                    \"description\": \"3XL\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"sizeTypeId\": 2,\n" +
                "            \"sizeTypeDescription\": \"UK\",\n" +
                "            \"isNumeric\": true,\n" +
                "            \"recommendations\": [\n" +
                "                {\n" +
                "                    \"id\": 7,\n" +
                "                    \"description\": \"0\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 8,\n" +
                "                    \"description\": \"1\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 9,\n" +
                "                    \"description\": \"2\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 10,\n" +
                "                    \"description\": \"3\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 11,\n" +
                "                    \"description\": \"4\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 12,\n" +
                "                    \"description\": \"5\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 13,\n" +
                "                    \"description\": \"6\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 14,\n" +
                "                    \"description\": \"7\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 15,\n" +
                "                    \"description\": \"8\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 16,\n" +
                "                    \"description\": \"9\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 17,\n" +
                "                    \"description\": \"10\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 18,\n" +
                "                    \"description\": \"11\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 19,\n" +
                "                    \"description\": \"12\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 20,\n" +
                "                    \"description\": \"13\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 21,\n" +
                "                    \"description\": \"14\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 22,\n" +
                "                    \"description\": \"15\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 23,\n" +
                "                    \"description\": \"16\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 24,\n" +
                "                    \"description\": \"17\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 25,\n" +
                "                    \"description\": \"18\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 26,\n" +
                "                    \"description\": \"19\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 27,\n" +
                "                    \"description\": \"20\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 28,\n" +
                "                    \"description\": \"21\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 29,\n" +
                "                    \"description\": \"22\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 31,\n" +
                "                    \"description\": \"24\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 33,\n" +
                "                    \"description\": \"26\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 35,\n" +
                "                    \"description\": \"28\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 37,\n" +
                "                    \"description\": \"30\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"sizeTypeId\": 3,\n" +
                "            \"sizeTypeDescription\": \"One Size\",\n" +
                "            \"isNumeric\": false,\n" +
                "            \"recommendations\": []\n" +
                "        }\n" +
                "    ],\n" +
                "    \"measurements\": [\n" +
                "        {\n" +
                "            \"id\": 0,\n" +
                "            \"description\": \"Length\",\n" +
                "            \"hint\": \"back collar to end of shirt tail\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 1,\n" +
                "            \"description\": \"Chest\",\n" +
                "            \"hint\": \"across arm pit to arm pit\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 2,\n" +
                "            \"description\": \"Shoulders\",\n" +
                "            \"hint\": \"measure across back between sleeve seams\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 3,\n" +
                "            \"description\": \"Sleeves\",\n" +
                "            \"hint\": \"from middle of neck to end of cuff\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";
    }

    private String getHatJson() {
        return "{\n" +
                "    \"category\": {\n" +
                "        \"id\": 1,\n" +
                "        \"description\": \"Hats\",\n" +
                "        \"hasSizing\": false,\n" +
                "        \"hasMeasurements\": false\n" +
                "    },\n" +
                "    \"subcategory\": {\n" +
                "        \"id\": 3,\n" +
                "        \"description\": \"Trucker Hats\",\n" +
                "        \"categoryId\": 1,\n" +
                "        \"category\": {\n" +
                "            \"id\": 1,\n" +
                "            \"description\": \"Hats\",\n" +
                "            \"hasSizing\": false,\n" +
                "            \"hasMeasurements\": false\n" +
                "        },\n" +
                "        \"attributes\": [\n" +
                "            {\n" +
                "                \"id\": 1,\n" +
                "                \"description\": \"Is adjustable\",\n" +
                "                \"displayText\": \"Adjustable\",\n" +
                "                \"hintText\": null,\n" +
                "                \"valueType\": \"bool\",\n" +
                "                \"isRequired\": false,\n" +
                "                \"uiInputId\": 5,\n" +
                "                \"valueLimit\": null,\n" +
                "                \"recommendations\": []\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    \"generalAttributes\": {\n" +
                "        \"primaryColor\": {\n" +
                "            \"id\": null,\n" +
                "            \"description\": \"Primary Color\",\n" +
                "            \"displayText\": \"Primary Color\",\n" +
                "            \"hintText\": null,\n" +
                "            \"valueType\": \"string\",\n" +
                "            \"isRequired\": false,\n" +
                "            \"uiInputId\": 0,\n" +
                "            \"valueLimit\": null,\n" +
                "            \"recommendations\": [\n" +
                "                {\n" +
                "                    \"id\": 0,\n" +
                "                    \"description\": \"Beige\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 1,\n" +
                "                    \"description\": \"Black\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 2,\n" +
                "                    \"description\": \"Blue\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 3,\n" +
                "                    \"description\": \"Brown\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 4,\n" +
                "                    \"description\": \"Gold\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 5,\n" +
                "                    \"description\": \"Gray\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 6,\n" +
                "                    \"description\": \"Green\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 7,\n" +
                "                    \"description\": \"Orange\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 8,\n" +
                "                    \"description\": \"Pink\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 9,\n" +
                "                    \"description\": \"Purple\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 10,\n" +
                "                    \"description\": \"Red\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 11,\n" +
                "                    \"description\": \"Silver\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 12,\n" +
                "                    \"description\": \"White\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 13,\n" +
                "                    \"description\": \"Yellow\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        \"secondaryColor\": {\n" +
                "            \"id\": null,\n" +
                "            \"description\": \"Secondary Color\",\n" +
                "            \"displayText\": \"Secondary Color\",\n" +
                "            \"hintText\": null,\n" +
                "            \"valueType\": \"string\",\n" +
                "            \"isRequired\": false,\n" +
                "            \"uiInputId\": 0,\n" +
                "            \"valueLimit\": null,\n" +
                "            \"recommendations\": [\n" +
                "                {\n" +
                "                    \"id\": 0,\n" +
                "                    \"description\": \"Beige\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 1,\n" +
                "                    \"description\": \"Black\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 2,\n" +
                "                    \"description\": \"Blue\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 3,\n" +
                "                    \"description\": \"Brown\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 4,\n" +
                "                    \"description\": \"Gold\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 5,\n" +
                "                    \"description\": \"Gray\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 6,\n" +
                "                    \"description\": \"Green\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 7,\n" +
                "                    \"description\": \"Orange\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 8,\n" +
                "                    \"description\": \"Pink\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 9,\n" +
                "                    \"description\": \"Purple\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 10,\n" +
                "                    \"description\": \"Red\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 11,\n" +
                "                    \"description\": \"Silver\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 12,\n" +
                "                    \"description\": \"White\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 13,\n" +
                "                    \"description\": \"Yellow\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        \"era\": {\n" +
                "            \"id\": null,\n" +
                "            \"description\": \"Era\",\n" +
                "            \"displayText\": \"Decade\",\n" +
                "            \"hintText\": null,\n" +
                "            \"valueType\": \"string\",\n" +
                "            \"isRequired\": false,\n" +
                "            \"uiInputId\": 0,\n" +
                "            \"valueLimit\": null,\n" +
                "            \"recommendations\": [\n" +
                "                {\n" +
                "                    \"id\": 0,\n" +
                "                    \"description\": \"Pre 1900\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 1,\n" +
                "                    \"description\": \"1900-1909\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 2,\n" +
                "                    \"description\": \"1910-1919\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 3,\n" +
                "                    \"description\": \"1920-1929\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 4,\n" +
                "                    \"description\": \"1930-1939\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 5,\n" +
                "                    \"description\": \"1940-1949\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 6,\n" +
                "                    \"description\": \"1950-1959\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 7,\n" +
                "                    \"description\": \"1960-1969\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 8,\n" +
                "                    \"description\": \"1970-1979\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 9,\n" +
                "                    \"description\": \"1980-1989\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 10,\n" +
                "                    \"description\": \"1990-1999\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 11,\n" +
                "                    \"description\": \"Before 2000\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 12,\n" +
                "                    \"description\": \"After 2000\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        \"material\": {\n" +
                "            \"id\": null,\n" +
                "            \"description\": \"Material\",\n" +
                "            \"displayText\": \"Material\",\n" +
                "            \"hintText\": null,\n" +
                "            \"valueType\": \"string\",\n" +
                "            \"isRequired\": false,\n" +
                "            \"uiInputId\": 0,\n" +
                "            \"valueLimit\": null,\n" +
                "            \"recommendations\": [\n" +
                "                {\n" +
                "                    \"id\": 0,\n" +
                "                    \"description\": \"100% Cotton\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 1,\n" +
                "                    \"description\": \"Cotton Blend\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 3,\n" +
                "                    \"description\": \"Synthetic\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 4,\n" +
                "                    \"description\": \"Silk\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 5,\n" +
                "                    \"description\": \"Leather\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 6,\n" +
                "                    \"description\": \"Wool\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 2,\n" +
                "                    \"description\": \"Polyester\"\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    },\n" +
                "    \"sizeOptions\": [\n" +
                "        {\n" +
                "            \"sizeTypeId\": 0,\n" +
                "            \"sizeTypeDescription\": \"US Numeric\",\n" +
                "            \"isNumeric\": true,\n" +
                "            \"recommendations\": [\n" +
                "                {\n" +
                "                    \"id\": 7,\n" +
                "                    \"description\": \"0\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 8,\n" +
                "                    \"description\": \"1\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 9,\n" +
                "                    \"description\": \"2\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 10,\n" +
                "                    \"description\": \"3\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 11,\n" +
                "                    \"description\": \"4\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 12,\n" +
                "                    \"description\": \"5\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 13,\n" +
                "                    \"description\": \"6\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 14,\n" +
                "                    \"description\": \"7\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 15,\n" +
                "                    \"description\": \"8\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 16,\n" +
                "                    \"description\": \"9\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 17,\n" +
                "                    \"description\": \"10\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 18,\n" +
                "                    \"description\": \"11\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 19,\n" +
                "                    \"description\": \"12\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 20,\n" +
                "                    \"description\": \"13\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 21,\n" +
                "                    \"description\": \"14\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 22,\n" +
                "                    \"description\": \"15\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 23,\n" +
                "                    \"description\": \"16\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 24,\n" +
                "                    \"description\": \"17\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 25,\n" +
                "                    \"description\": \"18\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 26,\n" +
                "                    \"description\": \"19\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 27,\n" +
                "                    \"description\": \"20\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 28,\n" +
                "                    \"description\": \"21\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 29,\n" +
                "                    \"description\": \"22\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 31,\n" +
                "                    \"description\": \"24\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 33,\n" +
                "                    \"description\": \"26\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 35,\n" +
                "                    \"description\": \"28\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 37,\n" +
                "                    \"description\": \"30\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"sizeTypeId\": 1,\n" +
                "            \"sizeTypeDescription\": \"US Letter\",\n" +
                "            \"isNumeric\": false,\n" +
                "            \"recommendations\": [\n" +
                "                {\n" +
                "                    \"id\": 0,\n" +
                "                    \"description\": \"XS\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 1,\n" +
                "                    \"description\": \"S\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 2,\n" +
                "                    \"description\": \"M\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 3,\n" +
                "                    \"description\": \"L\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 4,\n" +
                "                    \"description\": \"XL\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 5,\n" +
                "                    \"description\": \"2XL\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 6,\n" +
                "                    \"description\": \"3XL\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"sizeTypeId\": 2,\n" +
                "            \"sizeTypeDescription\": \"UK\",\n" +
                "            \"isNumeric\": true,\n" +
                "            \"recommendations\": [\n" +
                "                {\n" +
                "                    \"id\": 7,\n" +
                "                    \"description\": \"0\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 8,\n" +
                "                    \"description\": \"1\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 9,\n" +
                "                    \"description\": \"2\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 10,\n" +
                "                    \"description\": \"3\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 11,\n" +
                "                    \"description\": \"4\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 12,\n" +
                "                    \"description\": \"5\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 13,\n" +
                "                    \"description\": \"6\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 14,\n" +
                "                    \"description\": \"7\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 15,\n" +
                "                    \"description\": \"8\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 16,\n" +
                "                    \"description\": \"9\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 17,\n" +
                "                    \"description\": \"10\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 18,\n" +
                "                    \"description\": \"11\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 19,\n" +
                "                    \"description\": \"12\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 20,\n" +
                "                    \"description\": \"13\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 21,\n" +
                "                    \"description\": \"14\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 22,\n" +
                "                    \"description\": \"15\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 23,\n" +
                "                    \"description\": \"16\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 24,\n" +
                "                    \"description\": \"17\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 25,\n" +
                "                    \"description\": \"18\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 26,\n" +
                "                    \"description\": \"19\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 27,\n" +
                "                    \"description\": \"20\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 28,\n" +
                "                    \"description\": \"21\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 29,\n" +
                "                    \"description\": \"22\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 31,\n" +
                "                    \"description\": \"24\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 33,\n" +
                "                    \"description\": \"26\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 35,\n" +
                "                    \"description\": \"28\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 37,\n" +
                "                    \"description\": \"30\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"sizeTypeId\": 3,\n" +
                "            \"sizeTypeDescription\": \"One Size\",\n" +
                "            \"isNumeric\": false,\n" +
                "            \"recommendations\": []\n" +
                "        }\n" +
                "    ],\n" +
                "    \"measurements\": []\n" +
                "}";
    }
}
