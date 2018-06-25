package com.jerry.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jerry.crud.bean.Employee;
import com.jerry.crud.bean.EmployeeExample;
import com.jerry.crud.bean.EmployeeExample.Criteria;
import com.jerry.crud.dao.EmployeeMapper;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeMapper employeeMapper;

	public List<Employee> getAll(String gender, String queryCondition,Integer dId) {
		EmployeeExample employeeExample = new EmployeeExample();
		Criteria createCriteria = employeeExample.createCriteria();
		Criteria createCriteria2 = null;
		
		if(queryCondition != null && !queryCondition.equals("")) {
			createCriteria2 = employeeExample.createCriteria();
			createCriteria.andEmpNameLike("%"+queryCondition+"%");
			createCriteria2.andEmailLike("%"+queryCondition+"%");
		}
		
		if(gender != null && !gender.equals("")) {
			createCriteria.andGenderEqualTo(gender);
			if(createCriteria2 != null) {
				createCriteria2.andGenderEqualTo(gender);
			}
		}
		
		if(dId != null && dId != 0) {
			createCriteria.andDIdEqualTo(dId);
			if(createCriteria2 != null) {
				createCriteria2.andDIdEqualTo(dId);
			}
		}
		if(createCriteria2 != null) {
			employeeExample.or(createCriteria2);
		}
		employeeExample.setOrderByClause("emp_id");
		
		return employeeMapper.selectByExampleWithDept(employeeExample);
	}

	/**
	 * @param employee
	 */
	public void addEmp(Employee employee) {
		employeeMapper.insert(employee);
	}

	/**
	 * @param empName
	 * @return
	 */
	public boolean userNameCheck(String empName) {
		// TODO Auto-generated method stub
		EmployeeExample employeeExample = new EmployeeExample();
		employeeExample.createCriteria().andEmpNameEqualTo(empName);
		return employeeMapper.countByExample(employeeExample) == 0L;
	}
	


}
