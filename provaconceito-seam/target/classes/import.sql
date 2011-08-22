-- SQL statements which are executed at application startup if hibernate.hbm2ddl.auto is 'create' or 'create-drop'
insert into Usuario(nome, senha, email) values('Diego Ferreira', '123456', 'diego@diegosilva.com.br')
insert into Nota(id_usuario, titulo, texto) values(1, 'Titulo Teste', 'Texto Teste')
insert into Nota(id_usuario, titulo, texto) values(1, 'Titulo Teste 2', 'Texto Teste 2')
insert into Nota(id_usuario, titulo, texto) values(1, 'Titulo Teste 3', 'Texto Teste 2 Texto Teste 2Texto Teste 2Texto Teste 2Texto Teste 2')

insert into Usuario(nome, senha, email) values('Joaquim da Silva', '123456', 'joaquim@joaquim.com')
insert into Nota(id_usuario, titulo, texto) values(2, 'Titulo Teste', 'Texto Teste')
insert into Nota(id_usuario, titulo, texto) values(2, 'Titulo Teste 2', 'Texto Teste 2')
insert into Nota(id_usuario, titulo, texto) values(2, 'Titulo Teste 3', 'Texto Teste 2 Texto Teste 2Texto Teste 2Texto Teste 2Texto Teste 2')