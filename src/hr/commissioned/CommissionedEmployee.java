package hr.commissioned;

import hr.Employee;
import hr.EmployeeData;
import hr.exceptions.EmployeeCreationException;

public class CommissionedEmployee implements Employee {

    private EmployeeData employeeData;
    protected double commission;
    protected double sales;

    public CommissionedEmployee (EmployeeData employeeData, double commission) throws EmployeeCreationException {
        if (commission < 0) {
            throw new EmployeeCreationException("Employee commission must be a positive number");
        }
        this.employeeData = employeeData;
        this.commission = commission;
        this.sales = 0;
    }

    @Override
    public double getIncome() {
        return this.sales * this.commission;
    }

    @Override
    public String getName() {
        return this.employeeData.Name();
    }

    @Override
    public String getCPF() {
        return this.employeeData.cpf();
    }

    public void addSales(double value) {
        this.sales += value;
    }
}
