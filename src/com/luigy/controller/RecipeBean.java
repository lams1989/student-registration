package com.luigy.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import com.luigy.controller.domain.Plate;
import com.luigy.repository.RecipeOperation;

@Named
@SessionScoped
public class RecipeBean implements Serializable {

	List<Plate> recipeList = new ArrayList<Plate>();

	private static final long serialVersionUID = 1L;

	public List<Plate> recipesList() {
		recipeList = RecipeOperation.getRecipes();
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

	public String updateRecipeDetails(Plate recipeEdit) {
		RecipeOperation.updateRecipe(recipeEdit);
		return "/recipesList.xhtml?faces-redirect=true";
	}

	public String createRecipeRecord() {
		Map<String, Object> sessionMapObj = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

		Plate createNew = new Plate();
		createNew.setCod(recipeList.size() + 1);

		sessionMapObj.put("editRecordObj", createNew);

		return "/createRecipe.xhtml?faces-redirect=true";
	}

	public String saveRecipeDetails(Plate editRecordObj) {
		RecipeOperation.createRecipe(editRecordObj);

		return "/recipesList.xhtml?faces-redirect=true";
	}

	public String deleteRecipeRecord(int recipeCod) {
		RecipeOperation.deleteRecipe(recipeCod);
		return "/recipesList.xhtml?faces-redirect=true";
	}

}
