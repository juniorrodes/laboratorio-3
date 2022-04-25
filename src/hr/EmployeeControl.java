package hr;

import hr.commissioned.CommissionedEmployee;

import java.util.Locale;

public class EmployeeControl {

    private Employee[] employees;
    private int employeesCount;
    private int employeesLen;

    public EmployeeControl(int employeesCount) {
        this.employeesLen = 0;
        this.employeesCount = employeesCount;
        employees = new Employee[employeesCount];
    }

    private boolean isInsertPossible() {
        return employeesLen > employeesCount;
    }

    public boolean addEmployee(Employee employee) {
        if (isInsertPossible()) {
            return false;
        }
        this.employees[employeesLen++] = employee;
        return true;
    }

    public boolean addSale(double value, String name) {
        CommissionedEmployee commissionedEmployee = checkIfInstanceOfCommissionedEmployee(name);
        if (commissionedEmployee == null) {
            return false;
        }

        commissionedEmployee.addSales(value);
        return true;
    }

    private CommissionedEmployee checkIfInstanceOfCommissionedEmployee(String name) {
        for(int i  = 0; i < employeesLen; i++) {
            if (this.employees[i].getName().equalsIgnoreCase(name)) {
                return this.employees[i] instanceof CommissionedEmployee ?
                        ((CommissionedEmployee) this.employees[i]) : null;
            }
        }
        return null;
    }

    public boolean addHours(double value, String name) {
        HourlyEmployee hourlyEmployee = checkIfInstanceOfHourlyEmployee(name);
        if (hourlyEmployee == null) {
            return false;
        }

        hourlyEmployee.addHours(value);
        return true;
    }

    private HourlyEmployee checkIfInstanceOfHourlyEmployee(String name) {
        for(int i  = 0; i < employeesLen; i++) {
            if (this.employees[i].getName().equalsIgnoreCase(name)) {
                return this.employees[i] instanceof HourlyEmployee ?
                        ((HourlyEmployee) this.employees[i]) : null;
            }
        }
        return null;
    }

    public double getTotalIncome() {
        double totalIncome = 0;
        for (int i = 0; i < employeesLen; i++) {
            totalIncome += employees[i].getIncome();
        }
        return totalIncome;
    }

    public Employee[] getEmployees() {
        return employees;
    }

    public int getEmployeesLen() {
        return employeesLen;
    }
}
