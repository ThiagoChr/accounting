package br.accounting.domain.util.money;


public interface MoneyFunction<F> {

	Money getOperand(F input);

}
