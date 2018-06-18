package com.jerry.crud.test;

import java.util.Random;
import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jerry.crud.bean.Department;
import com.jerry.crud.bean.DepartmentExample;
import com.jerry.crud.bean.Employee;
import com.jerry.crud.dao.DepartmentMapper;
import com.jerry.crud.dao.EmployeeMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:applicationContext.xml"})
public class MapperTest {

	@Autowired
	EmployeeMapper employeeMapper;
	
	@Autowired
	DepartmentMapper departmentMapper;
	
	@Autowired
	SqlSession sqlSession;
	
	@Test
	public void testMapper() {
//		Department department = new Department();
//		department.setDeptName("开发部");
//		departmentMapper.insertSelective(department);
//		DepartmentExample departmentExample = new DepartmentExample();
//		departmentMapper.selectByExample()
//		Employee employee = new Employee();
//		employee.setEmpName("向博文");
//		employee.setGender("m");
//		employee.setEmail("jerrybw@163.com");
//		Employee employee = new Employee(null, "李四", "M", "lisi@163.com", 2);
//		employeeMapper.insertSelective(employee);
		EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
		Random random = new Random();
		for(int i = 0;i < 1000;i++) {
			String name = UUID.randomUUID().toString().substring(0,5)+i;
			mapper.insertSelective(new Employee(null,name,(random.nextInt(3) == 0)? "M":"F",name+"@163.com",random.nextInt(2)+2));
		}
		System.out.println("成功！");
	}
	
}
