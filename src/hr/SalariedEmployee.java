package hr;

import hr.exceptions.EmployeeCreationException;

public class SalariedEmployee implements Employee {

    private EmployeeData employeeData;
    private double weeklySalary;

    public SalariedEmployee(EmployeeData employeeData, double weeklySalary) throws EmployeeCreationException {
        if (weeklySalary < 0) {
            throw new EmployeeCreationException("Weekly salary must be a positive number");
        }
        this.employeeData = employeeData;
        this.weeklySalary = weeklySalary;
    }

    @Override
    public double getIncome() {
        return this.weeklySalary;
    }

    @Override
    public String getName() {
        return this.employeeData.Name();
    }

    @Override
    public String getCPF() {
        return this.employeeData.cpf();
    }
}
