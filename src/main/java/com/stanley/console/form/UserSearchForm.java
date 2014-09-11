package com.stanley.console.form;

import com.stanley.common.form.FieldCondition;
import com.stanley.common.form.Operation;
import com.stanley.common.form.SearchForm;

public class UserSearchForm  extends SearchForm{
	private String name;
	
	@FieldCondition(op=Operation.like)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/*@Override
	public String toString() {
		return "CallConfirmSearchForm [name like '" + name + "']";
	}*/
}
