package hr;

import hr.exceptions.EmployeeCreationException;

public class HourlyEmployee implements Employee {

    private EmployeeData employeeData;
    private double hourlySalary;
    private double workedHours;

    private static final double BONUS = 1.5;
    private static final double WEEK_HOURS = 40;

    public HourlyEmployee(EmployeeData employeeData, double hourlySalary) throws EmployeeCreationException {
        if (hourlySalary < 0) {
            throw new EmployeeCreationException("Hourly salary must be a positive number");
        }
        this.employeeData = employeeData;
        this.hourlySalary = hourlySalary;
        this.workedHours = 0;
    }

    @Override
    public double getIncome() {
        if (this.workedHours < 40) {
            return workedHours * hourlySalary;
        } else {
            return WEEK_HOURS * hourlySalary +
                    ((workedHours - WEEK_HOURS) * hourlySalary * BONUS);
        }
    }

    public void addHours(double hours) {
        this.workedHours += hours;
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
