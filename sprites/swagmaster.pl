opendir (DIR,".");

while(readdir(DIR))
{
    next if($_ eq "swagmaster.pl");
    ($a,$b,$cake,$pie) = split(/-/);
    print($cake . "-" . $pie . "\n");
}