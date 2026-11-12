# TDMXP — ambiente inicial organizado

Projeto inicial com frontend estático (HTML/CSS/JavaScript), API Java 21 + Spring Boot e PostgreSQL, orquestrados pelo Docker Compose.

## Requisitos
- Docker Desktop (ou Docker Engine + Compose)
- Git (opcional)

## Primeira execução
1. Na raiz do projeto, copie `.env.example` para `.env`.
2. Edite `.env` e defina uma senha forte em `DB_PASSWORD`.
3. Execute:

   ```bash
   docker compose up -d --build
   ```

4. Acesse:
   - Frontend: http://localhost:3000
   - API: http://localhost:8080/api/health
   - PostgreSQL: `localhost:5432` (acesso local por DBeaver)

## Comandos úteis
```bash
docker compose ps
docker compose logs -f
docker compose logs -f backend
docker compose down
```

`docker compose down` preserva o volume do banco. **Não use `docker compose down -v` se quiser manter os dados.**

## Comunicação entre containers
O backend conecta ao banco pelo hostname `postgres` na porta `5432`. O navegador acessa o frontend em `localhost:3000`; o Java disponibiliza a API em `localhost:8080`.

O frontend usa `/api` como caminho de API. O Nginx encaminha `/api/` para o serviço `backend:8080`, evitando depender de `localhost` dentro do container do navegador.

## Estrutura
- `frontend/`: HTML, CSS, JavaScript e Nginx.
- `backend/`: aplicação Spring Boot, entidades JPA, DTOs, repositories, services, controllers e tratamento global de exceções.
- `docs/`: documentação e SQL de referência.
- `.devcontainer/`: configuração de desenvolvimento VS Code/Codespaces.

## Observações importantes
- Este ZIP é uma base organizada inicial, não uma cópia integral do seu repositório atual. Os arquivos existentes que só apareceram nas capturas de tela (por exemplo, seu `index.html`, `style.css` e código Java atual) não foram incluídos porque não foram enviados como arquivos.
- As tabelas são criadas/atualizadas pelo Hibernate em desenvolvimento (`ddl-auto=update`). Para produção, prefira migrations com Flyway.
- Autenticação, autorização, integração real com Firecrawl/Resend/Devin, regras completas de alertas e testes de negócio ainda precisam ser implementados.
- As credenciais reais devem ficar somente no `.env`, nunca no Git.

## Comandos do dia
# Subir tudo (em segundo plano)
docker compose up -d
# Ver o status
docker compose ps
# Ver logs em tempo real
docker compose logs -f backend
# Parar (mantém os dados do banco)
docker compose stop
# Parar e remover os containers (mantém os dados do banco)
docker compose down