package br.unicamp.ic.caixaautomatico.spec;

public interface ITrmCxAut {

	void iniciarOperacao();

	void alternarModo(int senhaSupervisor);

	int getOp();

	int getInt(String str);

}
