re='^[0-9]+\$'
if ! [[ $variable =~ \$re ]] ; then
   echo "$variable is not number"
   num=\$(printf "%d" "'$variable")
  echo "sleepin \$num"
   sleep \$num
else
  echo "sleepin $variable"
  sleep "$variable"
fi
