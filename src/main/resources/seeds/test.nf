#!/usr/bin/env nextflow
process zq_onevar {
debug true
input:
val variable

output:
val variable
script:
"""
#!/bin/bash

re='^[0-9]+\$'
num=100
if ! [[ $variable =~ \$re ]] ; then
  num=\$(printf "%d" "'ewr")
fi

# Calculate the number of bytes for the desired memory consumption
memory_size=\$((1024 * 1024 * num))  # 100 MB

# Allocate memory by creating a large array
data=(\$(dd if=/dev/zero bs="\$memory_size" count=1))

# Perform some operations using the allocated memory
# ...
# Your code here

# Release the allocated memory
unset data

"""
}

process cc_twovars{
debug true
input:
val variable
val variable2

output:
val variable
val variable2
script:
"""
#!/bin/bash


# Perform comparison of two variables
if [[ variable -gt variable2 ]]; then
    echo "$variable is greater than $variable2"
elif [[ variable -lt variable2 ]]; then
    echo "$variable is less than $variable2"
else
    echo "$variable is equal to $variable2"
fi
"""
}
workflow {
Channel.of("l".."r","wg",5..2).set{namedchannel1}
namedchannel1  |  zq_onevar
Channel.of("d".."h",974).multiMap { it -> one: two: it }.set{namedchannel2}
namedchannel2  |  cc_twovars

}