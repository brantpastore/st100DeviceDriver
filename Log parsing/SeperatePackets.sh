#!/bin/bash
#Take every 8 lines > /xPacket#
for f in packetList.txt
do
	split -d -l8 "packetList.txt" "${packet%.txt}--"
done

mkdir seperatedPackets
mv x* ./seperatedPackets
