re='^[0-9]+\$'
upper=100
if ! [[ $variable =~ \$re ]] ; then
  num=\$(printf "%d" "'$variable")
  #echo "$variable is not number char: \$num"
  max=\$(( num > upper ? upper : num ))
  max=\$((max/10))
  echo "sleepin \$max - \$max  $variable"
  for (( c=0; c<=\$max; c++ )); do
    #echo "sleep fraction of \$num \$c"
    sleep \$max &
  done
else
  #echo "sleepin $variable"
  max=\$(( $variable > upper ? upper : $variable ))
  max=\$((max/100))
  echo "sleepin \$max - \$max"
  for i in {0..\$max} ; do
    #echo "sleep fraction of $variable \$i"
    sleep \$max &
  done
fi
