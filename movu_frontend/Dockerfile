# Use Node LTS
FROM node:18-alpine

WORKDIR /app

# Copia package.json e package-lock.json
COPY package*.json ./

# Instala dependências
RUN npm install

# Copia o restante do código
COPY . .

# Expõe porta
EXPOSE 5173

# Comando padrão para desenvolvimento
CMD ["npm", "run", "dev"]
