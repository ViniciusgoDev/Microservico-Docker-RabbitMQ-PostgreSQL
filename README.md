# 🐳 Projeto de Microserviços com Docker, RabbitMQ e PostgreSQL

Alguns pontos importantes antes de iniciar: os microserviços são bem simplificados, a intenção aqui é compreender Mensageria e Docker. Tenho outras APIs mais robustas, você pode dar uma olhada no Git. Enfim, vamos lá.

Este é um projeto desenvolvido para prática e aprendizado em **Microserviços**, **Mensageria** com **RabbitMQ** e **Docker**. Ele é composto por **três microserviços** que se comunicam através de filas no RabbitMQ, além de um banco de dados PostgreSQL.

## 📚 Visão Geral

Os microserviços implementados são:

1. **Order Service**: Responsável por criar e gerenciar pedidos.
2. **Tracking Service**: Simula o processo de rastreamento de pedidos.
3. **Notification Service**: Simula o envio de notificações.

A arquitetura do projeto utiliza o **Docker Compose** para subir todos os serviços, incluindo as instâncias de RabbitMQ e PostgreSQL.

## 🛠️ Tecnologias Utilizadas

- **Docker** e **Docker Compose**: Para conteinerização e orquestração dos serviços.
- **RabbitMQ**: Sistema de mensageria para a comunicação assíncrona entre os microserviços.
- **PostgreSQL**: Banco de dados relacional para persistência das informações.

## 📊 Arquitetura dos Microserviços

### 1. Order Service

- **Funções:**
    - Criar e gerenciar pedidos.
    - Persistir pedidos no banco de dados PostgreSQL.
    - Publicar mensagens em duas filas:
      - `ORDER_CREATED_QUEUE`
      - `ORDER_NOTIFICATION_QUEUE`
- **Endpoints:**
    - Criar novo pedido: `POST /orders`
    - Consultar pedido: `GET /orders/{id}`

### 2. Tracking Service

- **Funções:**
    - Ouve a fila `ORDER_CREATED_QUEUE`.
    - Simula a contratação de uma transportadora e gera um código de rastreio (via uma API externa simulada).
    - Publica uma mensagem de atualização na fila `ORDER_UPDATED_QUEUE` com o código de rastreio e status do transporte e manten atualizando sobre status do transporte.

### 3. Notification Service

- **Funções:**
    - Ouve a fila `ORDER_NOTIFICATION_QUEUE`.
    - Simula o envio de notificações aos clientes.

## 📂 Estrutura do Projeto

```
.
├── order-service/
│    ├── Dockerfile
│    └── ...
├── tracking-service/
│    ├── Dockerfile
│    └── ...
├── notification-service/
│    ├── Dockerfile
│    └── ...
└── docker-compose.yml
```

## ▶️ Executando o Projeto

1. Certifique-se de ter o **Docker** e o **Docker Compose** instalados na sua máquina.

2. Clone o repositório:

   ```bash
   git clone 
   cd nome-do-repositorio
   ```

3. Suba os serviços com Docker Compose:

   ```bash
   docker-compose up --build
   ```

