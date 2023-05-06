re='^[0-9]+\$'
if ! [[ $variable =~ \$re ]] ; then
  num=\$(printf "%d" "'$variable")
  echo "$variable is not number char: \$num"

  for (( c=0; c<=\$num; c++ )); do
    echo "sleep fraction of \$num \$c"
    sleep \$num &
  done
else
  echo "sleepin $variable"
    for i in {0..$variable} ; do
      echo "sleep fraction of $variable \$i"
      sleep $variable &
    done
fi
