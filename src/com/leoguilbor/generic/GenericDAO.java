/**
 *     This is an generic interface for DAO allowing decorator design pattern.
 *     Copyright (C) 2018 Leandro Lima
 * 
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 * 
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 * 
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.leoguilbor.generic;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface GenericDAO<T> {

	public Long save(T t);
	public Boolean update(T t);
	public Boolean delete(T t);
	public Boolean delete(Long id);
	public List<T> list();
	public List<T> list(String criteria);
	public T getById(Long id);
	public String listAsJson(String criteria) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException;
	public String listToJson(List<T> listByName) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException;
	
}

