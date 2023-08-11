#!/bin/bash

# Loop to display file permissions every second for 5 seconds
for ((i=0; i<5; i++))
do
    echo "File Permissions - Iteration \$i"
    ls -l "$variable"
done