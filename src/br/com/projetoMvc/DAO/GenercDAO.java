package br.com.projetoMvc.DAO;

import java.util.List;

public interface GenercDAO {

	public List<Object> listarTodos();
	public Object listarPorId(int id);
	public Boolean cadastrar(Object object);
	public Boolean alterar(Object object);
	public void excluir(int id);
		
	
}
