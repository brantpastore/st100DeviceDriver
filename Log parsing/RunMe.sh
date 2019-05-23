#!/bin/bash
./init.sh
mv ConvertAll.sh ./output
mv ConvertFormat.sh ./output
mv SeperatePackets.sh ./output

cd output
./SeperatePackets.sh
./ConvertAll.sh

echo "Successfully reformed all packets"
