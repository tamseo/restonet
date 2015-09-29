package tamseo.domain;

import java.util.List;

public class Recipe {

	private int id;
	private List<String> ingredients;
	private String procedure;
	private Integer calories;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<String> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<String> ingredients) {
		this.ingredients = ingredients;
	}

	public String getProcedure() {
		return procedure;
	}

	public void setProcedure(String procedure) {
		this.procedure = procedure;
	}

	public Integer getCalories() {
		return calories;
	}

	public void setCalories(Integer calories) {
		this.calories = calories;
	}

}
