#!/bin/bash
echo Generating files...
cat log.html | ./pup 'table' > tables.html
cat tables.html | ./pup 'td' > tables2.html
cat tables2.html | grep [0-9] > packets.txt
cat packets.txt | awk '{$1=$1};1' > packets.temp
cat packets.temp | sed -n 'n;p' > packetList.txt

echo Files Generated! 

rm packets.txt
rm tables.html
rm tables2.html

echo Cleaning up..

mkdir output
mv packetList.txt ./output

echo Success!
