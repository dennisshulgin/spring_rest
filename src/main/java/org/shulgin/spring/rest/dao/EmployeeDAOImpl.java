package org.shulgin.spring.rest.dao;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.shulgin.spring.rest.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public List<Employee> getAllEmployees() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Employee", Employee.class)
                .getResultList();
    }

    @Override
    @Transactional
    public void saveEmployee(Employee employee) {
        sessionFactory.getCurrentSession()
                .saveOrUpdate(employee);
    }

    @Override
    @Transactional
    public Employee getEmployeeById(int id) {
        return sessionFactory.getCurrentSession()
                .get(Employee.class, id);
    }

    @Override
    @Transactional
    public void deleteEmployee(int id) {
        String query = "delete from Employee where id=:employeeId";
        Query<Employee> deleteQuery = sessionFactory.getCurrentSession()
                .createQuery(query)
                .setParameter("employeeId", id);
        deleteQuery.executeUpdate();
    }
}
