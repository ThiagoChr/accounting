package br.gov.bcb.depep.domain.util.money;


public interface MoneyFunction<F> {

	Money getOperand(F input);

}
