# Usa a versão recomendada do Node
FROM node:22.12.0

# Define o diretório de trabalho
WORKDIR /app

# Copia o package.json e o package-lock.json
COPY package*.json ./

# Instala dependências
RUN npm install

# Copia o restante do projeto
COPY . .

# Define variável de ambiente para o Vite (se precisar)
ENV NODE_ENV=development
ENV VITE_API_URL=http://localhost:8080

# Expõe a porta padrão do Vite
EXPOSE 3000

# Comando padrão
CMD ["npm", "run", "dev"]
