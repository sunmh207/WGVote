package com.jitong.console.form;

import com.jitong.common.form.FieldCondition;
import com.jitong.common.form.Operation;
import com.jitong.common.form.SearchForm;

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
