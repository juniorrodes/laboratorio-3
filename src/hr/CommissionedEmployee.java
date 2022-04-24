package hr;

public class CommissionedEmployee implements Employee{

    private String name;
    private String cpf;
    protected double commission;
    protected double sales;

    public CommissionedEmployee (String name, String cpf, double commission) {
        this.name = name;
        this.cpf = cpf;
        this.commission = commission;
        this.sales = 0;
    }

    @Override
    public double getIncome() {
        return this.sales * this.commission;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getCPF() {
        return this.cpf;
    }
}
