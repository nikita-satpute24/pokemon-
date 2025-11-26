import { useState } from "react";
import SearchBar from "./components/SearchBar";
import PokemonCard from "./components/PokemonCard";
import { getPokemon } from "./services/pokemonService";
import "./App.css";

function App() {
  const [pokemon, setPokemon] = useState(null);
  const [error, setError] = useState("");

  const handleSearch = async (name) => {
    try {
      setError("");
      const data = await getPokemon(name);
      setPokemon(data);
    } catch (err) {
      setPokemon(null);
      setError("Pokemon not found! Try again.");
    }
  };

  return (
    <div className="app-container">
      <h1 className="title">Pokedex Search</h1>

      <div className="search-area">
        <SearchBar onSearch={handleSearch} />
      </div>

      <div className="content-area">
        {error && <p className="error-message">{error}</p>}
        {pokemon && <PokemonCard pokemon={pokemon} />}
      </div>
    </div>
  );
}

export default App;
