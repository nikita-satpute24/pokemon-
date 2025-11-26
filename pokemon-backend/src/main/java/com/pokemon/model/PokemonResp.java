package com.pokemon.model;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class PokemonResp 
{
	private int id;
	private String name;
	private double height;
	private double weight;
	private List<String> abilities;
	private List<String> types;
	private String imageUrl;
	private int baseExperience;
	private List<String> moves;
	private Map<String, Integer> stats;

	
}
