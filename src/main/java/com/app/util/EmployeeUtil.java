package com.app.util;

import com.app.model.Employee;

public interface EmployeeUtil {
	public static void mapNotNullValues(Employee source, Employee employee) {
		if(employee!=null ) {
			
			if(employee.geteName()!=null) source.seteName(employee.geteName());
			if(employee.geteSal()!=null) source.seteSal(employee.geteSal());
			
			
		}
	}
}
