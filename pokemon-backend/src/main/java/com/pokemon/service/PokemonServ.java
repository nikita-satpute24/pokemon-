package com.pokemon.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pokemon.exception.PokemonNotFoundException;
import com.pokemon.model.PokemonResp;

@Service
public class PokemonServ 
{
	private final String API_URL = "https://pokeapi.co/api/v2/pokemon/";
	private final RestTemplate restTemp = new RestTemplate();
	private final ObjectMapper Mapper = new ObjectMapper();
	
	@Cacheable(value = "pokemonCache", key = "#name.toLowerCase()")
	public PokemonResp getPokemon(String name) throws Exception
	{
		String url = API_URL + name.toLowerCase();
		
		String resp;
		try {
			resp = restTemp.getForObject(url, String.class);
			
			if (resp == null) {
	            throw new PokemonNotFoundException("Pokemon Not Found");
	        }
		} catch (HttpClientErrorException.NotFound  e) {
			throw new PokemonNotFoundException("Pokemon Not Found");
		}
		
		JsonNode root = Mapper.readTree(resp);
		
		
		PokemonResp pokemon = new PokemonResp();
		
		pokemon.setId(root.get("id").asInt());
		pokemon.setName(root.get("name").asText());
		
		int rawHeight = root.get("height").asInt();
		int rawWeight = root.get("weight").asInt();
		
		double heightMeters = rawHeight / 10.0;
		double weightkg = rawWeight / 10.0;
		
		pokemon.setHeight(heightMeters);
		pokemon.setWeight(weightkg);
		
		List<String> abilities = new ArrayList<String>();
		
		root.get("abilities").forEach(a -> abilities.add(a.get("ability").get("name").asText()));
		
		pokemon.setAbilities(abilities);
		
		List<String> types = new ArrayList<String>();
		root.get("types").forEach(t -> types.add(t.get("type").get("name").asText()));
		
		pokemon.setTypes(types);
		
		JsonNode sprites = root.get("sprites");
		String img = sprites.path("other").path("official-artwork").path("front_default").asText();
		
		if(img == null || img.isEmpty() || img.equals("null"))
		{
			img = sprites.path("other").path("home").path("front_default").asText();
		}
		
		if (img == null || img.isEmpty() || img.equals("null")) {
		    img = sprites.path("front_default").asText();
		}
		pokemon.setImageUrl(img);
		
		pokemon.setBaseExperience(root.get("base_experience").asInt());

		Map<String, Integer> stats = new HashMap<String, Integer>();

		root.get("stats").forEach(s -> {
		    String statName = s.get("stat").get("name").asText();
		    int statValue = s.get("base_stat").asInt();
		    stats.put(statName, statValue);
		});

		pokemon.setStats(stats);
		
		List<String> moves = new ArrayList<String>();
		root.get("moves").forEach(m -> {
		    if (moves.size() < 5) {
		        moves.add(m.get("move").get("name").asText());
		    }
		});
		pokemon.setMoves(moves);


		return pokemon;
		
				
	}
}
