package table;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Produto;

public class ProdutoTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	public static final int COL_ID = 0;
	public static final int COL_NOME = 1;
	public List<Produto> lista;
	
	public ProdutoTableModel(List<Produto> lst) {
		lista = new ArrayList<Produto>(lst);
	}
	
	@Override
	public int getRowCount() {
		return lista.size();
	}

	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public Object getValueAt(int linhas, int colunas) {
		Produto produto = lista.get(linhas);
		if (colunas == COL_ID) return produto.getId();
		if (colunas == COL_NOME) return produto.getNome();
		return "";
	}
	
	@Override
	public String getColumnName(int colunas) {
		if (colunas == COL_ID) return "Código";
		if (colunas == COL_NOME) return "Nome";
		return "";
	}

}
