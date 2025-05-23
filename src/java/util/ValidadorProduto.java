/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import model.Produto;

/**
 *
 * @author joao.campos
 */
public class ValidadorProduto {

    public static String validarCamposObrigatorios(Produto p) {
        if (p.getDescricao() == null || p.getDescricao().trim().isEmpty()
                || p.getMarca() == null || p.getMarca().trim().isEmpty()
                || p.getUltimaatualizacao() == null
                || p.getIdSetor() <= 0) {
            return "todos os campos obrigatorio devem ser preenchidos.";
        }
        return null;
    }

    public static String validarValoresNumericos(Produto p) {
        if (p.getQuantidade() < 0) {
            return "A quantidade não pode ser negativa.";
        }
        if (p.getPreco() < 0) {
            return "O preço do seu produto não pode ser negativo.";

        }
        return null;
    }

    public static int validarIdProduto(String idStr) throws IllegalArgumentException {
        if (idStr == null || idStr.trim().isEmpty()) {
            throw new IllegalArgumentException("ID do produto não informado.");
        }
        try {
            return Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("ID inválido. Informe um número inteiro");
        }
    }

   
}
