export default function PokemonCard({ pokemon }) {

  // 🔥 Safe guards (prevents crash)
  const types = pokemon.types || [];
  const abilities = pokemon.abilities || [];
  const moves = pokemon.moves || [];
  const stats = pokemon.stats || {};

  return (
    <div className="poke-card">

      <div className="header">
        <h1>{pokemon.name?.toUpperCase()}</h1>
        <p># {pokemon.id}</p>
      </div>

      <img className="poke-img" src={pokemon.imageUrl} alt={pokemon.name} />

      <div className="details">

        {/* Types */}
        <div className="section">
          <h3>Types</h3>
          <div className="type-container">
            {types.map(t => (
              <span key={t} className={`type-badge ${t}`}>{t}</span>
            ))}
          </div>
        </div>

        {/* Abilities */}
        <div className="section">
          <h3>Abilities</h3>
          <div className="ability-container">
            {abilities.map(a => (
              <span key={a} className="ability-badge">{a}</span>
            ))}
          </div>
        </div>

        {/* Base Experience */}
        <div className="section">
          <h3>Base Experience</h3>
          <p className="base-exp">{pokemon.baseExperience}</p>
        </div>

        {/* Moves */}
        <h3 className="section-title">Top Moves</h3>
        <ul className="moves-list">
          {moves.map(m => (
            <li key={m}>{m}</li>
          ))}
        </ul>

        {/* Physical Stats */}
        <div className="section">
          <h3>Physical Stats</h3>
          <p><strong>Height:</strong> {pokemon.height} m</p>
          <p><strong>Weight:</strong> {pokemon.weight} kg</p>
        </div>

        {/* Battle Stats */}
        <div className="section">
          <h3>Battle Stats</h3>

          {Object.entries(stats).map(([key, value]) => (
            <div className="stat-row" key={key}>
              <span className="stat-name">{key.toUpperCase()}:</span>

              <div className="stat-bar">
                <div className="stat-fill" style={{ width: `${value}%` }}></div>
              </div>

              <span className="stat-value">{value}</span>
            </div>
          ))}

        </div>

      </div>
    </div>
  );
}
