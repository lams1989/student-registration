package com.luigy.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;

import com.luigy.controller.domain.Categori;
import com.luigy.controller.domain.Plates;
import com.luigy.repository.CategoriOperation;
import com.luigy.repository.PlatesOperation;

@Named
@SessionScoped
public class PlatesBean implements Serializable {

	List<Plates> platesList = new ArrayList<Plates>();

	private static final long serialVersionUID = 1L;

	public List<Plates> platesList() {
		platesList = PlatesOperation.getPlates();
		return platesList;
	}

	public List<SelectItem> getCategories() {
		List<Categori> categorias = CategoriOperation.getCategoris();
		List<SelectItem> selectItems = new ArrayList<SelectItem>();
		
		for (Categori categoria : categorias) {
			selectItems.add(new SelectItem(categoria.getId(), categoria.getCategoriname()));

		}
		return selectItems;
	}

	public String editPlatesRecord(int idplate) {
		Map<String, Object> sessionMapObj = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

		for (int i = 0; i < platesList.size(); i++) {
			if (platesList.get(i).getId() == idplate) {
				sessionMapObj.put("editRecordObj", platesList.get(i));
			}

		}
		return "/editPlates.xhtml?faces-redirect=true";
	}

	public String updatePlatesDetails(Plates plateEdit) {
		PlatesOperation.updatePlates(plateEdit);
		return "/platesList.xhtml?faces-redirect=true";
	}

	public String createPlatesRecord() {
		Map<String, Object> sessionMapObj = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

		Plates createNew = new Plates();
		createNew.setId(platesList.size() + 1);

		sessionMapObj.put("editRecordObj", createNew);

		return "/createPlates.xhtml?faces-redirect=true";
	}

	public String savePlatesDetails(Plates editRecordObj) {
		PlatesOperation.createPlates(editRecordObj);

		return "/platesList.xhtml?faces-redirect=true";
	}

	public String deletePlatesRecord(int idPlates) {
		PlatesOperation.deletePlates(idPlates);
		return "/platesList.xhtml?faces-redirect=true";
	}

}
