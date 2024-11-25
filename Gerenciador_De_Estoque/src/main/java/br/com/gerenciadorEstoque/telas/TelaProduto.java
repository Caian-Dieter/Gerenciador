package br.com.gerenciadorEstoque.telas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;


public class TelaProduto extends javax.swing.JInternalFrame {

    
    

    //usando variavel conexao do dal
    Connection conexao = null ;
    
    PreparedStatement pst = null;
    
    ResultSet rs = null;
    
    
    public TelaProduto() {
        initComponents();
      this.conexao = conexao;
    }
    
private void consultar() {
        String sql = "select * from categoria where id_Categoria=?";
        
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtCategoriaProduto.getText());
            rs = pst.executeQuery();

            if (rs.next()) {
                // Preenche os campos com os dados da categoria
                txtNomeProduto.setText(rs.getString("Nome"));
                txtDescricao.setText(rs.getString("descricao"));
            } else {
                JOptionPane.showMessageDialog(null, "Não há nada nessa categoria, crie algo.");
                txtNomeProduto.setText(null);
                txtDescricao.setText(null);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    // Método de cadastrar categoria
    private void cadastrar() {
        String sql = "{call cadastrarCategoria(?, ?)}";  // Chamada para a procedure no banco
        
        try {
            // Prepara a conexão para uso com CallableStatement
            pst = conexao.prepareCall(sql);

            // Preenche os parâmetros da procedure com os valores dos campos
            pst.setString(1, txtNomeProduto.getText());
            pst.setString(2, txtDescricao.getText());

            // Executa a procedure no banco de dados
            int adicionado = pst.executeUpdate();

            if (adicionado > 0) {
                JOptionPane.showMessageDialog(null, "Categoria cadastrada com sucesso!");

                // Limpa os campos após cadastro
                txtNomeProduto.setText(null);
                txtDescricao.setText(null);
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar categoria.");
                txtNomeProduto.setText(null);
                txtDescricao.setText(null);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    // Método de alterar categoria
    private void alterar() {
        String sql = "UPDATE categoria SET Nome=?, descricao=? WHERE id_Categoria=?";
        
        try {
            // Prepara a conexão para uso
            pst = conexao.prepareStatement(sql);

            // Verifica se os campos estão preenchidos
            if (txtNomeProduto.getText().isEmpty() || txtDescricao.getText().isEmpty() || txtCategoriaProduto.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos!");
                return;  // Sai do método caso algum campo esteja vazio
            }

            // Preenche os parâmetros da consulta com os dados dos campos
            pst.setString(1, txtNomeProduto.getText());
            pst.setString(2, txtDescricao.getText());
            pst.setString(3, txtCategoriaProduto.getText());

            // Executa a atualização no banco
            int linhasAfetadas = pst.executeUpdate();

            // Verifica se a atualização foi bem-sucedida
            if (linhasAfetadas > 0) {
                JOptionPane.showMessageDialog(null, "Dados da categoria alterados com sucesso!");
                txtNomeProduto.setText(null);
                txtDescricao.setText(null);
                txtCategoriaProduto.setText(null);
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao alterar dados. Verifique o ID da categoria.");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    // Método de excluir categoria
    private void excluir() {
        String sql = "delete from produto where id_Categoria=?";
        
        try {
            // Prepara a conexão para uso
            pst = conexao.prepareStatement(sql);

            // Preenche os parâmetros da consulta com os dados dos campos
            pst.setString(1, txtCategoriaProduto.getText());

            // Executa a exclusão no banco
            int excluir = pst.executeUpdate();

            // Verifica se a exclusão foi bem-sucedida
            if (excluir > 0) {
                JOptionPane.showMessageDialog(null, "Categoria excluída com sucesso!");
                txtNomeProduto.setText(null);
                txtDescricao.setText(null);
                txtCategoriaProduto.setText(null);
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao excluir categoria. Verifique o ID da categoria.");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnCatCreate = new javax.swing.JButton();
        btnCatRemove = new javax.swing.JButton();
        btnCatUpdate = new javax.swing.JButton();
        btnCatPesquisar = new javax.swing.JButton();
        txtNomeProduto = new javax.swing.JTextField();
        txtDescricao = new javax.swing.JTextField();
        txtQuantidadeEstoque = new javax.swing.JTextField();
        txtPrecoCompra = new javax.swing.JTextField();
        txtPrecoVenda = new javax.swing.JTextField();
        txtCategoriaProduto = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Produtos");
        setPreferredSize(new java.awt.Dimension(640, 580));

        jLabel2.setText("Nome produto");

        jLabel3.setText("Descrição");

        jLabel4.setText("Quantidade em estoque");

        jLabel5.setText("Preço de compra");

        jLabel6.setText("Preço de venda");

        jLabel7.setText("Categoria do produto");

        btnCatCreate.setIcon(new javax.swing.ImageIcon("C:\\Users\\Caian\\OneDrive\\Imagens\\icones\\adicionar.png")); // NOI18N
        btnCatCreate.setToolTipText("Adicionar");
        btnCatCreate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCatCreate.setPreferredSize(new java.awt.Dimension(80, 80));
        btnCatCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCatCreateActionPerformed(evt);
            }
        });

        btnCatRemove.setIcon(new javax.swing.ImageIcon("C:\\Users\\Caian\\OneDrive\\Imagens\\icones\\deletar.png")); // NOI18N
        btnCatRemove.setToolTipText("excluir");
        btnCatRemove.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCatRemove.setPreferredSize(new java.awt.Dimension(80, 80));
        btnCatRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCatRemoveActionPerformed(evt);
            }
        });

        btnCatUpdate.setIcon(new javax.swing.ImageIcon("C:\\Users\\Caian\\OneDrive\\Imagens\\icones\\editar.png")); // NOI18N
        btnCatUpdate.setToolTipText("editar");
        btnCatUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCatUpdate.setPreferredSize(new java.awt.Dimension(80, 80));
        btnCatUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCatUpdateActionPerformed(evt);
            }
        });

        btnCatPesquisar.setIcon(new javax.swing.ImageIcon("C:\\Users\\Caian\\OneDrive\\Imagens\\icones\\pesquisar.png")); // NOI18N
        btnCatPesquisar.setToolTipText("pesquisar");
        btnCatPesquisar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCatPesquisar.setPreferredSize(new java.awt.Dimension(80, 80));
        btnCatPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCatPesquisarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(btnCatCreate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                .addComponent(btnCatRemove, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addComponent(btnCatUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67)
                .addComponent(btnCatPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(txtCategoriaProduto))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtQuantidadeEstoque, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                            .addComponent(txtPrecoCompra)
                            .addComponent(txtPrecoVenda)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(53, 53, 53))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(81, 81, 81)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNomeProduto, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                            .addComponent(txtDescricao))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNomeProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txtQuantidadeEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtPrecoCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtPrecoVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtCategoriaProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCatCreate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnCatRemove, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnCatUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnCatPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(75, 75, 75))
        );

        setBounds(0, 0, 640, 540);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCatCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCatCreateActionPerformed
        cadastrar();
    }//GEN-LAST:event_btnCatCreateActionPerformed

    private void btnCatPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCatPesquisarActionPerformed
         consultar();
    }//GEN-LAST:event_btnCatPesquisarActionPerformed

    private void btnCatRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCatRemoveActionPerformed
       excluir();
    }//GEN-LAST:event_btnCatRemoveActionPerformed

    private void btnCatUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCatUpdateActionPerformed
        alterar();
    }//GEN-LAST:event_btnCatUpdateActionPerformed
    
    
   


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCatCreate;
    private javax.swing.JButton btnCatPesquisar;
    private javax.swing.JButton btnCatRemove;
    private javax.swing.JButton btnCatUpdate;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField txtCategoriaProduto;
    private javax.swing.JTextField txtDescricao;
    private javax.swing.JTextField txtNomeProduto;
    private javax.swing.JTextField txtPrecoCompra;
    private javax.swing.JTextField txtPrecoVenda;
    private javax.swing.JTextField txtQuantidadeEstoque;
    // End of variables declaration//GEN-END:variables

}