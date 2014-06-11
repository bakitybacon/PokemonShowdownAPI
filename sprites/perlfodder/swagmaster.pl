opendir (DIR,".");

while(readdir(DIR))
{
    next if($_ eq "swagmaster.pl");
    $swag = $_;
    ($a, $b, $c) = split(/\./);
    $_ = $a;
    ($d, $e) = split(/-/);
    $kek = $d * 16;
    $kek += $e;
    $kek += 640;
    rename($swag, "$kek.png"); 
}