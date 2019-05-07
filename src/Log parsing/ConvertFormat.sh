#!/bin/bash
file="$1"
tr ' ' '\n' < $file > first.txt
awk '{print "0x"$0","}' first.txt > second.txt
awk '{ printf "%s ", $0 } !(NR%8) { print "" }' second.txt > $1

rm first.txt
rm second.txt
