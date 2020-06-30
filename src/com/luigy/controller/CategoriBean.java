package com.luigy.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import com.luigy.controller.domain.Categori;
import com.luigy.repository.CategoriOperation;



@Named
@SessionScoped
public class CategoriBean implements Serializable {

	List<Categori> categoriList = new ArrayList<Categori>();

	private static final long serialVersionUID = 1L;

	public List<Categori> categoriList() {
		categoriList = CategoriOperation.getCategoris();
		return categoriList;
	}

	public String editCategoriRecord(int id) {
		Map<String, Object> sessionMapObj = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

		for (int i = 0; i < categoriList.size(); i++) {
			if (categoriList.get(i).getId() == id) {
				sessionMapObj.put("editRecordObj", categoriList.get(i));
			}

		}
		return "/editCategori.xhtml?faces-redirect=true";
	}

	public String updateCategoriDetails(Categori categoriEdit) {
		CategoriOperation.updateCategori(categoriEdit);
		return "/categoriList.xhtml?faces-redirect=true";
	}

	public String createCategoriRecord() {
		Map<String, Object> sessionMapObj = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

		Categori createNew = new Categori();
		createNew.setId(categoriList.size() + 1);

		sessionMapObj.put("editRecordObj", createNew);

		return "/createCategori.xhtml?faces-redirect=true";
	}

	public String saveCategoriDetails(Categori editRecordObj) {
		CategoriOperation.createCategori(editRecordObj);

		return "/categoriList.xhtml?faces-redirect=true";
	}

	public String deleteCategoriRecord(int id) {
		CategoriOperation.deleteCategori(id);
		return "/categoriList.xhtml?faces-redirect=true";
	}

}
