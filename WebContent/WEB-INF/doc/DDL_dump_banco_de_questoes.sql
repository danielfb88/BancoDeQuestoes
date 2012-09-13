
--
-- PostgreSQL database dump
--

-- Dumped from database version 9.1.5
-- Dumped by pg_dump version 9.1.5
-- Started on 2012-09-13 16:43:00 BRT

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 197 (class 3079 OID 11685)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2102 (class 0 OID 0)
-- Dependencies: 197
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 164 (class 1259 OID 33718)
-- Dependencies: 5
-- Name: anosemestre; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE anosemestre (
    id_anosemestre integer NOT NULL,
    ano numeric(4,0) NOT NULL,
    semestre smallint NOT NULL
);


ALTER TABLE public.anosemestre OWNER TO postgres;

--
-- TOC entry 163 (class 1259 OID 33716)
-- Dependencies: 164 5
-- Name: anosemestre_id_anosemestre_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE anosemestre_id_anosemestre_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.anosemestre_id_anosemestre_seq OWNER TO postgres;

--
-- TOC entry 2103 (class 0 OID 0)
-- Dependencies: 163
-- Name: anosemestre_id_anosemestre_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE anosemestre_id_anosemestre_seq OWNED BY anosemestre.id_anosemestre;


--
-- TOC entry 2104 (class 0 OID 0)
-- Dependencies: 163
-- Name: anosemestre_id_anosemestre_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('anosemestre_id_anosemestre_seq', 9, true);


--
-- TOC entry 184 (class 1259 OID 33884)
-- Dependencies: 5
-- Name: assunto; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE assunto (
    id_assunto integer NOT NULL,
    descricao character varying(50) NOT NULL
);


ALTER TABLE public.assunto OWNER TO postgres;

--
-- TOC entry 183 (class 1259 OID 33882)
-- Dependencies: 5 184
-- Name: assunto_id_assunto_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE assunto_id_assunto_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.assunto_id_assunto_seq OWNER TO postgres;

--
-- TOC entry 2105 (class 0 OID 0)
-- Dependencies: 183
-- Name: assunto_id_assunto_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE assunto_id_assunto_seq OWNED BY assunto.id_assunto;


--
-- TOC entry 2106 (class 0 OID 0)
-- Dependencies: 183
-- Name: assunto_id_assunto_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('assunto_id_assunto_seq', 14, true);


--
-- TOC entry 196 (class 1259 OID 50601)
-- Dependencies: 5
-- Name: assunto_pergunta; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE assunto_pergunta (
    id_assunto_pergunta integer NOT NULL,
    id_assunto integer NOT NULL,
    id_pergunta integer NOT NULL
);


ALTER TABLE public.assunto_pergunta OWNER TO postgres;

--
-- TOC entry 195 (class 1259 OID 50599)
-- Dependencies: 196 5
-- Name: assunto_pergunta_id_assunto_pergunta_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE assunto_pergunta_id_assunto_pergunta_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.assunto_pergunta_id_assunto_pergunta_seq OWNER TO postgres;

--
-- TOC entry 2107 (class 0 OID 0)
-- Dependencies: 195
-- Name: assunto_pergunta_id_assunto_pergunta_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE assunto_pergunta_id_assunto_pergunta_seq OWNED BY assunto_pergunta.id_assunto_pergunta;


--
-- TOC entry 2108 (class 0 OID 0)
-- Dependencies: 195
-- Name: assunto_pergunta_id_assunto_pergunta_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('assunto_pergunta_id_assunto_pergunta_seq', 7, true);


--
-- TOC entry 176 (class 1259 OID 33797)
-- Dependencies: 5
-- Name: coordenador_curso; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE coordenador_curso (
    id_coordenador_curso integer NOT NULL,
    id_usuario integer NOT NULL,
    id_curso integer NOT NULL,
    data_entrada date,
    data_saida date
);


ALTER TABLE public.coordenador_curso OWNER TO postgres;

--
-- TOC entry 175 (class 1259 OID 33795)
-- Dependencies: 5 176
-- Name: coordenador_curso_id_coordenador_curso_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE coordenador_curso_id_coordenador_curso_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.coordenador_curso_id_coordenador_curso_seq OWNER TO postgres;

--
-- TOC entry 2109 (class 0 OID 0)
-- Dependencies: 175
-- Name: coordenador_curso_id_coordenador_curso_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE coordenador_curso_id_coordenador_curso_seq OWNED BY coordenador_curso.id_coordenador_curso;


--
-- TOC entry 2110 (class 0 OID 0)
-- Dependencies: 175
-- Name: coordenador_curso_id_coordenador_curso_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('coordenador_curso_id_coordenador_curso_seq', 1, false);


--
-- TOC entry 162 (class 1259 OID 33709)
-- Dependencies: 1997 5
-- Name: curso; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE curso (
    id_curso integer NOT NULL,
    descricao character varying(50) NOT NULL,
    sigla character varying(5) NOT NULL,
    tipo_graduacao character(3) NOT NULL,
    CONSTRAINT check_tipo_graduacao__tec_bac_lic CHECK ((((tipo_graduacao = 'TEC'::bpchar) OR (tipo_graduacao = 'BAC'::bpchar)) OR (tipo_graduacao = 'LIC'::bpchar)))
);


ALTER TABLE public.curso OWNER TO postgres;

--
-- TOC entry 161 (class 1259 OID 33707)
-- Dependencies: 162 5
-- Name: curso_id_curso_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE curso_id_curso_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.curso_id_curso_seq OWNER TO postgres;

--
-- TOC entry 2111 (class 0 OID 0)
-- Dependencies: 161
-- Name: curso_id_curso_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE curso_id_curso_seq OWNED BY curso.id_curso;


--
-- TOC entry 2112 (class 0 OID 0)
-- Dependencies: 161
-- Name: curso_id_curso_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('curso_id_curso_seq', 28, true);


--
-- TOC entry 186 (class 1259 OID 33910)
-- Dependencies: 5
-- Name: disciplina; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE disciplina (
    id_disciplina integer NOT NULL,
    id_curso integer NOT NULL,
    descricao character varying(50) NOT NULL,
    sigla character varying(5) NOT NULL
);


ALTER TABLE public.disciplina OWNER TO postgres;

--
-- TOC entry 188 (class 1259 OID 33941)
-- Dependencies: 5
-- Name: disciplina_assunto; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE disciplina_assunto (
    id_disciplina_assunto integer NOT NULL,
    id_disciplina integer NOT NULL,
    id_assunto integer NOT NULL
);


ALTER TABLE public.disciplina_assunto OWNER TO postgres;

--
-- TOC entry 190 (class 1259 OID 42170)
-- Dependencies: 5
-- Name: disciplina_assunto__pergunta; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE disciplina_assunto__pergunta (
    id_disciplina_assunto__pergunta integer NOT NULL,
    id_disciplina_assunto integer NOT NULL,
    id_pergunta integer NOT NULL
);


ALTER TABLE public.disciplina_assunto__pergunta OWNER TO postgres;

--
-- TOC entry 189 (class 1259 OID 42168)
-- Dependencies: 190 5
-- Name: disciplina_assunto__pergunta_id_disciplina_assunto__pergunt_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE disciplina_assunto__pergunta_id_disciplina_assunto__pergunt_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.disciplina_assunto__pergunta_id_disciplina_assunto__pergunt_seq OWNER TO postgres;

--
-- TOC entry 2113 (class 0 OID 0)
-- Dependencies: 189
-- Name: disciplina_assunto__pergunta_id_disciplina_assunto__pergunt_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE disciplina_assunto__pergunta_id_disciplina_assunto__pergunt_seq OWNED BY disciplina_assunto__pergunta.id_disciplina_assunto__pergunta;


--
-- TOC entry 2114 (class 0 OID 0)
-- Dependencies: 189
-- Name: disciplina_assunto__pergunta_id_disciplina_assunto__pergunt_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('disciplina_assunto__pergunta_id_disciplina_assunto__pergunt_seq', 1, false);


--
-- TOC entry 187 (class 1259 OID 33939)
-- Dependencies: 188 5
-- Name: disciplina_assunto_id_disciplina_assunto_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE disciplina_assunto_id_disciplina_assunto_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.disciplina_assunto_id_disciplina_assunto_seq OWNER TO postgres;

--
-- TOC entry 2115 (class 0 OID 0)
-- Dependencies: 187
-- Name: disciplina_assunto_id_disciplina_assunto_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE disciplina_assunto_id_disciplina_assunto_seq OWNED BY disciplina_assunto.id_disciplina_assunto;


--
-- TOC entry 2116 (class 0 OID 0)
-- Dependencies: 187
-- Name: disciplina_assunto_id_disciplina_assunto_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('disciplina_assunto_id_disciplina_assunto_seq', 1, false);


--
-- TOC entry 185 (class 1259 OID 33908)
-- Dependencies: 5 186
-- Name: disciplina_id_disciplina_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE disciplina_id_disciplina_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.disciplina_id_disciplina_seq OWNER TO postgres;

--
-- TOC entry 2117 (class 0 OID 0)
-- Dependencies: 185
-- Name: disciplina_id_disciplina_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE disciplina_id_disciplina_seq OWNED BY disciplina.id_disciplina;


--
-- TOC entry 2118 (class 0 OID 0)
-- Dependencies: 185
-- Name: disciplina_id_disciplina_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('disciplina_id_disciplina_seq', 11, true);


--
-- TOC entry 166 (class 1259 OID 33726)
-- Dependencies: 5
-- Name: grade; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE grade (
    id_grade integer NOT NULL,
    id_curso integer NOT NULL,
    id_anosemestre_inicial integer NOT NULL,
    id_anosemestre_final integer NOT NULL,
    descricao character varying(50) NOT NULL
);


ALTER TABLE public.grade OWNER TO postgres;

--
-- TOC entry 165 (class 1259 OID 33724)
-- Dependencies: 5 166
-- Name: grade_id_grade_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE grade_id_grade_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.grade_id_grade_seq OWNER TO postgres;

--
-- TOC entry 2119 (class 0 OID 0)
-- Dependencies: 165
-- Name: grade_id_grade_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE grade_id_grade_seq OWNED BY grade.id_grade;


--
-- TOC entry 2120 (class 0 OID 0)
-- Dependencies: 165
-- Name: grade_id_grade_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('grade_id_grade_seq', 2, true);


--
-- TOC entry 170 (class 1259 OID 33757)
-- Dependencies: 5
-- Name: grade_periodo; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE grade_periodo (
    id_grade_periodo integer NOT NULL,
    id_grade integer NOT NULL,
    id_periodo integer NOT NULL
);


ALTER TABLE public.grade_periodo OWNER TO postgres;

--
-- TOC entry 194 (class 1259 OID 50583)
-- Dependencies: 5
-- Name: grade_periodo__disciplina; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE grade_periodo__disciplina (
    id_grade_periodo__disciplina integer NOT NULL,
    id_grade_periodo integer NOT NULL,
    id_disciplina integer NOT NULL
);


ALTER TABLE public.grade_periodo__disciplina OWNER TO postgres;

--
-- TOC entry 193 (class 1259 OID 50581)
-- Dependencies: 5 194
-- Name: grade_periodo__disciplina_id_grade_periodo__disciplina_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE grade_periodo__disciplina_id_grade_periodo__disciplina_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.grade_periodo__disciplina_id_grade_periodo__disciplina_seq OWNER TO postgres;

--
-- TOC entry 2121 (class 0 OID 0)
-- Dependencies: 193
-- Name: grade_periodo__disciplina_id_grade_periodo__disciplina_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE grade_periodo__disciplina_id_grade_periodo__disciplina_seq OWNED BY grade_periodo__disciplina.id_grade_periodo__disciplina;


--
-- TOC entry 2122 (class 0 OID 0)
-- Dependencies: 193
-- Name: grade_periodo__disciplina_id_grade_periodo__disciplina_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('grade_periodo__disciplina_id_grade_periodo__disciplina_seq', 3, true);


--
-- TOC entry 169 (class 1259 OID 33755)
-- Dependencies: 5 170
-- Name: grade_periodo_id_grade_periodo_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE grade_periodo_id_grade_periodo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.grade_periodo_id_grade_periodo_seq OWNER TO postgres;

--
-- TOC entry 2123 (class 0 OID 0)
-- Dependencies: 169
-- Name: grade_periodo_id_grade_periodo_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE grade_periodo_id_grade_periodo_seq OWNED BY grade_periodo.id_grade_periodo;


--
-- TOC entry 2124 (class 0 OID 0)
-- Dependencies: 169
-- Name: grade_periodo_id_grade_periodo_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('grade_periodo_id_grade_periodo_seq', 11, true);


--
-- TOC entry 172 (class 1259 OID 33775)
-- Dependencies: 2003 5
-- Name: grupo; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE grupo (
    id_grupo integer NOT NULL,
    descricao character varying(50) NOT NULL,
    tipo character(1) NOT NULL,
    CONSTRAINT check_grupo__a_c CHECK (((tipo = 'A'::bpchar) OR (tipo = 'C'::bpchar)))
);


ALTER TABLE public.grupo OWNER TO postgres;

--
-- TOC entry 171 (class 1259 OID 33773)
-- Dependencies: 5 172
-- Name: grupo_id_grupo_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE grupo_id_grupo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.grupo_id_grupo_seq OWNER TO postgres;

--
-- TOC entry 2125 (class 0 OID 0)
-- Dependencies: 171
-- Name: grupo_id_grupo_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE grupo_id_grupo_seq OWNED BY grupo.id_grupo;


--
-- TOC entry 2126 (class 0 OID 0)
-- Dependencies: 171
-- Name: grupo_id_grupo_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('grupo_id_grupo_seq', 101, true);


--
-- TOC entry 180 (class 1259 OID 33831)
-- Dependencies: 2008 2009 5
-- Name: pergunta; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE pergunta (
    id_pergunta integer NOT NULL,
    id_usuario integer NOT NULL,
    descricao character varying(50) NOT NULL,
    tipo_pergunta character(1) NOT NULL,
    nivel_pergunta character(1) NOT NULL,
    enunciado text NOT NULL,
    comentario text,
    CONSTRAINT check_nivel_pergunta__f_m_d CHECK ((((nivel_pergunta = 'F'::bpchar) OR (nivel_pergunta = 'M'::bpchar)) OR (nivel_pergunta = 'D'::bpchar))),
    CONSTRAINT check_tipo_pergunta__f_a CHECK (((tipo_pergunta = 'F'::bpchar) OR (tipo_pergunta = 'A'::bpchar)))
);


ALTER TABLE public.pergunta OWNER TO postgres;

--
-- TOC entry 179 (class 1259 OID 33829)
-- Dependencies: 180 5
-- Name: pergunta_id_pergunta_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE pergunta_id_pergunta_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.pergunta_id_pergunta_seq OWNER TO postgres;

--
-- TOC entry 2127 (class 0 OID 0)
-- Dependencies: 179
-- Name: pergunta_id_pergunta_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE pergunta_id_pergunta_seq OWNED BY pergunta.id_pergunta;


--
-- TOC entry 2128 (class 0 OID 0)
-- Dependencies: 179
-- Name: pergunta_id_pergunta_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('pergunta_id_pergunta_seq', 12, true);


--
-- TOC entry 168 (class 1259 OID 33749)
-- Dependencies: 5
-- Name: periodo; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE periodo (
    id_periodo integer NOT NULL,
    descricao character varying(50) NOT NULL,
    numero smallint NOT NULL
);


ALTER TABLE public.periodo OWNER TO postgres;

--
-- TOC entry 167 (class 1259 OID 33747)
-- Dependencies: 5 168
-- Name: periodo_id_periodo_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE periodo_id_periodo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.periodo_id_periodo_seq OWNER TO postgres;

--
-- TOC entry 2129 (class 0 OID 0)
-- Dependencies: 167
-- Name: periodo_id_periodo_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE periodo_id_periodo_seq OWNED BY periodo.id_periodo;


--
-- TOC entry 2130 (class 0 OID 0)
-- Dependencies: 167
-- Name: periodo_id_periodo_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('periodo_id_periodo_seq', 48, true);


--
-- TOC entry 178 (class 1259 OID 33813)
-- Dependencies: 5
-- Name: prova; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE prova (
    id_prova integer NOT NULL,
    id_grade_periodo integer NOT NULL,
    id_anosemestre integer NOT NULL,
    descricao character varying(50) NOT NULL,
    data_prova date NOT NULL
);


ALTER TABLE public.prova OWNER TO postgres;

--
-- TOC entry 177 (class 1259 OID 33811)
-- Dependencies: 178 5
-- Name: prova_id_prova_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE prova_id_prova_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.prova_id_prova_seq OWNER TO postgres;

--
-- TOC entry 2131 (class 0 OID 0)
-- Dependencies: 177
-- Name: prova_id_prova_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE prova_id_prova_seq OWNED BY prova.id_prova;


--
-- TOC entry 2132 (class 0 OID 0)
-- Dependencies: 177
-- Name: prova_id_prova_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('prova_id_prova_seq', 1, false);


--
-- TOC entry 182 (class 1259 OID 33866)
-- Dependencies: 5
-- Name: prova_pergunta; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE prova_pergunta (
    id_prova_pergunta integer NOT NULL,
    id_prova integer NOT NULL,
    id_pergunta integer NOT NULL,
    ordem smallint
);


ALTER TABLE public.prova_pergunta OWNER TO postgres;

--
-- TOC entry 181 (class 1259 OID 33864)
-- Dependencies: 5 182
-- Name: prova_pergunta_id_prova_pergunta_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE prova_pergunta_id_prova_pergunta_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.prova_pergunta_id_prova_pergunta_seq OWNER TO postgres;

--
-- TOC entry 2133 (class 0 OID 0)
-- Dependencies: 181
-- Name: prova_pergunta_id_prova_pergunta_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE prova_pergunta_id_prova_pergunta_seq OWNED BY prova_pergunta.id_prova_pergunta;


--
-- TOC entry 2134 (class 0 OID 0)
-- Dependencies: 181
-- Name: prova_pergunta_id_prova_pergunta_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('prova_pergunta_id_prova_pergunta_seq', 1, false);


--
-- TOC entry 192 (class 1259 OID 50549)
-- Dependencies: 5
-- Name: resposta; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE resposta (
    id_resposta integer NOT NULL,
    id_pergunta integer NOT NULL,
    descricao text NOT NULL,
    correta smallint NOT NULL,
    observacao text
);


ALTER TABLE public.resposta OWNER TO postgres;

--
-- TOC entry 191 (class 1259 OID 50547)
-- Dependencies: 5 192
-- Name: resposta_id_resposta_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE resposta_id_resposta_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.resposta_id_resposta_seq OWNER TO postgres;

--
-- TOC entry 2135 (class 0 OID 0)
-- Dependencies: 191
-- Name: resposta_id_resposta_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE resposta_id_resposta_seq OWNED BY resposta.id_resposta;


--
-- TOC entry 2136 (class 0 OID 0)
-- Dependencies: 191
-- Name: resposta_id_resposta_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('resposta_id_resposta_seq', 1, false);


--
-- TOC entry 174 (class 1259 OID 33784)
-- Dependencies: 5
-- Name: usuario; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE usuario (
    id_usuario integer NOT NULL,
    id_grupo integer NOT NULL,
    nome character varying(50) NOT NULL,
    login character varying(50) NOT NULL,
    senha character varying(64) NOT NULL
);


ALTER TABLE public.usuario OWNER TO postgres;

--
-- TOC entry 173 (class 1259 OID 33782)
-- Dependencies: 174 5
-- Name: usuario_id_usuario_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE usuario_id_usuario_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.usuario_id_usuario_seq OWNER TO postgres;

--
-- TOC entry 2137 (class 0 OID 0)
-- Dependencies: 173
-- Name: usuario_id_usuario_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE usuario_id_usuario_seq OWNED BY usuario.id_usuario;


--
-- TOC entry 2138 (class 0 OID 0)
-- Dependencies: 173
-- Name: usuario_id_usuario_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('usuario_id_usuario_seq', 10, true);


--
-- TOC entry 1998 (class 2604 OID 33721)
-- Dependencies: 164 163 164
-- Name: id_anosemestre; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY anosemestre ALTER COLUMN id_anosemestre SET DEFAULT nextval('anosemestre_id_anosemestre_seq'::regclass);


--
-- TOC entry 2011 (class 2604 OID 33887)
-- Dependencies: 183 184 184
-- Name: id_assunto; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY assunto ALTER COLUMN id_assunto SET DEFAULT nextval('assunto_id_assunto_seq'::regclass);


--
-- TOC entry 2017 (class 2604 OID 50604)
-- Dependencies: 195 196 196
-- Name: id_assunto_pergunta; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY assunto_pergunta ALTER COLUMN id_assunto_pergunta SET DEFAULT nextval('assunto_pergunta_id_assunto_pergunta_seq'::regclass);


--
-- TOC entry 2005 (class 2604 OID 33800)
-- Dependencies: 175 176 176
-- Name: id_coordenador_curso; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY coordenador_curso ALTER COLUMN id_coordenador_curso SET DEFAULT nextval('coordenador_curso_id_coordenador_curso_seq'::regclass);


--
-- TOC entry 1996 (class 2604 OID 33712)
-- Dependencies: 161 162 162
-- Name: id_curso; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY curso ALTER COLUMN id_curso SET DEFAULT nextval('curso_id_curso_seq'::regclass);


--
-- TOC entry 2012 (class 2604 OID 33913)
-- Dependencies: 185 186 186
-- Name: id_disciplina; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY disciplina ALTER COLUMN id_disciplina SET DEFAULT nextval('disciplina_id_disciplina_seq'::regclass);


--
-- TOC entry 2013 (class 2604 OID 33944)
-- Dependencies: 188 187 188
-- Name: id_disciplina_assunto; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY disciplina_assunto ALTER COLUMN id_disciplina_assunto SET DEFAULT nextval('disciplina_assunto_id_disciplina_assunto_seq'::regclass);


--
-- TOC entry 2014 (class 2604 OID 42173)
-- Dependencies: 189 190 190
-- Name: id_disciplina_assunto__pergunta; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY disciplina_assunto__pergunta ALTER COLUMN id_disciplina_assunto__pergunta SET DEFAULT nextval('disciplina_assunto__pergunta_id_disciplina_assunto__pergunt_seq'::regclass);


--
-- TOC entry 1999 (class 2604 OID 33729)
-- Dependencies: 166 165 166
-- Name: id_grade; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY grade ALTER COLUMN id_grade SET DEFAULT nextval('grade_id_grade_seq'::regclass);


--
-- TOC entry 2001 (class 2604 OID 33760)
-- Dependencies: 169 170 170
-- Name: id_grade_periodo; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY grade_periodo ALTER COLUMN id_grade_periodo SET DEFAULT nextval('grade_periodo_id_grade_periodo_seq'::regclass);


--
-- TOC entry 2016 (class 2604 OID 50586)
-- Dependencies: 193 194 194
-- Name: id_grade_periodo__disciplina; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY grade_periodo__disciplina ALTER COLUMN id_grade_periodo__disciplina SET DEFAULT nextval('grade_periodo__disciplina_id_grade_periodo__disciplina_seq'::regclass);


--
-- TOC entry 2002 (class 2604 OID 33778)
-- Dependencies: 172 171 172
-- Name: id_grupo; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY grupo ALTER COLUMN id_grupo SET DEFAULT nextval('grupo_id_grupo_seq'::regclass);


--
-- TOC entry 2007 (class 2604 OID 33834)
-- Dependencies: 180 179 180
-- Name: id_pergunta; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pergunta ALTER COLUMN id_pergunta SET DEFAULT nextval('pergunta_id_pergunta_seq'::regclass);


--
-- TOC entry 2000 (class 2604 OID 33752)
-- Dependencies: 168 167 168
-- Name: id_periodo; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY periodo ALTER COLUMN id_periodo SET DEFAULT nextval('periodo_id_periodo_seq'::regclass);


--
-- TOC entry 2006 (class 2604 OID 33816)
-- Dependencies: 178 177 178
-- Name: id_prova; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY prova ALTER COLUMN id_prova SET DEFAULT nextval('prova_id_prova_seq'::regclass);


--
-- TOC entry 2010 (class 2604 OID 33869)
-- Dependencies: 181 182 182
-- Name: id_prova_pergunta; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY prova_pergunta ALTER COLUMN id_prova_pergunta SET DEFAULT nextval('prova_pergunta_id_prova_pergunta_seq'::regclass);


--
-- TOC entry 2015 (class 2604 OID 50552)
-- Dependencies: 191 192 192
-- Name: id_resposta; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY resposta ALTER COLUMN id_resposta SET DEFAULT nextval('resposta_id_resposta_seq'::regclass);


--
-- TOC entry 2004 (class 2604 OID 33787)
-- Dependencies: 174 173 174
-- Name: id_usuario; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY usuario ALTER COLUMN id_usuario SET DEFAULT nextval('usuario_id_usuario_seq'::regclass);


--
-- TOC entry 2078 (class 0 OID 33718)
-- Dependencies: 164 2095
-- Data for Name: anosemestre; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY anosemestre (id_anosemestre, ano, semestre) FROM stdin;
1	2012	1
2	2015	1
4	2010	1
5	2010	2
6	2011	1
7	2011	2
8	2012	1
9	2012	2
\.


--
-- TOC entry 2088 (class 0 OID 33884)
-- Dependencies: 184 2095
-- Data for Name: assunto; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY assunto (id_assunto, descricao) FROM stdin;
1	Sintaxe PHP
3	GPU
5	Controle de Concorrência
7	Programação Linear
8	Teoria da Fornicação
6	Calculo da massa do cocô
12	Práticas Investigativas do Alcoolismo
11	Teoria da degustação de Cerveja com ênfase em Skol
\.


--
-- TOC entry 2094 (class 0 OID 50601)
-- Dependencies: 196 2095
-- Data for Name: assunto_pergunta; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY assunto_pergunta (id_assunto_pergunta, id_assunto, id_pergunta) FROM stdin;
7	1	12
\.


--
-- TOC entry 2084 (class 0 OID 33797)
-- Dependencies: 176 2095
-- Data for Name: coordenador_curso; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY coordenador_curso (id_coordenador_curso, id_usuario, id_curso, data_entrada, data_saida) FROM stdin;
\.


--
-- TOC entry 2077 (class 0 OID 33709)
-- Dependencies: 162 2095
-- Data for Name: curso; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY curso (id_curso, descricao, sigla, tipo_graduacao) FROM stdin;
21	Sistemas de Informação	SI	BAC
22	Psicologia	PS	BAC
23	Ciência da Computação	CC	BAC
24	Engenharia	ENG	BAC
25	Geologia	GEO	BAC
26	Física	FIS	BAC
27	Desenvolvimento de Software	DS	TEC
28	Redes	RD	TEC
\.


--
-- TOC entry 2089 (class 0 OID 33910)
-- Dependencies: 186 2095
-- Data for Name: disciplina; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY disciplina (id_disciplina, id_curso, descricao, sigla) FROM stdin;
8	21	Banco de Dados 1	BD1
9	21	Banco de Dados 2	BD2
10	21	Programação Orientada a Objetos 1	POO1
11	21	Sistemas Distribuídos	SD
\.


--
-- TOC entry 2090 (class 0 OID 33941)
-- Dependencies: 188 2095
-- Data for Name: disciplina_assunto; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY disciplina_assunto (id_disciplina_assunto, id_disciplina, id_assunto) FROM stdin;
\.


--
-- TOC entry 2091 (class 0 OID 42170)
-- Dependencies: 190 2095
-- Data for Name: disciplina_assunto__pergunta; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY disciplina_assunto__pergunta (id_disciplina_assunto__pergunta, id_disciplina_assunto, id_pergunta) FROM stdin;
\.


--
-- TOC entry 2079 (class 0 OID 33726)
-- Dependencies: 166 2095
-- Data for Name: grade; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY grade (id_grade, id_curso, id_anosemestre_inicial, id_anosemestre_final, descricao) FROM stdin;
2	21	1	2	SI2012
\.


--
-- TOC entry 2081 (class 0 OID 33757)
-- Dependencies: 170 2095
-- Data for Name: grade_periodo; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY grade_periodo (id_grade_periodo, id_grade, id_periodo) FROM stdin;
5	2	33
6	2	34
8	2	35
9	2	36
10	2	37
11	2	38
\.


--
-- TOC entry 2093 (class 0 OID 50583)
-- Dependencies: 194 2095
-- Data for Name: grade_periodo__disciplina; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY grade_periodo__disciplina (id_grade_periodo__disciplina, id_grade_periodo, id_disciplina) FROM stdin;
2	5	9
3	5	10
\.


--
-- TOC entry 2082 (class 0 OID 33775)
-- Dependencies: 172 2095
-- Data for Name: grupo; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY grupo (id_grupo, descricao, tipo) FROM stdin;
84	Admin	A
85	Coord	C
89	Teste OK	C
87	Teste Grupo JSF	C
88	Teste JSF	C
94	ok	C
100	dsdsadsa	A
101	novo grupo	C
\.


--
-- TOC entry 2086 (class 0 OID 33831)
-- Dependencies: 180 2095
-- Data for Name: pergunta; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY pergunta (id_pergunta, id_usuario, descricao, tipo_pergunta, nivel_pergunta, enunciado, comentario) FROM stdin;
12	5	Pergunta muito dificil	A	D	Quanto é 2 + 2?	\N
\.


--
-- TOC entry 2080 (class 0 OID 33749)
-- Dependencies: 168 2095
-- Data for Name: periodo; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY periodo (id_periodo, descricao, numero) FROM stdin;
33	Primeiro	1
34	Segunda	2
35	Terceiro	3
36	Quarto	4
37	Quinto	5
38	Sexto	6
39	Sétimo	7
40	Oitavo	8
41	Primeiro	1
42	Segunda	2
43	Terceiro	3
44	Quarto	4
45	Quinto	5
46	Sexto	6
47	Sétimo	7
48	Oitavo	8
\.


--
-- TOC entry 2085 (class 0 OID 33813)
-- Dependencies: 178 2095
-- Data for Name: prova; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY prova (id_prova, id_grade_periodo, id_anosemestre, descricao, data_prova) FROM stdin;
\.


--
-- TOC entry 2087 (class 0 OID 33866)
-- Dependencies: 182 2095
-- Data for Name: prova_pergunta; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY prova_pergunta (id_prova_pergunta, id_prova, id_pergunta, ordem) FROM stdin;
\.


--
-- TOC entry 2092 (class 0 OID 50549)
-- Dependencies: 192 2095
-- Data for Name: resposta; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY resposta (id_resposta, id_pergunta, descricao, correta, observacao) FROM stdin;
\.


--
-- TOC entry 2083 (class 0 OID 33784)
-- Dependencies: 174 2095
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY usuario (id_usuario, id_grupo, nome, login, senha) FROM stdin;
5	84	Daniel Bonfim alt	daniel	1234
10	84	Fulano da Silva Santos	fusilva	12345
\.


--
-- TOC entry 2021 (class 2606 OID 33723)
-- Dependencies: 164 164 2096
-- Name: anosemestre_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY anosemestre
    ADD CONSTRAINT anosemestre_pkey PRIMARY KEY (id_anosemestre);


--
-- TOC entry 2053 (class 2606 OID 50606)
-- Dependencies: 196 196 2096
-- Name: assunto_pergunta_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY assunto_pergunta
    ADD CONSTRAINT assunto_pergunta_pkey PRIMARY KEY (id_assunto_pergunta);


--
-- TOC entry 2041 (class 2606 OID 33889)
-- Dependencies: 184 184 2096
-- Name: assunto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY assunto
    ADD CONSTRAINT assunto_pkey PRIMARY KEY (id_assunto);


--
-- TOC entry 2019 (class 2606 OID 33715)
-- Dependencies: 162 162 2096
-- Name: curso_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY curso
    ADD CONSTRAINT curso_pkey PRIMARY KEY (id_curso);


--
-- TOC entry 2047 (class 2606 OID 42175)
-- Dependencies: 190 190 2096
-- Name: disciplina_assunto__pergunta_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY disciplina_assunto__pergunta
    ADD CONSTRAINT disciplina_assunto__pergunta_pkey PRIMARY KEY (id_disciplina_assunto__pergunta);


--
-- TOC entry 2045 (class 2606 OID 33946)
-- Dependencies: 188 188 2096
-- Name: disciplina_assunto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY disciplina_assunto
    ADD CONSTRAINT disciplina_assunto_pkey PRIMARY KEY (id_disciplina_assunto);


--
-- TOC entry 2043 (class 2606 OID 33915)
-- Dependencies: 186 186 2096
-- Name: disciplina_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY disciplina
    ADD CONSTRAINT disciplina_pkey PRIMARY KEY (id_disciplina);


--
-- TOC entry 2051 (class 2606 OID 50588)
-- Dependencies: 194 194 2096
-- Name: grade_periodo_disciplina_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY grade_periodo__disciplina
    ADD CONSTRAINT grade_periodo_disciplina_pkey PRIMARY KEY (id_grade_periodo__disciplina);


--
-- TOC entry 2027 (class 2606 OID 33762)
-- Dependencies: 170 170 2096
-- Name: grade_periodo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY grade_periodo
    ADD CONSTRAINT grade_periodo_pkey PRIMARY KEY (id_grade_periodo);


--
-- TOC entry 2023 (class 2606 OID 33731)
-- Dependencies: 166 166 2096
-- Name: grade_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY grade
    ADD CONSTRAINT grade_pkey PRIMARY KEY (id_grade);


--
-- TOC entry 2029 (class 2606 OID 33781)
-- Dependencies: 172 172 2096
-- Name: grupo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY grupo
    ADD CONSTRAINT grupo_pkey PRIMARY KEY (id_grupo);


--
-- TOC entry 2037 (class 2606 OID 33841)
-- Dependencies: 180 180 2096
-- Name: pergunta_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY pergunta
    ADD CONSTRAINT pergunta_pkey PRIMARY KEY (id_pergunta);


--
-- TOC entry 2025 (class 2606 OID 33754)
-- Dependencies: 168 168 2096
-- Name: periodo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY periodo
    ADD CONSTRAINT periodo_pkey PRIMARY KEY (id_periodo);


--
-- TOC entry 2039 (class 2606 OID 33871)
-- Dependencies: 182 182 2096
-- Name: prova_pergunta_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY prova_pergunta
    ADD CONSTRAINT prova_pergunta_pkey PRIMARY KEY (id_prova_pergunta);


--
-- TOC entry 2035 (class 2606 OID 33818)
-- Dependencies: 178 178 2096
-- Name: prova_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY prova
    ADD CONSTRAINT prova_pkey PRIMARY KEY (id_prova);


--
-- TOC entry 2049 (class 2606 OID 50557)
-- Dependencies: 192 192 2096
-- Name: resposta_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY resposta
    ADD CONSTRAINT resposta_pkey PRIMARY KEY (id_resposta);


--
-- TOC entry 2031 (class 2606 OID 33977)
-- Dependencies: 174 174 2096
-- Name: unique_login; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT unique_login UNIQUE (login);


--
-- TOC entry 2033 (class 2606 OID 33789)
-- Dependencies: 174 174 2096
-- Name: usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id_usuario);


--
-- TOC entry 2075 (class 2606 OID 50607)
-- Dependencies: 2040 184 196 2096
-- Name: fk_assunto_pergunta_id_assunto__assunto_id_assunto; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY assunto_pergunta
    ADD CONSTRAINT fk_assunto_pergunta_id_assunto__assunto_id_assunto FOREIGN KEY (id_assunto) REFERENCES assunto(id_assunto) ON DELETE CASCADE;


--
-- TOC entry 2076 (class 2606 OID 50612)
-- Dependencies: 180 2036 196 2096
-- Name: fk_assunto_pergunta_id_pergunta__pergunta_id_pergunta; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY assunto_pergunta
    ADD CONSTRAINT fk_assunto_pergunta_id_pergunta__pergunta_id_pergunta FOREIGN KEY (id_pergunta) REFERENCES pergunta(id_pergunta) ON DELETE CASCADE;


--
-- TOC entry 2061 (class 2606 OID 33806)
-- Dependencies: 2018 162 176 2096
-- Name: fk_coordenador_curso_id_curso__curso_id_curso; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY coordenador_curso
    ADD CONSTRAINT fk_coordenador_curso_id_curso__curso_id_curso FOREIGN KEY (id_curso) REFERENCES curso(id_curso) ON DELETE CASCADE;


--
-- TOC entry 2060 (class 2606 OID 33801)
-- Dependencies: 174 176 2032 2096
-- Name: fk_coordenador_curso_id_usuario__usuario_id_usuario; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY coordenador_curso
    ADD CONSTRAINT fk_coordenador_curso_id_usuario__usuario_id_usuario FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario) ON DELETE CASCADE;


--
-- TOC entry 2070 (class 2606 OID 42176)
-- Dependencies: 188 2044 190 2096
-- Name: fk_d_a__p_id_disciplina_assunto__d_a_id_disciplina_assunto; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY disciplina_assunto__pergunta
    ADD CONSTRAINT fk_d_a__p_id_disciplina_assunto__d_a_id_disciplina_assunto FOREIGN KEY (id_disciplina_assunto) REFERENCES disciplina_assunto(id_disciplina_assunto) ON DELETE CASCADE;


--
-- TOC entry 2071 (class 2606 OID 42181)
-- Dependencies: 180 190 2036 2096
-- Name: fk_d_a__p_id_pergunta__p_id_pergunta; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY disciplina_assunto__pergunta
    ADD CONSTRAINT fk_d_a__p_id_pergunta__p_id_pergunta FOREIGN KEY (id_pergunta) REFERENCES pergunta(id_pergunta) ON DELETE CASCADE;


--
-- TOC entry 2069 (class 2606 OID 33952)
-- Dependencies: 184 188 2040 2096
-- Name: fk_disciplina_assunto_id_assunto__assunto_id_assunto; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY disciplina_assunto
    ADD CONSTRAINT fk_disciplina_assunto_id_assunto__assunto_id_assunto FOREIGN KEY (id_assunto) REFERENCES assunto(id_assunto) ON DELETE CASCADE;


--
-- TOC entry 2068 (class 2606 OID 33947)
-- Dependencies: 188 2042 186 2096
-- Name: fk_disciplina_assunto_id_disciplina__disciplina_id_disciplina; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY disciplina_assunto
    ADD CONSTRAINT fk_disciplina_assunto_id_disciplina__disciplina_id_disciplina FOREIGN KEY (id_disciplina) REFERENCES disciplina(id_disciplina) ON DELETE CASCADE;


--
-- TOC entry 2067 (class 2606 OID 33916)
-- Dependencies: 186 162 2018 2096
-- Name: fk_disciplina_id_curso__curso_id_curso; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY disciplina
    ADD CONSTRAINT fk_disciplina_id_curso__curso_id_curso FOREIGN KEY (id_curso) REFERENCES curso(id_curso) ON DELETE CASCADE;


--
-- TOC entry 2056 (class 2606 OID 33742)
-- Dependencies: 2020 164 166 2096
-- Name: fk_grade_id_anosemestre_final__anosemestre_id_anosemestre; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY grade
    ADD CONSTRAINT fk_grade_id_anosemestre_final__anosemestre_id_anosemestre FOREIGN KEY (id_anosemestre_final) REFERENCES anosemestre(id_anosemestre) ON DELETE CASCADE;


--
-- TOC entry 2055 (class 2606 OID 33737)
-- Dependencies: 166 2020 164 2096
-- Name: fk_grade_id_anosemestre_inicial___anosemestre_id_anosemestre; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY grade
    ADD CONSTRAINT fk_grade_id_anosemestre_inicial___anosemestre_id_anosemestre FOREIGN KEY (id_anosemestre_inicial) REFERENCES anosemestre(id_anosemestre) ON DELETE CASCADE;


--
-- TOC entry 2054 (class 2606 OID 33732)
-- Dependencies: 166 162 2018 2096
-- Name: fk_grade_id_curso__curso_id_curso; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY grade
    ADD CONSTRAINT fk_grade_id_curso__curso_id_curso FOREIGN KEY (id_curso) REFERENCES curso(id_curso) ON DELETE CASCADE;


--
-- TOC entry 2073 (class 2606 OID 50589)
-- Dependencies: 2042 186 194 2096
-- Name: fk_grade_periodo_disciplina_id_disciplina__disciplina_id_discip; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY grade_periodo__disciplina
    ADD CONSTRAINT fk_grade_periodo_disciplina_id_disciplina__disciplina_id_discip FOREIGN KEY (id_disciplina) REFERENCES disciplina(id_disciplina) ON DELETE CASCADE;


--
-- TOC entry 2074 (class 2606 OID 50594)
-- Dependencies: 194 170 2026 2096
-- Name: fk_grade_periodo_disciplina_id_grade_periodo__grade_periodo_id_; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY grade_periodo__disciplina
    ADD CONSTRAINT fk_grade_periodo_disciplina_id_grade_periodo__grade_periodo_id_ FOREIGN KEY (id_grade_periodo) REFERENCES grade_periodo(id_grade_periodo) ON DELETE CASCADE;


--
-- TOC entry 2057 (class 2606 OID 33763)
-- Dependencies: 170 2022 166 2096
-- Name: fk_grade_periodo_id_grade__grade_id_grade; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY grade_periodo
    ADD CONSTRAINT fk_grade_periodo_id_grade__grade_id_grade FOREIGN KEY (id_grade) REFERENCES grade(id_grade) ON DELETE CASCADE;


--
-- TOC entry 2058 (class 2606 OID 33768)
-- Dependencies: 168 170 2024 2096
-- Name: fk_grade_periodo_id_periodo__periodo_id_periodo; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY grade_periodo
    ADD CONSTRAINT fk_grade_periodo_id_periodo__periodo_id_periodo FOREIGN KEY (id_periodo) REFERENCES periodo(id_periodo) ON DELETE CASCADE;


--
-- TOC entry 2064 (class 2606 OID 50656)
-- Dependencies: 180 2032 174 2096
-- Name: fk_pergunta_id_usuario__usuario_id_usuario; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pergunta
    ADD CONSTRAINT fk_pergunta_id_usuario__usuario_id_usuario FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario) ON DELETE CASCADE;


--
-- TOC entry 2062 (class 2606 OID 33819)
-- Dependencies: 178 2020 164 2096
-- Name: fk_prova_id_anosemestre__anosemestre_id_anosemestre; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY prova
    ADD CONSTRAINT fk_prova_id_anosemestre__anosemestre_id_anosemestre FOREIGN KEY (id_anosemestre) REFERENCES anosemestre(id_anosemestre) ON DELETE CASCADE;


--
-- TOC entry 2063 (class 2606 OID 33824)
-- Dependencies: 170 2026 178 2096
-- Name: fk_prova_id_grade_periodo__grade_periodo_id_grade_periodo; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY prova
    ADD CONSTRAINT fk_prova_id_grade_periodo__grade_periodo_id_grade_periodo FOREIGN KEY (id_grade_periodo) REFERENCES grade_periodo(id_grade_periodo) ON DELETE CASCADE;


--
-- TOC entry 2066 (class 2606 OID 33877)
-- Dependencies: 182 180 2036 2096
-- Name: fk_prova_pergunta_id_pergunta__pergunta_id_pergunta; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY prova_pergunta
    ADD CONSTRAINT fk_prova_pergunta_id_pergunta__pergunta_id_pergunta FOREIGN KEY (id_pergunta) REFERENCES pergunta(id_pergunta) ON DELETE CASCADE;


--
-- TOC entry 2065 (class 2606 OID 33872)
-- Dependencies: 182 178 2034 2096
-- Name: fk_prova_pergunta_id_prova__prova_id_prova; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY prova_pergunta
    ADD CONSTRAINT fk_prova_pergunta_id_prova__prova_id_prova FOREIGN KEY (id_prova) REFERENCES prova(id_prova) ON DELETE CASCADE;


--
-- TOC entry 2072 (class 2606 OID 50558)
-- Dependencies: 2036 192 180 2096
-- Name: fk_resposta_id_pergunta__pergunta_id_pergunta; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY resposta
    ADD CONSTRAINT fk_resposta_id_pergunta__pergunta_id_pergunta FOREIGN KEY (id_pergunta) REFERENCES pergunta(id_pergunta) ON DELETE CASCADE;


--
-- TOC entry 2059 (class 2606 OID 33790)
-- Dependencies: 2028 172 174 2096
-- Name: fk_usuario_id_grupo__grupo_id_grupo; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT fk_usuario_id_grupo__grupo_id_grupo FOREIGN KEY (id_grupo) REFERENCES grupo(id_grupo) ON DELETE CASCADE;


--
-- TOC entry 2101 (class 0 OID 0)
-- Dependencies: 5
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2012-09-13 16:43:00 BRT

--
-- PostgreSQL database dump complete
--

