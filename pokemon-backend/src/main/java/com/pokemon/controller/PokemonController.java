package com.pokemon.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pokemon.model.PokemonResp;
import com.pokemon.service.PokemonServ;

@RestController
@RequestMapping("/api/pokemon")
@CrossOrigin("*")
public class PokemonController {

	private final PokemonServ pokemonService;
	
	public PokemonController(PokemonServ pokemonService)
	{
		this.pokemonService = pokemonService;
	}
	
	@GetMapping("/{name}")
	public PokemonResp getPokemon(@PathVariable String name) throws Exception
	{
		return pokemonService.getPokemon(name);
	}
	
}
