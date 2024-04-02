package br.com.projetoMvc.test;

import javax.swing.JOptionPane;

public class ExemploJOptionPane {
	public static void main(String[] args) {
		String nome = JOptionPane.showInputDialog("Digite seu nome");
		JOptionPane.showMessageDialog(null, "Nome digitado:" + nome);

		String menu = "Menu de opções" 
		+ "/n[1] Cadastrar" 
		+ "/n[2] Listar todos";
		int opcao = Integer.parseInt(JOptionPane.showInputDialog(menu));
		
		JOptionPane.showMessageDialog(opcao);
	}
}
