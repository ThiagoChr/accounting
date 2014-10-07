package br.accounting.domain.bank;

import br.accounting.domain.graph.IVertex;

import com.google.common.base.Preconditions;

public class Bank implements IVertex {

    private String cnpj;
    private String name;

    private BankSize size;
    private BankMacroSegment macroSegment;
    private BankControlType controlType;

    public Bank(String cnpj, String name, BankSize bankSize,
            BankMacroSegment macroSegment, BankControlType controlType) {

        this.cnpj = Preconditions.checkNotNull(cnpj, "CNPJ cannot be null!");
        this.name = Preconditions.checkNotNull(name,
                "The bank's name cannot be null!");
        this.size = Preconditions.checkNotNull(bankSize,
                "The bank's size cannot be null!");
        this.macroSegment = Preconditions.checkNotNull(macroSegment,
                "The bank's macro-segment cannot be null!");
        this.controlType = Preconditions.checkNotNull(controlType,
                "The bank's control type cannot be null!");
    }

    public Bank() {
        // ntd
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getName() {
        return name;
    }

    public BankSize getSize() {
        return size;
    }

    public BankMacroSegment getMacroSegment() {
        return macroSegment;
    }

    public BankControlType getControlType() {
        return controlType;
    }

    protected void setCnpj(String cnpj) {
        this.cnpj = Preconditions
                .checkNotNull(cnpj, "The CNPJ cannot be null!");
    }

    protected void setName(String name) {
        this.name = Preconditions.checkNotNull(name,
                "The bank's name cannot be null!");
    }

    protected void setSize(String bankSize) {
        this.size = BankSize.transform(bankSize);
    }

    protected void setMacroSegment(String macroSegment) {
        this.macroSegment = BankMacroSegment.transform(macroSegment);
    }

    protected void setControlType(String controlType) {
        this.controlType = BankControlType.transform(controlType);
    }

    @Override
    public boolean equals(Object obj) {

        if (!Bank.class.isInstance(obj)) {
            return false;
        }

        Bank other = Bank.class.cast(obj);

        return this.cnpj.equals(other.getCnpj());
    }

    @Override
    public int hashCode() {
        return this.cnpj.hashCode();
    }

    @Override
    public String toString() {
        return cnpj + " (" + name + ") ";
    }

    @Override
    public String getId() {
        return getCnpj();
    }

}
