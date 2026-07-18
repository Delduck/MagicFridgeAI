package com.github.delduck.magicfridgeai.enums;

import lombok.Getter;

@Getter
public enum Categoria {

    LEGUME("Vegetais em que frutos, raízes ou sementes são utilizados na alimentação."),
    VERDURA("Hortaliças em que folhas, flores ou hastes são a parte comestível."),
    FRUTA("Frutos comestíveis, geralmente consumidos in natura ou em sobremesas."),
    CARNE("Carnes bovinas, suínas, aves, peixes e outros tipos de proteína animal."),
    PEIXE("Peixes e frutos do mar destinados ao consumo."),
    LATICINIO("Leite e seus derivados, como queijo, manteiga e iogurte."),
    GRAO("Grãos, cereais e sementes, como arroz, feijão, milho e lentilha."),
    MASSA("Massas alimentícias, como macarrão, lasanha e nhoque."),
    BEBIDA("Bebidas alcoólicas e não alcoólicas, como água, refrigerante e sucos."),
    DOCE("Doces, chocolates, balas, biscoitos recheados e sobremesas."),
    TEMPERO("Temperos, ervas, especiarias e condimentos utilizados no preparo de alimentos."),
    CONGELADO("Produtos congelados prontos ou semiprontos."),
    PADARIA("Pães, bolos, tortas e demais produtos de panificação."),
    ENLATADO("Alimentos conservados em latas ou embalagens herméticas."),
    HIGIENE("Produtos de higiene pessoal, como sabonete, shampoo e creme dental."),
    LIMPEZA("Produtos destinados à limpeza doméstica."),
    PET("Alimentos, acessórios e produtos para animais de estimação."),
    OUTROS("Produtos que não se enquadram nas demais categorias.");

    private final String descricao;

    Categoria(String descricao) {
        this.descricao = descricao;
    }
}
