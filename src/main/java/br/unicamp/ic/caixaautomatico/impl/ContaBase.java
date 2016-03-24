package br.unicamp.ic.caixaautomatico.impl;

import java.util.ArrayList;
import java.util.List;

import br.unicamp.ic.caixaautomatico.exceptions.CreditarValorException;
import br.unicamp.ic.caixaautomatico.exceptions.DebitarValorException;
import br.unicamp.ic.caixaautomatico.exceptions.ObterExtratoException;
import br.unicamp.ic.caixaautomatico.exceptions.ObterSaldoException;
import br.unicamp.ic.caixaautomatico.spec.IConta;

public abstract class ContaBase implements IConta {

	public static int ATIVA = 1;
	public static int ENCERRADA = 2;

	private int estado;
	private float saldoAnterior;
	// private List<String> datas;
	private List<String> historico;
	private List<Float> valorLanc;
	private int ultLanc;

	private String titular;
	private float saldoAtual;
	private int numConta;
	private int senha;

	private float limiteDeSaque;

	public ContaBase(String titular, float saldoAtual, int numConta, int senha) {
		this.estado = ATIVA;

		this.titular = titular;
		this.saldoAtual = saldoAtual;
		this.numConta = numConta;
		this.senha = senha;

		// this.datas = new ArrayList<String>();
		this.historico = new ArrayList<String>();
		this.valorLanc = new ArrayList<Float>();
		this.ultLanc = this.valorLanc.size() - 1;
	}

	@Override
	public float obterSaldo(int pwd) throws ObterSaldoException {
		if (isContaAtiva()) {
			if (isSenhaCorreta(pwd)) {
				return this.saldoAtual;
			} else {
				throw new ObterSaldoException("A senha de entrada deve ser igual à senha da conta");
			}
		} else {
			throw new ObterSaldoException("A conta deve estar ativa");
		}

		// return -1;
	}

	@Override
	public String obterExtrato(int pwd) throws ObterExtratoException {
		if (isContaAtiva()) {
			if (isSenhaCorreta(pwd)) {
				StringBuilder sb = new StringBuilder();

				sb.append("Cliente: " + this.titular);
				sb.append("\n");
				sb.append("Conta: " + this.numConta);
				sb.append("\n\n");

				for (String string : historico) {
					sb.append(string);
					sb.append("\n");
				}

				// for (int i = 0; i < datas.size(); i++) {
				// for (int x = 0; x < historico.size(); x++) {
				// for (int y = 0; y < valorLanc.size(); y++) {
				// float valorAtualizado = valorLanc.get(y);
				//
				// if
				// (historico.get(x).equals(normalizaString("Débito").toLowerCase()))
				// {
				// valorAtualizado *= -1;
				// }
				//
				// sb.append(datas.get(i) + " - " + historico.get(x) + " - "
				// + String.format("%.2f", valorAtualizado));
				//
				// sb.append("\n");
				//
				// x++;
				// i++;
				// }
				// }
				// }

				sb.append("SALDO: " + this.saldoAtual);

				return sb.toString();
			} else {
				throw new ObterExtratoException("A senha de entrada deve ser igual à senha da conta");
			}
		} else {
			throw new ObterExtratoException("A conta deve estar ativa");
		}

		// return null;
	}

	@Override
	public boolean debitarValor(String historico, float valor, int pwd) throws DebitarValorException {
		if (isContaAtiva()) {
			throw new DebitarValorException("A conta deve estar ativa");
		} else {
			if (isSenhaCorreta(pwd)) {
				if (isValorMaiorQueZero(valor)) {
					if (isSaldoAtualMaiorOuIgualValor(valor)) {
						if (isValorDentroDoLimite(valor)) {
							if (isValorMultiploDe10(valor)) {
								this.saldoAnterior = this.saldoAtual;
								this.saldoAtual -= valor;

								// this.datas.add(getDataAtual());
								this.historico.add(historico);
								this.valorLanc.add(valor);

								return true;
							} else {
								return false;
							}
						} else {
							return false;
						}
					} else {
						throw new DebitarValorException("O valor do lançamento não pode ser superior ao saldo");
					}
				} else {
					throw new DebitarValorException("O valor do lançamento deve ser maior que zero");
				}
			} else {
				throw new DebitarValorException("A senha de entrada deve ser igual à senha da conta");
			}
		}
	}

	@Override
	public boolean creditarValor(String historico, float valor) throws CreditarValorException {
		if (isContaAtiva()) {
			if (isValorMaiorQueZero(valor)) {
				this.saldoAnterior = this.saldoAtual;
				this.saldoAtual += valor;

				// this.datas.add(getDataAtual());
				this.historico.add(historico);
				this.valorLanc.add(valor);

				return true;
			} else {
				throw new CreditarValorException("O valor do lançamento deve ser maior que zero.");
			}
		} else {
			throw new CreditarValorException("A conta deve estar ativa");
		}
	}

	public void setLimiteDeSaque(float valor) {
		this.limiteDeSaque = valor;
	}

	// private String getDataAtual() {
	// Calendar data = Calendar.getInstance();
	// SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	//
	// return formato.format(data.getTime()).toString();
	// }
	//
	// private String normalizaString(String string) {
	// return Normalizer.normalize(string, Form.NFD);
	// }

	// private boolean isSacavel(int pwd, float valor) {
	// if (isSenhaCorreta(pwd)) {
	// if (isContaAtiva()) {
	// if (isValorMaiorQueZero(valor)) {
	// if (isSaldoAtualMaiorOuIgualValor(valor)) {
	// if (isValorDentroDoLimite(valor)) {
	// if (isValorMultiploDe10(valor)) {
	// return true;
	// }
	// }
	// }
	// }
	// }
	// }
	//
	// return false;
	// }

	private boolean isSenhaCorreta(int pwd) {
		if (pwd == this.senha) {
			return true;
		}

		return false;
	}

	private boolean isContaAtiva() {
		if (this.estado == ATIVA) {
			return true;
		}

		return false;
	}

	private boolean isValorMaiorQueZero(float valor) {
		if (valor > 0) {
			return true;
		}

		return false;
	}

	private boolean isSaldoAtualMaiorOuIgualValor(float valor) {
		if (this.saldoAtual >= valor) {
			return true;
		}

		return false;
	}

	private boolean isValorDentroDoLimite(float valor) {
		if (valor <= this.limiteDeSaque) {
			return true;
		}

		return false;
	}

	private boolean isValorMultiploDe10(float valor) {
		if (valor % 10 == 0) {
			return true;
		}

		return false;
	}

}
