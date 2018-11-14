package com.leoguilbor.generic;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import org.json.JSONException;
import org.json.JSONStringer;

public class GenericModelImpl implements GenericModel {

	
	public GenericModelImpl() {

	}

	public String toJson() throws JSONException, NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		JSONStringer json = new JSONStringer();
		
		json.object();
		for (Field field : this.getClass().getDeclaredFields()) {
			Object obj = new Object();

			System.out.println(obj);

			

			
			if (field.getType().getGenericSuperclass().getTypeName()=="com.leoguilbor.generic.GenericModelImpl") {
				
				System.out.println(field.getType());
				System.out.println("get"+field.getName().substring(0,1).toUpperCase()+field.getName().substring(1));
				GenericModelImpl ge = (GenericModelImpl) this.getClass().getMethod(
						"get"+field.getName().substring(0,1).toUpperCase()+field.getName().substring(1)
						)
						.invoke(this, null);
				System.out.println(ge.getClass().getMethod("toJson").invoke(ge, null));
				System.out.println(this.getClass().getMethod(
						"get"+field.getName().substring(0,1).toUpperCase()+field.getName().substring(1)
					)
					.invoke(this, null).
					
					getClass().getMethod("toJson")
						.invoke(ge,null));
				json.
				key(field.getName()).
				value(
						(this.getClass().getMethod(
								"get"+field.getName().substring(0,1).toUpperCase()+field.getName().substring(1)
							).invoke(this, null)).
								getClass().getMethod("toJson")
									.invoke(this.getClass().getMethod(
											"get"+field.getName().substring(0,1).toUpperCase()+field.getName().substring(1)
											)
											.invoke(this, null), null)
					).toString();
				
			}else {
			json.
				key(field.getName()).
				value(
					this.getClass().getMethod(
								"get"+field.getName().substring(0,1).toUpperCase()+field.getName().substring(1)
							)
							.invoke(this, null)
					).toString();
			}
		}
		json.endObject();
		
		
		return json.toString();
		
	}
}
