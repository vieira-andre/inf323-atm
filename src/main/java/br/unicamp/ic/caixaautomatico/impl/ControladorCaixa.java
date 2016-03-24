package br.unicamp.ic.caixaautomatico.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import br.unicamp.ic.caixaautomatico.exceptions.DebitarValorException;
import br.unicamp.ic.caixaautomatico.exceptions.EfetuarSaqueException;
import br.unicamp.ic.caixaautomatico.exceptions.ObterExtratoException;
import br.unicamp.ic.caixaautomatico.exceptions.ObterSaldoException;
import br.unicamp.ic.caixaautomatico.exceptions.RecarregarCaixaException;
import br.unicamp.ic.caixaautomatico.spec.ICadastroContas;
import br.unicamp.ic.caixaautomatico.spec.IConta;
import br.unicamp.ic.caixaautomatico.spec.IControladorCaixa;

public class ControladorCaixa implements IControladorCaixa {

	private int senhaDoCaixa;
	Caixa caixa;
	ICadastroContas cadastroContas = new CadastroContas();

	public ControladorCaixa(int senhaCaixa) {
		this.senhaDoCaixa = senhaCaixa;

		this.caixa = new Caixa(this.senhaDoCaixa);
	}

	@Override
	public float consultarSaldo(int numeroConta, int pwd) throws ObterSaldoException {
		IConta conta = cadastroContas.buscaConta(numeroConta);

		if (conta != null) {
			try {
				return conta.obterSaldo(pwd);
			} catch (ObterSaldoException e) {
				e.getMessage();
			}
		} else {
			throw new ObterSaldoException("Número de conta inválido");
		}

		return -1;
	}

	@Override
	public String consultarExtrato(int numeroConta, int pwd) throws ObterExtratoException {
		IConta conta = cadastroContas.buscaConta(numeroConta);

		if (conta != null) {
			try {
				return conta.obterExtrato(pwd);
			} catch (ObterExtratoException e) {
				e.getMessage();
			}
		} else {
			throw new ObterExtratoException("Número de conta inválido");
		}

		return null;
	}

	@Override
	public boolean efetuarSaque(int numeroConta, int pwd, float valor)
			throws EfetuarSaqueException, DebitarValorException {
//		if (!isValorMaiorQueZero(valor)) {
//			throw new DebitarValorException("Valor Saque dever ser maior que 0");
//		}
//
//		if (!isValorMultiploDe10(valor)) {
//			throw new EfetuarSaqueException("Valor Saque dever ser múltiplo de 10 (10, 20, 30 ...)");
//		}
//
//		if (!isSaldoDoCaixaMaiorOuIgualValor(caixa.obterSaldoCaixa(), valor)) {
//			throw new DebitarValorException(
//					"Valor Saque dever ser menor que valor disponível do caixa " + caixa.obterSaldoCaixa());
//		}

		IConta conta = cadastroContas.buscaConta(numeroConta);

		if (conta != null) {
			String historico = getDataAtual() + " - " + "Débito" + " - " + valor;

			try {
				conta.debitarValor(historico, valor, pwd);
			} catch (DebitarValorException e) {
				e.getMessage();
			}
		} else {
			throw new EfetuarSaqueException("Número de conta inválido" + numeroConta);
		}

		return false;
	}

	@Override
	public void recarregar(int pwd) throws RecarregarCaixaException {
		if (this.validarSenha(pwd)) {
			caixa.recarregar(pwd);
		} else {
			throw new RecarregarCaixaException("Senha incorreta!");
		}
	}

	@Override
	public boolean validarSenha(int pwd) {
		if (pwd == this.senhaDoCaixa) {
			return true;
		}

		return false;
	}

//	private boolean isValorMaiorQueZero(float valor) {
//		if (valor > 0) {
//			return true;
//		}
//
//		return false;
//	}
//
//	private boolean isValorMultiploDe10(float valor) {
//		if (valor % 10 == 0) {
//			return true;
//		}
//
//		return false;
//	}
//
//	private boolean isSaldoDoCaixaMaiorOuIgualValor(float saldoDoCaixa, float valor) {
//		if (saldoDoCaixa >= valor) {
//			return true;
//		}
//
//		return false;
//	}

	private String getDataAtual() {
		Calendar data = Calendar.getInstance();
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

		return formato.format(data.getTime()).toString();
	}

}