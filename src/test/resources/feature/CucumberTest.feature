#language: pt
#encoding: UTF-8

Funcionalidade: Teste

  @ct-01
  Cenario: Teste Frame
    Dado que estou no aplicativo

  @ct-02
  Cenario: Validar MyDemoApp Home
    Dado que estou na home do app MyDemoApp
    Entao deve ser apresentados produtos na home

  @ct-03
  Cenario: Home Deslogada - Apresentar area para Turbine seus Sonhos
    Dado que o usuário acesse a home deslogada
    Entao devo visualizar os cards de fidelidade