package br.unicamp.ic.caixaautomatico.spec;

public interface ICadastroContas {
	IConta buscaConta(int numeroConta);
	IConta criarContaCorrente(String titular, float saldoAtual, int senha);
	IConta criarContaEspecial(String titular, float saldoAtual, int senha);
}
