package teambuilder;

public enum Type 
{
	 Normal,Fire,Water,Electric,Grass,Ice,Fighting,
	 Poison,Ground,Flying,Psychic,Bug,Rock,Ghost,
	 Dragon,Dark,Steel,Fairy;
	 
	 public static final Type[] alltypes = 
		 {Normal,Fire,Water,Electric,Grass,Ice,Fighting,
			 Poison,Ground,Flying,Psychic,Bug,Rock,Ghost,
			 Dragon,Dark,Steel,Fairy};
	 
	 public Type stringToType(String type)
	 {
		 String lowe = type.toLowerCase();
		 switch(lowe)
		 {
		 case "normal":
			 return Normal;
		 case "fire":
			 return Fire;
		 case "water":
			 return Water;
		 case "electric":
			 return Electric;
		 case "grass":
			 return Grass;
		 case "ice":
			 return Ice;
		 case "fighting":
			 return Fighting;
		 case "poison":
			 return Poison;
		 case "ground":
			 return Ground;
		 case "flying":
			 return Flying;
		 case "psychic":
			 return Psychic;
		 case "bug":
			 return Bug;
		 case "rock":
			 return Rock;
		 case "ghost":
			 return Ghost;
		 case "dragon":
			 return Dragon;
		 case "dark":
			 return Dark;
		 case "steel":
			 return Steel;
		 case "fairy":
			 return Fairy;
		 default:
			 return null;
		 }
	 }
	 
	 static double getEffectiveness(Type one, Type two)
	 {
		 switch(one)
		 {
		 case Normal:
			 switch(two)
			 {
			 case Fire:
			 case Water:
			 case Electric:
			 case Grass:
			 case Ice:
			 case Fighting:
			 case Poison:
			 case Ground:
			 case Flying:
			 case Psychic:
			 case Bug:
			 case Dragon:
			 case Dark:
			 case Fairy:
			 case Normal:
				 return 1;
			 case Ghost:
				 return 0;
			 case Rock:
			 case Steel:
				 return 0.5;
			 }
		case Bug:
			switch(two)
			{
			case Dark:
			case Psychic:
			case Grass:
				return 2;
			 case Water:
			 case Electric:
			 case Ice:
			 case Ground:
			 case Bug:
			 case Dragon:
			 case Normal:
			 case Rock:
				 return 1;
			 case Fighting:
			 case Flying:
			 case Poison:
			 case Ghost:
			 case Steel:
			 case Fire:
			 case Fairy:
				 return 0.5;
			}
		case Dark:
			switch(two)
			{
			case Ghost:
			case Psychic:
				return 2;
			 case Normal:
			 case Flying:
			 case Poison:
			 case Ground:
			 case Rock:
			 case Bug:
			 case Steel:
			 case Fire:
			 case Water:
			 case Grass:
			 case Electric:
			 case Ice:
			 case Dragon:
				 return 1;
			 case Fighting:
			 case Dark:
			 case Fairy:
				 return 0.5;
			}
		case Dragon:
			switch(two)
			{
			case Dragon:
				return 2;
			 case Water:
			 case Electric:
			 case Ice:
			 case Ground:
			 case Bug:
			 case Normal:
			 case Fighting:
			 case Flying:
			 case Poison:
			 case Ghost:
			 case Fire:
			 case Rock:
			 case Dark:
			 case Grass:
			 case Psychic:
				 return 1;
			 case Steel:
				 return 0.5;
			 case Fairy:
				 return 0;
			}
		case Electric:
			switch(two)
			{
			case Water:
			case Flying:
				return 2;
			 case Ice:
			 case Bug:
			 case Normal:
			 case Fighting:
			 case Poison:
			 case Ghost:
			 case Fire:
			 case Rock:
			 case Dark:
			 case Psychic:
			 case Fairy:
			 case Steel:
				 return 1;
			 case Grass:
			 case Electric:
			 case Dragon:
				 return 0.5;
			 case Ground:
				 return 0;
			}
		case Fairy:
			switch(two)
			{
			case Dragon:
			case Dark:
			case Fighting:
				return 2;
			 case Water:
			 case Electric:
			 case Ice:
			 case Ground:
			 case Bug:
			 case Normal:
			 case Flying:
			 case Ghost:
			 case Rock:
			 case Grass:
			 case Psychic:
			 case Fairy:
				 return 1;
			 case Poison:
			 case Steel:
			 case Fire:
				 return 0.5;
			}
		case Fighting:
			switch(two)
			{
			case Normal:
			case Rock:
			case Steel:
			case Ice:
			case Dark:
				return 2;
			 case Water:
			 case Electric:
			 case Ground:
			 case Fighting:
			 case Fire:
			 case Grass:
			 case Dragon:
				 return 1;
			 case Flying:
			 case Poison:
			 case Bug:
			 case Psychic:
			 case Fairy:
				 return 0.5;
			 case Ghost:
				 return 0;
			}
		case Fire:
			switch(two)
			{
			case Bug:
			case Steel:
			case Grass:
			case Ice:
				return 2;
			 case Electric:
			 case Ground:
			 case Normal:
			 case Fighting:
			 case Flying:
			 case Poison:
			 case Ghost:
			 case Dark:
			 case Psychic:
			 case Fairy:
				 return 1;
			 case Fire:
			 case Rock:
			 case Water:
			 case Dragon:
				 return 0.5;
			}
		case Flying:
			switch(two)
			{
			case Fighting:
			case Bug:
			case Grass:
				return 2;
			 case Water:
			 case Ice:
			 case Ground:
			 case Normal:
			 case Flying:
			 case Poison:
			 case Ghost:
			 case Fire:
			 case Dark:
			 case Psychic:
			 case Fairy:
			 case Dragon:
				 return 1;
			 case Steel:
			 case Rock:
			 case Electric:
				 return 0.5;
			}
		case Ghost:
			switch(two)
			{
			case Ghost:
			case Psychic:
				return 2;
			 case Water:
			 case Electric:
			 case Ice:
			 case Ground:
			 case Bug:
			 case Fighting:
			 case Flying:
			 case Poison:
			 case Fire:
			 case Rock:
			 case Grass:
			 case Fairy:
			 case Steel:
			 case Dragon:
				 return 1;
			 case Dark:
				 return 0.5;
			 case Normal:
				 return 0;
			}
		case Grass:
			switch(two)
			{
			case Ground:
			case Rock:
			case Water:
				return 2;
			 case Electric:
			 case Ice:
			 case Normal:
			 case Fighting:
			 case Ghost:
			 case Dark:
			 case Psychic:
			 case Fairy:
				 return 1;
			 case Steel:
			 case Dragon:
			 case Flying:
			 case Poison:
			 case Bug:
			 case Fire:
			 case Grass:
				 return 0.5;
			}
		case Ground:
			switch(two)
			{
			case Poison:
			case Rock:
			case Steel:
			case Fire:
			case Electric:
				return 2;
			case Normal:
			case Fighting:
			case Ground:
			case Ghost:
			case Water:
			case Psychic:
			case Ice:
			case Dragon:
			case Dark:
			case Fairy:
				return 1;
			case Bug:
			case Grass:
				return 0.5;
			case Flying:
				return 0;
			}
		case Ice:
			switch(two)
			{
			 case Ground:
			 case Dragon:
			 case Grass:
			 case Flying:
				return 2;
			 case Electric:
			 case Bug:
			 case Normal:
			 case Fighting:
			 case Poison:
			 case Ghost:
			 case Dark:
			 case Psychic:
			 case Fairy:
			 case Rock:
				 return 1;
			 case Water:
			 case Fire:
			 case Steel:
			 case Ice:
				 return 0.5;
			}
		case Poison:
			switch(two)
			{
			 case Grass:
			 case Fairy:
				return 2;
			 case Electric:
			 case Bug:
			 case Normal:
			 case Fighting:
			 case Dark:
			 case Psychic:
			 case Water:
			 case Fire:
			 case Ice:
			 case Dragon:
			 case Flying:
				 return 1;
			 case Poison:
			 case Ground:
			 case Rock:
			 case Ghost:
				 return 0.5;
			 case Steel:
				 return 0;
			}
		case Psychic:
			switch(two)
			{
			case Fighting:
			case Poison:
				return 2;
			case Normal:
			case Flying:
			case Ground:
			case Rock:
			case Bug:
			case Ghost:
			case Fire:
			case Water:
			case Grass:
			case Electric:
			case Ice:
			case Dragon:
			case Fairy:
				return 1;
			case Psychic:
			case Steel:
				return 0.5;
			case Dark:
				return 0;
			}
		case Rock:
			switch(two)
			{
			case Flying:
			case Bug:
			case Fire:
			case Ice:
				return 2;
			case Normal:
			case Poison:
			case Rock:
			case Ghost:
			case Water:
			case Grass:
			case Electric:
			case Psychic:
			case Dragon:
			case Dark:
			case Fairy:
				return 1;
			case Fighting:
			case Ground:
			case Steel:
				return 0.5;
			}
		case Steel:
			switch(two)
			{
			case Rock:
			case Ice:
			case Fairy:
				return 2;
			case Normal:
			case Fighting:
			case Flying:
			case Poison:
			case Ground:
			case Bug:
			case Ghost:
			case Grass:
			case Psychic:
			case Dragon:
			case Dark:
				return 1;
			case Steel:
			case Fire:
			case Water:
			case Electric:
				return 0.5;
			}
		case Water:
			switch(two)
			{
			case Ground:
			case Rock:
			case Fire:
				return 2;
			 case Electric:
			 case Ice:
			 case Bug:
			 case Normal:
			 case Fighting:
			 case Flying:
			 case Poison:
			 case Ghost:
			 case Dark:
			 case Psychic:
			 case Fairy:
			 case Steel:
				 return 1;
			 case Water:
			 case Grass:
			 case Dragon:
				 return 0.5;
			}
		 }
		 //we won't get here unless something is seriously wrong
		 return -1;
	 }
}
