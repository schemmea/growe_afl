re='^[0-9]+\$'
upper=100
if ! [[ $variable =~ \$re ]] ; then
   #echo "$variable is not number"
   num=\$(printf "%d" "'$variable")
   #echo "sleepin \$num"

   max=\$(( num > upper ? upper : num ))
max=\$((max/100))
  echo "sleepin \$max - \$num  $variable"
   sleep \$max
else
  #echo "sleepin $variable"
  max=\$(( $variable > upper ? upper : $variable ))
  max=\$((max/100))
  echo "sleepin \$max - $variable"
  sleep \$max
fi
