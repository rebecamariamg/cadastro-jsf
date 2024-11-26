-- Cria a tabela de usuário
CREATE TABLE user (id integer primary key autoincrement, user text not null, senha text not null, perfil text not null, lembrar boolean not null, curso text not null);
