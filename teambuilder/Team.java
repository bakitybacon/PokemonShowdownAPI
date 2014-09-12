package teambuilder;

import java.util.ArrayList;

public class Team extends ArrayList<Pokemon>
{
	private static final long serialVersionUID = 1L;
	
	public Team(Pokemon ... pokemons)
	{
		for(Pokemon poke : pokemons)
			add(poke);
	}
	
	public Pokemon getByID(Species species)
	{
		for(Pokemon poke : this)
		{
			if(species.equals(poke.getSpecies()))
			{
				return poke;
			}
		}
		return new Pokemon(SpeciesList.placeholder);
	}
}
