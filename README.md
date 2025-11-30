# **TaskLab**

TaskLab é uma aplicação Java desenvolvida com Spring Boot, focada no gerenciamento de tarefas de forma simples, moderna e escalável.  
Este projeto utiliza boas práticas de arquitetura, migração de banco, segurança e containerização com Docker.

---

## 🚀 Tecnologias Utilizadas

### **Linguagem & Frameworks**
![Java](https://img.shields.io/badge/Java-21-007396?style=for-the-badge&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-6DB33F?style=for-the-badge&logo=springboot)
![Spring Security](https://img.shields.io/badge/Spring%20Security-secure-6DB33F?style=for-the-badge&logo=springsecurity)

### **Build & Dependências**
![Gradle](https://img.shields.io/badge/Gradle-build-02303A?style=for-the-badge&logo=gradle)

### **Banco & Migração**
![MySQL](https://img.shields.io/badge/MySQL-Database-4479A1?style=for-the-badge&logo=mysql)
![Flyway](https://img.shields.io/badge/Flyway-Migrations-CC0200?style=for-the-badge&logo=flyway)

### **Infraestrutura**
![Docker](https://img.shields.io/badge/Docker-Container-2496ED?style=for-the-badge&logo=docker)
![Git](https://img.shields.io/badge/Git-Version%20Control-F05032?style=for-the-badge&logo=git)

---

## 📌 Pré-requisitos

Certifique-se de ter instalado:

- Java 21
- Docker
- Git
- Gradle (opcional, o wrapper já está incluso)

---

## ⚙️ Configuração do Ambiente

### 🔁 Clonar o Repositório
```bash
git clone https://github.com/Joaomacosdev/task-lab.git
cd task-lab
```

---

## 🗄️ Configuração do Banco de Dados

O projeto utiliza **MySQL** via Docker com configuração no `docker-compose.yml`.

Para iniciar o banco:

```bash
docker-compose up -d
```

O banco estará disponível em:

```
localhost:3306
```

---

## ▶️ Executando o Projeto

### Usando o Gradle Wrapper
```bash
./gradlew bootRun
```

### Usando Docker

#### 1. Criar imagem
```bash
docker build -t tasklab .
```

#### 2. Subir container
```bash
docker run -p 8080:8080 tasklab
```

Aplicação disponível em:

```
http://localhost:8080
```

---

## 📁 Estrutura do Projeto

```
├── src
│   ├── main
│   │   ├── java/com/tasklab
│   │   ├── resources
│   │   │   ├── application.yml
│   │   │   ├── db/migration  (migrations Flyway)
├── build.gradle
├── Dockerfile
├── docker-compose.yml
└── README.md
```

---

## 🌐 Endpoints Principais

| Método | Endpoint         | Descrição                  |
|--------|------------------|----------------------------|
| GET    | /api/usuarios    | Lista usuários             |
| POST   | /api/usuarios    | Cria novo usuário          |
| GET    | /api/saude       | Verifica status da API     |

---

## 🤝 Contribuição

1. Faça um fork
2. Crie uma branch:
   ```bash
   git checkout -b minha-feature
   ```
3. Commit:
   ```bash
   git commit -m "Minha feature"
   ```
4. Push:
   ```bash
   git push origin minha-feature
   ```
5. Abra o Pull Request

---

## 📄 Licença

Este projeto está sob a **Licença Apache 2.0**.  
Consulte o arquivo `LICENSE` para mais detalhes.

---
