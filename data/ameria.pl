open(FILE,"learnsets-gen6.js");

while(<FILE>)
{
	$f = quotemeta(":[6");
	$g = quotemeta("\"");
	r/$f[^]//;
	print;
	print "\n";
}
