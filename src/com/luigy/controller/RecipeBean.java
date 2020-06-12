package com.luigy.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@SessionScoped
public class RecipeBean implements Serializable  {

	List<Recipe> recipeList = new ArrayList<Recipe>();
	
	private static final long serialVersionUID = 1L;

	public List<Recipe> recipesList() {

		return recipeList;
	}
	
	public String editRecipeRecord(int recipeCod) {
		Map<String, Object> sessionMapObj = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

		for (int i = 0; i < recipeList.size(); i++) {
			if (recipeList.get(i).getCod() == recipeCod) {
				sessionMapObj.put("editRecordObj", recipeList.get(i));
			}
			
		}
		return "/editRecipe.xhtml?faces-redirect=true";
	}
	
	public String updateRecipeDetails(Recipe recipeedit) {
		for (int i = 0 ; i < recipeList.size(); i++) {
			if(recipeList.get(i).getCod() == recipeedit.getCod()) {
				recipeList.get(i).setRecipename(recipeedit.getRecipename());
				recipeList.get(i).setIngredients(recipeedit.getIngredients());
				recipeList.get(i).setProcess(recipeedit.getProcess());
				recipeList.get(i).setPlatedtype(recipeedit.getPlatedtype());
			}
		}
		return "/recipesList.xhtml?faces-redirect=true";
	}
	
public String deleteRecipeRecord(int recipeCod) {
		
		for (int i = 0; i < recipeList.size(); i++) {
			if (recipeList.get(i).getCod() == recipeCod) {
				recipeList.remove(i);
			}
		}
		return "/recipesList.xhtml?faces-redirect=true";
	}

public String createRecipeRecord() {
	Map<String, Object> sessionMapObj = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
	
	Recipe createNew = new Recipe();
	createNew.setCod(recipeList.size() + 1);
	
	sessionMapObj.put("editRecordObj", createNew);
	
	return "/createRecipe.xhtml?faces-redirect=true";
}

public String saveRecipeDetails(Recipe editRecordObj) {
	recipeList.add(editRecordObj);
	
	return "/recipesList.xhtml?faces-redirect=true";
}

	@PostConstruct
	public void init() {
		Recipe recipe1 = new Recipe(1, "Ramen", "miso, cerdo, huevo, tofu, caldo", "armado", "plato hondo");
		Recipe recipe2 = new Recipe(2, "lomo saltado", "lomo, cebolla, champignon, soja, ostion", "salteado", "plato plano");
		
		recipeList.add(recipe1);
		recipeList.add(recipe2);
		
	}
	
	
}
