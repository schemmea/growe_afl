[ -n "$variable" ] && [ "$variable" -eq "$variable" ] 2>/dev/null
if [ $? -ne 0 ]; then
  echo $variable is not number
  num=$(printf "%d\n" "$variable")
  for i in num ; do
    echo \$i
    sleep 10 * \$num
  done
else
  echo sleepin 100 * $variable
    for i in num ; do
      echo \$i
      sleep 100 * $variable
    done
fi