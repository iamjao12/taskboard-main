-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 25/11/2024 às 03:51
-- Versão do servidor: 10.4.32-MariaDB
-- Versão do PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `taskboard`
--
CREATE DATABASE IF NOT EXISTS `taskboard` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `taskboard`;

-- --------------------------------------------------------

--
-- Estrutura para tabela `gerentes`
--

CREATE TABLE `gerentes` (
  `email` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `ger_id` int(11) NOT NULL,
  `tel` varchar(255) NOT NULL,
  `ger_cargo` varchar(255) NOT NULL,
  `ger_nivel_acesso` int(11) NOT NULL,
  `projeto_id` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `gerentes`
--

INSERT INTO `gerentes` (`email`, `nome`, `ger_id`, `tel`, `ger_cargo`, `ger_nivel_acesso`, `projeto_id`) VALUES
('joao.moraes23@fatec.sp.gov.br', 'João Victor Nogueira', 1, '11998333422', 'Gerente de Software', 3, '1'),
('katiamoraess@hotmail.com', 'Katia Moraes', 2, '11998333422', 'Gerente de Vendas', 2, '1'),
('joao.moraes23@fatec.sp.gov.br', 'João Victor Moraes', 4, '11998333422', 'Gerente de Software', 2, '3');

-- --------------------------------------------------------

--
-- Estrutura para tabela `membros`
--

CREATE TABLE `membros` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `cargo` varchar(255) DEFAULT NULL,
  `mem_id` varchar(255) NOT NULL,
  `numero` varchar(255) DEFAULT NULL,
  `mem_cargo` varchar(255) NOT NULL,
  `tel` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `projetos`
--

CREATE TABLE `projetos` (
  `pro_codigo` varchar(255) NOT NULL,
  `pro_data_fim` date DEFAULT NULL,
  `pro_data_inicio` date DEFAULT NULL,
  `pro_nome` varchar(100) NOT NULL,
  `pro_status` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `projetos`
--

INSERT INTO `projetos` (`pro_codigo`, `pro_data_fim`, `pro_data_inicio`, `pro_nome`, `pro_status`) VALUES
('1', '2024-11-30', '2024-11-05', 'Desenvolvimento de Planta da SoftHaus', 'Em andamento'),
('3', '2024-11-19', '2024-11-13', 'Teste do Software 12', 'Encerrado');

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `gerentes`
--
ALTER TABLE `gerentes`
  ADD PRIMARY KEY (`ger_id`),
  ADD KEY `FKqsxc3hu56seqxf71q6kes7ybs` (`projeto_id`);

--
-- Índices de tabela `membros`
--
ALTER TABLE `membros`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `projetos`
--
ALTER TABLE `projetos`
  ADD PRIMARY KEY (`pro_codigo`);

--
-- AUTO_INCREMENT para tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `gerentes`
--
ALTER TABLE `gerentes`
  MODIFY `ger_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de tabela `membros`
--
ALTER TABLE `membros`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- Restrições para tabelas despejadas
--

--
-- Restrições para tabelas `gerentes`
--
ALTER TABLE `gerentes`
  ADD CONSTRAINT `FKqsxc3hu56seqxf71q6kes7ybs` FOREIGN KEY (`projeto_id`) REFERENCES `projetos` (`pro_codigo`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
