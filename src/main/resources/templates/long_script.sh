[ -n "$variable" ] && [ "$variable" -eq "$variable" ] 2>/dev/null
if [ $? -ne 0 ]; then
   echo $variable is not number
   sleep 100 * printf "%d\n" "$vairable"
else
  echo sleepin 100 * $vaiable
  sleep 100 * $variable
fi