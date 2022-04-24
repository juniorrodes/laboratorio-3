package hr;

public class CommissionedBaseSalaryEmployee extends CommissionedEmployee{

    private double salary;

    public CommissionedBaseSalaryEmployee(String name, String cpf, double commission, double salary) {
        super (name, cpf, commission);
        this.salary = salary;
    }

    @Override
    public double getIncome() {
        return salary + (this.sales * this.commission);
    }
}
