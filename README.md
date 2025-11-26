
📘 Pokedex Search Engine – Full Stack Project

A simple and fast Pokédex Search Application built using:

Backend: Java, Spring Boot

Frontend: React.js

Cache: Caffeine Cache (for performance)

API Source: https://pokeapi.co

This application allows users to search for any Pokémon by name and view detailed information like image, abilities, stats, types, height, weight, moves, and more.

🚀 Features
⭐ Frontend (React)

Modern UI with animated components

Search bar with loading state & error popup

Shows:

Pokémon Name & ID

Pokémon Official Artwork

Types (with color badges)

Abilities

Base Experience

Height (meters)

Weight (kg)

Top moves

Stats (HP, Attack, Defense, Speed etc.) with animated bars

Fully responsive and user-friendly

⭐ Backend (Spring Boot)

REST endpoint:

GET /api/pokemon/{name}


Fetches live data from PokeAPI

Converts API response into clean JSON

Uses Caffeine Cache:

Cache expiry: 10 minutes

Max entries: 100

Faster response for repeated queries

Proper 404 JSON error handling

Clean service-controller architecture

🧠 Tech Stack
Layer	Technology
Backend	Java 23, Spring Boot 3.x
Cache	Caffeine Cache
Frontend	React.js (CRA)
HTTP Client	Axios
Build Tool	Maven
API Source	https://pokeapi.co
📡 API Documentation
GET /api/pokemon/{name}

Fetch Pokémon details by name (case-insensitive).

✔ Example:
GET http://localhost:8080/api/pokemon/pikachu

✔ Successful Response:
{
  "id": 25,
  "name": "pikachu",
  "height": 0.4,
  "weight": 6.0,
  "abilities": ["static", "lightning-rod"],
  "types": ["electric"],
  "baseExperience": 112,
  "moves": ["quick-attack", "iron-tail", "thunderbolt", "agility", "volt-tackle"],
  "stats": {
    "hp": 35,
    "attack": 55,
    "defense": 40,
    "special-attack": 50,
    "special-defense": 50,
    "speed": 90
  },
  "imageUrl": "https://raw.githubusercontent.com/.../official-artwork/25.png"
}

❗ Error Handling (404 JSON)

If Pokémon does NOT exist:

GET /api/pokemon/xyz


Returns:

{
  "timestamp": "2025-11-26T10:45:10",
  "status": 404,
  "error": "Not Found",
  "message": "Pokémon not found",
  "path": "/api/pokemon/xyz"
}


Handled via a GlobalExceptionHandler.

🚀 Running the Project Locally
1. Run Backend (Spring Boot)

Open terminal:

cd backend
mvn clean install
mvn spring-boot:run


Backend runs on:

http://localhost:8080


Test:

http://localhost:8080/api/pokemon/pikachu

2. Run Frontend (React)

Open a second terminal:

cd frontend
npm install
npm start


Frontend runs on:

http://localhost:3000

⚙️ Caching Strategy (Performance)

Project uses Caffeine Cache to speed up repeated queries.

Cache Configuration:

expireAfterWrite: 10 minutes

maximumSize: 100 entries

Cache Name: pokemonCache

This ensures:
✔ Fewer calls to PokeAPI
✔ Faster response time
✔ Better performance under heavy use

📁 Project Structure
pokedex-project/
│
├── backend/
│   ├── src/main/java/com/pokemon
│   │   ├── controller/
│   │   ├── service/
│   │   ├── model/
│   │   ├── config/  (CacheConfig)
│   │   └── exception/ (GlobalExceptionHandler)
│   ├── pom.xml
│
├── frontend/
│   ├── src/
│   │   ├── components/
│   │   ├── App.js
│   │   ├── App.css
│   └── package.json
│
└── README.md

🧪 Testing

Use browser or Postman:

GET http://localhost:8080/api/pokemon/pikachu


Frontend automatically fetches from backend and shows results beautifully.
