package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dao.Dao;
import model.Produto;
import table.ProdutoTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ProdutoView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfNome;
	private JTextField tfCodigo;
	private JTable table;
	
	private Produto produto = new Produto();
	private Dao dao = new Dao();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProdutoView frame = new ProdutoView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ProdutoView() {
		init();
		table.setModel(new ProdutoTableModel(new Dao().listar()));		
	}
	
	private void init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nome");
		lblNewLabel.setBounds(6, 85, 56, 25);
		contentPane.add(lblNewLabel);
		
		tfNome = new JTextField();
		tfNome.setBounds(86, 84, 497, 26);
		contentPane.add(tfNome);
		tfNome.setColumns(10);
		
		JLabel lblCdigo = new JLabel("C\u00F3digo");
		lblCdigo.setBounds(6, 47, 61, 16);
		contentPane.add(lblCdigo);
		
		tfCodigo = new JTextField();
		tfCodigo.setEditable(false);
		tfCodigo.setBounds(86, 42, 130, 26);
		contentPane.add(tfCodigo);
		tfCodigo.setColumns(10);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tfCodigo.getText().equals("")) {
					produto.setNome(tfNome.getText());
					dao.inserir(produto);
				} else {
					produto.setId(Long.parseLong(tfCodigo.getText()));
					produto.setNome(tfNome.getText());
					dao.alterar(produto);
				}
				table.setModel(new ProdutoTableModel(new Dao().listar()));
				tfCodigo.setText("");
				tfNome.setText("");
			}
		});
		btnSalvar.setBounds(466, 124, 117, 29);
		contentPane.add(btnSalvar);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfCodigo.setText("");
				tfNome.setText("");
			}
		});
		btnLimpar.setBounds(202, 124, 117, 29);
		contentPane.add(btnLimpar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(19, 164, 564, 181);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				tfCodigo.setText(table.getValueAt(table.getSelectedRow(), ProdutoTableModel.COL_ID).toString());
				tfNome.setText(table.getValueAt(table.getSelectedRow(), ProdutoTableModel.COL_NOME).toString());
			}
		});
		scrollPane.setViewportView(table);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!tfCodigo.getText().equals("")) {
					dao.excluir(Long.parseLong(tfCodigo.getText()));
					table.setModel(new ProdutoTableModel(new Dao().listar()));
					tfCodigo.setText("");
					tfNome.setText("");
				}
			}
		});
		btnExcluir.setBounds(342, 124, 117, 29);
		contentPane.add(btnExcluir);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 400);
		setLocationRelativeTo(null);
	}
}
