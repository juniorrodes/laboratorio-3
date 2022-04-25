package utils;

import hr.*;
import hr.commissioned.CommissionedBaseSalaryEmployee;
import hr.commissioned.CommissionedEmployee;
import hr.exceptions.EmployeeCreationException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UI {
    private static final int ADD_EMPLOYEE = 1;
    private static final int ADD_SALE_TO_EMPLOYEE = 2;
    private static final int ADD_WORK_HOURS = 3;
    private static final int DISPLAY_EMPLOYEES = 4;
    private static final int CALCULATE_INCOME = 5;
    private static final int QUIT = 6;

    private EmployeeControl employeeControl;
    private Scanner in;

    public UI() {
        this.employeeControl = new EmployeeControl(4);
    }

    public void menu() {
        do {
            this.printLine("MENU");
            this.printLine("[1] Add employee");
            this.printLine("[2] Add sale to employee");
            this.printLine("[3] Add worked hours");
            this.printLine("[4] Display employees");
            this.printLine("[5] Calculate income");
            this.printLine("[6] Quit");
            this.printLine("Choose your option: ");
        } while (this.checkSelection() != QUIT);
    }

    public byte checkSelection() {
        byte selection = this.getSelection();

        switch (selection) {
            case ADD_EMPLOYEE:
                this.addEmployeeByType();
                break;
            case ADD_SALE_TO_EMPLOYEE:
                this.addSaleToEmployee();
                break;
            case ADD_WORK_HOURS:
                this.addHoursToEmployee();
                break;
            case DISPLAY_EMPLOYEES:
                this.printEmployees();
                break;
            case CALCULATE_INCOME:
                this.printTotalIncome(this.employeeControl.getTotalIncome());
                break;
            case QUIT:
                break;
            default:
                this.printLine("Please insert a valid option");
                break;
        }

        return selection;
    }

    private void addSaleToEmployee() {
        String name = this.getStringValue("Enter the employee name: ");
        double value = this.getDoubleValue("And the value of the sale: ");
        if (!this.employeeControl.addSale(value, name)) {
            this.printLine("Employee does not exist, or it isn't a commissioned employee.");
        }
    }

    private void addHoursToEmployee() {
        String name = this.getStringValue("Enter the employee name: ");
        double value = this.getDoubleValue("And the worked hours: ");
        if (!this.employeeControl.addHours(value, name)) {
            this.printLine("Employee does not exist, or it isn't a hourly employee.");
        }
    }

    private byte getSelection() {
        try {
            this.in = new Scanner(System.in);
            return in.nextByte();
        } catch (InputMismatchException ex) {
            return 0;
        }
    }

    private void addEmployeeByType() {
        int count = 1 ;
        printLine("Select the type of the new employee");
        for (EmployeeType type : EmployeeType.values()) {
            this.printLine(String.format("[%d] %s", count++, type));
        }
        byte selection = this.getSelection();
        String name = this.getStringValue("Type the employee name: ");
        String cpf = this.getStringValue("Type the employee cpf");

        EmployeeData employee = new EmployeeData(name, cpf);

        switch (selection) {
            case 1:
                createEmployees(employee, EmployeeType.COMMISSIONED_BASE_SALARY_EMPLOYEE);
                break;
            case 2:
                createEmployees(employee, EmployeeType.COMMISSIONED_EMPLOYEE);
                break;
            case 3:
                createEmployees(employee, EmployeeType.HOURLY_EMPLOYEE);
                break;
            case 4:
                createEmployees(employee, EmployeeType.SALARIED_EMPLOYEE);
                break;
            default:
                printLine("Invalid option.");
        }
    }

    public void createEmployees (EmployeeData employee, EmployeeType type) {
        try {
            switch (type) {
                case COMMISSIONED_BASE_SALARY_EMPLOYEE:
                    double salary = getDoubleValue("Type the employee salary: ");
                    this.employeeControl.addEmployee(new CommissionedBaseSalaryEmployee(employee, salary, 0.04));
                    break;
                case HOURLY_EMPLOYEE:
                    double hourlySalary = getDoubleValue("Type the employee salary: ");
                    this.employeeControl.addEmployee(new HourlyEmployee(employee, hourlySalary));
                    break;
                case SALARIED_EMPLOYEE:
                    double weeklySalary = getDoubleValue("Type the employee salary: ");
                    this.employeeControl.addEmployee(new SalariedEmployee(employee, weeklySalary));
                    break;
                case COMMISSIONED_EMPLOYEE:
                    this.employeeControl.addEmployee(new CommissionedEmployee(employee, 0.1));
                    break;
            }
        } catch (EmployeeCreationException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void printEmployees() {
        Employee[] employees = this.employeeControl.getEmployees();
        for (int i = 0; i < this.employeeControl.getEmployeesLen(); i++) {
            printEmployeeInformation(employees[i]);
        }
    }

    private void printEmployeeInformation(Employee employee) {
        System.out.printf("Employee %s, has a income of %.2f\n", employee.getName(), employee.getIncome());
    }

    private void printTotalIncome(double totalIncome) {
        System.out.printf("The total income of the company is: %.2f\n", totalIncome);
    }

    private void printLine(String message) {
        System.out.println(message);
    }

    public String getStringValue(String message) {
        this.printLine(message);
        return this.in.next();
    }

    public double getDoubleValue(String message) {
        try {
            this.printLine(message);
            return this.in.nextDouble();
        } catch (InputMismatchException ex) {
            return -1;
        }
    }
}
