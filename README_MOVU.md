# 🌐 MOVU – Ambiente de Desenvolvimento

> MVP do sistema MOVU – uma plataforma que conecta **empresas** e **freelancers** de forma ágil, inovadora e escalável.  
> Este repositório contém o **backend (Spring Boot)**, o **frontend (React/Vite)** e o **banco de dados (MySQL)** orquestrados com **Docker Compose**.

---

## 🚀 Estrutura do Projeto

```
movu-web/
├── backend/          # API Spring Boot (porta 8080)
├── movu_frontend/         # Interface React (porta 3000)
├── docker-compose.yml
├── .env.example
└── README.md
```

---

## ⚙️ Pré-requisitos

Antes de iniciar, instale:

| Ferramenta | Versão recomendada | Descrição |
|-------------|--------------------|------------|
| [Git](https://git-scm.com/) | ≥ 2.30 | Controle de versão |
| [Docker Desktop](https://www.docker.com/products/docker-desktop/) | ≥ 4.30 | Containers |
| [Java JDK](https://adoptium.net/) | 17+ | Necessário para o backend |
| [Node.js](https://nodejs.org/en/) | ≥ 20.19 | Necessário para o frontend |
| [Maven](https://maven.apache.org/download.cgi) | ≥ 3.9 | Build do backend (se local) |

---

## 🧩 Passo a Passo de Instalação

1. **Clone o repositório**

```bash
git clone https://github.com/MarcioCalisto/movu-web.git
cd movu-web
```

2. **Crie o arquivo `.env`**

```bash
cp .env.example .env
```

O arquivo `.env` define as variáveis usadas no `docker-compose.yml` e no backend:

```bash
# .env
APP_PORT_BACKEND=8080
APP_PORT_FRONTEND=3000
MYSQL_ROOT_PASSWORD=movu_root_pass
MYSQL_DATABASE=movu_db
MYSQL_USER=movu_user
MYSQL_PASSWORD=movu_pass
JWT_SECRET=
```

3. **Suba o ambiente**

```bash
docker compose up --build -d
```

4. **Verifique os containers**

```bash
docker ps
```

Você deve ver:
- `movu_mysql` (MySQL 8.0)
- `movu_backend` (Spring Boot)
- `movu_frontend` (React)

---

## 🧠 Comunicação entre Serviços

| Serviço | Porta | Função | Comunicação |
|----------|--------|--------|--------------|
| MySQL | 3306 | Banco de dados | Usado pelo backend |
| Backend (Spring Boot) | 8080 | API REST | Conecta ao DB e expõe API |
| Frontend (Vite/React) | 3000 | Interface Web | Consome API do backend |

O backend conecta ao banco via hostname **`db`**, configurado no `docker-compose.yml`:
```
spring.datasource.url=jdbc:mysql://db:3306/movu_db
```

O frontend consome a API via variável:
```
VITE_API_URL=http://localhost:8080
```

---

## 🔍 Testando a Aplicação

### ✅ Backend (API)
Verifique se o backend está no ar:
```bash
curl http://localhost:8080/actuator/health
# Esperado: {"status":"UP"}
```

### ✅ Banco de Dados
Acesse o MySQL dentro do container:
```bash
docker exec -it movu_mysql mysql -u movu_user -p
# senha: movu_pass
USE movu_db;
SHOW TABLES;
```

### ✅ Frontend
Abra o navegador:
```
http://localhost:3000
```

Abra o DevTools → Network e confirme chamadas para `http://localhost:8080`.

---

## 🧱 Endpoints Básicos

### AuthController
| Método | Endpoint | Descrição |
|---------|-----------|------------|
| POST | `/api/auth/register` | Cria um novo usuário |
| POST | `/api/auth/login` | Realiza login (MVP sem JWT) |

### VacancyController
| Método | Endpoint | Descrição |
|---------|-----------|------------|
| GET | `/api/vacancies` | Lista vagas |
| POST | `/api/vacancies` | Cria nova vaga |
| GET | `/api/vacancies/{id}` | Consulta vaga por ID |

---

## 🩺 Troubleshooting (Erros Comuns)

| Problema | Causa provável | Solução |
|-----------|----------------|----------|
| ❌ Porta 3306 em uso | Outro MySQL local rodando | Pare o serviço local (`net stop mysql`) ou altere a porta no compose |
| ❌ Backend não conecta ao DB | Container `db` ainda inicializando | Reinicie backend: `docker compose restart backend` |
| ❌ Erro CORS no frontend | API bloqueando requisições | Adicione `@CrossOrigin(origins = "http://localhost:3000")` nos controllers |
| ❌ Flyway falhou | Migration duplicada ou DB sujo | `docker compose down -v` e `docker compose up --build` |
| ❌ Vite erro “Node < 20” | Node antigo no container | Troque imagem base para `node:20-alpine` no Dockerfile |

---

## 🧾 Estrutura Git

- **main** → produção  
- **develop** → integração  
- **feature/** → desenvolvimento de novas features  

Exemplo de fluxo:
```bash
git checkout -b feature/frontend-layout
# faça mudanças
git add .
git commit -m "feat(frontend): cria layout base"
git push origin feature/frontend-layout
```

Depois, abra um **Pull Request → develop → main**.

---

## 🧰 Comandos Úteis

```bash
# Parar tudo
docker compose down

# Parar e limpar volumes
docker compose down -v

# Ver logs de um container
docker compose logs -f backend

# Rebuildar frontend
docker compose build frontend && docker compose up -d frontend

# Acessar o shell do backend
docker exec -it movu_backend sh
```

---

## 📘 Próximos Passos

- [ ] Implementar autenticação JWT no backend  
- [ ] Criar formulário de login/cadastro no frontend  
- [ ] Adicionar migrations adicionais (`V2__add_indexes.sql`)  
- [ ] Documentar ambiente no Notion  
- [ ] Adicionar CI/CD com GitHub Actions  

---

**Autor:** [Márcio Calisto Freitas Barros](https://github.com/MarcioCalisto)  
💡 *Startup MOVU – Conectando talentos ao futuro do trabalho.*
