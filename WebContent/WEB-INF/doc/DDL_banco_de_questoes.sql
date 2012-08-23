/* CURSO
check_tipo_graduacao:
	- TEC = TECNOLOGO
	- BAC = BACHARELADO
	- LIC = LICENCIATURA
*/
--DROP TABLE curso CASCADE;
CREATE TABLE curso (
	id_curso SERIAL PRIMARY KEY,
	descricao VARCHAR(50) NOT NULL,
	sigla VARCHAR(5) NOT NULL,
	tipo_graduacao CHAR(3) NOT NULL 
		CONSTRAINT check_tipo_graduacao__tec_bac_lic 
		CHECK (tipo_graduacao = 'TEC' or tipo_graduacao = 'BAC' or tipo_graduacao = 'LIC')
);


/* SEMESTRE */
--DROP TABLE anosemestre CASCADE;
CREATE TABLE anosemestre (
	id_anosemestre SERIAL PRIMARY KEY,
	ano numeric(4,0) NOT NULL,
	semestre SMALLINT NOT NULL
);

/* GRADE */
--DROP TABLE grade CASCADE;
CREATE TABLE grade (
	id_grade SERIAL PRIMARY KEY,
	id_curso INT NOT NULL,
	id_anosemestre_inicial INT NOT NULL,
	id_anosemestre_final INT NOT NULL,
	descricao VARCHAR(50) NOT NULL
);
ALTER TABLE grade ADD CONSTRAINT fk_grade_id_curso__curso_id_curso 
	FOREIGN KEY (id_curso) REFERENCES curso (id_curso);

ALTER TABLE grade ADD CONSTRAINT fk_grade_id_anosemestre_inicial___anosemestre_id_anosemestre 
	FOREIGN KEY (id_anosemestre_inicial) REFERENCES anosemestre(id_anosemestre);
ALTER TABLE grade ADD CONSTRAINT fk_grade_id_anosemestre_final__anosemestre_id_anosemestre
	FOREIGN KEY (id_anosemestre_final) REFERENCES anosemestre(id_anosemestre);

/* PERIODO */
--DROP TABLE periodo CASCADE;
CREATE TABLE periodo (
	id_periodo SERIAL PRIMARY KEY,
	descricao VARCHAR(50) NOT NULL,
	numero SMALLINT NOT NULL
);

/* GRADE_PERIODO */
--DROP TABLE grade_periodo CASCADE;
CREATE TABLE grade_periodo (
	id_grade_periodo SERIAL PRIMARY KEY,
	id_grade INT NOT NULL,
	id_periodo INT NOT NULL
);
ALTER TABLE grade_periodo ADD CONSTRAINT fk_grade_periodo_id_grade__grade_id_grade 
	FOREIGN KEY (id_grade) REFERENCES grade (id_grade);
ALTER TABLE grade_periodo ADD CONSTRAINT fk_grade_periodo_id_periodo__periodo_id_periodo
	FOREIGN KEY (id_periodo) REFERENCES periodo (id_periodo);

/* 
GRUPO_DE_ACESSO
check: 
A = Administrador
C = Coordenador
 */
--DROP TABLE grupo CASCADE;
CREATE TABLE grupo (
	id_grupo SERIAL PRIMARY KEY,
	descricao VARCHAR(50) NOT NULL,
	tipo CHAR(1) NOT NULL CONSTRAINT check_grupo__a_c 
		CHECK (tipo = 'A' or tipo = 'C')
);

/* USUARIO */
--DROP TABLE usuario CASCADE;
CREATE TABLE usuario (
	id_usuario SERIAL PRIMARY KEY,
	id_grupo INT NOT NULL,
	nome VARCHAR(50) NOT NULL,
	login VARCHAR(50) NOT NULL,
	senha VARCHAR(64) NOT NULL /* hash de 64 bytes */
);
ALTER TABLE usuario ADD CONSTRAINT fk_usuario_id_grupo__grupo_id_grupo FOREIGN KEY (id_grupo) 
REFERENCES grupo(id_grupo);
ALTER TABLE usuario ADD CONSTRAINT unique_login UNIQUE(login);

/* COORDENADOR_CURSO */
--DROP TABLE coordenador_curso CASCADE;
CREATE TABLE coordenador_curso (
	id_coordenador_curso SERIAL NOT NULL,
	id_usuario INT NOT NULL,
	id_curso INT NOT NULL,
	data_entrada date,
	data_saida date
);
ALTER TABLE coordenador_curso ADD CONSTRAINT 
fk_coordenador_curso_id_usuario__usuario_id_usuario 
FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario);

ALTER TABLE coordenador_curso ADD CONSTRAINT 
fk_coordenador_curso_id_curso__curso_id_curso 
FOREIGN KEY (id_curso) REFERENCES curso(id_curso);

/* PROVA */
--DROP TABLE prova CASCADE;
CREATE TABLE prova (
	id_prova SERIAL PRIMARY KEY,
	id_grade_periodo INT NOT NULL,
	id_anosemestre INT NOT NULL,
	descricao VARCHAR(50) NOT NULL,	
	data_prova date NOT NULL
);
ALTER TABLE prova ADD CONSTRAINT fk_prova_id_anosemestre__anosemestre_id_anosemestre 
	FOREIGN KEY (id_anosemestre) REFERENCES anosemestre(id_anosemestre);

ALTER TABLE prova ADD CONSTRAINT fk_prova_id_grade_periodo__grade_periodo_id_grade_periodo 
	FOREIGN KEY (id_grade_periodo) REFERENCES grade_periodo(id_grade_periodo);

/* 
PERGUNTA 
check:
(tipo_pergunta)
F = Fechada
A = Aberta

(nivel_pergunta)
F = FACIL
M = MEDIO
D = DIFICIL
*/
--DROP TABLE pergunta CASCADE;
CREATE TABLE pergunta (
	id_pergunta SERIAL PRIMARY KEY,
	id_usuario INT NOT NULL,
	descricao VARCHAR(50) NOT NULL,
	tipo_pergunta CHAR(1) NOT NULL CONSTRAINT check_tipo_pergunta__F_A 
		CHECK (tipo_pergunta = 'F' or tipo_pergunta = 'A'),
	nivel_pergunta CHAR(1) NOT NULL CONSTRAINT check_nivel_pergunta__F_M_D 
		CHECK (nivel_pergunta = 'F' or nivel_pergunta = 'M' or nivel_pergunta = 'D'),
	enunciado TEXT NOT NULL,
	comentario TEXT
);
ALTER TABLE pergunta ADD CONSTRAINT fk_pergunta_id_usuario__usuario_id_usuario
FOREIGN KEY (id_usuario) REFERENCES usuario (id_usuario);

/* 
RESPOSTA
check_correta__s_n: 
	- S
	- N 
*/
--DROP TABLE resposta CASCADE;
CREATE TABLE resposta (
	id_resposta SERIAL PRIMARY KEY,
	id_pergunta INT NOT NULL,
	descricao TEXT NOT NULL,
	correta CHAR(1) NOT NULL,
	observacao TEXT
);
ALTER TABLE resposta ADD CONSTRAINT check_correta__s_n CHECK (correta = 'S' or correta = 'N');
ALTER TABLE resposta ADD CONSTRAINT fk_resposta_id_pergunta__pergunta_id_pergunta 
FOREIGN KEY (id_pergunta) REFERENCES pergunta(id_pergunta);

/* PROVA_PERGUNTA */
--DROP TABLE prova_pergunta CASCADE;
CREATE TABLE prova_pergunta (
	id_prova_pergunta SERIAL PRIMARY KEY,
	id_prova INT NOT NULL,
	id_pergunta INT NOT NULL,
	ordem SMALLINT
);
ALTER TABLE prova_pergunta ADD CONSTRAINT 
fk_prova_pergunta_id_prova__prova_id_prova 
FOREIGN KEY (id_prova) REFERENCES prova(id_prova);

ALTER TABLE prova_pergunta ADD CONSTRAINT 
fk_prova_pergunta_id_pergunta__pergunta_id_pergunta 
FOREIGN KEY (id_pergunta) REFERENCES pergunta(id_pergunta);

/* ASSUNTO */
--DROP TABLE assunto CASCADE;
CREATE TABLE assunto (
	id_assunto SERIAL PRIMARY KEY,
	descricao VARCHAR(50) NOT NULL
);

/* PERGUNTA_ASSUNTO */
--DROP TABLE pergunta_assunto CASCADE;
CREATE TABLE pergunta_assunto (
	id_pergunta_assunto SERIAL PRIMARY KEY,
	id_pergunta INT NOT NULL,
	id_assunto INT NOT NULL
);
ALTER TABLE pergunta_assunto ADD CONSTRAINT fk_pergunta_assunto_id_pergunta__pergunta_id_pergunta 
FOREIGN KEY (id_pergunta) REFERENCES pergunta(id_pergunta);

ALTER TABLE pergunta_assunto ADD CONSTRAINT fk_pergunta_assunto_id_assunto__assunto_id_assunto 
FOREIGN KEY (id_assunto) REFERENCES assunto(id_assunto);

/* DISCIPLINA */
--DROP TABLE disciplina CASCADE;
CREATE TABLE disciplina (
	id_disciplina SERIAL PRIMARY KEY,
	id_curso INT NOT NULL,
	descricao VARCHAR(50) NOT NULL,
	sigla VARCHAR(5) NOT NULL
);
ALTER TABLE disciplina ADD CONSTRAINT fk_disciplina_id_curso__curso_id_curso 
FOREIGN KEY (id_curso) REFERENCES curso(id_curso);

/* DISCIPLINA_PERGUNTA 
--DROP TABLE disciplina_pergunta CASCADE;
CREATE TABLE disciplina_pergunta (
	id_disciplina_pergunta SERIAL PRIMARY KEY,
	id_disciplina INT NOT NULL,
	id_pergunta INT NOT NULL
);

ALTER TABLE disciplina_pergunta ADD CONSTRAINT fk_disciplina_pergunta_id_disciplina__disciplina_id_disciplina 
	FOREIGN KEY (id_disciplina) REFERENCES disciplina(id_disciplina);

ALTER TABLE disciplina_pergunta ADD CONSTRAINT fk_disciplina_pergunta_id_pergunta__pergunta_id_pergunta 
	FOREIGN KEY (id_pergunta) REFERENCES pergunta (id_pergunta);
*/

/* DISCIPLINA_ASSUNTO */
--DROP TABLE disciplina_assunto CASCADE;
CREATE TABLE disciplina_assunto (
	id_disciplina_assunto SERIAL PRIMARY KEY,
	id_disciplina INT NOT NULL,
	id_assunto INT NOT NULL
);
ALTER TABLE disciplina_assunto ADD CONSTRAINT fk_disciplina_assunto_id_disciplina__disciplina_id_disciplina
FOREIGN KEY (id_disciplina) REFERENCES disciplina(id_disciplina);

ALTER TABLE disciplina_assunto ADD CONSTRAINT fk_disciplina_assunto_id_assunto__assunto_id_assunto 
FOREIGN KEY (id_assunto) REFERENCES assunto(id_assunto);

/* DISCIPLINA_ASSUNTO__PERGUNTA */
CREATE TABLE disciplina_assunto__pergunta (
	id_disciplina_assunto__pergunta SERIAL PRIMARY KEY,
	id_disciplina_assunto INT NOT NULL,
	id_pergunta INT NOT NULL
);
ALTER TABLE disciplina_assunto__pergunta ADD CONSTRAINT fk_d_a__p_id_disciplina_assunto__d_a_id_disciplina_assunto 
FOREIGN KEY (id_disciplina_assunto) REFERENCES disciplina_assunto (id_disciplina_Assunto);

ALTER TABLE disciplina_assunto__pergunta ADD CONSTRAINT fk_d_a__p_id_pergunta__p_id_pergunta 
FOREIGN KEY (id_pergunta) REFERENCES pergunta (id_pergunta);


/* GRADE_PERIODO_DISCIPLINA */
--DROP TABLE grade_periodo_disciplina CASCADE;
CREATE TABLE grade_periodo_disciplina (
	id_grade_periodo_disciplina SERIAL PRIMARY KEY,
	id_grade_periodo INT NOT NULL,
	id_disciplina INT NOT NULL
);
ALTER TABLE grade_periodo_disciplina ADD CONSTRAINT 
fk_grade_periodo_disciplina_id_grade_periodo__grade_periodo_id_grade_periodo 
FOREIGN KEY (id_grade_periodo) REFERENCES grade_periodo(id_grade_periodo);

ALTER TABLE grade_periodo_disciplina ADD CONSTRAINT 
fk_grade_periodo_disciplina_id_disciplina__disciplina_id_disciplina 
FOREIGN KEY (id_disciplina) REFERENCES disciplina (id_disciplina);
