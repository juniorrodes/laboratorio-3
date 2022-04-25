package hr.commissioned;

import hr.EmployeeData;
import hr.exceptions.EmployeeCreationException;

public class CommissionedBaseSalaryEmployee extends CommissionedEmployee{

    private double salary;

    public CommissionedBaseSalaryEmployee(EmployeeData employeeData, double salary, double commission) throws EmployeeCreationException {
        super (employeeData, commission);
        if (salary < 0) {
            throw new EmployeeCreationException("Employee salary must be a positive number");
        }
        this.salary = salary;
    }

    @Override
    public double getIncome() {
        return salary + (this.sales * this.commission);
    }
}
