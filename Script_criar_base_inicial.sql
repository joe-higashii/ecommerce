insert into tipo_usuario 
(tipo_usuario)
values
('admin'), ('cliente');

insert into usuario
(ativo, cod_usuario, dt_cadastro, email, nome, senha, telefone, tipo_usuario_id)
values
(true, '000001', '2023-10-20 00:00:00', 'admin@admin.com', 'Admin', '123456', '0000-0000', 1),
(true, '000002', '2023-10-20 00:00:00', 'cliente@cliente.com', 'Cliente', '123456', '0000-0000', 2);

insert into categoria
(ativo, cod_categoria, descricao)
values
(true, '000001', 'Ferragens'),
(true, '000002', 'Eletrica'),
(true, '000003', 'Maquinas'),
(true, '000004', 'EPI'),
(true, '000005', 'Construcao');

insert into forma_de_pagamento
(ativo, cod_pagamento, descricao)
values
(true, '000001', 'Dinheiro'),
(true, '000002', 'Debito'),
(true, '000003', 'Credito'),
(true, '000004', 'Pix'),
(true, '000005', 'Cheque');

insert into produto
(ativo, cod_prod, obs, preco_venda, prod_nome, qtd_est, categoria_id)
values
(true, '000001', 'Chave eletrica', 79.99, 'Chave de fenda eletrica', 100, 1),
(true, '000002', 'Fio 220v', 2.99, 'Fio Preto', 50, 2),
(true, '000003', 'Furadeira', 299.99, 'Furadeira Makita', 30, 3),
(true, '000004', 'Capacete', 19.99, 'Capacete para Obras', 200, 4),
(true, '000005', 'Cimento', 9.99, 'Saco de Cimento de 5KG', 100, 5),
(true, '000006', 'Prego', 1.39, 'Saco de Pregos', 500, 1),
(true, '000007', 'Disjuntor', 49.99, 'Disjuntor de 100V', 50, 2),
(true, '000008', 'Esmeril', 699.99, 'Esmeril Makita', 10, 3),
(true, '000009', 'Oculos', 14.99, 'Oculos protetor', 100, 4),
(true, '000010', 'Areia', 7.99, 'Saco de Areia de 5KG', 110, 5),
(true, '000011', 'Martelo', 25.99, 'Martelo pequeno', 40, 1),
(true, '000012', 'Filtro de Linha', 69.99, 'Filtro de Linha 5 tomadas com DSP', 30, 2),
(true, '000013', 'Parafusadeira', 759.99, 'Parafusadeira Makita', 10, 3),
(true, '000014', 'Bota', 39.99, 'Bota Anti Eletricidade', 100, 4),
(true, '000015', 'Tinta', 34.99, 'Galão de Tinta Suvinil', 50, 5);
