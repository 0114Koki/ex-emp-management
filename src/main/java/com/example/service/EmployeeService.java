package com.example.service;

import com.example.domain.Employee;
import com.example.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 従業員情報を操作するService
 *
 * @author koki.kurihara
 */
@Service
@Transactional
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * 全従業員情報を取得する.
     *
     * @return 従業員一覧情報
     */
    public List<Employee> showList(){
        return employeeRepository.findAll();
    }

    /**
     * 従業員情報を取得する
     *
     * @param id ID
     * @return 従業員情報
     */
    public Employee showDetail(Integer id){
        return employeeRepository.findById(id);
    }

    public void update(Employee employee){
        employeeRepository.update(employee);
    }
}
