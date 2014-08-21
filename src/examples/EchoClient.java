package examples;

import java.lang.System;

class EchoClient
{
	
	/*
	http://sim.smogon.com:8080/Stats/2014-04/
		http://sim.smogon.com:8080/Stats/2013-05/
			http://sim.smogon.com:8080/Stats/2013-05/
				http://sim.smogon.com:8080/Stats/2013-05/
					http://sim.smogon.com:8080/Stats/2013-05/
						http://sim.smogon.com:8080/Stats/2013-05/
							http://sim.smogon.com:8080/Stats/2013-05/
								http://sim.smogon.com:8080/Stats/2013-05/
									http://sim.smogon.com:8080/Stats/2013-05/
										http://sim.smogon.com:8080/Stats/2013-05/
											http://sim.smogon.com:8080/Stats/2013-05/
												http://sim.smogon.com:8080/Stats/2013-05/
													http://sim.smogon.com:8080/Stats/2013-05/
														http://sim.smogon.com:8080/Stats/2013-05/
															http://sim.smogon.com:8080/Stats/2013-05/
																http://sim.smogon.com:8080/Stats/2013-05/
																	http://sim.smogon.com:8080/Stats/2013-05/
																		http://sim.smogon.com:8080/Stats/2013-05/
																*/
	public static void main(String[] args)
	{
		System.out.println(kek("any"));
		System.out.println(kek("normal"));
		System.out.println(kek("adjacentFoe"));
		System.out.println(kek("allAdjacentFoes"));
		System.out.println(kek("adjacentAllyOrSelf"));
		System.out.println(kek("allyTeam"));
		System.out.println(kek("self"));
		System.out.println(kek("allAdjacent"));
		System.out.println(kek("adjacentAlly"));
		System.out.println(kek("all"));
		System.out.println(kek("scripted"));
		System.out.println(kek("randomNormal"));
		System.out.println(kek("allAdjacentFoes"));
		System.out.println(kek("allySide"));
		System.out.println(kek("foeSide"));
	}
	static String kek(String trrget)
	{
		switch(trrget)
		{
		case "any":
			return"Range.Any1";
		case "normal":
		case "adjacentFoe":
			return"Range.Adjacent1";
		case "allAdjacentFoes":
			return"Range.Opponent2";
		case "adjacentAllyOrSelf":
			return"Range.AllyOrSelf";
		case "allyTeam":
			return"Range.Allies";
		case "self":
			return"Range.Self";
		case "allAdjacent":
			return"Range.AllAdjacent";
		case "adjacentAlly":
			return"Range.AdjacentAlly";
		case "all":
			return"Range.All";
		case "scripted":
			return"Range.Scripted";
		case "randomNormal":
			return"Range.Random";
		case "allySide":
			return"Range.AllySide";
		case "foeSide":
			return"Range.FoeSide";
		}
		return "HURHURHUR";
	}
}